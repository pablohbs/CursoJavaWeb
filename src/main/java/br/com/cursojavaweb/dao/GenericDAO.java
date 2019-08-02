package br.com.cursojavaweb.dao;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.cursojavaweb.conexao.HibernateUtil;

/**
 * Entidade Generica para ser usada no GenericDAO
 * 
 * @author Pablo Henrique
 *
 * @param <Entidade>
 */
public class GenericDAO<Entidade> {
	private Class<Entidade> classe;
	private Entidade retornaId;

	@SuppressWarnings("unchecked")
	public GenericDAO() {
		this.classe = (Class<Entidade>) ((ParameterizedType) getClass().getGenericSuperclass())
				.getActualTypeArguments()[0];
	}

	/**
	 * Método salvar Generico, que é implementado no GenericDAO
	 * 
	 * @author Pablo Henrique
	 * @param entidade
	 */
	public void salvar(Entidade entidade) {
		Session sessao = HibernateUtil.getConexaoBaseLocal().openSession();
		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction();
			sessao.save(entidade);
			transacao.commit();
		} catch (RuntimeException erro) {
			if (transacao != null) {
				transacao.rollback();
			}
			throw erro;
		} finally {
			sessao.close();
		}
	}

	/**
	 * Método listar Generico, que é implementado no GenericDAO
	 * 
	 * @author Pablo Henrique
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Entidade> listar() {
		Session sessao = HibernateUtil.getConexaoBaseLocal().openSession();
		try {
			Criteria consulta = sessao.createCriteria(classe);
			List<Entidade> resultado = consulta.list();
			return resultado;
		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	}

	/**
	 * Método excluir Generico, que é implementado no GenericDAO
	 * 
	 * @author Pablo Henrique
	 * @param entidade
	 */
	public void excluir(Entidade entidade) {
		Session sessao = HibernateUtil.getConexaoBaseLocal().openSession();
		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction();
			sessao.delete(entidade);
			transacao.commit();
		} catch (RuntimeException erro) {
			if (transacao != null) {
				transacao.rollback();
			}
			throw erro;
		} finally {
			sessao.close();
		}
	}

	/**
	 * Método editar Generico, que é implementado no GenericDAO
	 * 
	 * @author Pablo Henrique
	 * @param entidade
	 */
	public void editar(Entidade entidade) {
		Session sessao = HibernateUtil.getConexaoBaseLocal().openSession();
		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction();
			sessao.update(entidade);
			transacao.commit();
		} catch (RuntimeException erro) {
			if (transacao != null) {
				transacao.rollback();
			}
			throw erro;
		} finally {
			sessao.close();
		}
	}

	/**
	 * Método insert/update Generico, Este método é especial, pois se o
	 * formulario for carregado sem dado, ele insere. Mas se o formulario for
	 * carregado já populado com dados, ele faz update
	 * 
	 * @author Pablo Henrique
	 * @param entidade
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Entidade merge(Entidade entidade) {
		Session sessao = HibernateUtil.getConexaoBaseLocal().openSession();
		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction();
			Entidade retorno = (Entidade) sessao.merge(entidade);
			transacao.commit();
			retornaId = retorno;
			return retorno;
		} catch (RuntimeException erro) {
			if (transacao != null) {
				transacao.rollback();
			}
			throw erro;
		} finally {
			sessao.close();
		}
	}

	public Class<Entidade> getClasse() {
		return classe;
	}
	public void setClasse(Class<Entidade> classe) {
		this.classe = classe;
	}
	public Entidade getRetornaId() {
		return retornaId;
	}
}
package br.com.cursojavaweb.conexao;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class HibernateContexto implements ServletContextListener {

	/**
	 * Aqui ele destroi a fábrica de sessões do Hibernate, fechando a conexão
	 * 
	 * @author Pablo Henrique
	 */
	@Override
	public void contextDestroyed(ServletContextEvent evento) {
		HibernateUtil.getConexaoBaseLocal().close();
		//HibernateUtil.getFabricaDeSessoes().close();

	}
	
	/**
	 * Aqui ele cria a fábrica de sessões do Hibernate, fabrica esse que será
	 * usada no DAO
	 * 
	 * @author Pablo Henrique
	 */
	@Override
	public void contextInitialized(ServletContextEvent evento) {
		HibernateUtil.getConexaoBaseLocal();
	}

}
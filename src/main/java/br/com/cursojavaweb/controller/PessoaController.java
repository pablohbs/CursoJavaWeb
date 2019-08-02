package br.com.cursojavaweb.controller;

import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.primefaces.PrimeFaces;

import br.com.cursojavaweb.dao.PessoaContatoDAO;
import br.com.cursojavaweb.dao.PessoaDAO;
import br.com.cursojavaweb.enuns.EnumSituacao;
import br.com.cursojavaweb.mensagem.MsgFeedBackUsuario;
import br.com.cursojavaweb.model.Pessoa;
import br.com.cursojavaweb.model.PessoaContato;
import br.com.cursojavaweb.relatorio.GerarRelatorio;

@ManagedBean
@ViewScoped
public class PessoaController implements Serializable {
	private static final long serialVersionUID = -2032557165451308430L;

	private EnumSituacao enumSituacao;
	private List<EnumSituacao> listaEnumSituacao = Arrays.asList(EnumSituacao.values());
	
	private Pessoa pessoa;
	private List<Pessoa> listaPessoa;
	private PessoaDAO pessoaDAO = new PessoaDAO();
	
	private PessoaContato contato;
	private List<PessoaContato> listaContato;
	private PessoaContatoDAO contatoDAO = new PessoaContatoDAO();

	@PostConstruct
	public void listarPessoa() {
		listaPessoa = pessoaDAO.listar();
	}

	public void novaPessoa() {
		pessoa = new Pessoa();
		
		listaContato = new ArrayList<>();
	}

	public void salvarPessoa() {
		try {

			if (pessoa.getId() == null) {
				pessoaDAO.merge(pessoa);
				for (PessoaContato contatoTemp : listaContato) {
					contatoTemp.setPessoa(pessoaDAO.getRetornaId());
					contatoDAO.merge(contatoTemp);
				}
			} else {
				pessoaDAO.merge(pessoa);
			}

			this.listarPessoa();
			MsgFeedBackUsuario.AdicionaMensagemSucesso("Pessoa Gravada com Sucesso!");
			
			PrimeFaces.current().executeScript("PF('dlgPessoa').hide();");

		} catch (RuntimeException erro) {
			MsgFeedBackUsuario.AdicionaMensagemErro("Erro " + erro.getMessage());
			erro.printStackTrace();
		}
	}

	public void editarPessoa(ActionEvent event) {
		pessoa = (Pessoa) event.getComponent().getAttributes().get("pessoaSelecionada");
		
		listaContato = pessoaDAO.listarContato(pessoa.getId());
		listaEnumSituacao = Arrays.asList(EnumSituacao.values());
	}

	public void excluirPessoa(ActionEvent event) {
		pessoa = (Pessoa) event.getComponent().getAttributes().get("pessoaSelecionada");
		try {
			pessoaDAO.excluir(pessoa);
			this.listarPessoa();

		} catch (RuntimeException erro) {
			MsgFeedBackUsuario.AdicionaMensagemErro("Erro " + erro.getMessage());
			erro.printStackTrace();
		}
	}

	public void novoContato() {
		contato = new PessoaContato();
	}

	public void salvarContato() {
		try {
			if (pessoa.getId() == null) {
				listaContato.add(contato);

			} else {
				contato.setPessoa(pessoa);
				contatoDAO.merge(contato);
				listaContato = pessoaDAO.listarContato(pessoa.getId());
			}
		} catch (RuntimeException erro) {
			MsgFeedBackUsuario.AdicionaMensagemErro("Erro " + erro.getMessage());
			erro.printStackTrace();
		}
	}

	public void excluirContato(ActionEvent event) {
		contato = (PessoaContato) event.getComponent().getAttributes().get("contatoSelecionado");
		try {
			if (pessoa.getId() == null) {
				int achou = -1;
				for (int posicao = 0; posicao < listaContato.size(); posicao++) {
					if (listaContato.get(posicao).getDescricao().equals(contato.getDescricao())) {
						achou = posicao;
					}
				}
				if (achou > -1) {
					listaContato.remove(achou);
				}
			} else {
				contatoDAO.excluir(contato);
				listaContato = pessoaDAO.listarContato(pessoa.getId());
			}
		} catch (RuntimeException erro) {
			MsgFeedBackUsuario.AdicionaMensagemErro("Erro " + erro.getMessage());
			erro.printStackTrace();
		}
	}
	
	public void gerar(ActionEvent evento) throws SQLException {
		pessoa = (Pessoa) evento.getComponent().getAttributes().get("pessoaSelecionada");
		try {

			String caminho = "/report/pessoa.jasper";
			String nomePDF = "inline;filename=Pessoa-" + pessoa.getId() + ".pdf";

			GerarRelatorio.gerar(pessoa.getId(), "IDPESSOA", nomePDF, caminho);
		} catch (IOException erro) {
			MsgFeedBackUsuario.AdicionaMensagemErro("Erro " + erro.getMessage());
			erro.printStackTrace();
		}
	}
	
	public void pesquisar() {
		try {
			listaPessoa = pessoaDAO.pesquisaPessoaPorStatus(enumSituacao);
			
		} catch (RuntimeException erro) {
			MsgFeedBackUsuario.AdicionaMensagemErro("Erro " + erro.getMessage());
			erro.printStackTrace();
		}
	}

	public Pessoa getPessoa() {
		if (pessoa == null) {
			pessoa = new Pessoa();
		}
		return pessoa;
	}
	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
	public List<Pessoa> getListaPessoa() {
		return listaPessoa;
	}
	public void setListaPessoa(List<Pessoa> listaPessoa) {
		this.listaPessoa = listaPessoa;
	}
	public PessoaDAO getPessoaDAO() {
		return pessoaDAO;
	}
	public void setPessoaDAO(PessoaDAO pessoaDAO) {
		this.pessoaDAO = pessoaDAO;
	}
	public PessoaContato getContato() {
		return contato;
	}
	public void setContato(PessoaContato contato) {
		this.contato = contato;
	}
	public List<PessoaContato> getListaContato() {
		return listaContato;
	}
	public void setListaContato(List<PessoaContato> listaContato) {
		this.listaContato = listaContato;
	}
	public EnumSituacao getEnumSituacao() {
		return enumSituacao;
	}
	public void setEnumSituacao(EnumSituacao enumSituacao) {
		this.enumSituacao = enumSituacao;
	}
	public List<EnumSituacao> getListaEnumSituacao() {
		return listaEnumSituacao;
	}
	public void setListaEnumSituacao(List<EnumSituacao> listaEnumSituacao) {
		this.listaEnumSituacao = listaEnumSituacao;
	}
}

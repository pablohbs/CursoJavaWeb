package br.com.cursojavaweb.mensagem;

import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;

public class MsgFeedBackUsuario {

	/**
	 * Mensagem de sucesso para exibir no browser ao usuario final após uma operação de insert, update ou delete
	 * 
	 * @author Pablo Henrique
	 * @param mensagem
	 */
	public static void AdicionaMensagemSucesso(String mensagem) {
		FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, mensagem, mensagem);
		FacesContext facesContext = FacesContext.getCurrentInstance();

		ExternalContext externalContext = facesContext.getExternalContext();
		Flash flash = externalContext.getFlash();
		flash.setKeepMessages(true);

		facesContext.addMessage(null, facesMessage);
	}

	/**
	 * Mensagem de erro para exibir no browser ao usuario final após uma operação de insert, update ou delete Lembrando que aqui ele reportará a exceção
	 * 
	 * @author Pablo Henrique
	 * @param mensagem
	 */
	public static void AdicionaMensagemErro(String mensagem) {
		FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, mensagem, mensagem);
		FacesContext facesContext = FacesContext.getCurrentInstance();

		ExternalContext externalContext = facesContext.getExternalContext();
		Flash flash = externalContext.getFlash();
		flash.setKeepMessages(true);

		facesContext.addMessage(null, facesMessage);
	}

	public static String getParam(String nome) {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ExternalContext externalContext = facesContext.getExternalContext();

		Map<String, String> parametros = externalContext.getRequestParameterMap();
		String valor = parametros.get(nome);
		return valor;
	}
}

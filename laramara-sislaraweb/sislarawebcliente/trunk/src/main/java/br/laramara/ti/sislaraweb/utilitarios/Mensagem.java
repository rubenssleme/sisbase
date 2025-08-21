package br.laramara.ti.sislaraweb.utilitarios;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;

public class Mensagem {

	private static synchronized void exibirMensagem(Severity severidade, String sumario, String detalhe) {
		FacesMessage message = new FacesMessage(sumario, detalhe);
		if (severidade != null) {
			message.setSeverity(severidade);
		}
		FacesContext.getCurrentInstance().addMessage(null, message);
	}
	
	public static synchronized void exibirMensagem(String mensagem) {
		exibirMensagem(FacesMessage.SEVERITY_INFO, mensagem, "");
	}

	public static synchronized void exibirMensagemErro(String erro) {
		exibirMensagem(FacesMessage.SEVERITY_ERROR, erro, "");
	}
}

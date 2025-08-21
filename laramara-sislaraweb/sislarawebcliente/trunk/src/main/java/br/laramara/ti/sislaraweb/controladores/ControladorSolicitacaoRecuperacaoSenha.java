package br.laramara.ti.sislaraweb.controladores;

import java.io.Serializable;

import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
public class ControladorSolicitacaoRecuperacaoSenha extends Controlador implements Serializable {
	private static final long serialVersionUID = -5439582789088844617L;

	private String email;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void solicitar(ActionEvent actionEvent) {
		exibirResultado(servicoSisLaraClient.efetuarSolicitacaoRecuperacaoSenha(email));
	}

}

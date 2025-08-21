package br.laramara.sistelemarketingserver.dominio.telefonia;

import br.laramara.sistelemarketingserver.dominio.contato.Telefone;

public class Ligacao {

	private String token;
	private Telefone telefone;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Telefone getTelefone() {
		return telefone;
	}

	public void setTelefone(Telefone telefone) {
		this.telefone = telefone;
	}
	
	public String obterTelefoneCompleto() {
		return telefone.obterTelefoneCompleto();
	}
}

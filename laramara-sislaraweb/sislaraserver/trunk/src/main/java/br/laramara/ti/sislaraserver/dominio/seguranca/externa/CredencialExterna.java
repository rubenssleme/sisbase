package br.laramara.ti.sislaraserver.dominio.seguranca.externa;

import br.laramara.ti.sislaracommons.utilitarios.Criptografia;

public class CredencialExterna {
	private String email;
	private String senha;

	public CredencialExterna(String email, String senha) {
		setEmail(email);
		setSenha(senha);
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = Criptografia.converterParaMD5(senha);
	}

	@Override
	public String toString() {
		return "CredencialExterna [email=" + email + ", senha=" + senha + "]";
	}

}

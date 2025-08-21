package br.laramara.ti.sislaraserver.dominio.seguranca;

import br.laramara.ti.sislaracommons.utilitarios.Criptografia;

public class CredencialExterna {
	private String email;
	private String senha;

	public CredencialExterna(String email, String senha) {
		this.email = email;
		this.senha = Criptografia.converterParaMD5(senha);
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
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CredencialExterna other = (CredencialExterna) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (senha == null) {
			if (other.senha != null)
				return false;
		} else if (!senha.equals(other.senha))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "CredencialExterna [email=" + email + ", senha=" + senha + "]";
	}

}

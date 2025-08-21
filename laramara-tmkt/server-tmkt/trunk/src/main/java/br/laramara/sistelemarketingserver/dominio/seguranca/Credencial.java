package br.laramara.sistelemarketingserver.dominio.seguranca;

import br.laramara.sistelemarketingcommons.utilitarios.Criptografia;
import br.laramara.sistelemarketingserver.dominio.telefonia.Ramal;

public class Credencial {
	private String usuario;
	private String senha;
	private Ramal ramal;

	public Credencial(String usuario, String senha, Ramal ramal) {
		this.usuario = usuario;
		this.senha = Criptografia.converterParaMD5(senha);
		this.ramal = ramal;
	}

	public String getUsuario() {
		return usuario;
	}

	public String getSenha() {
		return senha;
	}
	
	public Ramal getRamal() {
		return ramal;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Credencial other = (Credencial) obj;
		if (senha == null) {
			if (other.senha != null)
				return false;
		} else if (!senha.equals(other.senha))
			return false;
		if (usuario == null) {
			if (other.usuario != null)
				return false;
		} else if (!usuario.equals(other.usuario))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Credencial [contaAcesso=" + usuario + ", ramal=" + ramal + "]";
	}
}

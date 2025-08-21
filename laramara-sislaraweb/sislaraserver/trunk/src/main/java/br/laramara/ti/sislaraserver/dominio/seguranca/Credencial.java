package br.laramara.ti.sislaraserver.dominio.seguranca;

import br.laramara.ti.sislaracommons.utilitarios.Criptografia;

public class Credencial {
	private String usuario;
	private String senha;
	private String confirmacaoSenha;

	public Credencial(String usuario, String senha, String confirmacaoSenha) {
		this.usuario = usuario;
		this.senha = Criptografia.converterParaMD5(senha);
		this.confirmacaoSenha = Criptografia.converterParaMD5(confirmacaoSenha);
	}

	public String getUsuario() {
		return usuario;
	}

	public String getSenha() {
		return senha;
	}
	
	public String getConfirmacaoSenha() {
		return confirmacaoSenha;
	}

	public boolean novaSenhaValida() {
		return confirmacaoSenha != null && senha != null
				&& senha.equals(confirmacaoSenha);
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
		return "Credencial [contaAcesso=" + usuario + "]";
	}
}

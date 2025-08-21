package br.laramara.ti.sislaracommons.dtos.seguranca;

import br.laramara.ti.sislaracommons.dtos.ModeloDTO;

public class CredencialDTO extends ModeloDTO {
	private static final long serialVersionUID = -7707628886965980316L;

	private String usuario;
	private String senha;
	private String confirmacaoSenha;

	public CredencialDTO(String usuario, String senha, String confirmacaoSenha) {
		this.usuario = usuario;
		this.senha = senha;
		this.confirmacaoSenha = confirmacaoSenha;
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
}

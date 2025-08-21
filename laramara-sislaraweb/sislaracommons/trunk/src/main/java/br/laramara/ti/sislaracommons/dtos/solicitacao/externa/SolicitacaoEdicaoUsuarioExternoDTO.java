package br.laramara.ti.sislaracommons.dtos.solicitacao.externa;

import br.laramara.ti.sislaracommons.dtos.usuario.externo.UsuarioExternoDTO;

public class SolicitacaoEdicaoUsuarioExternoDTO {
	private String token;
	private UsuarioExternoDTO usuarioExternoDto;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public UsuarioExternoDTO getUsuarioExternoDto() {
		return usuarioExternoDto;
	}

	public void setUsuarioExternoDto(UsuarioExternoDTO usuarioExternoDto) {
		this.usuarioExternoDto = usuarioExternoDto;
	}

}
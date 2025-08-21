package br.laramara.ti.sislaraserver.dominio.seguranca;

import br.laramara.ti.sislaracommons.dtos.usuario.UsuarioDTO;

public class ChaveBloqueioUsuario extends ChaveBloqueio{

	private static final long serialVersionUID = 5197521824147654588L;

	public ChaveBloqueioUsuario(String identificacao) {
		super(identificacao);
	}

	public ChaveBloqueioUsuario(UsuarioDTO usuarioDto) {
		super(usuarioDto.getId().toString());
	}
}

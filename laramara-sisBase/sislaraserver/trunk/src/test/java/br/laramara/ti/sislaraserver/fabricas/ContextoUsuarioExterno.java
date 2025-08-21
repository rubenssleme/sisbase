package br.laramara.ti.sislaraserver.fabricas;

import br.laramara.ti.sislaracommons.dtos.usuario.UsuarioExternoDTO;
import br.laramara.ti.sislaraserver.dominio.usuario.UsuarioExterno;

public class ContextoUsuarioExterno {

	public static UsuarioExterno fabricarUsuarioExternoComTodosOsDados() {
		UsuarioExterno usuarioExterno = new UsuarioExterno();
		
		usuarioExterno.setId(1111L);
		usuarioExterno.setEmail("usuario.externo@gmail.com");
		usuarioExterno.setSenha("1234");
		usuarioExterno.setBloqueado(false);
		
		return usuarioExterno;
	}

	public static UsuarioExternoDTO fabricarUsuarioExternoDTOComTodosOsDados() {
		UsuarioExternoDTO usuarioExternoDTO = new UsuarioExternoDTO();
		
		usuarioExternoDTO.setId(1111L);
		usuarioExternoDTO.setEmail("usuario.externo@gmail.com");
		usuarioExternoDTO.setSenha("1234");
		usuarioExternoDTO.setBloqueado(false);
		
		return usuarioExternoDTO;
	}
}

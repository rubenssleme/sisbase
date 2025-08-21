package br.laramara.ti.sislaraserver.repositorios;

import br.laramara.ti.sislaraserver.dominio.seguranca.CredencialExterna;
import br.laramara.ti.sislaraserver.dominio.usuario.UsuarioExterno;

public interface RepositorioUsuarioExterno {

	UsuarioExterno obterUsuarioExterno(CredencialExterna credencialExterna);

	UsuarioExterno salvar(UsuarioExterno usuarioExterno);

	UsuarioExterno obterPorId(Long id);

}

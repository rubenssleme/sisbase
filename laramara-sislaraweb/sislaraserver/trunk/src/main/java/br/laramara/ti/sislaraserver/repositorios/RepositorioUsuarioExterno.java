package br.laramara.ti.sislaraserver.repositorios;

import br.laramara.ti.sislaraserver.dominio.seguranca.externa.CredencialExterna;
import br.laramara.ti.sislaraserver.dominio.usuario.externo.UsuarioExterno;

public interface RepositorioUsuarioExterno {

	UsuarioExterno obterUsuarioExterno(CredencialExterna credencialExterna);

	UsuarioExterno salvar(UsuarioExterno usuarioExterno);

	UsuarioExterno obterPorId(Long id);

	boolean cpfJaExiste(String cpf);
	
	UsuarioExterno obterPorEmail(String emailSolicitante);

	UsuarioExterno obterPorToken(String token);

}

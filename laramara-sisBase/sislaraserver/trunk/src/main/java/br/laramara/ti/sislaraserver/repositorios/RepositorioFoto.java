package br.laramara.ti.sislaraserver.repositorios;

import br.laramara.ti.sislaraserver.dominio.usuario.Usuario;
import br.laramara.ti.sislaraserver.dominio.usuario.UsuarioComFoto;

public interface RepositorioFoto {
	public void salvar(Usuario usuario, byte[] foto);

	public byte[] obterFoto(UsuarioComFoto usuarioComFoto);
}

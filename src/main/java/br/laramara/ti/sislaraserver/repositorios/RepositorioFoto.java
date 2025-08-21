package br.laramara.ti.sislaraserver.repositorios;

import br.laramara.ti.sislaraserver.dominio.usuario.UsuarioComFoto;
import br.laramara.ti.sislaraserver.dominio.usuario.Usuario;

public interface RepositorioFoto {
	public void salvar(Usuario usuario, byte[] foto);

	public byte[] obterFoto(UsuarioComFoto usuarioComFoto);
}

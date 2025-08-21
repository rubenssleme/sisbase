package br.laramara.ti.sislaraserver.repositorios;

import java.util.List;

import br.laramara.ti.sislaraserver.dominio.usuario.Usuario;

public interface RepositorioUsuario {
	public Usuario salvar(Usuario usuarioASalvar);

	public Usuario obterPorId(Long prontuario);

	public List<Usuario> pesquisarPorNome(String nome);
	
	public List<Usuario> pesquisarPorFamiliar(String nome);
	
	public List<Usuario> pesquisarPorRG(String rg);

	public List<Usuario> obterTodos();
}

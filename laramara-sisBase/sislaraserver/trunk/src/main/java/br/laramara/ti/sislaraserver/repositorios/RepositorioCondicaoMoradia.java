package br.laramara.ti.sislaraserver.repositorios;

import java.util.List;

import br.laramara.ti.sislaraserver.dominio.usuario.CondicaoMoradia;

public interface RepositorioCondicaoMoradia {
	public List<CondicaoMoradia> obterTodos();
}

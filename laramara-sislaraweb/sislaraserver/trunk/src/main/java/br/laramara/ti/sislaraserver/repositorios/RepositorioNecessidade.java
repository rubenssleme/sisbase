package br.laramara.ti.sislaraserver.repositorios;

import java.util.List;

import br.laramara.ti.sislaraserver.dominio.usuario.Necessidade;

public interface RepositorioNecessidade {
	public List<Necessidade> obterTodos();
}

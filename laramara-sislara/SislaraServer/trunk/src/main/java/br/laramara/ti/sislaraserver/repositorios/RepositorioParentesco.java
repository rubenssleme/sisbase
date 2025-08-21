package br.laramara.ti.sislaraserver.repositorios;

import java.util.List;

import br.laramara.ti.sislaraserver.dominio.familiar.Parentesco;

public interface RepositorioParentesco {
	public List<Parentesco> obterTodos();
}

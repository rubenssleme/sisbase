package br.laramara.ti.sislaraserver.repositorios;

import java.util.List;

import br.laramara.ti.sislaraserver.dominio.escola.Escolaridade;

public interface RepositorioEscolaridade {
	public List<Escolaridade> obterTodos();
}

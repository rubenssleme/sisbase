package br.laramara.ti.sislaraserver.repositorios;

import java.util.List;

import br.laramara.ti.sislaraserver.dominio.usuario.Expectativa;

public interface RepositorioExpectativa {
	public List<Expectativa> obterTodos();
}

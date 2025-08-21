package br.laramara.ti.sislaraserver.repositorios;

import java.util.List;

import br.laramara.ti.sislaraserver.dominio.trabalho.Cargo;

public interface RepositorioCargo {
	public List<Cargo> obterTodos();
}

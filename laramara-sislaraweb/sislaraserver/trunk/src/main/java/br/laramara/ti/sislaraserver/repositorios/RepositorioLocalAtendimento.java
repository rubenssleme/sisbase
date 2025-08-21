package br.laramara.ti.sislaraserver.repositorios;

import java.util.List;

import br.laramara.ti.sislaraserver.dominio.grupo.LocalAtendimento;

public interface RepositorioLocalAtendimento {
	public List<LocalAtendimento> obterTodos();
}

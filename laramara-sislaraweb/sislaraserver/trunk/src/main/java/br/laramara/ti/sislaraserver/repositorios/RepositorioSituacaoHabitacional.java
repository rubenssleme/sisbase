package br.laramara.ti.sislaraserver.repositorios;

import java.util.List;

import br.laramara.ti.sislaraserver.dominio.usuario.SituacaoHabitacional;

public interface RepositorioSituacaoHabitacional {
	public List<SituacaoHabitacional> obterTodos();
}

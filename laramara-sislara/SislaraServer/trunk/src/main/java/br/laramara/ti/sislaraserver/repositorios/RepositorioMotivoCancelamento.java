package br.laramara.ti.sislaraserver.repositorios;

import java.util.List;

import br.laramara.ti.sislaraserver.dominio.agenda.MotivoCancelamento;

public interface RepositorioMotivoCancelamento {
	public List<MotivoCancelamento> obterTodosAgendamento();
	public List<MotivoCancelamento> obterTodosDemanda();
	public MotivoCancelamento obterPorId(Long id);
}

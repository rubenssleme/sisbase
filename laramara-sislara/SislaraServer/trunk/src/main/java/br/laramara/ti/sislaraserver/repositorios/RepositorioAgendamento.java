package br.laramara.ti.sislaraserver.repositorios;

import java.util.Calendar;
import java.util.List;

import br.laramara.ti.sislaraserver.dominio.agenda.Agendamento;
import br.laramara.ti.sislaraserver.dominio.agenda.EspecificacaoPesquisaAgendamento;
import br.laramara.ti.sislaraserver.dominio.grupo.Profissional;

public interface RepositorioAgendamento {
	public Agendamento obterPorId(Long id);
	public List<Agendamento> obterPor(EspecificacaoPesquisaAgendamento especificacaoPesquisaAgendamento);
 	public List<Agendamento> obterTodosPor(Profissional profissional, Calendar data);
	public List<Agendamento> salvar(List<Agendamento> agendamentos);
	public Agendamento salvar(Agendamento agendamento);
}

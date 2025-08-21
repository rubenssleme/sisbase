package br.laramara.ti.sislaraserver.repositorios;

import br.laramara.ti.sislaraserver.dominio.agenda.Agendamento;
import br.laramara.ti.sislaraserver.dominio.atendimento.AtendimentoIndividual;
import br.laramara.ti.sislaraserver.dominio.espera.Espera;
import br.laramara.ti.sislaraserver.dominio.grupo.DescricaoTipoAtendimento;
import br.laramara.ti.sislaraserver.dominio.grupo.Modulo;

public interface RepositorioBloqueio {

	public boolean existeBloqueioListaEspera(Espera espera);

	public boolean existeBloqueioAgendamento(Agendamento agendamento);

	public boolean existeBloqueioAgendamento(
			DescricaoTipoAtendimento descricaoTipoAtendimento, Modulo modulo);

	public boolean existeLiberacaoAtendimentoIndivisualSimultaneo(
			AtendimentoIndividual atendimentoIndividual);
}

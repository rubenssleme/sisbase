package br.laramara.ti.sislaraserver.repositorios;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import br.laramara.ti.sislaraserver.dominio.agenda.Agendamento;
import br.laramara.ti.sislaraserver.dominio.atendimento.AtendimentoIndividual;
import br.laramara.ti.sislaraserver.dominio.espera.Espera;
import br.laramara.ti.sislaraserver.dominio.grupo.DescricaoTipoAtendimento;
import br.laramara.ti.sislaraserver.dominio.grupo.Modulo;
import br.laramara.ti.sislaraserver.dominio.seguranca.Area;
import br.laramara.ti.sislaraserver.dominio.seguranca.Bloqueio;

@Repository
public class RepositorioBloqueioBD extends RepositorioDB<Bloqueio> implements
		RepositorioBloqueio {

	private boolean existeBloqueio(
			DescricaoTipoAtendimento descricaoTipoAtendimento, Modulo modulo,
			Area area) {
		List<Bloqueio> bloqueios = new ArrayList<>();
		try {
			TypedQuery<Bloqueio> query = em
					.createQuery(
							"SELECT p FROM Bloqueio p WHERE p.descricaoTipoAtendimento = :descricaoTipoAtendimento AND p.modulo = :modulo AND p.area = :area",
							Bloqueio.class);
			query.setParameter("descricaoTipoAtendimento",
					descricaoTipoAtendimento);
			query.setParameter("modulo", modulo);
			query.setParameter("area", area);
			bloqueios = query.getResultList();
		} catch (Exception e) {
			logger.error("Não foi possível obter a lista de Bloqueio. \nDetalhe:"
					+ e);
		}
		return !bloqueios.isEmpty();
	}

	@Override
	public boolean existeBloqueioListaEspera(Espera espera) {
		return existeBloqueio(espera.getDescricaoTipoAtendimento(),
				espera.getModulo(), Area.LISTA_ESPERA);
	}

	@Override
	public boolean existeBloqueioAgendamento(
			DescricaoTipoAtendimento descricaoTipoAtendimento, Modulo modulo) {
		return existeBloqueio(descricaoTipoAtendimento, modulo, Area.AGENDA);
	}

	@Override
	public boolean existeBloqueioAgendamento(Agendamento agendamento) {
		return existeBloqueio(agendamento.getDescricaoTipoAtendimento(),
				agendamento.getModulo(), Area.AGENDA);
	}
	
	@Override
	public boolean existeLiberacaoAtendimentoIndivisualSimultaneo(
			AtendimentoIndividual atendimentoIndividual) {
		return existeBloqueio(
				atendimentoIndividual.getDescricaoTipoAtendimento(),
				atendimentoIndividual.getModulo(),
				Area.LIBERACAO_ATENDIMENTO_INDIVIDUAL_SIMULTANEO);
	}
}

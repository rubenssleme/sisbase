package br.laramara.ti.sislaraserver.repositorios;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import br.laramara.ti.sislaraserver.dominio.agenda.MotivoCancelamento;

@Repository
public class RepositorioMotivoCancelamentoBD extends
		RepositorioDB<MotivoCancelamento> implements
		RepositorioMotivoCancelamento {

	private static final String SQL_BASE = "SELECT i FROM MotivoCancelamento i";
	private static final String SQL_CONDICAO_AGENDAMENTO = " WHERE agendamento is TRUE";
	private static final String SQL_CONDICAO_DEMANDA = " WHERE demanda is TRUE";
	private static final String SQL_ORDEM = " ORDER BY i.descricao";
	
	@Override
	public List<MotivoCancelamento> obterTodosAgendamento() {
		return obterTodosAgendamento(SQL_BASE + SQL_CONDICAO_AGENDAMENTO + SQL_ORDEM);
	}
	
	@Override
	public List<MotivoCancelamento> obterTodosDemanda() {
		return obterTodosAgendamento(SQL_BASE + SQL_CONDICAO_DEMANDA + SQL_ORDEM);
	}
	
	
	public List<MotivoCancelamento> obterTodosAgendamento(String comando) {
		List<MotivoCancelamento> motivosCancelamento = new ArrayList<>();
		try {
			TypedQuery<MotivoCancelamento> query = em.createQuery(
					comando,
					MotivoCancelamento.class);
			motivosCancelamento = query.getResultList();
		} catch (Exception e) {
			logger.fatal("Erro durante pesquisa de Motivos de Cancelamento do Agendamento.\n Detalhes:"
					+ e);
		}
		return motivosCancelamento;
	}
}

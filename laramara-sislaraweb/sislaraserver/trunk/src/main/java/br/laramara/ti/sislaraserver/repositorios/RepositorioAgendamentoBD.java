package br.laramara.ti.sislaraserver.repositorios;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.laramara.ti.sislaraserver.dominio.agenda.Agendamento;
import br.laramara.ti.sislaraserver.dominio.agenda.EspecificacaoPesquisaAgendamento;
import br.laramara.ti.sislaraserver.dominio.espera.Espera;
import br.laramara.ti.sislaraserver.dominio.grupo.Profissional;

@Repository
public class RepositorioAgendamentoBD extends RepositorioDB<Agendamento>
		implements RepositorioAgendamento {

	private static final String COMANDO_BASE = "SELECT DISTINCT a FROM Agendamento a"
			+ CONDICIONAL_JUNCAO_USUARIO_PRE_CADASTRO_COM_HISTORICO;
	private static final String CONDICIONAL_PROFISSIONAL = " a.profissional = :profissional";
	private static final String CONDICIONAL_DATA_ATUAL_OU_FUTURA = " a.data >= CURRENT_DATE";
	private static final String CONDICIONAL_DATA_FUTURA = " a.data > CURRENT_DATE";
	private static final String CONDICIONAL_STATUS_AGENDAMENTO = " h.dataFinalVigencia IS NULL AND h.status = :statusAgendamento";
	private static final String COMANDO_ORDEM_DATA = " ORDER BY a.data";
	private static final String CONDICIONAL_INFORMACAO_ESSENCIAL = " (iu = :informacaoEssencial OR ip = :informacaoEssencial)";

	@Override
	public List<Agendamento> obterTodosPor(Profissional profissional,
			Calendar data) {
		List<Agendamento> agendamentos = new ArrayList<>();

		TypedQuery<Agendamento> query = em.createQuery(COMANDO_BASE
				+ CONDICIONAL_PROFISSIONAL + " AND " + CONDICIONAL_DATA, Agendamento.class);
		query.setParameter("profissional", profissional);
		query.setParameter("data", data);
		try {
			agendamentos = query.getResultList();
		} catch (Exception e) {
			logger.error("Não foi possível obter a lista de Agendamentos do "
					+ profissional + ". \nDetalhe:" + e);
		}
		return agendamentos;
	}

	@Transactional
	public List<Agendamento> salvar(List<Agendamento> agendamentos) {
		List<Agendamento> agendamentosSalvos = new ArrayList<>();
		for (Agendamento agendamento : agendamentos) {
			try {
				agendamentosSalvos.add(salvar(agendamento));
			} catch (Exception e) {
				logger.error("Ocorreu algum erro durante o armazenamento do "
						+ agendamento + ". \nDetalhes: " + e);
			}
		}
		return agendamentosSalvos;
	}

	@Transactional
	public Agendamento salvar(Agendamento agendamento) {
		String acao;
		try {
			if (agendamento.getId() == null) {
				em.persist(agendamento);
				acao = "Inclusão";
			} else {
				em.merge(agendamento);
				acao = "Alteração";
			}
			logger.info(acao + " do " + agendamento + " realizada com sucesso.");
		} catch (Exception e) {
			logger.error("Ocorreu algum erro durante o armazenamento do "
					+ agendamento + ". \nDetalhes: " + e);
		}
		return agendamento;
	}

	@Override
	public List<Agendamento> obterPor(
			EspecificacaoPesquisaAgendamento especificacaoPesquisaAgendamento) {
		List<Agendamento> agendamentos = new ArrayList<>();

		TypedQuery<Agendamento> query = comandoSql(especificacaoPesquisaAgendamento);

		try {
			agendamentos = query.getResultList();
		} catch (Exception e) {
			logger.error("Não foi possível obter a lista de Agendamentos do "
					+ especificacaoPesquisaAgendamento + ". \nDetalhe:" + e);
		}
		return agendamentos;
	}

	private TypedQuery<Agendamento> comandoSql(
			EspecificacaoPesquisaAgendamento especificacaoPesquisaAgendamento) {

		ComandoSQLEParametros comandoSQLEParametros = super
				.comandoSql(especificacaoPesquisaAgendamento);

		comandoSQLEParametros.setComandoBase(COMANDO_BASE);
		comandoSQLEParametros.setComandoOrdem(COMANDO_ORDEM_DATA);

		if (especificacaoPesquisaAgendamento.possuiProfissional()) {
			comandoSQLEParametros.adicionarComando(CONDICIONAL_PROFISSIONAL);
			comandoSQLEParametros.adicionarParametro("profissional",
					especificacaoPesquisaAgendamento.getProfissional());
		}
		if (especificacaoPesquisaAgendamento.possuiStatusAgendamento()) {
			comandoSQLEParametros
					.adicionarComando(CONDICIONAL_STATUS_AGENDAMENTO);
			comandoSQLEParametros.adicionarParametro("statusAgendamento",
					especificacaoPesquisaAgendamento.getStatusAgendamento());
		}
		if (especificacaoPesquisaAgendamento.somenteDataFutura()){
			comandoSQLEParametros.adicionarComando(CONDICIONAL_DATA_ATUAL_OU_FUTURA);
		}

		return super.obterQuery(comandoSQLEParametros);
	}

	@Override
	public List<Agendamento> obterAgendadosFuturosComInformacaoEssencial(Espera espera) {
		List<Agendamento> agendamentos = new ArrayList<>();

		TypedQuery<Agendamento> query = em.createQuery(COMANDO_BASE
				+ CONDICIONAL_INFORMACAO_ESSENCIAL + " AND " + CONDICIONAL_DATA_FUTURA, Agendamento.class);
		query.setParameter("informacaoEssencial",
				espera.possuiUsuario() ? espera.getUsuario().getInformacaoEssencial()
						: espera.getPreCadastro().getInformacaoEssencial());
		try {
			agendamentos = query.getResultList();
		} catch (Exception e) {
			logger.error(" Não foi possível obter a lista de Agendamentos do  \nDetalhe:" + e);
		}
		return agendamentos;
	}
}

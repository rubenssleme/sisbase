package br.laramara.ti.sislaraserver.repositorios;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.laramara.ti.sislaraserver.dominio.solicitacao.EspecificacaoPesquisaSolicitacaoRelatorio;
import br.laramara.ti.sislaraserver.dominio.solicitacao.SolicitacaoRelatorio;

@Repository
public class RepositorioSolicitacaoRelatorioBD extends
		RepositorioDB<SolicitacaoRelatorio> implements
		RepositorioSolicitacaoRelatorio {

	private static final String COMANDO_BASE = "SELECT DISTINCT a FROM SolicitacaoRelatorio a"
			+ CONDICIONAL_JUNCAO_USUARIO
			+ " LEFT JOIN a.historicosStatusSolicitacaoRelatorio p"
			+ CONDICIONAL;
	protected static final String CONDICIONAL_PROTOCOLO = " a.id = :protocolo";
	protected static final String CONDICIONAL_STATUS_SOLICITACAO_RELATORIO = " p.statusSolicitacaoRelatorio = :statusSolicitacaoRelatorio AND p.dataInicialVigencia IS NOT NULL AND p.dataFinalVigencia IS NULL ORDER BY a.id";

	@Override
	public List<SolicitacaoRelatorio> obterPor(
			EspecificacaoPesquisaSolicitacaoRelatorio especificacaoPesquisaSolicitacaoRelatorio) {

		List<SolicitacaoRelatorio> solicitacoesRelatorios = new ArrayList<>();

		TypedQuery<SolicitacaoRelatorio> query = comandoSql(especificacaoPesquisaSolicitacaoRelatorio);

		try {
			solicitacoesRelatorios = query.getResultList();
		} catch (Exception e) {
			logger.error("Não foi possível obter a lista de Solicitações de Relatórios do "
					+ especificacaoPesquisaSolicitacaoRelatorio
					+ ". \nDetalhe:" + e);
		}
		return solicitacoesRelatorios;
	}

	@Transactional
	public SolicitacaoRelatorio salvar(SolicitacaoRelatorio solicitacaoRelatorio) {
		String acao;
		try {
			if (solicitacaoRelatorio.getId() == null) {
				em.persist(solicitacaoRelatorio);
				acao = "Inclusão";
			} else {
				em.merge(solicitacaoRelatorio);
				acao = "Alteração";
			}
			logger.info(acao + " do " + solicitacaoRelatorio
					+ " realizada com sucesso.");
		} catch (Exception e) {
			logger.error("Ocorreu algum erro durante o armazenamento do "
					+ solicitacaoRelatorio + ". \nDetalhes: " + e);
		}
		return solicitacaoRelatorio;
	}

	private TypedQuery<SolicitacaoRelatorio> comandoSql(
			EspecificacaoPesquisaSolicitacaoRelatorio especificacaoPesquisaSolicitacaoRelatorio) {

		ComandoSQLEParametros comandoSQLEParametros = super
				.comandoSql(especificacaoPesquisaSolicitacaoRelatorio);

		comandoSQLEParametros.setComandoBase(COMANDO_BASE);

		if (especificacaoPesquisaSolicitacaoRelatorio.possuiProtocolo()) {
			comandoSQLEParametros.adicionarComando(CONDICIONAL_PROTOCOLO);
			comandoSQLEParametros.adicionarParametro("protocolo",
					especificacaoPesquisaSolicitacaoRelatorio.getProtocolo());
		}
		if (especificacaoPesquisaSolicitacaoRelatorio.possuiStatus()) {
			comandoSQLEParametros
					.adicionarComando(CONDICIONAL_STATUS_SOLICITACAO_RELATORIO);
			comandoSQLEParametros.adicionarParametro(
					"statusSolicitacaoRelatorio",
					especificacaoPesquisaSolicitacaoRelatorio.getStatus());
		}
		return super.obterQuery(comandoSQLEParametros);
	}
}

package br.laramara.ti.sislaraserver.repositorios;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.laramara.ti.sislaraserver.dominio.doacao.Demanda;
import br.laramara.ti.sislaraserver.dominio.doacao.EspecificacaoPesquisaDemanda;

@Repository
public class RepositorioDemandaBD extends RepositorioDB<Demanda> implements
		RepositorioDemanda {

	private static final String CONDICIONAL_JUNCAO_RECIBO = " LEFT JOIN a.captacoes c LEFT JOIN c.recibo r";

	private static final String COMANDO_BASE = "SELECT DISTINCT a FROM Demanda a"
			+ CONDICIONAL_JUNCAO_RECIBO + CONDICIONAL_JUNCAO_USUARIO_PRE_CADASTRO_COM_HISTORICO;
	
	private static final String CONDICIONAL_RECURSO = " a.recurso = :recurso";
	private static final String CONDICIONAL_PRE_CADASTRO = " a.preCadastro = :preCadastro";
	private static final String CONDICIONAL_CPF = " (iu.cpf = :cpf OR ip.cpf = :cpf) ";
	private static final String CONDICIONAL_STATUS_DEMANDA = " h.dataFinalVigencia IS NULL AND h.status = :statusDemanda";
	private static final String CONDICIONAL_NUMERO_RECIBO = " r.id = :numeroRecibo";

	@Inject
	private RepositorioArquivo repositorioArquivo;
	
	@Override
	public List<Demanda> obterPor(
			EspecificacaoPesquisaDemanda especificacaoPesquisaDemanda) {
		List<Demanda> demanda = new ArrayList<>();

		TypedQuery<Demanda> query = comandoSql(especificacaoPesquisaDemanda);

		try {
			demanda = query.getResultList();
		} catch (Exception e) {
			logger.error("Não foi possível obter a lista de Demandas do "
					+ especificacaoPesquisaDemanda + ". \nDetalhe:" + e);
		}
		return demanda;
	}

	private TypedQuery<Demanda> comandoSql(
			EspecificacaoPesquisaDemanda especificacaoPesquisaDemanda) {

		ComandoSQLEParametros comandoSQLEParametros = super
				.comandoSql(especificacaoPesquisaDemanda);

		comandoSQLEParametros.setComandoBase(COMANDO_BASE);
		
		if (especificacaoPesquisaDemanda.possuiRecurso()) {
			comandoSQLEParametros.adicionarComando(CONDICIONAL_RECURSO);
			comandoSQLEParametros.adicionarParametro("recurso",
					especificacaoPesquisaDemanda.getRecurso());
		}
		if (especificacaoPesquisaDemanda.possuiPreCadastro()) {
			comandoSQLEParametros.adicionarComando(CONDICIONAL_PRE_CADASTRO);
			comandoSQLEParametros.adicionarParametro("preCadastro",
					especificacaoPesquisaDemanda.getPreCadastro());
		}
		if (especificacaoPesquisaDemanda.possuiCpf()){
			comandoSQLEParametros.adicionarComando(CONDICIONAL_CPF);
			comandoSQLEParametros.adicionarParametro("cpf",
					especificacaoPesquisaDemanda.getCpf());
		}
		if (especificacaoPesquisaDemanda.possuiStatusDemanda()){
			comandoSQLEParametros.adicionarComando(CONDICIONAL_STATUS_DEMANDA);
			comandoSQLEParametros.adicionarParametro("statusDemanda",
					especificacaoPesquisaDemanda.getStatusDemanda());
		}
		if (especificacaoPesquisaDemanda.possuiNumeroRecibo()){
			comandoSQLEParametros.adicionarComando(CONDICIONAL_NUMERO_RECIBO);
			comandoSQLEParametros.adicionarParametro("numeroRecibo",
					Long.valueOf(especificacaoPesquisaDemanda.getNumeroRecibo()));
		}
		return super.obterQuery(comandoSQLEParametros);
	}

	@Transactional
	public List<Demanda> salvar(List<Demanda> demandas) {
		List<Demanda> demandasSalvas = new ArrayList<>();
		for (Demanda demanda : demandas) {
			try {
				demandasSalvas.add(salvar(demanda));
			} catch (Exception e) {
				logger.error("Ocorreu algum erro durante o armazenamento do "
						+ demanda + ". \nDetalhes: " + e);
			}
		}
		return demandasSalvas;
	}

	@Transactional
	public Demanda salvar(Demanda demanda) {
		String acao;
		try {
			if (demanda.getId() == null) {
				em.persist(demanda);
				acao = "Inclusão";
			} else {
				em.merge(demanda);
				acao = "Alteração";
			}
			repositorioArquivo.salvar(demanda);
			logger.info(acao + " do " + demanda + " realizada com sucesso.");
		} catch (Exception e) {
			logger.error("Ocorreu algum erro durante o armazenamento do "
					+ demanda + ". \nDetalhes: " + e);
		}
		return demanda;
	}
}

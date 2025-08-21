package br.laramara.ti.sislaraserver.repositorios;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.laramara.ti.sislaracommons.utilitarios.DataHoraUtils;
import br.laramara.ti.sislaraserver.dominio.atendimento.AtendimentoIndividual;
import br.laramara.ti.sislaraserver.dominio.atendimento.EspecificacaoPesquisaAtendimentoIndividual;
import br.laramara.ti.sislaraserver.dominio.grupo.DescricaoTipoAtendimento;
import br.laramara.ti.sislaraserver.dominio.grupo.Modulo;

@Repository
public class RepositorioAtendimentoIndividualBD extends
		RepositorioDB<AtendimentoIndividual> implements
		RepositorioAtendimentoIndividual {

	private static final String COMANDO_BASE = "SELECT DISTINCT a FROM AtendimentoIndividual a ";
	private static final String CONDICIONAL_JUNCAO_USUARIO_PRE_CADASTRO = "JOIN a.atendimentosProfissionais p LEFT JOIN a.usuario u LEFT JOIN a.preCadastro e ";
	private static final String CONDICIONAL_PROFISSIONAL = " p.profissional IN :profissional";
	private static final String CONDICIONAL_PRE_CADASTRO = " a.preCadastro = :preCadastro";
	private static final String CONDICIONAL_POSSUI_INTEGRACAO = " a.integracao IS NOT NULL";
	private static final String ORDEM_DATA = " ORDER BY a.data";
	
	@Inject
	private RepositorioArquivo repositorioArquivo;

	@Transactional
	public AtendimentoIndividual salvar(AtendimentoIndividual atendimento){
		String acao;
		try {
			if (atendimento.getId() == null) {
				em.persist(atendimento);
				acao = "Inclusão";
			} else {
				em.merge(atendimento);
				acao = "Alteração";
			}
			repositorioArquivo.salvar(atendimento);
			logger.info(acao + " do " + atendimento + " realizada com sucesso.");
 		} catch (Exception e) {
			logger.error("Ocorreu algum erro durante o armazenamento do "
					+ atendimento + ". \nDetalhes: " + e);
		}
		return atendimento;
	}
	
	@Override
	public List<AtendimentoIndividual> obterPor(
			EspecificacaoPesquisaAtendimentoIndividual especificacaoPesquisaAtendimentoIndividual) {
		return obterPor(especificacaoPesquisaAtendimentoIndividual,
				Integer.MAX_VALUE);
	}

	@Override
	public List<AtendimentoIndividual> obterPor(
			EspecificacaoPesquisaAtendimentoIndividual especificacaoPesquisaAtendimentoIndividual,
			int quantidade){
		return obterPor(especificacaoPesquisaAtendimentoIndividual, quantidade, true);
	}
			
	public List<AtendimentoIndividual> obterPor(
			EspecificacaoPesquisaAtendimentoIndividual especificacaoPesquisaAtendimentoIndividual,
			int quantidade, boolean preencherResumoIntegracao) {
		List<AtendimentoIndividual> atendimentosIndividuais = new ArrayList<>();
		TypedQuery<AtendimentoIndividual> query = comandoSql(especificacaoPesquisaAtendimentoIndividual);
		try {
			query.setMaxResults(quantidade);
			atendimentosIndividuais = query.getResultList();
			if (preencherResumoIntegracao){
				preencherResumoIntegracao(atendimentosIndividuais);
			}
		} catch (Exception e) {
			logger.error("Não foi possível obter a lista de Atendimento Individuais do "
					+ especificacaoPesquisaAtendimentoIndividual
					+ ". \nDetalhe:" + e);
		}
		return atendimentosIndividuais;
	}

	private void preencherResumoIntegracao(List<AtendimentoIndividual> atendimentosIndividuais) {
		for (AtendimentoIndividual atendimentoIndividual : atendimentosIndividuais) {
			if (atendimentoIndividual.possuiUsuario()) {
				atendimentoIndividual.setResumoIntegracao(
						obterResumoIntegracao(atendimentoIndividual.getUsuario().getId().toString()));
			}
		}
	}
	
	public String obterResumoIntegracao(String prontuario){
		EspecificacaoPesquisaAtendimentoIndividual especificacaoPesquisaAtendimentoIndividual = new EspecificacaoPesquisaAtendimentoIndividual();
		especificacaoPesquisaAtendimentoIndividual.setPossuiIntegracao(true);
		especificacaoPesquisaAtendimentoIndividual
				.setProntuario(prontuario);
		List<AtendimentoIndividual> atendimentosIndividuaisComIntegracao = obterPor(
				especificacaoPesquisaAtendimentoIndividual, Integer.MAX_VALUE, false);
		
		return atendimentosIndividuaisComIntegracao.stream()
				.map(atendimentoIndividualComIntegracao -> atendimentoIndividualComIntegracao.getDescricaoTipoAtendimento()
						.getNome() + " - " + atendimentoIndividualComIntegracao.getData() + " - "
						+ atendimentoIndividualComIntegracao.getOpcaoIntegracao().toString() + " - "
						+ atendimentoIndividualComIntegracao.getMotivoNaoIntegracao())
				.collect(Collectors.joining("\n"));
	}

	private TypedQuery<AtendimentoIndividual> comandoSql(
			EspecificacaoPesquisaAtendimentoIndividual especificacaoPesquisaAtendimentoIndividual) {

		ComandoSQLEParametros comandoSQLEParametros = super
				.comandoSql(especificacaoPesquisaAtendimentoIndividual);

		comandoSQLEParametros.setComandoBase(COMANDO_BASE
				+ CONDICIONAL_JUNCAO_USUARIO_PRE_CADASTRO + CONDICIONAL);
		if (especificacaoPesquisaAtendimentoIndividual.possuiProfissional()) {
			comandoSQLEParametros.adicionarComando(CONDICIONAL_PROFISSIONAL);
			comandoSQLEParametros.adicionarParametro("profissional",
					especificacaoPesquisaAtendimentoIndividual
							.getProfissional());
		}

		if (especificacaoPesquisaAtendimentoIndividual.possuiPreCadastro()) {
			comandoSQLEParametros.adicionarComando(CONDICIONAL_PRE_CADASTRO);
			comandoSQLEParametros.adicionarParametro("preCadastro",
					especificacaoPesquisaAtendimentoIndividual.getPreCadastro());
		}
		
		if (especificacaoPesquisaAtendimentoIndividual.possuiIntegracao()){
			comandoSQLEParametros.adicionarComando(CONDICIONAL_POSSUI_INTEGRACAO);
		}
		comandoSQLEParametros.adicionarOrdem(ORDEM_DATA);
		return super.obterQuery(comandoSQLEParametros);
	}

	@Override
	public boolean existeAtendimentoIndividualComUsuarioPrimeiraVez(AtendimentoIndividual atendimentoIndividualReferencia) {
		List<AtendimentoIndividual> atendimentosIndividuais = new ArrayList<>();
		if (atendimentoIndividualReferencia.possuiUsuario()) {
			try {
				EspecificacaoPesquisaAtendimentoIndividual especificacaoPesquisaAtendimentoIndividual = new EspecificacaoPesquisaAtendimentoIndividual();
				for (DescricaoTipoAtendimento descricaoTipoAtendimentoEquivalente : DescricaoTipoAtendimento
						.obterDescricoesEquivalenteParaRegraPrimeiraVez(
								atendimentoIndividualReferencia.getDescricaoTipoAtendimento())) {
					especificacaoPesquisaAtendimentoIndividual
							.setProntuario(atendimentoIndividualReferencia.getUsuario().getId().toString());
					especificacaoPesquisaAtendimentoIndividual
							.setDescricaoTipoAtendimento(descricaoTipoAtendimentoEquivalente);
					especificacaoPesquisaAtendimentoIndividual.setModulo(atendimentoIndividualReferencia.getModulo());
					atendimentosIndividuais.addAll(obterPor(especificacaoPesquisaAtendimentoIndividual));
				}
				atendimentosIndividuais.remove(atendimentoIndividualReferencia);
			
			} catch (Exception e) {
				logger.error("Não foi possível obter os atendimentos individuais. \nDetalhe:" + e);
			}
		} else{
			return false;
		}
		return atendimentosIndividuais.stream().anyMatch(atendimentoIndividual -> atendimentoIndividual.estaNormal()
				&& atendimentoIndividual.ePrimeiraVez() && atendimentoIndividual.estaComFrequenciaAT());
	}

	@Override
	public boolean existeAtendimentoAvaliacaoFuncionalComFrequenciaATUltimos6Meses(AtendimentoIndividual atendimentoIndividual) {
		EspecificacaoPesquisaAtendimentoIndividual especificacaoPesquisaAtendimentoIndividual = new EspecificacaoPesquisaAtendimentoIndividual();
		especificacaoPesquisaAtendimentoIndividual.setProntuario(atendimentoIndividual.getUsuario().getId().toString());
		especificacaoPesquisaAtendimentoIndividual
				.setDescricaoTipoAtendimento(DescricaoTipoAtendimento.fabricarDescricaoAvaliacaoFuncional());
		especificacaoPesquisaAtendimentoIndividual.setModulo(Modulo.fabricaModuloAtendimentoEspecificoEspecializado());
		especificacaoPesquisaAtendimentoIndividual.setDataInicio(DataHoraUtils
				.formatarData(DataHoraUtils.recuar6Meses(atendimentoIndividual.obterData())));
		especificacaoPesquisaAtendimentoIndividual.setDataTermino(DataHoraUtils
				.formatarData(atendimentoIndividual.obterData()));
		
		List<AtendimentoIndividual> atendimentosIndividuaisDeAvaliacaoFuncional = obterPor(
				especificacaoPesquisaAtendimentoIndividual, Integer.MAX_VALUE, false);
		atendimentosIndividuaisDeAvaliacaoFuncional.remove(atendimentoIndividual);
		return atendimentosIndividuaisDeAvaliacaoFuncional.stream()
				.anyMatch(atendimentoInvividual -> atendimentoIndividual.estaComFrequenciaAT());
	}
}

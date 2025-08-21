package br.laramara.ti.sislaraserver.fachadas.operacoes;

import java.util.ArrayList;
import java.util.List;

import br.laramara.ti.sislaracommons.dtos.ResultadoDTO;
import br.laramara.ti.sislaraserver.dominio.Resultado;
import br.laramara.ti.sislaraserver.dominio.atendimento.AtendimentoIndividual;
import br.laramara.ti.sislaraserver.dominio.atendimento.AtendimentoProfissional;
import br.laramara.ti.sislaraserver.dominio.atendimento.EspecificacaoPesquisaAtendimentoIndividual;
import br.laramara.ti.sislaraserver.dominio.endereco.Validavel;
import br.laramara.ti.sislaraserver.dominio.espera.AutomatizadorEspera;
import br.laramara.ti.sislaraserver.dominio.seguranca.ContaAcesso;
import br.laramara.ti.sislaraserver.fabricas.FabricaAtendimentoIndividual;
import br.laramara.ti.sislaraserver.repositorios.RepositorioAtendimentoIndividual;
import br.laramara.ti.sislaraserver.repositorios.RepositorioBloqueio;
import br.laramara.ti.sislaraserver.repositorios.RepositorioEspera;
import br.laramara.ti.sislaraserver.repositorios.RepositorioPreCadastro;
import br.laramara.ti.sislaraserver.repositorios.RepositorioTipoAtendimento;

public abstract class OperacaoAtendimentoIndividual {
	
	protected RepositorioAtendimentoIndividual repositorioAtendimentoIndividual;
	protected RepositorioBloqueio repositorioBloqueio;
	protected AutomatizadorEspera automatizadorEspera;
	protected FabricaAtendimentoIndividual fabricaAtendimentoIndividual;
	protected RepositorioEspera repositorioEspera;
	protected RepositorioTipoAtendimento repositorioTipoAtendimento;
	protected RepositorioPreCadastro repositorioPreCadastro;

	public OperacaoAtendimentoIndividual(
			RepositorioAtendimentoIndividual repositorioAtendimentoIndividual, RepositorioBloqueio repositorioBloqueio,	RepositorioEspera repositorioEspera,
			RepositorioTipoAtendimento repositorioTipoAtendimento, RepositorioPreCadastro repositorioPreCadastro, AutomatizadorEspera automatizadorEspera, FabricaAtendimentoIndividual fabricaAtendimentoIndividual) {
		this.repositorioAtendimentoIndividual = repositorioAtendimentoIndividual;
		this.repositorioBloqueio = repositorioBloqueio;
		this.repositorioEspera = repositorioEspera;
		this.repositorioTipoAtendimento = repositorioTipoAtendimento;
		this.repositorioPreCadastro = repositorioPreCadastro;
		this.automatizadorEspera = automatizadorEspera;
		this.fabricaAtendimentoIndividual = fabricaAtendimentoIndividual;
	}

	protected List<AtendimentoIndividual> obtemAtendimentosIndividuaisEmConflito(
			AtendimentoIndividual atendimentoIndividual) {
		List<AtendimentoIndividual> atendimentosIndividuaisEmConflito = new ArrayList<AtendimentoIndividual>();
		for (AtendimentoProfissional atendimentoProfissional : atendimentoIndividual
				.getAtendimentosProfissionais()) {
			EspecificacaoPesquisaAtendimentoIndividual especificacaoPesquisaAtendimentoIndividual = new EspecificacaoPesquisaAtendimentoIndividual();
			especificacaoPesquisaAtendimentoIndividual
					.setProfissional(atendimentoProfissional.getProfissional());
			especificacaoPesquisaAtendimentoIndividual
					.setDataInicio(atendimentoIndividual.getData());
			List<AtendimentoIndividual> atendimentosIndividuaisExistentes = repositorioAtendimentoIndividual
					.obterPor(especificacaoPesquisaAtendimentoIndividual);
			for (AtendimentoIndividual atendimentoIndividualExistente : atendimentosIndividuaisExistentes) {
				if (Validavel.conflitoHora(atendimentoIndividual.getHorario(),
						atendimentoIndividualExistente.getHorario())
						&& (atendimentoIndividual.possuiId()
								&& !atendimentoIndividual
										.equals(atendimentoIndividualExistente)
								&& !atendimentoIndividualExistente
										.estaCancelado() || !atendimentoIndividual
								.possuiId()
								&& !atendimentoIndividualExistente
										.estaCancelado())) {
					if (!repositorioBloqueio
							.existeLiberacaoAtendimentoIndivisualSimultaneo(atendimentoIndividual)) {
						atendimentosIndividuaisEmConflito
								.add(atendimentoIndividualExistente);
					}
				}
			}
		}
		return atendimentosIndividuaisEmConflito;
	}

	protected boolean existeAtendimentoIndividualEmConflito(
			List<AtendimentoIndividual> atendimentosIndividuais) {
		return atendimentosIndividuais.size() != 0;
	}

	protected String obterDescricaoDeAtendimentosEmConflito(
			List<AtendimentoIndividual> atendimentoIndividuais) {
		String retorno = "Já existe atendimento individual cadastrado com a data e hora especificado. Detalhes: ";
		for (AtendimentoIndividual atendimentoIndividual : atendimentoIndividuais) {
			for (AtendimentoProfissional atendimentoProfissional : atendimentoIndividual
					.getAtendimentosProfissionais()) {
				retorno += atendimentoProfissional.getProfissional().getNome()
						+ ", ";
			}
			retorno += atendimentoIndividual.getData() + ", "
					+ atendimentoIndividual.getHorario().getHoraInicio()
					+ " às "
					+ atendimentoIndividual.getHorario().getHoraTermino()
					+ ".\n ";
		}
		return retorno.substring(0, retorno.length() - 2);
	}
	
	public void processar(AtendimentoIndividual atendimentoIndividual,
			ContaAcesso contaAcesso, ResultadoDTO resultadoDto)
			throws Exception {
		AtendimentoIndividual atendimentoIndividualSalvo = repositorioAtendimentoIndividual
				.salvar(atendimentoIndividual);
		Resultado resultadoExcessoFaltas = automatizadorEspera
				.processarAtendimentoIndividualInclusaoNaListaDeEsperaPorExecessoDeFaltas(
						atendimentoIndividualSalvo, contaAcesso);
		resultadoDto.efetuadoComSucesso(fabricaAtendimentoIndividual
				.converterParaDTO(atendimentoIndividualSalvo),
				resultadoExcessoFaltas.getMensagem());
	}
}

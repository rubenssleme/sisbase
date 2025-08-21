package br.laramara.ti.sislaraserver.fachadas.operacoes;

import java.util.List;

import org.apache.log4j.Logger;

import br.laramara.ti.sislaracommons.dtos.ResultadoDTO;
import br.laramara.ti.sislaracommons.dtos.atendimento.AtendimentoIndividualDTO;
import br.laramara.ti.sislaracommons.dtos.atendimento.EspecificacaoCopiaAtendimentoIndividualDTO;
import br.laramara.ti.sislaraserver.dominio.atendimento.AtendimentoIndividual;
import br.laramara.ti.sislaraserver.dominio.atendimento.EspecificacaoCopiaAtendimentoIndividual;
import br.laramara.ti.sislaraserver.dominio.espera.AutomatizadorEspera;
import br.laramara.ti.sislaraserver.dominio.seguranca.ContaAcesso;
import br.laramara.ti.sislaraserver.fabricas.FabricaAtendimentoIndividual;
import br.laramara.ti.sislaraserver.fabricas.FabricaEspecificacaoCopiaAtendimentoIndividual;
import br.laramara.ti.sislaraserver.repositorios.RepositorioAtendimentoIndividual;
import br.laramara.ti.sislaraserver.repositorios.RepositorioBloqueio;

public class OperacaoAtendimentoIndividualCopia extends
		OperacaoAtendimentoIndividual implements OperacaoFachada {

	private static final Logger logger = Logger
			.getLogger(OperacaoAtendimentoIndividualCopia.class);

	private EspecificacaoCopiaAtendimentoIndividualDTO especificacaoCopiaAtendimentoIndividualDto;
	private FabricaAtendimentoIndividual fabricaAtendimentoIndividual;
	private AtendimentoIndividualDTO atendimentoIndividualDto;

	public OperacaoAtendimentoIndividualCopia(
			EspecificacaoCopiaAtendimentoIndividualDTO especificacaoCopiaAtendimentoIndividualDto,
			FabricaAtendimentoIndividual fabricaAtendimentoIndividual,
			RepositorioAtendimentoIndividual repositorioAtendimentoIndividual,
			RepositorioBloqueio repositorioBloqueio,
			AutomatizadorEspera automatizadorEspera,
			AtendimentoIndividualDTO atendimentoIndividualDto) {
		super(repositorioAtendimentoIndividual, repositorioBloqueio, null, null, null, automatizadorEspera, fabricaAtendimentoIndividual);
		this.especificacaoCopiaAtendimentoIndividualDto = especificacaoCopiaAtendimentoIndividualDto;
		this.fabricaAtendimentoIndividual = fabricaAtendimentoIndividual;
		this.atendimentoIndividualDto = atendimentoIndividualDto;
		this.automatizadorEspera = automatizadorEspera;
	}

	@Override
	public ResultadoDTO processar(ContaAcesso contaAcesso,
			ResultadoDTO resultadoDto) {
		EspecificacaoCopiaAtendimentoIndividual especificacaoCopiaAtendimentoIndividual = new FabricaEspecificacaoCopiaAtendimentoIndividual()
				.converterParaDominio(especificacaoCopiaAtendimentoIndividualDto);
		AtendimentoIndividual atendimentoIndividual = fabricaAtendimentoIndividual
				.converterParaDominio(atendimentoIndividualDto);
		atendimentoIndividual
				.prepararParaCopia(especificacaoCopiaAtendimentoIndividual);
		atendimentoIndividual.validarObrigatoriedadeETamanhoMaximoDeDados();

		if (atendimentoIndividual.validado()) {
			List<AtendimentoIndividual> atendimentosIndividuaisEmConflito = obtemAtendimentosIndividuaisEmConflito(atendimentoIndividual);
			if (!existeAtendimentoIndividualEmConflito(atendimentosIndividuaisEmConflito)) {
				logger.info(contaAcesso + " efetuou Solicitação de Cópia do "
						+ atendimentoIndividual + " com "
						+ especificacaoCopiaAtendimentoIndividual);
				try {
					processar(atendimentoIndividual, contaAcesso, resultadoDto);
				} catch (Exception e) {
					logger.error("Ocorreu algum erro durante o armazenamento do "
							+ atendimentoIndividual + ". \nDetalhes: " + e);
				}
			} else {
				resultadoDto
						.adicionarErro(obterDescricaoDeAtendimentosEmConflito(atendimentosIndividuaisEmConflito));
			}
		} else {
			resultadoDto.adicionarErro(atendimentoIndividual
					.obterDescricaoErros());
		}
		return resultadoDto;
	}
}

package br.laramara.ti.sislaraserver.fachadas.operacoes;

import org.apache.log4j.Logger;

import br.laramara.ti.sislaracommons.dtos.ResultadoDTO;
import br.laramara.ti.sislaracommons.dtos.agenda.AgendamentoDTO;
import br.laramara.ti.sislaraserver.dominio.agenda.Agendamento;
import br.laramara.ti.sislaraserver.dominio.seguranca.ContaAcesso;
import br.laramara.ti.sislaraserver.fabricas.FabricaAgendamento;
import br.laramara.ti.sislaraserver.repositorios.RepositorioAgendamento;

public class OperacaoAgendamentoCancelamento implements OperacaoFachada {

	private static final Logger logger = Logger
			.getLogger(OperacaoAgendamentoCancelamento.class);

	private FabricaAgendamento fabricaAgendamento;
	private RepositorioAgendamento repositorioAgendamento;
	private AgendamentoDTO agendamentoDto;

	public OperacaoAgendamentoCancelamento(FabricaAgendamento fabricaAgendamento,
			RepositorioAgendamento repositorioAgendamento,
			AgendamentoDTO agendamentoDto) {
		this.fabricaAgendamento = fabricaAgendamento;
		this.repositorioAgendamento = repositorioAgendamento;
		this.agendamentoDto = agendamentoDto;
	}

	@Override
	public ResultadoDTO processar(ContaAcesso contaAcesso,
			ResultadoDTO resultado) {
		Agendamento agendamento = fabricaAgendamento.converterParaDominio(
				agendamentoDto,
				repositorioAgendamento.obterPorId(agendamentoDto.getId()));
		agendamento.cancelar(contaAcesso);
		agendamento.validarObrigatoriedadeETamanhoMaximoDeDados();

		if (agendamento.validado()) {
			logger.info(contaAcesso + " efetuou Solicitação de Edição do "
					+ agendamento);
			Agendamento agendamentoSalvo = repositorioAgendamento
					.salvar(agendamento);
			resultado.efetuadoComSucesso(fabricaAgendamento
					.converterParaDTO(agendamentoSalvo));
		} else {
			resultado.adicionarErro(agendamento.obterDescricaoErros());
		}
		return resultado;
	}

}

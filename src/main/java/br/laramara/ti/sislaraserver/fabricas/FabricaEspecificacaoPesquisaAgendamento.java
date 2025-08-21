package br.laramara.ti.sislaraserver.fabricas;

import br.laramara.ti.sislaracommons.dtos.agenda.EspecificacaoPesquisaAgendamentoDTO;
import br.laramara.ti.sislaraserver.dominio.agenda.EspecificacaoPesquisaAgendamento;

public class FabricaEspecificacaoPesquisaAgendamento
		extends
		FabricaRecursiva<EspecificacaoPesquisaAgendamentoDTO, EspecificacaoPesquisaAgendamento> {

	@Override
	public EspecificacaoPesquisaAgendamentoDTO converterParaDTO(
			EspecificacaoPesquisaAgendamento especificacaoPesquisaAgendamento) {
		return null;
	}

	@Override
	public EspecificacaoPesquisaAgendamento converterParaDominio(
			EspecificacaoPesquisaAgendamentoDTO especificacaoPesquisaAgendamentoDto,
			EspecificacaoPesquisaAgendamento especificacaoPesquisaAgendamento) {
		if (especificacaoPesquisaAgendamentoDto != null) {
			if (especificacaoPesquisaAgendamento == null) {
				especificacaoPesquisaAgendamento = new EspecificacaoPesquisaAgendamento();
			}
			especificacaoPesquisaAgendamento
					.setProfissional(new FabricaProfissional()
							.converterParaDominio(especificacaoPesquisaAgendamentoDto
									.getProfissionalDto()));
			especificacaoPesquisaAgendamento
					.setDescricaoTipoAtendimento(new FabricaDescricaoTipoAtendimento()
							.converterParaDominio(especificacaoPesquisaAgendamentoDto
									.getDescricaoTipoAtendimentoDTO()));
			especificacaoPesquisaAgendamento.setModulo(new FabricaModulo()
					.converterParaDominio(especificacaoPesquisaAgendamentoDto
							.getModuloDTO()));
			especificacaoPesquisaAgendamento
					.setDataInicio(especificacaoPesquisaAgendamentoDto
							.getDataInicio());
			especificacaoPesquisaAgendamento
					.setDataTermino(especificacaoPesquisaAgendamentoDto
							.getDataTermino());
			especificacaoPesquisaAgendamento
					.setStatusAgendamento(new FabricaStatusAgendamento()
							.converterParaDominio(especificacaoPesquisaAgendamentoDto
									.getStatusAgendamentoDTO()));
			especificacaoPesquisaAgendamento
					.setProntuario(especificacaoPesquisaAgendamentoDto
							.getProntuario());
			especificacaoPesquisaAgendamento
					.setRg(especificacaoPesquisaAgendamentoDto
							.getRgPreCadastro());
			especificacaoPesquisaAgendamento
					.setDataFutura(especificacaoPesquisaAgendamentoDto
							.estaDataFutura());
		}
		return especificacaoPesquisaAgendamento;
	}

	@Override
	public EspecificacaoPesquisaAgendamento obterNovo() {
		return new EspecificacaoPesquisaAgendamento();
	}
}

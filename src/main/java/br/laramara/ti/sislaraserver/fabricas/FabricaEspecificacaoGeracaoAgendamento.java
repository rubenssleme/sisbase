package br.laramara.ti.sislaraserver.fabricas;

import br.laramara.ti.sislaracommons.dtos.agenda.EspecificacaoGeracaoAgendamentoDTO;
import br.laramara.ti.sislaraserver.dominio.agenda.EspecificacaoGeracaoAgendamento;

public class FabricaEspecificacaoGeracaoAgendamento
		extends
		FabricaRecursiva<EspecificacaoGeracaoAgendamentoDTO, EspecificacaoGeracaoAgendamento> {

	@Override
	public EspecificacaoGeracaoAgendamentoDTO converterParaDTO(
			EspecificacaoGeracaoAgendamento especificacaoGeracaoAgendamento) {
		return null;
	}

	@Override
	public EspecificacaoGeracaoAgendamento converterParaDominio(
			EspecificacaoGeracaoAgendamentoDTO especificacaoGeracaoAgendamentoDto,
			EspecificacaoGeracaoAgendamento especificacaoGeracaoAgendamento) {
		if (especificacaoGeracaoAgendamentoDto != null) {
			if (especificacaoGeracaoAgendamento == null) {
				especificacaoGeracaoAgendamento = new EspecificacaoGeracaoAgendamento();
			}
			especificacaoGeracaoAgendamento.setUsuario(new FabricaUsuario()
					.converterParaDominio(
							especificacaoGeracaoAgendamentoDto.getUsuarioDto(),
							especificacaoGeracaoAgendamento.getUsuario()));
			especificacaoGeracaoAgendamento
					.setPreCadastro(new FabricaPreCadastro()
							.converterParaDominio(
									especificacaoGeracaoAgendamentoDto
											.getPreCadastroDTO(),
									especificacaoGeracaoAgendamento
											.getPreCadastro()));
			especificacaoGeracaoAgendamento.setGrupos(new FabricaGrupo()
					.converterParaDominio(
							especificacaoGeracaoAgendamentoDto.getGruposDTO(),
							especificacaoGeracaoAgendamento.getGrupos()));
			especificacaoGeracaoAgendamento
					.setProfissionais(new FabricaProfissional()
							.converterParaDominio(especificacaoGeracaoAgendamentoDto
									.getProfissionaisDto()));
			especificacaoGeracaoAgendamento
					.setDescricaoTipoAtendimento(new FabricaDescricaoTipoAtendimento()
							.converterParaDominio(especificacaoGeracaoAgendamentoDto
									.getDescricaoTipoAtendimentoDto()));
			especificacaoGeracaoAgendamento.setModulo(new FabricaModulo()
					.converterParaDominio(especificacaoGeracaoAgendamentoDto
							.getModuloDto()));
			especificacaoGeracaoAgendamento.setSetor(new FabricaSetor()
					.converterParaDominio(especificacaoGeracaoAgendamentoDto
							.getSetorDto()));
			especificacaoGeracaoAgendamento
					.setLocalAtendimento(new FabricaLocalAtendimento()
							.converterParaDominio(especificacaoGeracaoAgendamentoDto
									.getLocalAtendimentoDto()));
			especificacaoGeracaoAgendamento
					.setDataInicio(especificacaoGeracaoAgendamentoDto
							.getDataInicio());
			especificacaoGeracaoAgendamento
					.setDataTermino(especificacaoGeracaoAgendamentoDto
							.getDataTermino());
			especificacaoGeracaoAgendamento.setDiaSemana(new FabricaDiaSemana()
					.converterParaDominio(especificacaoGeracaoAgendamentoDto
							.getDiaSemanaDto()));
			especificacaoGeracaoAgendamento.setHorario(new FabricaHorario()
					.converterParaDominio(especificacaoGeracaoAgendamentoDto
							.getHorarioDto()));
			especificacaoGeracaoAgendamento
					.setReservaStatus(new FabricaStatus()
							.converterParaDominio(especificacaoGeracaoAgendamentoDto
									.getReservaStatusDto()));
			especificacaoGeracaoAgendamento
					.setObs(especificacaoGeracaoAgendamentoDto.getObs());
		}
		return especificacaoGeracaoAgendamento;
	}

	@Override
	public EspecificacaoGeracaoAgendamento obterNovo() {
		return new EspecificacaoGeracaoAgendamento();
	}
}

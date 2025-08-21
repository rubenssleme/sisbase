package br.laramara.ti.sislaraserver.fabricas;

import br.laramara.ti.sislaracommons.dtos.agenda.AgendamentoDTO;
import br.laramara.ti.sislaraserver.dominio.agenda.Agendamento;

public class FabricaAgendamento extends FabricaRecursiva<AgendamentoDTO, Agendamento> {

	@Override
	public AgendamentoDTO converterParaDTO(Agendamento agendamento) {
		AgendamentoDTO agendamentoDto = new AgendamentoDTO();
		agendamentoDto.setId(agendamento.getId());
		agendamentoDto.setUsuarioDto(new FabricaUsuario()
				.converterParaDTO(agendamento.getUsuario()));
		agendamentoDto.setPreCadastroDto(new FabricaPreCadastro()
				.converterParaDTO(agendamento.getPreCadastro()));
		agendamentoDto.setGruposDto(new FabricaGrupo()
				.converterParaDTO(agendamento.getGrupos()));
		agendamentoDto.setProfissionalDto(new FabricaProfissional()
				.converterParaDTO(agendamento.getProfissional()));
		agendamentoDto
				.setDescricaoTipoAtendimentoDto(new FabricaDescricaoTipoAtendimento()
						.converterParaDTO(agendamento
								.getDescricaoTipoAtendimento()));
		agendamentoDto.setModuloDto(new FabricaModulo()
				.converterParaDTO(agendamento.getModulo()));
		agendamentoDto.setData(agendamento.getData());
		agendamentoDto.setDiaSemanaDto(new FabricaDiaSemana()
				.converterParaDTO(agendamento.getDiaSemana()));
		agendamentoDto.setHorarioDto(new FabricaHorario()
				.converterParaDTO(agendamento.getHorario()));
		agendamentoDto.setSetorDto(new FabricaSetor()
				.converterParaDTO(agendamento.getSetor()));
		agendamentoDto.setReservaParaDto(new FabricaStatus()
				.converterParaDTO(agendamento.getReservaPara()));
		agendamentoDto.setLocalAtendimentoDto(new FabricaLocalAtendimento()
				.converterParaDTO(agendamento.getLocalAtendimento()));
		agendamentoDto.setObs(agendamento.getObs());
		agendamentoDto.setStatusDto(new FabricaStatusAgendamento()
				.converterParaDTO(agendamento.getStatusAtual()));
		agendamentoDto.setMotivoCancelamentoDTO(new FabricaMotivoCancelamento()
				.converterParaDTO(agendamento.getMotivoCancelamento()));
		agendamentoDto.setJustificativaCancelamento(agendamento
				.getJustificativaCancelamento());
		agendamentoDto.setEstaDisponivel(agendamento.estaDisponivel());
		agendamentoDto.setEstaCancelado(agendamento.estaCancelado());
		agendamentoDto.setEstaAgendado(agendamento.estaAgendado());
		agendamentoDto.setEstaReagendado(agendamento.estaReagendado());
		agendamentoDto.setUsuarioCriadoPeloPreCadastro(
				new FabricaUsuario().converterParaDTO(agendamento.obterUsuarioCriadoPeloPreCadastro()));
		agendamentoDto.setResponsaveisOperacoes(agendamento
				.getHistoricoOperacoes());
		return agendamentoDto;
	}

	@Override
	public Agendamento converterParaDominio(AgendamentoDTO agendamentoDto,
			Agendamento agendamento) {
		if (agendamentoDto != null) {
			if (agendamento == null) {
				agendamento = new Agendamento();
			}
			agendamento.setUsuario(new FabricaUsuario().converterParaDominio(
					agendamentoDto.getUsuarioDto(), agendamento.getUsuario()));
			agendamento.setPreCadastro(new FabricaPreCadastro()
					.converterParaDominio(agendamentoDto.getPreCadastroDto(),
							agendamento.getPreCadastro()));
			agendamento.setGrupos(new FabricaGrupo().converterParaDominio(
					agendamentoDto.getGruposDto(), agendamento.getGrupos()));
			agendamento.setProfissional(new FabricaProfissional()
					.converterParaDominio(agendamentoDto.getProfissionalDto()));
			agendamento
					.setDescricaoTipoAtendimento(new FabricaDescricaoTipoAtendimento()
							.converterParaDominio(agendamentoDto
									.getDescricaoTipoAtendimentoDto()));
			agendamento.setModulo(new FabricaModulo()
					.converterParaDominio(agendamentoDto.getModuloDto()));
			agendamento.setData(agendamentoDto.getData());
			agendamento.setHorario(new FabricaHorario()
					.converterParaDominio(agendamentoDto.getHorarioDto()));
			agendamento.setSetor(new FabricaSetor()
					.converterParaDominio(agendamentoDto.getSetorDto()));
			agendamento.setReservaPara(new FabricaStatus()
					.converterParaDominio(agendamentoDto.getReservaParaDto()));
			agendamento.setLocalAtendimento(new FabricaLocalAtendimento()
					.converterParaDominio(agendamentoDto
							.getLocalAtendimentoDto()));
			agendamento.setObs(agendamentoDto.getObs());
			agendamento.setMotivoCancelamento(new FabricaMotivoCancelamento()
					.converterParaDominio(agendamentoDto
							.getMotivoCancelamentoDTO()));
			agendamento.setJustificativaCancelamento(agendamentoDto
					.getJustificativaCancelamento());
		}
		return agendamento;
	}

	@Override
	public Agendamento obterNovo() {
		return new Agendamento();
	}
}

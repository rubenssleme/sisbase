package br.laramara.ti.sislaraserver.fabricas;

import br.laramara.ti.sislaracommons.dtos.atendimento.AtendimentoProfissionalDTO;
import br.laramara.ti.sislaraserver.dominio.atendimento.AtendimentoProfissional;

public class FabricaAtendimentoProfissional extends
	FabricaBase<AtendimentoProfissionalDTO, AtendimentoProfissional> {

	public FabricaAtendimentoProfissional() {
	}

	@Override
	public AtendimentoProfissionalDTO converterParaDTO(
			AtendimentoProfissional atendimentoProfissional) {

		AtendimentoProfissionalDTO atendimentoProfissionalDto = null;
		if (atendimentoProfissional != null) {
			atendimentoProfissionalDto = new AtendimentoProfissionalDTO();
			atendimentoProfissionalDto.setId(atendimentoProfissional.getId());
			atendimentoProfissionalDto
					.setProfissionalDto(new FabricaProfissional()
							.converterParaDTO(atendimentoProfissional
									.getProfissional()));
			atendimentoProfissionalDto
					.setInformacaoAtendimentoDto(new FabricaInformacaoAtendimento()
							.converterParaDTO(atendimentoProfissional
									.getInformacaoAtendimento()));
			atendimentoProfissionalDto
					.setApenasParticipante(atendimentoProfissional
							.isApenasParticipante());
		}
		return atendimentoProfissionalDto;
	}

	@Override
	public AtendimentoProfissional converterParaDominio(
			AtendimentoProfissionalDTO atendimentoProfissionalDto) {

		AtendimentoProfissional atendimentoProfissional = null;
		if (atendimentoProfissionalDto != null) {
			atendimentoProfissional = new AtendimentoProfissional();
			atendimentoProfissional.setId(atendimentoProfissionalDto.getId());
			atendimentoProfissional.setProfissional(new FabricaProfissional()
					.converterParaDominio(atendimentoProfissionalDto
							.getProfissionalDto()));
			atendimentoProfissional
					.setInformacaoAtendimento(new FabricaInformacaoAtendimento()
							.converterParaDominio(atendimentoProfissionalDto
									.getInformacaoAtendimentoDto()));
		}
		return atendimentoProfissional;
	}
}

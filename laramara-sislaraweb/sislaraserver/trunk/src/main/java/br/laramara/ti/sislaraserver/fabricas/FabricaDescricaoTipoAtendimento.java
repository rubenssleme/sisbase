package br.laramara.ti.sislaraserver.fabricas;

import br.laramara.ti.sislaracommons.dtos.grupo.DescricaoTipoAtendimentoDTO;
import br.laramara.ti.sislaraserver.dominio.grupo.DescricaoTipoAtendimento;

public class FabricaDescricaoTipoAtendimento extends
		FabricaBase<DescricaoTipoAtendimentoDTO, DescricaoTipoAtendimento> {

	public final DescricaoTipoAtendimento converterParaDominio(
			DescricaoTipoAtendimentoDTO descricaoTipoAtendimentoDto) {
		DescricaoTipoAtendimento descricaoTipoAtendimento = null;
		if (descricaoTipoAtendimentoDto != null) {
			descricaoTipoAtendimento = new DescricaoTipoAtendimento();
			descricaoTipoAtendimento.setId(descricaoTipoAtendimentoDto.getId());
			descricaoTipoAtendimento.setNome(descricaoTipoAtendimentoDto.getNome());
			descricaoTipoAtendimento.setDescricao(descricaoTipoAtendimentoDto.getDescrica());
			descricaoTipoAtendimento.setSigla(descricaoTipoAtendimentoDto.getSigla());
			descricaoTipoAtendimento.setSetor(new FabricaSetor()
					.converterParaDominio(descricaoTipoAtendimentoDto.getSetoresDto()));
			descricaoTipoAtendimento.setTipoAtendimento(new FabricaTipoAtendimento()
					.converterParaDominio(descricaoTipoAtendimentoDto
							.getTipoAtendimentoDto()));
			descricaoTipoAtendimento.setNomesGrupos(new FabricaNomeGrupo()
					.converterParaDominio(descricaoTipoAtendimentoDto
							.getNomesGruposDto()));
			descricaoTipoAtendimento.setModulos(new FabricaModulo()
					.converterParaDominio(descricaoTipoAtendimentoDto
							.getModulosDto()));
		}
		return descricaoTipoAtendimento;
	}

	public final DescricaoTipoAtendimentoDTO converterParaDTO(
			DescricaoTipoAtendimento descricaoTipoAtendimento) {
		DescricaoTipoAtendimentoDTO descricaoTipoAtendimentoDto = new DescricaoTipoAtendimentoDTO();
		descricaoTipoAtendimentoDto.setId(descricaoTipoAtendimento.getId());
		descricaoTipoAtendimentoDto.setNome(descricaoTipoAtendimento.getNome());
		descricaoTipoAtendimentoDto.setDescricao(descricaoTipoAtendimento.getDescricao());
		descricaoTipoAtendimentoDto.setSigla(descricaoTipoAtendimento.getSigla());
		descricaoTipoAtendimentoDto.setDescricao(descricaoTipoAtendimento.getDescricao());
		descricaoTipoAtendimentoDto.setSetoresDto(new FabricaSetor()
				.converterParaDTO(descricaoTipoAtendimento.getSetor()));
		descricaoTipoAtendimentoDto
				.setTipoAtendimentoDto(new FabricaTipoAtendimento()
						.converterSemListaParaDTO(descricaoTipoAtendimento
								.getTipoAtendimento()));
		descricaoTipoAtendimentoDto.setNomesGruposDto(new FabricaNomeGrupo()
				.converterParaDTO(descricaoTipoAtendimento.getNomesGrupos()));
		descricaoTipoAtendimentoDto.setModulosDto(new FabricaModulo()
				.converterParaDTO(descricaoTipoAtendimento.getModulos()));
		return descricaoTipoAtendimentoDto;
	}
}

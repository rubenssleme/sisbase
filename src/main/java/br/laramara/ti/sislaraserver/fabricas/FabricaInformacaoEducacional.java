package br.laramara.ti.sislaraserver.fabricas;

import br.laramara.ti.sislaracommons.dtos.escola.InformacaoEducacionalDTO;
import br.laramara.ti.sislaraserver.dominio.escola.InformacaoEducacional;

public class FabricaInformacaoEducacional extends
		FabricaRecursiva<InformacaoEducacionalDTO, InformacaoEducacional> {

	public final InformacaoEducacional converterParaDominio(
			InformacaoEducacionalDTO informacaoEducacionalDto,
			InformacaoEducacional informacaoEducacional) {
		if (informacaoEducacionalDto != null) {
			if (informacaoEducacional == null) {
				informacaoEducacional = new InformacaoEducacional();
			}
			informacaoEducacional.setId(informacaoEducacionalDto.getId());
			informacaoEducacional.setInstituicao(new FabricaInstituicao()
					.converterParaDominio(informacaoEducacionalDto
							.getInstituicaoDto()));
			informacaoEducacional.setEscolaridade(new FabricaEscolaridade()
					.converterParaDominio(informacaoEducacionalDto
							.getEscolaridadeDto()));
			informacaoEducacional.setSerie(new FabricaSerie()
					.converterParaDominio(informacaoEducacionalDto
							.getSerieDto()));
			informacaoEducacional.setPeriodo(new FabricaPeriodo()
					.converterParaDominio(informacaoEducacionalDto
							.getPeriodoDto()));
			informacaoEducacional.setSituacao(new FabricaSituacao()
					.converterParaDominio(informacaoEducacionalDto
							.getSituacaoEducacionalDto()));
			informacaoEducacional.setNomeProfessor(informacaoEducacionalDto
					.getNomeProfessor());
			informacaoEducacional.setAreaFormacao(new FabricaAreaFormacao()
					.converterParaDominio(informacaoEducacionalDto
							.getAreaFormacaoDto()));
			informacaoEducacional.setDataReferencia(informacaoEducacionalDto
					.getDataReferencia());
		}
		return informacaoEducacional;
	}

	@Override
	public InformacaoEducacional obterNovo() {
		return new InformacaoEducacional();
	}

	public final InformacaoEducacionalDTO converterParaDTO(
			InformacaoEducacional informacaoEducacional) {
		InformacaoEducacionalDTO informacaoEducacionalDto = new InformacaoEducacionalDTO();
		if (informacaoEducacional.getId() != null) {
			informacaoEducacionalDto.setId(informacaoEducacional.getId());
		}
		informacaoEducacionalDto.setInstituicaoDto(new FabricaInstituicao()
				.converterParaDTO(informacaoEducacional.getInstituicao()));
		informacaoEducacionalDto.setEscolaridadeDto(new FabricaEscolaridade()
				.converterParaDTO(informacaoEducacional.getEscolaridade()));
		informacaoEducacionalDto.setSerieDto(new FabricaSerie()
				.converterParaDTO(informacaoEducacional.getSerie()));
		informacaoEducacionalDto.setPeriodoDto(new FabricaPeriodo()
				.converterParaDTO(informacaoEducacional.getPeriodo()));
		informacaoEducacionalDto
				.setSituacaoEducacionalDto(new FabricaSituacao()
						.converterParaDTO(informacaoEducacional.getSituacao()));
		informacaoEducacionalDto.setNomeProfessor(informacaoEducacional
				.getNomeProfessor());
		informacaoEducacionalDto.setAreaFormacaoDto(new FabricaAreaFormacao()
				.converterParaDTO(informacaoEducacional.getAreaFormacao()));
		informacaoEducacionalDto.setDataReferencia(informacaoEducacional
				.getDataReferencia());
		return informacaoEducacionalDto;
	}
}

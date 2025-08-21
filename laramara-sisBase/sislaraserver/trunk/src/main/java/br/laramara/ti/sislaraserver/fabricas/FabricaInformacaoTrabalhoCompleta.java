package br.laramara.ti.sislaraserver.fabricas;

import br.laramara.ti.sislaracommons.dtos.trabalho.InformacaoTrabalhoCompletaDTO;
import br.laramara.ti.sislaraserver.dominio.trabalho.InformacaoTrabalhoCompleta;

public class FabricaInformacaoTrabalhoCompleta extends
		FabricaBase<InformacaoTrabalhoCompletaDTO, InformacaoTrabalhoCompleta> {

	@Override
	public InformacaoTrabalhoCompleta converterParaDominio(
			InformacaoTrabalhoCompletaDTO informacaoTrabalhoCompletaDto) {
		InformacaoTrabalhoCompleta informacaoTrabalhoCompleta = null;
		if (informacaoTrabalhoCompletaDto != null) {
			informacaoTrabalhoCompleta = new InformacaoTrabalhoCompleta();
			informacaoTrabalhoCompleta.setId(informacaoTrabalhoCompletaDto
					.getId());
			informacaoTrabalhoCompleta
					.setDataInicial(informacaoTrabalhoCompletaDto
							.getDataInicialVigencia());
			informacaoTrabalhoCompleta
					.setDataFinal(informacaoTrabalhoCompletaDto
							.getDataFinalVigencia());
			informacaoTrabalhoCompleta.setCargo(new FabricaCargo()
					.converterParaDominio(informacaoTrabalhoCompletaDto
							.getCargoDto()));
			informacaoTrabalhoCompleta
					.setLocalTrabalho(new FabricaLocalTrabalho()
							.converterParaDominio(informacaoTrabalhoCompletaDto
									.getLocalTrabalhoDto()));
			informacaoTrabalhoCompleta.setVoluntario(new FabricaSimNao()
					.converterParaDominio(informacaoTrabalhoCompletaDto
							.getVoluntarioDto()));
			informacaoTrabalhoCompleta.setEstagiario(
					new FabricaSimNao().converterParaDominio(informacaoTrabalhoCompletaDto.getEstagiarioDto()));
			informacaoTrabalhoCompleta
					.setEncaminhadoPorLaramara(new FabricaSimNao()
							.converterParaDominio(informacaoTrabalhoCompletaDto
									.getEncaminhadoPorLaramaraDto()));
			informacaoTrabalhoCompleta.setObs(informacaoTrabalhoCompletaDto.getObs());
		}
		return informacaoTrabalhoCompleta;
	}

	@Override
	public InformacaoTrabalhoCompletaDTO converterParaDTO(
			InformacaoTrabalhoCompleta informacaoTrabalhoCompleta) {
		InformacaoTrabalhoCompletaDTO informacoTrabalhoCompletaDto = new InformacaoTrabalhoCompletaDTO();
		if (informacaoTrabalhoCompleta.getId() != null) {
			informacoTrabalhoCompletaDto.setId(informacaoTrabalhoCompleta
					.getId());
		}
		informacoTrabalhoCompletaDto
				.setDataInicialVigencia(informacaoTrabalhoCompleta
						.getDataInicial());
		informacoTrabalhoCompletaDto
				.setDataFinalVigencia(informacaoTrabalhoCompleta.getDataFinal());
		informacoTrabalhoCompletaDto.setCargoDto(new FabricaCargo()
				.converterParaDTO(informacaoTrabalhoCompleta.getCargo()));
		informacoTrabalhoCompletaDto
				.setLocalTrabalhoDto(new FabricaLocalTrabalho()
						.converterParaDTO(informacaoTrabalhoCompleta
								.getLocalTrabalho()));
		informacoTrabalhoCompletaDto.setVoluntarioDto(new FabricaSimNao()
				.converterParaDTO(informacaoTrabalhoCompleta.getVoluntario()));
		informacoTrabalhoCompletaDto
				.setEstagiarioDto(new FabricaSimNao().converterParaDTO(informacaoTrabalhoCompleta.getEstagiario()));
		informacoTrabalhoCompletaDto
				.setEncaminhadoPorLaramaraDto(new FabricaSimNao()
						.converterParaDTO(informacaoTrabalhoCompleta
								.getEncaminhadoPorLaramara()));
		informacoTrabalhoCompletaDto.setObs(informacaoTrabalhoCompleta.getObs());
		return informacoTrabalhoCompletaDto;
	}
}

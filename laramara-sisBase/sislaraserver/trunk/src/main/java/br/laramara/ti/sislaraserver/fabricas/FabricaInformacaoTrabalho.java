package br.laramara.ti.sislaraserver.fabricas;

import br.laramara.ti.sislaracommons.dtos.familiar.InformacaoTrabalhoDTO;
import br.laramara.ti.sislaraserver.dominio.familiar.InformacaoTrabalho;

public class FabricaInformacaoTrabalho extends FabricaBase<InformacaoTrabalhoDTO, InformacaoTrabalho> {

	public final InformacaoTrabalho converterParaDominio(
			InformacaoTrabalhoDTO informacaoTrabalhoDto) {
		InformacaoTrabalho informacaoTrabalho = null;
		if (informacaoTrabalhoDto != null) {
			informacaoTrabalho = new InformacaoTrabalho();
			if (informacaoTrabalhoDto.getId() != null) {
				informacaoTrabalho
						.setId(new Long(informacaoTrabalhoDto.getId()));
			}
			informacaoTrabalho.setEmpresa(informacaoTrabalhoDto.getEmpresa());
			informacaoTrabalho.setFuncao(informacaoTrabalhoDto.getFuncao());
			informacaoTrabalho.setCargo(new FabricaCargo().converterParaDominio(informacaoTrabalhoDto.getCargoDto()));
		}
		return informacaoTrabalho;
	}

	public final InformacaoTrabalhoDTO converterParaDTO(
			InformacaoTrabalho informacaoTrabalho) {
		InformacaoTrabalhoDTO informacaoTrabalhoDto = new InformacaoTrabalhoDTO();
		if (informacaoTrabalho.getId() != null) {
			informacaoTrabalhoDto.setId(informacaoTrabalho.getId());
		}
		informacaoTrabalhoDto.setFuncao(informacaoTrabalho.getFuncao());
		informacaoTrabalhoDto.setEmpresa(informacaoTrabalho.getEmpresa());
		informacaoTrabalhoDto.setCargoDto(new FabricaCargo().converterParaDTO(informacaoTrabalho.getCargo()));
		return informacaoTrabalhoDto;
	}
}

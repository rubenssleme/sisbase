package br.laramara.ti.sislaraserver.fabricas;

import br.laramara.ti.sislaracommons.dtos.doacao.PatrocinioDTO;
import br.laramara.ti.sislaraserver.dominio.doacao.Patrocinio;

public class FabricaPatrocinio extends FabricaBase<PatrocinioDTO, Patrocinio> {

	public final PatrocinioDTO converterParaDTO(Patrocinio patrocinio) {
		PatrocinioDTO patrocinioDto = new PatrocinioDTO();
		patrocinioDto.setId(patrocinio.getId());
		patrocinioDto.setEmpresaDto(new FabricaEmpresa().converterParaDTO(patrocinio.getEmpresa()));
		patrocinioDto.setValor(patrocinio.getValor());
		return patrocinioDto;
	}

	public final Patrocinio converterParaDominio(PatrocinioDTO patrocinioDto) {
		Patrocinio patrocinio = new Patrocinio();
		patrocinio.setId(patrocinioDto.getId());
		patrocinio.setEmpresa(new FabricaEmpresa().converterParaDominio(patrocinioDto.getEmpresaDto()));
		patrocinio.setValor(patrocinioDto.getValor());
		return patrocinio;
	}
}

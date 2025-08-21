package br.laramara.ti.sislaraserver.fabricas;

import br.laramara.ti.sislaracommons.dtos.doacao.PatrocinioDTO;
import br.laramara.ti.sislaraserver.dominio.doacao.Patrocinio;

public class ContextoPatrocinio {

	public static Patrocinio criarPatrocinio() {
		Patrocinio patrocinio = new Patrocinio();
		patrocinio.setEmpresa(ContextoEmpresa.criarEmpresa());
		patrocinio.setValor("123,45");
		return patrocinio;
	}

	public static PatrocinioDTO criarPatrocinioDto() {
		PatrocinioDTO patrocinioDto = new PatrocinioDTO();
		patrocinioDto.setEmpresaDto(ContextoEmpresa.criarEmpresaDto());
		patrocinioDto.setValor("123,45");
		return patrocinioDto;
	}

}

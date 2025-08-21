package br.laramara.ti.sislaraserver.fabricas;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.dtos.doacao.PatrocinioDTO;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.dominio.doacao.Patrocinio;

public class TestesFabricaPatrocinio {
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void fabrica_patrocinio_converte_objeto_de_dominio_para_dto() {
		Patrocinio patrocinio = ContextoPatrocinio.criarPatrocinio();

		PatrocinioDTO patrocinioDTO = new FabricaPatrocinio().converterParaDTO(patrocinio);

		Assert.assertEquals(patrocinioDTO.getId(), patrocinio.getId());
		Assert.assertEquals(patrocinioDTO.getEmpresaDto().getId(), patrocinio.getEmpresa().getId());
		Assert.assertEquals(patrocinioDTO.getValor(), patrocinio.getValor());
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void fabrica_empresa_converte_objeto_de_dto_para_dominio() {
		PatrocinioDTO patrocinioDto = ContextoPatrocinio.criarPatrocinioDto();

		Patrocinio patrocinioObtido = new FabricaPatrocinio().converterParaDominio(patrocinioDto);

		Assert.assertEquals(patrocinioObtido.getId(), patrocinioDto.getId());
		Assert.assertEquals(patrocinioObtido.getEmpresa().getId(), patrocinioDto.getEmpresaDto().getId());
		Assert.assertEquals(patrocinioObtido.getValor(), patrocinioDto.getValor());
	}
}

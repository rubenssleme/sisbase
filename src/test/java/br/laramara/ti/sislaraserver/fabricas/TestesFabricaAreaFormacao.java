package br.laramara.ti.sislaraserver.fabricas;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.dtos.escola.AreaFormacaoDTO;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.dominio.escola.AreaFormacao;

public class TestesFabricaAreaFormacao {
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void fabrica_area_formacao_converte_objeto_de_dominio_para_dto() {
		AreaFormacao areaFormacao = new AreaFormacao(new Long(1), "Direito");

		AreaFormacaoDTO areaFormacaoDTO = new FabricaAreaFormacao()
				.converterParaDTO(areaFormacao);

		Assert.assertEquals(areaFormacao.getId(), areaFormacaoDTO.getId());
		Assert.assertEquals(areaFormacao.getDescricao(),
				areaFormacaoDTO.toString());
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void fabrica_area_formacao_converte_objeto_de_dto_para_dominio() {
		AreaFormacaoDTO areaFormacaoDto = new AreaFormacaoDTO(new Long(1),
				"Direito");

		AreaFormacao areaFormacao = new FabricaAreaFormacao()
				.converterParaDominio(areaFormacaoDto);

		Assert.assertEquals(areaFormacaoDto.getId(), areaFormacao.getId());
		Assert.assertEquals(areaFormacaoDto.toString(), areaFormacao.getDescricao());
	}
}

package br.laramara.ti.sislaraserver.fabricas;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.dtos.usuario.TipoConstrucaoDTO;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.dominio.usuario.TipoConstrucao;

public class TestesFabricaTipoConstrucao {
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void fabrica_tipo_construcao_converte_objeto_de_dominio_para_dto() {
		TipoConstrucao tipoConstrucao = new TipoConstrucao(new Long(1000), "TESTE");

		TipoConstrucaoDTO tipoConstrucaoDTO = new FabricaTipoConstrucao()
				.converterParaDTO(tipoConstrucao);

		Assert.assertEquals(tipoConstrucaoDTO.getId(), tipoConstrucao.getId());
		Assert.assertEquals(tipoConstrucaoDTO.toString(), tipoConstrucao.getDescricao());
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void fabrica_tipo_construcao_converte_objeto_de_dto_para_dominio() {
		TipoConstrucaoDTO tipoConstrucaoDto = new TipoConstrucaoDTO(new Long(1000),
				"TESTE");

		TipoConstrucao tipoConstrucaoObtido = new FabricaTipoConstrucao()
				.converterParaDominio(tipoConstrucaoDto);

		Assert.assertEquals(tipoConstrucaoObtido.getId(), tipoConstrucaoDto.getId());
		Assert.assertEquals(tipoConstrucaoObtido.getDescricao(), tipoConstrucaoDto.toString());
	}
}

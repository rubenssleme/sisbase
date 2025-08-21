package br.laramara.ti.sislaraserver.fabricas;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.dtos.usuario.NecessidadeDTO;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.dominio.usuario.Necessidade;

public class TestesFabricaNecessidade {
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void fabrica_necessidade_converte_objeto_de_dominio_para_dto() {
		Necessidade necessidade = new Necessidade(new Long(11111), "Necessidade A");

		NecessidadeDTO necessidadeDTO = new FabricaNecessidade()
				.converterParaDTO(necessidade);

		Assert.assertEquals(necessidadeDTO.getId(), necessidade.getId());
		Assert.assertEquals(necessidadeDTO.toString(), necessidade.getDescricao());
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void fabrica_necessidade_converte_objeto_de_dto_para_dominio() {
		NecessidadeDTO necessidadeDto = new NecessidadeDTO(new Long(11111),
				"Necessidade B");

		Necessidade necessidadeObtido = new FabricaNecessidade()
				.converterParaDominio(necessidadeDto);

		Assert.assertEquals(necessidadeObtido.getId(), necessidadeDto.getId());
		Assert.assertEquals(necessidadeObtido.getDescricao(), necessidadeDto.toString());
	}
}

package br.laramara.ti.sislaraserver.fabricas;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.dtos.atendimento.TipoVinculoDTO;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.dominio.atendimento.TipoVinculo;

public class TestesFabricaTipoVinculo {

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void fabrica_tipo_vinculo_converte_objeto_de_dominio_para_dto() {
		TipoVinculo tipoVinculo = new TipoVinculo(new Long(1), "Professor");

		TipoVinculoDTO tipoVinculoDto = new FabricaTipoVinculo()
				.converterParaDTO(tipoVinculo);

		Assert.assertEquals(tipoVinculoDto.getId(), tipoVinculo.getId());
		Assert.assertEquals(tipoVinculoDto.toString(),
				tipoVinculo.getDescricao());
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void fabrica_tipo_vinculo_converte_objeto_de_dto_para_dominio() {
		Long id = new Long(1);
		String descricao = "Professor";

		TipoVinculoDTO tipoVinculoDto = new TipoVinculoDTO(id, descricao);

		TipoVinculo beneficiosObtido = new FabricaTipoVinculo()
				.converterParaDominio(tipoVinculoDto);

		Assert.assertEquals(beneficiosObtido.getDescricao(), descricao);
		Assert.assertEquals(beneficiosObtido.getId(), id);
	}
}

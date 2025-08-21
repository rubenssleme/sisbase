package br.laramara.ti.sislaraserver.fabricas;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.dtos.usuario.OrigemEncaminhamentoDTO;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.dominio.usuario.OrigemEncaminhamento;

public class TestesFabricaOrigemEncaminhamento {
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void fabrica_origem_encaminhamento_converte_objeto_de_dominio_para_dto() {
		OrigemEncaminhamento origemEncaminhamento = new OrigemEncaminhamento(new Long(1), "Hospital");

		OrigemEncaminhamentoDTO origemEncaminhamentoDTO = new FabricaOrigemEncaminhamento()
				.converterParaDTO(origemEncaminhamento);

		Assert.assertEquals(origemEncaminhamentoDTO.getId(), origemEncaminhamento.getId());
		Assert.assertEquals(origemEncaminhamentoDTO.toString(), origemEncaminhamento.getDescricao());
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void fabrica_origem_encaminhamento_converte_objeto_de_dto_para_dominio() {
		Long id = new Long(1);
		String descricao = "Hospital";

		OrigemEncaminhamentoDTO origemEncaminhamentoDto = new OrigemEncaminhamentoDTO(id, descricao);

		OrigemEncaminhamento origemEncaminhamentoObtido = new FabricaOrigemEncaminhamento()
				.converterParaDominio(origemEncaminhamentoDto);

		Assert.assertEquals(origemEncaminhamentoObtido.getDescricao(), origemEncaminhamentoDto.toString());
		Assert.assertEquals(origemEncaminhamentoObtido.getId(), origemEncaminhamentoDto.getId());
	}
}

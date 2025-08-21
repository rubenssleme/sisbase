package br.laramara.ti.sislaraserver.fabricas;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.dtos.usuario.EncaminhamentoDTO;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.dominio.usuario.Encaminhamento;

public class TestesFabricaEncaminhamento {

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void fabrica_encaminhamento_converte_objeto_de_dominio_para_dto() {
		Encaminhamento encaminhamento = ContextoEncaminhamento.fabricarEncaminhamento();

		EncaminhamentoDTO encaminhamentoDTO = new FabricaEncaminhamento().converterParaDTO(encaminhamento);

		Assert.assertEquals(encaminhamentoDTO.getId(), encaminhamento.getId());
		Assert.assertEquals(encaminhamentoDTO.toString(), encaminhamento.getDescricao());
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void fabrica_encaminhamento_converte_objeto_de_dto_para_dominio() {
		EncaminhamentoDTO encaminhamentoDto = ContextoEncaminhamento.fabricarEncaminhamentoDto();

		Encaminhamento encaminhamento = new FabricaEncaminhamento().converterParaDominio(encaminhamentoDto);

		Assert.assertEquals(encaminhamento.getId(), encaminhamentoDto.getId());
		Assert.assertEquals(encaminhamento.getDescricao(), encaminhamentoDto.toString());
	}
}

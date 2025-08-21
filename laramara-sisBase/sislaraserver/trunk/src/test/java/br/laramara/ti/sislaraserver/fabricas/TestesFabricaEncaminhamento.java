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
		encaminhamento.setId(new Long(12));

		EncaminhamentoDTO encaminhamentoDTO = new FabricaEncaminhamento().converterParaDTO(encaminhamento);

		Assert.assertEquals(encaminhamentoDTO.getId(), encaminhamento.getId());
		Assert.assertEquals(encaminhamentoDTO.getProfissionalLiberal(), encaminhamento.getProfissionalLiberal());
		Assert.assertEquals(encaminhamentoDTO.getOrigemEncaminhamentoDto().getId(), encaminhamento.getOrigemEncaminhamento().getId());
		Assert.assertEquals(encaminhamentoDTO.getRedeEncaminhamentoDto().getId(), encaminhamento.getRedeEncaminhamento().getId());
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void fabrica_encaminhamento_converte_objeto_de_dto_para_dominio() {
		EncaminhamentoDTO encaminhamentoDto = ContextoEncaminhamento.fabricarEncaminhamentoDto();
		encaminhamentoDto.setId(new Long(12));

		Encaminhamento encaminhamento = new FabricaEncaminhamento().converterParaDominio(encaminhamentoDto);

		Assert.assertEquals(encaminhamento.getId(), encaminhamentoDto.getId());
		Assert.assertEquals(encaminhamento.getProfissionalLiberal(), encaminhamentoDto.getProfissionalLiberal());
		Assert.assertEquals(encaminhamento.getOrigemEncaminhamento().getId(),
				encaminhamentoDto.getOrigemEncaminhamentoDto().getId());
		Assert.assertEquals(encaminhamento.getRedeEncaminhamento().getId(),
				encaminhamentoDto.getRedeEncaminhamentoDto().getId());
	}
}

package br.laramara.ti.sislaraserver.fabricas;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.dtos.usuario.OrigemEncaminhamentoDetalhadoDTO;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.dominio.usuario.OrigemEncaminhamentoDetalhado;

public class TestesFabricaOrigemEncaminhamentoDetalhado {

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void fabrica_origem_encaminhamento_detalhado_converte_objeto_de_dominio_para_dto() {

		OrigemEncaminhamentoDetalhado encaminhamento = ContextoOrigemEncaminhamentoDetalhado.fabricarOrigemEncaminhamentoDetalhado();
		encaminhamento.setId(new Long(12));

		OrigemEncaminhamentoDetalhadoDTO encaminhamentoDTO = new FabricaOrigemEncaminhamentoDetalhado().converterParaDTO(encaminhamento);

		Assert.assertEquals(encaminhamentoDTO.getId(), encaminhamento.getId());
		Assert.assertEquals(encaminhamentoDTO.getProfissionalLiberal(), encaminhamento.getProfissionalLiberal());
		Assert.assertEquals(encaminhamentoDTO.getOrigemEncaminhamentoDto().getId(), encaminhamento.getOrigemEncaminhamento().getId());
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void fabrica_origem_encaminhamento_detalhado_converte_objeto_de_dto_para_dominio() {
		OrigemEncaminhamentoDetalhadoDTO encaminhamentoDto = ContextoOrigemEncaminhamentoDetalhado.fabricarEncaminhamentoDto();
		encaminhamentoDto.setId(new Long(12));

		OrigemEncaminhamentoDetalhado encaminhamento = new FabricaOrigemEncaminhamentoDetalhado().converterParaDominio(encaminhamentoDto);

		Assert.assertEquals(encaminhamento.getId(), encaminhamentoDto.getId());
		Assert.assertEquals(encaminhamento.getProfissionalLiberal(), encaminhamentoDto.getProfissionalLiberal());
		Assert.assertEquals(encaminhamento.getOrigemEncaminhamento().getId(),
				encaminhamentoDto.getOrigemEncaminhamentoDto().getId());
	}
}

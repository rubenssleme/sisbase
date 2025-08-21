package br.laramara.ti.sislaraserver.fabricas;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.dtos.usuario.RedeEncaminhamentoDTO;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.dominio.usuario.RedeEncaminhamento;

public class TestesFabricaRedeEcaminhamento {
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void fabrica_redeencaminhamento_converte_objeto_de_dominio_para_dto() {
		RedeEncaminhamento redeEncaminhamento = new RedeEncaminhamento(new Long(1), "SUAS");

		RedeEncaminhamentoDTO redeEncaminhamentoDTO = new FabricaRedeEncaminhamento()
				.converterParaDTO(redeEncaminhamento);

		Assert.assertEquals(redeEncaminhamentoDTO.getId(), redeEncaminhamento.getId());
		Assert.assertEquals(redeEncaminhamentoDTO.toString(), redeEncaminhamento.getDescricao());
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void fabrica_rede_encaminhamento_converte_objeto_de_dto_para_dominio() {
		Long id = new Long(1);
		String descricao = "SUAS";

		RedeEncaminhamentoDTO redeEncaminhamentoDto = new RedeEncaminhamentoDTO(id, descricao);

		RedeEncaminhamento redeEncaminhamentoObtido = new FabricaRedeEncaminhamento()
				.converterParaDominio(redeEncaminhamentoDto);

		Assert.assertEquals(redeEncaminhamentoObtido.getDescricao(), redeEncaminhamentoDto.toString());
		Assert.assertEquals(redeEncaminhamentoObtido.getId(), redeEncaminhamentoDto.getId());
	}
}

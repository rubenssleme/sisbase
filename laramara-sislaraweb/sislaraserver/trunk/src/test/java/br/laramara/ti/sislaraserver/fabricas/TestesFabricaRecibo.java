package br.laramara.ti.sislaraserver.fabricas;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.dtos.doacao.ReciboDTO;
import br.laramara.ti.sislaracommons.utilitarios.DinheiroUtils;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.dominio.doacao.Recibo;

public class TestesFabricaRecibo {

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void fabrica_recibo_converte_objeto_de_dominio_para_dto() {
		Recibo recibo = ContextoRecibo.fabricarRecibo();

		ReciboDTO reciboDTO = new FabricaRecibo().converterParaDTO(recibo);

		Assert.assertEquals(reciboDTO.getId(), recibo.getId());
		Assert.assertEquals(reciboDTO.getCpfCnpj(), recibo.getCpfCnpj());
		Assert.assertEquals(reciboDTO.getData(), recibo.obterData());
		Assert.assertEquals(reciboDTO.getDescricao(), recibo.getDescricao());
		Assert.assertEquals(reciboDTO.isCancelado(), recibo.estaCancelado());
		Assert.assertEquals(reciboDTO.getMotivoDoCancelamento(), recibo.getMotivoDoCancelamento());
		Assert.assertEquals(reciboDTO.getNome(), recibo.getNome());
		Assert.assertEquals(reciboDTO.getValorTotalRecibo(), DinheiroUtils.obterDinheiro(recibo.getValorTotalRecibo()));
		Assert.assertEquals(reciboDTO.getFilial().getId(), recibo.getFilial().getId());
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void fabrica_recibo_converte_objeto_de_dto_para_dominio() {
		ReciboDTO reciboDto = ContextoRecibo.fabricarReciboDto();

		Recibo reciboObtido = new FabricaRecibo().converterParaDominio(reciboDto);

		Assert.assertEquals(reciboObtido.getId(), reciboDto.getId());
		Assert.assertEquals(reciboObtido.getCpfCnpj(), reciboDto.getCpfCnpj());
		Assert.assertEquals(reciboObtido.obterData(), reciboDto.getData());
		Assert.assertEquals(reciboObtido.getDescricao(), reciboDto.getDescricao());
		Assert.assertEquals(reciboObtido.getMotivoDoCancelamento(), reciboDto.getMotivoDoCancelamento());
		Assert.assertEquals(reciboObtido.getNome(), reciboDto.getNome());
		Assert.assertEquals(DinheiroUtils.obterDinheiro(reciboObtido.getValorTotalRecibo()), reciboDto.getValorTotalRecibo());
		Assert.assertEquals(reciboObtido.getFilial().getId(), reciboDto.getFilial().getId());
	}
}

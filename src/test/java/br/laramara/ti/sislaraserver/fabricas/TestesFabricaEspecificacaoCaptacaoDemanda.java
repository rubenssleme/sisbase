package br.laramara.ti.sislaraserver.fabricas;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.dtos.doacao.EspecificacaoCaptacaoDemandaDTO;
import br.laramara.ti.sislaracommons.utilitarios.DinheiroUtils;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.dominio.doacao.EspecificacaoCaptacaoDemanda;

public class TestesFabricaEspecificacaoCaptacaoDemanda {
	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void fabrica_especificacao_captacao_demanda_converte_objeto_de_dto_para_dominio() {
		EspecificacaoCaptacaoDemandaDTO especificacaoCaptacaoDemandaDto = ContextoEspecificacaoCaptacaoDemanda
				.fabricarDemandaComTodosOsDadosAlternativo();

		EspecificacaoCaptacaoDemanda especificacaoCaptacaoDemanda = new FabricaEspecificacaoCaptacaoDemanda()
				.converterParaDominio(especificacaoCaptacaoDemandaDto);

		Assert.assertEquals(especificacaoCaptacaoDemanda.getDemanda().getId(),
				especificacaoCaptacaoDemandaDto.getDemandaDto().getId());
		Assert.assertEquals(especificacaoCaptacaoDemanda.getValor(),
				DinheiroUtils.obterDinheiroOuInvalido(especificacaoCaptacaoDemandaDto.getValor()));
	}
}

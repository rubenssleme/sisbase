package br.laramara.ti.sislaraserver.fabricas;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.dtos.doacao.EspecificacaoPesquisaDemandaDTO;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.dominio.doacao.EspecificacaoPesquisaDemanda;

public class TestesFabricaEspecificacaoPesquisaDemanda {

	@Test(groups = { TiposDeTeste.INTEGRACAO_SEM_BD })
	public void fabrica_especificacao_pesquisa_demanda_converte_objeto_de_dto_para_dominio() {
		EspecificacaoPesquisaDemandaDTO especificacaoPesquisaDemandaDto = ContextoEspecificacaoPesquidaDemanda
				.fabricarDtoComTodosOsDados();

		EspecificacaoPesquisaDemanda especificacaoPesquisaDemanda = new FabricaEspecificacaoPesquisaDemanda()
				.converterParaDominio(especificacaoPesquisaDemandaDto);

		Assert.assertEquals(especificacaoPesquisaDemanda.getRecurso().getId(),
				especificacaoPesquisaDemandaDto.getRecurso().getId());
		Assert.assertEquals(especificacaoPesquisaDemanda.getProntuario().toString(),
				especificacaoPesquisaDemandaDto.getProntuario());
		Assert.assertEquals(especificacaoPesquisaDemanda.getPreCadastro().getId(),
				especificacaoPesquisaDemandaDto.getPreCadastro().getId());
		Assert.assertEquals(especificacaoPesquisaDemanda.getStatusDemanda().toString(),
				especificacaoPesquisaDemandaDto.getStatusDemandaDTO().toString());
		Assert.assertEquals(especificacaoPesquisaDemanda.getCpf(), especificacaoPesquisaDemandaDto.getCpf());
	}
}

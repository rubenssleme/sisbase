package br.laramara.ti.sislaraserver.fabricas;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.dtos.grupo.LocalAtendimentoDTO;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.dominio.grupo.LocalAtendimento;

public class TestesFabricaLocalAtendimento {

	@Test(groups = { TiposDeTeste.INTEGRACAO_SEM_BD })
	public void fabrica_localatendimento_converte_objeto_de_dto_para_dominio() {
		LocalAtendimentoDTO localAtendimentoDto = ContextoLocalAtendimento
				.construirLocalAtendimentoDTO();

		LocalAtendimento localAtendimento = new FabricaLocalAtendimento()
				.converterParaDominio(localAtendimentoDto);

		Assert.assertEquals(localAtendimento.getId(),
				localAtendimentoDto.getId());
		Assert.assertEquals(localAtendimento.getNome(),
				localAtendimentoDto.toString());
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_SEM_BD })
	public void fabrica_localatendimento_converte_objeto_de_dominio_para_dto() {
		LocalAtendimento localAtendimento = ContextoLocalAtendimento
				.fabricarComTodosOsDados();

		LocalAtendimentoDTO localAtendimentoDto = new FabricaLocalAtendimento()
				.converterParaDTO(localAtendimento);

		Assert.assertEquals(localAtendimento.getId(),
				localAtendimentoDto.getId());
		Assert.assertEquals(localAtendimento.getNome(),
				localAtendimentoDto.toString());
	}
}

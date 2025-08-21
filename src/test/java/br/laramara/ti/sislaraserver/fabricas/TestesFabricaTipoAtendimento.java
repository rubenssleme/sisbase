package br.laramara.ti.sislaraserver.fabricas;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.dtos.grupo.TipoAtendimentoDTO;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.dominio.grupo.TipoAtendimento;

public class TestesFabricaTipoAtendimento {
	@Test(groups = { TiposDeTeste.INTEGRACAO_SEM_BD })
	public void fabrica_tipoatendimento_converte_objeto_de_dto_para_dominio() {
		TipoAtendimentoDTO tipoAtendimentoDto = ContextoTipoAtendimento
				.construirTipoAtendimentoDTO();

		TipoAtendimento tipoAtendimento = new FabricaTipoAtendimento()
				.converterParaDominio(tipoAtendimentoDto);

		Assert.assertEquals(tipoAtendimento.getId(), tipoAtendimentoDto.getId());
		Assert.assertEquals(tipoAtendimento.getNome(),
				tipoAtendimentoDto.toString());
		Assert.assertEquals(tipoAtendimento.getDescricoesTipoAtendimentoAtivos()
				.size(), tipoAtendimentoDto.getDescricoesTipoAtendimentoDto()
				.size());
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_SEM_BD })
	public void fabrica_tipoatendimento_converte_objeto_de_dominio_para_dto() {
		TipoAtendimento tipoAtendimento = ContextoTipoAtendimento
				.fabricarComTodosOsDados();

		TipoAtendimentoDTO tipoAtendimentoDto = new FabricaTipoAtendimento()
				.converterParaDTO(tipoAtendimento);

		Assert.assertEquals(tipoAtendimento.getId(),
				tipoAtendimentoDto.getId());
		Assert.assertEquals(tipoAtendimento.getNome(),
				tipoAtendimentoDto.toString());
		Assert.assertEquals(tipoAtendimento.getDescricoesTipoAtendimentoAtivos()
				.size(), tipoAtendimentoDto.getDescricoesTipoAtendimentoDto()
				.size());
	}
}

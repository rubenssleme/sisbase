package br.laramara.ti.sislaraserver.fabricas.externa;

import org.junit.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.dtos.evento.DescricaoEventoDTO;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.dominio.evento.DescricaoEvento;
import br.laramara.ti.sislaraserver.fabricas.ContextoDescricaoEvento;

public class TestesFabricaDescricaoEvento {

	@Test(groups = { TiposDeTeste.INTEGRACAO_SEM_BD })
	public void fabrica_descricaoevento_converte_dto_para_dominio() {
		DescricaoEventoDTO descricaoEventoDto = ContextoDescricaoEvento.construirDescricaoEventoDescricaoEmentaDTO();

		DescricaoEvento descricaoEventoDominio = new FabricaDescricaoEvento()
				.converterParaDominio(descricaoEventoDto);

		Assert.assertEquals(descricaoEventoDto.getConteudo(), descricaoEventoDominio.getConteudo());
		Assert.assertEquals(descricaoEventoDto.getTipoDescricaoEvento().toString(), descricaoEventoDominio.getTipoDescricaoEvento().toString());
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_SEM_BD })
	public void fabrica_descricaoevento_converte_dominio_para_dto() {
		DescricaoEvento descricaoEventoDominio = ContextoDescricaoEvento.fabricarDescricaoEmentaComTodosOsDados();

		DescricaoEventoDTO descricaoEventoDto = new FabricaDescricaoEvento()
				.converterParaDTO(descricaoEventoDominio);

		Assert.assertEquals(descricaoEventoDominio.getConteudo(), descricaoEventoDto.getConteudo());
		Assert.assertEquals(descricaoEventoDominio.getTipoDescricaoEvento().toString(), descricaoEventoDto.getTipoDescricaoEvento().toString());
	}
	
}

package br.laramara.ti.sislaraserver.fabricas.externa;

import org.junit.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.dtos.evento.TipoDescricaoEventoDTO;
import br.laramara.ti.sislaraserver.dominio.evento.TipoDescricaoEvento;

public class TestesFabricaTipoDescricaoEvento {

	@Test
	public void fabrica_tipodescricaoevento_converte_dto_para_dominio() {
		TipoDescricaoEventoDTO tipoDescricaoEventoDto = new TipoDescricaoEventoDTO(
				TipoDescricaoEvento.CONTEUDO_PROGRAMACAO.toString());

		TipoDescricaoEvento tipoDescricaoEventoDominio = new FabricaTipoDescricaoEvento()
				.converterParaDominio(tipoDescricaoEventoDto);

		Assert.assertEquals(tipoDescricaoEventoDto.toString(), tipoDescricaoEventoDominio.toString());
	}

	@Test
	public void fabrica_tipodescricaoevento_converte_dominio_para_dto() {
		TipoDescricaoEvento tipoDescricaoEventoDominio = TipoDescricaoEvento.CONTEUDO_PROGRAMACAO;

		TipoDescricaoEventoDTO tipoDescricaoEventoDto = new FabricaTipoDescricaoEvento()
				.converterParaDTO(tipoDescricaoEventoDominio);

		Assert.assertEquals(tipoDescricaoEventoDominio.toString(), tipoDescricaoEventoDto.toString());
	}
}

package br.laramara.ti.sislaracommons.dtos.evento;

import org.testng.Assert;
import org.testng.annotations.Test;

public class TestesTipoDescricaoEventoDTO {

	@Test
	public void tipodescricaoeventodto_criado_com_sucesso() {
		String tipoDescricaoEvento = "Conteudo / Programação";
		TipoDescricaoEventoDTO tipoDescricaoEventoDto = new TipoDescricaoEventoDTO(tipoDescricaoEvento);

		Assert.assertFalse(tipoDescricaoEvento.toString().isEmpty());
		Assert.assertEquals(tipoDescricaoEventoDto.toString(), tipoDescricaoEvento);
	}
}

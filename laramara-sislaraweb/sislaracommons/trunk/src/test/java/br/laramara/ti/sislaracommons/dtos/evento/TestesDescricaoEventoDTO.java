package br.laramara.ti.sislaracommons.dtos.evento;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesDescricaoEventoDTO {

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void descricaoeventodto_foi_construida_pelo_server_com_sucesso() {
		Long id = 1L;
		TipoDescricaoEventoDTO tipoDescricaoEventoDTO = new TipoDescricaoEventoDTO("Conteúdo / Programação");
		String conteudo = "História e Importancia\n" + "Conceituação de deficiencia Visual";
		
		DescricaoEventoDTO descricaoEventoDto = new DescricaoEventoDTO();

		descricaoEventoDto.setId(id);
		descricaoEventoDto.setTipoDescricaoEvento(tipoDescricaoEventoDTO);
		descricaoEventoDto.setConteudo(conteudo);
		
		Assert.assertFalse(descricaoEventoDto.toString().isEmpty());
		Assert.assertEquals(descricaoEventoDto.getId(), id);
		Assert.assertEquals(descricaoEventoDto.getConteudo(), conteudo);
		Assert.assertEquals(descricaoEventoDto.getTipoDescricaoEvento(), tipoDescricaoEventoDTO);
	}

}

package br.laramara.ti.sismovimentacaocommons.dtos.movimentacao;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sismovimentacaocommons.utilitarios.TiposDeTeste;

public class TestesStatusDTO {
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void statusdto_foi_construida_com_sucesso() {

		String status = "ENTRADA_DO_ARQUIVO";
		ClassificacaoDTO classificacaoDto = new ClassificacaoDTO("BRANCO");
		StatusDTO statusDto = new StatusDTO(status, classificacaoDto);

		Assert.assertEquals(statusDto.toString(), status);
		Assert.assertEquals(statusDto.getClassificacaoDto(), classificacaoDto);
	}
}

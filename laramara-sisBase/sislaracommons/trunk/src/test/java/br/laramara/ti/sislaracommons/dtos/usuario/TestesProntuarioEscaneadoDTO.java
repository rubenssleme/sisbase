package br.laramara.ti.sislaracommons.dtos.usuario;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.dtos.ArquivoDTO;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesProntuarioEscaneadoDTO {

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void prontuarioescaneadodto_foi_construida_com_sucesso() {
		String nomeArquivo = "Pront 1222 pag 1";

		ProntuarioEscaneadoDTO periodoDto = new ProntuarioEscaneadoDTO(
				nomeArquivo, new ArquivoDTO());

		Assert.assertEquals(periodoDto.toString(), nomeArquivo);
	}
}

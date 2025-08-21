package br.laramara.ti.sislaracommons.dtos.grupo;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesEspecificacaoGeracaoAtendimentosDTO {

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void especificacaodto_foi_construida_com_sucesso() {
		Long id = new Long(1222);
		GrupoDTO grupoDto = new GrupoDTO();
		grupoDto.setId(id);
		ModuloPeriodoDTO moduloPeriodoDto = new ModuloPeriodoDTO();
		moduloPeriodoDto.setId(id);
		String data = "31/01/2012";
		String hora = "12:00";

		EspecificacaoGeracaoAtendimentosDTO especificacaoGeracaoAtendimentosDTO = new EspecificacaoGeracaoAtendimentosDTO(
				grupoDto.getId(), moduloPeriodoDto.getId(), data, hora, hora);

		Assert.assertEquals(especificacaoGeracaoAtendimentosDTO.getIdGrupo(),
				id);
		Assert.assertEquals(
				especificacaoGeracaoAtendimentosDTO.getIdModuloPeriodoDto(), id);
		Assert.assertEquals(especificacaoGeracaoAtendimentosDTO.getData(), data);
		Assert.assertEquals(
				especificacaoGeracaoAtendimentosDTO.getHoraInicial(), hora);
		Assert.assertEquals(especificacaoGeracaoAtendimentosDTO.getHoraFinal(),
				hora);
	}
}

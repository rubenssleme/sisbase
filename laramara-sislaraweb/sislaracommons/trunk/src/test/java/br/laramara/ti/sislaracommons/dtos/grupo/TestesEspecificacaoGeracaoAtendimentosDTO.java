package br.laramara.ti.sislaracommons.dtos.grupo;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.dtos.HorarioDTO;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesEspecificacaoGeracaoAtendimentosDTO {

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void especificacaodto_foi_construida_com_sucesso() {
		Long id = new Long(1222);
		GrupoDTO grupoDto = new GrupoDTO();
		grupoDto.setId(id);
		ModuloPeriodoDTO moduloPeriodoDto = new ModuloPeriodoDTO();
		moduloPeriodoDto.setId(id);
		String data = "31/12/2019";
		HorarioDTO horarioDto = new HorarioDTO("09:00", "10:00");

		EspecificacaoGeracaoAtendimentoDTO especificacaoGeracaoAtendimentosDTO = new EspecificacaoGeracaoAtendimentoDTO(moduloPeriodoDto.getId(), data, horarioDto);

		Assert.assertEquals(
				especificacaoGeracaoAtendimentosDTO.getIdModuloPeriodoDto(), id);
		Assert.assertEquals(especificacaoGeracaoAtendimentosDTO.getData(), data);
		Assert.assertEquals(especificacaoGeracaoAtendimentosDTO.getHorarioDto(), horarioDto);
	}
}

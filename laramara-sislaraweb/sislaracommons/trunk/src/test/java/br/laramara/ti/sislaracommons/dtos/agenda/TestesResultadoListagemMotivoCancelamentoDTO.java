package br.laramara.ti.sislaracommons.dtos.agenda;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesResultadoListagemMotivoCancelamentoDTO {

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void resultadolistagemagendamentodto_sem_erro_foi_construido() {
		List<MotivoCancelamentoDTO> motivosCancelamentoDto = new ArrayList<>();
		motivosCancelamentoDto.add(new MotivoCancelamentoDTO(new Long(1), "A"));
		motivosCancelamentoDto.add(new MotivoCancelamentoDTO(new Long(2), "B"));
		ResultadoListagemMotivoCancelamentoDTO resultadoDto = new ResultadoListagemMotivoCancelamentoDTO();
		resultadoDto.efetuadoComSucesso(motivosCancelamentoDto);

		Assert.assertTrue(resultadoDto.sucesso());
		Assert.assertFalse(resultadoDto.getObjetosDto().isEmpty());
	}
}

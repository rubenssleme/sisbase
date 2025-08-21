package br.laramara.ti.sislaracommons.dtos.doacao;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesResultadoListagemProjetoDTO {

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void resultadolistagemprojetodto_sem_erro_foi_construido() {
		List<ProjetoDTO> projetoDto = new ArrayList<>();
		projetoDto.add(new ProjetoDTO());
		projetoDto.add(new ProjetoDTO());
		ResultadoListagemProjetoDTO resultadoListagemProjetoDto = new ResultadoListagemProjetoDTO();
		resultadoListagemProjetoDto.efetuadoComSucesso(projetoDto);

		Assert.assertTrue(resultadoListagemProjetoDto.sucesso());
		Assert.assertFalse(resultadoListagemProjetoDto.getObjetosDto().isEmpty());
	}
}

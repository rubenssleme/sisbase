package br.laramara.ti.sislaracommons.dtos.usuario;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.dtos.grupo.LoteRecursoDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.ResultadoValidacaoLoteRecursoDTO;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesResultadoValidacaoLoteRecursoDTO {
	
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void resultadovalidacaoloterecursodto_sem_erro_foi_construido() {
		ResultadoValidacaoLoteRecursoDTO resultadoValidacaoLoteRecursoDto = new ResultadoValidacaoLoteRecursoDTO();
		resultadoValidacaoLoteRecursoDto.efetuadoComSucesso(new LoteRecursoDTO(null, null, null, null));
				

		Assert.assertTrue(resultadoValidacaoLoteRecursoDto.sucesso());
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void resultadovalidacaoloterecurso_com_erro_foi_construido() {
		String mensagemErro = "Houve algum erro";

		ResultadoValidacaoLoteRecursoDTO resultadoValidacaoLoteRecursoDto = new ResultadoValidacaoLoteRecursoDTO();
		resultadoValidacaoLoteRecursoDto.adicionarErro(mensagemErro);

		Assert.assertFalse(resultadoValidacaoLoteRecursoDto.sucesso());
		Assert.assertNotNull(resultadoValidacaoLoteRecursoDto.obterMensagens());
	}


}

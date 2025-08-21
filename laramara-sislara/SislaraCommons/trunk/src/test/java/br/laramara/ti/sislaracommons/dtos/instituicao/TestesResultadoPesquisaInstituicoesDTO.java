package br.laramara.ti.sislaracommons.dtos.instituicao;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.dtos.instituicao.InstituicaoDTO;
import br.laramara.ti.sislaracommons.dtos.instituicao.ResultadoListagemInstituicaoDTO;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesResultadoPesquisaInstituicoesDTO {
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void resultadopesquisainstituicaodto_com_usuarios_sem_erro_foi_construido() {
		List<InstituicaoDTO> instituicao = new ArrayList<>();
		instituicao.add(new InstituicaoDTO());
		instituicao.add(new InstituicaoDTO());

		ResultadoListagemInstituicaoDTO resultado = new ResultadoListagemInstituicaoDTO();
		resultado.efetuadoComSucesso(instituicao);

		Assert.assertFalse(resultado.getObjetosDto().isEmpty());
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void resultadopesquisainstituicaodto_com_um_usuario_sem_erro_foi_construido() {

		ResultadoListagemInstituicaoDTO resultado = new ResultadoListagemInstituicaoDTO();
		resultado.efetuadoComSucesso(new InstituicaoDTO());

		Assert.assertFalse(resultado.getObjetosDto().isEmpty());
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void resultadopesquisainstituicaodto_vazio_foi_construido() {

		ResultadoListagemInstituicaoDTO resultado = new ResultadoListagemInstituicaoDTO();
		resultado.nenhumRegistroEncontrado();

		Assert.assertFalse(resultado.sucesso());
		Assert.assertNotNull(resultado.obterMensagens());
	}
}

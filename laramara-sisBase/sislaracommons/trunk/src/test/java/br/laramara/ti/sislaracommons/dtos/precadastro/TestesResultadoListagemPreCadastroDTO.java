package br.laramara.ti.sislaracommons.dtos.precadastro;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesResultadoListagemPreCadastroDTO {
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void resultadolistagemprecadastrodto_com_usuarios_sem_erro_foi_construido() {
		List<PreCadastroDTO> preCadastros = new ArrayList<>();
		preCadastros.add(new PreCadastroDTO());
		preCadastros.add(new PreCadastroDTO());

		ResultadoListagemPreCadastroDTO resultado = new ResultadoListagemPreCadastroDTO();
		resultado.efetuadoComSucesso(preCadastros);

		Assert.assertFalse(resultado.getObjetosDto().isEmpty());
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void resultadolistagemprecadastrodto_com_um_usuario_sem_erro_foi_construido() {
		ResultadoListagemPreCadastroDTO resultado = new ResultadoListagemPreCadastroDTO();
		resultado.efetuadoComSucesso(new PreCadastroDTO());

		Assert.assertFalse(resultado.getObjetosDto().isEmpty());
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void resultadopesquisaprecadastrodto_vazio_foi_construido() {

		ResultadoListagemPreCadastroDTO resultado = new ResultadoListagemPreCadastroDTO();
		resultado.nenhumRegistroEncontrado();

		Assert.assertFalse(resultado.sucesso());
		Assert.assertNotNull(resultado.obterMensagens());
	}
}

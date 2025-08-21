package br.laramara.ti.sislaraserver.dominio.grupo;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.fabricas.ContextoModuloPeriodo;

public class TestesEdicaoModuloPeriodo {
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void fabrica_resultado_edicao_edicao_modulo_periodo_sem_erro() {
		ModuloPeriodo moduloPeriodo = ContextoModuloPeriodo.fabricarComTodosOsDadosInformatica();
		ResultadoEdicaoModuloPeriodo resultadoEdicaoModuloperiodo = new ResultadoEdicaoModuloPeriodo();
		resultadoEdicaoModuloperiodo.efetuadoComSucesso(moduloPeriodo);

		Assert.assertTrue(resultadoEdicaoModuloperiodo.sucesso());
		Assert.assertEquals(resultadoEdicaoModuloperiodo.getModuloPeriodo().getId(), moduloPeriodo.getId());
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void fabrica_resultado_edicao_edicao_modulo_periodo_com_erro() {
		String textoDeErro = "Texto de erro";
		ResultadoEdicaoModuloPeriodo resultadoEdicaoModuloperiodo = new ResultadoEdicaoModuloPeriodo();
		resultadoEdicaoModuloperiodo.adicionarErro(textoDeErro);

		Assert.assertFalse(resultadoEdicaoModuloperiodo.sucesso());
		Assert.assertTrue(resultadoEdicaoModuloperiodo.getMensagem().contains(textoDeErro));
	}
}

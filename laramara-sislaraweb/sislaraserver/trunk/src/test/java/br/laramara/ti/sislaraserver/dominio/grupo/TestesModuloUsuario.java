package br.laramara.ti.sislaraserver.dominio.grupo;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.fabricas.ContextoGenerico;
import br.laramara.ti.sislaraserver.fabricas.ContextoModuloUsuario;

public class TestesModuloUsuario {
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void modulousuario_validacao_com_erro_obrigatoriedade() {
		ModuloUsuario moduloUsuario = new ModuloUsuario();
		moduloUsuario.setDataOcorrencia("1212121");
		
		moduloUsuario.validarObrigatoriedadeETamanhoMaximoDeDados();

		Assert.assertEquals(moduloUsuario.obterNumeroErros(), 4);
	}
	
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void modulousuario_validacao_com_erro_data_desligamento_invalida() {
		ModuloUsuario moduloUsuario = new ModuloUsuario();
		moduloUsuario.setDataOcorrencia("1212121");
		moduloUsuario.setStatus(StatusRelacaoComModulo.DESLIGADO);
			
		moduloUsuario.validarObrigatoriedadeETamanhoMaximoDeDados();

		Assert.assertEquals(moduloUsuario.obterNumeroErros(), 3);
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void modulousuario_validacao_com_erro_tamanho_maximo() {
		ModuloUsuario moduloUsuario = ContextoModuloUsuario.fabricarComTodosOsDados();
		moduloUsuario.setObs(ContextoGenerico.obterGrande());
		
		moduloUsuario.validarObrigatoriedadeETamanhoMaximoDeDados();

		Assert.assertEquals(moduloUsuario.obterNumeroErros(), 1);
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void modulousuario_validacao_sem_erro_obrigatoriedade() {
		ModuloUsuario moduloUsuario = ContextoModuloUsuario
				.fabricarComTodosOsDados();

		moduloUsuario.validarObrigatoriedadeETamanhoMaximoDeDados();

		Assert.assertTrue(moduloUsuario.validado());
	}
}
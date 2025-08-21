package br.laramara.ti.sislaraserver.dominio.grupo;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.fabricas.ContextoGenerico;
import br.laramara.ti.sislaraserver.fabricas.ContextoModuloPreCadastro;

public class TestesModuloPreCadastro {
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void moduloprecadastro_validacao_com_erro_obrigatoriedade() {
		ModuloPreCadastro moduloPreCadastro = new ModuloPreCadastro();
		moduloPreCadastro.setDataOcorrencia("1212121");

		moduloPreCadastro.validarObrigatoriedadeETamanhoMaximoDeDados();

		Assert.assertEquals(moduloPreCadastro.obterNumeroErros(), 4);
	}
	
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void moduloprecadastro_validacao_com_erro_dados_invalido() {
		ModuloPreCadastro moduloPreCadastro = ContextoModuloPreCadastro
				.fabricarComTodosOsDados();
		moduloPreCadastro.setDiretoriaEnsino(null);
		moduloPreCadastro.setInstituicaoComSalaRecurso(null);
		moduloPreCadastro.setInstituicaoComOutrosAEE(null);
		moduloPreCadastro.setQuantidadeCriancas(ContextoGenerico.obterGrande());
		moduloPreCadastro.setQuantidadeAdultos(ContextoGenerico.obterGrande());

		moduloPreCadastro.validarObrigatoriedadeETamanhoMaximoDeDados();

		Assert.assertEquals(moduloPreCadastro.obterNumeroErros(), 2);
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void moduloprecadastro_validacao_com_erro_data_desligamento_invalida() {
		ModuloPreCadastro moduloUsuario = new ModuloPreCadastro();
		moduloUsuario.setDataOcorrencia("1212121");
		moduloUsuario.setStatus(StatusRelacaoComModulo.DESLIGADO);

		moduloUsuario.validarObrigatoriedadeETamanhoMaximoDeDados();

		Assert.assertEquals(moduloUsuario.obterNumeroErros(), 4);
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void moduloprecadastro_validacao_com_erro_tamanho_maximo_e_selecao_simultanea() {
		ModuloPreCadastro moduloPreCadastro = ContextoModuloPreCadastro
				.fabricarComTodosOsDados();
		moduloPreCadastro.setObs(ContextoGenerico.obterGrande());
		moduloPreCadastro.setNomeOrigemComunidade(ContextoGenerico
				.obterGrande());
		moduloPreCadastro.setCurso(ContextoGenerico.obterGrande());
		moduloPreCadastro.setQuantidadeAdultos("1111111111");
		moduloPreCadastro.setQuantidadeCriancas("1111111111");
		moduloPreCadastro.validarObrigatoriedadeETamanhoMaximoDeDados();

		Assert.assertEquals(moduloPreCadastro.obterNumeroErros(), 7);
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void moduloprecadastro_validacao_sem_erro_obrigatoriedade() {
		ModuloPreCadastro moduloPreCadastro = ContextoModuloPreCadastro
				.fabricarComTodosOsDados();
		moduloPreCadastro.setDiretoriaEnsino(null);
		moduloPreCadastro.setInstituicaoComSalaRecurso(null);
		moduloPreCadastro.setInstituicaoComOutrosAEE(null);

		moduloPreCadastro.validarObrigatoriedadeETamanhoMaximoDeDados();

		Assert.assertTrue(moduloPreCadastro.validado());
	}
}

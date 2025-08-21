package br.laramara.ti.sislaraserver.repositorios;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.TestesIntegracaoAbstrato;
import br.laramara.ti.sislaraserver.dominio.espera.EspecificacaoPesquisaEspera;
import br.laramara.ti.sislaraserver.dominio.espera.Espera;
import br.laramara.ti.sislaraserver.dominio.grupo.DescricaoTipoAtendimento;
import br.laramara.ti.sislaraserver.fabricas.ContextoEspecificacaoPesquisaEspera;
import br.laramara.ti.sislaraserver.utilitarios.Registro;

public class TestesRepositorioEspera extends TestesIntegracaoAbstrato {

	public TestesRepositorioEspera() {
		super("DadosTestesRepositorioEspera.xml");
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void repositorio_espera_pesquisa_com_especificacao() {
		DescricaoTipoAtendimento descricaoTipoAtendimento = new DescricaoTipoAtendimento();
		descricaoTipoAtendimento.setId(new Long(12222));
		EspecificacaoPesquisaEspera especificacao = ContextoEspecificacaoPesquisaEspera
				.fabricarDominioComTodosOsDados();
		especificacao.setDataInicio("01/01/2000");
		especificacao.setDataTermino("31/12/2020");
		especificacao.setInteresse(true);
		especificacao.setLmLigou(true);
		especificacao.setPendencias(true);

		RepositorioEspera repositorio = Registro.obterRepositorioEspera();

		List<Espera> esperaObtidos = repositorio.obterPor(especificacao);

		Assert.assertEquals(esperaObtidos.size(), 2);
	}
	

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void repositorio_espera_com_rg_do_pre_cadastro_na_especificacao() {
		DescricaoTipoAtendimento descricaoTipoAtendimento = new DescricaoTipoAtendimento();
		descricaoTipoAtendimento.setId(new Long(12222));
		EspecificacaoPesquisaEspera especificacao = ContextoEspecificacaoPesquisaEspera
				.fabricarDominioComTodosOsDados();
		especificacao.setDataInicio("01/01/2000");
		especificacao.setDataTermino("31/12/2020");
		especificacao.setInteresse(true);
		especificacao.setLmLigou(true);
		especificacao.setRg("1234");

		RepositorioEspera repositorio = Registro.obterRepositorioEspera();

		List<Espera> esperaObtidos = repositorio.obterPor(especificacao);

		Assert.assertEquals(esperaObtidos.size(), 1);
	}
	
	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void repositorio_espera_com_rg_do_usuario_na_especificacao() {
		DescricaoTipoAtendimento descricaoTipoAtendimento = new DescricaoTipoAtendimento();
		descricaoTipoAtendimento.setId(new Long(12222));
		EspecificacaoPesquisaEspera especificacao = ContextoEspecificacaoPesquisaEspera
				.fabricarDominioComTodosOsDados();
		especificacao.setDataInicio("01/01/2000");
		especificacao.setDataTermino("31/12/2020");
		especificacao.setInteresse(true);
		especificacao.setLmLigou(true);
		especificacao.setRg("4321");

		RepositorioEspera repositorio = Registro.obterRepositorioEspera();

		List<Espera> esperaObtidos = repositorio.obterPor(especificacao);

		Assert.assertEquals(esperaObtidos.size(), 1);
	}
	
	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void repositorio_espera_pesquisa_com_especificacao_data_inicio() {
		EspecificacaoPesquisaEspera especificacao = ContextoEspecificacaoPesquisaEspera
				.fabricarDominioComTodosOsDados();
		especificacao.setDataInicio("01/01/2000");
		especificacao.setDataTermino("");

		RepositorioEspera repositorio = Registro.obterRepositorioEspera();

		List<Espera> esperaObtidos = repositorio.obterPor(especificacao);

		Assert.assertEquals(esperaObtidos.size(), 0);
	}
	
	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void repositorio_espera_pesquisa_com_especificacao_data_termino() {
		EspecificacaoPesquisaEspera especificacao = ContextoEspecificacaoPesquisaEspera
				.fabricarDominioComTodosOsDados();
		especificacao.setDataInicio("");
		especificacao.setDataTermino("01/01/2000");

		RepositorioEspera repositorio = Registro.obterRepositorioEspera();

		List<Espera> esperaObtidos = repositorio.obterPor(especificacao);

		Assert.assertEquals(esperaObtidos.size(), 0);
	}
}

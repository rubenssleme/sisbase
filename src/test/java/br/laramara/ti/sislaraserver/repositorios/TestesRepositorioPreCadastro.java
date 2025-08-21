package br.laramara.ti.sislaraserver.repositorios;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.TestesIntegracaoAbstrato;
import br.laramara.ti.sislaraserver.dominio.precadastro.PreCadastro;
import br.laramara.ti.sislaraserver.fabricas.ContextoPreCadastro;
import br.laramara.ti.sislaraserver.utilitarios.Registro;

public class TestesRepositorioPreCadastro extends TestesIntegracaoAbstrato {

	private RepositorioPreCadastro repositorio;
	
	public TestesRepositorioPreCadastro(){
		super("DadosTestesRepositorioPreCadastro.xml");
		repositorio = Registro.obterRepositorioPreCadastro();
	}
	
	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void repositorio_de_precadastro_salva_e_obtem_pre_cadastro_por_nome_com_sucesso() throws RgDuplicadoException {
		PreCadastro preCadastro = ContextoPreCadastro
				.fabricarPreCadastroComTodosOsDados();
		preCadastro.setId(null);

		repositorio.salvar(preCadastro);

		List<PreCadastro> preCadastros = repositorio
				.pesquisarPorNome("Josep Paulo Meaza");

		Assert.assertEquals(preCadastros.size(), 1,
				"Deveria haver um registro armazenado.");
	}
	
	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB }, expectedExceptions = RgDuplicadoException.class)
	public void repositorio_de_precadastro_nao_inclui_cadastro_com_rg_duplicado()
			throws RgDuplicadoException {
		PreCadastro preCadastro = ContextoPreCadastro
				.fabricarPreCadastroComTodosOsDados();
		preCadastro.setId(null);
		preCadastro.getInformacaoEssencial().adicionarRg("184744");

		repositorio.salvar(preCadastro);
	}
	
	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB }, expectedExceptions = RgDuplicadoException.class)
	public void repositorio_de_precadastro_nao_altera_cadastro_com_rg_duplicado()
			throws RgDuplicadoException {
		PreCadastro preCadastro = repositorio.obterPorId(new Long(22222));
		preCadastro.getInformacaoEssencial().adicionarRg("184744");

		repositorio.salvar(preCadastro);
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void repositorio_de_precadastro_salva_e_obtem_pre_cadastro_por_rg_com_sucesso() throws RgDuplicadoException {
		String rg = "12122";
		PreCadastro preCadastro = ContextoPreCadastro
				.fabricarPreCadastroComTodosOsDados();

		preCadastro.getInformacaoEssencial().adicionarRg(rg);
		preCadastro.setId(null);

		repositorio.salvar(preCadastro);

		List<PreCadastro> preCadastrosObtido = repositorio.pesquisarPorRG(rg);

		Assert.assertEquals(preCadastrosObtido.size(), 1,
				"Deveria haver um registro armazenado.");
	}
	
	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void repositorio_de_precadastro_salva_e_obtem_pre_cadastro_por_cpf_com_sucesso()
			throws RgDuplicadoException {
		String cpf = "71894810287";
		PreCadastro preCadastro = ContextoPreCadastro.fabricarPreCadastroComTodosOsDados();

		preCadastro.getInformacaoEssencial().setCpf(cpf);
		preCadastro.setId(null);

		repositorio.salvar(preCadastro);

		List<PreCadastro> preCadastrosObtido = repositorio.pesquisarPorCpf(cpf);

		Assert.assertEquals(preCadastrosObtido.size(), 1);
	}
}

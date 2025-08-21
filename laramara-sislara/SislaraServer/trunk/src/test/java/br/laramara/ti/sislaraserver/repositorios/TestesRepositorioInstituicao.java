package br.laramara.ti.sislaraserver.repositorios;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.TestesIntegracaoAbstrato;
import br.laramara.ti.sislaraserver.dominio.Contato;
import br.laramara.ti.sislaraserver.dominio.endereco.Endereco;
import br.laramara.ti.sislaraserver.dominio.escola.Escolaridade;
import br.laramara.ti.sislaraserver.dominio.escola.TipoEspecialidade;
import br.laramara.ti.sislaraserver.dominio.instituicao.ClassificacaoInstituicao;
import br.laramara.ti.sislaraserver.dominio.instituicao.Instituicao;
import br.laramara.ti.sislaraserver.dominio.instituicao.TipoInstituicao;
import br.laramara.ti.sislaraserver.fabricas.ContextoContato;
import br.laramara.ti.sislaraserver.fabricas.ContextoEndereco;
import br.laramara.ti.sislaraserver.utilitarios.Registro;

public class TestesRepositorioInstituicao extends TestesIntegracaoAbstrato {

	private RepositorioInstituicao repositorio;

	public TestesRepositorioInstituicao() {
		super("DadosTestesRepositorioInstituicao.xml");
		repositorio = Registro.obterRepositorioInstituicao();
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void repositorio_de_instituicao_salva_e_obtem_instituicao_cadastrado_com_sucesso() {

		Instituicao instituicao = fabricarInstituicaoComTodosOsDados();

		repositorio.salvar(instituicao);

		Instituicao instituicaoObtida = repositorio.obterPorId(instituicao
				.getId());

		Assert.assertEquals(instituicao, instituicaoObtida);
		Assert.assertEquals(instituicao.getTipoInstituicao(), instituicaoObtida.getTipoInstituicao());
		Assert.assertEquals(instituicao.getNome(), instituicaoObtida.getNome());
		Assert.assertEquals(instituicao.getEndereco(), instituicaoObtida.getEndereco());
		Assert.assertEquals(instituicao.getContato().getTelefones().size(), 3);
		Assert.assertEquals(instituicao.getContato().getRamal(),
				instituicaoObtida.getContato().getRamal());
		Assert.assertEquals(instituicao.getContato().getNomeContato(),
				instituicaoObtida.getContato().getNomeContato());
		Assert.assertEquals(instituicao.getNomeCoordenadorResponsavel(),
				instituicaoObtida.getNomeCoordenadorResponsavel());
		Assert.assertEquals(instituicao.getContato().getEmail(),
				instituicaoObtida.getContato().getEmail());
		Assert.assertEquals(instituicao.getClassificacaoInstituicao(),
				instituicaoObtida.getClassificacaoInstituicao());
		Assert.assertEquals(instituicao.getObs(), instituicaoObtida.getObs());
		Assert.assertEquals(instituicao.getTiposEspecialidade().size(),
				instituicaoObtida.getTiposEspecialidade().size());
		Assert.assertEquals(instituicao.getEscolaridades().size(),
				instituicaoObtida.getEscolaridades().size());
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void repositorio_de_instituicao_altera_instituicao_existente_com_sucesso() {
		String novoNomeInstituicao = "Novo nome da instituição";
		Instituicao instituicao = fabricarInstituicaoComTodosOsDados();
		repositorio.salvar(instituicao);

		instituicao.setNome(novoNomeInstituicao);
		repositorio.salvar(instituicao);

		Instituicao instituicaoObtida = repositorio.obterPorId(instituicao
				.getId());

		Assert.assertEquals(instituicaoObtida.getNome(), novoNomeInstituicao);
	}
	
	@Test(groups = {TiposDeTeste.INTEGRACAO_COM_DB})
	public void repositorio_de_instituicao_pesquisa_instituicao_inexistente_por_id(){
		Instituicao instituicaoObtido = repositorio.obterPorId(new Long(338434));
		
		Assert.assertNull(instituicaoObtido);
	}
	
	@Test(groups = {TiposDeTeste.INTEGRACAO_COM_DB})
	public void repositorio_de_instituiao_pesquisa_instituicao_existentes_por_nome(){
		RepositorioInstituicao repositorioInstituicao = Registro.obterRepositorioInstituicao();
		String nomeAPesquisar = "Ministro";
		
		List<Instituicao> instituicoesObtidas = repositorioInstituicao.pesquisarPorNome(nomeAPesquisar);
		
		Assert.assertEquals(instituicoesObtidas.size(), 2);
	}
	
	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void repositorio_de_instituiao_pesquisa_todas() {
		RepositorioInstituicao repositorioInstituicao = Registro
				.obterRepositorioInstituicao();

		List<Instituicao> instituicoesObtidas = repositorioInstituicao
				.obterTodos();

		Assert.assertEquals(instituicoesObtidas.size(), 4);
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void repositorio_de_instituiao_pesquisa_todas_com_srms() {
		RepositorioInstituicao repositorioInstituicao = Registro
				.obterRepositorioInstituicao();

		List<Instituicao> instituicoesObtidas = repositorioInstituicao
				.obterTodosComSRMs();

		Assert.assertEquals(instituicoesObtidas.size(), 2);
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void repositorio_de_instituiao_pesquisa_todas_com_sala_recurso() {
		RepositorioInstituicao repositorioInstituicao = Registro
				.obterRepositorioInstituicao();

		List<Instituicao> instituicoesObtidas = repositorioInstituicao
				.obterTodosComSalaRecurso();

		Assert.assertEquals(instituicoesObtidas.size(), 1);
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void repositorio_de_instituiao_pesquisa_todas_com_outros_aee() {
		RepositorioInstituicao repositorioInstituicao = Registro
				.obterRepositorioInstituicao();

		List<Instituicao> instituicoesObtidas = repositorioInstituicao
				.obterTodosComOutrosAEE();

		Assert.assertEquals(instituicoesObtidas.size(), 1);
	}
	
 private Instituicao fabricarInstituicaoComTodosOsDados() {
		List<TipoEspecialidade> tiposEspecialidade = new ArrayList<>();
		tiposEspecialidade.add(TipoEspecialidade.DF);
		tiposEspecialidade.add(TipoEspecialidade.DI);
		
		List<Escolaridade> escolaridades = new ArrayList<>();
		escolaridades.add(new Escolaridade(new Long(1), "SUPERIOR"));
		
		Instituicao instituicao = new Instituicao();
		instituicao.setTipoInstituicao(TipoInstituicao.CIEJA);
		instituicao.setNome("CEFAI São Mateus");
		instituicao.setNomeCoordenadorResponsavel("Victor Vitalino");
		instituicao.setClassificacao(ClassificacaoInstituicao.ESTADUAL);
		instituicao.setObs("Texto de observação muito longo.");
		instituicao.setTiposEspecialidade(tiposEspecialidade);
		instituicao.setEscolaridades(escolaridades);
		
		Endereco endereco = ContextoEndereco.fabricarEnderecoComTodosOsDados();
		Contato contato = ContextoContato.fabricarContatoComTodosOsDados();
		instituicao.setEndereco(endereco);
		instituicao.setContato(contato);

		return instituicao;
	}
}

package br.laramara.sistelemarketingclient;

import java.util.Arrays;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.sistelemarketingclient.fabricas.ContextoPaginas;
import br.laramara.sistelemarketingclient.paginas.PaginaCampanhaEdicao;
import br.laramara.sistelemarketingclient.paginas.PaginaCampanhaGerenciamento;
import br.laramara.sistelemarketingclient.paginas.PaginaContaAcessoEdicao;
import br.laramara.sistelemarketingclient.paginas.PaginaContaAcessoGerenciamento;
import br.laramara.sistelemarketingclient.paginas.PaginaListagem;
import br.laramara.sistelemarketingclient.paginas.PaginaLogin;
import br.laramara.sistelemarketingclient.paginas.PaginaNivelPermissaoEdicao;
import br.laramara.sistelemarketingclient.paginas.PaginaNivelPermissaoGerenciamento;
import br.laramara.sistelemarketingclient.paginas.PaginaPrincipal;
import br.laramara.sistelemarketingcommons.utilitarios.TiposDeTeste;


public class TestesSistelemarketingclient extends TestesIntegracaoAbstrato {
	
	private static final String MENSAGEM_NAO_AUTORIZADO = "Erro: Não autorizado.";
	private static final String MENSAGEM_SALVO_COM_SUCESSO = "Salvo com sucesso.";

	private PaginaNivelPermissaoGerenciamento navegarParaNivelPermissaoGerenciamento() {
		logarUsandoUsuarioGerente();

		PaginaPrincipal paginaPrincipal = ContextoPaginas.obterPaginaPrincipal();
		paginaPrincipal.acessarMenuNiveisPermissoes();

		return ContextoPaginas.obterPaginaNivelPermissaoGerenciamento();
	}

	private PaginaContaAcessoGerenciamento navegarParaContaAcessoGerenciamento() {
		logarUsandoUsuarioGerente();
		
		PaginaPrincipal paginaPrincipal = ContextoPaginas.obterPaginaPrincipal();
		paginaPrincipal.acessarMenuUsuarios();
		
		return ContextoPaginas.obterPaginaContaAcessoGerenciamento();
	}
	
	private PaginaCampanhaGerenciamento navegarParaCampanhaGerenciamento() {
		logarUsandoUsuarioGerente();
		
		PaginaPrincipal paginaPrincipal = ContextoPaginas.obterPaginaPrincipal();
		paginaPrincipal.acessarMenuCampanhas();
		
		return ContextoPaginas.obterPaginaCampanhaGerenciamento();
	}

	private PaginaNivelPermissaoEdicao navegarParaNivelPermissaoEdicao() {
		logarUsandoUsuarioGerente();
		
		PaginaPrincipal paginaPrincipal = ContextoPaginas.obterPaginaPrincipal();
		paginaPrincipal.acessarMenuNiveisPermissoes();
		
		PaginaNivelPermissaoGerenciamento paginaNivelPermissaoGerenciamento = ContextoPaginas.obterPaginaNivelPermissaoGerenciamento();
		paginaNivelPermissaoGerenciamento.editarGerente();
		
		return ContextoPaginas.obterPaginaNivelPermissaoEdicao();
	}
	
	private PaginaContaAcessoEdicao navegarParaContaAcessoEdicao() {
		logarUsandoUsuarioGerente();
		
		PaginaPrincipal paginaPrincipal = ContextoPaginas.obterPaginaPrincipal();
		paginaPrincipal.acessarMenuUsuarios();
		
		PaginaContaAcessoGerenciamento paginaContaAcessoGerenciamento = ContextoPaginas.obterPaginaContaAcessoGerenciamento();
		paginaContaAcessoGerenciamento.editarCarlos();
		
		return ContextoPaginas.obterPaginaContaAcessoEdicao();
	}
		
	private PaginaCampanhaEdicao navegarParaCampanhaEdicao() {
		logarUsandoUsuarioGerente();
		
		PaginaPrincipal paginaPrincipal = ContextoPaginas.obterPaginaPrincipal();
		paginaPrincipal.acessarMenuCampanhas();
		
		PaginaCampanhaGerenciamento paginaCampanhaGerenciamento = ContextoPaginas.obterPaginaCampanhaGerenciamento();
		paginaCampanhaGerenciamento.editarCampanhaA();
		
		return ContextoPaginas.obterPaginaCampanhaEdicao();
	}

	private void logarUsandoUsuarioGerente() {
		PaginaLogin paginaInicial = ContextoPaginas.obterPaginaLogin();
		paginaInicial.incluirLoginGerente();
		paginaInicial.incluirSenha1234();
		paginaInicial.acessar();
	}

	private void logarUsandoUsuarioOperador() {
		PaginaLogin paginaInicial = ContextoPaginas.obterPaginaLogin();
		paginaInicial.incluirLoginOperador();
		paginaInicial.incluirSenha1234();
		paginaInicial.acessar();
	}
	
	@Test(groups = { TiposDeTeste.CONTA_ACESSO })
	public void efetua_logoff_com_sucesso() {
		logarUsandoUsuarioOperador();
		
		PaginaPrincipal paginaInicial = ContextoPaginas.obterPaginaPrincipal();
		paginaInicial.sairDoSistema();

		Assert.assertTrue(paginaInicial.estaNaPaginaLogin());
	}
	
	@Test(groups = { TiposDeTeste.CONTA_ACESSO })
	public void efetua_login_com_sucesso() {
		PaginaLogin paginaInicial = ContextoPaginas.obterPaginaLogin();
		paginaInicial.incluirLoginGerente();
		paginaInicial.incluirSenha1234();
		paginaInicial.acessar();

		Assert.assertTrue(paginaInicial.estaNaPaginaPrincipal());
	}
	
	@Test(groups = { TiposDeTeste.CONTA_ACESSO })
	public void nao_efetua_login_com_senha_errada() {
		PaginaLogin paginaInicial = ContextoPaginas.obterPaginaLogin();
		paginaInicial.incluirLoginGerente();
		paginaInicial.incluirSenha4321();
		paginaInicial.acessar();

		Assert.assertTrue(paginaInicial.contemMensagem(MENSAGEM_NAO_AUTORIZADO));
	}

	@Test(groups = { TiposDeTeste.CONTA_ACESSO })
	public void nao_efetua_login_sem_dados_obrigatorios() {
		PaginaLogin paginaInicial = ContextoPaginas.obterPaginaLogin();
		paginaInicial.acessar();

		Assert.assertTrue(paginaInicial.contemMensagens(
				"Erro: O campo Login é obrigatório.", 
				"Erro: O campo Senha é obrigatório."));
	}
	
	@Test(groups = { TiposDeTeste.NIVEL_PERMISSAO })
	public void carrega_edicao_nivel_permissao_com_sucesso() {
		PaginaNivelPermissaoEdicao paginaNivelPermissaoEdicao = navegarParaNivelPermissaoEdicao();
			
		Assert.assertTrue(paginaNivelPermissaoEdicao.contemValor("Gerente", PaginaNivelPermissaoEdicao.NIVEL));
		Assert.assertTrue(paginaNivelPermissaoEdicao.contemCheckMarcado("permissoes:0"));
	}
	
	@Test(groups = { TiposDeTeste.NIVEL_PERMISSAO })
	public void edita_nivel_permissao_com_sucesso() {
		PaginaNivelPermissaoEdicao paginaNivelPermissaoEdicao = navegarParaNivelPermissaoEdicao();
		paginaNivelPermissaoEdicao.salva();
			
		Assert.assertTrue(paginaNivelPermissaoEdicao.contemMensagens(MENSAGEM_SALVO_COM_SUCESSO));
	}
	
	@Test(groups = { TiposDeTeste.NIVEL_PERMISSAO })
	public void inclui_nivel_permissao_com_sucesso() {
		PaginaNivelPermissaoGerenciamento paginaNivelPermissaoGerenciamento = navegarParaNivelPermissaoGerenciamento();
		paginaNivelPermissaoGerenciamento.novo();
		
		PaginaNivelPermissaoEdicao paginaNivelPermissaoEdicao = ContextoPaginas.obterPaginaNivelPermissaoEdicaoIncluirNova();
		paginaNivelPermissaoEdicao.incluirValor("Nova permissao", "nivel");
		paginaNivelPermissaoEdicao.salva();
		
		Assert.assertTrue(paginaNivelPermissaoEdicao.contemMensagens(MENSAGEM_SALVO_COM_SUCESSO));
	}

	@Test(groups = { TiposDeTeste.NIVEL_PERMISSAO })
	public void nao_edita_nivel_permissao_sem_dados_obrigatorios() {
		PaginaNivelPermissaoEdicao paginaNivelPermissaoEdicao = navegarParaNivelPermissaoEdicao();
		paginaNivelPermissaoEdicao.apagarDescricao();
		paginaNivelPermissaoEdicao.salva();
			
		Assert.assertTrue(paginaNivelPermissaoEdicao.contemMensagens("Erro: O campo Nível é obrigatório."));
	}

	@Test(groups = { TiposDeTeste.NIVEL_PERMISSAO })
	public void nao_edita_nivel_permissao_com_dados_invalidos() {
		PaginaNivelPermissaoEdicao paginaNivelPermissaoEdicao = navegarParaNivelPermissaoEdicao();
		paginaNivelPermissaoEdicao.incluirDescricaoInvalida();
		paginaNivelPermissaoEdicao.salva();
			
		Assert.assertTrue(paginaNivelPermissaoEdicao.contemMensagens("Erro: Insira um nível contendo até 100 caracteres."));
	}
	
	@Test(groups = { TiposDeTeste.NIVEL_PERMISSAO })
	public void nao_acessao_gerenciamento_nivel_permissao_sem_permissao() {
		logarUsandoUsuarioOperador();
		
		PaginaNivelPermissaoGerenciamento paginaNivelPermissaoGerenciamento = ContextoPaginas.obterPaginaNivelPermissaoGerenciamentoForcado();
			
		Assert.assertTrue(paginaNivelPermissaoGerenciamento.estaNaPaginaSemAutorizacao());
	}
	
	@Test(groups = { TiposDeTeste.NIVEL_PERMISSAO })
	public void nao_acessao_edicao_nivel_permissao_sem_permissao() {
		logarUsandoUsuarioOperador();
		
		PaginaNivelPermissaoEdicao paginaNivelPermissaoEdicao = ContextoPaginas.obterPaginaNivelPermissaoEdicaoForcado();
			
		Assert.assertTrue(paginaNivelPermissaoEdicao.estaNaPaginaSemAutorizacao());
	}
	
	@Test(groups = { TiposDeTeste.CONTA_ACESSO })
	public void carrega_edicao_conta_acesso_com_sucesso() {
		PaginaContaAcessoEdicao paginaContaAcessoEdicao = navegarParaContaAcessoEdicao();
			
		Assert.assertTrue(paginaContaAcessoEdicao.contemValor("jose", PaginaContaAcessoEdicao.LOGIN));
		Assert.assertTrue(paginaContaAcessoEdicao.contemValor("", "senha"));
		Assert.assertTrue(paginaContaAcessoEdicao.contemItemSelecionadoNoCombo("Operador", "nivel"));
		Assert.assertTrue(paginaContaAcessoEdicao.contemItemSelecionadoNoCombo("MANHA", "turno"));
		Assert.assertTrue(paginaContaAcessoEdicao.contemCheckMarcado("ativo"));
	}

	@Test(groups = { TiposDeTeste.CONTA_ACESSO })
	public void edita_conta_acesso_com_sucesso() {
		PaginaContaAcessoEdicao paginaContaAcessoEdicao = navegarParaContaAcessoEdicao();
		paginaContaAcessoEdicao.salva();
			
		Assert.assertTrue(paginaContaAcessoEdicao.contemMensagem(MENSAGEM_SALVO_COM_SUCESSO));
	}
	
	@Test(groups = { TiposDeTeste.CONTA_ACESSO })
	public void inclui_conta_acesso_com_sucesso() {
		PaginaContaAcessoGerenciamento paginaContaAcessoGerenciamento = navegarParaContaAcessoGerenciamento();
		paginaContaAcessoGerenciamento.novo();
		
		PaginaContaAcessoEdicao paginaContaAcessoEdicao = ContextoPaginas.obterPaginaContaAcessoEdicaoIncluirNovo();
		paginaContaAcessoEdicao.incluirValor("Josep Meaza", PaginaContaAcessoEdicao.NOME);
		paginaContaAcessoEdicao.incluirValor("joao", PaginaContaAcessoEdicao.LOGIN);
		paginaContaAcessoEdicao.incluirValor("1234", "senha");
		paginaContaAcessoEdicao.selecionarOpcao("Gerente", "nivel");
		paginaContaAcessoEdicao.salva();
		
		Assert.assertTrue(paginaContaAcessoGerenciamento.contemMensagem(MENSAGEM_SALVO_COM_SUCESSO));
	}
	
	@Test(groups = { TiposDeTeste.CONTA_ACESSO })
	public void nao_edita_conta_acesso_sem_dados_obrigatorios() {
		PaginaContaAcessoEdicao paginaContaAcessoEdicao = navegarParaContaAcessoEdicao();
		paginaContaAcessoEdicao.apagarNome();
		paginaContaAcessoEdicao.apagarLogin();
		paginaContaAcessoEdicao.deselecionarNivel();
		paginaContaAcessoEdicao.salva();
		
		Assert.assertTrue(paginaContaAcessoEdicao.contemMensagens(
				"Erro: O campo Nome é obrigatório.",
				"Erro: O campo Login é obrigatório.", 
				"Erro: O campo Nível é obrigatório."));
	}
	
	@Test(groups = { TiposDeTeste.CONTA_ACESSO })
	public void nao_edita_conta_acesso_com_dados_invalidos() {
		PaginaContaAcessoEdicao paginaContaAcessoEdicao = navegarParaContaAcessoEdicao();
		paginaContaAcessoEdicao.incluirLoginInvalida();
		paginaContaAcessoEdicao.salva();
			
		Assert.assertTrue(paginaContaAcessoEdicao.contemMensagem("Erro: Insira um login contendo até 100 caracteres."));
	}
		
	@Test(groups = { TiposDeTeste.CONTA_ACESSO })
	public void nao_acessao_gerenciamento_conta_acesso_sem_permissao() {
		logarUsandoUsuarioOperador();
		
		PaginaContaAcessoGerenciamento paginaContaAcessoGerenciamento = ContextoPaginas.obterPaginaContaAcessoGerenciamentoForcado();
			
		Assert.assertTrue(paginaContaAcessoGerenciamento.estaNaPaginaSemAutorizacao());
	}
	
	@Test(groups = { TiposDeTeste.CONTA_ACESSO })
	public void nao_acessao_edicao_conta_acesso_sem_permissao() {
		logarUsandoUsuarioOperador();
		
		PaginaContaAcessoEdicao paginaContaAcessoEdicao = ContextoPaginas.obterPaginaContaAcessoEdicaoForcado();
			
		Assert.assertTrue(paginaContaAcessoEdicao.estaNaPaginaSemAutorizacao());
	}
	
	@Test(groups = { TiposDeTeste.CAMPANHA })
	public void carrega_edicao_campanha_com_sucesso() {
		PaginaCampanhaEdicao paginaCampanhaEdicao = navegarParaCampanhaEdicao();
			
		String abaDadosGerais = "dadosGerais";
		
		Assert.assertTrue(paginaCampanhaEdicao.contemValorNaAba("Nome da campanha B", "nome", abaDadosGerais));
		Assert.assertTrue(paginaCampanhaEdicao.contemMensagemNaAba("Texto de descrição B", "descricao", abaDadosGerais));
		Assert.assertTrue(paginaCampanhaEdicao.contemValorNaAba("31/01/2018", "dataInicio", abaDadosGerais));
		Assert.assertTrue(paginaCampanhaEdicao.contemValorNaAba("31/12/2018", "dataTermino", abaDadosGerais));
		Assert.assertTrue(paginaCampanhaEdicao.contemValorNaAba("50.000,10", "metaFinanceira_input", abaDadosGerais));
		Assert.assertTrue(paginaCampanhaEdicao.contemValorNaAba("20000", "metaQuantidadeLigacoes", abaDadosGerais));
		Assert.assertTrue(paginaCampanhaEdicao.contemValorNaAba("Critério Bairro Santa Secilia", "criterios"));
		Assert.assertTrue(paginaCampanhaEdicao.contemValorNaAba("Paulo Augusto R$ 1.000,00 1000", "operadores"));
		Assert.assertTrue(paginaCampanhaEdicao.contemValorNaAba("Jose Augusto R$ 2.000,00 2000", "operadores"));
	}
	
	@Test(groups = { TiposDeTeste.CAMPANHA })
	public void edita_campanha_com_sucesso() {
		PaginaCampanhaEdicao paginaCampanhaEdicao = navegarParaCampanhaEdicao();
		paginaCampanhaEdicao.salva();
			
		Assert.assertTrue(paginaCampanhaEdicao.contemMensagem(MENSAGEM_SALVO_COM_SUCESSO));
	}
	
	@Test(groups = { TiposDeTeste.CAMPANHA })
	public void inclui_campanha_com_sucesso() {
		PaginaCampanhaGerenciamento paginaCampanhaGerenciamento = navegarParaCampanhaGerenciamento();
		paginaCampanhaGerenciamento.novo();
		
		PaginaCampanhaEdicao paginaCampanhaEdicao = ContextoPaginas.obterPaginaCampanhaEdicaoIncluirNova();
		paginaCampanhaEdicao.incluirValor("Nome da campanha C", "nome");
		paginaCampanhaEdicao.incluirValor("01/01/2000", "dataInicio");
		paginaCampanhaEdicao.abrirAba("criterios");
		paginaCampanhaEdicao.incluirValor("Criterio A", "nomeCriterio");
		paginaCampanhaEdicao.selecionarOpcao("São Paulo", "estado");
		paginaCampanhaEdicao.selecionarOpcao("São Paulo", "municipio");
		paginaCampanhaEdicao.selecionarOpcao("Barra Funda", "bairro");
		paginaCampanhaEdicao.incluirValor("10000", "quantidadeADistribuir");
		paginaCampanhaEdicao.acionarBotao("adicionarCriterio");
		paginaCampanhaEdicao.salva();
		
		Assert.assertTrue(paginaCampanhaEdicao.contemMensagem(MENSAGEM_SALVO_COM_SUCESSO));
	}
	
	@Test(groups = { TiposDeTeste.CAMPANHA })
	public void adiciona_e_remove_criterio_de_campanha_com_sucesso() {
		PaginaCampanhaGerenciamento paginaCampanhaGerenciamento = navegarParaCampanhaGerenciamento();
		paginaCampanhaGerenciamento.novo();
		
		PaginaCampanhaEdicao paginaCampanhaEdicao = ContextoPaginas.obterPaginaCampanhaEdicaoIncluirNova();
		paginaCampanhaEdicao.abrirAba("criterios");
		paginaCampanhaEdicao.incluirValor("Criterio A", "nomeCriterio");
		paginaCampanhaEdicao.selecionarOpcao("São Paulo", "estado");
		paginaCampanhaEdicao.selecionarOpcao("São Paulo", "municipio");
		paginaCampanhaEdicao.selecionarOpcao("Barra Funda", "bairro");
		paginaCampanhaEdicao.incluirValor("10000", "quantidadeADistribuir");
		paginaCampanhaEdicao.acionarBotao("adicionarCriterio");
		
		paginaCampanhaEdicao.incluirValor("Criterio B", "nomeCriterio");
		paginaCampanhaEdicao.selecionarOpcao("São Paulo", "estado");
		paginaCampanhaEdicao.selecionarOpcao("São Paulo", "municipio");
		paginaCampanhaEdicao.selecionarOpcao("Higienópolis", "bairro");
		paginaCampanhaEdicao.incluirValor("10000", "quantidadeADistribuir");
		paginaCampanhaEdicao.acionarBotao("adicionarCriterio");
		paginaCampanhaEdicao.acionarBotao("tabelaCriterio:0:remover");
		
		Assert.assertFalse(paginaCampanhaEdicao
				.contemMensagem("Criterio A(Distribuir 10000 de São Paulo, São Paulo, Barra Funda)", "tabelaCriterio"));
		Assert.assertTrue(paginaCampanhaEdicao.contemMensagem(
				"Criterio B(Distribuir 10000 de São Paulo, São Paulo, Higienópolis)", "tabelaCriterio"));
	}
	
	@Test(groups = { TiposDeTeste.CAMPANHA })
	public void nao_adiciona_criterio_de_campanha_sem_dados_obrigatorios() {
		PaginaCampanhaGerenciamento paginaCampanhaGerenciamento = navegarParaCampanhaGerenciamento();
		paginaCampanhaGerenciamento.novo();
		
		PaginaCampanhaEdicao paginaCampanhaEdicao = ContextoPaginas.obterPaginaCampanhaEdicaoIncluirNova();
		paginaCampanhaEdicao.abrirAba("criterios");
		paginaCampanhaEdicao.acionarBotao("adicionarCriterio");
		
		Assert.assertTrue(paginaCampanhaEdicao.contemMensagens(
				"Erro: O campo Nome do critério é obrigatório.",
				"Erro: O campo Estado é obrigatório.", 
				"Erro: O campo Município é obrigatório.",
				"Erro: O campo Bairro é obrigatório.", 
				"Erro: O campo Quantidade a distribuir é obrigatório."));
	}

	@Test(groups = { TiposDeTeste.CAMPANHA })
	public void nao_edita_campanha_sem_dados_obrigatorios() {
		PaginaCampanhaEdicao paginaCampanhaEdicao = navegarParaCampanhaEdicao();
		paginaCampanhaEdicao.apagarNome();
		paginaCampanhaEdicao.apagarDataInicio();
		paginaCampanhaEdicao.salva();
		
		Assert.assertTrue(paginaCampanhaEdicao.contemMensagens("Erro: O campo Nome é obrigatório.",
				"Erro: O campo Data de início é obrigatório."));
	}
	
	@Test(groups = { TiposDeTeste.CAMPANHA })
	public void nao_edita_campanha_com_dados_invalidos() {
		PaginaCampanhaEdicao paginaCampanhaEdicao = navegarParaCampanhaEdicao();
		paginaCampanhaEdicao.incluirNomeInvalida();
		paginaCampanhaEdicao.salva();
			
		Assert.assertTrue(paginaCampanhaEdicao.contemMensagens("Erro: Insira um nome contendo até 100 caracteres."));
	}
	
	@Test(groups = { TiposDeTeste.CAMPANHA})
	public void nao_acessao_edicao_campanha_sem_permissao() {
		logarUsandoUsuarioOperador();
		
		PaginaCampanhaEdicao paginaCampanhaEdicao = ContextoPaginas.obterPaginaCampanhaEdicaoForcado();
			
		Assert.assertTrue(paginaCampanhaEdicao.estaNaPaginaSemAutorizacao());
	}
	
	@Test(groups = { TiposDeTeste.CAMPANHA })
	public void nao_acessao_gerenciamento_campanha_sem_permissao() {
		logarUsandoUsuarioOperador();
		
		PaginaCampanhaGerenciamento paginaCampanhaGerenciamento = ContextoPaginas.obterPaginaCampanhaGerenciamentoForcado();
			
		Assert.assertTrue(paginaCampanhaGerenciamento.estaNaPaginaSemAutorizacao());
	}
	
	@Test(groups = { TiposDeTeste.CAMPANHA })
	public void adiciona_remove_e_altera_alocacao_de_operador_com_sucesso() {
		PaginaCampanhaGerenciamento paginaCampanhaGerenciamento = navegarParaCampanhaGerenciamento();
		paginaCampanhaGerenciamento.novo();
		
		PaginaCampanhaEdicao paginaCampanhaEdicao = ContextoPaginas.obterPaginaCampanhaEdicaoIncluirNova();
		paginaCampanhaEdicao.abrirAba("operadores");
		paginaCampanhaEdicao.selecionarOpcao("Jose Augusto", "operador");
		paginaCampanhaEdicao.incluirValor("10000", "metaFinanceiraOperador_input");
		paginaCampanhaEdicao.incluirValor("10000", "metaQuantidadeLigacoesOperador");
		paginaCampanhaEdicao.acionarBotao("salvarAlocacaoOperador");
		paginaCampanhaEdicao.selecionarOpcao("Paulo Augusto", "operador");
		paginaCampanhaEdicao.incluirValor("20000", "metaFinanceiraOperador_input");
		paginaCampanhaEdicao.incluirValor("20000", "metaQuantidadeLigacoesOperador");
		paginaCampanhaEdicao.acionarBotao("salvarAlocacaoOperador");
		paginaCampanhaEdicao.acionarBotao("tabelaAlocacoesOperadores:0:remover");
		paginaCampanhaEdicao.acionarBotao("tabelaAlocacoesOperadores:0:alterar");
		paginaCampanhaEdicao.acionarBotao("cancelarAlocacaoOperador");
		paginaCampanhaEdicao.acionarBotao("tabelaAlocacoesOperadores:0:alterar");
		paginaCampanhaEdicao.alterarValor("30000", "metaFinanceiraOperador_input");
		paginaCampanhaEdicao.alterarValor("30000", "metaQuantidadeLigacoesOperador");
		paginaCampanhaEdicao.acionarBotao("salvarAlocacaoOperador");

		Assert.assertFalse(paginaCampanhaEdicao
				.contemMensagem("Jose Augusto R$ 10.000,00 10000", "tabelaAlocacoesOperadores"));
		Assert.assertTrue(paginaCampanhaEdicao
				.contemMensagem("Paulo Augusto R$ 30.000,00 30000", "tabelaAlocacoesOperadores"));
	}
	
	@Test(groups = { TiposDeTeste.CAMPANHA })
	public void nao_adiciona_alocacao_de_operador_sem_dados_obrigatorios() {
		PaginaCampanhaGerenciamento paginaCampanhaGerenciamento = navegarParaCampanhaGerenciamento();
		paginaCampanhaGerenciamento.novo();
		
		PaginaCampanhaEdicao paginaCampanhaEdicao = ContextoPaginas.obterPaginaCampanhaEdicaoIncluirNova();
		paginaCampanhaEdicao.abrirAba("operadores");
		paginaCampanhaEdicao.acionarBotao("salvarAlocacaoOperador");
		
		Assert.assertTrue(paginaCampanhaEdicao.contemMensagens(
				"Erro: O campo Operador é obrigatório."));
	}
	
	@Test(groups = { TiposDeTeste.LISTAGEM })
	public void nao_acessao_listagem_sem_permissao() {
		logarUsandoUsuarioGerente();
		
		PaginaListagem paginaListagem = ContextoPaginas.obterPaginaListagemForcado();
			
		Assert.assertTrue(paginaListagem.estaNaPaginaSemAutorizacao());
	}
	
	@Test(groups = { TiposDeTeste.LISTAGEM })
	public void carrega_listagem_com_sucesso() {
		logarUsandoUsuarioOperador();
		
		PaginaListagem paginaListagem = ContextoPaginas.obterPaginaListagemForcado();
		paginaListagem.acionarBotao("obterContato");
	
		List<String> possiveisContatos = Arrays.asList(
				"Nome" + "Paulo Augusto" + "CPF" + "71894810287" + "CEP" + "01151000" + "Endereço"
						+ "Rua Brigadeiro Galvão, 573, AP 63" + "BairroBarra Funda" + "Município/Estado"
						+ "São Paulo/São Paulo" + "Email" + "pbandeira@yahoo.com.br" + "Telefones" + "Telefone"
						+ "(11)111111111" + "(11)986866204"
				, 
				"Nome" + "Joao Augusto" + "CPF" + "11223344556" + "CEP" + "01151000" + "Endereço"
						+ "Rua Brigadeiro Galvão, 834, AP 13" + "Bairro" + "Barra Funda" + "Município/Estado"
						+ "São Paulo/São Paulo" + "Email" + "joao@yahoo.com.br" + "Telefones" + "Telefone"
						+ "(11)957878870" + "(11)981847597"
				);
		
		Assert.assertTrue(paginaListagem.contemMensagem(possiveisContatos, "gridContato"));
	}
}
package br.laramara.ti.sislaraweb;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaraweb.controladores.Controlador;
import br.laramara.ti.sislaraweb.fabricas.ContextoPaginas;
import br.laramara.ti.sislaraweb.paginas.PaginaCadastroUsuarioExterno;
import br.laramara.ti.sislaraweb.paginas.PaginaDetalheCurso;
import br.laramara.ti.sislaraweb.paginas.PaginaInicial;
import br.laramara.ti.sislaraweb.paginas.PaginaInscricao;
import br.laramara.ti.sislaraweb.paginas.PaginaLogin;
import br.laramara.ti.sislaraweb.paginas.PaginaPerfil;
import br.laramara.ti.sislaraweb.paginas.PaginaSolicitacaoRecuperacaoSenha;

public class TestesSislaraweb extends TestesIntegracaoAbstrato {

	private static final String EMAIL_INVALIDO = "carloskafka7@g";
	private static final String EMAIL_PADRAO_COM_PERFIL_PREENCHIDO = "carloskafka7@gmail.com";
	private static final String EMAIL_SECUNDARIO_COM_PERFIL_PREENCHIDO = "c@gmail.com";
	
	private static final String EMAIL_SECUNDARIO_SEM_PERFIL_PREENCHIDO = "a@gmail.com";
	private static final String EMAIL_TERCIARIO_SEM_PERFIL_PREENCHIDO = "b@gmail.com";

	public TestesSislaraweb() {
		super(Controlador.PAGINA_PRINCIPAL);
	}

	@Test
	public void autenticar_usuario_externo_com_sucesso() {
		PaginaInicial paginaInicial = ContextoPaginas.obterPaginaInicial(EMAIL_PADRAO_COM_PERFIL_PREENCHIDO);

		Assert.assertTrue(paginaInicial.paginaValida());
	}

	@Test
	public void autenticar_usuario_externo_com_email_invalido_sem_sucesso() {
		PaginaLogin paginaLogin = ContextoPaginas.obterPaginaLogin(EMAIL_INVALIDO, ContextoPaginas.SENHA_PADRAO);

		Assert.assertTrue(paginaLogin.emailInvalido());
	}

	@Test
	public void autenticar_usuario_externo_com_senha_invalida_sem_sucesso() {
		PaginaLogin paginaLogin = ContextoPaginas.obterPaginaLogin(EMAIL_PADRAO_COM_PERFIL_PREENCHIDO,
				ContextoPaginas.SENHA_INVALIDA);

		Assert.assertTrue(paginaLogin.loginInvalido());
	}

	@Test
	public void solicita_recuperacao_senha_com_sucesso() {
		PaginaSolicitacaoRecuperacaoSenha paginaSolicitacaoRecuperacaoSenha = ContextoPaginas
				.obterPaginaSolicitacaoRecuperacaoSenha();

		paginaSolicitacaoRecuperacaoSenha.preencherEmail(EMAIL_PADRAO_COM_PERFIL_PREENCHIDO);

		paginaSolicitacaoRecuperacaoSenha.solicitarRecuperacaoSenha();

		Assert.assertTrue(paginaSolicitacaoRecuperacaoSenha.paginaValida());
		Assert.assertTrue(paginaSolicitacaoRecuperacaoSenha.sucesso());
	}

	@Test
	public void solicita_recuperacao_senha_sem_sucesso() {
		PaginaSolicitacaoRecuperacaoSenha paginaSolicitacaoRecuperacaoSenha = ContextoPaginas
				.obterPaginaSolicitacaoRecuperacaoSenha();

		paginaSolicitacaoRecuperacaoSenha.preencherEmail(EMAIL_INVALIDO);

		paginaSolicitacaoRecuperacaoSenha.solicitarRecuperacaoSenha();

		Assert.assertTrue(paginaSolicitacaoRecuperacaoSenha.invalido());
	}

	@Test
	public void cadastra_usuario_externo_com_sucesso() {
		String nomeCompleto = "Carlos Henrique da Silva Kafka";
		String telefoneCelular = "(11)981847597";
		String email = "carlos_kafka@hotmail.com";
		boolean autorizoRecebimentoInformativo = true;

		PaginaCadastroUsuarioExterno paginaCadastroUsuarioExterno = ContextoPaginas
				.obterPaginaCadastroUsuarioExterno();

		paginaCadastroUsuarioExterno.preencherDadosParaCadastro(nomeCompleto, telefoneCelular, email,
				autorizoRecebimentoInformativo);

		paginaCadastroUsuarioExterno.cadastrar();

		Assert.assertTrue(paginaCadastroUsuarioExterno.paginaValida());
		Assert.assertTrue(paginaCadastroUsuarioExterno.sucesso());
	}

	@Test
	public void cadastra_usuario_externo_sem_sucesso() {
		String nomeCompleto = "";
		String telefoneCelular = "";
		String email = "";
		boolean autorizoRecebimentoInformativo = true;

		PaginaCadastroUsuarioExterno paginaCadastroUsuarioExterno = ContextoPaginas
				.obterPaginaCadastroUsuarioExterno();

		paginaCadastroUsuarioExterno.preencherDadosParaCadastro(nomeCompleto, telefoneCelular, email,
				autorizoRecebimentoInformativo);

		paginaCadastroUsuarioExterno.cadastrar();

		Assert.assertTrue(paginaCadastroUsuarioExterno.invalido());
	}

	@Test
	public void obtem_detalhe_curso_por_id_grupo_com_sucesso() {
		PaginaDetalheCurso paginaDetalheCurso = ContextoPaginas.obterPaginaDetalheCurso();

		paginaDetalheCurso.clicarEmSaibaMaisNoCursoEnsinoEAplicacaoSistemaBrailleemNivelBasico();

		Assert.assertTrue(paginaDetalheCurso.paginaValida());
	}

	@Test
	public void obtem_detalhe_curso_por_id_grupo_sem_sucesso() {
		PaginaDetalheCurso paginaDetalheCurso = ContextoPaginas.obterPaginaDetalheCurso();

		paginaDetalheCurso.clicarEmInscrever();

		Assert.assertTrue(paginaDetalheCurso.invalido());
	}

	@Test
	public void obtem_pagina_perfil_com_sucesso() {
		PaginaPerfil paginaPerfil = ContextoPaginas.obterPaginaPerfil(EMAIL_SECUNDARIO_SEM_PERFIL_PREENCHIDO);

		Assert.assertTrue(paginaPerfil.paginaValida());
	}

	@Test
	public void obtem_pagina_perfil_sem_sucesso() {
		PaginaPerfil paginaPerfil = ContextoPaginas.obterPaginaPerfil(EMAIL_PADRAO_COM_PERFIL_PREENCHIDO);

		Assert.assertTrue(paginaPerfil.estaNaPaginaInscricoes());
	}

	@Test
	public void obtem_pagina_inscricao_com_dados_sobre_deficiencia_habilitado_com_sucesso() {
		PaginaInscricao paginaInscricao = ContextoPaginas.obterPaginaInscricaoDoCursoEnsinoEAplicacaoSistemaBrailleEmNivelBasico(EMAIL_SECUNDARIO_COM_PERFIL_PREENCHIDO);

		Assert.assertTrue(paginaInscricao.paginaValida());
		Assert.assertTrue(paginaInscricao.possuiDadosSobreDeficiencia());
	}
	
	@Test
	public void obtem_pagina_inscricao_com_dados_sobre_deficiencia_desabilitado_com_sucesso() {
		PaginaInscricao paginaInscricao = ContextoPaginas.obterPaginaInscricaoDoCursoEnsinoEAplicacaoSistemaBrailleEmNivelBasico(EMAIL_PADRAO_COM_PERFIL_PREENCHIDO);

		Assert.assertTrue(paginaInscricao.paginaValida());
		Assert.assertFalse(paginaInscricao.possuiDadosSobreDeficiencia());
	}

	@Test
	public void edita_usuario_externo_com_sucesso() {
		String nomeCompleto = "Carlos Henrique da Silva Kafka";
		String dataNascimento = "27/09/1993";
		String cpf = "11111111111";
		String rgRne = "333474685";
		String telefoneCelular = "(11)981847597";
		String outroTelefone = "(11)911112233";
		boolean autorizoRecebimentoInformativo = true;
		String cep = "05205220";
		String endereco = "Rua Barão Geraldo de Rezende";
		String numero = "525";
		String complemento = "AP22";
		String bairro = "Jardim Russo";
		String uf = "SP";
		String municipio = "São Paulo";

		PaginaPerfil paginaPerfil = ContextoPaginas.obterPaginaPerfil(EMAIL_TERCIARIO_SEM_PERFIL_PREENCHIDO);

		paginaPerfil.preencherInformacoesPessoais(nomeCompleto, dataNascimento, cpf, rgRne);
		paginaPerfil.preencherDadosParaContato(telefoneCelular, outroTelefone, autorizoRecebimentoInformativo);
		paginaPerfil.preencherEnderecoResidencial(cep, endereco, numero, complemento, bairro, uf, municipio);

		paginaPerfil.salvar();

		Assert.assertTrue(paginaPerfil.estaNaPaginaInscricoes());
	}

	@Test
	public void edita_usuario_externo_sem_sucesso() {
		String nomeCompleto = "";
		String dataNascimento = "";
		String cpf = "";
		String rgRne = "";
		String telefoneCelular = "";
		String outroTelefone = "";
		boolean autorizoRecebimentoInformativo = false;
		String cep = "";
		String endereco = "";
		String numero = "";
		String complemento = "";
		String bairro = "";
		String uf = "";
		String municipio = "";

		PaginaPerfil paginaPerfil = ContextoPaginas.obterPaginaPerfil(EMAIL_SECUNDARIO_SEM_PERFIL_PREENCHIDO);

		paginaPerfil.preencherInformacoesPessoais(nomeCompleto, dataNascimento, cpf, rgRne);
		paginaPerfil.preencherDadosParaContato(telefoneCelular, outroTelefone, autorizoRecebimentoInformativo);
		paginaPerfil.preencherEnderecoResidencial(cep, endereco, numero, complemento, bairro, uf, municipio);

		paginaPerfil.salvar();

		Assert.assertTrue(paginaPerfil.mensagemInvalida());
	}

	@Test
	public void realiza_inscricao_com_opcao_usar_outro_endereco_com_sucesso() {
		String cep = "05205220";
		String endereco = "Rua Barão Geraldo de Rezende";
		String numero = "525";
		String complemento = "AP22";
		String bairro = "Jardim Russo";
		String uf = "SP";
		String municipio = "São Paulo";
		String nomeParaCracha = "Carlos Kafka";
		String observacoes = "Obs";
		String areaFormacao = "Administração";
		String localTrabalho = "Laramara";
		String cargoOuFuncao = "Analista de Sistemas";
		boolean usuarioExternoPossuiCadeiraDeRodas = true;
		boolean usuarioExternoPossuiCaoGuia = true;
		boolean valorTotalAlmocoIncluso = false;
		
		PaginaInscricao paginaInscricao = ContextoPaginas.obterPaginaInscricaoDoCursoEnsinoEAplicacaoSistemaBrailleEmNivelBasico(EMAIL_SECUNDARIO_COM_PERFIL_PREENCHIDO);

		paginaInscricao.clicarEmUsarOutroEndereco();
		paginaInscricao.preencherEndereco(cep, endereco, numero, complemento, bairro, uf, municipio);
		paginaInscricao.preencherNomeParaCracha(nomeParaCracha);
		paginaInscricao.preencherObservacoes(observacoes);
		paginaInscricao.preencherAreaFormacao(areaFormacao);
		paginaInscricao.preencherLocalTrabalho(localTrabalho);
		paginaInscricao.preencherCargoOuFuncao(cargoOuFuncao);
		paginaInscricao.marcarUsuarioExternoPossuiCadeiraDeRodas(usuarioExternoPossuiCadeiraDeRodas);
		paginaInscricao.marcarUsuarioExternoPossuiCaoGuia(usuarioExternoPossuiCaoGuia);
		paginaInscricao.marcarValorTotalAlmocoIncluso(valorTotalAlmocoIncluso);
		paginaInscricao.confirmar();
		
		Assert.assertTrue(paginaInscricao.sucesso());
	}
	
	@Test
	public void realiza_inscricao_com_opcao_inclusao_valortotalalmoco_com_sucesso() {
		String cep = "05205220";
		String endereco = "Rua Barão Geraldo de Rezende";
		String numero = "525";
		String complemento = "AP22";
		String bairro = "Jardim Russo";
		String uf = "SP";
		String municipio = "São Paulo";
		String nomeParaCracha = "Carlos Kafka";
		String observacoes = "Obs";
		String areaFormacao = "Administração";
		String localTrabalho = "Laramara";
		String cargoOuFuncao = "Analista de Sistemas";
		boolean usuarioExternoPossuiCadeiraDeRodas = true;
		boolean usuarioExternoPossuiCaoGuia = true;
		boolean valorTotalAlmocoIncluso = true;
		
		PaginaInscricao paginaInscricao = ContextoPaginas.obterPaginaInscricaoDoCursoEnsinoEAplicacaoSistemaBrailleEmNivelBasico(EMAIL_SECUNDARIO_COM_PERFIL_PREENCHIDO);

		paginaInscricao.clicarEmUsarOutroEndereco();
		paginaInscricao.preencherEndereco(cep, endereco, numero, complemento, bairro, uf, municipio);
		paginaInscricao.preencherNomeParaCracha(nomeParaCracha);
		paginaInscricao.preencherObservacoes(observacoes);
		paginaInscricao.preencherAreaFormacao(areaFormacao);
		paginaInscricao.preencherLocalTrabalho(localTrabalho);
		paginaInscricao.preencherCargoOuFuncao(cargoOuFuncao);
		paginaInscricao.marcarUsuarioExternoPossuiCadeiraDeRodas(usuarioExternoPossuiCadeiraDeRodas);
		paginaInscricao.marcarUsuarioExternoPossuiCaoGuia(usuarioExternoPossuiCaoGuia);
		paginaInscricao.marcarValorTotalAlmocoIncluso(valorTotalAlmocoIncluso);
		paginaInscricao.confirmar();
		
		Assert.assertTrue(paginaInscricao.sucesso());
	}
	
	@Test
	public void realiza_inscricao_sem_opcao_inclusao_valortotalalmoco_com_sucesso() {
		String cep = "05205220";
		String endereco = "Rua Barão Geraldo de Rezende";
		String numero = "525";
		String complemento = "AP22";
		String bairro = "Jardim Russo";
		String uf = "SP";
		String municipio = "São Paulo";
		String nomeParaCracha = "Carlos Kafka";
		String observacoes = "Obs";
		String areaFormacao = "Administração";
		String localTrabalho = "Laramara";
		String cargoOuFuncao = "Analista de Sistemas";
		boolean usuarioExternoPossuiCadeiraDeRodas = true;
		boolean usuarioExternoPossuiCaoGuia = true;
		
		PaginaInscricao paginaInscricao = ContextoPaginas.obterPaginaInscricaoDoCursoAvaliacaoFuncionalEVisao(EMAIL_SECUNDARIO_COM_PERFIL_PREENCHIDO);

		paginaInscricao.clicarEmUsarOutroEndereco();
		paginaInscricao.preencherEndereco(cep, endereco, numero, complemento, bairro, uf, municipio);
		paginaInscricao.preencherNomeParaCracha(nomeParaCracha);
		paginaInscricao.preencherObservacoes(observacoes);
		paginaInscricao.preencherAreaFormacao(areaFormacao);
		paginaInscricao.preencherLocalTrabalho(localTrabalho);
		paginaInscricao.preencherCargoOuFuncao(cargoOuFuncao);
		paginaInscricao.marcarUsuarioExternoPossuiCadeiraDeRodas(usuarioExternoPossuiCadeiraDeRodas);
		paginaInscricao.marcarUsuarioExternoPossuiCaoGuia(usuarioExternoPossuiCaoGuia);
		paginaInscricao.confirmar();
		
		Assert.assertTrue(paginaInscricao.sucesso());
		Assert.assertFalse(paginaInscricao.valorTotalAlmocoVisivel());
	}
	
	@Test
	public void realiza_inscricao_com_opcao_usar_endereco_ja_existente_com_sucesso() {
		String nomeParaCracha = "Carlos Kafka";
		String observacoes = "Obs";
		String areaFormacao = "Administração";
		String localTrabalho = "Laramara";
		String cargoOuFuncao = "Analista de Sistemas";
		boolean usuarioExternoPossuiCadeiraDeRodas = true;
		boolean usuarioExternoPossuiCaoGuia = true;
		boolean valorTotalAlmocoIncluso = true;
		
		PaginaInscricao paginaInscricao = ContextoPaginas.obterPaginaInscricaoDoCursoEnsinoEAplicacaoSistemaBrailleEmNivelBasico(EMAIL_SECUNDARIO_COM_PERFIL_PREENCHIDO);

		paginaInscricao.clicarEmUsarEnderecoJaExistente();
		paginaInscricao.preencherNomeParaCracha(nomeParaCracha);
		paginaInscricao.preencherObservacoes(observacoes);
		paginaInscricao.preencherAreaFormacao(areaFormacao);
		paginaInscricao.preencherLocalTrabalho(localTrabalho);
		paginaInscricao.preencherCargoOuFuncao(cargoOuFuncao);
		paginaInscricao.marcarUsuarioExternoPossuiCadeiraDeRodas(usuarioExternoPossuiCadeiraDeRodas);
		paginaInscricao.marcarUsuarioExternoPossuiCaoGuia(usuarioExternoPossuiCaoGuia);
		paginaInscricao.marcarValorTotalAlmocoIncluso(valorTotalAlmocoIncluso);
		paginaInscricao.confirmar();
		
		Assert.assertTrue(paginaInscricao.sucesso());
	}
	
	@Test
	public void realiza_inscricao_sem_sucesso() {
		String cep = "";
		String endereco = "";
		String numero = "";
		String complemento = "";
		String bairro = "";
		String uf = "";
		String municipio = "";
		String nomeParaCracha = "";
		String observacoes = "";
		String areaFormacao = "";
		String localTrabalho = "";
		String cargoOuFuncao = "";
		boolean usuarioExternoPossuiCadeiraDeRodas = false;
		boolean usuarioExternoPossuiCaoGuia = false;
		boolean valorTotalAlmocoIncluso = false;
		
		PaginaInscricao paginaInscricao = ContextoPaginas.obterPaginaInscricaoDoCursoEnsinoEAplicacaoSistemaBrailleEmNivelBasico(EMAIL_SECUNDARIO_COM_PERFIL_PREENCHIDO);

		paginaInscricao.clicarEmUsarOutroEndereco();
		paginaInscricao.preencherEndereco(cep, endereco, numero, complemento, bairro, uf, municipio);
		paginaInscricao.preencherNomeParaCracha(nomeParaCracha);
		paginaInscricao.preencherObservacoes(observacoes);
		paginaInscricao.preencherAreaFormacao(areaFormacao);
		paginaInscricao.preencherLocalTrabalho(localTrabalho);
		paginaInscricao.preencherCargoOuFuncao(cargoOuFuncao);
		paginaInscricao.marcarUsuarioExternoPossuiCadeiraDeRodas(usuarioExternoPossuiCadeiraDeRodas);
		paginaInscricao.marcarUsuarioExternoPossuiCaoGuia(usuarioExternoPossuiCaoGuia);
		paginaInscricao.marcarValorTotalAlmocoIncluso(valorTotalAlmocoIncluso);
		paginaInscricao.confirmar();
		
		Assert.assertTrue(paginaInscricao.mensagemInvalida());
	}
}
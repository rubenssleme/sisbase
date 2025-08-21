package br.laramara.ti.sismovimentacaoserver.servicos.rmi;

import java.rmi.RemoteException;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sismovimentacaocommons.dtos.ChavePesquisaDTO;
import br.laramara.ti.sismovimentacaocommons.dtos.ResultadoAlteracaoSenhaDTO;
import br.laramara.ti.sismovimentacaocommons.dtos.movimentacao.EspecificacaoPesquisaMovimentacaoDTO;
import br.laramara.ti.sismovimentacaocommons.dtos.movimentacao.MovimentacaoDTO;
import br.laramara.ti.sismovimentacaocommons.dtos.movimentacao.ResultadoEdicaoMovimentacaoDTO;
import br.laramara.ti.sismovimentacaocommons.dtos.movimentacao.ResultadoListagemAbdbDTO;
import br.laramara.ti.sismovimentacaocommons.dtos.movimentacao.ResultadoListagemFibraDTO;
import br.laramara.ti.sismovimentacaocommons.dtos.movimentacao.ResultadoListagemMovimentacaoDTO;
import br.laramara.ti.sismovimentacaocommons.dtos.movimentacao.ResultadoListagemPapelDTO;
import br.laramara.ti.sismovimentacaocommons.dtos.movimentacao.ResultadoListagemSimNaoDTO;
import br.laramara.ti.sismovimentacaocommons.dtos.movimentacao.ResultadoListagemStatusDTO;
import br.laramara.ti.sismovimentacaocommons.dtos.movimentacao.ResultadoListagemTextoDTO;
import br.laramara.ti.sismovimentacaocommons.dtos.movimentacao.StatusDTO;
import br.laramara.ti.sismovimentacaocommons.dtos.seguranca.ContaAcessoDTO;
import br.laramara.ti.sismovimentacaocommons.dtos.seguranca.CredencialDTO;
import br.laramara.ti.sismovimentacaocommons.dtos.seguranca.EspecificacaoPesquisaContaAcessoDTO;
import br.laramara.ti.sismovimentacaocommons.dtos.seguranca.EspecificacaoPesquisaPerfilDTO;
import br.laramara.ti.sismovimentacaocommons.dtos.seguranca.PerfilDTO;
import br.laramara.ti.sismovimentacaocommons.dtos.seguranca.ResultadoAlteracaoExtensaoRelatorioDTO;
import br.laramara.ti.sismovimentacaocommons.dtos.seguranca.ResultadoAutenticacaoDTO;
import br.laramara.ti.sismovimentacaocommons.dtos.seguranca.ResultadoEdicaoContaAcessoDTO;
import br.laramara.ti.sismovimentacaocommons.dtos.seguranca.ResultadoEdicaoPerfilDTO;
import br.laramara.ti.sismovimentacaocommons.dtos.seguranca.ResultadoListagemContaAcessoDTO;
import br.laramara.ti.sismovimentacaocommons.dtos.seguranca.ResultadoListagemPerfilDTO;
import br.laramara.ti.sismovimentacaocommons.dtos.seguranca.ResultadoListagemPermissaoDTO;
import br.laramara.ti.sismovimentacaocommons.dtos.seguranca.ResultadoOperacaoFiltroGrupoDTO;
import br.laramara.ti.sismovimentacaocommons.servicos.ServicoSisMovimentacaoServer;
import br.laramara.ti.sismovimentacaocommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sismovimentacaoserver.TestesIntegracaoAbstrato;
import br.laramara.ti.sismovimentacaoserver.utilitarios.Registro;

public class TestesServicoSisMovimentacaoServerRmi extends TestesIntegracaoAbstrato {

	private ServicoSisMovimentacaoServer servicoSisMovimentacaoServerRmi;

	public TestesServicoSisMovimentacaoServerRmi() {
		super("DadosTestesServicoSisMovimentacaoServerRmi.xml");
		servicoSisMovimentacaoServerRmi = Registro.obterServicoSisMovimentacaoServer();
	}

	private CredencialDTO construirCredencialDtoValida() {
		return new CredencialDTO("pabsantos", "1234");
	}
	
	private CredencialDTO construirCredencialAlternativaDtoValida() {
		return new CredencialDTO("rleme", "a", "a");
	}
	
	private CredencialDTO construirCredencialAlternativaDtoInvalida() {
		return new CredencialDTO("paulo", "a", "c");
	}
	
	private CredencialDTO construirCredencialDtoValidaSemSenha() {
		return new CredencialDTO("pabsantos","","");
	}
	
	private CredencialDTO construirCredencialDtoSemPermissao(){
		return new CredencialDTO("teste", "teste");
	}
		
	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_nao_efetua_login_bloqueado() throws RemoteException {
		CredencialDTO credencialDto = new CredencialDTO("marcos", "marcos");
		ResultadoAutenticacaoDTO resultadoDto = servicoSisMovimentacaoServerRmi
				.autenticarLogin(credencialDto);

		Assert.assertFalse(resultadoDto.sucesso());
	}
	
	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_efetua_pesquisa_todas_contas_acesso()
			throws RemoteException {
		EspecificacaoPesquisaContaAcessoDTO especificacaoPesquisaContasAcessoDTO = new EspecificacaoPesquisaContaAcessoDTO();
		especificacaoPesquisaContasAcessoDTO.adicionarParametro(
				ChavePesquisaDTO.TODOS_CONTAS_ACESSO, "");

		ResultadoListagemContaAcessoDTO listagemContaAcessoDto = servicoSisMovimentacaoServerRmi
				.pesquisarContaAcessoPor(especificacaoPesquisaContasAcessoDTO);

		Assert.assertTrue(listagemContaAcessoDto.sucesso());
		Assert.assertEquals(listagemContaAcessoDto.getObjetosDto().size(), 6);
	}
	
	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_obtem_listagem_perfil() throws RemoteException {
		ResultadoListagemPerfilDTO resultadoListagemPerfil = servicoSisMovimentacaoServerRmi
				.obterListagemPerfil();

		Assert.assertTrue(resultadoListagemPerfil.sucesso());
		Assert.assertFalse(resultadoListagemPerfil.getObjetosDto().isEmpty(),
				"A lista deveria conter os itens.");
		Assert.assertEquals(resultadoListagemPerfil.getObjetosDto().size(), 2);
	}
	
	@Test(groups = { TiposDeTeste.INTEGRACAO_SEM_BD })
	public void servico_pesquisa_listagem_perfil_atraves_de_especificacao()
			throws RemoteException {
		EspecificacaoPesquisaPerfilDTO especificacao = new EspecificacaoPesquisaPerfilDTO();
		especificacao.adicionarParametro(ChavePesquisaDTO.TODOS_PERFIS, "");
		ResultadoListagemPerfilDTO resultadoListagemPerfilDto = servicoSisMovimentacaoServerRmi
				.pesquisarPerfilPor(especificacao);

		Assert.assertTrue(resultadoListagemPerfilDto.sucesso());
		Assert.assertFalse(
				resultadoListagemPerfilDto.getObjetosDto().isEmpty(),
				"A lista deveria conter os itens.");
	}
	
	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_obtem_listagem_permissao() throws RemoteException {
		ResultadoListagemPermissaoDTO resultadoListagemPermissao = servicoSisMovimentacaoServerRmi
				.obterListagemPermissao();

		Assert.assertTrue(resultadoListagemPermissao.sucesso());
		Assert.assertFalse(resultadoListagemPermissao.getObjetosDto().isEmpty(),
				"A lista deveria conter os itens.");
		Assert.assertEquals(resultadoListagemPermissao.getObjetosDto().size(), 8);
	}
	
	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_obtem_listagem_status() throws RemoteException {
		ResultadoListagemStatusDTO resultadoListagemStatus = servicoSisMovimentacaoServerRmi
				.obterListagemStatus();

		Assert.assertTrue(resultadoListagemStatus.sucesso());
		Assert.assertFalse(resultadoListagemStatus.getObjetosDto().isEmpty(),
				"A lista deveria conter os itens.");
		Assert.assertEquals(resultadoListagemStatus.getObjetosDto().size(), 3);
	}
	
	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_obtem_listagem_sim_nao() throws RemoteException {
		ResultadoListagemSimNaoDTO resultadoListagemSimNao = servicoSisMovimentacaoServerRmi
				.obterListagemSimNao();

		Assert.assertTrue(resultadoListagemSimNao.sucesso());
		Assert.assertEquals(resultadoListagemSimNao.getObjetosDto().size(), 2);
	}
	
	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_obtem_listagem_fibra() throws RemoteException {
		ResultadoListagemFibraDTO resultadoListagemFibra = servicoSisMovimentacaoServerRmi
				.obterListagemFibra();

		Assert.assertTrue(resultadoListagemFibra.sucesso());
		Assert.assertEquals(resultadoListagemFibra.getObjetosDto().size(), 3);
	}
	
	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_obtem_listagem_papel() throws RemoteException {
		ResultadoListagemPapelDTO resultadoListagemPapel = servicoSisMovimentacaoServerRmi
				.obterListagemPapel();

		Assert.assertTrue(resultadoListagemPapel.sucesso());
		Assert.assertEquals(resultadoListagemPapel.getObjetosDto().size(), 4);
	}
	
	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_obtem_listagem_abdb() throws RemoteException {
		ResultadoListagemAbdbDTO resultadoListagemAbdb = servicoSisMovimentacaoServerRmi
				.obterListagemAbdb();

		Assert.assertTrue(resultadoListagemAbdb.sucesso());
		Assert.assertEquals(resultadoListagemAbdb.getObjetosDto().size(), 2);
	}
		
	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_obtem_movimentacao_apartir_de_especificacao() throws RemoteException {
		
		EspecificacaoPesquisaMovimentacaoDTO especificacaoPesquisaMovimentacaoDTO = new EspecificacaoPesquisaMovimentacaoDTO();
		especificacaoPesquisaMovimentacaoDTO.setQuantidadePesquisa(2);
		ResultadoListagemMovimentacaoDTO resultadoListagemMovimentacao = servicoSisMovimentacaoServerRmi
				.pesquisarMovimentacaoPor(especificacaoPesquisaMovimentacaoDTO);

		Assert.assertTrue(resultadoListagemMovimentacao.sucesso());
		Assert.assertFalse(resultadoListagemMovimentacao.getObjetosDto().isEmpty(),
				"A lista deveria conter os itens.");
		Assert.assertEquals(resultadoListagemMovimentacao.getObjetosDto().size(), 2);
	}
	
	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_obtem_relacao_clientes_com_sucesso()
			throws RemoteException {

		ResultadoListagemTextoDTO resultadoListagemTextoDto = servicoSisMovimentacaoServerRmi
				.obterListagemCliente();

		Assert.assertTrue(resultadoListagemTextoDto.sucesso());
		Assert.assertFalse(resultadoListagemTextoDto.getObjetosDto().isEmpty(),
				"A lista deveria conter os itens.");
		Assert.assertEquals(resultadoListagemTextoDto.getObjetosDto().size(), 2);
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_obtem_relacao_descricao_com_sucesso()
			throws RemoteException {

		ResultadoListagemTextoDTO resultadoListagemTextoDto = servicoSisMovimentacaoServerRmi
				.obterListagemDescricao();

		Assert.assertTrue(resultadoListagemTextoDto.sucesso());
		Assert.assertFalse(resultadoListagemTextoDto.getObjetosDto().isEmpty(),
				"A lista deveria conter os itens.");
		Assert.assertEquals(resultadoListagemTextoDto.getObjetosDto().size(), 2);
	}
	
	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_obtem_relacao_bobina_com_sucesso()
			throws RemoteException {

		ResultadoListagemTextoDTO resultadoListagemTextoDto = servicoSisMovimentacaoServerRmi
				.obterListagemBobina();

		Assert.assertTrue(resultadoListagemTextoDto.sucesso());
		Assert.assertFalse(resultadoListagemTextoDto.getObjetosDto().isEmpty(),
				"A lista deveria conter os itens.");
		Assert.assertEquals(resultadoListagemTextoDto.getObjetosDto().size(), 2);
	}
	
	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_obtem_relacao_tipo_prova_com_sucesso()
			throws RemoteException {

		ResultadoListagemTextoDTO resultadoListagemTextoDto = servicoSisMovimentacaoServerRmi
				.obterListagemTipoProva();

		Assert.assertTrue(resultadoListagemTextoDto.sucesso());
		Assert.assertFalse(resultadoListagemTextoDto.getObjetosDto().isEmpty(),
				"A lista deveria conter os itens.");
		Assert.assertEquals(resultadoListagemTextoDto.getObjetosDto().size(), 2);
	}
	
	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_obtem_relacao_plana_papel_com_sucesso()
			throws RemoteException {

		ResultadoListagemTextoDTO resultadoListagemTextoDto = servicoSisMovimentacaoServerRmi
				.obterListagemPlanaPapel();

		Assert.assertTrue(resultadoListagemTextoDto.sucesso());
		Assert.assertFalse(resultadoListagemTextoDto.getObjetosDto().isEmpty(),
				"A lista deveria conter os itens.");
		Assert.assertEquals(resultadoListagemTextoDto.getObjetosDto().size(), 2);
	}
		
	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_obtem_relacao_descricao_produto_com_sucesso()
			throws RemoteException {

		ResultadoListagemTextoDTO resultadoListagemTextoDto = servicoSisMovimentacaoServerRmi
				.obterListagemDescricaoProduto();

		Assert.assertTrue(resultadoListagemTextoDto.sucesso());
		Assert.assertFalse(resultadoListagemTextoDto.getObjetosDto().isEmpty(),
				"A lista deveria conter os itens.");
		Assert.assertEquals(resultadoListagemTextoDto.getObjetosDto().size(), 2);
	}
	
	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_obtem_relacao_cor_com_sucesso() throws RemoteException {

		ResultadoListagemTextoDTO resultadoListagemTextoDto = servicoSisMovimentacaoServerRmi
				.obterListagemCor();

		Assert.assertTrue(resultadoListagemTextoDto.sucesso());
		Assert.assertFalse(resultadoListagemTextoDto.getObjetosDto().isEmpty(),
				"A lista deveria conter os itens.");
		Assert.assertEquals(resultadoListagemTextoDto.getObjetosDto().size(), 2);
	}
	
	
	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_inclui_perfil_com_sucesso() throws RemoteException {
		ResultadoAutenticacaoDTO resultadoDto = servicoSisMovimentacaoServerRmi
				.autenticarLogin(construirCredencialDtoValida());

		PerfilDTO perfilDto = new PerfilDTO(null, "Teste");

		ResultadoEdicaoPerfilDTO resultadoEdicaoAtendimentoIndividualDto = servicoSisMovimentacaoServerRmi
				.editarPerfil(perfilDto, resultadoDto.getToken());

		Assert.assertTrue(resultadoEdicaoAtendimentoIndividualDto.sucesso());
	}
	
	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_inclui_movimentacao_com_sucesso() throws RemoteException {
		ResultadoAutenticacaoDTO resultadoDto = servicoSisMovimentacaoServerRmi
				.autenticarLogin(construirCredencialDtoValida());

		ResultadoEdicaoMovimentacaoDTO resultadoEdicaoMovimentacaoDto = servicoSisMovimentacaoServerRmi
				.incluirMovimentacao("31/12/2012 09:00", resultadoDto.getToken());

		Assert.assertTrue(resultadoEdicaoMovimentacaoDto.sucesso());
	}
	
	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_altera_movimentacao_com_sucesso()
			throws RemoteException {
		ResultadoAutenticacaoDTO resultadoDto = servicoSisMovimentacaoServerRmi
				.autenticarLogin(construirCredencialDtoValida());

		EspecificacaoPesquisaMovimentacaoDTO especificacao = new EspecificacaoPesquisaMovimentacaoDTO();
		especificacao.setQuantidadePesquisa(1);

		ResultadoListagemMovimentacaoDTO resultadoListagemMovimentacaoDto = servicoSisMovimentacaoServerRmi
				.pesquisarMovimentacaoPor(especificacao);

		MovimentacaoDTO movimentacaoDto = resultadoListagemMovimentacaoDto
				.getObjetosDto().get(0);
		movimentacaoDto.setGl("XXXX");

		ResultadoEdicaoMovimentacaoDTO resultadoEdicaoMovimentacaoDTO = servicoSisMovimentacaoServerRmi
				.editarMovimentacao(movimentacaoDto, resultadoDto.getToken());

		Assert.assertTrue(resultadoEdicaoMovimentacaoDTO.sucesso());
	}
	
	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_avanca_status_movimentacao_com_sucesso()
			throws RemoteException {
		ResultadoAutenticacaoDTO resultadoDto = servicoSisMovimentacaoServerRmi
				.autenticarLogin(construirCredencialDtoValida());

		EspecificacaoPesquisaMovimentacaoDTO especificacao = new EspecificacaoPesquisaMovimentacaoDTO();
		especificacao.setQuantidadePesquisa(1);

		ResultadoListagemMovimentacaoDTO resultadoListagemMovimentacaoDto = servicoSisMovimentacaoServerRmi
				.pesquisarMovimentacaoPor(especificacao);

		MovimentacaoDTO movimentacaoDto = resultadoListagemMovimentacaoDto
				.getObjetosDto().get(0);

		ResultadoEdicaoMovimentacaoDTO resultadoEdicaoMovimentacaoDTO = servicoSisMovimentacaoServerRmi
				.avancarStatusMovimentacao(movimentacaoDto, "01/01/2000 09:00",
						resultadoDto.getToken());

		Assert.assertTrue(resultadoEdicaoMovimentacaoDTO.sucesso());
	}
	
	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_nao_altera_status_movimentacao_por_causa_de_violacao_sequencia()
			throws RemoteException {
		ResultadoAutenticacaoDTO resultadoDto = servicoSisMovimentacaoServerRmi
				.autenticarLogin(construirCredencialDtoValida());

		ResultadoEdicaoMovimentacaoDTO resultadoEdicaoMovimentacaoDto = servicoSisMovimentacaoServerRmi
				.incluirMovimentacao("31/12/2012 09:00",
						resultadoDto.getToken());

		ResultadoEdicaoMovimentacaoDTO resultadoEdicaoStatusMovimentacaoDTO = servicoSisMovimentacaoServerRmi
				.editarStatusMovimentacao(
						(MovimentacaoDTO) resultadoEdicaoMovimentacaoDto
								.obterObjetoDtoEditado(), new StatusDTO(
								"APROVADO_AGUARDANDO_ESPECIFICACAO"),
						"31/12/2012", resultadoDto.getToken());

		Assert.assertFalse(resultadoEdicaoStatusMovimentacaoDTO.sucesso());
	}
	
	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_altera_status_movimentacao_com_sucesso()
			throws RemoteException {
		ResultadoAutenticacaoDTO resultadoDto = servicoSisMovimentacaoServerRmi
				.autenticarLogin(construirCredencialDtoValida());

		EspecificacaoPesquisaMovimentacaoDTO especificacao = new EspecificacaoPesquisaMovimentacaoDTO();
		especificacao.setQuantidadePesquisa(1);

		ResultadoListagemMovimentacaoDTO resultadoListagemMovimentacaoDto = servicoSisMovimentacaoServerRmi
				.pesquisarMovimentacaoPor(especificacao);

		MovimentacaoDTO movimentacaoDto = resultadoListagemMovimentacaoDto
				.getObjetosDto().get(0);
		
		ResultadoEdicaoMovimentacaoDTO resultadoEdicaoStatusMovimentacaoDTO = servicoSisMovimentacaoServerRmi
				.editarStatusMovimentacao(movimentacaoDto, new StatusDTO(
						"ARTE_FINALIZADA"), "31/12/2012", resultadoDto
						.getToken());

		Assert.assertTrue(resultadoEdicaoStatusMovimentacaoDTO.sucesso());
	}
	
	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_inclui_perfil_sem_sucesso_por_falta_de_permissao() throws RemoteException {
		ResultadoAutenticacaoDTO resultadoDto = servicoSisMovimentacaoServerRmi
				.autenticarLogin(construirCredencialDtoSemPermissao());

		PerfilDTO perfilDto = new PerfilDTO(null, "Teste");

		ResultadoEdicaoPerfilDTO resultadoEdicaoAtendimentoIndividualDto = servicoSisMovimentacaoServerRmi
				.editarPerfil(perfilDto, resultadoDto.getToken());

		Assert.assertFalse(resultadoEdicaoAtendimentoIndividualDto.sucesso());
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_efetua_autenticacao_a_partir_de_credencial_valida()
			throws Exception {
		CredencialDTO credencialDto = construirCredencialDtoValida();

		ResultadoAutenticacaoDTO resultadoDto = servicoSisMovimentacaoServerRmi
				.autenticarLogin(credencialDto);

		Assert.assertTrue(resultadoDto.sucesso(),
				"A conta de acesso deveria ter sido autenticada.");
		Assert.assertNotNull(resultadoDto.getToken(),
				"Um token deveria ser gerado.");
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_nega_autenticacao_a_partir_de_credencial_invalida()
			throws Exception {
		CredencialDTO credencialDto = new CredencialDTO("invalida", "999999");

		ResultadoAutenticacaoDTO resultadoDto = servicoSisMovimentacaoServerRmi
				.autenticarLogin(credencialDto);

		Assert.assertFalse(resultadoDto.sucesso(),
				"A autenticação deveria ser negada.");
	}
	
	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_inclui_nova_conta_acesso_pelo_administrador_com_sucesso()
			throws Exception {
		ResultadoAutenticacaoDTO tokenDto = servicoSisMovimentacaoServerRmi
				.autenticarLogin(construirCredencialDtoValida());
		
		ContaAcessoDTO contaAcessoDto = new ContaAcessoDTO();
		contaAcessoDto.setUsuario("josep");
		contaAcessoDto.setSenha("meaza");
		contaAcessoDto.setPerfilDto(new PerfilDTO(new Long(1111), "Administrador"));

		ResultadoEdicaoContaAcessoDTO resultadoEdicaoContaAcessoDto = servicoSisMovimentacaoServerRmi
				.editarContaAcesso(contaAcessoDto, tokenDto.getToken());
	
		Assert.assertTrue(resultadoEdicaoContaAcessoDto.sucesso());
	}
	
	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_inclui_nova_conta_acesso_pelo_administrador_sem_sucesso()
			throws Exception {
		ResultadoAutenticacaoDTO tokenDto = servicoSisMovimentacaoServerRmi
				.autenticarLogin(construirCredencialDtoSemPermissao());
		
		ContaAcessoDTO contaAcessoDto = new ContaAcessoDTO();
		contaAcessoDto.setUsuario("josep");
		contaAcessoDto.setSenha("meaza");
		contaAcessoDto.setPerfilDto(new PerfilDTO(new Long(1111), "Administrador"));

		ResultadoEdicaoContaAcessoDTO resultadoEdicaoContaAcessoDto = servicoSisMovimentacaoServerRmi
				.editarContaAcesso(contaAcessoDto, tokenDto.getToken());
	
		Assert.assertFalse(resultadoEdicaoContaAcessoDto.sucesso());
	}
	
	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_inclui_nova_conta_acesso_invalida_pelo_administrador_sem_sucesso()
			throws Exception {
		ResultadoAutenticacaoDTO tokenDto = servicoSisMovimentacaoServerRmi
				.autenticarLogin(construirCredencialDtoValida());
		
		ContaAcessoDTO contaAcessoDto = new ContaAcessoDTO();

		ResultadoEdicaoContaAcessoDTO resultadoEdicaoContaAcessoDto = servicoSisMovimentacaoServerRmi
				.editarContaAcesso(contaAcessoDto, tokenDto.getToken());
	
		Assert.assertFalse(resultadoEdicaoContaAcessoDto.sucesso());
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_ativa_filtro_grupo_na_conta_cesso_sem_sucesso()
			throws Exception {
		ResultadoAutenticacaoDTO tokenDto = servicoSisMovimentacaoServerRmi
				.autenticarLogin(construirCredencialDtoValida());

		ResultadoOperacaoFiltroGrupoDTO resultadoOperacaoFiltroGrupoDto = servicoSisMovimentacaoServerRmi
				.ativarFiltroGrupo(tokenDto.getToken());

		boolean filtroAtivo = servicoSisMovimentacaoServerRmi
				.filtroEstaAtivado(tokenDto.getToken());

		Assert.assertTrue(resultadoOperacaoFiltroGrupoDto.sucesso());
		Assert.assertTrue(filtroAtivo);
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_desativa_filtro_grupo_na_conta_cesso_sem_sucesso()
			throws Exception {
		ResultadoAutenticacaoDTO tokenDto = servicoSisMovimentacaoServerRmi
				.autenticarLogin(construirCredencialDtoValida());

		servicoSisMovimentacaoServerRmi.ativarFiltroGrupo(tokenDto.getToken());
		ResultadoOperacaoFiltroGrupoDTO resultadoOperacaoFiltroGrupoDto = servicoSisMovimentacaoServerRmi
				.desativarFiltroGrupo(tokenDto.getToken());

		boolean filtroAtivo = servicoSisMovimentacaoServerRmi
				.filtroEstaAtivado(tokenDto.getToken());

		Assert.assertTrue(resultadoOperacaoFiltroGrupoDto.sucesso());
		Assert.assertFalse(filtroAtivo);
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_permite_autorizacao_acesso_a_funcionalidade()
			throws RemoteException {
		ResultadoAutenticacaoDTO resultadoDto = servicoSisMovimentacaoServerRmi
				.autenticarLogin(construirCredencialDtoValida());

		boolean possuiAutorizacao = servicoSisMovimentacaoServerRmi.possuiAutorizacao(
				resultadoDto.getToken(), "CONTA_ACESSO_EDICAO");

		Assert.assertTrue(possuiAutorizacao);
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_nega_autorizacao_acesso_a_funcionalidade()
			throws RemoteException {
		ResultadoAutenticacaoDTO resultadoDto = servicoSisMovimentacaoServerRmi
				.autenticarLogin(construirCredencialDtoSemPermissao());

		boolean possuiNegado = servicoSisMovimentacaoServerRmi.possuiAutorizacao(
				resultadoDto.getToken(), "CONTA_ACESSO_EDICAO");

		Assert.assertFalse(possuiNegado);
	}

	
	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_gera_altera_extensao_relatorio_padrao_para_xls()
			throws RemoteException {
		ResultadoAutenticacaoDTO resultadoAutenticacaoDto = servicoSisMovimentacaoServerRmi
				.autenticarLogin(construirCredencialDtoValida());

		ResultadoAlteracaoExtensaoRelatorioDTO resultadoAlteracaoDto = servicoSisMovimentacaoServerRmi
				.alterarExtensaoRelatorioParaXLS(resultadoAutenticacaoDto
						.getToken());

		Assert.assertTrue(resultadoAlteracaoDto.sucesso());
	}
	
	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_gera_altera_extensao_relatorio_padrao_para_pdf()
			throws RemoteException {
		ResultadoAutenticacaoDTO resultadoAutenticacaoDto = servicoSisMovimentacaoServerRmi
				.autenticarLogin(construirCredencialDtoValida());

		ResultadoAlteracaoExtensaoRelatorioDTO resultadoAlteracaoDto = servicoSisMovimentacaoServerRmi
				.alterarExtensaoRelatorioParaPDF(resultadoAutenticacaoDto
						.getToken());

		Assert.assertTrue(resultadoAlteracaoDto.sucesso());
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_altera_senha_conta_acesso_com_sucesso()
			throws RemoteException {
		CredencialDTO credencialDto = construirCredencialAlternativaDtoValida();

		ResultadoAutenticacaoDTO resultadoAutenticacaoDto = servicoSisMovimentacaoServerRmi
				.autenticarLogin(credencialDto);

		ResultadoAlteracaoSenhaDTO resultadoAlteracaoSenhaDto = servicoSisMovimentacaoServerRmi.alterarSenha(credencialDto,
				resultadoAutenticacaoDto.getToken());

		ResultadoAutenticacaoDTO resultadoAutenticacaoAposAlteracaoDto = servicoSisMovimentacaoServerRmi
				.autenticarLogin(credencialDto);

		Assert.assertTrue(resultadoAlteracaoSenhaDto.sucesso());
		Assert.assertTrue(resultadoAutenticacaoAposAlteracaoDto.sucesso());
	}
	
	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_nao_altera_senha_conta_acesso_com_senha_diferente()
			throws RemoteException {
		CredencialDTO credencialDto = construirCredencialAlternativaDtoInvalida();

		ResultadoAutenticacaoDTO resultadoAutenticacaoDto = servicoSisMovimentacaoServerRmi
				.autenticarLogin(credencialDto);

		ResultadoAlteracaoSenhaDTO resultadoAlteracaoSenhaDto = servicoSisMovimentacaoServerRmi
				.alterarSenha(credencialDto,
						resultadoAutenticacaoDto.getToken());

		Assert.assertFalse(resultadoAlteracaoSenhaDto.sucesso());
	}
	
	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_nao_altera_senha_conta_acesso_sem_senha_em_branco()
			throws RemoteException {
		CredencialDTO credencialDto = construirCredencialDtoValida();

		ResultadoAutenticacaoDTO resultadoAutenticacaoDto = servicoSisMovimentacaoServerRmi
				.autenticarLogin(credencialDto);

		ResultadoAlteracaoSenhaDTO resultadoAlteracaoSenhaDto = servicoSisMovimentacaoServerRmi
				.alterarSenha(construirCredencialDtoValidaSemSenha(),
						resultadoAutenticacaoDto.getToken());

		Assert.assertFalse(resultadoAlteracaoSenhaDto.sucesso());
	}
}
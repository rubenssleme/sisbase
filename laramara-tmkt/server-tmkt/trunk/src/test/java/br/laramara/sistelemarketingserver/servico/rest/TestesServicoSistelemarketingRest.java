package br.laramara.sistelemarketingserver.servico.rest;

import java.rmi.RemoteException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;

import br.laramara.sistelemarketingcommons.dtos.BairroResultadoDTO;
import br.laramara.sistelemarketingcommons.dtos.EstadoResultadoDTO;
import br.laramara.sistelemarketingcommons.dtos.MunicipioResultadoDTO;
import br.laramara.sistelemarketingcommons.dtos.campanha.AlocacaoOperadorDTO;
import br.laramara.sistelemarketingcommons.dtos.campanha.AlocacaoOperadorResultadoDTO;
import br.laramara.sistelemarketingcommons.dtos.campanha.CampanhaDTO;
import br.laramara.sistelemarketingcommons.dtos.campanha.CampanhaResultadoDTO;
import br.laramara.sistelemarketingcommons.dtos.campanha.CampanhaSolicitacaoEdicaoDTO;
import br.laramara.sistelemarketingcommons.dtos.campanha.CriterioDTO;
import br.laramara.sistelemarketingcommons.dtos.campanha.CriterioResultadoDTO;
import br.laramara.sistelemarketingcommons.dtos.contato.DistribuicaoContatoResultadoDTO;
import br.laramara.sistelemarketingcommons.dtos.contato.TelefoneDTO;
import br.laramara.sistelemarketingcommons.dtos.doacao.DoacaoDTO;
import br.laramara.sistelemarketingcommons.dtos.doacao.DoacaoResultadoDTO;
import br.laramara.sistelemarketingcommons.dtos.doacao.DoacaoSolicitacaoEdicaoDTO;
import br.laramara.sistelemarketingcommons.dtos.doacao.MetodoResultadoDTO;
import br.laramara.sistelemarketingcommons.dtos.seguranca.ContaAcessoDTO;
import br.laramara.sistelemarketingcommons.dtos.seguranca.ContaAcessoResultadoAutenticacaoDTO;
import br.laramara.sistelemarketingcommons.dtos.seguranca.ContaAcessoResultadoDTO;
import br.laramara.sistelemarketingcommons.dtos.seguranca.ContaAcessoSolicitacaoEdicaoDTO;
import br.laramara.sistelemarketingcommons.dtos.seguranca.CredencialDTO;
import br.laramara.sistelemarketingcommons.dtos.seguranca.NivelDTO;
import br.laramara.sistelemarketingcommons.dtos.seguranca.NivelResultadoDTO;
import br.laramara.sistelemarketingcommons.dtos.seguranca.NivelSolicitacaoEdicaoDTO;
import br.laramara.sistelemarketingcommons.dtos.seguranca.PermissaoResultadoDTO;
import br.laramara.sistelemarketingcommons.dtos.seguranca.TurnoResultadoDTO;
import br.laramara.sistelemarketingcommons.dtos.telefonia.LigacaoDTO;
import br.laramara.sistelemarketingcommons.dtos.telefonia.LigacaoResultadoDTO;
import br.laramara.sistelemarketingcommons.dtos.telefonia.RamalResultadoDTO;
import br.laramara.sistelemarketingcommons.servicos.rest.ContratoRest;
import br.laramara.sistelemarketingcommons.utilitarios.TiposDeTeste;
import br.laramara.sistelemarketingserver.TestesIntegracaoAbstrato;
import br.laramara.sistelemarketingserver.dominio.campanha.ContextoAlocacaoOperador;
import br.laramara.sistelemarketingserver.dominio.campanha.ContextoCampanha;
import br.laramara.sistelemarketingserver.dominio.campanha.ContextoCriterio;
import br.laramara.sistelemarketingserver.dominio.doacao.ContextoDoacao;
import br.laramara.sistelemarketingserver.dominio.seguranca.ContextoContaAcesso;
import br.laramara.sistelemarketingserver.dominio.seguranca.ContextoCredencial;
import br.laramara.sistelemarketingserver.dominio.seguranca.ContextoNivel;
import br.laramara.sistelemarketingserver.dominio.seguranca.ContextoToken;
import br.laramara.sistelemarketingserver.utilitarios.Configuracao;
import br.laramara.sistelemarketingserver.utilitarios.Registro;


public class TestesServicoSistelemarketingRest extends TestesIntegracaoAbstrato{
	
	private WireMockServer wireMockServer;
	
	private static final String URL_BASE = "http://"
			+ new Configuracao().obterConfiguracao(Configuracao.SERVIDOR_ENDERECO) + ":"
			+ new Configuracao().obterConfiguracao(Configuracao.SERVIDOR_PORTA);
	
	protected TestesServicoSistelemarketingRest() {
		super("DadosTestesServicoSistelemarketing.xml");
		Registro.inicializarContexto();
		wireMockServer = new WireMockServer(
				Integer.valueOf(new Configuracao().obterConfiguracao(Configuracao.PBX_API_PORTA)));
	}
	
	private static String obterUrl(String url, String chave, String valor) {
		return (URL_BASE + url).replace(chave, valor);
	}
	
	private static String obterUrlBase(String url) {
		return URL_BASE + url;
	}
	
	private void gerarStubDeStatusDeRamalDaTelefonia() {
		wireMockServer.stubFor(WireMock.get(WireMock.urlEqualTo("/mxml?action=extensionstatelist"))
				.willReturn(WireMock.aResponse().withBody(
						"<ajax-response>"
						+ "<response type='object' id='unknown'>"
				        + "<generic event='ExtensionStatus' exten='6489' context='ext-local' hint='PJSIP/6489&amp;Custom:DND6489,CustomPresence:6489' status='4' statustext='Unavailable' />"
				        + "</response>"
				    	+ "</ajax-response>").withStatus(200).withHeader("Content-Type", "application/xml")));
	}
	
	private void gerarStubDeLigacaoDeTelefonia() {
		wireMockServer.stubFor(WireMock.get(WireMock.urlEqualTo("/mxml?action=originate&channel=pjsip/6489&exten=011957878870&priority=1&context=from-internal"))
				.willReturn(WireMock.aResponse().withBody(
						"<ajax-response>" +
					    "<response type='object' id='unknown'>" + 
					        "<generic response='Success' message='Originate successfully queued' />" +
					    "</response>" +
					"</ajax-response>").withStatus(200).withHeader("Content-Type", "application/xml")));
	}

	private void gerarStubDeAutenticacaoDeTelefonia() {
		wireMockServer.stubFor(WireMock.get(WireMock.urlEqualTo("/mxml?action=login&username=mobileasy&secret=123456"))
				.willReturn(WireMock.aResponse().withBody(
					"<ajax-response>" +
					    "<response type='object' id='unknown'>" +
					       "<generic response='Success' message='Authentication accepted' /> " +
					    "</response>" + 
					"</ajax-response>").withStatus(200).withHeader("Content-Type", "application/xml")));
	}
	
	private static String criarUrlContaAcessoObter(Long id) {
		return obterUrl(ContratoRest.URL_CONTA_ACESSO_OBTER, ContratoRest.URL_ID, id.toString());
	}
	
	private String criarUrlNivelObter(Long id) {
		return obterUrl(ContratoRest.URL_NIVEL_OBTER, ContratoRest.URL_ID, id.toString());
	}
	
	private String criarUrlPermissaoObter(String token) {
		return obterUrl(ContratoRest.URL_PERMISSAO_OBTER, ContratoRest.URL_TOKEN, token);
	}
	
	private String criarUrlMunicipioObter(String id) {
		return obterUrl(ContratoRest.URL_MUNICIPIO_LISTAR, ContratoRest.URL_ID, id.toString());
	}
	
	private String criarUrlBairroObter(String id) {
		return obterUrl(ContratoRest.URL_BAIRRO_LISTAR, ContratoRest.URL_ID, id.toString());
	}
	
	private String criarUrlCampanhaObter(Long id) {
		return obterUrl(ContratoRest.URL_CAMPANHA_OBTER, ContratoRest.URL_ID, id.toString());
	}
	
	private String criarUrlDistribuicaoContatoObter(String token) {
		return obterUrl(ContratoRest.URL_DISTRIBUICAO_CONTATO_OBTER, ContratoRest.URL_TOKEN, token);
	}
	
	private String criarUrlAlocacaoOperadorValidar() {
		return obterUrlBase(ContratoRest.URL_ALOCACAO_OPERADOR_VALIDAR);
	}
	
	private String criarUrlRamalListar() {
		return obterUrlBase(ContratoRest.URL_RAMAL_LISTAR);
	}

	private String criarUrlContaAcessoAutenticar() {
		return obterUrlBase(ContratoRest.URL_CONTA_ACESSO_AUTENTICAR);
	}
	
	private String criarUrlContaAcessoEditar() {
		return obterUrlBase(ContratoRest.URL_CONTA_ACESSO_EDITAR);
	}

	private String criarUrlContaAcessoListarTodosOperadoresAtivos() {
		return obterUrlBase(ContratoRest.URL_CONTA_ACESSO_LISTAR_TODOS_OPERADORES_ATIVOS);
	}

	private String criarUrlCampanhaEditar() {
		return obterUrlBase(ContratoRest.URL_CAMPANHA_EDITAR);
	}

	private String criarUrlContaAcessoListar() {
		return obterUrlBase(ContratoRest.URL_CONTA_ACESSO_LISTAR);
	}

	private String criarUrlTurnoListar() {
		return obterUrlBase(ContratoRest.URL_TURNO_LISTAR);
	}
	
	private String criarUrlEstadoListar() {
		return obterUrlBase(ContratoRest.URL_ESTADO_LISTAR);
	}
	
	private String criarUrlCriterioValidar() {
		return obterUrlBase(ContratoRest.URL_CRITERIO_VALIDAR);
	}

	private String criarUrlNivelEditar() {
		return obterUrlBase(ContratoRest.URL_NIVEL_EDITAR);
	}
	
	private String criarUrlNivelListar() {
		return obterUrlBase(ContratoRest.URL_NIVEL_LISTAR);
	}
	
	private String criarUrlStatusRamalObter(String token) {
		return obterUrl(ContratoRest.URL_STATUS_RAMAL_OBTER, ContratoRest.URL_TOKEN, token);
	}
		
	private String criarUrlLigar() {
		return obterUrlBase(ContratoRest.URL_LIGAR);
	}

	private String criarUrlDoacaoEditar() {
		return obterUrlBase(ContratoRest.URL_DOACAO_EDITAR);
	}
	
	private String criarUrlMetodoListar() {
		return obterUrlBase(ContratoRest.URL_METODO_LISTAR);
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_autentica_usuario_com_sucesso() throws RemoteException {
		CredencialDTO credencialDto = ContextoCredencial.construirPauloBandeira();
		
		ContaAcessoResultadoAutenticacaoDTO resultadoAutenticacaoDTO = executarPost(criarUrlContaAcessoAutenticar(),
				credencialDto, ContaAcessoResultadoAutenticacaoDTO.class);

		Assert.assertTrue(resultadoAutenticacaoDTO.sucesso());
		Assert.assertNotNull(resultadoAutenticacaoDTO.obterContaAcessoDto());
		Assert.assertNotNull(resultadoAutenticacaoDTO.getToken());
	}
	
	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_nao_autentica_usuario_com_senha_errada() throws RemoteException {
		CredencialDTO credencialDto = ContextoCredencial.construirSenhaErrada();

		ContaAcessoResultadoAutenticacaoDTO resultadoAutenticacaoDTO = executarPost(criarUrlContaAcessoAutenticar(),
				credencialDto, ContaAcessoResultadoAutenticacaoDTO.class);

		Assert.assertFalse(resultadoAutenticacaoDTO.sucesso());
		Assert.assertTrue(resultadoAutenticacaoDTO.getMensagem().contains("Não autorizado."));
	}
	
	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_nao_autentica_usuario_inativado() throws RemoteException {
		CredencialDTO credencialDto = ContextoCredencial.construirPriscilaBandeira();

		ContaAcessoResultadoAutenticacaoDTO resultadoAutenticacaoDTO = executarPost(criarUrlContaAcessoAutenticar(),
				credencialDto, ContaAcessoResultadoAutenticacaoDTO.class);

		Assert.assertFalse(resultadoAutenticacaoDTO.sucesso());
		Assert.assertTrue(resultadoAutenticacaoDTO.getMensagem().contains("Não autorizado."));
	}
	
	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_edita_conta_acesso_com_sucesso() throws RemoteException {
		ContaAcessoDTO contaAcessoDto = ContextoContaAcesso.fabricar();
		
		ContaAcessoSolicitacaoEdicaoDTO contaAcessoSolicitacaoEdicaoDTO = new ContaAcessoSolicitacaoEdicaoDTO();
		contaAcessoSolicitacaoEdicaoDTO.setContaAcessoDto(contaAcessoDto);
		contaAcessoSolicitacaoEdicaoDTO.setToken(ContextoToken.obterCarlosEduardo());
		
		
		ContaAcessoResultadoDTO resultadoEdicaoDTO = executarPost(criarUrlContaAcessoEditar(),
				contaAcessoSolicitacaoEdicaoDTO, ContaAcessoResultadoDTO.class);

		Assert.assertTrue(resultadoEdicaoDTO.sucesso());
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_inclui_conta_acesso_com_sucesso() throws RemoteException {
		ContaAcessoDTO contaAcessoDto = ContextoContaAcesso.fabricar();
		contaAcessoDto.setId(null);
		contaAcessoDto.setSenha("1234");
		
		ContaAcessoSolicitacaoEdicaoDTO contaAcessoSolicitacaoEdicaoDTO = new ContaAcessoSolicitacaoEdicaoDTO();
		contaAcessoSolicitacaoEdicaoDTO.setContaAcessoDto(contaAcessoDto);
		contaAcessoSolicitacaoEdicaoDTO.setToken(ContextoToken.obterCarlosEduardo());
		
		ContaAcessoResultadoDTO resultadoEdicaoDTO = executarPost(criarUrlContaAcessoEditar(),
				contaAcessoSolicitacaoEdicaoDTO, ContaAcessoResultadoDTO.class);

		Assert.assertTrue(resultadoEdicaoDTO.sucesso());
	}
	
	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_nao_edita_conta_acesso_sem_autorizacao() throws RemoteException {
		ContaAcessoDTO contaAcessoDto = ContextoContaAcesso.fabricar();
		
		ContaAcessoSolicitacaoEdicaoDTO contaAcessoSolicitacaoEdicaoDTO = new ContaAcessoSolicitacaoEdicaoDTO();
		contaAcessoSolicitacaoEdicaoDTO.setContaAcessoDto(contaAcessoDto);
		contaAcessoSolicitacaoEdicaoDTO.setToken(ContextoToken.obterPauloAugusto());
		
		
		ContaAcessoResultadoDTO resultadoEdicaoDTO = executarPost(criarUrlContaAcessoEditar(),
				contaAcessoSolicitacaoEdicaoDTO, ContaAcessoResultadoDTO.class);

		Assert.assertFalse(resultadoEdicaoDTO.sucesso());
		Assert.assertTrue(resultadoEdicaoDTO.obterMensagens().contains("Não autorizado."));
	}
	
	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_nao_edita_conta_acesso_com_dados_invalidos() throws RemoteException {
		ContaAcessoDTO contaAcessoDto = ContextoContaAcesso.fabricar();
		contaAcessoDto.setLogin(null);
		
		ContaAcessoSolicitacaoEdicaoDTO contaAcessoSolicitacaoEdicaoDTO = new ContaAcessoSolicitacaoEdicaoDTO();
		contaAcessoSolicitacaoEdicaoDTO.setContaAcessoDto(contaAcessoDto);
		contaAcessoSolicitacaoEdicaoDTO.setToken(ContextoToken.obterCarlosEduardo());
		
		ContaAcessoResultadoDTO resultadoEdicaoDTO = executarPost(criarUrlContaAcessoEditar(),
				contaAcessoSolicitacaoEdicaoDTO, ContaAcessoResultadoDTO.class);

		Assert.assertFalse(resultadoEdicaoDTO.sucesso());
		Assert.assertTrue(resultadoEdicaoDTO.obterMensagens().contains("Insira um login válido."));
	}
	
	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_obtem_conta_acesso_com_sucesso() throws RemoteException {
		Long id = new Long(1000);
		ContaAcessoResultadoDTO resultadoDTO = executarGet(criarUrlContaAcessoObter(id),
				ContaAcessoResultadoDTO.class);

		Assert.assertTrue(resultadoDTO.sucesso());
		Assert.assertEquals(resultadoDTO.obterContaAcessoDto().getId(), id);
	}
	
	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_obtem_listagem_conta_acesso_com_sucesso() throws RemoteException {
		ContaAcessoResultadoDTO resultadoDTO = executarGet(criarUrlContaAcessoListar(),
				ContaAcessoResultadoDTO.class);

		Assert.assertTrue(resultadoDTO.sucesso());
		Assert.assertEquals(resultadoDTO.getContasAcessosDto().size(), 3);
	}

	
	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_obtem_listagem_operadores_com_sucesso() throws RemoteException {
		ContaAcessoResultadoDTO resultadoDTO = executarGet(criarUrlContaAcessoListarTodosOperadoresAtivos(),
				ContaAcessoResultadoDTO.class);

		Assert.assertTrue(resultadoDTO.sucesso());
		Assert.assertEquals(resultadoDTO.getContasAcessosDto().size(), 1);
	}
	
	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_obtem_listagem_nivel_com_sucesso() throws RemoteException {
		NivelResultadoDTO resultadoDTO = executarGet(criarUrlNivelListar(),
				NivelResultadoDTO.class);

		Assert.assertTrue(resultadoDTO.sucesso());
		Assert.assertEquals(resultadoDTO.getNiveisDto().size(), 2);
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_obtem_nivel_com_sucesso() throws RemoteException {
		Long id = new Long(2);
		NivelResultadoDTO resultadoDTO = executarGet(criarUrlNivelObter(id),
				NivelResultadoDTO.class);

		Assert.assertTrue(resultadoDTO.sucesso());
		Assert.assertEquals(resultadoDTO.obterNivelDto().getId(), id);
	}
	
	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_edita_nivel_com_sucesso() throws RemoteException {
		NivelDTO nivelDto = ContextoNivel.fabricarNivelDto();
		
		NivelSolicitacaoEdicaoDTO nivelSolicitacaoEdicaoDTO = new NivelSolicitacaoEdicaoDTO();
		nivelSolicitacaoEdicaoDTO.setNivelDto(nivelDto);
		nivelSolicitacaoEdicaoDTO.setToken(ContextoToken.obterCarlosEduardo());
		
		NivelResultadoDTO resultadoEdicaoDTO = executarPost(criarUrlNivelEditar(),
				nivelSolicitacaoEdicaoDTO, NivelResultadoDTO.class);

		Assert.assertTrue(resultadoEdicaoDTO.sucesso());
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_inclui_nivel_com_sucesso() throws RemoteException {
		NivelDTO nivelDto = ContextoNivel.fabricarNivelDto();
		nivelDto.setId(null);
		
		NivelSolicitacaoEdicaoDTO nivelSolicitacaoEdicaoDTO = new NivelSolicitacaoEdicaoDTO();
		nivelSolicitacaoEdicaoDTO.setNivelDto(nivelDto);
		nivelSolicitacaoEdicaoDTO.setToken(ContextoToken.obterCarlosEduardo());
		
		NivelResultadoDTO resultadoEdicaoDTO = executarPost(criarUrlNivelEditar(),
				nivelSolicitacaoEdicaoDTO, NivelResultadoDTO.class);

		Assert.assertTrue(resultadoEdicaoDTO.sucesso());
	}
	
	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_nao_edita_nivel_sem_autorizacao() throws RemoteException {
		NivelDTO nivelDto = ContextoNivel.fabricarNivelDto();
		
		NivelSolicitacaoEdicaoDTO nivelSolicitacaoEdicaoDTO = new NivelSolicitacaoEdicaoDTO();
		nivelSolicitacaoEdicaoDTO.setNivelDto(nivelDto);
		nivelSolicitacaoEdicaoDTO.setToken(ContextoToken.obterPauloAugusto());
		
		NivelResultadoDTO resultadoEdicaoDTO = executarPost(criarUrlNivelEditar(),
				nivelSolicitacaoEdicaoDTO, NivelResultadoDTO.class);

		Assert.assertFalse(resultadoEdicaoDTO.sucesso());
		Assert.assertTrue(resultadoEdicaoDTO.obterMensagens().contains("Não autorizado."));
	}
	
	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_nao_edita_nivel_com_dados_invalidos() throws RemoteException {
		NivelDTO nivelDto = ContextoNivel.fabricarNivelDto();
		nivelDto.setDescricao("");
		
		NivelSolicitacaoEdicaoDTO nivelSolicitacaoEdicaoDTO = new NivelSolicitacaoEdicaoDTO();
		nivelSolicitacaoEdicaoDTO.setNivelDto(nivelDto);
		nivelSolicitacaoEdicaoDTO.setToken(ContextoToken.obterCarlosEduardo());
		
		NivelResultadoDTO resultadoEdicaoDTO = executarPost(criarUrlNivelEditar(),
				nivelSolicitacaoEdicaoDTO, NivelResultadoDTO.class);

		Assert.assertFalse(resultadoEdicaoDTO.sucesso());
		Assert.assertTrue(resultadoEdicaoDTO.obterMensagens().contains("Insira um nível."));
	}
		
	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_obtem_listagem_permissao_com_sucesso() throws RemoteException {
		PermissaoResultadoDTO resultadoDTO = executarGet(criarUrlPermissaoListar(),
				PermissaoResultadoDTO.class);

		Assert.assertTrue(resultadoDTO.sucesso());
		Assert.assertEquals(resultadoDTO.getPermissoesDto().size(), 6);
	}

	private String criarUrlPermissaoListar() {
		return obterUrlBase(ContratoRest.URL_PERMISSAO_LISTAR);
	}
	
	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_obtem_permissao_com_sucesso() throws RemoteException {
		PermissaoResultadoDTO resultadoDTO = executarGet(criarUrlPermissaoObter(ContextoToken.obterCarlosEduardo()),
				PermissaoResultadoDTO.class);

		Assert.assertTrue(resultadoDTO.sucesso());
		Assert.assertEquals(resultadoDTO.getPermissoesDto().size(), 5);
	}
	
	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_obtem_listagem_turno_com_sucesso() throws RemoteException {
		TurnoResultadoDTO resultadoDTO = executarGet(criarUrlTurnoListar(),
				TurnoResultadoDTO.class);

		Assert.assertTrue(resultadoDTO.sucesso());
		Assert.assertEquals(resultadoDTO.getTurnosDto().size(), 2);
	}

	
	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_obtem_listagem_estado_com_sucesso() throws RemoteException {
		EstadoResultadoDTO resultadoDTO = executarGet(criarUrlEstadoListar(),
				EstadoResultadoDTO.class);

		Assert.assertTrue(resultadoDTO.sucesso());
		Assert.assertEquals(resultadoDTO.getEstadosDto().size(), 2);
	}

	
	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_obtem_listagem_municipio_com_sucesso() throws RemoteException {
		MunicipioResultadoDTO resultadoDTO = executarGet(criarUrlMunicipioObter("26"),
				MunicipioResultadoDTO.class);

		Assert.assertTrue(resultadoDTO.sucesso());
		Assert.assertEquals(resultadoDTO.getMunicipiosDto().size(), 2);
	}
	
	
	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_obtem_listagem_bairro_com_sucesso() throws RemoteException {
		BairroResultadoDTO resultadoDTO = executarGet(criarUrlBairroObter("8966"),
				BairroResultadoDTO.class);

		Assert.assertTrue(resultadoDTO.sucesso());
		Assert.assertEquals(resultadoDTO.getBairrosDto().size(), 3);
	}
	
	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_edita_campanha_com_sucesso() throws RemoteException {
		CampanhaDTO campanhaDto = ContextoCampanha.fabricarDto();
		
		CampanhaSolicitacaoEdicaoDTO campanhaSolicitacaoEdicaoDTO = new CampanhaSolicitacaoEdicaoDTO();
		campanhaSolicitacaoEdicaoDTO.setCampanhaDto(campanhaDto);
		campanhaSolicitacaoEdicaoDTO.setToken(ContextoToken.obterCarlosEduardo());
				
		CampanhaResultadoDTO resultadoEdicaoDTO = executarPost(criarUrlCampanhaEditar(),
				campanhaSolicitacaoEdicaoDTO, CampanhaResultadoDTO.class);

		Assert.assertTrue(resultadoEdicaoDTO.sucesso());
	}

	
	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_inclui_campanha_acesso_com_sucesso() throws RemoteException {
		CampanhaDTO campanhaDto = ContextoCampanha.fabricarDto();
		campanhaDto.setId(null);
		campanhaDto.setNome("novo nome");
		
		CampanhaSolicitacaoEdicaoDTO campanhaSolicitacaoEdicaoDTO = new CampanhaSolicitacaoEdicaoDTO();
		campanhaSolicitacaoEdicaoDTO.setCampanhaDto(campanhaDto);
		campanhaSolicitacaoEdicaoDTO.setToken(ContextoToken.obterCarlosEduardo());
		
		CampanhaResultadoDTO resultadoEdicaoDTO = executarPost(criarUrlCampanhaEditar(),
				campanhaSolicitacaoEdicaoDTO, CampanhaResultadoDTO.class);

		Assert.assertTrue(resultadoEdicaoDTO.sucesso());
	}
	
	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_nao_edita_campanha_com_dados_invalidos() throws RemoteException {
		CampanhaDTO campanhaDto = ContextoCampanha.fabricarDto();
		campanhaDto.setNome(null);
		
		CampanhaSolicitacaoEdicaoDTO campanhaSolicitacaoEdicaoDTO = new CampanhaSolicitacaoEdicaoDTO();
		campanhaSolicitacaoEdicaoDTO.setCampanhaDto(campanhaDto);
		campanhaSolicitacaoEdicaoDTO.setToken(ContextoToken.obterCarlosEduardo());
		
		CampanhaResultadoDTO resultadoEdicaoDTO = executarPost(criarUrlCampanhaEditar(),
				campanhaSolicitacaoEdicaoDTO, CampanhaResultadoDTO.class);

		Assert.assertFalse(resultadoEdicaoDTO.sucesso());
		Assert.assertTrue(resultadoEdicaoDTO.obterMensagens().contains("Insira um nome válido."));
	}
	
	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_nao_edita_campanha_sem_autorizacao() throws RemoteException {
		CampanhaDTO campanhaDto = ContextoCampanha.fabricarDto();
		
		CampanhaSolicitacaoEdicaoDTO campanhaSolicitacaoEdicaoDTO = new CampanhaSolicitacaoEdicaoDTO();
		campanhaSolicitacaoEdicaoDTO.setCampanhaDto(campanhaDto);
		campanhaSolicitacaoEdicaoDTO.setToken(ContextoToken.obterPauloAugusto());
		
		
		CampanhaResultadoDTO resultadoEdicaoDTO = executarPost(criarUrlCampanhaEditar(),
				campanhaSolicitacaoEdicaoDTO, CampanhaResultadoDTO.class);

		Assert.assertFalse(resultadoEdicaoDTO.sucesso());
		Assert.assertTrue(resultadoEdicaoDTO.obterMensagens().contains("Não autorizado."));
	}
	
	
	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_obtem_listagem_campanha_com_sucesso() throws RemoteException {
		CampanhaResultadoDTO resultadoDTO = executarGet(obterUrlBase(ContratoRest.URL_CAMPANHA_LISTAR),
				CampanhaResultadoDTO.class);

		Assert.assertTrue(resultadoDTO.sucesso());
		Assert.assertEquals(resultadoDTO.getCampanhasDto().size(), 2);
	}
	
	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_obtem_campanha_com_sucesso() throws RemoteException {
		Long id = new Long(1000);
		CampanhaResultadoDTO resultadoDTO = executarGet(criarUrlCampanhaObter(id),
				CampanhaResultadoDTO.class);

		Assert.assertTrue(resultadoDTO.sucesso());
		Assert.assertEquals(resultadoDTO.obterCampanhaDto().getId(), id);
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_criterio_valida_com_sucesso() throws RemoteException {
		CriterioResultadoDTO resultadoDTO = executarPost(criarUrlCriterioValidar(),
				ContextoCriterio.fabricarDto(), CriterioResultadoDTO.class);

		Assert.assertTrue(resultadoDTO.sucesso());
	}
	
	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_criterio_valida_com_erro() throws RemoteException {
		CriterioDTO criterioDto = ContextoCriterio.fabricarDto();
		criterioDto.setNome("");
		CriterioResultadoDTO resultadoDTO = executarPost(criarUrlCriterioValidar(),
				criterioDto, CriterioResultadoDTO.class);

		Assert.assertFalse(resultadoDTO.sucesso());
		Assert.assertTrue(resultadoDTO.obterMensagens().contains("Insira um nome válido."));
	}
	
	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_alocacao_operador_valida_com_sucesso() throws RemoteException {
		AlocacaoOperadorResultadoDTO resultadoDTO = executarPost(
				criarUrlAlocacaoOperadorValidar(), ContextoAlocacaoOperador.criarDto(),
				AlocacaoOperadorResultadoDTO.class);

		Assert.assertTrue(resultadoDTO.sucesso());
	}
	
	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_alocacao_operador_valida_com_erro() throws RemoteException {
		AlocacaoOperadorDTO alocacaoOperadorDto = ContextoAlocacaoOperador.criarDto();
		alocacaoOperadorDto.setContaAcessoDto(null);
		AlocacaoOperadorResultadoDTO resultadoDTO = executarPost(criarUrlAlocacaoOperadorValidar(),
				alocacaoOperadorDto, AlocacaoOperadorResultadoDTO.class);

		Assert.assertFalse(resultadoDTO.sucesso());
		Assert.assertTrue(resultadoDTO.obterMensagens().contains("Insira um operador."));
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_obtem_listagem_ramais_com_sucesso() throws RemoteException {
		RamalResultadoDTO resultadoDTO = executarGet(criarUrlRamalListar(),
				RamalResultadoDTO.class);

		Assert.assertTrue(resultadoDTO.sucesso());
		Assert.assertEquals(resultadoDTO.getRamaisDto().size(), 2);
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_nao_obtem_distribuicao_contato_inexistente() throws RemoteException {
		Registro.obterDistribuidorContato().distribuirContatos();
		DistribuicaoContatoResultadoDTO resultadoDTO = executarGet(
				criarUrlDistribuicaoContatoObter(ContextoToken.obterCarlosEduardo()),
				DistribuicaoContatoResultadoDTO.class);

		Assert.assertFalse(resultadoDTO.sucesso());
		Assert.assertTrue(resultadoDTO.getMensagem().contains("Não existem contatos disponíveis."));
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_obtem_distribuicao_contato_com_sucesso() throws RemoteException {
		Registro.obterDistribuidorContato().limparDistribuicoesSelecionadas();
		Registro.obterDistribuidorContato().distribuirContatos();
		
		DistribuicaoContatoResultadoDTO resultadoDTO = executarGet(
				criarUrlDistribuicaoContatoObter(ContextoToken.obterPauloAugusto()),
				DistribuicaoContatoResultadoDTO.class);

		Assert.assertTrue(resultadoDTO.sucesso());
		Assert.assertNotNull(resultadoDTO.obterDistribuicaoContatoDto());
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_obtem_status_ramal_com_sucesso() throws RemoteException {
		wireMockServer.resetAll();
		gerarStubDeAutenticacaoDeTelefonia();
		gerarStubDeStatusDeRamalDaTelefonia();
		wireMockServer.start();
		    
		String resultadoDTO = executarGet(
				criarUrlStatusRamalObter(ContextoToken.obterPauloAugusto()),
				String.class);

		wireMockServer.stop();
		Assert.assertEquals(resultadoDTO, "Ramal indisponível");
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_telefonia_liga_com_sucesso() throws RemoteException {
		wireMockServer.resetAll();
		gerarStubDeAutenticacaoDeTelefonia();
		gerarStubDeLigacaoDeTelefonia();
		wireMockServer.start();
		
		TelefoneDTO telefoneDto = new TelefoneDTO();
		telefoneDto.setDdd("11");
		telefoneDto.setTelefone("957878870");
		LigacaoDTO ligacaoDTO = new LigacaoDTO();
		ligacaoDTO.setToken(ContextoToken.obterPauloAugusto());
		ligacaoDTO.setTelefoneDto(telefoneDto);
		LigacaoResultadoDTO resultadoDTO = executarPost(criarUrlLigar(), ligacaoDTO, LigacaoResultadoDTO.class);

		wireMockServer.stop();
		Assert.assertTrue(resultadoDTO.sucesso());
	} 
	
	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_efetua_doacao_com_sucesso() throws RemoteException {
		DoacaoDTO doacaoDto = ContextoDoacao.fabricarDto();
		doacaoDto.setId(null);
		
		DoacaoSolicitacaoEdicaoDTO doacaoSolicitacaoEdicaoDTO = new DoacaoSolicitacaoEdicaoDTO();
		doacaoSolicitacaoEdicaoDTO.setDoacaoDto(doacaoDto);
		doacaoSolicitacaoEdicaoDTO.setToken(ContextoToken.obterPauloAugusto());
				
		DoacaoResultadoDTO resultadoEdicaoDTO = executarPost(criarUrlDoacaoEditar(),
				doacaoSolicitacaoEdicaoDTO, DoacaoResultadoDTO.class);

		Assert.assertTrue(resultadoEdicaoDTO.sucesso());
	}
	
	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_nao_efetua_doacao_sem_autorizacao() throws RemoteException {
		DoacaoDTO doacaoDto = ContextoDoacao.fabricarDto();
		doacaoDto.setId(null);
		
		DoacaoSolicitacaoEdicaoDTO doacaoSolicitacaoEdicaoDTO = new DoacaoSolicitacaoEdicaoDTO();
		doacaoSolicitacaoEdicaoDTO.setDoacaoDto(doacaoDto);
		doacaoSolicitacaoEdicaoDTO.setToken(ContextoToken.obterCarlosEduardo());
				
		DoacaoResultadoDTO resultadoEdicaoDTO = executarPost(criarUrlDoacaoEditar(),
				doacaoSolicitacaoEdicaoDTO, DoacaoResultadoDTO.class);

		Assert.assertFalse(resultadoEdicaoDTO.sucesso());
		Assert.assertTrue(resultadoEdicaoDTO.obterMensagens().contains("Não autorizado."));
	}	
	
	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_obtem_listagem_metodo_com_sucesso() throws RemoteException {
		MetodoResultadoDTO resultadoDTO = executarGet(criarUrlMetodoListar(),
				MetodoResultadoDTO.class);

		Assert.assertTrue(resultadoDTO.sucesso());
		Assert.assertEquals(resultadoDTO.getMetodosDto().size(), 1);
	}



	//servico_inclui_doacao_acesso_com_sucesso()
	//servico_nao_edita_doacao_com_dados_invalidos()
	
}

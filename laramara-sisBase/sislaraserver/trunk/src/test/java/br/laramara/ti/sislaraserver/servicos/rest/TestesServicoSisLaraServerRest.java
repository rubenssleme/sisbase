package br.laramara.ti.sislaraserver.servicos.rest;

import java.rmi.RemoteException;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.dtos.seguranca.CredencialExternaDTO;
import br.laramara.ti.sislaracommons.dtos.seguranca.ResultadoAutenticacaoDTO;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.TestesIntegracaoAbstrato;
import br.laramara.ti.sislaraserver.fabricas.ContextoCredencialExterna;
import br.laramara.ti.sislaraserver.utilitarios.Configuracao;
import br.laramara.ti.sislaraserver.utilitarios.Registro;

public class TestesServicoSisLaraServerRest extends TestesIntegracaoAbstrato {
	private static final String URL_BASE = "http://"
			+ new Configuracao().obterConfiguracao(Configuracao.SERVIDOR_ENDERECO_IP) + ":"
			+ new Configuracao().obterConfiguracao(Configuracao.SERVIDOR_PORTA);
	private static final String URL_INSCRICAO_AUTENTICACAO_USUARIO_EXTERNO = URL_BASE + ServicoSisLaraServerRest.URL_AUTENTICACAO_USUARIO_EXTERNO;

	public TestesServicoSisLaraServerRest() {
		super("DadosTestesServicoSisLaraServerRest.xml");
		Registro.inicializarContexto();
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_rest_autentica_usuario_externo_com_sucesso() throws RemoteException {
		HttpEntity<CredencialExternaDTO> credencialExternaDTO = criarEntidadeJson(
				ContextoCredencialExterna.construirCredencialExternaDTO());

		ResponseEntity<ResultadoAutenticacaoDTO> resposta = executarPost(URL_INSCRICAO_AUTENTICACAO_USUARIO_EXTERNO,
				credencialExternaDTO, ResultadoAutenticacaoDTO.class);
		ResultadoAutenticacaoDTO resultadoAutenticacaoDTO = resposta.getBody();

		Assert.assertTrue(resultadoAutenticacaoDTO.sucesso());
		Assert.assertFalse(resultadoAutenticacaoDTO.getToken().getToken().isEmpty());
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_rest_autentica_usuario_externo_sem_sucesso() throws RemoteException {
		String erro = "Usuário ou senha inválido. Tente novamente.";
		HttpEntity<CredencialExternaDTO> credencialExternaDTO = criarEntidadeJson(
				ContextoCredencialExterna.construirCredencialExternaDTONaoExistente());
		
		
		ResponseEntity<ResultadoAutenticacaoDTO> resposta = executarPost(URL_INSCRICAO_AUTENTICACAO_USUARIO_EXTERNO,
				credencialExternaDTO, ResultadoAutenticacaoDTO.class);
		ResultadoAutenticacaoDTO resultadoAutenticacaoDTO = resposta.getBody();

		Assert.assertFalse(resultadoAutenticacaoDTO.sucesso());
		Assert.assertNull(resultadoAutenticacaoDTO.getToken());
		Assert.assertTrue(resultadoAutenticacaoDTO.getMensagem().contains(erro));
	}

}
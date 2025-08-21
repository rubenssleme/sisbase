package br.laramara.ti.sislaraserver.dominio.seguranca;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.fabricas.ContextoContaAcesso;
import br.laramara.ti.sislaraserver.repositorios.RepositorioContaAcesso;

public class TestesAutenticador {

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void autenticador_valida_conta_acesso_e_retorna_token()
			throws ContaAcessoNaoAutenticadoException {

		Autenticador autenticador = obterAutenticador();
		Credencial credencial = new Credencial("paulo", "1234");

		String token = autenticador.autentica(credencial);

		Assert.assertNotNull(token, "Um token válido deveria ser retornado.");
	}

	@Test(groups = { TiposDeTeste.UNITARIO }, expectedExceptions = ContaAcessoNaoAutenticadoException.class)
	public void autenticador_lanca_excecao_ao_validar_conta_acesso_inexistente()
			throws ContaAcessoNaoAutenticadoException {

		Autenticador autenticador = new Autenticador(
				obterRepositorioContaAcessoStubSemContaAcesso(),
				new CoordenadorEdicaoGeral());
		Credencial credencial = new Credencial("paulo", "1234");

		autenticador.autentica(credencial);
	}
	
	private RepositorioContaAcesso obterRepositorioContaAcessoStubComUmaContaAcesso() {
		RepositorioContaAcesso stubRepositorioContaAcesso = mock(RepositorioContaAcesso.class);
		when(stubRepositorioContaAcesso.obterContaAcesso(any(Credencial.class)))
				.thenReturn(ContextoContaAcesso.fabricarComTodosOsDados());
		return stubRepositorioContaAcesso;
	}

	private RepositorioContaAcesso obterRepositorioContaAcessoStubSemContaAcesso() {
		RepositorioContaAcesso stubRepositorioContaAcesso = mock(RepositorioContaAcesso.class);
		when(stubRepositorioContaAcesso.obterContaAcesso(any(Credencial.class)))
				.thenReturn(null);
		return stubRepositorioContaAcesso;
	}

	private Autenticador obterAutenticador() {
		return new Autenticador(
				obterRepositorioContaAcessoStubComUmaContaAcesso(),
				new CoordenadorEdicaoGeral());
	}
}

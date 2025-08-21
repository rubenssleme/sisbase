package br.laramara.sistelemarketingserver.dominio.seguranca;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.sistelemarketingcommons.utilitarios.TiposDeTeste;
import br.laramara.sistelemarketingserver.TestesIntegracaoAbstrato;
import br.laramara.sistelemarketingserver.dominio.telefonia.Ramal;
import br.laramara.sistelemarketingserver.utilitarios.Registro;


public class TestesAutenticador extends TestesIntegracaoAbstrato {
	
	protected TestesAutenticador() {
		super("DadosTestesAutenticador.xml");
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void autenticador_valida_conta_acesso_e_retorna_token()
			throws ContaAcessoNaoAutenticadoException {

		Autenticador autenticador = Registro.obterAutenticador();
		Credencial credencial = new Credencial("paulo.bandeira", "1234", Ramal.RAMAL_6435);

		ContaAcesso contaAcesso = autenticador.autentica(credencial);

		Assert.assertNotNull(contaAcesso.getToken(), "Um token válido deveria ser retornado.");
	}

	@Test(groups = { TiposDeTeste.UNITARIO }, expectedExceptions = ContaAcessoNaoAutenticadoException.class)
	public void autenticador_lanca_excecao_ao_validar_conta_acesso_inexistente()
			throws ContaAcessoNaoAutenticadoException {

		Autenticador autenticador = Registro.obterAutenticador();
		Credencial credencial = new Credencial("paulo.bandeira", "4321", Ramal.RAMAL_6435);

		autenticador.autentica(credencial);
	}
	
}

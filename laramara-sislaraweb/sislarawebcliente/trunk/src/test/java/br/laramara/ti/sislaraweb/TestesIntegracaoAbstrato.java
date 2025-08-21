package br.laramara.ti.sislaraweb;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;

import br.laramara.ti.sislaraweb.utilitarios.DriverUtils;

public abstract class TestesIntegracaoAbstrato {

	private String url;
	
	public TestesIntegracaoAbstrato(String url) {
		this.url = url;
	}
	
	@BeforeMethod
	public void inicializarUrl() {
		DriverUtils.inicializarFirefoxDriver();
		limparCookies();
		redirecionarParaPaginaInicial();
	}

	@AfterClass
	public void encerrarUrl() {
		DriverUtils.finalizarDriver();
	}

	protected void redirecionarParaPaginaInicial() {
		DriverUtils.ir(url);
	}

	protected void limparCookies() {
		DriverUtils.getDriver().manage().deleteAllCookies();
	}
}
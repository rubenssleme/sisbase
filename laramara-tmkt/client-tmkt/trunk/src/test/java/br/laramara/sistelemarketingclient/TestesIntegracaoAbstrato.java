package br.laramara.sistelemarketingclient;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;

import br.laramara.sistelemarketingclient.utils.DriverUtils;
import br.laramara.sistelemarketingcommons.utilitarios.TiposDeTeste;

public class TestesIntegracaoAbstrato {

	@AfterClass(groups = { TiposDeTeste.CONTA_ACESSO, TiposDeTeste.NIVEL_PERMISSAO, TiposDeTeste.CAMPANHA })
	public void fecharNavegaro() {
		DriverUtils.fecharNavegador();
	}
	
	@AfterMethod(groups = { TiposDeTeste.CONTA_ACESSO, TiposDeTeste.NIVEL_PERMISSAO, TiposDeTeste.CAMPANHA })
	public void irPaginaEmBranco() {
		DriverUtils.irPaginaEmBranco();
	}
}

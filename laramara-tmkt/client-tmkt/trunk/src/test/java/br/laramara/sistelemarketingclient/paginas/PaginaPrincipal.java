package br.laramara.sistelemarketingclient.paginas;

import br.laramara.sistelemarketingclient.utils.DriverUtils;

public class PaginaPrincipal extends Pagina{

	private static final String MENU_ADMINISTRACAO_USUARIOS = "Administração de usuários";
	private static final String MENU_SUPERVISAO = "Supervisão";

	public PaginaPrincipal() {
		DriverUtils.ir(URL_BASE + "/principal.xhtml", false);
	}

	public void acessarMenuNiveisPermissoes() {
		DriverUtils.clicarNoLink(MENU_ADMINISTRACAO_USUARIOS, "Níveis/Permissões");
	}

	public void acessarMenuUsuarios() {
		DriverUtils.clicarNoLink(MENU_ADMINISTRACAO_USUARIOS, "Usuários");
	}

	public void sairDoSistema() {
		DriverUtils.clicarNoBotao("sair");
	}

	public void acessarMenuCampanhas() {
		DriverUtils.clicarNoLink(MENU_SUPERVISAO, "Campanhas");
	}
}

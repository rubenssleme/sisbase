package br.laramara.sistelemarketingclient.paginas;

import br.laramara.sistelemarketingclient.utils.DriverUtils;

public class PaginaNivelPermissaoGerenciamento extends Pagina{

	public static final String URL_EDICAO_NIVEL_PERMISSAO_GERENTE = "/nivelEdicao.xhtml?id=2";
	
	public PaginaNivelPermissaoGerenciamento(boolean forcado) {
		DriverUtils.ir(URL_BASE + "/nivelGerenciamento.xhtml", forcado);
	}
	
	public void editarGerente() {
		DriverUtils.clicarNoLinkComUrl(URL_MOBILEASY + URL_EDICAO_NIVEL_PERMISSAO_GERENTE);
	}

	public void novo() {
		DriverUtils.clicarNoBotao(ID_BOTAO_NOVO);
	}
}

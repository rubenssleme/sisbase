package br.laramara.sistelemarketingclient.paginas;

import br.laramara.sistelemarketingclient.utils.DriverUtils;

public class PaginaContaAcessoGerenciamento extends Pagina{

	public static final String URL_EDICAO_CONTA_ACESSO_CARLOS = "/contaAcessoEdicao.xhtml?id=2000";

	public PaginaContaAcessoGerenciamento(boolean forcado) {
		DriverUtils.ir(URL_BASE + "/contaAcessoGerenciamento.xhtml", forcado);
	}
	
	public void editarCarlos() {
		DriverUtils.clicarNoLinkComUrl(URL_MOBILEASY + URL_EDICAO_CONTA_ACESSO_CARLOS);
	}

	public void novo() {
		DriverUtils.clicarNoBotao(ID_BOTAO_NOVO);
	}
}

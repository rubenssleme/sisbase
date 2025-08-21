package br.laramara.sistelemarketingclient.paginas;

import br.laramara.sistelemarketingclient.utils.DriverUtils;

public class PaginaCampanhaGerenciamento extends Pagina{

	public static final String URL_EDICAO_CAMPANHA_A = "/campanhaEdicao.xhtml?id=2000";

	public PaginaCampanhaGerenciamento(boolean forcado) {
		DriverUtils.ir(URL_BASE + "/campanhaGerenciamento.xhtml", forcado);
	}
	
	public void editarCampanhaA() {
		DriverUtils.clicarNoLinkComUrl(URL_MOBILEASY + URL_EDICAO_CAMPANHA_A);
	}

	public void novo() {
		DriverUtils.clicarNoBotao(ID_BOTAO_NOVO);
	}
}

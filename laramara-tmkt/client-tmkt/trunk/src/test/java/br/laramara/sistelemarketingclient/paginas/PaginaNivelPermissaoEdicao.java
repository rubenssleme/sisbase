package br.laramara.sistelemarketingclient.paginas;

import br.laramara.sistelemarketingclient.utils.ContextoGenerico;
import br.laramara.sistelemarketingclient.utils.DriverUtils;

public class PaginaNivelPermissaoEdicao extends Pagina {

	public static final String URL_EDICAO_NIVEL_PERMISSAO_NOVA = "/nivelEdicao.xhtml";

	public static final String NIVEL = "nivel";

	public PaginaNivelPermissaoEdicao(String url, boolean forcado) {
		DriverUtils.ir(URL_BASE + url, forcado);
	}

	public void salva() {
		DriverUtils.clicarNoBotao("salvar");
	}

	public void apagarDescricao() {
		DriverUtils.limparTextoDoElemento(NIVEL);
	}

	public void incluirDescricaoInvalida() {
		DriverUtils.incluirTextoNoElemento(ContextoGenerico.obterGrande(), NIVEL);
	}
}

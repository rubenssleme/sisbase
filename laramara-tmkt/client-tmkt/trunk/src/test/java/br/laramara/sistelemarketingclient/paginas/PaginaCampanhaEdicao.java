package br.laramara.sistelemarketingclient.paginas;

import br.laramara.sistelemarketingclient.utils.ContextoGenerico;
import br.laramara.sistelemarketingclient.utils.DriverUtils;

public class PaginaCampanhaEdicao extends Pagina {
	
	public static final String URL_EDICAO_NOVA = "/campanhaEdicao.xhtml";

	private static final String NOME = "nome";
	private static final String DATA_INICIO = "dataInicio";
	
	public PaginaCampanhaEdicao(String url, boolean forcado) {
		DriverUtils.ir(URL_BASE + url, forcado);
	}
	
	public void salva() {
		DriverUtils.clicarNoBotao("salvar");
	}

	public void apagarNome() {
		DriverUtils.limparTextoDoElemento(NOME);
	}

	public void incluirNomeInvalida() {
		DriverUtils.incluirTextoNoElemento(ContextoGenerico.obterGrande(), NOME);
	}

	public void apagarDataInicio() {
		DriverUtils.limparTextoDoElemento(DATA_INICIO);
	}
}

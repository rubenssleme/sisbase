package br.laramara.sistelemarketingclient.paginas;

import br.laramara.sistelemarketingclient.utils.ContextoGenerico;
import br.laramara.sistelemarketingclient.utils.DriverUtils;

public class PaginaContaAcessoEdicao extends Pagina {

	public static final String URL_EDICAO_CONTA_ACESSO_NOVO = "/contaAcessoEdicao.xhtml";
	
	public static final String NOME = "nome";
	public static final String LOGIN = "login";
	public static final String NIVEL = "nivel";
	
	public PaginaContaAcessoEdicao(String url, boolean forcado) {
		DriverUtils.ir(URL_BASE + url, forcado);
	}

	public void salva() {
		DriverUtils.clicarNoBotao("salvar");
	}

	public void apagarLogin() {
		DriverUtils.limparTextoDoElemento(LOGIN);
	}

	public void incluirLoginInvalida() {
		DriverUtils.incluirTextoNoElemento(ContextoGenerico.obterGrande(), LOGIN);
	}

	public void apagarNome() {
		DriverUtils.limparTextoDoElemento(NOME);
	}

	public void deselecionarNivel() {
		DriverUtils.deselecionarItem(NIVEL);
	}
}

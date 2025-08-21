package br.laramara.sistelemarketingclient.paginas;

import br.laramara.sistelemarketingclient.utils.DriverUtils;

public class PaginaLogin extends Pagina {

	private static final String ID_CAMPO_LOGIN = "login";
	private static final String ID_CAMPO_SENHA = "senha";
	
	public final static String USUARIO_GERENTE = "carlos";
	public final static String USUARIO_OPERADOR = "paulo";
	public final static String SENHA_PADRAO = "1234";
	public final static String SENHA_ERRADA = "4321";
	
	public PaginaLogin() {
		DriverUtils.ir(URL_BASE, false);
	}

	public void incluirLoginGerente() {
		DriverUtils.incluirTextoNoElemento(USUARIO_GERENTE, ID_CAMPO_LOGIN);
	}
	
	public void incluirLoginOperador() {
		DriverUtils.incluirTextoNoElemento(USUARIO_OPERADOR, ID_CAMPO_LOGIN);
	}

	public void incluirSenha1234() {
		DriverUtils.incluirTextoNoElemento(SENHA_PADRAO, ID_CAMPO_SENHA);
	}

	public void incluirSenha4321() {
		DriverUtils.incluirTextoNoElemento(SENHA_ERRADA, ID_CAMPO_SENHA);
	}

	public void acessar() {
		DriverUtils.clicarNoBotao("acessar");
	}

	public boolean estaNaPaginaPrincipal() {
		return DriverUtils.tituloContemTexto(TITULO_TELA_PRINCIPAL);
	}
}

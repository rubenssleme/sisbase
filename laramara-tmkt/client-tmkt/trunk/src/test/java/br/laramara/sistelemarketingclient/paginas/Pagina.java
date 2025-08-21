package br.laramara.sistelemarketingclient.paginas;

import java.util.List;

import br.laramara.sistelemarketingclient.utils.DriverUtils;

public class Pagina {
	
	protected static final String URL_LOCAL = "http://localhost:8080";
	protected static final String URL_MOBILEASY = "/mobileasy";
	protected static final String URL_BASE = URL_LOCAL + URL_MOBILEASY;
	
	protected static final String ID_BOTAO_NOVO = "novo";
	private static final String ID_CAMPO_MENSAGEM = "mensagem";
	private static final String TITULO_TELA_SEM_AUTORIZACAO = "Sem autorização";
	private static final String TITULO_TELA_LOGIN = "Login";
	protected final static String TITULO_TELA_PRINCIPAL = "Tela inicial";
	private static final String TITULO_TELA_EDICAO_NIVEL_PERMISSAO = "Edição de nível/permissão";
	private static final String TITULO_TELA_EDICAO_CONTA_ACESSO = "Edição de usuário";
	private static final String TITULO_TELA_EDICAO_CAMPANHA = "Edição de campanha";
	
	public void abrirAba(String aba) {
		DriverUtils.clicarNoLinkComUrl("#" + aba);
	}

	public void selecionarOpcao(String opcao, String id) {
		DriverUtils.marcarItemNoCombo(opcao, id);
	}

	public void acionarBotao(String id) {
		DriverUtils.clicarNoBotao(id);
	}
	
	public void incluirValor(String string, String id) {
		DriverUtils.incluirTextoNoElemento(string, id);
	}
	
	public void alterarValor(String string, String id) {
		DriverUtils.alterarTextoNoElemento(string, id);
	}	
	
	public boolean contemValorNaAba(String texto, String aba) {
		abrirAba(aba);
		return contemMensagem(texto, aba);
	}
	
	public boolean contemValorNaAba(String texto, String id, String aba) {
		abrirAba(aba);
		return DriverUtils.contemValor(texto, id);
	}
	
	public boolean contemMensagemNaAba(String texto, String id, String aba) {
		abrirAba(aba);
		return contemMensagem(texto, id);
	}
	
	public boolean contemValor(String texto, String id) {
		return DriverUtils.contemValor(texto, id);
	}
	
	public boolean contemCheckMarcado(String id) {
		return DriverUtils.contemCheckMarcado(id);
	}
	
	public boolean contemItemSelecionadoNoCombo(String texto, String id) {
		return DriverUtils.contemItemSelecionadoNoCombo(texto, id);
	}
	
	public boolean contemMensagem(List<String> textos, String id) {
		return textos.stream().anyMatch(texto -> DriverUtils.contemMensagem(texto, id));
	}
	
	public boolean contemMensagem(String texto, String id) {
		return DriverUtils.contemMensagem(texto, id);
	}
	
	public boolean contemMensagens(String... texto) {
		return DriverUtils.contemMensagem(ID_CAMPO_MENSAGEM, texto);
	}
	
	public boolean contemMensagem(String texto) {
		return DriverUtils.contemMensagem(texto, ID_CAMPO_MENSAGEM);
	}
	
	public boolean estaNaPaginaSemAutorizacao() {
		return DriverUtils.tituloContemTexto(TITULO_TELA_SEM_AUTORIZACAO);
	}
	
	public boolean estaNaPaginaLogin() {
		return DriverUtils.tituloContemTexto(TITULO_TELA_LOGIN);
	}
	
	public boolean estaNaPaginaDeEdicaoNivelPermissao() {
		return DriverUtils.tituloContemTexto(TITULO_TELA_EDICAO_NIVEL_PERMISSAO);
	}
	
	public boolean estaNaPaginaDeEdicaoContaAcesso() {
		return DriverUtils.tituloContemTexto(TITULO_TELA_EDICAO_CONTA_ACESSO);
	}
	
	public boolean estaNaPaginaDeEdicaoCampanha() {
		return DriverUtils.tituloContemTexto(TITULO_TELA_EDICAO_CAMPANHA);
	}
}

package br.laramara.ti.sislaraweb.paginas;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import br.laramara.ti.sislaraweb.utilitarios.DriverUtils;

public class PaginaLogin extends PaginaBase {
	private static final String MENSAGEM_ERRO_EMAIL_INVALIDO = "Erro: Informe um e-mail válido.";
	private static final String MENSAGEM_ERRO_LOGIN_INVALIDO = "Usuário ou senha inválido. Tente novamente.";
	
	@CacheLookup
	@FindBy(id = "email")
	private WebElement email;
	@CacheLookup
	@FindBy(id = "senha")
	private WebElement senha;
	@CacheLookup
	@FindBy(id = "botao")
	private WebElement botaoAcessar;
	@CacheLookup
	@FindBy(id = "botaoRecuperarSenha")
	private WebElement botaoRecuperarSenha;
	@FindBy(id = "botaoCadastrarUsuarioExterno")
	private WebElement botaoCadastrarUsuarioExterno;
	@FindBy(xpath = "//*[@id=\"mensagem\"]/div/ul/li/span")
	private WebElement mensagem;

	private <T> T logar(String email, String senha, Class<T> proximaPagina) {
		DriverUtils.adicionarTexto(this.email, email);
		DriverUtils.adicionarTexto(this.senha, senha);
		DriverUtils.clicar(botaoAcessar);
		return PageFactory.initElements(DriverUtils.getDriver(), proximaPagina);
	}

	public PaginaInicial logarComoUsuarioExterno(String email, String senha) {
		return logar(email, senha, PaginaInicial.class);
	}

	public PaginaLogin logarComoUsuarioInvalido(String email, String senha) {
		return logar(email, senha, PaginaLogin.class);
	}

	public PaginaSolicitacaoRecuperacaoSenha abrirPaginaSolicitacaoRecuperacaoSenha() {
		return abrir(botaoRecuperarSenha, PaginaSolicitacaoRecuperacaoSenha.class);
	}

	public PaginaCadastroUsuarioExterno abrirPaginaCadastroUsuarioExterno() {
		return abrir(botaoCadastrarUsuarioExterno, PaginaCadastroUsuarioExterno.class);
	}

	public boolean emailInvalido() {
		DriverUtils.esperarElementoTerTexto(mensagem, MENSAGEM_ERRO_EMAIL_INVALIDO);
		return DriverUtils.possuiTagComConteudo(TAG_SPAN, MENSAGEM_ERRO_EMAIL_INVALIDO);
	}

	public boolean loginInvalido() {
		DriverUtils.esperarElementoTerTexto(mensagem, MENSAGEM_ERRO_LOGIN_INVALIDO);
		return DriverUtils.possuiTagComConteudo(TAG_SPAN, MENSAGEM_ERRO_LOGIN_INVALIDO);
	}

}

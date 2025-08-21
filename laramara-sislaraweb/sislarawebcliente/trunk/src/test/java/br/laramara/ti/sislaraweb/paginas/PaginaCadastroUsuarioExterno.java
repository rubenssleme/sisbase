package br.laramara.ti.sislaraweb.paginas;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import br.laramara.ti.sislaraweb.utilitarios.DriverUtils;

public class PaginaCadastroUsuarioExterno extends PaginaBase {
	private static final String TITULO_PAGINA_CADASTRO_DE_USUÁRIO_EXTERNO = "SisLaraWEB - Cadastro de Usuário Externo";
	
	private static final String MENSAGEM_ERRO_CADASTRO_INVALIDO = "Erro: o campo Nome Completo é obrigatório.\n" + 
			"Erro: o campo Telefone Celular é obrigatório.\n" + 
			"Erro: o campo E-mail é obrigatório.";
	
	protected static final String MENSAGEM_SUCESSO_CADASTRO_REALIZADO_COM_SUCESSO = "Cadastro realizado com sucesso. Link de confirmação enviado para o seu e-mail.";
	
	@FindBy(id = "nomeCompleto")
	private WebElement nomeCompleto;
	@FindBy(id = "telefoneCelular")
	private WebElement dddETelefone;
	@FindBy(id = "email")
	private WebElement email;
	@FindBy(id = "autorizoRecebimentoInformativo")
	private WebElement autorizaEnvioInformativos;
	@FindBy(id = "botaoCadastrar")
	private WebElement botaoCadastrar;
	@FindBy(id = "mensagem")
	private WebElement mensagem;

	public void preencherDadosParaCadastro(String nomeCompleto, String telefoneCelular, String email, boolean autorizoRecebimentoInformativo) {
		DriverUtils.adicionarTexto(this.nomeCompleto, nomeCompleto);
		DriverUtils.adicionarTexto(this.dddETelefone, telefoneCelular);
		DriverUtils.adicionarTexto(this.email, email);
		DriverUtils.marcar(this.autorizaEnvioInformativos, autorizoRecebimentoInformativo);
	}
	
	public void cadastrar() {
		DriverUtils.clicar(botaoCadastrar);
	}

	public boolean paginaValida() {
		return paginaValida(TITULO_PAGINA_CADASTRO_DE_USUÁRIO_EXTERNO);
	}

	public boolean sucesso() {
		return sucesso(mensagem, MENSAGEM_SUCESSO_CADASTRO_REALIZADO_COM_SUCESSO);
	}
	
	public boolean invalido() {
		return sucesso(mensagem, MENSAGEM_ERRO_CADASTRO_INVALIDO);
	}
	
}

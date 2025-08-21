package br.laramara.ti.sislaraweb.paginas;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;

import br.laramara.ti.sislaraweb.utilitarios.DriverUtils;

public class PaginaSolicitacaoRecuperacaoSenha extends PaginaBase {
	private static final String MENSAGEM_ERRO_EMAIL_OU_CPF_NAO_ENCONTRADO_OU_INVALIDO = "E-mail ou CPF não encontrado ou inválido.";
	
	private static final String MENSAGEM_SUCESSO_LINK_ALTERACAO_ENVIADO_PARA_O_EMAIL_COM_SUCESSO = "Link de alteração de senha enviado para o email com sucesso.";
	
	@CacheLookup
	@FindBy(id = "email")
	private WebElement email;
	@CacheLookup
	@FindBy(id = "botaoSolicitar")
	private WebElement botaoSolicitar;
	@FindBy(xpath = "//*[@id=\"mensagem\"]/div/ul/li/span")
	private WebElement mensagem;

	public void solicitarRecuperacaoSenha() {
		DriverUtils.clicarEEsperar(botaoSolicitar);
	}

	public void preencherEmail(String emailSolicitante) {
		DriverUtils.adicionarTexto(this.email, emailSolicitante);
	}

	public boolean paginaValida() {
		return super.paginaValida("SisLaraWEB - Solicitação de email de recuperação de senha");
	}

	public boolean sucesso() {
		DriverUtils.esperarElementoTerTexto(mensagem, MENSAGEM_SUCESSO_LINK_ALTERACAO_ENVIADO_PARA_O_EMAIL_COM_SUCESSO);
		return DriverUtils.possuiTagComConteudo(TAG_SPAN,
				MENSAGEM_SUCESSO_LINK_ALTERACAO_ENVIADO_PARA_O_EMAIL_COM_SUCESSO);
	}

	public boolean invalido() {
		DriverUtils.esperarElementoTerTexto(mensagem, MENSAGEM_ERRO_EMAIL_OU_CPF_NAO_ENCONTRADO_OU_INVALIDO);
		return DriverUtils.possuiTagComConteudo(TAG_SPAN, MENSAGEM_ERRO_EMAIL_OU_CPF_NAO_ENCONTRADO_OU_INVALIDO);
	}

}

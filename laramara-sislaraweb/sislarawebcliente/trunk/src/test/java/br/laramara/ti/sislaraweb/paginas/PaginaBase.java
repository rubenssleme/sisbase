package br.laramara.ti.sislaraweb.paginas;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import br.laramara.ti.sislaraweb.utilitarios.DriverUtils;

public abstract class PaginaBase {
	protected final String TAG_TR = "tr";
	protected final String TAG_DIV = "div";
	protected final String TAG_SPAN = "span";
	protected final String TAG_TAB_INDEX = "tabindex";
	protected final String TAG_EDICAO_FORM_DIALOG = "edicaoFormDialog";

	public PaginaBase() {
		PageFactory.initElements(DriverUtils.getDriver(), this);		
	}

	protected boolean paginaValida(String titulo) {
		DriverUtils.esperarTituloConterTexto(titulo);
		return tituloCorreto(titulo);
	}

	private boolean tituloCorreto(String titulo) {
		return DriverUtils.getDriver().getTitle().equals(titulo);
	}

	protected boolean sucesso(WebElement elemento, String informacaoAlterada) {
		DriverUtils.esperarElementoTerTexto(elemento, informacaoAlterada);
		String texto = DriverUtils.obterTexto(elemento);
		return texto.equals(informacaoAlterada);
	}

	protected <T> T abrir(WebElement botao, Class<T> pagina) {
		DriverUtils.habilitarJavascript(true);
		DriverUtils.clicar(botao);
		return PageFactory.initElements(DriverUtils.getDriver(), pagina);
	}

}
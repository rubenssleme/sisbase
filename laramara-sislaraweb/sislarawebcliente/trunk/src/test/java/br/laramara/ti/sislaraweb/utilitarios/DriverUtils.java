package br.laramara.ti.sislaraweb.utilitarios;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DriverUtils {
	private static final String PROPRIEDADE_GECKO_DRIVER = "webdriver.gecko.driver";
	private static final String CAMINHO_GECKODRIVER = "src/test/resources/geckodriver.exe";

	private static final String MENU_SELECAO_OPCAO_INVALIDA = "Selecione";
	private static final int TEMPO_ESPERA_EM_SEGUNDOS = 15;

	private static WebDriver driver;
	
	public static WebDriver getDriver() {
		return driver;
	}
	
	public static boolean possuiTagComConteudo(String tag, String conteudo) {
		boolean resultado = false;
	
		for (WebElement elemento : obterElementosPorTag(tag)) {
			if (obterTexto(elemento).contains(conteudo)) {
				resultado = true;
				break;
			}
		}

		return resultado;
	}

	private static List<WebElement> obterElementosPor(By by) {
		try {
			return driver.findElements(by);
		} catch (StaleElementReferenceException e) {
			return obterElementosPor(by);
		}
	}

	public static List<WebElement> obterElementosPorTag(String tag) {
		return obterElementosPor(By.tagName(tag));
	}

	public static void selecionarOpcao(WebElement menu, String texto) {
		try {
			new Select(menu).selectByVisibleText(texto.isEmpty() ? MENU_SELECAO_OPCAO_INVALIDA : texto);
		} catch (StaleElementReferenceException e) {
			selecionarOpcao(menu, texto);
		}
	}
	
	public static void adicionarTexto(WebElement elemento, String texto) {
		try {
			elemento.click();
			elemento.clear();
			elemento.sendKeys(texto);
		} catch (StaleElementReferenceException e) {
			adicionarTexto(elemento, texto);
		}
	}
	
	public static void clicarEEsperar(WebElement elemento) {
		clicar(elemento);
		esperar(550);
	}

	public static void clicar(WebElement elemento) {
		elemento.click();
	}

	public static void esperar(long milissegundos) {
		try {
			Thread.sleep(milissegundos);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void habilitarJavascript(boolean ativado) {
		if (driver instanceof HtmlUnitDriver) {
			((HtmlUnitDriver) driver).setJavascriptEnabled(ativado);
		}
	}

	public static String obterTexto(WebElement elemento) {
		try {
			return elemento.getText();
		} catch (StaleElementReferenceException e) {
			return obterTexto(elemento);
		}
	}

	private static WebElement esperarElementoAparecer(WebElement elemento) {
		return obterWebDriverWait()
				.until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOf(elemento)));
	}

	public static WebElement esperarElementoAparecerPorElemento(WebElement elemento) {
		return esperarElementoAparecer(elemento);
	}

	public static boolean esperarElementoTerTexto(WebElement elemento, String texto) {
		boolean possui = false;
		while (!possui) {
			WebElement elementoObtido = esperarElementoAparecerPorElemento(elemento);
			String textoObtido = obterTexto(elementoObtido);

			possui = textoObtido.equals(texto);
		}

		return possui;
	}

	public static boolean esperarTituloConterTexto(String titulo) {
		return obterWebDriverWait().until(ExpectedConditions.titleIs(titulo));
	}

	public static void marcar(WebElement elemento, boolean marcarElemento) {
		if (marcarElemento) {
			elemento.click();
		}
	}
	
	private static FluentWait<WebDriver> obterWebDriverWait() {
		return new WebDriverWait(driver, TEMPO_ESPERA_EM_SEGUNDOS);
	}

	public static void ir(String url) {
		getDriver().get(url);
	}

	public static void finalizarDriver() {
		if (driver != null) {
			driver.close();
		}
	}

	public static void inicializarFirefoxDriver() {
		if (driver == null) {
			System.setProperty(PROPRIEDADE_GECKO_DRIVER, CAMINHO_GECKODRIVER);
			driver = new FirefoxDriver();
			driver.manage().timeouts().implicitlyWait(TEMPO_ESPERA_EM_SEGUNDOS, TimeUnit.SECONDS);
		}
	}

	public static boolean esperarElementoSerMarcado(WebElement elemento) {
		obterWebDriverWait().until(ExpectedConditions.elementToBeSelected(elemento));
		return esperarElementoAparecer(elemento).isSelected();
	}

	public static boolean elementoEstaVisivel(WebElement elemento) {
		try {
			return elemento.isDisplayed();
		} catch (WebDriverException e) {
			return false;
		}
	}
}
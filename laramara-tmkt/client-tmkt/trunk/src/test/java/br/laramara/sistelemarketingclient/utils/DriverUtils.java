package br.laramara.sistelemarketingclient.utils;

import java.time.Duration;
import java.util.Hashtable;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DriverUtils {
	
	private static final String SELECIONE_UMA_OPCAO = "Selecione uma opção";
	private static final String ATRIBUTO_VALOR = "value";
	private static final String URL_BRANCO = "about:blank";
	private static final int TEMPO_ESPERA_EM_SEGUNDOS = 3;
	private static final int TEMPO_POOLING_EM_SEGUNDOS = 1;
	private static final String PROPRIEDADE_GECKO_DRIVER = "webdriver.gecko.driver";
	private static final String CAMINHO_GECKODRIVER = "src/test/resources/geckodriver.exe";
	
	private static WebDriver driver;
	
	private static Map<String, Boolean> estadoMenu = new Hashtable<>();
	
	private static WebDriver obterWebDriver() {
		if (driver == null) {
			System.setProperty(PROPRIEDADE_GECKO_DRIVER, CAMINHO_GECKODRIVER);
			driver = new FirefoxDriver();
			driver.manage().timeouts().implicitlyWait(TEMPO_ESPERA_EM_SEGUNDOS, TimeUnit.SECONDS);
		}
		return driver;
	}
	
	public static void ir(String urlParaAbrir, boolean forcado) {
		String urlAtual = obterWebDriver().getCurrentUrl();
		if ((!urlParaAbrir.equals(urlAtual) && urlAtual.equals(URL_BRANCO)) || (urlParaAbrir.equals(URL_BRANCO))) {
			obterWebDriver().get(urlParaAbrir);
		}else {
			while(!forcado && !urlParaAbrir.equals(urlAtual)) {
				urlAtual = obterWebDriver().getCurrentUrl();
			}
			obterWebDriver().get(urlParaAbrir);
		}
	}
	
	public static void limparTextoDoElemento(String id) {
		obterElementoPor(By.id(id)).clear();
	}
		
	public static void incluirTextoNoElemento(String texto, String id) {
		executar(() -> obterElementoPor(By.id(id)).sendKeys(texto));
	}
	
	public static void alterarTextoNoElemento(String texto, String id) {
		limparTextoDoElemento(id);
		obterElementoPor(By.id(id)).sendKeys(texto);
	}
	
	private static WebElement obterElementoPor(By by) {
		return obterWebDriver().findElement(by);
	}
	
	private static void clicar(WebElement elemento) {
		elemento.click();
	}

	public static void clicarNoBotao(String id) {
		executar(() -> clicar(obterElementoPor(By.id(id))));
	}
	
	public static void executar(Runnable tarefa) {
		boolean tentaNovamente = false;
		do {
			try {
				tarefa.run();
				tentaNovamente = false;
			} catch (Exception e) {
				tentaNovamente = true;
			}
		} while (tentaNovamente);
	}
	 
	public static void clicarNoLinkComUrl(String url) {
		obterElementoPor(By.xpath("//a[@href='" + url + "']")).click();
	}
	
	public static void clicarNoLink(String linkCategoria, String linkItem) {
		if (!menuCategoriaExpandido(linkCategoria)) {
			obterElementoPor(By.linkText(linkCategoria)).click();
			marcarComoMenuExpandido(linkCategoria);
		}
		WebElement elementoItem = obterElementoPor(By.linkText(linkItem));
		elementoItem.click();
	}
	
	private static void marcarComoMenuExpandido(String linkCategoria) {
		estadoMenu.put(linkCategoria, true);
	}

	private static boolean menuCategoriaExpandido(String linkCategoria) {
		return estadoMenu.getOrDefault(linkCategoria, false);
	}

	public static boolean tituloContemTexto(String titulo) {
		return new WebDriverWait(obterWebDriver(), TEMPO_ESPERA_EM_SEGUNDOS)
				.pollingEvery(Duration.ofSeconds(TEMPO_POOLING_EM_SEGUNDOS))
				.until(ExpectedConditions.titleContains(titulo));
	}
	
	public static void marcarItemNoCombo(String opcao, String id) {
		executar(() -> obterComponenteOpcoes(id).selectByVisibleText(opcao));
	}
	
	public static boolean contemItemSelecionadoNoCombo(String texto, String id) {
		return obterComponenteOpcoes(id).getFirstSelectedOption().getText().contains(texto);
	}

	private static Select obterComponenteOpcoes(String id) {
		return new Select(obterElementoPor(By.id(id)));
	}

	public static boolean contemCheckMarcado(String id) {
		return (obterElementoPor(By.id(id))).getAttribute("checked").equals("true"); 
	}
	
	public static boolean contemValor(String texto, String id) {
		return obterElementoPor(By.id(id)).getAttribute(ATRIBUTO_VALOR).contains(texto);
	}

	public static boolean contemMensagem(String id, String... textos) {
		String mensagens = obterElementoPor(By.id(id)).getText().replaceAll("\n", "");
		String mensagensAVerificar = "";
		for (String texto : textos) {
			mensagensAVerificar += texto;
		}
		return mensagens.equals(mensagensAVerificar);
	}
	
	public static boolean contemMensagem(String texto, String id) {
		return obterElementoPor(By.id(id)).getText().replaceAll("\r", "").replaceAll("\n", "").contains(texto);
	}

	public static void fecharNavegador() {
		obterWebDriver().quit();
	}

	public static void irPaginaEmBranco() {
		DriverUtils.ir(URL_BRANCO, false);
	}

	public static void deselecionarItem(String id) {
		obterComponenteOpcoes(id).selectByVisibleText(SELECIONE_UMA_OPCAO);
	}
}

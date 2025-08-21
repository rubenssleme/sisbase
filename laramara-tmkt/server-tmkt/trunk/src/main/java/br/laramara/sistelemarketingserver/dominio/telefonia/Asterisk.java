package br.laramara.sistelemarketingserver.dominio.telefonia;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.DomNodeList;
import com.gargoylesoftware.htmlunit.xml.XmlPage;

import br.laramara.sistelemarketingserver.utilitarios.Configuracao;

@Component
public class Asterisk implements PBX {

	private final Logger logger = Logger.getLogger(Asterisk.class);

	private WebClient webClient = new WebClient();
	private ExecutorService executor = Executors.newFixedThreadPool(10);

	private XmlPage executarSolicitacao(String url) throws Exception {
		XmlPage resultado = webClient.getPage(url);
		return resultado;
	}

	private String obterUrlBase() {
		return "http://" + new Configuracao().obterConfiguracao(Configuracao.PBX_API_ENDERECO) + ":"
				+ new Configuracao().obterConfiguracao(Configuracao.PBX_API_PORTA) + "/mxml?action=";
	}
	
	private void autenticar() {
		try {
			executarSolicitacao(obterUrlBase() + "login&username="
					+ new Configuracao().obterConfiguracao(Configuracao.PBX_API_LOGIN) + "&secret="
					+ new Configuracao().obterConfiguracao(Configuracao.PBX_API_SENHA) + "");
		} catch (Exception e) {
			logger.fatal("Erro durante autenticacao. \nDetalhes:" + e);
		}
	}

	@Override
	public void ligar(String ramal, String numero) {
		Runnable runnableTask = () -> {
			try {
				autenticar();
				executarSolicitacao(obterUrlBase() + "originate&channel=pjsip/" + ramal + "&exten=" + numero
						+ "&priority=1&context=from-internal");
			} catch (Exception e) {
				logger.fatal("Erro durante ligação. \nDetalhes:" + e);
			}
		};
		executor.execute(runnableTask);
	}

	@Override
	public String obterStatusRamais(String ramal) {
		try {	
			autenticar();
			XmlPage resultado = executarSolicitacao(obterUrlBase() + "extensionstatelist");

			DomNodeList<DomElement> nodoPrincipal = resultado.getElementsByTagName("generic");
			DomElement nodoRamal = nodoPrincipal.stream()
					.filter(elemento -> elemento.getAttribute("exten").equals(ramal)).findFirst().orElse(null);
			return nodoRamal.getAttribute("statustext");
		} catch (Exception e) {
			logger.fatal("Erro durante obtenção de status do ramal. \nDetalhes:" + e);
		}
		return "";
	}
}

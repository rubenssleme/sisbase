package br.laramara.sistelemarketingserver.dominio.contato;

import javax.inject.Inject;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class AgendadorDistribuicaoContato {
	
	private static final int TEMPO_ESPERA_ENTRE_DISTRIBUICOES = 5000;
	
	@Inject
	private DistribuidorContato distribuidorContato;
	
	@Scheduled(fixedDelay=TEMPO_ESPERA_ENTRE_DISTRIBUICOES)
	public void distribuirContatos() {
		distribuidorContato.distribuirContatos();
	}
}

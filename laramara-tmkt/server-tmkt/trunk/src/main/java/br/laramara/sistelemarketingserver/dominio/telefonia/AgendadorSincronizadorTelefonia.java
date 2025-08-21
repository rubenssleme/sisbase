package br.laramara.sistelemarketingserver.dominio.telefonia;

import javax.inject.Inject;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class AgendadorSincronizadorTelefonia {

	private static final int TEMPO_ESPERA_DE_SINCRONIZACOES_EM_MILESEGUNDOS = 3000;

	@Inject
	private SincronizadorTelefonia sincronizadorTelefonia;

	@Scheduled(fixedDelay = TEMPO_ESPERA_DE_SINCRONIZACOES_EM_MILESEGUNDOS)
	public void distribuirContatos() {
		sincronizadorTelefonia.sincronizar();
	}
}

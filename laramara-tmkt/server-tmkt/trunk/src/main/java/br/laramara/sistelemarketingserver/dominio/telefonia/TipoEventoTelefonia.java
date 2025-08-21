package br.laramara.sistelemarketingserver.dominio.telefonia;

import java.util.Arrays;

public enum TipoEventoTelefonia {
	ANSWERED("ANSWERED"), NO_ANSWER("NO ANSWER");
	
	private String evento;

	TipoEventoTelefonia(String evento) {
		this.evento = evento;
	}

	public String getEvento() {
		return evento;
	}
	
	public static TipoEventoTelefonia obter(String evento) {
		return Arrays.asList(TipoEventoTelefonia.values()).stream().filter(status -> status.getEvento().equals(evento))
				.findFirst().orElse(null);
	}
}

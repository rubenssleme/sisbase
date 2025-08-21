package br.laramara.sistelemarketingserver.dominio.telefonia;

import java.util.Arrays;
import java.util.List;

public enum StatusRamal {
	INDISPONIVEL("Unavailable", "Ramal indisponível"), 
	DISPONIVEL("Idle", "Ramal disponível"),
	ATENDA("Ringing", "Atenda o ramal"),
	USANDO("InUse", "Chamada em andamento");

	private final String statusTelefonia;
	private final String statusTraduzido;
	
	private StatusRamal(String statusTelefonia, String statusTraduzido) {
		this.statusTelefonia = statusTelefonia;
		this.statusTraduzido = statusTraduzido;
	}
	
	public boolean possuiStatusTelefonia(String statusTelefonia) {
		return this.statusTelefonia.toLowerCase().equals(statusTelefonia.toLowerCase());
	}
	
	public String obterStatusTelefoniaTraduzido() {
		return statusTraduzido;
	}

	public static StatusRamal obter(String statusTelefonia) {
		List<StatusRamal> todosStatus = Arrays.asList(StatusRamal.values());
		return todosStatus.stream().filter(status -> status.possuiStatusTelefonia(statusTelefonia)).findFirst()
				.orElse(null);
	}
}

package br.laramara.sistelemarketingserver.dominio.telefonia;

import java.util.Arrays;

public enum Ramal {
	RAMAL_6489("6489"), RAMAL_6435("6435");

	private String numero;

	Ramal(String numero) {
		this.numero = numero;
	}

	public String getNumero() {
		return numero;
	}

	public static Ramal obter(String numero) {
		return Arrays.asList(Ramal.values()).stream().filter(status -> status.getNumero().equals(numero)).findFirst()
				.orElse(null);
	}
}

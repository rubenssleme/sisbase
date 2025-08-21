package br.laramara.ti.sislaraserver.dominio.grupo;

import java.util.Calendar;

public enum DiaSemana {

	SEGUNDA(Calendar.MONDAY), 
	TERÇA(Calendar.TUESDAY), 
	QUARTA(Calendar.WEDNESDAY), 
	QUINTA(Calendar.THURSDAY), 
	SEXTA(Calendar.FRIDAY),
	SABADO(Calendar.SATURDAY),
	DOMINGO(Calendar.SUNDAY);

	private final int indice;

	private DiaSemana(int indice) {
		this.indice = indice;
	}

	public int getIndice() {
		return indice;
	}
	
	public static DiaSemana obterDiaSemana(Calendar data) {
		DiaSemana retorno = null;
		for (DiaSemana diaSemana : values()) {
			if (diaSemana.getIndice() == data.get(Calendar.DAY_OF_WEEK)) {
				retorno = diaSemana;
			}
		}
		return retorno;
	}
}

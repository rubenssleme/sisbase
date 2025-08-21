package br.laramara.ti.sislaracommons.utilitarios;

import java.util.Calendar;

public class MaquinaTempo {

	private static final MaquinaTempo maquinaTempo = new MaquinaTempo();
	private static Calendar dataAtual;
	
	private MaquinaTempo() {
	}

	public static final MaquinaTempo obterInstancia() {
		return maquinaTempo;
	}
	
	public Calendar obterCalendarioAtual() {
		return dataAtual == null ? Calendar.getInstance() : dataAtual;
	}

	public static void mudarDataAtual(String textoData) {
		dataAtual = DataHoraUtils.criar(textoData);
	}

	public static void restaurarDataOriginal() {
		dataAtual = null;
	}
}

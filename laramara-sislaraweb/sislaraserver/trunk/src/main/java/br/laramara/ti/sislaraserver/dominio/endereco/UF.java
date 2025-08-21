package br.laramara.ti.sislaraserver.dominio.endereco;

import java.util.Arrays;

public enum UF {
	AC, AL, AP, AM, BA, CE, DF, ES, GO, MA, MT, MS, MG, PA, PB, PR, PE, PI, RJ, RN, RS, RO, RR, SC, SP, SE, TO, OUTRO;
	
	public static boolean existe(String ufString) {
		return Arrays.asList(UF.values()).stream().anyMatch(uf -> uf.name().equals(ufString));
	}
	
}
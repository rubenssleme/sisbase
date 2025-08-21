package br.laramara.ti.sislaraserver.dominio.grupo;

import java.util.ArrayList;
import java.util.List;

public enum Frequencia {
	
	AT, OA, FJ, FP, FR, FU, RC;

	public static final List<Frequencia> deUsuarios() {
		List<Frequencia> retorno = new ArrayList<>();
		retorno.add(Frequencia.AT);
		retorno.add(Frequencia.FJ);
		retorno.add(Frequencia.FP);
		retorno.add(Frequencia.FU);
		retorno.add(Frequencia.FR);
		retorno.add(Frequencia.OA);
		retorno.add(Frequencia.RC);
		return retorno;
	}

	public static final List<Frequencia> deProfissionais() {
		List<Frequencia> retorno = new ArrayList<>();
		retorno.add(Frequencia.AT);
		retorno.add(Frequencia.FP);
		retorno.add(Frequencia.FR);
		retorno.add(Frequencia.OA);
		retorno.add(Frequencia.RC);
		return retorno;
	}
}

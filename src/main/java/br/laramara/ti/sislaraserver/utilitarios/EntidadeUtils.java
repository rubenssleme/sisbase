package br.laramara.ti.sislaraserver.utilitarios;

import java.util.Calendar;

import br.laramara.ti.sislaraserver.dominio.grupo.DiaSemana;

public class EntidadeUtils {
	public static final Long incrementar(Long versao) {
		if (versao != null) {
			versao++;
			return versao;
		} else {
			return new Long(1);
		}
	}
	
	public static boolean diaSemanaCompativelComData(DiaSemana diaSemana, Calendar ultimaData) {
		return ultimaData.get(Calendar.DAY_OF_WEEK) == diaSemana
				.getIndice();
	}
}

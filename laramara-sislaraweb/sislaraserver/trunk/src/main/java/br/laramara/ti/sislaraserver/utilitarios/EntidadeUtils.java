package br.laramara.ti.sislaraserver.utilitarios;

import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import br.laramara.ti.sislaraserver.dominio.grupo.DiaSemana;
import br.laramara.ti.sislaraserver.dominio.precadastro.PreCadastro;
import br.laramara.ti.sislaraserver.dominio.usuario.Usuario;

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
	
	public static String obterIdadeDeUsuarioOuPreCadastro(Usuario usuario, PreCadastro preCadastro){
		return usuario != null ? usuario.obterIdade() : preCadastro != null ? preCadastro.obterIdade() : "";
	}
	
	public static boolean possuiOcorrenciaDuplicada(List<?> a) {
		return a.stream().anyMatch(o -> comparar(a, o) > 1);
	}

	private static int comparar(List<?> a, Object o) {
		return Collections.frequency(a, o);
	}
}

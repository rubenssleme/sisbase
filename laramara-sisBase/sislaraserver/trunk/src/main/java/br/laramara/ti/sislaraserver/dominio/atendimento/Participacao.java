package br.laramara.ti.sislaraserver.dominio.atendimento;

import java.util.Arrays;
import java.util.List;

public enum Participacao {
	APENAS_ACOMPANHANTE, APENAS_FAMILIA, APENAS_USUARIO, COM_ACOMPANHANTE, COM_FAMILIA;
	
	public static List<Participacao> obterParticipacoesUnicas(){
		return Arrays.asList(APENAS_FAMILIA, APENAS_USUARIO, APENAS_ACOMPANHANTE);
	}
	
	public static List<Participacao> obterParticipacoesUnicasLiberadas(){
		return Arrays.asList(APENAS_FAMILIA, APENAS_ACOMPANHANTE);
	}

}

package br.laramara.ti.sislaraserver.dominio.usuario;

import java.util.ArrayList;
import java.util.List;


public enum Setor {
	PROCEJA, CTO, DI, NENHUM;
	
	public static final List<Setor> statusDisponiveis(){
		List<Setor> retorno = new ArrayList<Setor>();
		retorno.add(Setor.PROCEJA);
		retorno.add(Setor.CTO);
		return retorno;
	}
	
	public static final List<Setor> statusDisponiveisParaProjetos(){
		List<Setor> retorno = new ArrayList<Setor>();
		retorno.addAll(statusDisponiveis());
		retorno.add(Setor.DI);
		return retorno;
	}
}
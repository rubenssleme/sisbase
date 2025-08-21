package br.laramara.ti.sislaraserver.dominio.usuario;

import java.util.ArrayList;
import java.util.List;

public enum Status {
	ACESSO, AFASTADO, ALTA, CASO_NOVO, CNFSP, DESLIGADO, DESISTENTE, FALECIDO, INTEGRADO, RETORNO, TODOS, PRIMEIRA_VEZ;

	public static final List<Status> statusDisponiveisParaUsuarios() {
		List<Status> retorno = new ArrayList<Status>();
		retorno.add(Status.ACESSO);
		retorno.add(Status.AFASTADO);
		retorno.add(Status.ALTA);
		retorno.add(Status.CASO_NOVO);
		retorno.add(Status.DESLIGADO);
		retorno.add(Status.DESISTENTE);
		retorno.add(Status.FALECIDO);
		retorno.add(Status.INTEGRADO);
		retorno.add(Status.RETORNO);
		return retorno;
	}
	
	public static final List<Status> statusPassivosDeAgendamento() {
		List<Status> retorno = new ArrayList<Status>();
		retorno.add(Status.CASO_NOVO);
		retorno.add(Status.CNFSP);
		retorno.add(Status.RETORNO);
		retorno.add(Status.TODOS);
		return retorno;
	}
	
	public static final List<Status> statusDisponiveisParaFamiliar(){
		List<Status> retorno = new ArrayList<Status>();
		retorno.add(Status.FALECIDO);
		return retorno;
	}
	
	public static final List<Status> statusDisponiveisParaAtendimentoIndividual(){
		List<Status> retorno = new ArrayList<Status>();
		retorno.add(Status.PRIMEIRA_VEZ);
		retorno.add(Status.RETORNO);
		return retorno;
	}
}

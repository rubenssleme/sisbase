package br.laramara.ti.sislaraserver.dominio.doacao;

import java.util.ArrayList;
import java.util.List;

public enum StatusDemanda {
	AGUARDANDO, PRORROGADA, RESERVADO, DISPONIVEL, ENTREGUE, CANCELADA;

	public static List<StatusDemanda> obterStatusLimitado() {
		List<StatusDemanda> status = new ArrayList<>();
		status.add(DISPONIVEL);
		status.add(ENTREGUE);
		status.add(CANCELADA);
		return status;
	}
	
	public static List<StatusDemanda> obterStatus() {
		List<StatusDemanda> status = new ArrayList<>();
		status.add(AGUARDANDO); 
		status.add(PRORROGADA);
		status.add(RESERVADO);
		status.add(DISPONIVEL);
		status.add(ENTREGUE);
		status.add(CANCELADA);
		return status;
	}
}

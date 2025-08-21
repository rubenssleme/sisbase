package br.laramara.sistelemarketingserver.dominio.doacao;

import java.util.Arrays;
import java.util.List;

public enum Metodo {
	DINHEIRO(Arrays.asList(TipoRetirada.MENSAGEIRO, TipoRetirada.EMAIL));
	
	private final List<TipoRetirada> tiposRetiradas;

	private Metodo(List<TipoRetirada> tiposRetiradas) {
		this.tiposRetiradas = tiposRetiradas;
	}

	public List<TipoRetirada> getTiposRetiradas() {
		return tiposRetiradas;
	}
}

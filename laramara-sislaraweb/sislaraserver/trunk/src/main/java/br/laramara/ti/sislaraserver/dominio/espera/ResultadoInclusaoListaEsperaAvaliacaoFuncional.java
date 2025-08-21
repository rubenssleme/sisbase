package br.laramara.ti.sislaraserver.dominio.espera;

import br.laramara.ti.sislaraserver.dominio.Resultado;

public class ResultadoInclusaoListaEsperaAvaliacaoFuncional extends Resultado{

	private Espera espera;
	
	public void efetuadoComSucesso(Espera espera) {
		this.espera = espera;
		efetuadoComSucesso();
	}

	public Espera getEspera() {
		return espera;
	}
}

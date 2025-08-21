package br.laramara.ti.sislaraserver.dominio.grupo;

import br.laramara.ti.sislaraserver.dominio.Resultado;

public class ResultadoEdicaoModuloPeriodo extends Resultado{
	
	private ModuloPeriodo moduloPeriodo;
	
	public void efetuadoComSucesso(ModuloPeriodo moduloPeriodo) {
		this.moduloPeriodo = moduloPeriodo;
		efetuadoComSucesso();
	}

	public ModuloPeriodo getModuloPeriodo() {
		return moduloPeriodo;
	}
}

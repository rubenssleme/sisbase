package br.laramara.ti.sislaraserver.fabricas;

import br.laramara.ti.sislaraserver.dominio.escola.DreCefai;

public class ContextoDreCefai {

	public static DreCefai fabricarDreCefaiComTodosOsDados() {
		return new DreCefai(new Long(12), "DreCfai");
	}

}

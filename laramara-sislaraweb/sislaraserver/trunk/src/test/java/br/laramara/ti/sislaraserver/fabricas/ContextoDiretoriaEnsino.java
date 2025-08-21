package br.laramara.ti.sislaraserver.fabricas;

import br.laramara.ti.sislaraserver.dominio.escola.DiretoriaEnsino;

public class ContextoDiretoriaEnsino {

	public static DiretoriaEnsino fabricarDiretoriaEnsinoComTodosOsDados() {
		return new DiretoriaEnsino(new Long(12), "Diretoria");
	}

}

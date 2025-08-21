package br.laramara.ti.sislaraserver.repositorios;

import br.laramara.ti.sislaraserver.dominio.Versionavel;

public interface Versionado {
	public boolean versaoAtual(Versionavel versionavel);
}

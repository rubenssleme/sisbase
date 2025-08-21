package br.laramara.ti.sismovimentacaoserver.repositorios;

import br.laramara.ti.sismovimentacaoserver.dominio.Versionavel;

public interface Versionado {
	public boolean versaoAtual(Versionavel versionavel);
}

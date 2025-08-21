package br.laramara.sistelemarketingserver.repositorios;

import br.laramara.sistelemarketingserver.dominio.seguranca.Versionavel;

public interface Versionado {
	public boolean versaoAtual(Versionavel versionavel);
}

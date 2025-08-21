package br.laramara.ti.sislaraserver.repositorios;

import br.laramara.ti.sislaraserver.dominio.escola.InformacaoEducacional;

public interface RepositorioInformacaoEducacional {
	public InformacaoEducacional obterPorId(Long id);
}

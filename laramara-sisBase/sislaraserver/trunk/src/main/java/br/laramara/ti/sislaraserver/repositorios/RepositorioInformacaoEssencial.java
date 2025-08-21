package br.laramara.ti.sislaraserver.repositorios;

import br.laramara.ti.sislaraserver.dominio.InformacaoEssencial;

public interface RepositorioInformacaoEssencial {
	public InformacaoEssencial obterPorId(Long id);
}

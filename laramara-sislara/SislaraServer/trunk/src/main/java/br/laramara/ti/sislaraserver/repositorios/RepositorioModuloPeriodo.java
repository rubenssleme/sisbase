package br.laramara.ti.sislaraserver.repositorios;

import br.laramara.ti.sislaraserver.dominio.grupo.ModuloPeriodo;

public interface RepositorioModuloPeriodo {
	public ModuloPeriodo obterPorId(Long id);

	public ModuloPeriodo salvar(ModuloPeriodo moduloPeriodo);
}

package br.laramara.ti.sislaraserver.repositorios;

import java.util.List;

import br.laramara.ti.sislaraserver.dominio.usuario.InfraestruturaBasica;

public interface RepositorioInfraestruturaBasica {
	public List<InfraestruturaBasica> obterTodos();
}

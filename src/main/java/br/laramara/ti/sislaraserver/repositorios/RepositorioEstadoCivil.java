package br.laramara.ti.sislaraserver.repositorios;

import java.util.List;

import br.laramara.ti.sislaraserver.dominio.usuario.EstadoCivil;

public interface RepositorioEstadoCivil {
	public List<EstadoCivil> obterTodos();
}

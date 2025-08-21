package br.laramara.ti.sislaraserver.repositorios;

import java.util.List;

import br.laramara.ti.sislaraserver.dominio.usuario.Doenca;

public interface RepositorioDoenca {
	public List<Doenca> obterTodos();
}

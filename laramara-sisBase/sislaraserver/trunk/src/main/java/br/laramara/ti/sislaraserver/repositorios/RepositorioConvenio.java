package br.laramara.ti.sislaraserver.repositorios;

import java.util.List;

import br.laramara.ti.sislaraserver.dominio.usuario.Convenio;

public interface RepositorioConvenio {
	public List<Convenio> obterTodos();
}

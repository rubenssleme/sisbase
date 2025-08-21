package br.laramara.ti.sislaraserver.repositorios;

import java.util.List;

import br.laramara.ti.sislaraserver.dominio.usuario.Encaminhamento;

public interface RepositorioEncaminhamento {
	public List<Encaminhamento> obterTodos();
}

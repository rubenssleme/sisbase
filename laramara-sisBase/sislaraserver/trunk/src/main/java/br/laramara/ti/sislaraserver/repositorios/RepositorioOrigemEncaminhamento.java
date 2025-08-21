package br.laramara.ti.sislaraserver.repositorios;

import java.util.List;

import br.laramara.ti.sislaraserver.dominio.usuario.OrigemEncaminhamento;

public interface RepositorioOrigemEncaminhamento {
	public List<OrigemEncaminhamento> obterTodos();
}

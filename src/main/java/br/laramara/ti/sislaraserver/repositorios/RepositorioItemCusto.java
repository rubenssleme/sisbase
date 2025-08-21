package br.laramara.ti.sislaraserver.repositorios;

import java.util.List;

import br.laramara.ti.sislaraserver.dominio.usuario.ItemCusto;

public interface RepositorioItemCusto {
	public List<ItemCusto> obterTodosDaDoenca();

	public List<ItemCusto> obterTodosDaDeficiencia();
}

package br.laramara.ti.sislaraserver.repositorios;

import java.util.List;

import br.laramara.ti.sislaraserver.dominio.doacao.Filial;

public interface RepositorioFilial {
	public List<Filial> obterTodos();
}

package br.laramara.ti.sislaraserver.repositorios;

import java.util.List;

import br.laramara.ti.sislaraserver.dominio.trabalho.LocalTrabalho;

public interface RepositorioLocalTrabalho {
	public List<LocalTrabalho> obterTodos();
}

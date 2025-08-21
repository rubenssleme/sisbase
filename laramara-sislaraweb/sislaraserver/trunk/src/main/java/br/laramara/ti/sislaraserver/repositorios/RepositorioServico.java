package br.laramara.ti.sislaraserver.repositorios;

import java.util.List;

import br.laramara.ti.sislaraserver.dominio.usuario.Servico;

public interface RepositorioServico {
	public List<Servico> obterTodos();
}

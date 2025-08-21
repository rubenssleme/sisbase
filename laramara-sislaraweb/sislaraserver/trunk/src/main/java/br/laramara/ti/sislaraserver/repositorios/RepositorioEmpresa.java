package br.laramara.ti.sislaraserver.repositorios;

import java.util.List;

import br.laramara.ti.sislaraserver.dominio.doacao.Empresa;

public interface RepositorioEmpresa {
	public List<Empresa> obterTodos();
}

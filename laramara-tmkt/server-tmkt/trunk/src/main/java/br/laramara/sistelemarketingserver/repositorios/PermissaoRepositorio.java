package br.laramara.sistelemarketingserver.repositorios;

import java.util.List;

import br.laramara.sistelemarketingserver.dominio.seguranca.Permissao;

public interface PermissaoRepositorio {
	public List<Permissao> obterTodos();
}

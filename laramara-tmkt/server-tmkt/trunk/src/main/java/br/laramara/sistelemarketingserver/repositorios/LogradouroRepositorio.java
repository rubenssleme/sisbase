package br.laramara.sistelemarketingserver.repositorios;

import java.util.List;

import br.laramara.sistelemarketingserver.dominio.Bairro;

public interface LogradouroRepositorio {
	public List<Bairro> obterTodos(Long idMunicipio);
}

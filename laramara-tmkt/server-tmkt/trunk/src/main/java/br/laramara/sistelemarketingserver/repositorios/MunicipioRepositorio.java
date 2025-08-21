package br.laramara.sistelemarketingserver.repositorios;

import java.util.List;

import br.laramara.sistelemarketingserver.dominio.Municipio;

public interface MunicipioRepositorio {
	public List<Municipio> obterTodos(Long idEstado);
}

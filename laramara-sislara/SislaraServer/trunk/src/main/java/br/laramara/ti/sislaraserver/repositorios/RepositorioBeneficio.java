package br.laramara.ti.sislaraserver.repositorios;

import java.util.List;

import br.laramara.ti.sislaraserver.dominio.usuario.Beneficio;

public interface RepositorioBeneficio {
	public List<Beneficio> obterTodos();
}

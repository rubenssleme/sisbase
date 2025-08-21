package br.laramara.sistelemarketingserver.repositorios;

import java.util.List;

import br.laramara.sistelemarketingserver.dominio.seguranca.Nivel;

public interface NivelRepositorio {
	public Nivel salvar(Nivel nivel);

	public List<Nivel> obterTodos();

	public Nivel obter(Long id);
}

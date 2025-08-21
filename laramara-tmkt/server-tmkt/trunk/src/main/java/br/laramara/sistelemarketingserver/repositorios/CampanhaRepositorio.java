package br.laramara.sistelemarketingserver.repositorios;

import java.util.List;

import br.laramara.sistelemarketingserver.dominio.campanha.Campanha;

public interface CampanhaRepositorio {
	
	public Campanha salvar(Campanha campanha);
	
	public Campanha obter(Long id);
	
	public List<Campanha> obterTodos();

	public List<Campanha> obterTodosAtivos();
}

package br.laramara.ti.sismovimentacaoserver.repositorios;

import java.util.List;

import br.laramara.ti.sismovimentacaoserver.dominio.Profissional;

public interface RepositorioProfissional {
	
	public void salvar(Profissional profissionalASalvar);

	public List<Profissional> obterProfissionaisAtivos();
	
	public List<Profissional> obterTodos();
	
	public List<Profissional> obterVoluntarioAtivo();
}

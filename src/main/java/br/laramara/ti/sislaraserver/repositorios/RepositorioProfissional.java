package br.laramara.ti.sislaraserver.repositorios;

import java.util.List;

import br.laramara.ti.sislaraserver.dominio.grupo.Profissional;

public interface RepositorioProfissional {
	
	public void salvar(Profissional profissionalASalvar);

	public List<Profissional> obterProfissionaisAtivos();
	
	public List<Profissional> obterTodos();
	
	public List<Profissional> obterVoluntarioAtivo();
}

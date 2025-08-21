package br.laramara.ti.sislaraserver.repositorios;

import java.util.List;

import br.laramara.ti.sislaraserver.dominio.doacao.Projeto;

public interface RepositorioProjeto {
	public void salvar(Projeto projetoASalvar);

	public Projeto obterPorId(Long id);

	public List<Projeto> obterTodos();
	
	public List<Projeto> obterAtivos();
	
	public List<Projeto> pesquisarPorNome(String nome);
}
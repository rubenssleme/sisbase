package br.laramara.ti.sislaraserver.repositorios;

import java.util.List;

import br.laramara.ti.sislaraserver.dominio.instituicao.Instituicao;

public interface RepositorioInstituicao {
	public void salvar(Instituicao instituicaoASalvar);

	public Instituicao obterPorId(Long id);

	public List<Instituicao> pesquisarPorNome(String nome);
	
	public boolean possuiInstituicao(Instituicao instituicao);

	public List<Instituicao> obterTodos();
	
	public List<Instituicao> obterTodosOrdenadasPorNome();
	
	public List<Instituicao> obterTodosComSRMs();
	
	public List<Instituicao> obterTodosComSalaRecurso();

	public List<Instituicao> obterTodosComOutrosAEE();
}

package br.laramara.ti.sislaraserver.repositorios;

import java.util.List;

import br.laramara.ti.sislaraserver.dominio.contribuicao.Contribuinte;

public interface RepositorioContribuinte {
	public Contribuinte salvar(Contribuinte contribuinteASalvar);
	public Contribuinte obterPorId(Long id);
	public List<Contribuinte> obterTodos();
	public List<Contribuinte> obterPorNome(String nome);
}

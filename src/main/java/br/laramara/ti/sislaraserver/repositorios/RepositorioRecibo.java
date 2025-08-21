package br.laramara.ti.sislaraserver.repositorios;

import java.util.List;

import br.laramara.ti.sislaraserver.dominio.doacao.Recibo;

public interface RepositorioRecibo {
	public Recibo obterPorId(Long id);
	public Recibo obterMaisRecentePorCpfCnpj(String cpfCnpj);
	public List<Recibo> obterTodosPorCpfCnpj(String cpfCnpj);
	public Recibo salvar(Recibo reciboASalvar); 
	public List<Recibo> obterTodos();
	public List<Recibo> obterPorFilial(String filial);
}

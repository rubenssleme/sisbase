package br.laramara.ti.sislaraserver.repositorios;

import java.util.List;

import br.laramara.ti.sislaraserver.dominio.seguranca.ContaAcesso;
import br.laramara.ti.sislaraserver.dominio.seguranca.Credencial;

public interface RepositorioContaAcesso {
	public ContaAcesso obterContaAcesso(Credencial credencial);
	
	public ContaAcesso obterPorId(Long id);
	
	public void salvar(ContaAcesso contaAcesso);

	public List<ContaAcesso> obterTodos();

	public ContaAcesso obterPorToken(String token);
}

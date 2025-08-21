package br.laramara.sistelemarketingserver.repositorios;

import java.util.List;

import br.laramara.sistelemarketingserver.dominio.seguranca.ContaAcesso;
import br.laramara.sistelemarketingserver.dominio.seguranca.Credencial;

public interface ContaAcessoRepositorio {
	public ContaAcesso salvar(ContaAcesso contaAcesso);

	public List<ContaAcesso> obterTodos();
	
	public List<ContaAcesso> obterTodosOperadoresAtivos();

	public ContaAcesso obter(Long id);
	
	public ContaAcesso obterContaAcesso(Credencial credencial);
	
	public ContaAcesso obterPorToken(String token);
}

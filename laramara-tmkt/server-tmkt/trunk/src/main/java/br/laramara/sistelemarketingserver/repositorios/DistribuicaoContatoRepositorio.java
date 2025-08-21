package br.laramara.sistelemarketingserver.repositorios;

import br.laramara.sistelemarketingserver.dominio.contato.DistribuicaoContato;
import br.laramara.sistelemarketingserver.dominio.contato.EventoTelefonia;
import br.laramara.sistelemarketingserver.dominio.seguranca.ContaAcesso;

public interface DistribuicaoContatoRepositorio {

	public DistribuicaoContato salvar(DistribuicaoContato distriuicaoContato);
	
	public DistribuicaoContato obterEmAtuacao(ContaAcesso contaAcesso);

	public DistribuicaoContato obterDistribuicaoMaisRecentePorEvento(EventoTelefonia eventoTelefonia);

	public DistribuicaoContato obterPorId(Long id);

}

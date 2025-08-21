package br.laramara.sistelemarketingserver.repositorios;

import br.laramara.sistelemarketingserver.dominio.doacao.Doacao;

public interface DoacaoRepositorio {
	public Doacao obter(Long id);
	
	public Doacao salvar(Doacao doacao);
}

package br.laramara.ti.sislaraserver.repositorios;

import br.laramara.ti.sislaraserver.dominio.inscricao.Inscricao;

public interface RepositorioInscricao {
	Inscricao salvar(Inscricao inscricao);
	Inscricao obterPorId(Long id);
}

package br.laramara.ti.sislaraserver.repositorios;

import java.util.List;

import br.laramara.ti.sislaraserver.dominio.solicitacao.EspecificacaoPesquisaSolicitacaoRelatorio;
import br.laramara.ti.sislaraserver.dominio.solicitacao.SolicitacaoRelatorio;

public interface RepositorioSolicitacaoRelatorio {
	public SolicitacaoRelatorio obterPorId(Long id);

	public List<SolicitacaoRelatorio> obterPor(
			EspecificacaoPesquisaSolicitacaoRelatorio especificacaoPesquisaSolicitacaoRelatorio);

	public SolicitacaoRelatorio salvar(SolicitacaoRelatorio solicitacaoRelatorio);
}

package br.laramara.ti.sismovimentacaoserver.repositorios;

import java.util.List;

import br.laramara.ti.sismovimentacaoserver.dominio.movimentacao.Movimentacao;

public interface RepositorioMovimentacao {
	public List<Movimentacao> obterPor(EspecificacaoPesquisaMovimentacao especificacaoPesquisaMovimentacao);
	public Movimentacao salvar(Movimentacao movimentacao);
	public Movimentacao obterPorId(Long id);
	public List<String> obterListagemCliente();
	public List<String> obterListagemCor();
	public List<String> obterListagemDescricao();
	public List<String> obterListagemDescricaoProduto();
	public List<String> obterListagemBobina();
	public List<String> obterListagemPlanaPapel();
	public List<String> obterListagemTipoProva(); 
}

package br.laramara.ti.sismovimentacaoserver.repositorios;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.laramara.ti.sismovimentacaoserver.dominio.movimentacao.Movimentacao;

@Repository
public class RepositorioMovimentacaoBD extends RepositorioDB<Movimentacao>
		implements RepositorioMovimentacao {

	@Override
	public List<Movimentacao> obterPor(
			EspecificacaoPesquisaMovimentacao especificacaoPesquisaMovimentacao) {
		List<Movimentacao> movimentacao = new ArrayList<>();

		TypedQuery<Movimentacao> query = em.createQuery(
				"SELECT m FROM Movimentacao m ORDER BY m.id desc",
				Movimentacao.class);
		query.setMaxResults(especificacaoPesquisaMovimentacao
				.getQuantidadeResultado());
		try {
			movimentacao = query.getResultList();
		} catch (Exception e) {
			logger.error("Não foi possível obter as Movimentacoes. \nDetalhe:"
					+ e);
		}
		return movimentacao;
	}

	@Transactional
	public Movimentacao salvar(Movimentacao movimentacaoASalvar) {
		String acao = "";
		if (movimentacaoASalvar.getId() == null) {
			em.persist(movimentacaoASalvar);
			acao = "Inclusão";
		} else {
			em.merge(movimentacaoASalvar);
			acao = "Alteração";
		}
		logger.info(acao + " do " + movimentacaoASalvar
				+ " realizada com sucesso.");

		return movimentacaoASalvar;
	}

	@Override
	public List<String> obterListagemCliente() {
		return obterLista("m.cliente");
	}

	@Override
	public List<String> obterListagemCor() {
		return obterLista("m.cor");
	}

	@Override
	public List<String> obterListagemDescricao() {
		return obterLista("m.descricao");
	}
	
	@Override
	public List<String> obterListagemBobina() {
		return obterLista("m.bobina");
	}
	
	@Override
	public List<String> obterListagemPlanaPapel() {
		return obterLista("m.planaPapel");
	}
	
	@Override
	public List<String> obterListagemDescricaoProduto() {
		return obterLista("m.descricaoProduto");
	}

	@Override
	public List<String> obterListagemTipoProva() {
		return obterLista("m.tipoProva");
	}

	private List<String> obterLista(String campo) {
		SortedSet<String> textoSemRepeticao = new TreeSet<>();
		TypedQuery<String> query = em.createQuery("SELECT DISTINCT " + campo
				+ " FROM Movimentacao m ORDER BY " + campo, String.class);
		try {
			List<String> resultados = query.getResultList();
			for (String resultado : resultados) {
				textoSemRepeticao.add(resultado.toUpperCase().trim());
			}

		} catch (Exception e) {
			logger.error("Não foi possível obter. \nDetalhe:" + e);
		}
		return new ArrayList<>(textoSemRepeticao);
	}
}

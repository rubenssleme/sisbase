package br.laramara.ti.sislaraserver.repositorios;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.laramara.ti.sislaraserver.dominio.escola.TipoApoio;
import br.laramara.ti.sislaraserver.dominio.instituicao.Instituicao;
import br.laramara.ti.sislaraserver.repositorios.cache.CacheInstituicao;

@Repository
public class RepositorioInstituicaoBD extends RepositorioDB<Instituicao>
		implements RepositorioInstituicao {

	@Inject
	private CacheInstituicao cache;
	
	@Transactional
	public void salvar(Instituicao instituicaoASalvar) {
		String acao;
		try {
			if (instituicaoASalvar.getId() == null) {
				em.persist(instituicaoASalvar);
				acao = "Inclusão";
			} else {
				em.merge(instituicaoASalvar);
				acao = "Alteração";
			}
			cache.atualizarCache(instituicaoASalvar);
			logger.info(acao + " do " + instituicaoASalvar
					+ " realizada com sucesso.");
		} catch (Exception e) {
			logger.error("Ocorreu algum erro durante o armazenamento do "
					+ instituicaoASalvar + ". \nDetalhes: " + e);
		}
	}

	public List<Instituicao> pesquisarPorNome(String nome) {
		List<Instituicao> instituicoes = new ArrayList<>();
		try {
			TypedQuery<Instituicao> query = em
					.createQuery(
							"SELECT i FROM Instituicao i WHERE LOWER(remover_acento(i.nome)) LIKE LOWER(remover_acento(:nome)) "
									+ "ORDER BY i.nome", Instituicao.class);
			query.setParameter("nome", "%" + nome.toLowerCase() + "%");
			instituicoes = query.getResultList();
		} catch (Exception e) {
			logger.fatal("Erro durante pesquisa de Instituição por nome.\n Detalhes:"
					+ e);
		}
		return instituicoes;
	}

	@Override
	public List<Instituicao> obterTodos() {
		List<Instituicao> resultado = cache.obterTodos();
		Collections.sort(resultado, Instituicao.obterComparador());
		return resultado;
	}

	@Override
	public List<Instituicao> obterTodosOrdenadasPorNome() {
		List<Instituicao> instituicoes = new ArrayList<>();
		try {
			TypedQuery<Instituicao> query = em.createQuery(
					"SELECT i FROM Instituicao i",
					Instituicao.class);
			instituicoes = query.getResultList();
		} catch (Exception e) {
			logger.fatal("Erro durante pesquisa de Todas as Instituição.\n Detalhes:"
					+ e);
		}
		return instituicoes;
	}

	@Override
	public List<Instituicao> obterTodosComSRMs() {
		return obterComFiltro(TipoApoio.SRMs);
	}

	@Override
	public List<Instituicao> obterTodosComSalaRecurso() {
		return obterComFiltro(TipoApoio.SALA_DE_RECURSO_DO_ESTADO);
	}

	@Override
	public List<Instituicao> obterTodosComOutrosAEE() {
		return obterComFiltro(TipoApoio.OUTROS_AEE);
	}
	
	private List<Instituicao> obterComFiltro(TipoApoio tipoApoio) {
		List<Instituicao> instituicoes = new ArrayList<>();
		try {
			TypedQuery<Instituicao> query = em
					.createQuery(
							"SELECT i FROM Instituicao i WHERE i.tipoApoio = :tipoApoio ORDER BY i.nome",
							Instituicao.class);
			query.setParameter("tipoApoio", tipoApoio);
			instituicoes = query.getResultList();
		} catch (Exception e) {
			logger.fatal("Erro durante pesquisa de Instituiçoes com "
					+ tipoApoio.toString() + ".\n Detalhes:" + e);
		}
		return instituicoes;
	}

	@Override
	public boolean possuiInstituicao(Instituicao instituicao) {
		if (instituicao.getId() == null) {
			return pesquisarPorNome(instituicao.getNomeSemEspaco()).size() >= 1;
		} else {
			List<Instituicao> existentes = pesquisarPorNome(instituicao
					.getNomeSemEspaco());
			return !existentes.isEmpty()
					&& !existentes.get(0).equals(instituicao);
		}
	}
}

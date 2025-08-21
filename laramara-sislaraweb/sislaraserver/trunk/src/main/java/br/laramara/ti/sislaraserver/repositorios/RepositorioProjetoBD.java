package br.laramara.ti.sislaraserver.repositorios;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.laramara.ti.sislaraserver.dominio.doacao.Projeto;

@Repository
public class RepositorioProjetoBD extends RepositorioDB<Projeto> implements
		RepositorioProjeto {

	@Inject
	private RepositorioArquivo repositorioArquivo;
	
	@Transactional
	public void salvar(Projeto projetoASalvar) {
		String acao;
		try {
			if (projetoASalvar.getId() == null) {
				em.persist(projetoASalvar);
				acao = "Inclusão";
			} else {
				em.merge(projetoASalvar);
				acao = "Alteração";
			}
			repositorioArquivo.salvar(projetoASalvar);
			logger.info(acao + " do " + projetoASalvar
					+ " realizada com sucesso.");
		} catch (Exception e) {
			logger.error("Ocorreu algum erro durante o armazenamento do "
					+ projetoASalvar + ". \nDetalhes: " + e);
		}
	}

	@Override
	public List<Projeto> obterTodos() {
		List<Projeto> projetos = new ArrayList<>();
		try {
			TypedQuery<Projeto> query = em.createQuery(
					"SELECT i FROM Projeto i ORDER BY i.nome", Projeto.class);
			projetos = query.getResultList();
		} catch (Exception e) {
			logger.fatal("Erro durante pesquisa de Todos Projetos.\n Detalhes:"
					+ e);
		}
		return projetos;
	}

	@Override
	public List<Projeto> obterAtivos() {
		List<Projeto> projetos = new ArrayList<>();
		try {
			TypedQuery<Projeto> query = em
					.createQuery(
							"SELECT i FROM Projeto i WHERE i.ativo IS TRUE ORDER BY i.dataInicialVigencia DESC",
							Projeto.class);
			projetos = query.getResultList();
		} catch (Exception e) {
			logger.fatal("Erro durante pesquisa de Projetos Ativos.\n Detalhes:"
					+ e);
		}
		return projetos;
	}

	@Override
	public List<Projeto> pesquisarPorNome(String nome) {
		List<Projeto> projetos = new ArrayList<>();
		try {
			TypedQuery<Projeto> query = em
					.createQuery(
							"SELECT i FROM Projeto i WHERE LOWER(remover_acento(i.nome)) LIKE LOWER(remover_acento(:nome)) " +
							"ORDER BY i.nome",
							Projeto.class);
			query.setParameter("nome", "%" + nome.toLowerCase() + "%");
			projetos = query.getResultList();
		} catch (Exception e) {
			logger.fatal("Erro durante pesquisa de Projeto por nome.\n Detalhes:"
					+ e);
		}
		return projetos;
	}
}

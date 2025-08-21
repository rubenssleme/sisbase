package br.laramara.sistelemarketingserver.repositorios;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import br.laramara.sistelemarketingserver.dominio.campanha.Campanha;

@Repository
public class CampanhaRepositorioBD extends RepositorioDB<Campanha> implements CampanhaRepositorio {

	@Transactional
	public Campanha salvar(Campanha campanha) {
		Campanha campanhaSalvo = null;
		try {
			campanhaSalvo = em.merge(campanha);
			logger.info("Edição do " + campanhaSalvo + " realizada com sucesso.");
		} catch (Exception e) {
			logger.error("Ocorreu algum erro durante o armazenamento do " + campanhaSalvo + ". \nDetalhes: " + e);
		}
		return campanhaSalvo;
	}

	@Override
	public Campanha obter(Long id) {
		Campanha campanha = null;
		if (id != null) {
			try {
				TypedQuery<Campanha> query = em.createQuery("SELECT c FROM Campanha c WHERE c.id = :id",
						Campanha.class);
				query.setParameter("id", id);
				campanha = query.getSingleResult();
			} catch (Exception e) {
				logger.fatal("Erro durante pesquisa de campanha.\n Detalhes:" + e);
			}
		}
		return campanha;
	}

	@Override
	public List<Campanha> obterTodos() {
		List<Campanha> campanhas = new ArrayList<>();
		try {
			TypedQuery<Campanha> query = em.createQuery("SELECT c FROM Campanha c ORDER BY c.nome",
					Campanha.class);
			campanhas = query.getResultList();
		} catch (Exception e) {
			logger.fatal("Erro durante pesquisa de todos as campanhas.\n Detalhes:" + e);
		}
		return campanhas;
	}

	@Override
	public List<Campanha> obterTodosAtivos() {
		return obterTodos().stream().filter(campanha -> campanha.ativo()).collect(Collectors.toList());
	}

}

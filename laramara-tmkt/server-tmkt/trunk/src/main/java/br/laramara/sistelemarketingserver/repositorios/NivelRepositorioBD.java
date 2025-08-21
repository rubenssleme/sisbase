package br.laramara.sistelemarketingserver.repositorios;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import br.laramara.sistelemarketingserver.dominio.seguranca.Nivel;

@Repository
public class NivelRepositorioBD extends RepositorioDB<Nivel> implements NivelRepositorio {

	@Transactional
	public Nivel salvar(Nivel nivel) {
		Nivel nivelSalvo = null; 
		try {
			nivelSalvo = em.merge(nivel);
			logger.info("Edição do " + nivelSalvo + " realizada com sucesso.");
		} catch (Exception e) {
			logger.error("Ocorreu algum erro durante o armazenamento do " + nivelSalvo + ". \nDetalhes: " + e);
		}
		return nivelSalvo;
	}

	@Override
	public List<Nivel> obterTodos() {
		List<Nivel> nivel = new ArrayList<>();
		try {
			TypedQuery<Nivel> query = em.createQuery(
					"SELECT c FROM Nivel c ORDER BY c.descricao",
					Nivel.class);
			nivel = query.getResultList();
		} catch (Exception e) {
			logger.fatal("Erro durante pesquisa de todos os níveis.\n Detalhes:"
					+ e);
		}
		return nivel;
	}

	@Override
	public Nivel obter(Long id) {
		Nivel nivel = null;
		try {
			TypedQuery<Nivel> query = em.createQuery(
					"SELECT c FROM Nivel c WHERE c.id = :id",
					Nivel.class);
			query.setParameter("id", id);
			nivel = query.getSingleResult();
		} catch (Exception e) {
			logger.fatal("Erro durante pesquisa de todos os níveis.\n Detalhes:"
					+ e);
		}
		return nivel;
	}
}

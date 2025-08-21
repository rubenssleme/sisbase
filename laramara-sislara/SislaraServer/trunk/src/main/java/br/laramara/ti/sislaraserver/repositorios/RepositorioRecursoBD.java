package br.laramara.ti.sislaraserver.repositorios;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import br.laramara.ti.sislaraserver.dominio.grupo.Recurso;

@Repository
public class RepositorioRecursoBD extends
		RepositorioDB<Recurso> implements
		RepositorioRecurso {

	@Override
	public List<Recurso> obterTodos() {
		List<Recurso> recurso = new ArrayList<>();
		try {
			TypedQuery<Recurso> query = em.createQuery(
					"SELECT i FROM Recurso i ORDER BY i.descricao",
					Recurso.class);
			recurso = query.getResultList();
		} catch (Exception e) {
			logger.fatal("Erro durante pesquisa de Recursos.\n Detalhes:"
					+ e);
		}
		return recurso;
	}

}

package br.laramara.ti.sislaraserver.repositorios;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import br.laramara.ti.sislaraserver.dominio.usuario.RecursoMoradia;

@Repository
public class RepositorioRecursoMoradiaBD extends RepositorioDB<RecursoMoradia> implements RepositorioRecursoMoradia {

	@Override
	public List<RecursoMoradia> obterTodos() {
		List<RecursoMoradia> recurso = new ArrayList<>();
		try {
			TypedQuery<RecursoMoradia> query = em.createQuery("SELECT i FROM RecursoMoradia i ORDER BY i.descricao",
					RecursoMoradia.class);
			recurso = query.getResultList();
		} catch (Exception e) {
			logger.fatal("Erro durante pesquisa de Recursos de Moradia.\n Detalhes:" + e);
		}
		return recurso;
	}
}

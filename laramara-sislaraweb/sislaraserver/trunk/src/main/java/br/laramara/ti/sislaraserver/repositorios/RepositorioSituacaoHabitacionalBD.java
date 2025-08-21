package br.laramara.ti.sislaraserver.repositorios;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import br.laramara.ti.sislaraserver.dominio.usuario.SituacaoHabitacional;

@Repository
public class RepositorioSituacaoHabitacionalBD extends RepositorioDB<SituacaoHabitacional> implements RepositorioSituacaoHabitacional {

	@Override
	public List<SituacaoHabitacional> obterTodos() {
		List<SituacaoHabitacional> situacaoHabitacional = new ArrayList<>();

		TypedQuery<SituacaoHabitacional> query = em.createQuery("SELECT s FROM SituacaoHabitacional s ORDER BY s.descricao", SituacaoHabitacional.class);
		try {
			situacaoHabitacional = query.getResultList();
		} catch (Exception e) {
			logger.error("Não foi possível obter a lista de Situações Habitacionais. \nDetalhe:" + e);
		}
		return situacaoHabitacional;
	}

}

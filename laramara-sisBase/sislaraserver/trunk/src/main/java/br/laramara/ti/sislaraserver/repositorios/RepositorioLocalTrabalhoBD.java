package br.laramara.ti.sislaraserver.repositorios;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import br.laramara.ti.sislaraserver.dominio.trabalho.LocalTrabalho;

@Repository
public class RepositorioLocalTrabalhoBD extends RepositorioDB<LocalTrabalho>
		implements RepositorioLocalTrabalho {

	@Override
	public List<LocalTrabalho> obterTodos() {
		List<LocalTrabalho> localTrabalho = new ArrayList<>();

		TypedQuery<LocalTrabalho> query = em.createQuery(
				"SELECT s FROM LocalTrabalho s ORDER BY s.nome",
				LocalTrabalho.class);
		try {
			localTrabalho = query.getResultList();
		} catch (Exception e) {
			logger.error("Não foi possível obter a lista de LocalTrabalho. \nDetalhe:"
					+ e);
		}
		return localTrabalho;
	}
}

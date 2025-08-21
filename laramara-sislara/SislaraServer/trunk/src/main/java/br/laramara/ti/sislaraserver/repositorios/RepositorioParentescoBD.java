package br.laramara.ti.sislaraserver.repositorios;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import br.laramara.ti.sislaraserver.dominio.familiar.Parentesco;

@Repository
public class RepositorioParentescoBD extends RepositorioDB<Parentesco> implements
		RepositorioParentesco {

	public List<Parentesco> obterTodos() {
		List<Parentesco> parentesco = new ArrayList<>();

		TypedQuery<Parentesco> query = em.createQuery(
				"SELECT p FROM Parentesco p", Parentesco.class);
		try {
			parentesco = query.getResultList();
		} catch (Exception e) {
			logger.error("Não foi possível obter a lista de parentesco. \nDetalhe:"
					+ e);
		}
		return parentesco;
	}
}

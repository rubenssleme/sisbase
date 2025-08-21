package br.laramara.ti.sislaraserver.repositorios;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import br.laramara.ti.sislaraserver.dominio.trabalho.Cargo;

@Repository
public class RepositorioCargoBD extends RepositorioDB<Cargo> implements
		RepositorioCargo {

	@Override
	public List<Cargo> obterTodos() {
		List<Cargo> cargo = new ArrayList<>();

		TypedQuery<Cargo> query = em.createQuery(
				"SELECT s FROM Cargo s ORDER BY s.nome", Cargo.class);
		try {
			cargo = query.getResultList();
		} catch (Exception e) {
			logger.error("Não foi possível obter a lista de Cargo. \nDetalhe:"
					+ e);
		}
		return cargo;
	}
}

package br.laramara.ti.sislaraserver.repositorios;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import br.laramara.ti.sislaraserver.dominio.escola.DreCefai;

@Repository
public class RepositorioDreCefaiBD extends RepositorioDB<DreCefai> implements
		RepositorioDreCefai {

	public List<DreCefai> obterTodos() {
		List<DreCefai> dreCefais = new ArrayList<>();

		TypedQuery<DreCefai> query = em.createQuery(
				"SELECT d FROM DreCefai d ORDER BY d.nome", DreCefai.class);
		try {
			dreCefais = query.getResultList();
		} catch (Exception e) {
			logger.error("Não foi possível obter a lista de DreCefai. \nDetalhe:"
					+ e);
		}
		return dreCefais;
	}
}

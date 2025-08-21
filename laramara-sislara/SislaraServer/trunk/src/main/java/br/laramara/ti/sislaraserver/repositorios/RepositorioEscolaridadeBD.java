package br.laramara.ti.sislaraserver.repositorios;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import br.laramara.ti.sislaraserver.dominio.escola.Escolaridade;

@Repository
public class RepositorioEscolaridadeBD extends RepositorioDB<Escolaridade> implements
		RepositorioEscolaridade {

	public List<Escolaridade> obterTodos() {
		List<Escolaridade> escolaridade = new ArrayList<>();

		TypedQuery<Escolaridade> query = em.createQuery(
				"SELECT e FROM Escolaridade e", Escolaridade.class);
		try {
			escolaridade = query.getResultList();
		} catch (Exception e) {
			logger.error("Não foi possível obter a lista de escolaridades. \nDetalhe:"
					+ e);
		}
		return escolaridade;
	}

}

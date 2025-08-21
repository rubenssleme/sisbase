package br.laramara.ti.sislaraserver.repositorios;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import br.laramara.ti.sislaraserver.dominio.usuario.Beneficio;
@Repository
public class RepositorioBenefecioBD extends RepositorioDB<Beneficio> implements
		RepositorioBeneficio {

	@Override
	public List<Beneficio> obterTodos() {
		List<Beneficio> beneficios = new ArrayList<>();

		TypedQuery<Beneficio> query = em.createQuery(
				"SELECT b FROM Beneficio b ORDER BY b.descricao",
				Beneficio.class);
		try {
			beneficios = query.getResultList();
		} catch (Exception e) {
			logger.error("Não foi possível obter a lista de Beneficios. \nDetalhe:"
					+ e);
		}
		return beneficios;
	}

}

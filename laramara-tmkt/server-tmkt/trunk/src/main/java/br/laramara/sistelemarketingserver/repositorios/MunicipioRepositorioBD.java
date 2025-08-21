package br.laramara.sistelemarketingserver.repositorios;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import br.laramara.sistelemarketingserver.dominio.Municipio;

@Repository
public class MunicipioRepositorioBD  extends RepositorioDB<Municipio> implements MunicipioRepositorio {

	@Override
	public List<Municipio> obterTodos(Long idEstado) {
		List<Municipio> municipios = new ArrayList<>();
		try {
			TypedQuery<Municipio> query = em.createQuery("SELECT c FROM Municipio c WHERE c.estado.id = :estado ORDER BY c.descricao",
					Municipio.class);
			query.setParameter("estado", idEstado);
			municipios = query.getResultList();
		} catch (Exception e) {
			logger.fatal("Erro durante pesquisa de todos os municípios.\n Detalhes:" + e);
		}
		return municipios;
	}

}

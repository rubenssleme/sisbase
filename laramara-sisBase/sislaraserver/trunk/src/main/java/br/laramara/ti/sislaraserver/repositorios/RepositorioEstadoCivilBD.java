package br.laramara.ti.sislaraserver.repositorios;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import br.laramara.ti.sislaraserver.dominio.usuario.EstadoCivil;

@Repository
public class RepositorioEstadoCivilBD extends RepositorioDB<EstadoCivil> implements
		RepositorioEstadoCivil {

	public List<EstadoCivil> obterTodos() {
		List<EstadoCivil> estadoCivil = new ArrayList<>();

		TypedQuery<EstadoCivil> query = em.createQuery(
				"SELECT e FROM EstadoCivil e", EstadoCivil.class);
		try {
			estadoCivil = query.getResultList();
		} catch (Exception e) {
			logger.error("N�o foi poss�vel obter a lista de Estado Civil. \nDetalhe:"
					+ e);
		}
		return estadoCivil;
	}
}

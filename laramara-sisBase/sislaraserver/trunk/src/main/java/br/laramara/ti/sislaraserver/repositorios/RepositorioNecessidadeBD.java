package br.laramara.ti.sislaraserver.repositorios;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import br.laramara.ti.sislaraserver.dominio.usuario.Necessidade;

@Repository
public class RepositorioNecessidadeBD extends RepositorioDB<Necessidade> implements RepositorioNecessidade {

	@Override
	public List<Necessidade> obterTodos() {
		List<Necessidade> necessidades = new ArrayList<>();

		TypedQuery<Necessidade> query = em.createQuery("SELECT s FROM Necessidade s ORDER BY s.descricao", Necessidade.class);
		try {
			necessidades = query.getResultList();
		} catch (Exception e) {
			logger.error("Não foi possível obter a lista de Necessidades. \nDetalhe:" + e);
		}
		return necessidades;
	}
}

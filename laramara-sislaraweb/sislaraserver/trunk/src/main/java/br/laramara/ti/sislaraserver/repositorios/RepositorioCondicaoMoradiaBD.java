package br.laramara.ti.sislaraserver.repositorios;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import br.laramara.ti.sislaraserver.dominio.usuario.CondicaoMoradia;

@Repository
public class RepositorioCondicaoMoradiaBD extends RepositorioDB<CondicaoMoradia> implements RepositorioCondicaoMoradia {

	@Override
	public List<CondicaoMoradia> obterTodos() {
		List<CondicaoMoradia> condicaoMoradia = new ArrayList<>();

		TypedQuery<CondicaoMoradia> query = em.createQuery("SELECT s FROM CondicaoMoradia s ORDER BY s.descricao", CondicaoMoradia.class);
		try {
			condicaoMoradia = query.getResultList();
		} catch (Exception e) {
			logger.error("Não foi possível obter a lista de Condições de Moradia. \nDetalhe:" + e);
		}
		return condicaoMoradia;
	}

}

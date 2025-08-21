package br.laramara.ti.sislaraserver.repositorios;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import br.laramara.ti.sislaraserver.dominio.usuario.Expectativa;

@Repository
public class RepositorioExpectativaBD extends RepositorioDB<Expectativa> implements RepositorioExpectativa {

	@Override
	public List<Expectativa> obterTodos() {
		List<Expectativa> expectativas = new ArrayList<>();

		TypedQuery<Expectativa> query = em.createQuery("SELECT s FROM Expectativa s ORDER BY s.descricao", Expectativa.class);
		try {
			expectativas = query.getResultList();
		} catch (Exception e) {
			logger.error("Não foi possível obter a lista de Expectativas. \nDetalhe:" + e);
		}
		return expectativas;
	}

}

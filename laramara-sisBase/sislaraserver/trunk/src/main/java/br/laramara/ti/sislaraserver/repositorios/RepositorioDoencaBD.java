package br.laramara.ti.sislaraserver.repositorios;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import br.laramara.ti.sislaraserver.dominio.usuario.Doenca;

@Repository
public class RepositorioDoencaBD extends RepositorioDB<Doenca> implements
		RepositorioDoenca {

	@Override
	public List<Doenca> obterTodos() {
		List<Doenca> doenca = new ArrayList<>();

		TypedQuery<Doenca> query = em.createQuery(
				"SELECT s FROM Doenca s ORDER BY s.descricao", Doenca.class);
		try {
			doenca = query.getResultList();
		} catch (Exception e) {
			logger.error("Não foi possível obter a lista de Doenças. \nDetalhe:"
					+ e);
		}
		return doenca;
	}
}

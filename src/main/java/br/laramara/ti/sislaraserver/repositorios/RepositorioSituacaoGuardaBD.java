package br.laramara.ti.sislaraserver.repositorios;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import br.laramara.ti.sislaraserver.dominio.usuario.SituacaoGuarda;
@Repository
public class RepositorioSituacaoGuardaBD extends RepositorioDB<SituacaoGuarda> implements
		RepositorioSituacaoGuarda {

	@Override
	public List<SituacaoGuarda> obterTodos() {
		List<SituacaoGuarda> parentesco = new ArrayList<>();

		TypedQuery<SituacaoGuarda> query = em.createQuery(
				"SELECT s FROM SituacaoGuarda s ORDER BY s.descricao",
				SituacaoGuarda.class);
		try {
			parentesco = query.getResultList();
		} catch (Exception e) {
			logger.error("Não foi possível obter a lista de Situações de Guarda. \nDetalhe:"
					+ e);
		}
		return parentesco;
	}
}

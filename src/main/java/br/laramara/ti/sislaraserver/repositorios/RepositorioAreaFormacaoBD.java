package br.laramara.ti.sislaraserver.repositorios;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import br.laramara.ti.sislaraserver.dominio.escola.AreaFormacao;

@Repository
public class RepositorioAreaFormacaoBD extends RepositorioDB<AreaFormacao>
		implements RepositorioAreaFormacao {

	@Override
	public List<AreaFormacao> obterTodos() {
		List<AreaFormacao> areaFormacao = new ArrayList<>();

		TypedQuery<AreaFormacao> query = em.createQuery(
				"SELECT s FROM AreaFormacao s ORDER BY s.nome",
				AreaFormacao.class);
		try {
			areaFormacao = query.getResultList();
		} catch (Exception e) {
			logger.error("Não foi possível obter a lista de Area de Formacao. \nDetalhe:"
					+ e);
		}
		return areaFormacao;
	}
}

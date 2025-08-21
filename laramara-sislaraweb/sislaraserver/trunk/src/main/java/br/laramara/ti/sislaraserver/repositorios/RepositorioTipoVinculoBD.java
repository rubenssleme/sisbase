package br.laramara.ti.sislaraserver.repositorios;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import br.laramara.ti.sislaraserver.dominio.atendimento.TipoVinculo;

@Repository
public class RepositorioTipoVinculoBD extends RepositorioDB<TipoVinculo>
		implements RepositorioTipoVinculo {

	@Override
	public List<TipoVinculo> obterTodos() {
		List<TipoVinculo> tipoVinculo = new ArrayList<>();
		try {
			TypedQuery<TipoVinculo> query = em.createQuery(
					"SELECT i FROM TipoVinculo i ORDER BY i.descricao",
					TipoVinculo.class);
			tipoVinculo = query.getResultList();
		} catch (Exception e) {
			logger.fatal("Erro durante pesquisa de Tipo de Vínculo.\n Detalhes:"
					+ e);
		}
		return tipoVinculo;
	}
}

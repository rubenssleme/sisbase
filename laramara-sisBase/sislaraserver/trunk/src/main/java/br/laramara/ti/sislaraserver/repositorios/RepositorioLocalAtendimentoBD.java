package br.laramara.ti.sislaraserver.repositorios;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import br.laramara.ti.sislaraserver.dominio.grupo.LocalAtendimento;

@Repository
public class RepositorioLocalAtendimentoBD extends RepositorioDB<LocalAtendimento> implements
		RepositorioLocalAtendimento {

	@Override
	public List<LocalAtendimento> obterTodos() {
		List<LocalAtendimento> localAtendimentos = new ArrayList<>();
		try {
			TypedQuery<LocalAtendimento> query = em.createQuery(
					"SELECT i FROM LocalAtendimento i ORDER BY i.nome",
					LocalAtendimento.class);
			localAtendimentos = query.getResultList();
		} catch (Exception e) {
			logger.fatal("Erro durante pesquisa de Locais de Atendimento.\n Detalhes:"
					+ e);
		}
		return localAtendimentos;
	}

}

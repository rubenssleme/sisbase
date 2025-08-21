package br.laramara.ti.sislaraserver.repositorios;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import br.laramara.ti.sislaraserver.dominio.grupo.TipificacaoServico;

@Repository
public class RepositorioTipificacaoServicoBD extends
		RepositorioDB<TipificacaoServico> implements
		RepositorioTipificacaoServico {

	@Override
	public List<TipificacaoServico> obterTodos() {
		List<TipificacaoServico> tipificacaoServico = new ArrayList<>();
		try {
			TypedQuery<TipificacaoServico> query = em.createQuery(
					"SELECT i FROM TipificacaoServico i ORDER BY i.nome",
					TipificacaoServico.class);
			tipificacaoServico = query.getResultList();
		} catch (Exception e) {
			logger.fatal("Erro durante pesquisa de Tipificação de Serviços.\n Detalhes:"
					+ e);
		}
		return tipificacaoServico;
	}

}

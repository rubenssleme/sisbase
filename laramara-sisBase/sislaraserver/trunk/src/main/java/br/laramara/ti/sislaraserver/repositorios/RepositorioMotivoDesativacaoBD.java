package br.laramara.ti.sislaraserver.repositorios;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import br.laramara.ti.sislaraserver.dominio.contribuicao.MotivoDesativacao;

@Repository
public class RepositorioMotivoDesativacaoBD extends RepositorioDB<MotivoDesativacao>
		implements RepositorioMotivoDesativacao {

	@Override
	public List<MotivoDesativacao> obterTodos() {
		List<MotivoDesativacao> motivosDesativacao = new ArrayList<>();
		try {
			TypedQuery<MotivoDesativacao> query = em.createQuery(
					"SELECT i FROM MotivoDesativacao i ORDER BY i.descricao",
					MotivoDesativacao.class);
			motivosDesativacao = query.getResultList();
		} catch (Exception e) {
			logger.fatal("Erro durante pesquisa de Motivos de Desativcao do Contribuinte.\n Detalhes:"
					+ e);
		}
		return motivosDesativacao;
	}
}

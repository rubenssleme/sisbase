package br.laramara.ti.sislaraserver.repositorios;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.laramara.ti.sislaraserver.dominio.retirada.Retirada;
import br.laramara.ti.sislaraserver.dominio.retirada.StatusRetirada;

@Repository
public class RepositorioRetiradaBD extends RepositorioDB<Retirada> implements
		RepositorioRetirada {

	@Override
	public List<Retirada> obterRetiradas(Long prontuario, StatusRetirada statusRetirada) {
		List<Retirada> retiradas = new ArrayList<>();

		try {
			TypedQuery<Retirada> query = em.createQuery(
					"SELECT r FROM Retirada r" +
					" JOIN r.historicoStatus h"
							+ " WHERE r.prontuario = :prontuario " +
							" AND h.dataFinalVigencia IS NULL" +
							" AND h.status = :status",
					Retirada.class);
			query.setParameter("prontuario", prontuario);
			query.setParameter("status", statusRetirada);
			retiradas = query.getResultList();
		} catch (Exception e) {
			logger.fatal("Erro durante pesquisa de Retirada por Prontuário.\n Detalhes:"
					+ e);
		}

		return retiradas;
	}

	@Transactional
	public Retirada salvar(Retirada retiradaASalvar) {
		String acao;
		try {
			if (retiradaASalvar.getId() == null) {
				em.persist(retiradaASalvar);
				acao = "Inclusão";
			} else {
				em.merge(retiradaASalvar);
				acao = "Alteração";
			}
			logger.info(acao + " do " + retiradaASalvar
					+ " realizada com sucesso.");
		} catch (Exception e) {
			logger.error("Ocorreu algum erro durante o armazenamento do "
					+ retiradaASalvar + ". \nDetalhes: " + e);
		}
		return retiradaASalvar;
	}
}

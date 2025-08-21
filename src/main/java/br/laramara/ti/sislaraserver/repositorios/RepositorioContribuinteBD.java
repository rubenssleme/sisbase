package br.laramara.ti.sislaraserver.repositorios;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.laramara.ti.sislaraserver.dominio.contribuicao.Contribuinte;

@Repository
public class RepositorioContribuinteBD extends RepositorioDB<Contribuinte>
		implements RepositorioContribuinte {

	@Transactional
	public Contribuinte salvar(Contribuinte contribuinteASalvar) {
		String acao = "";
		try {
			if (contribuinteASalvar.getId() == null) {
				em.persist(contribuinteASalvar);
				acao = "Inclusão";
			} else {
				em.merge(contribuinteASalvar);
				acao = "Alteração";
			}
			logger.info(acao + " do " + contribuinteASalvar
					+ " realizada com sucesso.");
		} catch (Exception e) {
			logger.error("Ocorreu algum erro durante o armazenamento do "
					+ contribuinteASalvar + ". \nDetalhes: " + e);
		}
		return contribuinteASalvar;
	}

	@Override
	public List<Contribuinte> obterTodos() {
		List<Contribuinte> contribuintes = new ArrayList<>();

		TypedQuery<Contribuinte> query = em.createQuery(
				"SELECT b FROM Contribuinte b ORDER BY b.id",
				Contribuinte.class);
		try {
			contribuintes = query.getResultList();
		} catch (Exception e) {
			logger.error("Não foi possível obter a lista de Contribuintes. \nDetalhe:"
					+ e);
		}
		return contribuintes;
	}

	@Override
	public List<Contribuinte> obterPorNome(String nome) {
		List<Contribuinte> contribuintes = new ArrayList<>();
		try {
			TypedQuery<Contribuinte> query = em
					.createQuery(
							"SELECT i FROM Contribuinte i WHERE LOWER(remover_acento(i.nomeEmpresa)) LIKE LOWER(remover_acento(:nome)) "
									+ "ORDER BY i.nomeEmpresa", Contribuinte.class);
			query.setParameter("nome", "%" + nome.toLowerCase() + "%");
			contribuintes = query.getResultList();
		} catch (Exception e) {
			logger.fatal("Erro durante pesquisa de Contribuinte por nome.\n Detalhes:"
					+ e);
		}
		return contribuintes;
	}
}

package br.laramara.sistelemarketingserver.repositorios;

import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import br.laramara.sistelemarketingserver.dominio.doacao.Doacao;

@Repository
public class DoacaoRepositorioBD extends RepositorioDB<Doacao> implements DoacaoRepositorio {

	@Override
	public Doacao obter(Long id) {
		Doacao doacao = null;
		if (id != null) {
			try {
				TypedQuery<Doacao> query = em.createQuery("SELECT c FROM Doacao c WHERE c.id = :id", Doacao.class);
				query.setParameter("id", id);
				doacao = query.getSingleResult();
			} catch (Exception e) {
				logger.fatal("Erro durante pesquisa de doacao.\n Detalhes:" + e);
			}
		}
		return doacao;
	}

	@Transactional
	public Doacao salvar(Doacao doacao) {
		Doacao doacaoSalvo = null;
		try {
			doacaoSalvo = em.merge(doacao);
			logger.info("Edição do " + doacaoSalvo + " realizada com sucesso.");
		} catch (Exception e) {
			logger.error("Ocorreu algum erro durante o armazenamento do " + doacaoSalvo + ". \nDetalhes: " + e);
		}
		return doacaoSalvo;
	}
}

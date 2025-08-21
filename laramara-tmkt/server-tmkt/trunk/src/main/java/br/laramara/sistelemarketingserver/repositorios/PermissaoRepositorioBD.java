package br.laramara.sistelemarketingserver.repositorios;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import br.laramara.sistelemarketingserver.dominio.seguranca.Permissao;

@Repository
public class PermissaoRepositorioBD extends RepositorioDB<Permissao> implements PermissaoRepositorio {

	@Override
	public List<Permissao> obterTodos() {
		List<Permissao> permissao = new ArrayList<>();
		try {
			TypedQuery<Permissao> query = em.createQuery(
					"SELECT c FROM Permissao c ORDER BY c.descricao",
					Permissao.class);
			permissao = query.getResultList();
		} catch (Exception e) {
			logger.fatal("Erro durante pesquisa de todos os níveis.\n Detalhes:"
					+ e);
		}
		return permissao;
	}

}

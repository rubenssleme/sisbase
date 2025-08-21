package br.laramara.sistelemarketingserver.repositorios;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import br.laramara.sistelemarketingserver.dominio.Estado;

@Repository
public class EstadoRepositorioBD extends RepositorioDB<Estado> implements EstadoRepositorio {

	@Override
	public List<Estado> obterTodos() {
		List<Estado> estados = new ArrayList<>();
		try {
			TypedQuery<Estado> query = em.createQuery("SELECT c FROM Estado c ORDER BY c.descricao", Estado.class);
			estados = query.getResultList();
		} catch (Exception e) {
			logger.fatal("Erro durante pesquisa de todos os estados.\n Detalhes:" + e);
		}
		return estados;
	}
}

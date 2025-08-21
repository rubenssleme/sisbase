package br.laramara.sistelemarketingserver.repositorios;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import br.laramara.sistelemarketingserver.dominio.Bairro;
import br.laramara.sistelemarketingserver.dominio.Logradouro;

@Repository
public class LogradouroRepositorioBD extends RepositorioDB<Logradouro> implements LogradouroRepositorio {

	@Override
	public List<Bairro> obterTodos(Long idMunicipio) {
		List<Bairro> bairros = new ArrayList<>();
		List<String> logradouros = new ArrayList<>();
		try {
			TypedQuery<String> query = em.createQuery("SELECT DISTINCT c.bairro FROM Logradouro c "
					+ "WHERE c.municipio.id = :municipio "
					+ "ORDER BY c.bairro",
					String.class);
			query.setParameter("municipio", idMunicipio);
			logradouros = query.getResultList();
			logradouros.stream().filter(logradouro -> !logradouro.isEmpty())
					.forEach(logradouro -> bairros.add(new Bairro(logradouro)));
		} catch (Exception e) {
			logger.fatal("Erro durante pesquisa de todos os logradouros.\n Detalhes:" + e);
		}
		return bairros;
	}
}

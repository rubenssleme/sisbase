package br.laramara.sistelemarketingserver.repositorios;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import br.laramara.sistelemarketingserver.dominio.Municipio;
import br.laramara.sistelemarketingserver.dominio.contato.Contato;
import br.laramara.sistelemarketingserver.dominio.contato.StatusContato;

@Repository
public class ContatoRepositorioBD extends RepositorioDB<Contato> implements ContatoRepositorio {
	
	private static final String SELECAO_CONTATO_NOVO_DE_MUNICIPIO_E_BAIRRO = "FROM Contato c INNER JOIN c.historicoStatus hissta "
			+ "WHERE hissta.status = :status AND hissta.dataFinalVigencia is NULL AND c.cep.municipio = :municipio AND c.cep.bairro = :bairro";
	private Long itensPorPagina = new Long(100);
	
	@Override	
	public Long obterNumeroPaginasNovosPor(Municipio municipio, String bairro) {
		try {
			TypedQuery<Long> query = em.createQuery("SELECT count(c.id) " + SELECAO_CONTATO_NOVO_DE_MUNICIPIO_E_BAIRRO,
					Long.class);
			query.setParameter("municipio", municipio);
			query.setParameter("bairro", bairro);
			query.setParameter("status", StatusContato.NOVO);
			
			return ((query.getSingleResult() / itensPorPagina) + 1);
		} catch (Exception e) {
			logger.fatal("Erro durante pesquisa de contatos.\n Detalhes:" + e);
		}
		return new Long(0);
	}
	
	@Override
	public List<Contato> obterNovosPor(Municipio municipio, String bairro, Integer pagina) {
		List<Contato> contatos = new ArrayList<>();
		try {
			TypedQuery<Contato> query = em.createQuery("SELECT c " + SELECAO_CONTATO_NOVO_DE_MUNICIPIO_E_BAIRRO,
					Contato.class);
			query.setParameter("municipio", municipio);
			query.setParameter("bairro", bairro);
			query.setParameter("status", StatusContato.NOVO);
			
			query.setFirstResult((int)((pagina-1) * itensPorPagina));
			query.setMaxResults(itensPorPagina.intValue());
			
			contatos = query.getResultList();
		} catch (Exception e) {
			logger.fatal("Erro durante pesquisa de contatos.\n Detalhes:" + e);
		}
		return contatos;
	}
}

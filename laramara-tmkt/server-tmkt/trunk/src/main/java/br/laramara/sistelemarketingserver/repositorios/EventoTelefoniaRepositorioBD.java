package br.laramara.sistelemarketingserver.repositorios;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import br.laramara.sistelemarketingserver.dominio.contato.EventoTelefonia;

@Repository
public class EventoTelefoniaRepositorioBD extends RepositorioDB<EventoTelefonia> implements EventoTelefoniaRepositorio {

	@Override
	public Long obterUltimoIdentificador() {
		Long resultado = null;

		Query query = em.createQuery("SELECT MAX(e.id) FROM EventoTelefonia e", Long.class);
		try {
			resultado = (Long) query.getSingleResult();
		} catch (Exception e) {
			logger.error(
					"Ocorreu um erro durante a consulta de ultimo identificador de evento de telefonia. \nDetalhes: "
							+ e);
		}
		return resultado;
	}
}

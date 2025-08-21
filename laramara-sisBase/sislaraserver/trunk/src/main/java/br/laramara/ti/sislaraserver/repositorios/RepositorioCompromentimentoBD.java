package br.laramara.ti.sislaraserver.repositorios;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import br.laramara.ti.sislaraserver.dominio.usuario.Comprometimento;

@Repository
public class RepositorioCompromentimentoBD extends RepositorioDB<Comprometimento>
		implements RepositorioComprometimento {

	@Override
	public List<Comprometimento> obterTodos() {
		List<Comprometimento> comprometimento = new ArrayList<>();

		TypedQuery<Comprometimento> query = em.createQuery("SELECT s FROM Comprometimento s ORDER BY s.descricao",
				Comprometimento.class);
		try {
			comprometimento = query.getResultList();
		} catch (Exception e) {
			logger.error("Não foi possível obter a lista de Comprometimento. \nDetalhe:" + e);
		}
		return comprometimento;
	}
}

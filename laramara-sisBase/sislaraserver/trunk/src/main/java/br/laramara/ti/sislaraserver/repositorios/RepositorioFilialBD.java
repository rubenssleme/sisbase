package br.laramara.ti.sislaraserver.repositorios;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.laramara.ti.sislaraserver.dominio.doacao.Filial;

@Repository
public class RepositorioFilialBD extends RepositorioDB<Filial> implements RepositorioFilial {

	@Transactional
	public List<Filial> obterTodos() {
		List<Filial> filial = new ArrayList<>();

		TypedQuery<Filial> query = em.createQuery("SELECT e FROM Filial e", Filial.class);
		try {
			filial = query.getResultList();
		} catch (Exception e) {
			logger.error("Não foi possível obter a lista de Filial. \nDetalhe:" + e);
		}
		return filial;
	}
}

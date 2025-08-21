package br.laramara.ti.sislaraserver.repositorios;

import org.springframework.stereotype.Repository;

import br.laramara.ti.sislaraserver.dominio.familiar.Familiar;

@Repository
public class RepositorioFamiliarBD extends RepositorioDB<Familiar> implements
		RepositorioFamiliar {

	public Familiar obterPorId(Long id) {
		Familiar familiar = null;
		try {
			familiar = em.find(Familiar.class, id);
		} catch (Exception e) {
			logger.error("Não foi possível obter a Familiar com o id " + id
					+ ".\n Detalhe:" + e);
		}
		return familiar;
	}

}

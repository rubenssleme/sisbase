package br.laramara.ti.sislaraserver.repositorios;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import br.laramara.ti.sislaraserver.dominio.usuario.InfraestruturaBasica;

@Repository
public class RepositorioInfraestruturaBasicaBD extends RepositorioDB<InfraestruturaBasica> implements RepositorioInfraestruturaBasica{

	@Override
	public List<InfraestruturaBasica> obterTodos() {
		List<InfraestruturaBasica> infraestruturaBasica = new ArrayList<>();

		TypedQuery<InfraestruturaBasica> query = em.createQuery("SELECT s FROM InfraestruturaBasica s ORDER BY s.descricao", InfraestruturaBasica.class);
		try {
			infraestruturaBasica = query.getResultList();
		} catch (Exception e) {
			logger.error("Não foi possível obter a lista de Infraestrutura basica. \nDetalhe:" + e);
		}
		return infraestruturaBasica;
	}

}

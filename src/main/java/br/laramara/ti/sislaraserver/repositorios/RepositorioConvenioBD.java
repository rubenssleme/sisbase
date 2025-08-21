package br.laramara.ti.sislaraserver.repositorios;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import br.laramara.ti.sislaraserver.dominio.usuario.Convenio;

@Repository
public class RepositorioConvenioBD extends RepositorioDB<Convenio> implements
		RepositorioConvenio {

	@Override
	public List<Convenio> obterTodos() {
		List<Convenio> convenio = new ArrayList<>();

		TypedQuery<Convenio> query = em
				.createQuery("SELECT s FROM Convenio s ORDER BY s.descricao",
						Convenio.class);
		try {
			convenio = query.getResultList();
		} catch (Exception e) {
			logger.error("Não foi possível obter a lista de Convenio. \nDetalhe:"
					+ e);
		}
		return convenio;
	}

}

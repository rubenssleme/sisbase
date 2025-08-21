package br.laramara.ti.sislaraserver.repositorios;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import br.laramara.ti.sislaraserver.dominio.usuario.Deficiencia;

@Repository
public class RepositorioDeficienciaBD extends RepositorioDB<Deficiencia> implements RepositorioDeficiencia {
	
	@Override
	public List<Deficiencia> obterTodos() {
		List<Deficiencia> deficiencia = new ArrayList<>();
		
		TypedQuery<Deficiencia> query = em
				.createQuery("SELECT s FROM Deficiencia s ORDER BY s.descricao",
						Deficiencia.class);
		try {
			deficiencia = query.getResultList();
		} catch (Exception e) {
			logger.error("Não foi possível obter a lista de Deficiência. \nDetalhe:"
					+ e);
		}
		return deficiencia;
	}
		
}



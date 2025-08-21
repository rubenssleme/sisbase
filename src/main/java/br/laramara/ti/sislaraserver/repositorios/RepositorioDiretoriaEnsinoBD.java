package br.laramara.ti.sislaraserver.repositorios;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import br.laramara.ti.sislaraserver.dominio.escola.DiretoriaEnsino;

@Repository
public class RepositorioDiretoriaEnsinoBD extends RepositorioDB<DiretoriaEnsino> implements
		RepositorioDiretoriaEnsino {

	public List<DiretoriaEnsino> obterTodos() {
		List<DiretoriaEnsino> diretoriaEnsino = new ArrayList<>();

		TypedQuery<DiretoriaEnsino> query = em.createQuery(
				"SELECT d FROM DiretoriaEnsino d ORDER BY d.nome",
				DiretoriaEnsino.class);
		try {
			diretoriaEnsino = query.getResultList();
		} catch (Exception e) {
			logger.error("Não foi possível obter a lista de Diretorias de Ensino. \nDetalhe:"
					+ e);
		}
		return diretoriaEnsino;
	}
}

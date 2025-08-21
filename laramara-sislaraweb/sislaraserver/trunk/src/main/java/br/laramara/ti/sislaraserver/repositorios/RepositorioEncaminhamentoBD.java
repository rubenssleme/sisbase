package br.laramara.ti.sislaraserver.repositorios;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import br.laramara.ti.sislaraserver.dominio.usuario.Encaminhamento;

@Repository
public class RepositorioEncaminhamentoBD extends RepositorioDB<Encaminhamento> implements RepositorioEncaminhamento {

	@Override
	public List<Encaminhamento> obterTodos() {
		List<Encaminhamento> encaminhamentos = new ArrayList<>();

		TypedQuery<Encaminhamento> query = em.createQuery("SELECT s FROM Encaminhamento s ORDER BY s.descricao",
				Encaminhamento.class);
		try {
			encaminhamentos = query.getResultList();
		} catch (Exception e) {
			logger.error("Não foi possível obter a lista de Encaminhamento. \nDetalhe:" + e);
		}
		return encaminhamentos;
	}
}

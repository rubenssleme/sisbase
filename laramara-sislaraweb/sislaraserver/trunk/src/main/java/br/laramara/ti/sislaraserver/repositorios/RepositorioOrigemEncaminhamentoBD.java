package br.laramara.ti.sislaraserver.repositorios;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import br.laramara.ti.sislaraserver.dominio.usuario.OrigemEncaminhamento;

@Repository
public class RepositorioOrigemEncaminhamentoBD extends RepositorioDB<OrigemEncaminhamento>
		implements RepositorioOrigemEncaminhamento {

	@Override
	public List<OrigemEncaminhamento> obterTodos() {
		List<OrigemEncaminhamento> origemEncaminhamento = new ArrayList<>();

		TypedQuery<OrigemEncaminhamento> query = em
				.createQuery("SELECT s FROM OrigemEncaminhamento s ORDER BY s.descricao", OrigemEncaminhamento.class);
		try {
			origemEncaminhamento = query.getResultList();
		} catch (Exception e) {
			logger.error("Não foi possível obter a lista de OrigemEncaminhamento. \nDetalhe:" + e);
		}
		return origemEncaminhamento;
	}
}

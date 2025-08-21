package br.laramara.ti.sislaraserver.repositorios;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import br.laramara.ti.sislaraserver.dominio.usuario.RedeEncaminhamento;

@Repository
public class RepositorioRedeEncaminhamentoBD extends RepositorioDB<RedeEncaminhamento>
		implements RepositorioRedeEncaminhamento {

	@Override
	public List<RedeEncaminhamento> obterTodos() {
		List<RedeEncaminhamento> redeEncaminhamento = new ArrayList<>();

		TypedQuery<RedeEncaminhamento> query = em.createQuery("SELECT s FROM RedeEncaminhamento s ORDER BY s.descricao",
				RedeEncaminhamento.class);
		try {
			redeEncaminhamento = query.getResultList();
		} catch (Exception e) {
			logger.error("Não foi possível obter a lista de RedeEncaminhamento. \nDetalhe:" + e);
		}
		return redeEncaminhamento;
	}
}

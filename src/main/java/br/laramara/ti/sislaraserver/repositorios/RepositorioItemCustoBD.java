package br.laramara.ti.sislaraserver.repositorios;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import br.laramara.ti.sislaraserver.dominio.usuario.ItemCusto;

@Repository
public class RepositorioItemCustoBD extends RepositorioDB<ItemCusto> implements
		RepositorioItemCusto {

	private List<ItemCusto> obterTodos(String sql) {
		List<ItemCusto> itensCusto = new ArrayList<>();

		TypedQuery<ItemCusto> query = em.createQuery(sql, ItemCusto.class);
		try {
			itensCusto = query.getResultList();
		} catch (Exception e) {
			logger.error("Não foi possível obter a lista de Item de Custo. \nDetalhe:"
					+ e);
		}
		return itensCusto;
	}

	@Override
	public List<ItemCusto> obterTodosDaDoenca() {
		return obterTodos("SELECT d FROM ItemCusto d WHERE d.doenca is TRUE ORDER BY d.descricao");
	}

	@Override
	public List<ItemCusto> obterTodosDaDeficiencia() {
		return obterTodos("SELECT d FROM ItemCusto d WHERE d.deficiencia is TRUE ORDER BY d.descricao");
	}
}

package br.laramara.ti.sislaraserver.repositorios;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import br.laramara.ti.sislaraserver.dominio.usuario.TipoConstrucao;

@Repository
public class RepositorioTipoConstrucaoBD extends RepositorioDB<TipoConstrucao> implements RepositorioTipoConstrucao {

	@Override
	public List<TipoConstrucao> obterTodos() {
		List<TipoConstrucao> tipoConstrucoes = new ArrayList<>();

		TypedQuery<TipoConstrucao> query = em.createQuery("SELECT s FROM TipoConstrucao s ORDER BY s.descricao", TipoConstrucao.class);
		try {
			tipoConstrucoes = query.getResultList();
		} catch (Exception e) {
			logger.error("Não foi possível obter a lista de Tipo de Construcoes. \nDetalhe:" + e);
		}
		return tipoConstrucoes;
	}

}

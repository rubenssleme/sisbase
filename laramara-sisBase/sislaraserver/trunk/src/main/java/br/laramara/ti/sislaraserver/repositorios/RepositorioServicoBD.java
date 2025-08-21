package br.laramara.ti.sislaraserver.repositorios;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import br.laramara.ti.sislaraserver.dominio.usuario.Servico;

@Repository
public class RepositorioServicoBD extends RepositorioDB<Servico> implements RepositorioServico {

	@Override
	public List<Servico> obterTodos() {
		List<Servico> servicos = new ArrayList<>();

		TypedQuery<Servico> query = em.createQuery("SELECT s FROM Servico s ORDER BY s.descricao", Servico.class);
		try {
			servicos = query.getResultList();
		} catch (Exception e) {
			logger.error("Não foi possível obter a lista de Servicos. \nDetalhe:" + e);
		}
		return servicos;
	}

}

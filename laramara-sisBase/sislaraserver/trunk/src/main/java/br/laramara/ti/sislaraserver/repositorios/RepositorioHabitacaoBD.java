package br.laramara.ti.sislaraserver.repositorios;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import br.laramara.ti.sislaraserver.dominio.usuario.Habitacao;

@Repository
public class RepositorioHabitacaoBD extends RepositorioDB<Habitacao> implements RepositorioHabitacao {

	@Override
	public List<Habitacao> obterTodos() {
		List<Habitacao> habitacoes = new ArrayList<>();

		TypedQuery<Habitacao> query = em.createQuery("SELECT s FROM Habitacao s ORDER BY s.descricao", Habitacao.class);
		try {
			habitacoes = query.getResultList();
		} catch (Exception e) {
			logger.error("Não foi possível obter a lista de Habitações. \nDetalhe:" + e);
		}
		return habitacoes;
	}
}

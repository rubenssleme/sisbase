package br.laramara.ti.sismovimentacaoserver.repositorios;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.laramara.ti.sismovimentacaoserver.dominio.seguranca.Perfil;

@Repository
public class RepositorioPerfilBD extends RepositorioDB<Perfil> implements
		RepositorioPerfil {

	public List<Perfil> obterTodos() {
		List<Perfil> perfis = new ArrayList<>();
		try {
			TypedQuery<Perfil> query = em.createQuery("SELECT p FROM Perfil p",
					Perfil.class);
			perfis = query.getResultList();
		} catch (Exception e) {
			logger.error("Não foi possível obter a lista de Perfis. \nDetalhe:"
					+ e);
		}
		return perfis;
	}

	@Transactional
	public Perfil salvar(Perfil perfil) {
		String acao = "";
		if (perfil.getId() == null) {
			em.persist(perfil);
			acao = "Inclusão";
		} else {
			em.merge(perfil);
			acao = "Alteração";
		}
		logger.info(acao + " do " + perfil + " realizada com sucesso.");
		return perfil;
	}
}

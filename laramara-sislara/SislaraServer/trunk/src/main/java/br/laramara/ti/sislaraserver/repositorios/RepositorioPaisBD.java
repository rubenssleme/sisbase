package br.laramara.ti.sislaraserver.repositorios;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import br.laramara.ti.sislaraserver.dominio.endereco.EnderecoCEP;
import br.laramara.ti.sislaraserver.dominio.endereco.Pais;

@Repository
public class RepositorioPaisBD extends RepositorioDB<Pais> implements RepositorioPais {

	public List<Pais> obterTodos() {
		List<Pais> paises = new ArrayList<>();

		TypedQuery<Pais> query = em.createQuery("SELECT p FROM Pais p ORDER BY p.nome",
				Pais.class);
		try {
			paises = query.getResultList();
		} catch (Exception e) {
			logger.error("Não foi possível obter a lista de paises. \nDetalhe:"
					+ e);
		}
		return paises;
	}

	@Override
	public EnderecoCEP carregarPais(EnderecoCEP enderecoCEP) {
		try {
			TypedQuery<Pais> query = em.createQuery(
					"SELECT p FROM Pais p WHERE nome LIKE :nome", Pais.class);
			query.setParameter("nome", enderecoCEP.getPais().getNome());
			enderecoCEP.setPais(query.getSingleResult());
		} catch (Exception e) {
			logger.error("Não foi possível obter o País. \nDetalhe:" + e);
		}
		return enderecoCEP;
	}
}

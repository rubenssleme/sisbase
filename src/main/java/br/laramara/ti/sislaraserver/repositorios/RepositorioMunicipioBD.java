package br.laramara.ti.sislaraserver.repositorios;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import br.laramara.ti.sislaraserver.dominio.endereco.EnderecoCEP;
import br.laramara.ti.sislaraserver.dominio.endereco.Municipio;
import br.laramara.ti.sislaraserver.dominio.endereco.UF;

@Repository
public class RepositorioMunicipioBD extends RepositorioDB<Municipio> implements
		RepositorioMunicipio {

	public List<Municipio> pesquisarPor(UF uf) {
		List<Municipio> municipios = new ArrayList<>();

		TypedQuery<Municipio> query = em.createQuery(
				"SELECT m FROM Municipio m WHERE m.uf = :UF ORDER BY m.nome",
				Municipio.class);
		try {
			query.setParameter("UF", uf);
			municipios = query.getResultList();
		} catch (Exception e) {
			logger.error("Não foi possível obter a lista de municípios. \nDetalhe:"
					+ e);
		}
		return municipios;
	}

	@Override
	public EnderecoCEP carregarMunicipio(EnderecoCEP enderecoCEP) {
		try {
			TypedQuery<Municipio> query = em
					.createQuery(
							"SELECT p FROM Municipio p WHERE nome = :nome AND uf = :uf ",
							Municipio.class);
			query.setParameter("nome", enderecoCEP.getMunicipio().getNome());
			query.setParameter("uf", enderecoCEP.getUf());
			Municipio municipio = query.getSingleResult();
			enderecoCEP.setMunicipio(municipio);
		} catch (Exception e) {
			logger.error("Não foi possível obter o Município. \nDetalhe:" + e);
		}
		return enderecoCEP;
	}
}

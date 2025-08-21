package br.laramara.ti.sislaraserver.repositorios;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import br.laramara.ti.sislaraserver.dominio.usuario.Cid;

@Repository
public class RepositorioCidBD extends RepositorioDB<Cid> implements
		RepositorioCid {

	@Override
	public Cid obterPorCid(String cid) {
		Cid resultado = null;
		Query query = em.createQuery(
				"SELECT cid FROM Cid cid WHERE LOWER(cid.id) = LOWER(:cid)", Cid.class);
		query.setParameter("cid", cid);
		try {
			resultado = (Cid) query.getSingleResult();
		} catch (NoResultException ex) {
			logger.info("Nenhum Cid foi obtida a partir do " + cid);
		} catch (Exception e) {
			logger.error("Ocorreu um erro durante a consulta de CIDS. \nDetalhes: "
					+ e);
		}
		return resultado;
	}
}

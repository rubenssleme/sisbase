package br.laramara.ti.sislaraserver.repositorios;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import br.laramara.ti.sislaraserver.dominio.doacao.Empresa;

@Repository
public class RepositorioEmpresaBD extends RepositorioDB<Empresa> implements RepositorioEmpresa {

	@Override
	public List<Empresa> obterTodos() {
		List<Empresa> empresas = new ArrayList<>();

		TypedQuery<Empresa> query = em.createQuery(
				"SELECT d FROM Empresa d ORDER BY d.nome", Empresa.class);
		try {
			empresas = query.getResultList();
		} catch (Exception e) {
			logger.error("Não foi possível obter a lista de Empresas. \nDetalhe:"
					+ e);
		}
		return empresas;
	}
}

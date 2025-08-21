package br.laramara.ti.sislaraserver.repositorios;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import br.laramara.ti.sislaraserver.dominio.usuario.Vulnerabilidade;

@Repository
public class RepositorioVulnerabilidadeBD extends RepositorioDB<Vulnerabilidade> implements RepositorioVulnerabilidade {

	@Override
	public List<Vulnerabilidade> obterTodosParaUsuario() {
		return obterTodos().stream().filter(vulnerabilidade -> vulnerabilidade.disponivelParaUsuario())
				.collect(Collectors.toList());
	}

	@Override
	public List<Vulnerabilidade> obterTodosParaFamilia() {
		return obterTodos().stream().filter(vulnerabilidade -> vulnerabilidade.disponivelParaFamilia())
				.collect(Collectors.toList());
	}
	
	private List<Vulnerabilidade> obterTodos(){
		List<Vulnerabilidade> vulnerabilidade = new ArrayList<>();
		try {
			TypedQuery<Vulnerabilidade> query = em.createQuery("SELECT i FROM Vulnerabilidade i ORDER BY i.descricao",
					Vulnerabilidade.class);
			vulnerabilidade = query.getResultList();
		} catch (Exception e) {
			logger.fatal("Erro durante pesquisa de Vulnerabilidades.\n Detalhes:" + e);
		}
		return vulnerabilidade;
	}
}

package br.laramara.ti.sismovimentacaoserver.repositorios;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.laramara.ti.sismovimentacaoserver.dominio.Profissional;

@Repository
@QualificadorRepositorioProfissionalSistema
public class RepositorioProfissionalBD extends RepositorioDB<Profissional> implements
		RepositorioProfissional {

	private String profissional = "SELECT i FROM Profissional i " +
			"	WHERE i.habilitado = true " +
			"   AND profissional = true " +
			"		ORDER BY i.nome";
	private String voluntario = "SELECT i FROM Profissional i " +
			"	WHERE i.habilitado = true " +
			" 	AND voluntario = true " +
			"		ORDER BY i.nome";

	@Override
	public List<Profissional> obterProfissionaisAtivos() {
		return obterProfissional(profissional);
	}
	
	@Override
	public List<Profissional> obterVoluntarioAtivo() {
		return obterProfissional(voluntario);
	}
	
	private  List<Profissional> obterProfissional(String sql) {
		List<Profissional> profissionais = new ArrayList<>();
		try {
			TypedQuery<Profissional> query = em.createQuery(sql,
					Profissional.class);
			profissionais = query.getResultList();
		} catch (Exception e) {
			logger.fatal("Erro durante pesquisa de todos os Profissionais.\n Detalhes:"
					+ e);
		}
		return profissionais;
	}
	
	@Override
	public List<Profissional> obterTodos() {
		List<Profissional> profissionais = new ArrayList<>();
		try {
			TypedQuery<Profissional> query = em.createQuery(
					"SELECT i FROM Profissional i " +
					"		ORDER BY i.nome",
					Profissional.class);
			profissionais = query.getResultList();
		} catch (Exception e) {
			logger.fatal("Erro durante pesquisa de todos os Profissionais.\n Detalhes:"
					+ e);
		}
		return profissionais;
	}

	@Transactional
	public void salvar(Profissional profissionalASalvar) {
		String acao;
		try {
			if (profissionalASalvar.getId() == null) {
				em.persist(profissionalASalvar);
				acao = "Inclusão";
			} else {
				em.merge(profissionalASalvar);
				acao = "Alteração";
			}
			logger.info(acao + " do " + profissionalASalvar
					+ " realizada com sucesso.");
		} catch (Exception e) {
			logger.error("Ocorreu algum erro durante o armazenamento do "
					+ profissionalASalvar + ". \nDetalhes: " + e);
		}
	}
}

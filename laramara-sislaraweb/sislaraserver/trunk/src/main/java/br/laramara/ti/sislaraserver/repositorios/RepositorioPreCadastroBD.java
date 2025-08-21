package br.laramara.ti.sislaraserver.repositorios;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.laramara.ti.sislaraserver.dominio.precadastro.PreCadastro;

@Repository
public class RepositorioPreCadastroBD extends RepositorioDB<PreCadastro> implements
		RepositorioPreCadastro {

	@Transactional
	public void salvar(PreCadastro preCadastroASalvar)
			throws RgDuplicadoException {
		if (existeMaisDeUmPreCadastroComRG(preCadastroASalvar)) {
			throw new RgDuplicadoException();
		}
		String acao = "";
		if (preCadastroASalvar.getId() == null) {
			em.persist(preCadastroASalvar);
			acao = "Inclusão";
		} else {
			em.merge(preCadastroASalvar);
			acao = "Alteração";
		}
		logger.info(acao + " do " + preCadastroASalvar
				+ " realizada com sucesso.");
	}

	public List<PreCadastro> pesquisarPorNome(String nome) {
		List<PreCadastro> preCadastros = new ArrayList<>();
		if (naoEstaVazio(nome) && possuiMaisDeTresCaracteres(nome)) {
			try {
				TypedQuery<PreCadastro> query = em
						.createQuery(
								"SELECT p FROM PreCadastro p WHERE LOWER(remover_acento(p.informacaoEssencial.nome)) LIKE LOWER(remover_acento(:nome)) "
										+ "ORDER BY p.informacaoEssencial.nome",
								PreCadastro.class);
				query.setParameter("nome", "%" + nome.toLowerCase() + "%");
				preCadastros = query.getResultList();
			} catch (Exception e) {
				logger.fatal("Erro durante pesquisa de PreCadastros por nome.\n Detalhes:"
						+ e);
			}
		}
		return preCadastros;
	}

	public List<PreCadastro> pesquisarPorRG(String rg) {
		List<PreCadastro> preCadastros = new ArrayList<>();
		if (naoEstaVazio(rg)) {
			try {
				TypedQuery<PreCadastro> query = em.createQuery(
						"SELECT p FROM PreCadastro p "
								+ "	JOIN p.informacaoEssencial.historicoRg r "
								+ "WHERE LOWER(r.rg) LIKE LOWER(:rg) "
								+ "AND r.dataFinalVigencia IS NULL "
								+ "ORDER BY p.informacaoEssencial.nome",
						PreCadastro.class);
				query.setParameter("rg", rg.toLowerCase());
				preCadastros = query.getResultList();
			} catch (Exception e) {
				logger.fatal("Erro durante pesquisa de PreCadastros por RG.\n Detalhes:"
						+ e);
			}
		}
		return preCadastros;
	}

	private boolean existeMaisDeUmPreCadastroComRG(PreCadastro preCadastro) {
		List<PreCadastro> preCadastrosExistente = pesquisarPorRG(preCadastro
				.getInformacaoEssencial().getRg());
		if (preCadastrosExistente.size() >= 1
				&& !preCadastrosExistente.contains(preCadastro)) {
			return true;
		} else if ((preCadastrosExistente.size() > 1 && preCadastrosExistente
				.contains(preCadastro))) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public List<PreCadastro> pesquisarPorCpf(String cpf) {
		List<PreCadastro> preCadastros = new ArrayList<>();
		if (naoEstaVazio(cpf)) {
			try {
				TypedQuery<PreCadastro> query = em.createQuery(
						"SELECT p FROM PreCadastro p "
								+ "	JOIN p.informacaoEssencial r "
								+ "WHERE LOWER(r.cpf) LIKE LOWER(:cpf) ",
						PreCadastro.class);
				query.setParameter("cpf", cpf.toLowerCase());
				preCadastros = query.getResultList();
			} catch (Exception e) {
				logger.fatal("Erro durante pesquisa de PreCadastros por RG.\n Detalhes:"
						+ e);
			}
		}
		return preCadastros;
	}
}
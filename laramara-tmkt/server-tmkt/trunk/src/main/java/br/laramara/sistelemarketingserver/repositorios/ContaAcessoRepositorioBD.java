package br.laramara.sistelemarketingserver.repositorios;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import br.laramara.sistelemarketingserver.dominio.seguranca.ContaAcesso;
import br.laramara.sistelemarketingserver.dominio.seguranca.Credencial;

@Repository
public class ContaAcessoRepositorioBD extends RepositorioDB<ContaAcesso> implements ContaAcessoRepositorio {

	@Override
	public ContaAcesso obterPorToken(String token) {
		ContaAcesso resultado = null;
		Query query = em.createQuery(
				"SELECT cta FROM ContaAcesso cta WHERE cta.token = :token",
				ContaAcesso.class);
		query.setParameter("token", token);
		try {
			resultado = (ContaAcesso) query.getSingleResult();
		} catch (NoResultException ex) {
			logger.info("Nenhuma conta de acesso foi obtida a partir da token "
					+ token);
		} catch (Exception e) {
			logger.error("Ocorreu um erro durante a consulta de contas de acesso. \nDetalhes: "
					+ e);
		}
		return resultado;
	}
	
	@Override
	public ContaAcesso obterContaAcesso(Credencial credencial) {
		ContaAcesso resultado = null;
		Query query = em
				.createQuery(
						"SELECT cta FROM ContaAcesso cta WHERE cta.login = :login AND cta.senha = :senha",
						ContaAcesso.class);
		query.setParameter("login", credencial.getUsuario());
		query.setParameter("senha", credencial.getSenha());
		try {
			resultado = (ContaAcesso) query.getSingleResult();
		} catch (NoResultException ex) {
			logger.info("Nenhuma conta de acesso foi obtida a partir da "
					+ credencial);
		} catch (Exception e) {
			logger.error("Ocorreu um erro durante a consulta de contas de acesso. \nDetalhes: "
					+ e);
		}

		return resultado;
	}
	
	@Transactional
	public ContaAcesso salvar(ContaAcesso contaAcesso) {
		ContaAcesso contaAcessoSalvo = null;
		try {
			contaAcessoSalvo = em.merge(contaAcesso);
			logger.info("Edição do " + contaAcessoSalvo + " realizada com sucesso.");
		} catch (Exception e) {
			logger.error("Ocorreu algum erro durante o armazenamento do " + contaAcessoSalvo + ". \nDetalhes: " + e);
		}
		return contaAcessoSalvo;
	}

	@Override
	public List<ContaAcesso> obterTodos() {
		List<ContaAcesso> contaAcesso = new ArrayList<>();
		try {
			TypedQuery<ContaAcesso> query = em.createQuery("SELECT c FROM ContaAcesso c ORDER BY c.login",
					ContaAcesso.class);
			contaAcesso = query.getResultList();
		} catch (Exception e) {
			logger.fatal("Erro durante pesquisa de todos os conta acessos.\n Detalhes:" + e);
		}
		return contaAcesso;
	}

	@Override
	public ContaAcesso obter(Long id) {
		ContaAcesso contaAcesso = null;
		if (id != null) {
			try {
				TypedQuery<ContaAcesso> query = em.createQuery("SELECT c FROM ContaAcesso c WHERE c.id = :id",
						ContaAcesso.class);
				query.setParameter("id", id);
				contaAcesso = query.getSingleResult();
			} catch (Exception e) {
				logger.fatal("Erro durante pesquisa de todos os conta acessos.\n Detalhes:" + e);
			}
		}
		return contaAcesso;
	}

	@Override
	public List<ContaAcesso> obterTodosOperadoresAtivos() {
		return obterTodos().stream().filter(contaAcesso -> contaAcesso.possuiNivelOperadorAtivo())
				.collect(Collectors.toList());
	}
}

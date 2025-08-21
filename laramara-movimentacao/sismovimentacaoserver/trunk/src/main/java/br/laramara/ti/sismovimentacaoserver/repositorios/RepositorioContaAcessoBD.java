package br.laramara.ti.sismovimentacaoserver.repositorios;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.laramara.ti.sismovimentacaoserver.dominio.seguranca.ContaAcesso;
import br.laramara.ti.sismovimentacaoserver.dominio.seguranca.Credencial;

@Repository
public class RepositorioContaAcessoBD extends RepositorioDB<ContaAcesso>
		implements RepositorioContaAcesso {

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
	public void salvar(ContaAcesso contaAcessoASalvar) {
		String acao;
		try {
			if (contaAcessoASalvar.getId() == null) {
				em.persist(contaAcessoASalvar);
				acao = "Inclusão";
			} else {
				em.merge(contaAcessoASalvar);
				acao = "Alteração";
			}
			logger.info(acao + " do " + contaAcessoASalvar.toStringCompleto()
					+ " realizada com sucesso.");
		} catch (Exception e) {
			logger.error("Ocorreu algum erro durante o armazenamento do "
					+ contaAcessoASalvar.toStringCompleto() + ". \nDetalhes: "
					+ e);
		}
	}

	@Override
	public List<ContaAcesso> obterTodos() {
		List<ContaAcesso> contasAcesso = new ArrayList<>();
		try {
			TypedQuery<ContaAcesso> query = em.createQuery(
					"SELECT c FROM ContaAcesso c ORDER BY c.login",
					ContaAcesso.class);
			contasAcesso = query.getResultList();
		} catch (Exception e) {
			logger.fatal("Erro durante pesquisa de Todas as Contas Acesso.\n Detalhes:"
					+ e);
		}
		return contasAcesso;
	}

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
}

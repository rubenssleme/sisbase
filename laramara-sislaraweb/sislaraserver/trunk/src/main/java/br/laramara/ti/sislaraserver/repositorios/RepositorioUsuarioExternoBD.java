package br.laramara.ti.sislaraserver.repositorios;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.laramara.ti.sislaraserver.dominio.seguranca.externa.CredencialExterna;
import br.laramara.ti.sislaraserver.dominio.usuario.externo.UsuarioExterno;

@Repository
public class RepositorioUsuarioExternoBD extends RepositorioDB<UsuarioExterno> implements RepositorioUsuarioExterno {

	@Transactional
	public synchronized UsuarioExterno salvar(UsuarioExterno usuarioExterno) {
		String acao;
		try {
			if (usuarioExterno.getId() == null) {
				em.persist(usuarioExterno);
				acao = "Inclusão";
			} else {
				em.merge(usuarioExterno);
				acao = "Alteração";
			}
			logger.info(acao + " do " + usuarioExterno.toString() + " realizada com sucesso.");
		} catch (Exception e) {
			logger.error("Ocorreu algum erro durante o armazenamento do " + usuarioExterno.toString() + ". \nDetalhes: "
					+ e);
		}

		return usuarioExterno;
	}

	@Override
	public UsuarioExterno obterUsuarioExterno(CredencialExterna credencialExterna) {
		UsuarioExterno resultado = null;
		Query query = em.createQuery(
				"SELECT usuext FROM UsuarioExterno usuext WHERE usuext.email = :email AND usuext.senha = :senha",
				UsuarioExterno.class);
		query.setParameter("email", credencialExterna.getEmail());
		query.setParameter("senha", credencialExterna.getSenha());
		try {
			resultado = (UsuarioExterno) query.getSingleResult();
		} catch (NoResultException ex) {
			logger.info("Nenhum usuário externo foi obtido a partir da " + credencialExterna);
		} catch (Exception e) {
			logger.error("Ocorreu um erro durante a consulta de usuários externos. \nDetalhes: " + e);
		}

		return resultado;
	}

	@Override
	public UsuarioExterno obterPorEmail(String emailSolicitante) {
		UsuarioExterno resultado = null;
		Query query = em.createQuery("SELECT usuext FROM UsuarioExterno usuext WHERE usuext.email = :email",
				UsuarioExterno.class);
		query.setParameter("email", emailSolicitante);
		try {
			resultado = (UsuarioExterno) query.getSingleResult();
		} catch (NoResultException ex) {
			logger.info("Nenhum usuário externo foi obtido a partir do e-mail " + emailSolicitante);
		} catch (Exception e) {
			logger.error("Ocorreu um erro durante a consulta de usuários externos. \nDetalhes: " + e);
		}

		return resultado;
	}
	
	@Override
	public UsuarioExterno obterPorToken(String token) {
		UsuarioExterno resultado = null;
		Query query = em.createQuery("SELECT usuext FROM UsuarioExterno usuext WHERE usuext.token = :token",
				UsuarioExterno.class);
		query.setParameter("token", token);
		try {
			resultado = (UsuarioExterno) query.getSingleResult();
		} catch (NoResultException ex) {
			logger.info("Nenhum usuário externo foi obtido a partir do Token " + token);
		} catch (Exception e) {
			logger.error("Ocorreu um erro durante a consulta de usuários externos. \nDetalhes: " + e);
		}
		return resultado;
	}
	
	@Override
	public boolean cpfJaExiste(String cpf) {
		boolean resultado = false;
		
		Query query = em.createQuery("SELECT usuext FROM UsuarioExterno usuext WHERE usuext.cpf = :cpf",
				UsuarioExterno.class);
		query.setParameter("cpf", cpf);
		try {
			resultado = query.getSingleResult() != null;
		} catch (NoResultException ex) {
			logger.info("Nenhum usuário externo foi obtido a partir do CPF " + cpf);
		} catch (Exception e) {
			logger.error("Ocorreu um erro durante a consulta de usuários externos. \nDetalhes: " + e);
		}
		return resultado;
	}

	@Override
	public UsuarioExterno obterPorId(Long id) {
		return (UsuarioExterno) super.obterPorId(id);
	}
}

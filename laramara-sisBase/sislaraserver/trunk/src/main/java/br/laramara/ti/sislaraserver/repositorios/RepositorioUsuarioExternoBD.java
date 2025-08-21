package br.laramara.ti.sislaraserver.repositorios;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.laramara.ti.sislaraserver.dominio.seguranca.CredencialExterna;
import br.laramara.ti.sislaraserver.dominio.usuario.UsuarioExterno;

@Repository
public class RepositorioUsuarioExternoBD extends RepositorioDB<UsuarioExterno> implements
		RepositorioUsuarioExterno{

	@Transactional
	public synchronized UsuarioExterno salvar(UsuarioExterno usuarioExterno) {
		String acao;
		try {
			if (usuarioExterno.getId() == null) {
				em.persist(usuarioExterno);
				acao = "Inclus�o";
			} else {
				em.merge(usuarioExterno);
				acao = "Altera��o";
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
		Query query = em
				.createQuery(
						"SELECT usuext FROM UsuarioExterno usuext WHERE usuext.email = :email AND usuext.senha = :senha",
						UsuarioExterno.class);
		query.setParameter("email", credencialExterna.getEmail());
		query.setParameter("senha", credencialExterna.getSenha());
		try {
			resultado = (UsuarioExterno) query.getSingleResult();
		} catch (NoResultException ex) {
			logger.info("Nenhum usu�rio externo foi obtido a partir da "
					+ credencialExterna);
		} catch (Exception e) {
			logger.error("Ocorreu um erro durante a consulta de usu�rios externos. \nDetalhes: "
					+ e);
		}

		return resultado;
	}
	
	@Override
	public UsuarioExterno obterPorId(Long id) {
		UsuarioExterno usuarioExterno = (UsuarioExterno) super.obterPorId(id);
		return usuarioExterno;
	}	
}

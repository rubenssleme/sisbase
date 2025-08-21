package br.laramara.ti.sislaraserver.repositorios;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.laramara.ti.sislaraserver.dominio.usuario.Usuario;
import br.laramara.ti.sislaraserver.dominio.usuario.UsuarioComFoto;

@Repository
public class RepositorioUsuarioBD extends RepositorioDB<Usuario> implements
		RepositorioUsuario{

	@Inject
	private RepositorioFoto repositorioFoto;
	
	@Transactional
	public Usuario salvar(Usuario usuarioASalvar) {
		String acao;
		Usuario usuarioSalvo = null;
		try {
			usuarioASalvar.mudarVersao();
			em.merge(usuarioASalvar);
			if (usuarioASalvar.getId() == null) {
				acao = "Inclusão";
				usuarioSalvo = obterPorId(obterValorIdentificador(Usuario.SEQUENCIA));
			} else {
				acao = "Alteração";
				usuarioSalvo = obterPorId(usuarioASalvar.getId());
			}
			repositorioFoto.salvar(usuarioSalvo, usuarioASalvar.getFoto());
			anexarFoto(usuarioSalvo);
			logger.info(acao + " do " + usuarioSalvo
					+ " realizada com sucesso.");
		} catch (Exception e) {
			logger.error("Ocorreu algum erro durante o armazenamento do "
					+ usuarioASalvar + ". \nDetalhes: " + e);
		}
		return usuarioSalvo;
	}

	@Override
	public Usuario obterPorId(Long id) {
		Usuario usuario = (Usuario) super.obterPorId(id);
		anexarFoto(usuario);
		return usuario;
	}

	@Override
	public List<Usuario> pesquisarPorNome(String nome) {
		return pesquisarPor(
				nome,
				"SELECT u FROM Usuario u WHERE LOWER(remover_acento(u.informacaoEssencial.nome)) LIKE LOWER(remover_acento(:nome))");
	}
	
	@Override
	public List<Usuario> pesquisarPorFamiliar(String nome) {
		return pesquisarPor(
				nome,
				"SELECT u FROM Usuario u  JOIN u.familiares f WHERE LOWER(remover_acento(f.informacaoEssencial.nome)) LIKE LOWER(remover_acento(:nome))");
	}
	
	private List<Usuario> pesquisarPor(String texto, String sql) {
		List<Usuario> usuarios = new ArrayList<>();
		if (naoEstaVazio(texto) && possuiMaisDeTresCaracteres(texto)) {
			try {
				TypedQuery<Usuario> query = em.createQuery(sql, Usuario.class);
				query.setParameter("nome", "%" + texto.toLowerCase() + "%");
				usuarios = query.getResultList();
				anexarFoto(usuarios);
			} catch (Exception e) {
				logger.fatal("Erro durante pesquisa de Usuário.\n Detalhes:"
						+ e);
			}
		}
		return usuarios;
	}

	@Override
	public List<Usuario> pesquisarPorRG(String rg) {
		List<Usuario> usuarios = new ArrayList<>();
		if (naoEstaVazio(rg)) {
			try {
				TypedQuery<Usuario> query = em.createQuery(
						"SELECT u FROM Usuario u "
								+ "	JOIN u.informacaoEssencial.historicoRg r "
								+ "WHERE LOWER(r.rg) LIKE LOWER(:rg)", Usuario.class);
				query.setParameter("rg", rg.toLowerCase());
				usuarios = query.getResultList();
				anexarFoto(usuarios);
			} catch (Exception e) {
				logger.fatal("Erro durante pesquisa de Usuário por RG.\n Detalhes:"
						+ e);
			}
		}
		return usuarios;
	}
	
	private void anexarFoto(UsuarioComFoto usuarioComFoto) {
		if (usuarioComFoto != null) {
			usuarioComFoto.setFoto(repositorioFoto.obterFoto(usuarioComFoto));
		}
	}
	 
	private void anexarFoto(List<Usuario> usuarios){
		for(Usuario usuario : usuarios){
			anexarFoto(usuario);
		}
	}

	@Override
	public List<Usuario> obterTodos() {
		List<Usuario> usuarios = new ArrayList<>();
		try {
			TypedQuery<Usuario> query = em.createQuery(
					"SELECT u FROM Usuario u", Usuario.class);
			usuarios = query.getResultList();
		} catch (Exception e) {
			logger.fatal("Erro durante pesquisa de todos os Usuários.\n Detalhes:"
					+ e);
		}
		return usuarios;
	}
}

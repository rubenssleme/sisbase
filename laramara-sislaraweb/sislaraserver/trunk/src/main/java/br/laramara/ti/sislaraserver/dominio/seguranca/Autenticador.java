package br.laramara.ti.sislaraserver.dominio.seguranca;

import java.util.UUID;

import javax.inject.Inject;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import br.laramara.ti.sislaracommons.dtos.seguranca.TokenDTO;
import br.laramara.ti.sislaraserver.dominio.seguranca.externa.CredencialExterna;
import br.laramara.ti.sislaraserver.dominio.seguranca.externa.UsuarioExternoNaoAutenticadoException;
import br.laramara.ti.sislaraserver.dominio.usuario.externo.UsuarioExterno;
import br.laramara.ti.sislaraserver.repositorios.RepositorioContaAcesso;
import br.laramara.ti.sislaraserver.repositorios.RepositorioUsuarioExterno;

@Component
public class Autenticador{
	
	private final Logger logger = Logger.getLogger(Autenticador.class);

	@Inject
	private RepositorioUsuarioExterno repositorioUsuarioExterno;
	@Inject
	private RepositorioContaAcesso repositorioContaAcesso;
	@Inject
	private CoordenadorEdicaoGeral coordenadorEdicaoGeral;
	
	public Autenticador() {
		super();
	}

	public Autenticador(RepositorioUsuarioExterno repositorioUsuarioExterno) {
		this();
		this.repositorioUsuarioExterno = repositorioUsuarioExterno;
	}

	public Autenticador(RepositorioContaAcesso repositorioContaAcesso,
			CoordenadorEdicaoGeral coordenadorEdicaoGeral) {
		this();
		this.repositorioContaAcesso = repositorioContaAcesso;
		this.coordenadorEdicaoGeral = coordenadorEdicaoGeral;
	}

	public synchronized String autenticaUsuarioExterno(CredencialExterna credencialExterna)
			throws UsuarioExternoNaoAutenticadoException {
		UsuarioExterno usuarioExterno = repositorioUsuarioExterno
				.obterUsuarioExterno(credencialExterna);

		if (usuarioExterno != null && !usuarioExterno.isBloqueado()) {
			String token = UUID.randomUUID().toString();
			usuarioExterno.setToken(token);
			repositorioUsuarioExterno.salvar(usuarioExterno);
			logger.info("Autenticação do " + usuarioExterno
					+ " efetuada com sucesso. ");
			return token;
		} else {
			logger.info("Tentativa de autenticação da " + credencialExterna
					+ " falhou");
			throw new UsuarioExternoNaoAutenticadoException();
		}
	}
	
	public synchronized String autentica(Credencial credencial)
			throws ContaAcessoNaoAutenticadoException {
		ContaAcesso contaAcesso = repositorioContaAcesso
				.obterContaAcesso(credencial);

		if (contaAcesso != null && !contaAcesso.isBloqueado()) {
			String token = UUID.randomUUID().toString();
			contaAcesso.setToken(token);
			coordenadorEdicaoGeral.desbloquearValor(contaAcesso);
			repositorioContaAcesso.salvar(contaAcesso);
			logger.info("Autenticação da " + contaAcesso
					+ " efetuada com sucesso. ");
			return token;
		} else {
			logger.info("Tentativa de autenticação da " + credencial
					+ " falhou");
			throw new ContaAcessoNaoAutenticadoException();
		}
	}

	public boolean tokenAtivo(TokenDTO tokenDTO) {
		return repositorioContaAcesso.obterPorToken(tokenDTO.getToken()) != null;
	}
}

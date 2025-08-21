package br.laramara.ti.sismovimentacaoserver.dominio.seguranca;

import java.util.UUID;

import javax.inject.Inject;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import br.laramara.ti.sismovimentacaoserver.repositorios.RepositorioContaAcesso;

@Component
public class Autenticador{
	
	private final Logger logger = Logger.getLogger(Autenticador.class);

	@Inject
	private RepositorioContaAcesso repositorioContaAcesso;
	@Inject
	private CoordenadorEdicaoGeral coordenadorEdicaoGeral;
	
	public Autenticador() {
		super();
	}

	public Autenticador(RepositorioContaAcesso repositorioContaAcesso,
			CoordenadorEdicaoGeral coordenadorEdicaoGeral) {
		this();
		this.repositorioContaAcesso = repositorioContaAcesso;
		this.coordenadorEdicaoGeral = coordenadorEdicaoGeral;
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
}

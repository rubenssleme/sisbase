package br.laramara.sistelemarketingserver.dominio.seguranca;

import java.util.UUID;

import javax.inject.Inject;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import br.laramara.sistelemarketingserver.repositorios.ContaAcessoRepositorio;

@Component
public class Autenticador{
	
	private final Logger logger = Logger.getLogger(Autenticador.class);
	@Inject
	private ContaAcessoRepositorio repositorioContaAcesso;
	
	public Autenticador() {
		super();
	}
	
	public synchronized ContaAcesso autentica(Credencial credencial)
			throws ContaAcessoNaoAutenticadoException {
		ContaAcesso contaAcesso = repositorioContaAcesso
				.obterContaAcesso(credencial);

		if (contaAcesso != null && contaAcesso.isAtivo()) {
			contaAcesso.setToken(UUID.randomUUID().toString());
			contaAcesso.setRamal(credencial.getRamal());
			repositorioContaAcesso.salvar(contaAcesso);
			logger.info("Autenticação da " + contaAcesso
					+ " efetuada com sucesso. ");
			return contaAcesso;
		} else {
			logger.info("Tentativa de autenticação da " + credencial
					+ " falhou");
			throw new ContaAcessoNaoAutenticadoException();
		}
	}
}

package br.laramara.ti.sislaraserver.utilitarios;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.inject.Inject;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import br.laramara.ti.sislaracommons.dtos.seguranca.TokenDTO;
import br.laramara.ti.sislaraserver.dominio.seguranca.ContaAcesso;
import br.laramara.ti.sislaraserver.repositorios.RepositorioContaAcesso;

@Component
public class AvisoAtualizacao {

	protected final Logger logger = Logger.getLogger(AvisoAtualizacao.class);

	private Map<ContaAcesso, String> contaAcessoComAvisoLido;

	private String chaveAtualAvisoDeAtualizacao;

	boolean ativado = false;
	
	boolean fechamentoAutomaticoClient = false;

	@Inject
	private RepositorioContaAcesso repositorioContaAcesso;

	public AvisoAtualizacao() {
		super();
		chaveAtualAvisoDeAtualizacao = "";
		contaAcessoComAvisoLido = new HashMap<>();
	}
	
	public boolean fechamentoAutomaticoClientAtivado() {
		return fechamentoAutomaticoClient;
	}

	public synchronized void ativarFechamentoAutomaticoClient() {
		if (ativado){
			this.fechamentoAutomaticoClient = true;
		}
	}

	private synchronized void ativar() {
		chaveAtualAvisoDeAtualizacao = UUID.randomUUID().toString();
		ativado = true;
		logger.info("Aviso de atualização ativado.");
	}

	public synchronized boolean ativarDesativarAvisoDeAtualizacao() {
		if (chaveAtualAvisoDeAtualizacao.isEmpty()) {
			ativar();
		} else {
			desativar();
		}
		return ativado;
	}

	public synchronized void desativar() {
		contaAcessoComAvisoLido.clear();
		chaveAtualAvisoDeAtualizacao = "";
		ativado = false;
		fechamentoAutomaticoClient = false;
		logger.info("Aviso de atualização desativado.");
	}

	public synchronized boolean exibirAvisoDeAtualizacao(TokenDTO tokenDto) {
		boolean resultado = false;
		ContaAcesso contaAcesso = repositorioContaAcesso.obterPorToken(tokenDto
				.getToken());

		String chaveAvisoDeAtualizacaoDoContaAcesso = contaAcessoComAvisoLido
				.get(contaAcesso);

		if ((chaveAvisoDeAtualizacaoDoContaAcesso == null && !chaveAtualAvisoDeAtualizacao
				.isEmpty())
				|| (chaveAvisoDeAtualizacaoDoContaAcesso != null
						&& !chaveAtualAvisoDeAtualizacao.isEmpty() && !chaveAvisoDeAtualizacaoDoContaAcesso
							.equals(chaveAtualAvisoDeAtualizacao))) {
			resultado = true;
		} else {
			resultado = false;
		}
		return resultado;
	}

	public synchronized void confirmarLeituraDeAvisoDeAtualizacao(
			TokenDTO tokenDto) {
		ContaAcesso contaAcesso = repositorioContaAcesso.obterPorToken(tokenDto
				.getToken());
		contaAcessoComAvisoLido.put(contaAcesso, chaveAtualAvisoDeAtualizacao);
	}
}

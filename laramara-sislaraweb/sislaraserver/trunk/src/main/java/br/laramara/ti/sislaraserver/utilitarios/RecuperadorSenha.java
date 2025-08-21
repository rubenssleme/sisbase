package br.laramara.ti.sislaraserver.utilitarios;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.inject.Inject;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import br.laramara.ti.sislaraserver.dominio.usuario.externo.UsuarioExterno;
import br.laramara.ti.sislaraserver.repositorios.RepositorioUsuarioExterno;
import br.laramara.ti.sislaraserver.servicos.rest.ServicoSisLaraServerRest;

@Component
public class RecuperadorSenha implements Serializable {

	private static final long serialVersionUID = -8738425326545907331L;

	private static Map<String, String> solicitacoes = new HashMap<>();

	private final Logger logger;

	@Inject
	private RepositorioUsuarioExterno repositorioUsuarioExterno;

	public RecuperadorSenha() {
		this.logger = Logger.getLogger(getClass());
	}

	public boolean enviarEmailDeConfirmacao(String emailSolicitante) {
		return enviarEmail(emailSolicitante, "Confirmação de E-mail no " + ServicoSisLaraServerRest.SISLARAWEB,
				"Bem vindo ao SislaraWeb.<br>" + "<a href=\"http://"
						+ new Configuracao().obterConfiguracao(Configuracao.EMAIL_IP_LINK) + ":"
						+ new Configuracao().obterConfiguracao(Configuracao.APLICACAO_WEB_PORTA) + "/"
						+ ServicoSisLaraServerRest.SISLARAWEB + "/recuperarSenha.xhtml?chave="
						+ gerarChaveDeRecuperacaoSenha(emailSolicitante) + "\">"
						+ "<br><br>Clique aqui para cadastrar sua senha de acesso no "
						+ ServicoSisLaraServerRest.SISLARAWEB + ".</a>");
	}

	public boolean enviarEmailDeRecuperacaoSenha(String emailSolicitante) {
		return enviarEmail(emailSolicitante, "Alterar senha do " + ServicoSisLaraServerRest.SISLARAWEB,
				"<a href=\"http://" + new Configuracao().obterConfiguracao(Configuracao.EMAIL_IP_LINK) + ":"
						+ new Configuracao().obterConfiguracao(Configuracao.APLICACAO_WEB_PORTA) + "/"
						+ ServicoSisLaraServerRest.SISLARAWEB + "/recuperarSenha.xhtml?chave="
						+ gerarChaveDeRecuperacaoSenha(emailSolicitante) + "\">Para recuperar ou alterar sua senha do "
						+ ServicoSisLaraServerRest.SISLARAWEB + ", clique aqui.</a>");
	}

	private boolean enviarEmail(String emailSolicitante, String assunto, String conteudo) {
		boolean retorno = false;
		try {
			String tipo = new Configuracao().obterConfiguracao(Configuracao.EMAIL_MIME_HTML);

			retorno = EmailUtils.enviarMensagemPorEmail(emailSolicitante, assunto, conteudo, tipo);
		} catch (Exception e) {
			logger.fatal("Erro durante envio de recuperação de senha.\nDetalhes: " + e.getMessage());
		}

		return retorno;
	}

	public synchronized boolean cadastrarNovaSenha(String chave, String novaSenha) {
		boolean retorno = false;
		try {
			String emailSolicitante = solicitacoes.get(chave);
			UsuarioExterno usuarioExterno = repositorioUsuarioExterno.obterPorEmail(emailSolicitante);
			usuarioExterno.setSenha(novaSenha);
			repositorioUsuarioExterno.salvar(usuarioExterno);
			solicitacoes.remove(chave);
			retorno = true;
		} catch (Exception e) {
			retorno = false;
		}
		return retorno;
	}

	private synchronized String gerarChaveDeRecuperacaoSenha(String emailSolicitante) {
		String chave = UUID.randomUUID().toString();
		solicitacoes.put(chave, emailSolicitante);
		return chave;
	}
}

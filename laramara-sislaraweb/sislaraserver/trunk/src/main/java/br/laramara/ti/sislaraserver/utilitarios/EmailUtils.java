package br.laramara.ti.sislaraserver.utilitarios;

import java.io.File;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.log4j.Logger;

public class EmailUtils {

	private static final Logger logger = Logger.getLogger(EmailUtils.class);

	public static boolean enviarArquivoPorEmail(String textoTitulo, String host, String numeroPorta, String origem,
			String senha, String emailsDestino, File arquivoCobranca) {
		boolean sucesso = false;
		try {
			Properties props = obterPropriedadesDeEmail();

			Session mailSession = Session.getDefaultInstance(props);
			Transport transport = mailSession.getTransport();

			MimeMessage message = new MimeMessage(mailSession);
			message.setSubject(textoTitulo);
			message.setFrom(new InternetAddress(origem));
			message.addRecipients(Message.RecipientType.CC, emailsDestino);

			BodyPart messageBodyPart = new MimeBodyPart();
			FileDataSource arquivo = new FileDataSource(arquivoCobranca);
			messageBodyPart.setDataHandler(new DataHandler(arquivo));
			messageBodyPart.setFileName(arquivoCobranca.getName());

			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(messageBodyPart);

			message.setContent(multipart);

			transport.connect(host, Integer.parseInt(numeroPorta), origem, senha);
			transport.sendMessage(message, message.getRecipients(Message.RecipientType.CC));
			transport.close();
			sucesso = true;
		} catch (Exception e) {
			logger.fatal("Erro durante envio de email: " + e);
		}
		return sucesso;
	}

	private static Properties obterPropriedadesDeEmail() {
		Properties props = new Properties();
		props.put("mail.transport.protocol", "smtp");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		return props;
	}

	public static synchronized boolean enviarMensagemPorEmail(String emailDestino, String assunto,
			String conteudo, String tipo) {
		boolean sucesso = false;
		try {
			Properties props = obterPropriedadesDeEmail();

			Session mailSession = Session.getDefaultInstance(props);
			Transport transport = mailSession.getTransport();

			MimeMessage message = new MimeMessage(mailSession);
			message.setSubject(assunto);
			message.setFrom(new InternetAddress(new Configuracao().obterConfiguracao(Configuracao.EMAIL_ENVIO)));
			message.setContent(conteudo, tipo);
			message.addRecipients(Message.RecipientType.CC, emailDestino);

			transport.connect(new Configuracao().obterConfiguracao(Configuracao.EMAIL_HOST),
					Integer.parseInt(new Configuracao().obterConfiguracao(Configuracao.EMAIL_PORTA)),
					new Configuracao().obterConfiguracao(Configuracao.EMAIL_ENVIO),
					new Configuracao().obterConfiguracao(Configuracao.EMAIL_SENHA));
			transport.sendMessage(message, message.getRecipients(Message.RecipientType.CC));
			transport.close();
			sucesso = true;
		} catch (Exception e) {
			logger.fatal("Erro durante envio de email: " + e.getMessage());
		}
		return sucesso;
	}
}

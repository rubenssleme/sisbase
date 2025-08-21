package br.laramara.ti.sislaraserver.repositorios;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import br.laramara.ti.sislaraserver.dominio.usuario.Usuario;
import br.laramara.ti.sislaraserver.dominio.usuario.UsuarioComFoto;
import br.laramara.ti.sislaraserver.utilitarios.Configuracao;

@Repository
public class RepositorioFotoRede implements RepositorioFoto {

	private static final Logger logger = Logger
			.getLogger(RepositorioFotoRede.class);

	public String localArmazenamento;
	public String extensao;

	public RepositorioFotoRede() {
		localArmazenamento = new Configuracao()
				.obterConfiguracao(Configuracao.DIRETORIO_FOTOS);
		extensao = new Configuracao()
				.obterConfiguracao(Configuracao.EXTENSAO_FOTOS);
	}

	@Override
	public void salvar(Usuario usuario, byte[] foto) {
		if (usuario.getId() != null && foto != null) {
			try {
				FileUtils.writeByteArrayToFile(
						criarRepresentacaoArquivo(usuario), foto);
				logger.info("Foto do prontuário " + usuario.getId()
						+ " armazenada com sucesso.");
			} catch (IOException e) {
				logger.error("Erro durante armazenamento de foto do prontuário "
						+ usuario.getId() + " \nDetalhes: " + e);
			}
		}
	}

	@Override
	public byte[] obterFoto(UsuarioComFoto usuarioComFoto) {
		byte[] retorno = null;
		if (usuarioComFoto != null) {
			try {
				retorno = FileUtils
						.readFileToByteArray(criarRepresentacaoArquivo((Usuario) usuarioComFoto));
			} catch (FileNotFoundException n) {
			} catch (Exception e) {
				logger.error("Erro durante leitura de arquivo de foto do prontuário "
						+ ((Usuario) usuarioComFoto).getId()
						+ ". \nDetalhes: "
						+ e);
			}
		}
		return retorno;
	}

	private File criarRepresentacaoArquivo(Usuario usuario) {
		return new File(
				new Configuracao()
						.obterConfiguracao(Configuracao.DIRETORIO_FOTOS)
						+ usuario.getId().toString() + "." + extensao);
	}
}

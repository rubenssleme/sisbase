package br.laramara.ti.sismovimentacaocommons.utilitarios;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;

import javax.imageio.ImageIO;

import org.apache.log4j.Logger;
import org.imgscalr.Scalr;

public class ImagemUtils {

	private static final Logger logger = Logger.getLogger(ImagemUtils.class);

	private static final int ESCALA = 230;

	public static byte[] remimensionar(File imagem) {
		byte[] retorno = null;
		try {
			BufferedImage bufferedImage = Scalr.resize(ImageIO.read(imagem),
					Scalr.Method.QUALITY, Scalr.Mode.FIT_TO_WIDTH, ESCALA);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write(bufferedImage, "jpg", baos);
			baos.flush();
			retorno = baos.toByteArray();
			baos.close();
		} catch (Exception e) {
			logger.error("Erro durante redimensionamento da imagem. \nDetalhes: "
					+ e);
		}
		return retorno;
	}
}

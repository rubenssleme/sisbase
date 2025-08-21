package br.laramara.ti.sismovimentacaocommons.utilitarios;

import java.io.File;
import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sismovimentacaocommons.utilitarios.ImagemUtils;
import br.laramara.ti.sismovimentacaocommons.utilitarios.TiposDeTeste;

public class TestesImagemUtils {

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void imagemutils_redimensiona_imagem_corretamente()
			throws IOException {
		File imagem = new File("src/test/resources/imagem_teste.jpg");
		byte[] imagemRedimensionada = ImagemUtils.remimensionar(imagem);

		Assert.assertEquals(imagemRedimensionada.length, 6270);
	}
}

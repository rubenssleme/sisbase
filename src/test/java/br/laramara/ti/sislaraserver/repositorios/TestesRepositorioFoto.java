package br.laramara.ti.sislaraserver.repositorios;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.dominio.usuario.Usuario;
import br.laramara.ti.sislaraserver.utilitarios.Registro;

public class TestesRepositorioFoto {
	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void repositorio_de_fotos_salva_e_obtem_foto_com_sucesso()
			throws IOException {
		byte[] fotoASalvar = FileUtils.readFileToByteArray(new File(
				"src/test/resources/foto_teste.jpg"));
		Long prontuario = new Long("123456");

		Usuario usuario = new Usuario();
		usuario.setId(prontuario);

		RepositorioFoto repositorioFoto = Registro.obterRepositorioFoto();
		repositorioFoto.salvar(usuario, fotoASalvar);

		Assert.assertEquals(repositorioFoto.obterFoto(usuario).length,
				fotoASalvar.length);
	}
}

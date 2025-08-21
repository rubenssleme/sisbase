package br.laramara.ti.sislaraserver.dominio.usuario;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.dominio.Arquivo;
import br.laramara.ti.sislaraserver.dominio.seguranca.ExtensaoArquivo;

public class TestesProntuarioEscaneado {

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void prontuario_escaneado_ajusta_nome_sem_extensao() {
		ProntuarioEscaneado prontuarioEscaneado = new ProntuarioEscaneado(
				"Pront 6373 pag 21", 1, new Arquivo(null, ExtensaoArquivo.jpg));

		Assert.assertEquals(prontuarioEscaneado.getNomeArquivo(),
				"Pront 6373 pag 21.jpg");
	}
	
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void prontuario_escaneado_ajusta_nome_com_extensao() {
		ProntuarioEscaneado prontuarioEscaneado = new ProntuarioEscaneado(
				"Pront 6373 pag 21.jpg", 1, new Arquivo(null, ExtensaoArquivo.jpg));

		Assert.assertEquals(prontuarioEscaneado.getNomeArquivo(),
				"Pront 6373 pag 21.jpg");
	}
}

package br.laramara.ti.sislaraserver.fabricas;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.dtos.ArquivoDTO;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.dominio.Arquivo;
import br.laramara.ti.sislaraserver.dominio.seguranca.ExtensaoArquivo;

public class TestesFabricaRelatorio {

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void fabrica_relatorio_converte_objeto_de_dominio_para_dto() {
		byte[] relatorio = new byte[122];
		ExtensaoArquivo extensao = ExtensaoArquivo.pdf;

		Arquivo relatorioDominio = new Arquivo(relatorio, extensao);

		ArquivoDTO relatorioDTO = new FabricaArquivo()
				.converterParaDTO(relatorioDominio);

		Assert.assertEquals(relatorioDTO.obterConteudo().length,
				relatorio.length);
		Assert.assertEquals(relatorioDTO.obterExtensao(), extensao.toString());
	}
}

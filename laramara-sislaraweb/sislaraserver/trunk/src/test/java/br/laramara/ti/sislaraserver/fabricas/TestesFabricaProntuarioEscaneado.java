package br.laramara.ti.sislaraserver.fabricas;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.dtos.usuario.ProntuarioEscaneadoDTO;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.dominio.Arquivo;
import br.laramara.ti.sislaraserver.dominio.seguranca.ExtensaoArquivo;
import br.laramara.ti.sislaraserver.dominio.usuario.ProntuarioEscaneado;

public class TestesFabricaProntuarioEscaneado {
	@Test(groups = { TiposDeTeste.INTEGRACAO_SEM_BD })
	public void fabrica_prontuario_escaneado_converte_objeto_de_dominio_para_dto() {
		String nome = "teste.jpg";
		byte[] arquivo = new byte[10];
		ProntuarioEscaneado prontuarioEscaneado = new ProntuarioEscaneado(nome, 0,
				new Arquivo(arquivo, ExtensaoArquivo.jpg));

		ProntuarioEscaneadoDTO prontuarioEscaneadoDto = new FabricaProntuarioEscaneado()
				.converterParaDTO(prontuarioEscaneado);

		Assert.assertEquals(prontuarioEscaneado.getNomeArquivo(),
				prontuarioEscaneadoDto.toString());
		Assert.assertEquals(
				prontuarioEscaneado.getArquivo().getConteudo().length,
				prontuarioEscaneadoDto.getArquivoDto().obterConteudo().length);
		Assert.assertEquals(prontuarioEscaneado.getArquivo().getExtensao()
				.toString(), prontuarioEscaneadoDto.getArquivoDto()
				.obterExtensao().toString());

	}
}

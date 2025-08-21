package br.laramara.ti.sislaraserver.fabricas;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.dtos.ArquivoDisponivelDTO;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.dominio.ArquivoDisponivel;

public class TestesFabricaArquivoDisponivel {

	@Test(groups = { TiposDeTeste.INTEGRACAO_SEM_BD })
	public void fabrica_arquivo_disponivel_converte_objeto_de_dominio_para_dto() {
		ArquivoDisponivel arquivoDisponivel = ContextoArquivoDisponivel.fabricarComTodosOsDados();

		ArquivoDisponivelDTO arquivoDisponivelDtoCovertido = new FabricaArquivoDisponivel()
				.converterParaDTO(arquivoDisponivel);

		Assert.assertEquals(arquivoDisponivelDtoCovertido.getDataAtendimento(), arquivoDisponivel.getDataAtendimento());
		Assert.assertEquals(arquivoDisponivelDtoCovertido.getHoraInicio(), arquivoDisponivel.getHoraInicio());
		Assert.assertEquals(arquivoDisponivelDtoCovertido.getHoraTermino(), arquivoDisponivel.getHoraTermino());
		Assert.assertEquals(arquivoDisponivelDtoCovertido.getNomeGrupo(), arquivoDisponivel.getNomeGrupo());
		Assert.assertEquals(arquivoDisponivelDtoCovertido.getNomeArquivo(), arquivoDisponivel.getNomeArquivo());
		Assert.assertEquals(arquivoDisponivelDtoCovertido.getTipo(), arquivoDisponivel.getTipo());
		Assert.assertEquals(arquivoDisponivelDtoCovertido.getIdAtendimento(), arquivoDisponivel.getIdAtendimento());
	}
}

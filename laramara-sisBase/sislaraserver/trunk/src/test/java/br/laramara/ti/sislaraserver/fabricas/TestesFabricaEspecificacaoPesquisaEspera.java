package br.laramara.ti.sislaraserver.fabricas;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.dtos.espera.EspecificacaoPesquisaEsperaDTO;
import br.laramara.ti.sislaracommons.utilitarios.DataHoraUtils;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.dominio.espera.EspecificacaoPesquisaEspera;

public class TestesFabricaEspecificacaoPesquisaEspera {

	@Test(groups = { TiposDeTeste.INTEGRACAO_SEM_BD })
	public void fabrica_especificacao_pesquisa_espera_converte_objeto_de_dto_para_dominio() {
		EspecificacaoPesquisaEsperaDTO especificacaoPesquisaDto = ContextoEspecificacaoPesquisaEspera
				.fabricarDtoComTodosOsDados();

		EspecificacaoPesquisaEspera especificacaoPesquisaEspera = new FabricaEspecificacaoPesquisaEspera()
				.converterParaDominio(especificacaoPesquisaDto);

		Assert.assertEquals(especificacaoPesquisaEspera
				.getDescricaoTipoAtendimento().getId(),
				especificacaoPesquisaDto.obterDescricaoTipoAtendimentoDto()
						.getId());
		Assert.assertEquals(especificacaoPesquisaEspera.getModulo().getId(),
				especificacaoPesquisaDto.obterModuloDto().getId());
		Assert.assertEquals(especificacaoPesquisaEspera.getNomeGrupo().getId(),
				especificacaoPesquisaDto.getNomeGrupo().getId());
		Assert.assertEquals(especificacaoPesquisaEspera.getSetor().toString(),
				especificacaoPesquisaDto.obterSetorDto().toString());
		Assert.assertEquals(especificacaoPesquisaEspera.getStatusEspera()
				.toString(), especificacaoPesquisaDto.obterStatusEsperaDto()
				.toString());
		Assert.assertEquals(especificacaoPesquisaEspera.getTipoEspera()
				.toString(), especificacaoPesquisaDto.obterTipoEsperaDto()
				.toString());
		Assert.assertEquals(DataHoraUtils
				.formatarData(especificacaoPesquisaEspera.getDataInicio()),
				especificacaoPesquisaDto.getDataInicio());
		Assert.assertEquals(DataHoraUtils
				.formatarData(especificacaoPesquisaEspera.getDataTermino()),
				especificacaoPesquisaDto.getDataTermino());
		Assert.assertEquals(especificacaoPesquisaEspera.getProntuario()
				.toString(), especificacaoPesquisaDto.getProntuario()
				.toString());
		Assert.assertEquals(especificacaoPesquisaEspera.getRg(),
				especificacaoPesquisaDto.getRg());
		Assert.assertEquals(especificacaoPesquisaEspera.isInteresse(), especificacaoPesquisaDto.getInteresse());
		Assert.assertEquals(especificacaoPesquisaEspera.isLmLigou(), especificacaoPesquisaDto.getLmLigou());
		Assert.assertEquals(especificacaoPesquisaEspera.isPendencias(), especificacaoPesquisaDto.getPendencias());
	}
}

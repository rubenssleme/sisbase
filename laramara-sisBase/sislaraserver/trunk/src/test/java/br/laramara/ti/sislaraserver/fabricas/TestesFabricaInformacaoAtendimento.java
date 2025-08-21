package br.laramara.ti.sislaraserver.fabricas;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.dtos.grupo.InformacaoAtendimentoDTO;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.dominio.grupo.InformacaoAtendimento;

public class TestesFabricaInformacaoAtendimento {

	@Test(groups = { TiposDeTeste.INTEGRACAO_SEM_BD })
	public void fabrica_informacao_atendimento_converte_objeto_de_dto_para_dominio() {
		Long id = new Long(34324);
		InformacaoAtendimentoDTO informacaoAtendimentoDto = ContextoInformacaoAtendimento
				.construirInformacaoAtendimentoDTO();
		informacaoAtendimentoDto.setId(id);
		InformacaoAtendimento informacaoAtendimentoConvertido = new FabricaInformacaoAtendimento()
				.converterParaDominio(informacaoAtendimentoDto);

		Assert.assertEquals(informacaoAtendimentoConvertido.getId().toString(),
				informacaoAtendimentoDto.getId().toString());
		Assert.assertEquals(informacaoAtendimentoConvertido.getDescricao(),
				informacaoAtendimentoDto.getDescricao());
		Assert.assertEquals(informacaoAtendimentoConvertido.getFrequencia()
				.toString(), informacaoAtendimentoDto.getFrequenciaDto()
				.toString());
		Assert.assertEquals(informacaoAtendimentoConvertido.getJustificativa(),
				informacaoAtendimentoDto.getJustificativa());
		Assert.assertEquals(informacaoAtendimentoConvertido.getParticipacaoDetalhada().size(),
				informacaoAtendimentoDto.getParticipacaoDetalhadaDto().size());
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_SEM_BD })
	public void fabrica_informacao_atendimento_converte_objeto_de_dominio_para_dto() {
		Long id = new Long("34324");
		InformacaoAtendimento informacaoAtendimentoDominio = ContextoInformacaoAtendimento
				.fabricarInformacaoTrabalhoComTodosOsDados();
		informacaoAtendimentoDominio.setId(id);
		InformacaoAtendimentoDTO informacaoAtendiementoDtoConvertido = new FabricaInformacaoAtendimento()
				.converterParaDTO(informacaoAtendimentoDominio);

		Assert.assertEquals(informacaoAtendiementoDtoConvertido.getId(),
				informacaoAtendimentoDominio.getId());
		Assert.assertEquals(informacaoAtendiementoDtoConvertido.getDescricao(),
				informacaoAtendimentoDominio.getDescricao());
		Assert.assertEquals(informacaoAtendiementoDtoConvertido
				.getFrequenciaDto().toString(), informacaoAtendimentoDominio
				.getFrequencia().toString());
		Assert.assertEquals(
				informacaoAtendiementoDtoConvertido.getJustificativa(),
				informacaoAtendimentoDominio.getJustificativa());
		Assert.assertEquals(informacaoAtendiementoDtoConvertido.getParticipacaoDetalhadaDto().size(),
				informacaoAtendimentoDominio.getParticipacaoDetalhada().size());
	}
}

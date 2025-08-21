package br.laramara.ti.sislaraserver.fabricas;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.dtos.InformacaoEssencialDTO;
import br.laramara.ti.sislaracommons.utilitarios.DataHoraUtils;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.dominio.InformacaoEssencial;

public class TestesFabricaInformacaoEssencial {
	@Test(groups = { TiposDeTeste.INTEGRACAO_SEM_BD })
	public void fabrica_informacao_essencial_converte_objeto_de_dto_para_dominio() {
		InformacaoEssencialDTO informacaoEssencialDto = ContextoInformacaoEssencial
				.construirInformacaoEssencialDTO();
		InformacaoEssencial informacaoEssencialConvertido = new FabricaInformacaoEssencial()
				.converterParaDominio(informacaoEssencialDto);

		Assert.assertEquals(informacaoEssencialConvertido.getNome(),
				informacaoEssencialDto.getNome().toString());
		Assert.assertEquals(informacaoEssencialConvertido.getDataNascimento(),
				informacaoEssencialDto.getDataNascimento().toString());
		Assert.assertEquals(informacaoEssencialConvertido.getContato()
				.getNomeContato(), informacaoEssencialDto.getContatoDto()
				.getNomeContato().toString());
		Assert.assertEquals(informacaoEssencialConvertido.getCpf(), informacaoEssencialDto.getCpf());
		Assert.assertEquals(informacaoEssencialConvertido.getRg(), informacaoEssencialDto.getRg());
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_SEM_BD })
	public void fabrica_informacao_essencial_converte_objeto_de_dominio_para_dto() {
		InformacaoEssencial informacaoEssencialDominio = ContextoInformacaoEssencial
				.fabricarInformacaoEssencialComTodosOsDados();

		InformacaoEssencialDTO informacaoEssencialConvertido = new FabricaInformacaoEssencial()
				.converterParaDTO(informacaoEssencialDominio);

		Assert.assertEquals(informacaoEssencialConvertido.getId(),
				informacaoEssencialDominio.getId());
		Assert.assertEquals(informacaoEssencialConvertido.getNome().toString(),
				informacaoEssencialDominio.getNome());
		Assert.assertEquals(informacaoEssencialConvertido.getIdade(), String.valueOf(DataHoraUtils
				.obterAnosTranscorridos(DataHoraUtils.criar(informacaoEssencialDominio.getDataNascimento()))));
		Assert.assertEquals(informacaoEssencialConvertido.getDataNascimento()
				.toString(), informacaoEssencialDominio.getDataNascimento());
		Assert.assertEquals(informacaoEssencialConvertido.getContatoDto()
				.getNomeContato().toString(), informacaoEssencialDominio
				.getContato().getNomeContato());
		Assert.assertEquals(informacaoEssencialConvertido.possuiUsuarioAssociado()
				, informacaoEssencialDominio.possuiUsuarioAssociado());
		Assert.assertEquals(informacaoEssencialConvertido.getCpf(), informacaoEssencialDominio.getCpf());
		Assert.assertEquals(informacaoEssencialConvertido.getRg(), informacaoEssencialDominio.getRg());
	}
}

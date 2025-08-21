package br.laramara.ti.sislaraserver.fabricas;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.dtos.contribuicao.ContribuinteDTO;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.dominio.contribuicao.Contribuinte;

public class TestesFabricaContribuinte {
	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void fabrica_contribuinte_converte_objeto_de_dominio_para_dto() {
		Contribuinte contribuinte = ContextoContribuinte
				.fabricarContribuinteComTodosOsDados();

		ContribuinteDTO contribuinteDto = new FabricaContribuinte()
				.converterParaDTO(contribuinte);

		Assert.assertEquals(contribuinteDto.getId(), contribuinte.getId());
		Assert.assertEquals(contribuinteDto.getDataCadastro(), contribuinte.getDataCadastro());
		Assert.assertEquals(contribuinteDto.getEnderecoDto().getEndereco(), contribuinte.getEndereco().getEndereco());
		Assert.assertEquals(contribuinteDto.getContatoDto().getNomeContato(),
				contribuinte.getContato().getNomeContato());
		Assert.assertEquals(contribuinteDto.getStatusContribuinteDto().toString(),
				contribuinte.obterStatusAtual().name());
		Assert.assertEquals(contribuinteDto.getContribuicao(), contribuinte.getValorContribuicao());
		Assert.assertEquals(contribuinteDto.getMotivoDesativacaoDTO().getId(),
				contribuinte.getMotivoDesativacao().getId());
		Assert.assertEquals(contribuinteDto.getCpf(), contribuinte.getCpf());
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void fabrica_contribuinte_converte_objeto_de_dto_para_dominio() {
		ContribuinteDTO contribuinteDto = ContextoContribuinte
				.fabricarContribuinteDtoComTodosOsDados();

		Contribuinte contribuinte = new FabricaContribuinte()
				.converterParaDominio(contribuinteDto);

		Assert.assertEquals(contribuinte.getId(), contribuinteDto.getId());
		Assert.assertEquals(contribuinte.getEndereco().getEndereco(),
				contribuinteDto.getEnderecoDto().getEndereco());
		Assert.assertEquals(contribuinte.getContato().getNomeContato(),
				contribuinteDto.getContatoDto().getNomeContato());
		Assert.assertEquals(contribuinte.obterStatusAtual().name(),
				contribuinteDto.getStatusContribuinteDto().toString());
		Assert.assertEquals(contribuinte.getValorContribuicao(), 
				contribuinteDto.getContribuicao());
		Assert.assertEquals(contribuinte.getMotivoDesativacao().getId(),
				contribuinteDto.getMotivoDesativacaoDTO().getId());
		Assert.assertEquals(contribuinte.getCpf(), contribuinteDto.getCpf());
	}
}

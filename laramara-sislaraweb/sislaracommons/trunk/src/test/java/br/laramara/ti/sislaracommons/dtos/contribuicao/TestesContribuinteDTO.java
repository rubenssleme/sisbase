package br.laramara.ti.sislaracommons.dtos.contribuicao;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.dtos.ContatoDTO;
import br.laramara.ti.sislaracommons.dtos.endereco.EnderecoDTO;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesContribuinteDTO {

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void contribuicaodto_foi_construida_com_sucesso() {
		Long id = new Long(122222);
		String nome = "JOSEP MEAZA";
		String data = "01/01/2000";
		String contribuicao = "334,44";
		String cpf = "71894810287";
		
		MotivoDesativacaoDTO motivoDesativacaoDTO = new MotivoDesativacaoDTO(new Long(id), "Crise");

		ContatoDTO contatoDto = new ContatoDTO();
		contatoDto.setId(id);

		EnderecoDTO enderecoDTO = new EnderecoDTO();
		enderecoDTO.setId(id);

		ContribuinteDTO contribuinteDTO = new ContribuinteDTO();
		contribuinteDTO.setId(id);
		contribuinteDTO.setDataCadastro(data);
		contribuinteDTO.setNomeEmpresa(nome);
		contribuinteDTO.setEnderecoDto(enderecoDTO);
		contribuinteDTO.setContatoDto(contatoDto);
		contribuinteDTO.setContribuicao(contribuicao);
		contribuinteDTO.setMotivoDesativacaoDTO(motivoDesativacaoDTO);
		contribuinteDTO.setCpf(cpf);

		Assert.assertEquals(contribuinteDTO.getId(), id);
		Assert.assertEquals(contribuinteDTO.getDataCadastro(), data);
		Assert.assertEquals(contribuinteDTO.getNomeEmpresa(), nome);
		Assert.assertEquals(contribuinteDTO.getContatoDto().getId(), id);
		Assert.assertEquals(contribuinteDTO.getEnderecoDto().getId(), id);
		Assert.assertEquals(contribuinteDTO.getContribuicao(), contribuicao);
		Assert.assertEquals(contribuinteDTO.getMotivoDesativacaoDTO().getId(), motivoDesativacaoDTO.getId());
		Assert.assertEquals(contribuinteDTO.getCpf(), cpf);
	}
}

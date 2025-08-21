package br.laramara.ti.sislaracommons.dtos.atendimento;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.dtos.atendimento.AtendimentoUsuarioDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.FrequenciaDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.InformacaoAtendimentoDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.UsuarioDTO;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesAtendimentoUsuarioDTO {

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void atendimentousuariodto_foi_construida_com_sucesso() {
		Long id = new Long("1222");
		FrequenciaDTO frequenciaDto = new FrequenciaDTO("AT");
		String textoGrande = "super texto";
		InformacaoAtendimentoDTO informacoesAtendimentoDto = new InformacaoAtendimentoDTO();
		
		AtendimentoUsuarioDTO atendimentoUsuarioDto = new AtendimentoUsuarioDTO();
		UsuarioDTO usuarioDto = new UsuarioDTO(id);

		atendimentoUsuarioDto.setId(id);
		atendimentoUsuarioDto.setUsuarioDto(usuarioDto);
		informacoesAtendimentoDto.setFrequenciaDto(frequenciaDto);
		informacoesAtendimentoDto.setDescricao(textoGrande);
		informacoesAtendimentoDto.setJustificativa(textoGrande);
		atendimentoUsuarioDto
				.setInformacaoAtendimentoDto(informacoesAtendimentoDto);

		
		Assert.assertEquals(atendimentoUsuarioDto.getId(), id);
		Assert.assertEquals(atendimentoUsuarioDto.getUsuarioDto()
				.getId(), id);
		Assert.assertEquals(atendimentoUsuarioDto
				.getInformacaoAtendimentoDto().getFrequenciaDto().toString(),
				frequenciaDto.toString());
		Assert.assertEquals(atendimentoUsuarioDto
				.getInformacaoAtendimentoDto().getDescricao(), textoGrande);
		Assert.assertEquals(atendimentoUsuarioDto
				.getInformacaoAtendimentoDto().getJustificativa(), textoGrande);
	}
}

package br.laramara.ti.sislaracommons.dtos.atendimento;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.dtos.escola.DiretoriaEnsinoDTO;
import br.laramara.ti.sislaracommons.dtos.escola.DreCefaiDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.FrequenciaDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.InformacaoAtendimentoDTO;
import br.laramara.ti.sislaracommons.dtos.instituicao.InstituicaoDTO;
import br.laramara.ti.sislaracommons.dtos.precadastro.PreCadastroDTO;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesAtendimentoPreCadastroDTO {
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void atendimentoprecadastrodto_foi_construida_com_sucesso() {
		Long id = new Long("1222");
		FrequenciaDTO frequenciaDto = new FrequenciaDTO("AT");
		String textoGrande = "super texto";
		InformacaoAtendimentoDTO informacoesAtendimentoDto = new InformacaoAtendimentoDTO();
		
		InstituicaoDTO instituicaoDto = new InstituicaoDTO();
		instituicaoDto.setId(id);
		DreCefaiDTO dreCefaiDto = new DreCefaiDTO(id, "DRE");
		DiretoriaEnsinoDTO diretoriaEnsinoDto = new DiretoriaEnsinoDTO(id, "Diretorioa");
		TipoVinculoDTO tipoVinculoDto = new TipoVinculoDTO(id, "Tipo");

		AtendimentoPreCadastroDTO atendimentoPreCadastroDto = new AtendimentoPreCadastroDTO();
		PreCadastroDTO preCadastroDto = new PreCadastroDTO();
		preCadastroDto.setId(id);

		atendimentoPreCadastroDto.setId(id);
		atendimentoPreCadastroDto.setPreCadastroDto(preCadastroDto);
		informacoesAtendimentoDto.setFrequenciaDto(frequenciaDto);
		informacoesAtendimentoDto.setDescricao(textoGrande);
		informacoesAtendimentoDto.setJustificativa(textoGrande);
		atendimentoPreCadastroDto
				.setInformacaoAtendimentoDto(informacoesAtendimentoDto);
		atendimentoPreCadastroDto.setInstituicaoDto(instituicaoDto);
		atendimentoPreCadastroDto.setDreCefaiDto(dreCefaiDto);
		atendimentoPreCadastroDto.setDiretoriaEnsinoDto(diretoriaEnsinoDto);
		atendimentoPreCadastroDto.setTipoVinculoDto(tipoVinculoDto);
		atendimentoPreCadastroDto.setNomeOrigem(textoGrande);

		Assert.assertEquals(atendimentoPreCadastroDto.getId(), id);
		Assert.assertEquals(atendimentoPreCadastroDto.getPreCadastroDto()
				.getId(), id);
		Assert.assertEquals(atendimentoPreCadastroDto
				.getInformacaoAtendimentoDto().getFrequenciaDto().toString(),
				frequenciaDto.toString());
		Assert.assertEquals(atendimentoPreCadastroDto
				.getInformacaoAtendimentoDto().getDescricao(), textoGrande);
		Assert.assertEquals(atendimentoPreCadastroDto
				.getInformacaoAtendimentoDto().getJustificativa(), textoGrande);
		Assert.assertEquals(atendimentoPreCadastroDto
				.getInstituicaoDto().getId(), id);
		Assert.assertEquals(atendimentoPreCadastroDto
				.getDreCefaiDto().getId(), id);
		Assert.assertEquals(atendimentoPreCadastroDto
				.getDiretoriaEnsinoDto().getId(), id);
		Assert.assertEquals(atendimentoPreCadastroDto
				.getTipoVinculoDto().getId(), id);
		Assert.assertEquals(atendimentoPreCadastroDto
				.getNomeOrigem(), textoGrande);
	}
}

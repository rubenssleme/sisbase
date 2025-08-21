package br.laramara.ti.sislaracommons.dtos.escola;

import java.util.ArrayList;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.dtos.instituicao.InstituicaoDTO;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesInformacaoEducacionalDTO {

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void informacaoEducacionaldto_foi_construida_com_sucesso() {
		Long id = new Long(23);
		String nomeInstituicao = "EE Jose Machada";
		String escolaridade = "SUPERIOR";
		String serie = "1º SÉRIE";
		String situacao = "CURSANDO";
		String periodo = "MANHA";
		String nomeProfessor = "Josep Meaza";
		AreaFormacaoDTO areaFormacaoDto = new AreaFormacaoDTO(id, "Direito");
		String data = "31/12/2013";

		InformacaoEducacionalDTO informacaoEducacionalDto = new InformacaoEducacionalDTO();
		informacaoEducacionalDto.setId(id);
		InstituicaoDTO instituicaoDto = new InstituicaoDTO();
		instituicaoDto.setNome(nomeInstituicao);
		informacaoEducacionalDto.setInstituicaoDto(instituicaoDto);
		informacaoEducacionalDto.setEscolaridadeDto(new EscolaridadeDTO(
				new Long(1), escolaridade, new ArrayList<SerieDTO>()));
		informacaoEducacionalDto.setSerieDto(new SerieDTO(id, serie));
		informacaoEducacionalDto
				.setSituacaoEducacionalDto(new SituacaoEducacionalDTO(situacao));
		informacaoEducacionalDto.setPeriodoDto(new PeriodoDTO(periodo));
		informacaoEducacionalDto.setNomeProfessor(nomeProfessor);
		informacaoEducacionalDto.setAreaFormacaoDto(areaFormacaoDto);
		informacaoEducacionalDto.setDataReferencia(data);

		Assert.assertEquals(informacaoEducacionalDto.getId(), id);
		Assert.assertEquals(informacaoEducacionalDto.getInstituicaoDto()
				.getNome(), nomeInstituicao);
		Assert.assertEquals(informacaoEducacionalDto.getEscolaridadeDto()
				.toString(), escolaridade);
		Assert.assertEquals(informacaoEducacionalDto.getSerieDto().toString(),
				serie);
		Assert.assertEquals(informacaoEducacionalDto
				.getSituacaoEducacionalDto().toString(), situacao);
		Assert.assertEquals(
				informacaoEducacionalDto.getPeriodoDto().toString(), periodo);
		Assert.assertEquals(informacaoEducacionalDto.getNomeProfessor(),
				nomeProfessor);
		Assert.assertEquals(informacaoEducacionalDto.getAreaFormacaoDto()
				.getId(), areaFormacaoDto.getId());
		Assert.assertEquals(informacaoEducacionalDto.getDataReferencia(), data);
	}
}

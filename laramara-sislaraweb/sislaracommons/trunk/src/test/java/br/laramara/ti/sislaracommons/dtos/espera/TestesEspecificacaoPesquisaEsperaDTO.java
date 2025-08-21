package br.laramara.ti.sislaracommons.dtos.espera;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.dtos.grupo.DescricaoTipoAtendimentoDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.ModuloDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.NomeGrupoDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.SetorDTO;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesEspecificacaoPesquisaEsperaDTO {
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void especificacaopesquisaesperadto_sem_erro_foi_construido() {

		Long id = new Long(12222);
		String data = "31/12/2012";
		DescricaoTipoAtendimentoDTO descricaoTipoAtendimentoDto = new DescricaoTipoAtendimentoDTO();
		descricaoTipoAtendimentoDto.setId(new Long(1));
		ModuloDTO moduloDto = new ModuloDTO(id, "Informatica");
		NomeGrupoDTO nomeGrupoDto = new NomeGrupoDTO(new Long(12), "G02");
		SetorDTO setorDto = new SetorDTO("CTO");
		StatusEsperaDTO statusEsperaDto = new StatusEsperaDTO("AGENDADO");
		TipoEsperaDTO tipoEsperaDto = new TipoEsperaDTO("LU");
		String prontuario = "12345";
		String rg = "348734A";

		EspecificacaoPesquisaEsperaDTO especificacaoDto = new EspecificacaoPesquisaEsperaDTO();
		especificacaoDto
				.setDescricaoTipoAtendimentoDto(descricaoTipoAtendimentoDto);
		especificacaoDto.setModuloDto(moduloDto);
		especificacaoDto.setNomeGrupo(nomeGrupoDto);
		especificacaoDto.setSetorDto(setorDto);
		especificacaoDto.setDataInicio(data);
		especificacaoDto.setDataTermino(data);
		especificacaoDto.setStatusEsperadoDto(statusEsperaDto);
		especificacaoDto.setTipoEsperaDto(tipoEsperaDto);
		especificacaoDto.setProntuario(prontuario);
		especificacaoDto.setRg(rg);

		Assert.assertEquals(especificacaoDto.obterDescricaoTipoAtendimentoDto()
				.getId(), descricaoTipoAtendimentoDto.getId());
		Assert.assertEquals(especificacaoDto.obterModuloDto().getId(),
				moduloDto.getId());
		Assert.assertEquals(especificacaoDto.getNomeGrupo().toString(),
				nomeGrupoDto.toString());
		Assert.assertEquals(especificacaoDto.getDataInicio(), data);
		Assert.assertEquals(especificacaoDto.getDataTermino(), data);
		Assert.assertEquals(especificacaoDto.obterSetorDto().toString(),
				setorDto.toString());
		Assert.assertEquals(especificacaoDto.obterStatusEsperaDto().toString(),
				statusEsperaDto.toString());
		Assert.assertEquals(especificacaoDto.obterTipoEsperaDto().toString(),
				tipoEsperaDto.toString());
		Assert.assertEquals(especificacaoDto.getProntuario().toString(), prontuario);
		Assert.assertEquals(especificacaoDto.getRg(), rg);
	}
}

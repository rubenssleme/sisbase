package br.laramara.ti.sislaracommons.dtos.doacao;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.dtos.grupo.RecursoDTO;
import br.laramara.ti.sislaracommons.dtos.precadastro.PreCadastroDTO;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesEspecificacaoPesquisaDemandaDTO {

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void especificacaopesquisademandadto_sem_erro_foi_construido() {
		PreCadastroDTO preCadastroDto = new PreCadastroDTO();
		String prontuario = "1234";
		String cpf = "71894810287";
		Long id = new Long(12222);
		preCadastroDto.setId(id);
		StatusDemandaDTO statusDemandaDTO = new StatusDemandaDTO("AGUARDANDO");
		RecursoDTO recurso = new RecursoDTO(id, "Recurso", false, "100,00");
		ProjetoDTO projetoDto = new ProjetoDTO();
		projetoDto.setId(id);
		EspecificacaoPesquisaDemandaDTO especificacaoDto = new EspecificacaoPesquisaDemandaDTO();
		especificacaoDto.setRecursoDto(recurso);
		especificacaoDto.setProntuario(prontuario);
		especificacaoDto.setPreCadastroDto(preCadastroDto);
		especificacaoDto.setStatusDemandaDto(statusDemandaDTO);
		especificacaoDto.setCpf(cpf);

		Assert.assertEquals(especificacaoDto.getRecurso().getId(),
				recurso.getId());
		Assert.assertEquals(especificacaoDto.getProntuario(), prontuario);
		Assert.assertEquals(especificacaoDto.getPreCadastro().getId(),
				preCadastroDto.getId());
		Assert.assertEquals(especificacaoDto.getStatusDemandaDTO().toString(), statusDemandaDTO.toString());
		Assert.assertEquals(especificacaoDto.getCpf(), cpf);
	}
}

package br.laramara.ti.sislaracommons.dtos.grupo;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.dtos.atendimento.TipoVinculoDTO;
import br.laramara.ti.sislaracommons.dtos.instituicao.InstituicaoDTO;
import br.laramara.ti.sislaracommons.dtos.precadastro.PreCadastroDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.UsuarioDTO;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesModuloPreCadastroDTO {
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void modulouprecadastrodto_foi_construida_com_sucesso() {

		Long id = new Long(12222);
		ModuloPeriodoDTO moduloPeriodoDTO = new ModuloPeriodoDTO();
		ModuloDTO moduloDto = new ModuloDTO(new Long(1), "Informática");
		moduloPeriodoDTO.setModuloDto(moduloDto);
		String dataInicio = "01/01/2011";
		String dataDesligamento = "31/12/2011";
		String status = "33";
		String obs = "GIgantesto texto de observações.";
		boolean aprovado = true;
		TipoVinculoDTO tipoVinculoDto = new TipoVinculoDTO(new Long(1),
				"Empresa");
		String nomeOrigemComunidade = "CTIS";
		String curso = "TI";
		PreCadastroDTO preCadastroDto = new PreCadastroDTO();
		preCadastroDto.setId(id);

		InstituicaoDTO instituicaoDto = new InstituicaoDTO();
		instituicaoDto.setId(id);

		UsuarioDTO usuarioDto = new UsuarioDTO(id);

		ModuloPreCadastroDTO moduloPreCadastroDto = new ModuloPreCadastroDTO();
		moduloPreCadastroDto.setId(id);
		moduloPreCadastroDto.setPreCadastroDto(preCadastroDto);
		moduloPreCadastroDto.setUsuarioVinculadoDto(usuarioDto);
		moduloPreCadastroDto.setDataInicio(dataInicio);
		moduloPreCadastroDto.setDataOcorrencia(dataDesligamento);
		moduloPreCadastroDto
				.setStatusDto(new StatusRelacaoComModuloDTO(status));
		moduloPreCadastroDto.setObs(obs);
		moduloPreCadastroDto.setAprovado(aprovado);
		moduloPreCadastroDto.setTipoVinculoDto(tipoVinculoDto);
		moduloPreCadastroDto.setNomeOrigemComunidade(nomeOrigemComunidade);
		moduloPreCadastroDto.setCurso(curso);
		moduloPreCadastroDto.setInstituicaoDto(instituicaoDto);
		moduloPreCadastroDto.setInstituicaoComSrmsDto(instituicaoDto);
		moduloPreCadastroDto.setInstituicaoComSalaDeRecursoDto(instituicaoDto);
		moduloPreCadastroDto.setInstituicaoComOutrosAEEDto(instituicaoDto);

		Assert.assertEquals(moduloPreCadastroDto.getId(), id);
		Assert.assertEquals(moduloPreCadastroDto.getPreCadastroDto().getId(),
				id);
		Assert.assertEquals(moduloPreCadastroDto.getUsuarioVinculadoDto().getId(), id);
		Assert.assertEquals(moduloPreCadastroDto.getDataInicio(), dataInicio);
		Assert.assertEquals(moduloPreCadastroDto.getDataOcorrencia(),
				dataDesligamento);
		Assert.assertEquals(moduloPreCadastroDto.getStatusDto().toString(),
				status);
		Assert.assertEquals(moduloPreCadastroDto.getObs(), obs);
		Assert.assertEquals(moduloPreCadastroDto.isAprovado(), aprovado);
		Assert.assertEquals(
				moduloPreCadastroDto.getTipoVinculoDto().toString(),
				tipoVinculoDto.toString());
		Assert.assertEquals(moduloPreCadastroDto.getNomeOrigemComunidade(),
				nomeOrigemComunidade);
		Assert.assertEquals(moduloPreCadastroDto.getCurso(), curso);
		Assert.assertEquals(moduloPreCadastroDto.getInstituicaoComSRMsDto()
				.getId(), instituicaoDto.getId());
		Assert.assertEquals(moduloPreCadastroDto
				.getInstituicaoComSalaDeRecursoDto().getId(), instituicaoDto
				.getId());
		Assert.assertEquals(moduloPreCadastroDto
				.getInstituicaoComOutrosAEEDto().getId(), instituicaoDto
				.getId());
		Assert.assertEquals(moduloPreCadastroDto.getInstituicaoDto().getId(),
				instituicaoDto.getId());
	}
}

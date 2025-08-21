package br.laramara.ti.sislaraserver.fabricas;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.dtos.grupo.ModuloPreCadastroDTO;
import br.laramara.ti.sislaracommons.utilitarios.DataHoraUtils;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.dominio.grupo.ModuloPreCadastro;

public class TestesFabricaModuloPreCadastro {
	@Test(groups = { TiposDeTeste.INTEGRACAO_SEM_BD })
	public void fabrica_moduloprecadastro_converte_objeto_de_dominio_para_dto() {
		ModuloPreCadastro moduloPreCadastro = ContextoModuloPreCadastro
				.fabricarComTodosOsDados();
		moduloPreCadastro.setId(new Long(1222));
		moduloPreCadastro.getPreCadastro().setId(new Long(12222));

		ModuloPreCadastroDTO moduloPreCadastroDTO = new FabricaModuloPreCadastro()
				.converterParaDTO(moduloPreCadastro);

		Assert.assertEquals(moduloPreCadastroDTO.getId(),
				moduloPreCadastro.getId());
		Assert.assertEquals(moduloPreCadastroDTO.getPreCadastroDto().getId(),
				moduloPreCadastro.getPreCadastro().getId());
		Assert.assertEquals(moduloPreCadastroDTO.getUsuarioVinculadoDto()
				.getId(), moduloPreCadastro.getUsuarioVinculado().getId());
		Assert.assertEquals(moduloPreCadastroDTO.getDataInicio(),
				moduloPreCadastro.getDataInicio());
		Assert.assertEquals(moduloPreCadastroDTO.getDataOcorrencia(),
				moduloPreCadastro.getDataOcorrencia());
		Assert.assertEquals(moduloPreCadastroDTO.getStatusDto().toString(),
				moduloPreCadastro.getStatus().toString());
		Assert.assertEquals(moduloPreCadastroDTO.getObs(),
				moduloPreCadastro.getObs());
		Assert.assertEquals(moduloPreCadastroDTO.isAprovado(),
				moduloPreCadastro.isAprovado());
		Assert.assertEquals(moduloPreCadastroDTO.getTipoVinculoDto()
				.toString(), moduloPreCadastro.getTipoVinculo().getDescricao());
		Assert.assertEquals(moduloPreCadastroDTO.getNomeOrigemComunidade(),
				moduloPreCadastro.getNomeOrigemComunidade());
		Assert.assertEquals(moduloPreCadastroDTO.getCurso(),
				moduloPreCadastro.getCurso());
		Assert.assertEquals(moduloPreCadastroDTO.getInstituicaoDto()
					.getNome(), moduloPreCadastro.getInstituicao().getNome());
		Assert.assertEquals(moduloPreCadastroDTO.getInstituicaoComSRMsDto()
				.getNome(), moduloPreCadastro.getInstituicaoComSrms().getNome());
		Assert.assertEquals(moduloPreCadastroDTO
				.getInstituicaoComSalaDeRecursoDto().getNome(),
				moduloPreCadastro.getInstituicaoComSalaRecurso().getNome());
		Assert.assertEquals(moduloPreCadastroDTO
				.getInstituicaoComOutrosAEEDto().getNome(), moduloPreCadastro
				.getInstituicaoComOutrosAEE().getNome());
		Assert.assertEquals(moduloPreCadastroDTO.getDreCefaiDto()
					.toString(), moduloPreCadastro.getDreCefai().getNome());
		Assert.assertEquals(moduloPreCadastroDTO.getDiretoriaEnsinoDto()
					.toString(), moduloPreCadastro.getDiretoriaEnsino().getNome());
		Assert.assertEquals(moduloPreCadastroDTO.getQuantidadeCriancas(), moduloPreCadastro.getQuantidadeCriancas());
		Assert.assertEquals(moduloPreCadastroDTO.getQuantidadeAdultos(), moduloPreCadastro.getQuantidadeAdultos());
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_SEM_BD })
	public void fabrica_moduloprecadastro_converte_objeto_de_dto_para_dominio() {
		ModuloPreCadastroDTO moduloPreCadastroDto = ContextoModuloPreCadastro
				.fabricarModuloPreCadastroDTO();
		moduloPreCadastroDto.setId(new Long(1233));

		ModuloPreCadastro moduloPreCadastroObtido = new FabricaModuloPreCadastro()
				.converterParaDominio(moduloPreCadastroDto);

		Assert.assertEquals(moduloPreCadastroObtido.getId(),
				moduloPreCadastroDto.getId());
		Assert.assertEquals(moduloPreCadastroObtido.getUsuarioVinculado()
				.getId(), moduloPreCadastroDto.getUsuarioVinculadoDto().getId());
		Assert.assertEquals(moduloPreCadastroObtido.getDataInicio(),
				DataHoraUtils.formatarData(DataHoraUtils
						.criar(moduloPreCadastroDto.getDataInicio())));
		Assert.assertEquals(moduloPreCadastroObtido.getDataOcorrencia(),
				DataHoraUtils.formatarData(DataHoraUtils
						.criar(moduloPreCadastroDto.getDataOcorrencia())));
		Assert.assertEquals(moduloPreCadastroObtido.getStatus().toString(),
				moduloPreCadastroDto.getStatusDto().toString());
		Assert.assertEquals(moduloPreCadastroObtido.getObs(),
				moduloPreCadastroDto.getObs());
		Assert.assertEquals(moduloPreCadastroObtido.isAprovado(),
				moduloPreCadastroDto.isAprovado());
		Assert.assertEquals(moduloPreCadastroObtido.getTipoVinculo()
				.getDescricao(), moduloPreCadastroDto.getTipoVinculoDto()
				.toString());
		Assert.assertEquals(moduloPreCadastroObtido.getNomeOrigemComunidade(),
				moduloPreCadastroDto.getNomeOrigemComunidade());
		Assert.assertEquals(moduloPreCadastroObtido.getCurso(),
				moduloPreCadastroDto.getCurso());
		Assert.assertEquals(moduloPreCadastroObtido.getInstituicao()
					.getNome(), moduloPreCadastroDto.getInstituicaoDto().getNome());
		Assert.assertEquals(moduloPreCadastroObtido.getInstituicaoComSrms()
				.getNome(), moduloPreCadastroDto.getInstituicaoComSRMsDto()
				.getNome());
		Assert.assertEquals(moduloPreCadastroObtido
				.getInstituicaoComSalaRecurso().getNome(), moduloPreCadastroDto
				.getInstituicaoComSalaDeRecursoDto().getNome());
		Assert.assertEquals(moduloPreCadastroObtido
				.getInstituicaoComOutrosAEE().getNome(), moduloPreCadastroDto
				.getInstituicaoComOutrosAEEDto().getNome());
		Assert.assertEquals(moduloPreCadastroObtido.getDreCefai()
					.getNome(), moduloPreCadastroDto.getDreCefaiDto().toString());
		Assert.assertEquals(moduloPreCadastroObtido.getDiretoriaEnsino()
					.getNome(), moduloPreCadastroDto.getDiretoriaEnsinoDto().toString());
		Assert.assertEquals(moduloPreCadastroObtido.getQuantidadeCriancas(),
				moduloPreCadastroDto.getQuantidadeCriancas());
		Assert.assertEquals(moduloPreCadastroObtido.getQuantidadeAdultos(),
				moduloPreCadastroDto.getQuantidadeAdultos());
	}
}

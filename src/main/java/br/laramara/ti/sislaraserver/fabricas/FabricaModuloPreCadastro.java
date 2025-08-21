package br.laramara.ti.sislaraserver.fabricas;

import br.laramara.ti.sislaracommons.dtos.grupo.ModuloPreCadastroDTO;
import br.laramara.ti.sislaraserver.dominio.grupo.ModuloPreCadastro;

public class FabricaModuloPreCadastro extends
		FabricaRecursiva<ModuloPreCadastroDTO, ModuloPreCadastro> {

	@Override
	public ModuloPreCadastroDTO converterParaDTO(
			ModuloPreCadastro moduloPreCadastro) {
		ModuloPreCadastroDTO moduloPreCadastroDto = null;
		if (moduloPreCadastro != null) {
			moduloPreCadastroDto = new ModuloPreCadastroDTO();
			moduloPreCadastroDto.setId(moduloPreCadastro.getId());
			moduloPreCadastroDto.setPreCadastroDto(new FabricaPreCadastro()
					.converterParaDTO(moduloPreCadastro.getPreCadastro()));
			moduloPreCadastroDto.setUsuarioVinculadoDto(new FabricaUsuario()
					.converterParaDTO(moduloPreCadastro.getUsuarioVinculado()));
			moduloPreCadastroDto.setDataInicio(moduloPreCadastro
					.getDataInicio());
			moduloPreCadastroDto.setDataOcorrencia(moduloPreCadastro
					.getDataOcorrencia());
			moduloPreCadastroDto
					.setStatusDto(new FabricaStatusRelacaoComModulo()
							.converterParaDTO(moduloPreCadastro.getStatus()));
			moduloPreCadastroDto.setAprovado(moduloPreCadastro.isAprovado());
			moduloPreCadastroDto.setObs(moduloPreCadastro.getObs());
			moduloPreCadastroDto
					.setTipoVinculoDto(new FabricaTipoVinculo()
							.converterParaDTO(moduloPreCadastro
									.getTipoVinculo()));
			moduloPreCadastroDto.setNomeOrigemComunidade(moduloPreCadastro
					.getNomeOrigemComunidade());
			moduloPreCadastroDto.setCurso(moduloPreCadastro.getCurso());
			moduloPreCadastroDto.setInstituicaoDto(new FabricaInstituicao()
					.converterParaDTO(moduloPreCadastro
							.getInstituicao()));
			moduloPreCadastroDto
					.setInstituicaoComSrmsDto(new FabricaInstituicao()
							.converterParaDTO(moduloPreCadastro
									.getInstituicaoComSrms()));
			moduloPreCadastroDto
					.setInstituicaoComSalaDeRecursoDto(new FabricaInstituicao()
							.converterParaDTO(moduloPreCadastro
									.getInstituicaoComSalaRecurso()));
			moduloPreCadastroDto
					.setInstituicaoComOutrosAEEDto(new FabricaInstituicao()
							.converterParaDTO(moduloPreCadastro
									.getInstituicaoComOutrosAEE()));
			moduloPreCadastroDto.setDreCefaiDto(new FabricaDreCefai()
					.converterParaDTO(moduloPreCadastro
								.getDreCefai()));
			moduloPreCadastroDto.setDiretoriaEnsinoDto(new FabricaDiretoriaEnsino()
					.converterParaDTO(moduloPreCadastro
							.getDiretoriaEnsino()));	
			moduloPreCadastroDto.setQuantidadeCriancas(moduloPreCadastro.getQuantidadeCriancas());
			moduloPreCadastroDto.setQuantidadeAdultos(moduloPreCadastro.getQuantidadeAdultos());
		}
		return moduloPreCadastroDto;
	}

	@Override
	public ModuloPreCadastro converterParaDominio(
			ModuloPreCadastroDTO moduloPreCadastroDto,
			ModuloPreCadastro moduloPreCadastro) {
		if (moduloPreCadastroDto != null) {
			if (moduloPreCadastro == null) {
				moduloPreCadastro = new ModuloPreCadastro();
			}
			moduloPreCadastro.setId(moduloPreCadastroDto.getId());
			moduloPreCadastro.setPreCadastro(new FabricaPreCadastro()
					.converterParaDominio(
							moduloPreCadastroDto.getPreCadastroDto(),
							moduloPreCadastro.getPreCadastro()));
			moduloPreCadastro.setUsuarioVinculado(new FabricaUsuario()
					.converterParaDominio(
							moduloPreCadastroDto.getUsuarioVinculadoDto(),
							moduloPreCadastro.getUsuarioVinculado()));
			moduloPreCadastro.setDataInicio(moduloPreCadastroDto
					.getDataInicio());
			moduloPreCadastro.setDataOcorrencia(moduloPreCadastroDto
					.getDataOcorrencia());
			moduloPreCadastro.setStatus(new FabricaStatusRelacaoComModulo()
					.converterParaDominio(moduloPreCadastroDto.getStatusDto()));
			moduloPreCadastro.setAprovado(moduloPreCadastroDto.isAprovado());
			moduloPreCadastro.setObs(moduloPreCadastroDto.getObs());
			moduloPreCadastro.setTipoVinculo(new FabricaTipoVinculo()
					.converterParaDominio(moduloPreCadastroDto
							.getTipoVinculoDto()));
			moduloPreCadastro.setNomeOrigemComunidade(moduloPreCadastroDto
					.getNomeOrigemComunidade());
			moduloPreCadastro.setCurso(moduloPreCadastroDto.getCurso());
			moduloPreCadastro.setInstituicao(new FabricaInstituicao()
					.converterParaDominio(moduloPreCadastroDto
							.getInstituicaoDto()));
			moduloPreCadastro.setInstituicaoComSrms(new FabricaInstituicao()
					.converterParaDominio(moduloPreCadastroDto
							.getInstituicaoComSRMsDto()));
			moduloPreCadastro
					.setInstituicaoComSalaRecurso(new FabricaInstituicao()
							.converterParaDominio(moduloPreCadastroDto
									.getInstituicaoComSalaDeRecursoDto()));
			moduloPreCadastro
					.setInstituicaoComOutrosAEE(new FabricaInstituicao()
							.converterParaDominio(moduloPreCadastroDto
									.getInstituicaoComOutrosAEEDto()));
			moduloPreCadastro.setDreCefai(new FabricaDreCefai()
					.converterParaDominio(moduloPreCadastroDto
							.getDreCefaiDto()));
			moduloPreCadastro.setDiretoriaEnsino(new FabricaDiretoriaEnsino()
					.converterParaDominio(moduloPreCadastroDto
							.getDiretoriaEnsinoDto()));
			moduloPreCadastro.setQuantidadeCriancas(moduloPreCadastroDto.getQuantidadeCriancas());
			moduloPreCadastro.setQuantidadeAdultos(moduloPreCadastroDto.getQuantidadeAdultos());
		}
		return moduloPreCadastro;
	}

	@Override
	public ModuloPreCadastro obterNovo() {
		return new ModuloPreCadastro();
	}
}
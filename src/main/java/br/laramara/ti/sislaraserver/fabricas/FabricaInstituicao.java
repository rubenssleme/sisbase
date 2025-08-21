package br.laramara.ti.sislaraserver.fabricas;

import br.laramara.ti.sislaracommons.dtos.endereco.EnderecoDTO;
import br.laramara.ti.sislaracommons.dtos.instituicao.InstituicaoDTO;
import br.laramara.ti.sislaraserver.dominio.instituicao.Instituicao;

public class FabricaInstituicao extends FabricaBase<InstituicaoDTO, Instituicao> {

	public final Instituicao converterParaDominio(
			InstituicaoDTO instituicaoDto) {
		Instituicao instituicao = null;
		if (instituicaoDto != null) {
			instituicao = new Instituicao();
			if (instituicaoDto.getId() != null) {
				instituicao.setId(instituicaoDto.getId());
			}
			instituicao.setTipoInstituicao(new FabricaTipoInstituicao()
					.converterParaDominio(instituicaoDto
							.getTipoInstituicaoDto()));
			instituicao.setNome(instituicaoDto.getNome());
			instituicao.setEndereco(new FabricaEndereco()
					.converterParaDominio(instituicaoDto.getEnderecoDto()));

			instituicao.setNomeCoordenadorResponsavel(instituicaoDto
					.getNomeCoordenadorResponsavel());
			instituicao.setClassificacao(new FabricaClassificacaoInstituicao()
					.converterParaDominio(instituicaoDto
							.getClassificacaoInstituicaoDto()));
			instituicao.setObs(instituicaoDto.getObs());
			instituicao.setTipoApoio(new FabricaTipoApoio()
					.converterParaDominio(instituicaoDto.getTipoApoioDto()));
			instituicao.setTiposEspecialidade(new FabricaTipoEspecialidade()
					.converterParaDominio(instituicaoDto.getTiposEspecialidadeDTO()));
			instituicao.setDreCefai(new FabricaDreCefai()
					.converterParaDominio(instituicaoDto.getDreCefaiDto()));
			instituicao.setDiretoriaEnsino(new FabricaDiretoriaEnsino()
					.converterParaDominio(instituicaoDto
							.getDiretoriaEnsinoDto()));
			instituicao
					.setEscolaridades(new FabricaEscolaridade()
							.converterParaDominio(instituicaoDto
									.getEscolaridadesDto()));
			instituicao.setEndereco(new FabricaEndereco()
					.converterParaDominio(instituicaoDto.getEnderecoDto()));
			instituicao.setContato(new FabricaContato()
					.converterParaDominio(instituicaoDto.getContatoDto()));
		}
		return instituicao;
	}

	public final InstituicaoDTO converterParaDTO(
			Instituicao instituicao) {
		InstituicaoDTO instituicaoDto = null;
		if (instituicao != null) {
			instituicaoDto = new InstituicaoDTO();
			instituicaoDto.setId(instituicao.getId());
			instituicaoDto.setTipoInstituicaoDto(new FabricaTipoInstituicao()
					.converterParaDTO(instituicao.getTipoInstituicao()));
			instituicaoDto.setNome(instituicao.getNome());
			instituicaoDto.setEnderecoDto(new FabricaEndereco()
					.converterParaDTO(instituicao.getEndereco()));
			instituicaoDto.setContatoDto(new FabricaContato()
					.converterParaDTO(instituicao.getContato()));
			instituicaoDto.setNomeCoordenadorResponsavel(instituicao
					.getNomeCoordenadorResponsavel());
			instituicaoDto
					.setClassificacaoInstituicaoDto(new FabricaClassificacaoInstituicao()
							.converterParaDTO(instituicao
									.getClassificacaoInstituicao()));
			instituicaoDto.setObs(instituicao.getObs());
			instituicaoDto.setTipoApoioDto(new FabricaTipoApoio()
					.converterParaDTO(instituicao.getTipoApoio()));
			instituicaoDto.setTiposEspecialidadeDTO(new FabricaTipoEspecialidade()
					.converterParaDTO(instituicao.getTiposEspecialidade()));
			instituicaoDto.setDreCefaiDto(new FabricaDreCefai()
					.converterParaDTO(instituicao.getDreCefai()));
			instituicaoDto.setDiretoriaEnsinoDto(new FabricaDiretoriaEnsino()
					.converterParaDTO(instituicao.getDiretoriaEnsino()));
			instituicaoDto.setEscolaridadesDto(new FabricaEscolaridade()
					.converterParaDTO(instituicao.getEscolaridades()));
			EnderecoDTO enderecoDto = new FabricaEndereco()
					.converterParaDTO(instituicao.getEndereco());
			instituicaoDto.setEnderecoDto(enderecoDto);
		}

		return instituicaoDto;
	}
}

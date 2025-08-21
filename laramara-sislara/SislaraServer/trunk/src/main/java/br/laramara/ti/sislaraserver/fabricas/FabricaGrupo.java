package br.laramara.ti.sislaraserver.fabricas;

import br.laramara.ti.sislaracommons.dtos.grupo.GrupoDTO;
import br.laramara.ti.sislaraserver.dominio.grupo.Grupo;


public class FabricaGrupo extends FabricaRecursiva<GrupoDTO, Grupo> {

	@Override
	public GrupoDTO converterParaDTO(Grupo grupo) {
		GrupoDTO grupoDto = null;
		if (grupo != null) {
			grupoDto = new GrupoDTO();
			grupoDto.setId(grupo.getId());
			grupoDto.setVersao(grupo.getVersao());
			grupoDto.setInicializado(grupo.isInicializado());
			grupoDto.setAtivo(grupo.isAtivo());
			grupoDto.setSetorDto(new FabricaSetor().converterParaDTO(grupo
					.getSetor()));
			grupoDto.setDescricaoTipoAtendimentoDTO(new FabricaDescricaoTipoAtendimento()
					.converterParaDTO(grupo.getDescricaoTipoAtendimento()));
			grupoDto.setDataInicio(grupo.getDataInicio());
			grupoDto.setDataTermino(grupo
					.getDataTermino());
			grupoDto.setNomeGrupoDto(new FabricaNomeGrupo()
					.converterParaDTO(grupo.getNomeGrupo()));
			grupoDto.setTurma(grupo.getTurma());
			grupoDto.setNivel(grupo.getNivel());
			grupoDto.setModulosPeriodosDto(new FabricaModuloPeriodo()
					.converterParaDTO(grupo.getModulosPeriodos()));
			grupoDto.setTipificacoesServicoDto(new FabricaTipificacaoServico()
					.converterParaDTO(grupo.getTipificacoesServico()));
			grupoDto.setDescricao(grupo.getDescricao());
			grupoDto.setInstituicaoDoacaoDto(new FabricaInstituicao()
					.converterParaDTO(grupo.getInstituicaoDoacao()));
			grupoDto.setDoacaoRecursoDto(new FabricaLoteRecurso()
					.converterParaDTO(grupo.getDoacaoRecurso()));
			grupoDto.setTodosUsuariosDto(new FabricaUsuario().converterParaDTO(grupo
					.obterTodosUsuarios()));
			grupoDto.setTodosPreCadastro(new FabricaPreCadastro().converterParaDTO(grupo
					.obterTodosPreCadastro()));
		}
		return grupoDto;
	}

	@Override
	public Grupo converterParaDominio(GrupoDTO grupoDto, Grupo grupo) {
		if (grupoDto != null) {
			if (grupo == null) {
				grupo = new Grupo();
			}
			grupo.setId(grupoDto.getId());
			grupo.setAtivo(grupoDto.isAtivo());
			grupo.setSetor(new FabricaSetor().converterParaDominio(grupoDto
					.getSetorDto()));
			grupo.setDescricaoTipoAtendimento(new FabricaDescricaoTipoAtendimento()
					.converterParaDominio(grupoDto
							.getDescricaoTipoAtendimentoDTO()));
			grupo.setDataInicio(grupoDto.getDataInicio());
			grupo.setDataTermino(grupoDto.getDataTermino());
			grupo.setNomeGrupo(new FabricaNomeGrupo()
					.converterParaDominio(grupoDto.getNomeGrupoDto()));
			grupo.setTurma(grupoDto.getTurma());
			grupo.setNivel(grupoDto.getNivel());
			grupo.setModulosPeriodos(new FabricaModuloPeriodo()
					.converterParaDominio(grupoDto.getModulosPeriodosDto(),
							grupo.getModulosPeriodos()));
			grupo.setTipificacoesServico(new FabricaTipificacaoServico()
					.converterParaDominio(grupoDto.getTipificacoesServicoDto()));
			grupo.setDescricao(grupoDto.getDescricao());
			grupo.setInstituicaoDoacao(new FabricaInstituicao()
					.converterParaDominio(grupoDto.getInstituicaoDoacaoDto()));
			grupo.setDoacaoRecurso(new FabricaLoteRecurso()
					.converterParaDominio(grupoDto.getDoacaoRecursoDto()));
		}
		return grupo;
	}

	@Override
	public Grupo obterNovo() {
		return new Grupo();
	}
}

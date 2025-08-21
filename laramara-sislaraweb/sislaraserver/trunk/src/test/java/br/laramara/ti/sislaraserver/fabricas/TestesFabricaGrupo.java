package br.laramara.ti.sislaraserver.fabricas;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.dtos.grupo.GrupoDTO;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.dominio.grupo.Grupo;

public class TestesFabricaGrupo {

	@Test(groups = { TiposDeTeste.INTEGRACAO_SEM_BD })
	public void fabrica_grupo_converte_objeto_de_dominio_para_dto() {
		Grupo grupo = ContextoGrupo.fabricarGrupoComTodosOsDados();

		GrupoDTO grupoDTO = new FabricaGrupo().converterParaDTO(grupo);

		Assert.assertEquals(grupoDTO.getId(), grupo.getId());
		Assert.assertEquals(grupoDTO.isInicializado(), grupo.isInicializado());
		Assert.assertEquals(grupoDTO.getVersao(), grupo.getVersao());
		Assert.assertEquals(grupoDTO.isAtivo(), grupo.isAtivo());
		Assert.assertEquals(grupoDTO.getSetorDto().toString(), grupo.getSetor()
				.toString());
		Assert.assertEquals(grupoDTO.getDescricaoTipoAtendimentoDTO().getNome(), grupo
				.getDescricaoTipoAtendimento().getNome());
		Assert.assertEquals(grupoDTO.getDataInicio(),
				grupo.getDataInicio());
		Assert.assertEquals(grupoDTO.getDataTermino(), grupo.getDataTermino());
		Assert.assertEquals(grupoDTO.getNomeGrupoDto().toString(), grupo
				.getNomeGrupo().getNome());
		Assert.assertEquals(grupoDTO.getTurma(), grupo.getTurma());
		Assert.assertEquals(grupoDTO.getNivel(), grupo.getNivel());
		Assert.assertEquals(grupoDTO.getModulosPeriodosDto().size(), grupo
				.getModulosPeriodos().size());
		Assert.assertEquals(grupoDTO.getDescricao(), grupo.getDescricao());
		Assert.assertEquals(grupoDTO.getTipificacoesServicoDto().size(), grupo
				.getTipificacoesServico().size());
		Assert.assertEquals(grupoDTO.getInstituicaoDoacaoDto().getNome(), 
				grupo.getInstituicaoDoacao().getNome());
		Assert.assertEquals(grupoDTO.getDoacaoRecursoDto().size(), 
				grupo.getDoacaoRecurso().size());
		Assert.assertEquals(grupoDTO.getNomeCurso(), grupo.getNomeCurso());
		Assert.assertEquals(grupoDTO.getDataInicioPreInscricao(), grupo.getDataInicioPreInscricao());
		Assert.assertEquals(grupoDTO.getDataTerminoPreInscricao(), grupo.getDataTerminoPreInscricao());
		Assert.assertEquals(grupoDTO.getValorTotalAlmoco(), grupo.getValorTotalAlmoco());
		Assert.assertEquals(grupoDTO.getInvestimento(), grupo.getInvestimento());
		Assert.assertEquals(grupoDTO.isPublicado(), grupo.isPublicado());
		Assert.assertEquals(grupoDTO.getDescricoesEventoDto().size(),
				grupo.getDescricoesEvento().size());
		Assert.assertEquals(grupoDTO.getTodosUsuariosDto().size(),
				grupo.obterTodosUsuarios().size());
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_SEM_BD })
	public void fabrica_grupo_converte_objeto_de_dto_para_dominio() {
		GrupoDTO grupoDto = ContextoGrupo.construirGrupoDTO();

		Grupo grupoObtido = new FabricaGrupo().converterParaDominio(
				grupoDto);

		Assert.assertEquals(grupoObtido.getId(), grupoDto.getId());
		Assert.assertEquals(grupoObtido.isAtivo(), grupoDto.isAtivo());
		Assert.assertEquals(grupoObtido.getSetor().toString(), grupoDto
				.getSetorDto().toString());
		Assert.assertEquals(grupoObtido.getDescricaoTipoAtendimento().getNome(),
				grupoDto.getDescricaoTipoAtendimentoDTO().getNome());
		Assert.assertEquals(grupoObtido.getDataInicio(),
				grupoDto.getDataInicio());
		Assert.assertEquals(grupoObtido.getDataTermino(),
				grupoDto.getDataTermino());
		Assert.assertEquals(grupoObtido.getNomeGrupo().getNome(), grupoDto
				.getNomeGrupoDto().toString());
		Assert.assertEquals(grupoObtido.getTurma(), grupoDto.getTurma());
		Assert.assertEquals(grupoObtido.getNivel(), grupoDto.getNivel());
		Assert.assertEquals(grupoObtido.getModulosPeriodos().size(), grupoDto
				.getModulosPeriodosDto().size());
		Assert.assertEquals(grupoObtido.getDescricao(), grupoDto.getDescricao());
		Assert.assertEquals(grupoObtido.getTipificacoesServico().size(),
				grupoDto.getTipificacoesServicoDto().size());
		Assert.assertEquals(grupoObtido.getInstituicaoDoacao().getNome(), 
				grupoDto.getInstituicaoDoacaoDto().getNome());
		Assert.assertEquals(grupoObtido.getNomeCurso(), grupoDto.getNomeCurso());
		Assert.assertEquals(grupoObtido.getDataInicioPreInscricao(), grupoDto.getDataInicioPreInscricao());
		Assert.assertEquals(grupoObtido.getDataTerminoPreInscricao(), grupoDto.getDataTerminoPreInscricao());
		Assert.assertEquals(grupoObtido.getValorTotalAlmoco(), grupoDto.getValorTotalAlmoco());
		Assert.assertEquals(grupoObtido.getInvestimento(), grupoDto.getInvestimento());
		Assert.assertEquals(grupoObtido.isPublicado(), grupoDto.isPublicado());
		Assert.assertEquals(grupoObtido.getDescricoesEvento().size(),
				grupoDto.getDescricoesEventoDto().size());
		Assert.assertEquals(grupoObtido.getDoacaoRecurso().size(), 
				grupoDto.getDoacaoRecursoDto().size());
		
	}
}
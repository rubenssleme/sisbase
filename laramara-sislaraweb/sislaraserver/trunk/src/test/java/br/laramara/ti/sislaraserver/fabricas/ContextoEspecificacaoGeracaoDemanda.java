package br.laramara.ti.sislaraserver.fabricas;

import java.util.Arrays;

import br.laramara.ti.sislaracommons.dtos.doacao.EspecificacaoGeracaoDemandaDTO;

public class ContextoEspecificacaoGeracaoDemanda {

	public static EspecificacaoGeracaoDemandaDTO fabricarDemandaComTodosOsDados() {
	
		EspecificacaoGeracaoDemandaDTO especificacaoGeracaoDemandaDto = new EspecificacaoGeracaoDemandaDTO();
		especificacaoGeracaoDemandaDto.setPreCadastrosDto(ContextoPreCadastro.construirPreCadastroDTO());
		especificacaoGeracaoDemandaDto.setProjetoDto(ContextoProjeto
				.construirProjetoDTO());
		especificacaoGeracaoDemandaDto.setRecursosEDescrucaiRecursoDto(Arrays.asList(ContextoRecursoDescricaoRecurso.criarDto()));
		return especificacaoGeracaoDemandaDto;
	}
	
	public static EspecificacaoGeracaoDemandaDTO fabricarDemandaComTodosOsDadosAlternativo() {
	
		EspecificacaoGeracaoDemandaDTO especificacaoGeracaoDemandaDto = new EspecificacaoGeracaoDemandaDTO();
		especificacaoGeracaoDemandaDto.setPreCadastrosDto(ContextoPreCadastro.construirPreCadastroDTO());
		especificacaoGeracaoDemandaDto.setProjetoDto(ContextoProjeto
				.construirProjetoDTO());
		especificacaoGeracaoDemandaDto
				.setRecursosEDescrucaiRecursoDto(Arrays.asList(ContextoRecursoDescricaoRecurso.criarDto()));
		especificacaoGeracaoDemandaDto.setGrupoDto(ContextoGrupo.construirGrupoDTOComIdentificacao());
		especificacaoGeracaoDemandaDto.setCartelaDeSelos(false);
		especificacaoGeracaoDemandaDto.setDataExpectativa("31/12/2000");
		return especificacaoGeracaoDemandaDto;
	}
}

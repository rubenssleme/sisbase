package br.laramara.ti.sislaraserver.fabricas;

import java.util.Arrays;

import br.laramara.ti.sislaracommons.dtos.doacao.EspecificacaoGeracaoDemandaDTO;

public class ContextoEspecificacaoGeracaoDemanda {

	public static EspecificacaoGeracaoDemandaDTO fabricarDemandaComTodosOsDados() {
	
		EspecificacaoGeracaoDemandaDTO especificacaoGeracaoDemandaDto = new EspecificacaoGeracaoDemandaDTO();
		especificacaoGeracaoDemandaDto.setPreCadastrosDto(ContextoPreCadastro.construirPreCadastroDTO());
		especificacaoGeracaoDemandaDto.setProjetoDto(ContextoProjeto
				.construirProjetoDTO());
		especificacaoGeracaoDemandaDto.setRecursosDto(Arrays.asList(ContextoRecurso
				.construirRecursoDTO()));
		return especificacaoGeracaoDemandaDto;
	}
	
	public static EspecificacaoGeracaoDemandaDTO fabricarDemandaComTodosOsDadosAlternativo() {
	
		EspecificacaoGeracaoDemandaDTO especificacaoGeracaoDemandaDto = new EspecificacaoGeracaoDemandaDTO();
		especificacaoGeracaoDemandaDto.setPreCadastrosDto(ContextoPreCadastro.construirPreCadastroDTO());
		especificacaoGeracaoDemandaDto.setProjetoDto(ContextoProjeto
				.construirProjetoDTO());
		especificacaoGeracaoDemandaDto.setRecursosDto(Arrays.asList(ContextoRecurso
				.construirRecursoDTO()));
		especificacaoGeracaoDemandaDto.setGrupoDto(ContextoGrupo.construirGrupoDTOComIdentificacao());
		especificacaoGeracaoDemandaDto.setCartelaDeSelos(false);
		return especificacaoGeracaoDemandaDto;
	}
}

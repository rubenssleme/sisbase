package br.laramara.ti.sislaraserver.fabricas;

import br.laramara.ti.sislaracommons.dtos.espera.EspecificacaoPesquisaEsperaDTO;
import br.laramara.ti.sislaracommons.dtos.espera.StatusEsperaDTO;
import br.laramara.ti.sislaracommons.dtos.espera.TipoEsperaDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.DescricaoTipoAtendimentoDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.ModuloDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.NomeGrupoDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.SetorDTO;
import br.laramara.ti.sislaraserver.dominio.espera.EspecificacaoPesquisaEspera;
import br.laramara.ti.sislaraserver.dominio.espera.StatusEspera;
import br.laramara.ti.sislaraserver.dominio.espera.TipoEspera;
import br.laramara.ti.sislaraserver.dominio.grupo.NomeGrupo;
import br.laramara.ti.sislaraserver.dominio.usuario.Setor;

public class ContextoEspecificacaoPesquisaEspera {
	public static EspecificacaoPesquisaEspera fabricarDominioComTodosOsDados() {
		EspecificacaoPesquisaEspera especificacaoPesquisaEspera = new EspecificacaoPesquisaEspera();
		especificacaoPesquisaEspera
				.setDescricaoTipoAtendimento(ContextoDescricaoTipoAtendimento
						.fabricarComTodosOsDados());
		especificacaoPesquisaEspera.setModulo(ContextoModulo
				.fabricarComTodosOsDados());
		especificacaoPesquisaEspera.setDataInicio("01/01/2012");
		especificacaoPesquisaEspera.setDataTermino("31/12/2012");
		especificacaoPesquisaEspera.setSetor(Setor.CTO);
		especificacaoPesquisaEspera.setStatusEspera(StatusEspera.AGUARDANDO);
		especificacaoPesquisaEspera.setTipoEspera(TipoEspera.RET);
		especificacaoPesquisaEspera.setNomeGrupo(new NomeGrupo(new Long(1000), "G02"));
		return especificacaoPesquisaEspera;
	}
	
	public static EspecificacaoPesquisaEsperaDTO fabricarDtoPesquisaEsperaDescricaoServicoSocialModuloExcessoDeFaltas(Long prontuario) {
		EspecificacaoPesquisaEsperaDTO especificacaoPesquisaEsperaDto = new EspecificacaoPesquisaEsperaDTO();
		especificacaoPesquisaEsperaDto.setProntuario(prontuario.toString());
		especificacaoPesquisaEsperaDto.setStatusEsperadoDto(new StatusEsperaDTO("AGUARDANDO"));
		especificacaoPesquisaEsperaDto.setInteresse(false);
		especificacaoPesquisaEsperaDto.setLmLigou(false);
		especificacaoPesquisaEsperaDto.setPendencias(false);
		especificacaoPesquisaEsperaDto
				.setDescricaoTipoAtendimentoDto(ContextoDescricaoTipoAtendimento.construirDescricaoServicoSocialDTO());
		especificacaoPesquisaEsperaDto.setModuloDto(ContextoModulo.construirModuloExcessoDeFaltasDTO());
		return especificacaoPesquisaEsperaDto;
	}
	

	public static EspecificacaoPesquisaEsperaDTO fabricarDtoComTodosOsDados() {
		Long id = new Long(12222);
		String data = "31/12/2012";
		DescricaoTipoAtendimentoDTO descricaoTipoAtendimentoDto = ContextoDescricaoTipoAtendimento.construirDescricaoTipoAtendimentoDTO();
		ModuloDTO moduloDto = new ModuloDTO(id, "Informatica");
		NomeGrupoDTO nomeGrupoDto = new NomeGrupoDTO(id, "G02");
		SetorDTO setorDto = new SetorDTO("CTO");
		StatusEsperaDTO statusEsperaDto = new StatusEsperaDTO("AGENDADO");
		TipoEsperaDTO tipoEsperaDto = new TipoEsperaDTO("RET");

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
		especificacaoDto.setProntuario("1234");
		especificacaoDto.setRg("3443A");
		especificacaoDto.setInteresse(true);
		especificacaoDto.setLmLigou(true);
		especificacaoDto.setPendencias(false);

		return especificacaoDto;
	}
}

package br.laramara.ti.sislaraserver.fabricas;

import br.laramara.ti.sislaracommons.dtos.contribuicao.ContribuinteDTO;
import br.laramara.ti.sislaracommons.dtos.contribuicao.MotivoDesativacaoDTO;
import br.laramara.ti.sislaracommons.dtos.contribuicao.StatusContribuinteDTO;
import br.laramara.ti.sislaraserver.dominio.contribuicao.Contribuinte;
import br.laramara.ti.sislaraserver.dominio.contribuicao.MotivoDesativacao;
import br.laramara.ti.sislaraserver.dominio.contribuicao.StatusContribuinte;

public class ContextoContribuinte {
	public static Contribuinte fabricarContribuinteComTodosOsDados() {
		Contribuinte contribuinte = new Contribuinte();
		contribuinte.setId(new Long(12222));
		contribuinte.setEndereco(ContextoEndereco
				.fabricarEnderecoComTodosOsDados());
		contribuinte.setContato(ContextoContato
				.fabricarContatoComTodosOsDados());
		contribuinte.adicionarStatus(StatusContribuinte.ATIVADO);
		contribuinte.setValorContribuicao("75,50");
		contribuinte.setNomeEmpresa("Paulo");
		contribuinte.setMotivoDesativacao(new MotivoDesativacao(new Long(1), "Crise"));
		return contribuinte;
	}

	public static ContribuinteDTO fabricarContribuinteDtoComTodosOsDados() {
		ContribuinteDTO contribuinteDTO = new ContribuinteDTO();
		contribuinteDTO.setId(new Long(12222));
		contribuinteDTO.setEnderecoDto(ContextoEndereco.construirEnderecoDTO());
		contribuinteDTO.setContatoDto(ContextoContato.construirContatoDTO());
		contribuinteDTO.setStatusContribuinteDto(new StatusContribuinteDTO("DESATIVADO"));
		contribuinteDTO.setContribuicao("75,50");
		contribuinteDTO.setMotivoDesativacaoDTO(new MotivoDesativacaoDTO(new Long(1), "Crise"));
		contribuinteDTO.setNomeEmpresa("Paulo Augusto");
		return contribuinteDTO;
	}
}

package br.laramara.ti.sislaraserver.fabricas;

import br.laramara.ti.sislaracommons.dtos.atendimento.AcaoCondutaDTO;
import br.laramara.ti.sislaracommons.dtos.atendimento.TipoAcaoCondutaDTO;
import br.laramara.ti.sislaraserver.dominio.atendimento.AcaoConduta;
import br.laramara.ti.sislaraserver.dominio.atendimento.TipoAcaoConduta;

public class ContextoAcaoConduta {

	public static AcaoCondutaDTO fabricarAcoesCondutaDto() {
		AcaoCondutaDTO acaoCondutaA = new AcaoCondutaDTO();
		acaoCondutaA.setTipoAcaoCondutaDto(new TipoAcaoCondutaDTO(TipoAcaoConduta.INTEGRAR.toString()));
		acaoCondutaA.setGrupoDto(ContextoGrupo.construirGrupoDTOComIdentificacao());
		acaoCondutaA.setCancelada(false);
		return acaoCondutaA;
	}

	public static AcaoConduta fabricarAcaoCondutaComTodosOsDados() {
		AcaoConduta acaoConduta = new AcaoConduta();
		acaoConduta.setId(new Long(1000));
		acaoConduta.setUsuario(ContextoUsuario.fabricaUsuarioComTodosOsDadosEProntuario());
		acaoConduta.setTipoAcaoConduta(TipoAcaoConduta.INTEGRAR);
		acaoConduta.setGrupo(ContextoGrupo.fabricarGrupoComTodosOsDados());
		acaoConduta.setCancelada(false);
		return acaoConduta;
	}
}

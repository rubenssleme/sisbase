package br.laramara.ti.sislaraserver.fabricas;

import br.laramara.ti.sislaracommons.dtos.grupo.EspecificacaoGeracaoAtendimentosDTO;
import br.laramara.ti.sislaracommons.dtos.pendencia.PendenciaDTO;
import br.laramara.ti.sislaracommons.utilitarios.DataHoraUtils;
import br.laramara.ti.sislaraserver.dominio.pendencia.Pendencia;

public class FabricaPendencia extends FabricaBase<PendenciaDTO, Pendencia> {
	public final PendenciaDTO converterParaDTO(Pendencia pendencia) {
		return pendencia != null ? new PendenciaDTO(pendencia.getId(), pendencia.obterDescricaoPendencia(),
				pendencia.eAtendimentoIndividualUsuarioOuPreCadastro() ? new FabricaAtendimentoIndividual()
						.converterParaDTO(pendencia.obterAtendimentoIndividualReferenciaParaGeracao()) : null,
				pendencia.eAtendimentoDeGrupo() ? new EspecificacaoGeracaoAtendimentosDTO(pendencia.getGrupo().getId(),
						pendencia.getModuloPeriodo().getId(), DataHoraUtils.formatarData(pendencia.getData()),
						pendencia.getHorario().getHoraInicio(), pendencia.getHorario().getHoraTermino()) : null)
				: null;
	}

	public final Pendencia converterParaDominio(PendenciaDTO participacao) {
		return null;
	}
}

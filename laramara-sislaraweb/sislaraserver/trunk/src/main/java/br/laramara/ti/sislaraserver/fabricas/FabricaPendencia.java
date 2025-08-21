package br.laramara.ti.sislaraserver.fabricas;

import br.laramara.ti.sislaracommons.dtos.pendencia.PendenciaDTO;
import br.laramara.ti.sislaraserver.dominio.pendencia.AtendimentoIndividualReferenciavel;
import br.laramara.ti.sislaraserver.dominio.pendencia.Pendencia;

public class FabricaPendencia extends FabricaBase<PendenciaDTO, Pendencia> {
	public final PendenciaDTO converterParaDTO(Pendencia pendencia) {
		return pendencia != null ? new PendenciaDTO(pendencia.getId(), pendencia.obterDescricaoPendencia(),
				pendencia.eAtendimentoIndividualUsuarioOuPreCadastro() ? new FabricaAtendimentoIndividual()
						.converterParaDTO(((AtendimentoIndividualReferenciavel)pendencia).obterAtendimentoIndividualReferenciaParaGeracao()) : null,
				pendencia.eAtendimentoDeGrupo() ? true : false) : null;
	}

	public final Pendencia converterParaDominio(PendenciaDTO participacao) {
		return null;
	}
}

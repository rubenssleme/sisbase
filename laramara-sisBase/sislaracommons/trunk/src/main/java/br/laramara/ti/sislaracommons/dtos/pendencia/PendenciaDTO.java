package br.laramara.ti.sislaracommons.dtos.pendencia;

import br.laramara.ti.sislaracommons.Identificavel;
import br.laramara.ti.sislaracommons.dtos.ModeloDTO;
import br.laramara.ti.sislaracommons.dtos.atendimento.AtendimentoIndividualDTO;

public class PendenciaDTO extends ModeloDTO implements Identificavel {

	private static final long serialVersionUID = -1807294481639273016L;

	private Long id;

	private String descricaoPendencia;
	
	private AtendimentoIndividualDTO atendimentoIndividualDTOReferenciaParaGeracao;
	
	private boolean pendenciaAtendimentoGrupo;
	
	public PendenciaDTO(Long id, String descricaoPendencia,
			AtendimentoIndividualDTO atendimentoIndividualDTOReferenciaParaGeracao,
			boolean pendenciaAtendimentoGrupo) {
		this.id = id;
		this.descricaoPendencia = descricaoPendencia;
		this.atendimentoIndividualDTOReferenciaParaGeracao = atendimentoIndividualDTOReferenciaParaGeracao;
		this.pendenciaAtendimentoGrupo = pendenciaAtendimentoGrupo;
	}
	
	public AtendimentoIndividualDTO getAtendimentoIndividualDTOReferenciaParaGeracao() {
		return atendimentoIndividualDTOReferenciaParaGeracao;
	}

	public boolean ePendenciaAtendimentoIndividual(){
		return atendimentoIndividualDTOReferenciaParaGeracao != null;
	}
	
	@Override
	public Long getId() {
		return id;
	}

	public boolean ePendenciaAtendimentoGrupo() {
		return pendenciaAtendimentoGrupo;
	}

	@Override
	public String toString() {
		return descricaoPendencia;
	}
}

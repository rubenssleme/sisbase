package br.laramara.ti.sislaracommons.dtos.pendencia;

import br.laramara.ti.sislaracommons.Identificavel;
import br.laramara.ti.sislaracommons.dtos.ModeloDTO;
import br.laramara.ti.sislaracommons.dtos.atendimento.AtendimentoIndividualDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.EspecificacaoGeracaoAtendimentosDTO;

public class PendenciaDTO extends ModeloDTO implements Identificavel {

	private static final long serialVersionUID = -1807294481639273016L;

	private Long id;

	private String descricaoPendencia;
	
	private AtendimentoIndividualDTO atendimentoIndividualDTOReferenciaParaGeracao;
	
	private EspecificacaoGeracaoAtendimentosDTO especificacaoGeracaoAtendimentosDeGrupoDTO;
	
	public PendenciaDTO(Long id, String descricaoPendencia, AtendimentoIndividualDTO atendimentoIndividualDTOReferenciaParaGeracao, EspecificacaoGeracaoAtendimentosDTO especificacaoGeracaoAtendimentosDTO){
		this.id = id;
		this.descricaoPendencia = descricaoPendencia;
		this.atendimentoIndividualDTOReferenciaParaGeracao = atendimentoIndividualDTOReferenciaParaGeracao;
		this.especificacaoGeracaoAtendimentosDeGrupoDTO = especificacaoGeracaoAtendimentosDTO;
	}
	
	public AtendimentoIndividualDTO getAtendimentoIndividualDTOReferenciaParaGeracao() {
		return atendimentoIndividualDTOReferenciaParaGeracao;
	}

	public void setAtendimentoIndividualDTOReferenciaParaGeracao(
			AtendimentoIndividualDTO atendimentoIndividualDTOReferenciaParaGeracao) {
		this.atendimentoIndividualDTOReferenciaParaGeracao = atendimentoIndividualDTOReferenciaParaGeracao;
	}

	public boolean ePendenciaAtendimentoIndividual(){
		return atendimentoIndividualDTOReferenciaParaGeracao != null;
	}
	
	public EspecificacaoGeracaoAtendimentosDTO getEspecificacaoGeracaoAtendimentosDTO() {
		return especificacaoGeracaoAtendimentosDeGrupoDTO;
	}

	public void setEspecificacaoGeracaoAtendimentosDTO(Long idGrupo, Long idModuloPeriodoDto, String data,
			String horaInicial, String horaFinal) {
		this.especificacaoGeracaoAtendimentosDeGrupoDTO = new EspecificacaoGeracaoAtendimentosDTO(idGrupo, idModuloPeriodoDto,
				data, horaInicial, horaFinal);
	}

	public boolean ePendenciaAtendimentoGrupo(){
		return especificacaoGeracaoAtendimentosDeGrupoDTO != null;
	}
	
	@Override
	public Long getId() {
		return id;
	}

	@Override
	public String toString() {
		return descricaoPendencia;
	}
}

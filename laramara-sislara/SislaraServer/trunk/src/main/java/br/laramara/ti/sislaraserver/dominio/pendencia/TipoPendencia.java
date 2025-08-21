package br.laramara.ti.sislaraserver.dominio.pendencia;

import br.laramara.ti.sislaracommons.utilitarios.DataHoraUtils;
import br.laramara.ti.sislaracommons.utilitarios.MaquinaTempo;

public enum TipoPendencia {
	ENVIO_LISTA_ESPERA_EXCESSO_DE_FALTAS("(IMPORTANTE)Prontuário " + TipoPendencia.DESCRICAO
			+ " foi enviado para a lista de espera do SS por excesso de faltas."), 
	ATENDIMENTO_INDIVIDUAL_USUARIO("Atendimento individual do Prontuário " + TipoPendencia.DESCRICAO), 
	ATENDIMENTO_GRUPO("Atendimento do grupo no " + TipoPendencia.DESCRICAO), 
	ATENDIMENTO_INDIVIDUAL_PRE_CADASTRO("Atendimento individual do pré-cadastro " + TipoPendencia.DESCRICAO),
	TRANSFERENCIA_DE_REUNIAO_DE_INTEGRACAO("Reunião de integração do Prontuáiro " + TipoPendencia.DESCRICAO + " será transferida para outro grupo disponível."), 
	INCLUSAO_DE_REUNIAO_DE_INTEGRACAO("Inclusão automática do Prontuário " + TipoPendencia.DESCRICAO + " em reunião de integração será efetuada.");

	private final String descricao;
	private static final String DESCRICAO = ":descricao";

	private TipoPendencia(String descricao) {
		this.descricao = descricao;
	}
	
	public boolean posterior(Pendencia pendencia) {
		if (this.equals(ATENDIMENTO_INDIVIDUAL_USUARIO) || this.equals(ATENDIMENTO_INDIVIDUAL_PRE_CADASTRO)){
			return pendencia.getAgendamento().getDataCalendario().before(MaquinaTempo.obterInstancia().obterCalendarioAtual());
		}else if (equals(ATENDIMENTO_GRUPO)){
			return pendencia.getData().before(MaquinaTempo.obterInstancia().obterCalendarioAtual());
		}else{
			return false;
		}
	}

	public String obterDescricao(Pendencia pendencia) {
		if (this.equals(TipoPendencia.ENVIO_LISTA_ESPERA_EXCESSO_DE_FALTAS)
				|| this.equals(TipoPendencia.TRANSFERENCIA_DE_REUNIAO_DE_INTEGRACAO)
				|| this.equals(TipoPendencia.INCLUSAO_DE_REUNIAO_DE_INTEGRACAO)) {
			return this.descricao.replace(DESCRICAO, pendencia.getProntuario().toString());
		} else if (this.equals(TipoPendencia.ATENDIMENTO_INDIVIDUAL_USUARIO)) {
			return this.descricao.replace(DESCRICAO,
					pendencia.getProntuario().toString() + obterTextoDescricao(pendencia));
		} else if (this.equals(TipoPendencia.ATENDIMENTO_GRUPO)) {
			return this.descricao.replace(DESCRICAO,
					pendencia.getGrupo().obterNomeGrupoETurma() + ", Data: "
							+ DataHoraUtils.formatarData(pendencia.getData()) + ", Módulo: "
							+ pendencia.getModuloPeriodo().getModulo().getNome());
		} else if (this.equals(TipoPendencia.ATENDIMENTO_INDIVIDUAL_PRE_CADASTRO)){
			return this.descricao.replace(DESCRICAO,
					pendencia.getAgendamento().getPreCadastro().getInformacaoEssencial().getNome()
							+ obterTextoDescricao(pendencia));
		} else {
			return "";
		}
	}
	
	private String obterTextoDescricao(Pendencia pendencia) {
		return ", Data: " + pendencia.getAgendamento().getData() + ", Tipo: "
				+ pendencia.getAgendamento().getDescricaoTipoAtendimento().getTipoAtendimento().getNome()
				+ ", Descrição: " + pendencia.getAgendamento().getDescricaoTipoAtendimento().getNome() + ", Módulo: "
				+ pendencia.getAgendamento().getModulo().getNome();
	}
}

package br.laramara.ti.sislaraserver.dominio.pendencia;

import br.laramara.ti.sislaracommons.utilitarios.DataHoraUtils;
import br.laramara.ti.sislaracommons.utilitarios.MaquinaTempo;

public enum TipoPendencia {
	ENVIO_LISTA_ESPERA_EXCESSO_DE_FALTAS("(IMPORTANTE)Prontu�rio " + TipoPendencia.DESCRICAO
			+ " foi enviado para a lista de espera do SS por excesso de faltas."), 
	ATENDIMENTO_INDIVIDUAL_USUARIO("Atendimento individual do Prontu�rio " + TipoPendencia.DESCRICAO), 
	ATENDIMENTO_GRUPO("Atendimento do grupo no " + TipoPendencia.DESCRICAO), 
	ATENDIMENTO_INDIVIDUAL_PRE_CADASTRO("Atendimento individual do pr�-cadastro " + TipoPendencia.DESCRICAO),
	TRANSFERENCIA_DE_REUNIAO_DE_INTEGRACAO("Reuni�o de integra��o do Prontu�iro " + TipoPendencia.DESCRICAO + " ser� transferida para outro grupo dispon�vel."), 
	INCLUSAO_DE_REUNIAO_DE_INTEGRACAO("Inclus�o autom�tica do Prontu�rio " + TipoPendencia.DESCRICAO + " em reuni�o de integra��o ser� efetuada.");

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
							+ DataHoraUtils.formatarData(pendencia.getData()) + ", M�dulo: "
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
				+ ", Descri��o: " + pendencia.getAgendamento().getDescricaoTipoAtendimento().getNome() + ", M�dulo: "
				+ pendencia.getAgendamento().getModulo().getNome();
	}
}

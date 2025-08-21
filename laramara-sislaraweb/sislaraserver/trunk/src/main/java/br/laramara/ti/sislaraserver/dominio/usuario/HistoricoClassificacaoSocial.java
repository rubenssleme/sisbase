package br.laramara.ti.sislaraserver.dominio.usuario;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import br.laramara.ti.sislaraserver.dominio.Historico;

@Entity
@Table(name = "historico_classificacao_social")
public class HistoricoClassificacaoSocial extends Historico {

	@Enumerated(EnumType.STRING)
	@Column(name = "classificacao_social", length = TamanhoMaximoUsuario.CLASSIFICACAO_SOCIAL)
	private ClassificacaoSocial classificacaoSocial;

	public HistoricoClassificacaoSocial() {
	}

	public HistoricoClassificacaoSocial(ClassificacaoSocial classificacaoSocial) {
		super();
		this.classificacaoSocial = classificacaoSocial;
	}

	public ClassificacaoSocial getClassificacaoSocial() {
		return classificacaoSocial;
	}
	
	public boolean possuiClassificacaoSocialNula(){
		return classificacaoSocial == null;
	}
}

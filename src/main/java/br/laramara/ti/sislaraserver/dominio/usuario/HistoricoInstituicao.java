package br.laramara.ti.sislaraserver.dominio.usuario;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import br.laramara.ti.sislaraserver.dominio.Historico;
import br.laramara.ti.sislaraserver.dominio.instituicao.Instituicao;

@Entity
@Table(name = "historico_instituicao")
public class HistoricoInstituicao extends Historico {

	@OneToOne
	@JoinColumn(name = "id_instituicao")
	private Instituicao instituicao;

	public HistoricoInstituicao() {
	}

	public HistoricoInstituicao(Instituicao instituicao) {
		super();
		this.instituicao = instituicao;
	}

	public Instituicao getInstituicao() {
		return instituicao;
	}

	public boolean possuiInstituicao() {
		return instituicao != null;
	}
}

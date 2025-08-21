package br.laramara.ti.sislaraserver.dominio.usuario;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import br.laramara.ti.sislaraserver.dominio.Historico;
import br.laramara.ti.sislaraserver.dominio.TamanhoMaximoGenerico;

@Entity
@Table(name = "historico_setor")
public class HistoricoSetor extends Historico{
	
	@Enumerated(EnumType.STRING)
	@Column(name = "id_setor", length = TamanhoMaximoGenerico.SETOR, nullable = false)
	private Setor setor;

	public HistoricoSetor(){
	}
	
	public HistoricoSetor(Setor setor) {
		super();
		this.setor = setor;
	}

	public Setor getSetor() {
		return setor;
	}
}

package br.laramara.ti.sislaraserver.dominio.agenda;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.laramara.ti.sislaraserver.dominio.TamanhoMaximoGenerico;

@Entity
@Table(name = "motivo_cancelamento")
public class MotivoCancelamento {
	
	public static final Long ID_MOTIVO_CANCELAMENTO_LIGOU_E_PEDIU_PARA_CANCELAR = new Long(3);

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "descricao", length = TamanhoMaximoGenerico.DESCRICAO, nullable = false)
	private String descricao;
	
	public MotivoCancelamento() {
	}

	public MotivoCancelamento(Long id, String descricao) {
		super();
		this.id = id;
		this.descricao = descricao;
	}

	public Long getId() {
		return id;
	}

	public String getDescricao() {
		return descricao;
	}

	@Override
	public String toString() {
		return "MotivoCancelamento [id=" + id + ", descricao=" + descricao + "]";
	}
}

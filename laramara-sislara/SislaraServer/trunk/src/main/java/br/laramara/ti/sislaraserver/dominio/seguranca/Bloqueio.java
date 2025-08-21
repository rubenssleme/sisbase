package br.laramara.ti.sislaraserver.dominio.seguranca;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.laramara.ti.sislaraserver.dominio.TamanhoMaximoGenerico;
import br.laramara.ti.sislaraserver.dominio.grupo.DescricaoTipoAtendimento;
import br.laramara.ti.sislaraserver.dominio.grupo.Modulo;

@Entity
@Table(name = "bloqueio")
public class Bloqueio {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "id_descricao_tipo_atendimento", nullable = false)
	private DescricaoTipoAtendimento descricaoTipoAtendimento;
	
	@ManyToOne
	@JoinColumn(name = "id_modulo", nullable = false)
	private Modulo modulo;

	@Enumerated(EnumType.STRING)
	@Column(name = "area", length = TamanhoMaximoGenerico.AREA)
	private Area area;
	
	public Bloqueio(){
	}
}

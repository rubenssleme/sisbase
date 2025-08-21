package br.laramara.ti.sislaraserver.dominio.usuario;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.laramara.ti.sislaraserver.dominio.TamanhoMaximoGenerico;

@Entity
@Table(name = "deficiencia")
public class Deficiencia{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "descricao", length = TamanhoMaximoGenerico.DESCRICAO, nullable = false)
	private String descricao;
	
	@Column(name = "etiologia_obrigatorio")
	private boolean etiologiaObrigatorio;

	public Deficiencia(){
	}
	
	public Deficiencia(Long Id, String descricao, boolean etiologiaObrigatorio){
		this.id = Id;
		this.descricao = descricao;
		this.etiologiaObrigatorio = etiologiaObrigatorio;
	}
	
	public Long getId() {
		return id;
	}

	public String getDescricao() {
		return descricao;
	}
	
	public boolean isEtiologiaObrigatorio() {
		return etiologiaObrigatorio;
	}

	@Override
	public String toString() {
		return "Deficiencia [id=" + id + ", descricao=" + descricao
				+ ", etiologiaObrigatorio = " + etiologiaObrigatorio + "]";
	}
}

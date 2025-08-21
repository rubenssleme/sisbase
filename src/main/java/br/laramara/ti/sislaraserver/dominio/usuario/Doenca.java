package br.laramara.ti.sislaraserver.dominio.usuario;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.laramara.ti.sislaraserver.dominio.TamanhoMaximoGenerico;

@Entity
@Table(name = "doenca")
public class Doenca {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "descricao", length = TamanhoMaximoGenerico.DESCRICAO, nullable = false)
	private String descricao;

	public Doenca(){
		
	}
	
	public Doenca(Long Id, String Descricao){
		this.id = Id;
		this.descricao = Descricao;
	}
	
	public Long getId() {
		return id;
	}

	public String getDescricao() {
		return descricao;
	}
	
	@Override
	public String toString() {
		return "Doenca [id=" + id + ", descricao=" + descricao + "]";
	}
}

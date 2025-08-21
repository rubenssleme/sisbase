package br.laramara.sistelemarketingserver.dominio.contato;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.laramara.sistelemarketingserver.dominio.seguranca.TamanhoMaximoGenerico;

@Entity
@Table(name = "telefone")
public class Telefone implements Serializable{

	private static final long serialVersionUID = 1301314696031829745L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "ddd", length = TamanhoMaximoGenerico.DDD)
	private String ddd;
	
	@Column(name = "telefone", length = TamanhoMaximoGenerico.TELEFONE)
	private String telefone;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDdd() {
		return ddd;
	}

	public void setDdd(String ddd) {
		this.ddd = ddd;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String obterTelefoneCompleto() {
		return "0" + getDdd() + getTelefone();
	}
	
	@Override
	public String toString() {
		return "Telefone [id=" + id + ", ddd=" + ddd + ", telefone=" + telefone + "]";
	}
}

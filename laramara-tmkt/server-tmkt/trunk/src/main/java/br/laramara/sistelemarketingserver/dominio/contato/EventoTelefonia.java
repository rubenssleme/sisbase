package br.laramara.sistelemarketingserver.dominio.contato;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

import br.laramara.sistelemarketingserver.dominio.seguranca.TamanhoMaximoGenerico;
import br.laramara.sistelemarketingserver.dominio.telefonia.Ramal;
import br.laramara.sistelemarketingserver.dominio.telefonia.TipoEventoTelefonia;

@Entity
@Table(name = "evento_telefonia")
public class EventoTelefonia {

	@Id
	private Long id;

	@Enumerated(EnumType.STRING)
	@Column(name = "tipo_evento", length = TamanhoMaximoGenerico.TIPO_EVENTO, nullable = false)
	private TipoEventoTelefonia tipoEvento;

	@Enumerated(EnumType.STRING)
	@Column(name = "ramal", length = TamanhoMaximoGenerico.RAMAL, nullable = false)
	private Ramal ramal;

	@Column(name = "ddd", length = TamanhoMaximoGenerico.DDD, nullable = false)
	private String ddd;

	@Column(name = "telefone", length = TamanhoMaximoGenerico.TELEFONE, nullable = false)
	private String telefone;

	@Column(name = "uniqueid", length = TamanhoMaximoGenerico.UNIQUEID, nullable = false)
	private String uniqueId;

	@Column(name = "duracao_ligacao", nullable = false)
	private Integer duracaoLigacao;

	@Column(name = "data_ocorrencia", nullable = false)
	protected LocalDateTime dataOcorrencia;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TipoEventoTelefonia getTipoEvento() {
		return tipoEvento;
	}

	public void setTipoEvento(TipoEventoTelefonia tipoEvento) {
		this.tipoEvento = tipoEvento;
	}

	public Ramal getRamal() {
		return ramal;
	}

	public void setRamal(Ramal ramal) {
		this.ramal = ramal;
	}

	public String getDdd() {
		return ddd;
	}

	public void setDdd(String ddd) {
		this.ddd = ddd.substring(1, ddd.length());;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getUniqueId() {
		return uniqueId;
	}

	public void setUniqueId(String uniqueId) {
		this.uniqueId = uniqueId;
	}

	public Integer getDuracaoLigacao() {
		return duracaoLigacao;
	}

	public void setDuracaoLigacao(Integer duracaoLigacao) {
		this.duracaoLigacao = duracaoLigacao;
	}

	public LocalDateTime getDataOcorrencia() {
		return dataOcorrencia;
	}

	public void setDataOcorrencia(LocalDateTime dataOcorrencia) {
		this.dataOcorrencia = dataOcorrencia;
	}

	@Override
	public String toString() {
		return "EventoTelefonia [id=" + id + ", tipoEvento=" + tipoEvento + ", ramal=" + ramal + ", ddd=" + ddd
				+ ", telefone=" + telefone + ", uniqueId=" + uniqueId + ", duracaoLigacao=" + duracaoLigacao
				+ ", dataOcorrencia=" + dataOcorrencia + "]";
	}
}

package br.laramara.ti.sislaraserver.dominio.usuario;

import java.util.Comparator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import br.laramara.ti.sislaracommons.utilitarios.DataHoraUtils;
import br.laramara.ti.sislaraserver.dominio.Historico;
import br.laramara.ti.sislaraserver.dominio.TamanhoMaximoGenerico;
import br.laramara.ti.sislaraserver.dominio.grupo.StatusRelacaoComModulo;

@Entity
@Table(name = "historico_status_usuario")
public class HistoricoStatusUsuario extends Historico {

	@Enumerated(EnumType.STRING)
	@Column(name = "status", length = TamanhoMaximoGenerico.STATUS, nullable = false)
	private StatusRelacaoComModulo status;
	
	@Column(name = "obs", length = TamanhoMaximoGenerico.OBS)
	private String obs;

	public HistoricoStatusUsuario() {
	}

	public HistoricoStatusUsuario(StatusRelacaoComModulo status, String obs) {
		super();
		this.status = status;
		this.obs = obs;
	}

	public StatusRelacaoComModulo getStatus() {
		return status;
	}

	public static final Comparator<HistoricoStatusUsuario> obterComparador() {
		return new Comparator<HistoricoStatusUsuario>() {
			public int compare(HistoricoStatusUsuario o1,
					HistoricoStatusUsuario o2) {
				return (o1.getDataInicialVigencia().compareTo(o2
						.getDataInicialVigencia()));
			}
		};
	}
	
	@Override
	public String toString() {
		return "HistoricoStatusUsuario [dataInicialVigencia="
				+ DataHoraUtils.formatarDataHora(dataInicialVigencia)
				+ ", dataFinalVigencia="
				+ DataHoraUtils.formatarDataHora(dataFinalVigencia)
				+ ", status=" + status + ", obs= " + obs + "]";
	}
}

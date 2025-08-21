package br.laramara.ti.sislaraserver.dominio.atendimento;

import java.util.Calendar;

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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.laramara.ti.sislaracommons.Identificavel;
import br.laramara.ti.sislaracommons.utilitarios.DataHoraUtils;
import br.laramara.ti.sislaracommons.utilitarios.MaquinaTempo;
import br.laramara.ti.sislaraserver.dominio.TamanhoMaximoGenerico;
import br.laramara.ti.sislaraserver.dominio.ValidavelObrigatoriedadeETamanhoMaximo;
import br.laramara.ti.sislaraserver.dominio.endereco.Validavel;
import br.laramara.ti.sislaraserver.dominio.grupo.DescricaoTipoAtendimento;
import br.laramara.ti.sislaraserver.dominio.grupo.Grupo;
import br.laramara.ti.sislaraserver.dominio.usuario.Setor;
import br.laramara.ti.sislaraserver.dominio.usuario.Usuario;

@Entity
@Table(name = "acao_conduta")
public class AcaoConduta extends Validavel implements ValidavelObrigatoriedadeETamanhoMaximo, Identificavel{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Long id;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "tipo_acao_conduta", length = TamanhoMaximoGenerico.TIPO_ACAO_CONDUTA, nullable = false)
	private TipoAcaoConduta tipoAcaoConduta;
	
	@ManyToOne
	@JoinColumn(name = "id_grupo")
	private Grupo grupo;
	
	@ManyToOne
	@JoinColumn(name = "id_usuario")
	private Usuario usuario;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_processamento")
	private Calendar dataProcessamento;
	
	@Column(name = "resultado_processamento", length = TamanhoMaximoGenerico.OBS)
	private String resultadoProcessamento;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "data_expectativa")
	private Calendar dataExpectativa;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "id_setor", length = TamanhoMaximoGenerico.SETOR)
	private Setor setor;
	
	@Column(name = "obs", length = TamanhoMaximoGenerico.OBS)
	private String obs;
		
	@Column(name = "cancelada")
	private boolean cancelada;

	public boolean possuiId() {
		return id != null && !cancelada;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TipoAcaoConduta getTipoAcaoConduta() {
		return tipoAcaoConduta;
	}

	public void setTipoAcaoConduta(TipoAcaoConduta tipoAcaoConduta) {
		this.tipoAcaoConduta = tipoAcaoConduta;
	}

	public Grupo getGrupo() {
		return grupo;
	}
	
	public DescricaoTipoAtendimento obterDescricaoTipoAtendimento(){
		return grupo.getDescricaoTipoAtendimento();
	}

	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public boolean cancelada() {
		return cancelada;
	}

	public void setCancelada(boolean cancelada) {
		this.cancelada = cancelada;
	}
	
	public Setor getSetor() {
		return setor;
	}

	public void setSetor(Setor setor) {
		this.setor = setor;
	}

	public String getDataExpectativa() {
		return DataHoraUtils.formatarData(dataExpectativa);
	}

	public void setDataExpectativa(String dataExpectativa) {
		this.dataExpectativa = DataHoraUtils.obterDataValidaInvalidaOuNulo(dataExpectativa);
	}

	public String getDataProcessamento() {
		return DataHoraUtils.formatarDataHora(dataProcessamento);
	}
	
	public void marcarComoProcessado(String resultadoProcessamento) {
		if (dataProcessamento == null) {
			this.dataProcessamento = MaquinaTempo.obterInstancia().obterCalendarioAtual();
			this.resultadoProcessamento = resultadoProcessamento;
		}
	}
	
	public void setObs(String obs) {
		this.obs = obs;
	}

	public String getObs() {
		return obs != null ? obs : "";
	}

	public void setResultadoProcessamento(String resultadoProcessamento) {
		this.resultadoProcessamento = resultadoProcessamento;
	}

	public String getResultadoProcessamento() {
		return resultadoProcessamento != null ? resultadoProcessamento : "";
	}

	private boolean processada(){
		return dataProcessamento != null;
	}

	public boolean canceladaEProcessada(){
		return cancelada() && processada();
	}
	
	public boolean naoCanceladaEAProcessar(){
		return !cancelada() && !processada();
	}
	
	public boolean eParaRetornar() {
		return eRetornar() && !cancelada();
	}
	
	public boolean eParaNaoECasoParaLM() {
		return eNaoECasoParaLM() && !cancelada();
	}

	private boolean eRetornar() {
		return tipoAcaoConduta.equals(TipoAcaoConduta.RETORNAR);
	}

	public boolean eParaIntegrar() {
		return eIntegrar() && !cancelada();
	}

	private boolean eIntegrar() {
		return tipoAcaoConduta.equals(TipoAcaoConduta.INTEGRAR);
	}

	private boolean eNaoECasoParaLM() {
		return tipoAcaoConduta.equals(TipoAcaoConduta.NAO_E_CASO_PARA_LM);
	}

	@Override
	public void validarObrigatoriedadeETamanhoMaximoDeDados() {
		if (tipoAcaoConduta == null) {
			adicionarErro("Adicione um tipo de conduta.");
		} else {
			if (eIntegrar()) {
				if (grupo == null){
					adicionarErro("Selecione um grupo para conduta.");
				}
				if (dataExpectativa != null){
					adicionarErro("Não é possível selecionar uma data de expectativa.");
				}
			}
			if (eRetornar()) {
				if (dataExpectativa == null) {
					adicionarErro("Selecione uma data de expectativa.");
				}
				if (setor == null) {
					adicionarErro("Selecione um setor.");
				}
				if (grupo != null){
					adicionarErro("Não é possível selecionar um grupo.");
				}
			}
			if (eNaoECasoParaLM()){
				if (grupo != null || dataExpectativa != null){
					adicionarErro("Não é possível selecionar um grupo, data de expectativa.");
				}
				if (obs == null || obs.isEmpty()) {
					adicionarErro("Insira uma observação.");
				}
			}
			if (DataHoraUtils.dataInvalida(dataExpectativa)){
				adicionarErro("Insira uma data válida.");
			}
		}
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AcaoConduta other = (AcaoConduta) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "AcaoConduta [id=" + id + ", tipoAcaoConduta=" + tipoAcaoConduta + ", dataProcessamento="
				+ DataHoraUtils.formatarDataHora(dataProcessamento) + ", resultadoProcessamento="
				+ resultadoProcessamento + ", dataExpectativa=" + DataHoraUtils.formatarData(dataExpectativa)
				+ ", setor=" + setor + ", obs=" + obs + ", grupo=" + grupo + ", usuario=" + usuario + ", cancelada="
				+ cancelada + "]";
	}
}

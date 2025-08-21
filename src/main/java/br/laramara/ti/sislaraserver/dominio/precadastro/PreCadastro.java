package br.laramara.ti.sislaraserver.dominio.precadastro;

import java.util.Calendar;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.laramara.ti.sislaracommons.Identificavel;
import br.laramara.ti.sislaracommons.utilitarios.DataHoraUtils;
import br.laramara.ti.sislaracommons.utilitarios.MaquinaTempo;
import br.laramara.ti.sislaraserver.dominio.InformacaoEssencial;
import br.laramara.ti.sislaraserver.dominio.TamanhoMaximoGenerico;
import br.laramara.ti.sislaraserver.dominio.endereco.Validavel;
import br.laramara.ti.sislaraserver.dominio.usuario.Usuario;

@Entity
@Table(name = "pre_cadastro")
public class PreCadastro extends Validavel implements Identificavel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Long id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_cadastro", nullable = false)
	private Calendar dataCadastro;

	@OneToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST }, optional = false)
	@JoinColumn(name = "id_informacao_essencial")
	private InformacaoEssencial informacaoEssencial;

	@Column(name = "obs", length = TamanhoMaximoGenerico.OBS)
	private String obs;

	public PreCadastro() {
		informacaoEssencial = new InformacaoEssencial();
		dataCadastro = MaquinaTempo.obterInstancia().obterCalendarioAtual();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDataCadastro() {
		return DataHoraUtils.formatarDataHora(dataCadastro);
	}

	public InformacaoEssencial getInformacaoEssencial() {
		return informacaoEssencial;
	}

	public void setInformacaoEssencial(InformacaoEssencial informacaoEssencial) {
		this.informacaoEssencial = informacaoEssencial;
	}

	public String getObs() {
		return obs;
	}

	public void setObs(String obs) {
		this.obs = obs;
	}
	
	public String getRg() {
		return informacaoEssencial.getRg();
	}
	
	public boolean possuiUsuarioAssociado() {
		return informacaoEssencial != null && informacaoEssencial.possuiUsuarioAssociado();
	}
	
	public Usuario obterUsuarioCriadoPeloPreCadastro() {
		if (possuiUsuarioAssociado()) {
			return obterUsuarioAssociado();
		} else {
			return null;
		}
	}

	public Usuario obterUsuarioAssociado() {
		return informacaoEssencial.getUsuario();
	}

	public void validarObrigatoriedadeETamanhoMaximoDeDados() {
		validarInformacaoEssencial();
		validarTamanhoMaximoDeDados();
	}

	private void validarInformacaoEssencial() {
		informacaoEssencial.validarObrigatoriedadeDeNomeRgContatoETamanhoMaximo();
		if (!informacaoEssencial.validado()) {
			adicionarErro(informacaoEssencial.obterErros());
		}
	}

	protected void validarTamanhoMaximoDeDados() {
		if (tamanhoMaximoViolado(obs, TamanhoMaximoGenerico.OBS)) {
			adicionarErro("Insira as Observações contendo até "
					+ TamanhoMaximoGenerico.OBS + " caracteres.");
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
		PreCadastro other = (PreCadastro) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	

	@Override
	public String toString() {
		return "PreCadastro [id=" + id + ", dataCadastro = "
				+ DataHoraUtils.formatarDataHora(dataCadastro)
				+ ", informacaoEssencial=" + informacaoEssencial + ", obs="
				+ obs + "]";
	}
}

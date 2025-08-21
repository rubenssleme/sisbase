package br.laramara.ti.sislaraserver.dominio.inscricao;

import java.util.Calendar;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.laramara.ti.sislaracommons.Identificavel;
import br.laramara.ti.sislaracommons.utilitarios.DataHoraUtils;
import br.laramara.ti.sislaracommons.utilitarios.MaquinaTempo;
import br.laramara.ti.sislaraserver.dominio.TamanhoMaximoGenerico;
import br.laramara.ti.sislaraserver.dominio.ValidavelObrigatoriedadeETamanhoMaximo;
import br.laramara.ti.sislaraserver.dominio.endereco.Endereco;
import br.laramara.ti.sislaraserver.dominio.endereco.Validavel;
import br.laramara.ti.sislaraserver.dominio.grupo.Grupo;
import br.laramara.ti.sislaraserver.dominio.usuario.externo.UsuarioExterno;

@Entity
@Table(name = "inscricao")
public class Inscricao extends Validavel implements Identificavel, ValidavelObrigatoriedadeETamanhoMaximo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_hora", nullable = false)
	protected Calendar dataHora;
	@ManyToOne
	@JoinColumn(name = "id_usuario_externo", nullable = false)
	private UsuarioExterno usuarioExterno;
	@OneToOne(cascade = CascadeType.PERSIST, optional = false)
	@JoinColumn(name = "id_endereco")
	private Endereco endereco;
	@Column(name = "nome_para_cracha", length = TamanhoMaximoInscricao.NOME_PARA_CRACHA, nullable = false)
	private String nomeParaCracha;
	@Column(name = "observacoes", length = TamanhoMaximoGenerico.OBS)
	private String observacoes;
	@Column(name = "area_formacao", length = TamanhoMaximoGenerico.NOME, nullable = false)
	private String areaFormacao;
	@Column(name = "local_trabalho", length = TamanhoMaximoGenerico.NOME, nullable = false)
	private String localTrabalho;
	@Column(name = "cargo_ou_funcao", length = TamanhoMaximoGenerico.NOME, nullable = false)
	private String cargoOuFuncao;
	@Column(name = "usuario_externo_possui_cadeira_de_rodas")
	private boolean usuarioExternoPossuiCadeiraDeRodas;
	@Column(name = "usuario_externo_possui_cao_guia")
	private boolean usuarioExternoPossuiCaoGuia;
	@ManyToOne
	@JoinColumn(name = "id_grupo", nullable = false)
	private Grupo grupo;
	@Column(name = "valor_total_almoco_incluso")
	private boolean valorTotalAlmocoIncluso;

	public Inscricao() {
		inicializarDataHora();
	}

	private void inicializarDataHora() {
		setDataHora(MaquinaTempo.obterInstancia().obterCalendarioAtual());
	}

	@Override
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDataHora() {
		return DataHoraUtils.formatarDataHora(dataHora);
	}

	public void setDataHora(Calendar dataHora) {
		this.dataHora = dataHora;
	}

	public void setUsuarioExterno(UsuarioExterno usuarioExterno) {
		this.usuarioExterno = usuarioExterno;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public String getNomeParaCracha() {
		return nomeParaCracha;
	}

	public void setNomeParaCracha(String nomeParaCracha) {
		this.nomeParaCracha = nomeParaCracha;
	}

	public String getObservacoes() {
		return observacoes;
	}

	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}

	public String getAreaFormacao() {
		return areaFormacao;
	}

	public void setAreaFormacao(String areaFormacao) {
		this.areaFormacao = areaFormacao;
	}

	public String getLocalTrabalho() {
		return localTrabalho;
	}

	public void setLocalTrabalho(String localTrabalho) {
		this.localTrabalho = localTrabalho;
	}

	public String getCargoOuFuncao() {
		return cargoOuFuncao;
	}

	public void setCargoOuFuncao(String cargoOuFuncao) {
		this.cargoOuFuncao = cargoOuFuncao;
	}

	public boolean isUsuarioExternoPossuiCadeiraDeRodas() {
		return usuarioExternoPossuiCadeiraDeRodas;
	}

	public void setUsuarioExternoPossuiCadeiraDeRodas(boolean usuarioExternoPossuiCadeiraDeRodas) {
		this.usuarioExternoPossuiCadeiraDeRodas = usuarioExternoPossuiCadeiraDeRodas;
	}

	public boolean isUsuarioExternoPossuiCaoGuia() {
		return usuarioExternoPossuiCaoGuia;
	}

	public void setUsuarioExternoPossuiCaoGuia(boolean usuarioExternoPossuiCaoGuia) {
		this.usuarioExternoPossuiCaoGuia = usuarioExternoPossuiCaoGuia;
	}

	public Grupo getGrupo() {
		return grupo;
	}

	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}

	public boolean isValorTotalAlmocoIncluso() {
		return valorTotalAlmocoIncluso;
	}

	public void setValorTotalAlmocoIncluso(boolean valorTotalAlmocoIncluso) {
		this.valorTotalAlmocoIncluso = valorTotalAlmocoIncluso;
	}

	@Override
	public void validarObrigatoriedadeETamanhoMaximoDeDados() {
		if (nomeParaCracha == null || nomeParaCracha.isEmpty()) {
			adicionarErro("Informe um nome para crachá válido.");
		}
		if (areaFormacao == null || areaFormacao.isEmpty()) {
			adicionarErro("Informe uma área de formação válida.");
		}
		if (localTrabalho == null || localTrabalho.isEmpty()) {
			adicionarErro("Informe um local de trabalho válido.");
		}
		if (cargoOuFuncao == null || cargoOuFuncao.isEmpty()) {
			adicionarErro("Informe um cargo ou função válido.");
		}
		if (grupo == null || grupo.getId() == null) {
			adicionarErro("Informe um curso / evento válido.");
		}
		validarTamanhoMaximoDeDados();
		validarObrigatoriedadeDeDadosEndereco();
	}

	private void validarTamanhoMaximoDeDados() {
		if (tamanhoMaximoViolado(observacoes, TamanhoMaximoGenerico.OBS)) {
			adicionarErro("Insira observações contendo até " + TamanhoMaximoGenerico.OBS + " caracteres.");
		}
		if (tamanhoMaximoViolado(nomeParaCracha, TamanhoMaximoInscricao.NOME_PARA_CRACHA)) {
			adicionarErro("Insira um nome para cracha contendo até " + TamanhoMaximoInscricao.NOME_PARA_CRACHA
					+ " caracteres.");
		}
		if (tamanhoMaximoViolado(areaFormacao, TamanhoMaximoGenerico.NOME)) {
			adicionarErro("Insira uma área de formação contendo até " + TamanhoMaximoGenerico.NOME + " caracteres.");
		}
		if (tamanhoMaximoViolado(localTrabalho, TamanhoMaximoGenerico.NOME)) {
			adicionarErro("Insira um local de trabalho contendo até " + TamanhoMaximoGenerico.NOME + " caracteres.");
		}
		if (tamanhoMaximoViolado(cargoOuFuncao, TamanhoMaximoGenerico.NOME)) {
			adicionarErro("Insira um cargo ou função contendo até " + TamanhoMaximoGenerico.NOME + " caracteres.");
		}
	}

	private void validarObrigatoriedadeDeDadosEndereco() {
		if (endereco == null) {
			adicionarErro("Informe um endereço válido.");
		} else {
			getEndereco().validarObrigatoriedadeETamanhoMaximoDeDadosSemZonaESemPais();
			if (!getEndereco().validado()) {
				adicionarErro(getEndereco().obterDescricaoErros());
			}
		}
	}

	@Override
	public String toString() {
		return "Inscricao [id=" + id + ", dataHora=" + getDataHora() + ", usuarioExterno=" + usuarioExterno + ", endereco="
				+ endereco + ", nomeParaCracha=" + nomeParaCracha + ", observacoes=" + observacoes + ", areaFormacao="
				+ areaFormacao + ", localTrabalho=" + localTrabalho + ", cargoOuFuncao=" + cargoOuFuncao
				+ ", usuarioExternoPossuiCadeiraDeRodas=" + usuarioExternoPossuiCadeiraDeRodas
				+ ", usuarioExternoPossuiCaoGuia=" + usuarioExternoPossuiCaoGuia + ", grupo=" + grupo
				+ ", valorTotalAlmocoIncluso=" + valorTotalAlmocoIncluso + "]";
	}

}
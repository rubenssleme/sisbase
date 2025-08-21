package br.laramara.ti.sislaraserver.dominio;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.laramara.ti.sislaracommons.utilitarios.CpfUtils;
import br.laramara.ti.sislaracommons.utilitarios.DataHoraUtils;
import br.laramara.ti.sislaracommons.utilitarios.TextoUtils;
import br.laramara.ti.sislaraserver.dominio.endereco.Endereco;
import br.laramara.ti.sislaraserver.dominio.endereco.Municipio;
import br.laramara.ti.sislaraserver.dominio.endereco.Pais;
import br.laramara.ti.sislaraserver.dominio.endereco.UF;
import br.laramara.ti.sislaraserver.dominio.endereco.Validavel;
import br.laramara.ti.sislaraserver.dominio.endereco.Zona;
import br.laramara.ti.sislaraserver.dominio.usuario.Usuario;

@Entity
@Table(name = "informacao_essencial")
public class InformacaoEssencial extends Validavel implements ValidavelCpf {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "nome", length = TamanhoMaximoGenerico.NOME, nullable = false)
	private String nome;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "data_nascimento")
	private Calendar dataNascimento;

	@OneToMany(cascade = { CascadeType.MERGE, CascadeType.PERSIST }, fetch = FetchType.EAGER)
	@JoinTable(name = "informacao_essencial_historico_rg", joinColumns = { 
			@JoinColumn(name = "id_informacao_essencial", referencedColumnName = "id") }, 
			inverseJoinColumns = { @JoinColumn(name = "id_historico_rg", referencedColumnName = "id") })
	private List<HistoricoRg> historicoRg;
	
	@Column(name = "cpf", length = TamanhoMaximoGenerico.CPF)
	private String cpf;

	@OneToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST}, optional = false)
	@JoinColumn(name = "id_contato")
	private Contato contato;
	
	@OneToOne(mappedBy="informacaoEssencial")
	private Usuario usuario;
		
	@OneToMany(cascade = { CascadeType.MERGE, CascadeType.PERSIST }, fetch = FetchType.EAGER)
	@JoinTable(name = "informacao_essencial_endereco", joinColumns = { @JoinColumn(name = "id_informacao_essencial", referencedColumnName = "id") }, 
			inverseJoinColumns = { @JoinColumn(name = "id_endereco", referencedColumnName = "id") })
	private List<Endereco> endereco;

	public InformacaoEssencial() {
		super();
		contato = new Contato();
		historicoRg = new ArrayList<>();
		endereco = new ArrayList<>();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getDataNascimento() {
		return DataHoraUtils.formatarData(dataNascimento);
	}

	public void setDataNascimento(String data) {
		this.dataNascimento = DataHoraUtils.obterDataValidaInvalidaOuNulo(data);
	}
	
	public int getIdadeComoInteiro() {
		return DataHoraUtils.obterAnosTranscorridos(dataNascimento);
	}
	
	public boolean possuiIdadeIgualOuSuperior5Anos() {
		return dataNascimento != null && getIdadeComoInteiro() >= 5;
	}
	
	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = TextoUtils.removerCaracteresInvalidosEAnularVazio(cpf);
	}

	public String getIdadeComoString() {
		if (dataNascimento != null) {
			String idadeRetorno = String.valueOf(getIdadeComoInteiro());
			return idadeRetorno.length() == 1 ? "0" + idadeRetorno : idadeRetorno;
		} else {
			return "???";
		}
	}

	public Contato getContato() {
		return contato;
	}

	public void setContato(Contato contato) {
		this.contato = contato;
	}

	public boolean possuiUsuarioAssociado(){
		return usuario != null;
	}
	
	public Usuario getUsuario() {
		return usuario;
	}

	public UF getUf() {
		return obterEnderecoAtual().getUf();
	}

	public String getCep() {
		return obterEnderecoAtual().getCep();
	}

	public Municipio getMunicipio() {
		return obterEnderecoAtual().getMunicipio();
	}

	public String getBairro() {
		return obterEnderecoAtual().getBairro();
	}

	public String getEndereco() {
		return obterEnderecoAtual().getEndereco();
	}

	public String getNumero() {
		return obterEnderecoAtual().getNumero();
	}

	public String getComplemento() {
		return obterEnderecoAtual().getComplemento();
	}

	public Zona getZona() {
		return obterEnderecoAtual().getZona();
	}

	public Pais getPais() {
		return obterEnderecoAtual().getPais();
	}
	
	public boolean possuiId() {
		return id != null;
	}
	
	public void validarEnderecoAtualCompleto(){
		Endereco enderecoAtual = obterEnderecoAtual();
		enderecoAtual.validarObrigatoriedadeETamanhoMaximoDeDados();
		if (!enderecoAtual.validado()){
			adicionarErro(enderecoAtual.obterErros());
		}	
	}
	
	public boolean possuiCpf() {
		return cpf != null && !cpf.isEmpty();
	}
	
	public void adicionarEndereco(Endereco endereco) {
		Endereco enderecoAtual = obterEnderecoAtual();
		if (!enderecoAtual.equals(endereco)) {
			enderecoAtual.encerrarVigencia();
			this.endereco.add(endereco);
		}
	}
	
	public Endereco obterEnderecoAtual() {
		return Historico.obterHistoricoAtual(endereco) != null ? (Endereco) Historico
				.obterHistoricoAtual(endereco) : new Endereco();
	}
	
	private void validarTamanhoMaximoDeDados() {
		if (tamanhoMaximoViolado(nome, TamanhoMaximoGenerico.NOME)) {
			adicionarErro("Insira um Nome contendo até "
					+ TamanhoMaximoGenerico.NOME + " caracteres.");
		}
		
		for (HistoricoRg historicoRg : this.historicoRg) {
			historicoRg.validarTamanhoMaximoDeDados();
			if (!historicoRg.validado()){
				adicionarErro(historicoRg.obterErros());
			}
		}
	}

	private void validarConsistenciaDoCpf() {
		if (cpf != null && !TextoUtils.estaVazio(cpf) && !CpfUtils.validarCPF(cpf)) {
			adicionarErro("Insira um CPF válido.");
		}
	}

	public void validarObrigatoriedadeDeNomeRgContatoETamanhoMaximo() {
		validaExistenciaDeNome();
		validaExistenciaDeRG();
		validarConsistenciaDoCpf();
		validatValidadeDeDataNascimento();
		validarContato();
		validarTamanhoMaximoDeDados();
	}
	
	public void validarObrigatoriedadeDeNomeRgCpfContatoEnderecoCompletoETamanhoMaximo() {
		validarObrigatoriedadeDeNomeRgContatoETamanhoMaximo();
		validarEnderecoAtualCompleto();
	}

	public void validarObrigatoriedadeDeNomeETamanhoMaximoDeDados() {
		validaExistenciaDeNome();
		validarTamanhoMaximoDeDados();
		validarTamanhoMaximoDadosContato();
	}

	private void validaExistenciaDeNome() {
		if (nome == null || TextoUtils.estaVazio(nome)) {
			adicionarErro("Insira um Nome.");
		}
	}

	public void adicionarRg(String rg) {
		HistoricoRg historicoRgAtual = obterRgAtual();
		HistoricoRg historicoRgAAdicionar = new HistoricoRg(rg);

		if (historicoRgAtual == null && !historicoRgAAdicionar.estaVazio()) {
			this.historicoRg.add(historicoRgAAdicionar);
		} else if (historicoRgAtual != null
				&& !historicoRgAtual.getRg().equals(
						historicoRgAAdicionar.getRg())) {
			historicoRgAtual.encerrarVigencia();
			this.historicoRg.add(historicoRgAAdicionar);
		}
	}
	
	private HistoricoRg obterRgAtual() {
		return Historico.obterHistoricoAtual(historicoRg) != null ? ((HistoricoRg) Historico
				.obterHistoricoAtual(historicoRg)) : null;
	}
	
	public String getRg() {
		return obterRgAtual() != null ? ((HistoricoRg) obterRgAtual()).getRg()
				: "";
	}
	
	private void validaExistenciaDeRG() {
		HistoricoRg historicoRgAtual = obterRgAtual();
		if (historicoRg.size() == 0
				|| (historicoRgAtual != null && historicoRgAtual.getRg()
						.isEmpty())) {
			adicionarErro("Insira o RG.");
		}
	}

	private void validarContato() {
		contato.validarObrigatoriedadeETamanhoMaximoDeDados();
		if (!contato.validado()) {
			adicionarErro(contato.obterErros());
		}
	}

	private void validarTamanhoMaximoDadosContato() {
		contato.validarTamanhoMaximoDeDados();
		if (!contato.validado()) {
			adicionarErro(contato.obterErros());
		}
	}
	
	public void validatObrigatoriedadeDeDataNascimento() {
		if (dataNascimento == null
				|| DataHoraUtils.dataInvalida(dataNascimento)) {
			adicionarErro("Insira uma Data de Nascimento válida.");
		}
	}

	@Override
	public void validarObrigatoriedadeDeCpf() {
		if (!possuiCpf()){
			adicionarErro("O solicitante não possui o CPF cadastrado.");
		}
	}
	
	public boolean possuiDataNascimento() {
		return dataNascimento != null;
	}
	
	public void validatValidadeDeDataNascimento() {
		if (DataHoraUtils.dataInvalida(dataNascimento)) {
			adicionarErro("Insira uma Data de Nascimento válida.");
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
		InformacaoEssencial other = (InformacaoEssencial) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "InformacaoEssencial [id=" + id + ", nome=" + nome + ", rg="
				+ getRg() + ", cpf=" + cpf + ", dataNascimento="
				+ DataHoraUtils.formatarDataHora(dataNascimento) + ", contato="
				+ contato + ", possuiUsuarioAssociado="
				+ possuiUsuarioAssociado() + ", Endereco=" + endereco + "]";
	}
}

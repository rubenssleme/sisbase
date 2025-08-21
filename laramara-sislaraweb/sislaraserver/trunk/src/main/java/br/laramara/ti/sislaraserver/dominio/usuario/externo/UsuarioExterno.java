package br.laramara.ti.sislaraserver.dominio.usuario.externo;

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
import br.laramara.ti.sislaracommons.utilitarios.CpfUtils;
import br.laramara.ti.sislaracommons.utilitarios.Criptografia;
import br.laramara.ti.sislaracommons.utilitarios.DataHoraUtils;
import br.laramara.ti.sislaracommons.utilitarios.EmailUtils;
import br.laramara.ti.sislaracommons.utilitarios.NumeroUtils;
import br.laramara.ti.sislaracommons.utilitarios.TextoUtils;
import br.laramara.ti.sislaraserver.dominio.TamanhoMaximoGenerico;
import br.laramara.ti.sislaraserver.dominio.ValidavelCpf;
import br.laramara.ti.sislaraserver.dominio.ValidavelObrigatoriedadeETamanhoMaximo;
import br.laramara.ti.sislaraserver.dominio.endereco.Endereco;
import br.laramara.ti.sislaraserver.dominio.endereco.Validavel;
import br.laramara.ti.sislaraserver.dominio.usuario.TamanhoMaximoUsuario;

@Entity
@Table(name = "usuario_externo")
public class UsuarioExterno extends Validavel implements Identificavel, ValidavelObrigatoriedadeETamanhoMaximo, ValidavelCpf {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "email", length = TamanhoMaximoGenerico.EMAIL, nullable = false)
	private String email;
	@Column(name = "nome_completo")
	private String nomeCompleto;
	@Column(name = "ddd")
	private String ddd;
	@Column(name = "telefone_celular")
	private String telefoneCelular;
	@Column(name = "outro_telefone")
	private String outroTelefone;
	@Column(name = "cpf")
	private String cpf;
	@Column(name = "senha")
	private String senha;
	@Column(name = "autorizo_recebimento_informativo")
	private boolean autorizoRecebimentoInformativo;
	@Column(name = "bloqueado")
	private boolean bloqueado;
	@Column(name = "token", length = TamanhoMaximoGenerico.TOKEN)
	private String token;
	@Temporal(TemporalType.DATE)
	@Column(name = "data_nascimento")
	private Calendar dataNascimento;
	@Column(name = "rg_rne")
	private String rgRne;
	@OneToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST}, optional = false)
	@JoinColumn(name = "id_endereco_residencial")
	private Endereco enderecoResidencial;
	@Column(name = "e_pessoa_com_deficiencia")
	private boolean ePessoaComDeficiencia;
	@Column(name = "possui_baixa_visao")
	private boolean possuiBaixaVisao;
	@Column(name = "possui_cegueira")
	private boolean possuiCegueira;
	@Column(name = "outra_deficiencia")
	private String outraDeficiencia;
	
	public UsuarioExterno() {
		bloqueado = false;
		enderecoResidencial = new Endereco();
	}
	
	@Override
	public Long getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = TextoUtils.removerCaracteresInvalidosEAnularVazio(email);
	}

	public String getNomeCompleto() {
		return nomeCompleto;
	}

	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = TextoUtils.anularVazio(nomeCompleto);
	}

	private void setDdd(String ddd) {
		this.ddd = NumeroUtils.obterTexto(NumeroUtils.retornaInteiroOuInvalido(ddd));
	}

	public String getOutroTelefone() {
		return outroTelefone;
	}

	public void setOutroTelefone(String outroTelefone) {
		this.outroTelefone = TextoUtils.removerCaracteresInvalidosEAnularVazio(outroTelefone);
	}
	
	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = TextoUtils.removerCaracteresInvalidosEAnularVazio(cpf);
	}

	public String getDdd() {
		return ddd;
	}

	public String getDddETelefoneCelular() {
		return ddd + telefoneCelular;
	}
	public String getTelefoneCelular() {
		return telefoneCelular;
	}

	private void setTelefoneCelular(String telefoneCelular) {
		this.telefoneCelular = NumeroUtils.obterTexto(NumeroUtils.retornaInteiroOuInvalido(telefoneCelular));
	}

	public void setDddETelefone(String dddETelefone) {
		dddETelefone = TextoUtils.removerCaracteresInvalidosEAnularVazio(dddETelefone);
		if (dddETelefone != null && dddETelefone.length() >= 2) {
			setDdd(dddETelefone.substring(0, 2));
			setTelefoneCelular(dddETelefone.substring(2, dddETelefone.length()));
		} 
	}
	
	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		configurarSenha(senha);
	}

	public boolean isAutorizoRecebimentoInformativo() {
		return autorizoRecebimentoInformativo;
	}

	public void setAutorizoRecebimentoInformativo(boolean autorizoRecebimentoInformativo) {
		this.autorizoRecebimentoInformativo = autorizoRecebimentoInformativo;
	}

	public boolean isBloqueado() {
		return bloqueado;
	}

	public void setBloqueado(boolean bloqueado) {
		this.bloqueado = bloqueado;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getDataNascimento() {
		return DataHoraUtils.formatarData(dataNascimento);
	}

	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = DataHoraUtils.obterDataValidaInvalidaOuNulo(dataNascimento);
	}

	public String getRgRne() {
		return rgRne;
	}

	public void setRgRne(String rgRne) {
		this.rgRne = TextoUtils.removerCaracteresInvalidosRG(rgRne);
	}

	public Endereco getEnderecoResidencial() {
		return enderecoResidencial;
	}

	public void setEnderecoResidencial(Endereco enderecoResidencial) {
		this.enderecoResidencial = enderecoResidencial;
	}
	
	public boolean isePessoaComDeficiencia() {
		return ePessoaComDeficiencia;
	}

	public void setePessoaComDeficiencia(boolean ePessoaComDeficiencia) {
		this.ePessoaComDeficiencia = ePessoaComDeficiencia;
	}

	public boolean isPossuiBaixaVisao() {
		return possuiBaixaVisao;
	}

	public void setPossuiBaixaVisao(boolean possuiBaixaVisao) {
		this.possuiBaixaVisao = possuiBaixaVisao;
	}

	public boolean isPossuiCegueira() {
		return possuiCegueira;
	}

	public void setPossuiCegueira(boolean possuiCegueira) {
		this.possuiCegueira = possuiCegueira;
	}

	public String getOutraDeficiencia() {
		return outraDeficiencia;
	}

	public void setOutraDeficiencia(String outraDeficiencia) {
		this.outraDeficiencia = outraDeficiencia;
	}

	public void setId(Long id) {
		this.id = id;
	}

	private void configurarSenha(String senha) {
		if (senha != null && !TextoUtils.estaVazio(senha)) {
			this.senha = Criptografia.converterParaMD5(senha);
		}
	}

	private void validarObrigatoriedadeDeDados() {
		if (email == null || email.isEmpty() || !EmailUtils.emailValido(email)) {
			adicionarErro("Informe um e-mail válido.");
		}
		if (nomeCompleto == null || nomeCompleto.isEmpty()) {
			adicionarErro("Informe um nome completo válido.");
		}		
		if (dddInvalido() || telefoneCelularInvalido()) {
			adicionarErro("Informe um telefone válido.");
		}
	}
	
	@Override
	public void validarObrigatoriedadeDeCpf() {
		if (cpf == null || TextoUtils.estaVazio(cpf) || !CpfUtils.validarCPF(cpf)) {
			adicionarErro("Insira um CPF válido.");
		}		
	}
	
	public void validarPerfilPreenchido() {
		validarObrigatoriedadeETamanhoMaximoDeDados();
		validarObrigatoriedadeDeCpf();
		if (dataNascimento == null || DataHoraUtils.dataInvalida(dataNascimento)) {
			adicionarErro("Insira uma Data de Nascimento válida.");
		}
		if (rgRne == null || rgRne.isEmpty()) {
			adicionarErro("Insira um RG ou RNE válido.");
		}
		validarObrigatoriedadeETamanhoMaximoDeDadosEnderecoResidencial();

		if (ePessoaComDeficienciaMasNaoPossuiBaixaVisaoENaoPossuiCegueiraENaoPossuiOutraDeficiencia()
				|| naoEPessoaComDeficienciaMasPossuiOutraDeficiencia()) {
			adicionarErro("Informe ao menos uma deficiência válida.");
		}
	}

	private boolean naoEPessoaComDeficienciaMasPossuiOutraDeficiencia() {
		return !ePessoaComDeficiencia && (outraDeficiencia != null && !outraDeficiencia.isEmpty() || possuiBaixaVisao || possuiCegueira);
	}

	private boolean ePessoaComDeficienciaMasNaoPossuiBaixaVisaoENaoPossuiCegueiraENaoPossuiOutraDeficiencia() {
		return ePessoaComDeficiencia && (!possuiBaixaVisao && !possuiCegueira && (outraDeficiencia == null || outraDeficiencia.isEmpty()));
	}

	private void validarObrigatoriedadeETamanhoMaximoDeDadosEnderecoResidencial() {
		if (enderecoResidencial == null) {
			adicionarErro("Informe um endereço válido.");
		} else  {
			enderecoResidencial.validarObrigatoriedadeETamanhoMaximoDeDadosSemZonaESemPais();
			if (!enderecoResidencial.validado()) {
				adicionarErro(enderecoResidencial.obterErros());
			}
		}
	}

	private boolean telefoneCelularInvalido() {
		return telefoneCelular == null || telefoneCelular.isEmpty() || NumeroUtils.numeroInteiroInvalido(NumeroUtils.retornaInteiroOuInvalido(telefoneCelular));
	}
	
	private boolean dddInvalido() {
		return ddd == null || ddd.isEmpty() || NumeroUtils.numeroInteiroInvalido(NumeroUtils.retornaInteiroOuInvalido(ddd));
	}
	
	public void atribuirTipoEnderecoResidencial() {
		getEnderecoResidencial().atribuirTipoEnderecoResidencial();
	}

	private void validarTamanhoMaximoDeDados() {
		if (tamanhoMaximoViolado(email, TamanhoMaximoGenerico.EMAIL)) {
			adicionarErro("Insira um Email contendo até " + TamanhoMaximoGenerico.EMAIL + " caracteres.");
		}
		if (tamanhoMaximoViolado(nomeCompleto, TamanhoMaximoGenerico.NOME)) {
			adicionarErro("Insira um nome completo contendo até " + TamanhoMaximoGenerico.NOME + " caracteres.");
		}	
		if (tamanhoMinimoEMaximoViolado(telefoneCelular, TamanhoMaximoGenerico.TELEFONE_MINIMO,
				TamanhoMaximoGenerico.TELEFONE_MAXIMO)) {
			adicionarErro("O telefone celular (" + ddd + ")" + " " + telefoneCelular
					+ " é inválido. Insira um DDD contendo " + TamanhoMaximoGenerico.DDD
					+ " digitos e o telefone celular contendo de " + TamanhoMaximoGenerico.TELEFONE_MINIMO + " a "
					+ TamanhoMaximoGenerico.TELEFONE_MAXIMO + " dígitos.");
		}
		if (tamanhoMinimoEMaximoViolado(cpf, TamanhoMaximoGenerico.CPF, TamanhoMaximoGenerico.CPF)) {
			adicionarErro("O CPF " + cpf + " é inválido. Insira um CPF contendo "
					+ TamanhoMaximoGenerico.CPF + " digitos.");
		}
		if (tamanhoMinimoEMaximoViolado(outroTelefone,
				TamanhoMaximoGenerico.DDD + TamanhoMaximoGenerico.TELEFONE_MINIMO,
				TamanhoMaximoGenerico.DDD + TamanhoMaximoGenerico.TELEFONE_MAXIMO)) {
			adicionarErro("O outro telefone " + outroTelefone + " é inválido. Insira um DDD contendo "
					+ TamanhoMaximoGenerico.DDD + " digitos e o outro telefone contendo de " + TamanhoMaximoGenerico.TELEFONE_MINIMO
					+ " a " + TamanhoMaximoGenerico.TELEFONE_MAXIMO + " dígitos.");
		}
		if (tamanhoMaximoViolado(rgRne, TamanhoMaximoUsuario.RG)) {
			adicionarErro("O RG / RNE " + rgRne + " é inválido. Insira um RG / RNE contendo "
					+ TamanhoMaximoUsuario.RG + " digitos.");
		}
	}

	@Override
	public void validarObrigatoriedadeETamanhoMaximoDeDados() {
		validarObrigatoriedadeDeDados();
		validarTamanhoMaximoDeDados();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UsuarioExterno other = (UsuarioExterno) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "UsuarioExterno [id=" + id + ", email=" + email + ", nomeCompleto=" + nomeCompleto + ", ddd=" + ddd
				+ ", telefoneCelular=" + telefoneCelular + ", outroTelefone=" + outroTelefone + ", cpf=" + cpf
				+ ", autorizoRecebimentoInformativo=" + autorizoRecebimentoInformativo
				+ ", bloqueado=" + bloqueado + ", token=" + token + ", dataNascimento=" + getDataNascimento() + ", rgRne="
				+ rgRne + ", enderecoResidencial=" + enderecoResidencial + ", ePessoaComDeficiencia="
				+ ePessoaComDeficiencia + ", possuiBaixaVisao=" + possuiBaixaVisao + ", possuiCegueira="
				+ possuiCegueira + ", outraDeficiencia=" + outraDeficiencia + "]";
	}
	
}

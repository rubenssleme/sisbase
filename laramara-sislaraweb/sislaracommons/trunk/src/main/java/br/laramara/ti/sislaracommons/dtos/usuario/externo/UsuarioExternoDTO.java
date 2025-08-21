package br.laramara.ti.sislaracommons.dtos.usuario.externo;

import br.laramara.ti.sislaracommons.Identificavel;
import br.laramara.ti.sislaracommons.dtos.ModeloDTO;
import br.laramara.ti.sislaracommons.dtos.endereco.EnderecoDTO;
import br.laramara.ti.sislaracommons.utilitarios.Criptografia;

public class UsuarioExternoDTO extends ModeloDTO implements Identificavel {

	private static final long serialVersionUID = -6890670949381185096L;

	private Long id;
	private String email;
	private String nomeCompleto;
	private String senha;
	private String outroTelefone;
	private String cpf;
	private String token;
	private String dataNascimento;
	private String rgRne;
	private String dddETelefoneCelular;
	private boolean autorizoRecebimentoInformativo;
	private boolean bloqueado;
	private EnderecoDTO enderecoResidencial;
	private boolean ePessoaComDeficiencia;
	private boolean possuiBaixaVisao;
	private boolean possuiCegueira;
	private String outraDeficiencia;

	public UsuarioExternoDTO() {
	}

	public UsuarioExternoDTO(String email, String nomeCompleto, String dddETelefoneCelular,
			boolean autorizoRecebimentoInformativo) {
		super();
		setEmail(email);
		setNomeCompleto(nomeCompleto);
		setDddETelefoneCelular(dddETelefoneCelular);
		setAutorizoRecebimentoInformativo(autorizoRecebimentoInformativo);
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public Long getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = Criptografia.converterParaMD5(senha);
	}

	public String getOutroTelefone() {
		return outroTelefone;
	}

	public void setOutroTelefone(String outroTelefone) {
		this.outroTelefone = outroTelefone;
	}

	public String getCpf() {
		return cpf;
	}
	
	public String obterCpfCapado() {
		String c = cpf;
		StringBuilder sb = new StringBuilder(c);
				for(int i=2; i<=8;i++  ) {
					sb.setCharAt(i, '*');
				}
		return sb.toString();
	}
	
	public void setCpfCapado(String cpf) {
		
	}
	

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getNomeCompleto() {
		return nomeCompleto;
	}

	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}

	public String getDddETelefoneCelular() {
		return dddETelefoneCelular;
	}

	public void setDddETelefoneCelular(String dddETelefoneCelular) {
		this.dddETelefoneCelular = dddETelefoneCelular;
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

	public String getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getRgRne() {
		return rgRne;
	}

	public void setRgRne(String rgRne) {
		this.rgRne = rgRne;
	}

	public EnderecoDTO getEnderecoResidencial() {
		return enderecoResidencial;
	}

	public void setEnderecoResidencial(EnderecoDTO enderecoResidencial) {
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

	@Override
	public String toString() {
		return "UsuarioExternoDTO [id=" + id + ", email=" + email + ", nomeCompleto=" + nomeCompleto + ", senha="
				+ senha + ", outroTelefone=" + outroTelefone + ", cpf=" + cpf + ", token=" + token + ", dataNascimento="
				+ dataNascimento + ", rgRne=" + rgRne + ", dddETelefoneCelular=" + dddETelefoneCelular
				+ ", autorizoRecebimentoInformativo=" + autorizoRecebimentoInformativo + ", bloqueado=" + bloqueado
				+ ", enderecoResidencial=" + enderecoResidencial + ", souPessoaComDeficiencia=" + ePessoaComDeficiencia
				+ ", possuoBaixaVisao=" + possuiBaixaVisao + ", possuoCegueira=" + possuiCegueira
				+ ", outraDeficiencia=" + outraDeficiencia + "]";
	}

}
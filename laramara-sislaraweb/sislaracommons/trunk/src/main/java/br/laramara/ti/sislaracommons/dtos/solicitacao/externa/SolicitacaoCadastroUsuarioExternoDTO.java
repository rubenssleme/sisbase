package br.laramara.ti.sislaracommons.dtos.solicitacao.externa;

import br.laramara.ti.sislaracommons.dtos.ModeloDTO;

public class SolicitacaoCadastroUsuarioExternoDTO extends ModeloDTO {
	private static final long serialVersionUID = -4718300058032826001L;
	
	private String email;
	private String nomeCompleto;
	private String dddETelefone;
	private boolean autorizoRecebimentoInformativo;

	public SolicitacaoCadastroUsuarioExternoDTO() {
		super();
	}

	public SolicitacaoCadastroUsuarioExternoDTO(String nomeCompleto, String dddETelefone, String email,
			boolean autorizoRecebimentoInformativo) {
		super();
		setNomeCompleto(nomeCompleto);
		setDddETelefone(dddETelefone);
		setEmail(email);
		setAutorizoRecebimentoInformativo(autorizoRecebimentoInformativo);
	}

	public String getNomeCompleto() {
		return nomeCompleto;
	}

	public String getDddETelefone() {
		return dddETelefone;
	}

	public void setDddETelefone(String dddETelefone) {
		this.dddETelefone = dddETelefone;
	}

	public String getEmail() {
		return email;
	}

	public boolean isAutorizoRecebimentoInformativo() {
		return autorizoRecebimentoInformativo;
	}

	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setAutorizoRecebimentoInformativo(boolean autorizoRecebimentoInformativo) {
		this.autorizoRecebimentoInformativo = autorizoRecebimentoInformativo;
	}

	@Override
	public String toString() {
		return "SolicitacaoCadastroInformacoesBasicasInscricaoDTO [nomeCompleto=" + nomeCompleto + ", dddETelefone="
				+ dddETelefone + ", email=" + email + ", autorizoRecebimentoInformativo="
				+ autorizoRecebimentoInformativo + "]";
	}

}

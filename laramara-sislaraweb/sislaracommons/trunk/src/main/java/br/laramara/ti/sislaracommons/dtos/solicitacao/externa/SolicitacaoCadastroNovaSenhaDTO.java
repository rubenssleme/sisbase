package br.laramara.ti.sislaracommons.dtos.solicitacao.externa;

import br.laramara.ti.sislaracommons.dtos.ModeloDTO;

public class SolicitacaoCadastroNovaSenhaDTO extends ModeloDTO {
	private static final long serialVersionUID = -5405369715403103705L;

	private String chave;
	private String novaSenha;
	private String confirmacaoSenha;

	public SolicitacaoCadastroNovaSenhaDTO() {
		super();
	}

	public SolicitacaoCadastroNovaSenhaDTO(String chave, String novaSenha, String confirmacaoSenha) {
		super();
		setChave(chave);
		setNovaSenha(novaSenha);
		setConfirmacaoSenha(confirmacaoSenha);
	}

	public void setChave(String chave) {
		this.chave = chave;
	}

	public void setNovaSenha(String novaSenha) {
		this.novaSenha = novaSenha;
	}

	public void setConfirmacaoSenha(String confirmacaoSenha) {
		this.confirmacaoSenha = confirmacaoSenha;
	}

	public String getChave() {
		return chave;
	}

	public String getNovaSenha() {
		return novaSenha;
	}

	public String getConfirmacaoSenha() {
		return confirmacaoSenha;
	}

	@Override
	public String toString() {
		return "SolicitacaoAlteracaoSenhaDTO [chave=" + chave + ", novaSenha=" + novaSenha + ", confirmacaoSenha="
				+ confirmacaoSenha + "]";
	}

}

package br.laramara.ti.sislaraserver.dominio.solicitacao.externa;

public class SolicitacaoCadastroNovaSenha {
	private String chave;
	private String novaSenha;
	private String confirmacaoSenha;

	public SolicitacaoCadastroNovaSenha(String chave, String novaSenha, String confirmacaoSenha) {
		super();
		setChave(chave);
		setNovaSenha(novaSenha);
		setConfirmacaoSenha(confirmacaoSenha);
	}

	public String getChave() {
		return chave;
	}

	public void setChave(String chave) {
		this.chave = chave;
	}

	public String getNovaSenha() {
		return novaSenha;
	}

	public void setNovaSenha(String novaSenha) {
		this.novaSenha = novaSenha;
	}

	public String getConfirmacaoSenha() {
		return confirmacaoSenha;
	}

	public void setConfirmacaoSenha(String confirmacaoSenha) {
		this.confirmacaoSenha = confirmacaoSenha;
	}

	public boolean novaSenhaValida() {
		return novaSenha.equals(confirmacaoSenha);
	}

	@Override
	public String toString() {
		return "SolicitacaoCadastroNovaSenha [chave=" + chave + ", novaSenha=" + novaSenha + ", confirmacaoSenha="
				+ confirmacaoSenha + "]";
	}

}

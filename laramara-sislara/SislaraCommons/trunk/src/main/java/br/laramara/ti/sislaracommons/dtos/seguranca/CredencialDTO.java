package br.laramara.ti.sislaracommons.dtos.seguranca;

import br.laramara.ti.sislaracommons.dtos.ModeloDTO;
import br.laramara.ti.sislaracommons.utilitarios.Criptografia;

public class CredencialDTO extends ModeloDTO {
	private static final long serialVersionUID = -7707628886965980316L;

	private String usuario;
	private String senha;
	private String confirmacaoSenha;

	public CredencialDTO(String usuario, String senha) {
		this.usuario = usuario;
		this.senha = Criptografia.converterParaMD5(senha);
	}

	public CredencialDTO(String usuario, String senha, String confirmacaoSenha) {
		this(usuario, senha);
		this.confirmacaoSenha = Criptografia.converterParaMD5(confirmacaoSenha);
	}

	public String getUsuario() {
		return usuario;
	}

	public String getSenha() {
		return senha;
	}

	public boolean novaSenhaValida() {
		return confirmacaoSenha != null && senha != null
				&& senha.equals(confirmacaoSenha);
	}
}

package br.laramara.ti.sislaraweb.controladores;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.laramara.ti.sislaracommons.dtos.solicitacao.externa.SolicitacaoCadastroNovaSenhaDTO;
import br.laramara.ti.sislaraweb.utilitarios.Mensagem;

@Named
@ViewScoped
public class ControladorRecuperacaoSenha extends Controlador implements Serializable {
	private static final long serialVersionUID = 137620494741565972L;

	private String chave;
	private SolicitacaoCadastroNovaSenhaDTO solicitacaoCadastroNovaSenhaDTO;

	@PostConstruct
	public void inicializar() {
		carregarSolicitacaoCadastroNovaSenha();
	}

	private void carregarSolicitacaoCadastroNovaSenha() {
		if (solicitacaoCadastroNovaSenhaDTO == null) {
			solicitacaoCadastroNovaSenhaDTO = new SolicitacaoCadastroNovaSenhaDTO();
		}
	}
	
	public String getChave() {
		return chave;
	}

	public void setChave(String chave) {
		this.chave = chave;
	}

	public SolicitacaoCadastroNovaSenhaDTO getSolicitacaoCadastroNovaSenhaDTO() {
		return solicitacaoCadastroNovaSenhaDTO;
	}

	public void setSolicitacaoCadastroNovaSenhaDTO(SolicitacaoCadastroNovaSenhaDTO solicitacaoCadastroNovaSenhaDTO) {
		this.solicitacaoCadastroNovaSenhaDTO = solicitacaoCadastroNovaSenhaDTO;
	}

	public void recuperar(ActionEvent actionEvent) {
		if (solicitacaoCadastroNovaSenhaDTO.getNovaSenha()
				.equals(solicitacaoCadastroNovaSenhaDTO.getConfirmacaoSenha())) {
			solicitacaoCadastroNovaSenhaDTO.setChave(chave);

			exibirResultado(servicoSisLaraClient.cadastrarNovaSenha(solicitacaoCadastroNovaSenhaDTO));
		} else {
			Mensagem.exibirMensagemErro(MENSAGEM_ERRO_DURANTE_O_CADASTRO_AS_SENHAS_NAO_ESTAO_IGUAIS);
		}
	}
}

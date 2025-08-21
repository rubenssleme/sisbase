package br.laramara.ti.sislaraweb.controladores;

import java.io.Serializable;

import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.laramara.ti.sislaracommons.dtos.solicitacao.externa.SolicitacaoCadastroUsuarioExternoDTO;

@Named
@ViewScoped
public class ControladorPaginaCadastroUsuarioExterno extends Controlador implements Serializable {
	private static final long serialVersionUID = 4500193739866159573L;

	private SolicitacaoCadastroUsuarioExternoDTO solicitacaoCadastroUsuarioExternoDTO;

	public SolicitacaoCadastroUsuarioExternoDTO getSolicitacaoCadastroUsuarioExternoDTO() {
		if (solicitacaoCadastroUsuarioExternoDTO == null) {
			solicitacaoCadastroUsuarioExternoDTO = new SolicitacaoCadastroUsuarioExternoDTO();
		}
		return solicitacaoCadastroUsuarioExternoDTO;
	}

	public void setSolicitacaoCadastroUsuarioExternoDTO(
			SolicitacaoCadastroUsuarioExternoDTO solicitacaoCadastroUsuarioExternoDTO) {
		this.solicitacaoCadastroUsuarioExternoDTO = solicitacaoCadastroUsuarioExternoDTO;
	}

	public void cadastrarUsuarioExterno(ActionEvent evt) {
		exibirResultado(servicoSisLaraClient.cadastrarUsuarioExterno(solicitacaoCadastroUsuarioExternoDTO));
	}

	public void sair(ActionEvent actionEvent) {
		sair();
	}

}

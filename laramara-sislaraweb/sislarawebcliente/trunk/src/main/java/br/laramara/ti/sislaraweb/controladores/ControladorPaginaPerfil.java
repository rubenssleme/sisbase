package br.laramara.ti.sislaraweb.controladores;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.laramara.ti.sislaracommons.dtos.solicitacao.externa.SolicitacaoEdicaoUsuarioExternoDTO;

@Named
@ViewScoped
public class ControladorPaginaPerfil extends Controlador implements Serializable {

	private static final long serialVersionUID = 8095292433799087939L;

	@PostConstruct
	public void autorizar() {
		redirecionarParaPaginaInicialSeNaoEstiverLogado();
	}
	
	public void editarUsuarioExterno(ActionEvent actionEvent) {
		getUsuarioExternoDto().setEnderecoResidencial(getEnderecoDto());

		SolicitacaoEdicaoUsuarioExternoDTO solicitacaoEdicaoUsuarioExternoDTO = new SolicitacaoEdicaoUsuarioExternoDTO();

		solicitacaoEdicaoUsuarioExternoDTO.setUsuarioExternoDto(getUsuarioExternoDto());
		solicitacaoEdicaoUsuarioExternoDTO.setToken(getToken());

		exibirResultado(servicoSisLaraClient.editarUsuarioExterno(solicitacaoEdicaoUsuarioExternoDTO));
		redirecionarParaPaginaInicialSeNaoEstiverLogado();
	}

	public void sair(ActionEvent actionEvent) {
		sair();
	}

}
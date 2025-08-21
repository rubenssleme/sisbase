package br.laramara.sistelemarketingclient.controladores;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.laramara.sistelemarketingcommons.dtos.seguranca.NivelDTO;
import br.laramara.sistelemarketingcommons.dtos.seguranca.NivelResultadoDTO;

@Named
@ViewScoped
public class NivelControladorGerenciamento extends Controlador implements Serializable {

	private static final long serialVersionUID = -3577484978922611850L;

	private List<NivelDTO> niveisDto;

	@PostConstruct
	public void inicializar() {
		if (possuiPermissaoVisualizarNivel()) {
			carregarNiveis();
		}else {
			redirecionarSemAutorizacao();
		}
	}
	
	private void carregarNiveis() {
		NivelResultadoDTO nivelResultado = servicoSisLaraClient.nivelListar();
		if (nivelResultado.sucesso()) {
			niveisDto = nivelResultado.getNiveisDto();
		} else {
			exibirMensagemErro(nivelResultado.obterMensagens());
		}		
	}

	public List<NivelDTO> getNiveisDto() {
		return niveisDto;
	}
	
	public void processarMensagemInformacao() {
		if (possuiMensagemInformacao()) {
			exibirMensagemInformacao();
		}
    }
	
	public void novo(ActionEvent actionEvent) {
		redirecionarNivelEdicao();
	}
}
package br.laramara.sistelemarketingclient.controladores;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.laramara.sistelemarketingcommons.dtos.campanha.CampanhaDTO;
import br.laramara.sistelemarketingcommons.dtos.campanha.CampanhaResultadoDTO;

@Named
@ViewScoped
public class CampanhaControladorGerenciamento extends Controlador implements Serializable {

	private static final long serialVersionUID = -3577484978922611850L;

	private List<CampanhaDTO> campanhasDto;

	@PostConstruct
	public void inicializar() {
		if (possuiPermissaoVisualizarCampanha()) {
			carregarCampanhas();
		}else {
			redirecionarSemAutorizacao();
		}
	}

	private void carregarCampanhas() {
		CampanhaResultadoDTO campanhaResultado = servicoSisLaraClient.campanhaListar();
		if (campanhaResultado.sucesso()) {
			campanhasDto = campanhaResultado.getCampanhasDto();
		} else {
			exibirMensagemErro(campanhaResultado.obterMensagens());
		}				
	}

	public List<CampanhaDTO> getCampanhasDto() {
		return campanhasDto;
	}
	
	public void processarMensagemInformacao() {
		if (possuiMensagemInformacao()) {
			exibirMensagemInformacao();
		}
    }
	
	public void novo(ActionEvent actionEvent) {
		redirecionarCampanhaEdicao();
	}
}

package br.laramara.sistelemarketingclient.controladores;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.laramara.sistelemarketingcommons.dtos.seguranca.ContaAcessoDTO;
import br.laramara.sistelemarketingcommons.dtos.seguranca.ContaAcessoResultadoDTO;

@Named
@ViewScoped
public class ContaAcessoControladorGerenciamento extends Controlador implements Serializable {

	private static final long serialVersionUID = -3577484978922611850L;

	private List<ContaAcessoDTO> contasAcessosDto;

	@PostConstruct
	public void inicializar() {
		if (possuiPermissaoVisualizarContaAcesso()) {
			carregarContasAcessos();
		}else {
			redirecionarSemAutorizacao();
		}
	}

	private void carregarContasAcessos() {
		ContaAcessoResultadoDTO contasAcessosResultado = servicoSisLaraClient.contaAcessoListar();
		if (contasAcessosResultado.sucesso()) {
			contasAcessosDto = contasAcessosResultado.getContasAcessosDto();
		} else {
			exibirMensagemErro(contasAcessosResultado.obterMensagens());
		}		
	}

	public List<ContaAcessoDTO> getContasAcessosDto() {
		return contasAcessosDto;
	}
	
	public void processarMensagemInformacao() {
		if (possuiMensagemInformacao()) {
			exibirMensagemInformacao();
		}
    }
	
	public void novo(ActionEvent actionEvent) {
		redirecionarContaAcessoEdicao();
	}
}

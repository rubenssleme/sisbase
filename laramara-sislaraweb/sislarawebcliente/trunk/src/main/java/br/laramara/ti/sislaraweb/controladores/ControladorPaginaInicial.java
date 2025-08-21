package br.laramara.ti.sislaraweb.controladores;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
public class ControladorPaginaInicial extends Controlador implements Serializable {
	private static final long serialVersionUID = -205793420671911228L;
	
	@PostConstruct
	public void autorizar() {
		redirecionarParaPaginaInicialSeNaoEstiverLogado();
	}
	
	public void sair(ActionEvent actionEvent) {
		sair();
	}
}

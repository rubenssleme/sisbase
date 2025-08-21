package br.laramara.ti.sislaraweb.controladores;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.laramara.ti.sislaracommons.dtos.seguranca.ResultadoAutenticacaoDTO;
import br.laramara.ti.sislaracommons.dtos.seguranca.externa.CredencialExternaDTO;
import br.laramara.ti.sislaracommons.dtos.solicitacao.externa.ResultadoConsultaUsuarioExternoDTO;
import br.laramara.ti.sislaracommons.utilitarios.EmailUtils;
import br.laramara.ti.sislaraweb.utilitarios.Mensagem;

@Named
@ViewScoped
public class ControladorLogin extends Controlador implements Serializable {
	private static final long serialVersionUID = -3581381866986040360L;

	private CredencialExternaDTO credencialExternaDto;

	@PostConstruct
	public void inicializar() {
		carregarCredencialExterna();
	}

	private void carregarCredencialExterna() {
		if (credencialExternaDto == null) {
			credencialExternaDto = new CredencialExternaDTO();
		}
	}
	
	public CredencialExternaDTO getCredencialExternaDto() {
		return credencialExternaDto;
	}

	public void setCredencialExternaDto(CredencialExternaDTO credencialExternaDto) {
		this.credencialExternaDto = credencialExternaDto;
	}

	public void logar(ActionEvent actionEvent) {
		if (EmailUtils.emailValido(credencialExternaDto.getEmail())) {
			autenticarUsuarioExterno();
		} else {
			Mensagem.exibirMensagemErro(Controlador.MENSAGEM_ERRO_INFORME_UM_LOGIN_VALIDO);
		}
	}

	private void autenticarUsuarioExterno() {
		ResultadoAutenticacaoDTO resultadoAutenticacaoDTO = servicoSisLaraClient
				.autenticarUsuarioExterno(credencialExternaDto);

		if (resultadoAutenticacaoDTO.sucesso()) {
			setToken(resultadoAutenticacaoDTO.getToken().getToken());
			atualizarUsuarioExternoNaSessao();
			redirecionarParaPaginaInicial();
		} else {
			Mensagem.exibirMensagemErro(resultadoAutenticacaoDTO.getMensagem());
		}
	}

	private void atualizarUsuarioExternoNaSessao() {
		ResultadoConsultaUsuarioExternoDTO resultadoConsultaUsuarioExternoDto = servicoSisLaraClient
				.obterUsuarioExternoPorToken(getToken());
		
		if (resultadoConsultaUsuarioExternoDto.sucesso()) {
			setUsuarioExternoDto(resultadoConsultaUsuarioExternoDto.getUsuarioExternoDto());
		}
	}
}
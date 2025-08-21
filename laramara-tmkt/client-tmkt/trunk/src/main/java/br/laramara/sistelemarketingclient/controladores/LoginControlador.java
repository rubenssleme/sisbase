package br.laramara.sistelemarketingclient.controladores;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.laramara.sistelemarketingclient.servicos.rest.ServicoSistelemarketingClient;
import br.laramara.sistelemarketingcommons.dtos.seguranca.ContaAcessoResultadoAutenticacaoDTO;
import br.laramara.sistelemarketingcommons.dtos.seguranca.CredencialDTO;
import br.laramara.sistelemarketingcommons.dtos.telefonia.RamalDTO;
import br.laramara.sistelemarketingcommons.dtos.telefonia.RamalResultadoDTO;

@Named
@ViewScoped
public class LoginControlador extends Controlador implements Serializable {
	
	private static final long serialVersionUID = -3581381866986040360L;

	private String login;
	private String senha;
	private List<SelectItem> ramaisDto;
	private String ramalDtoSelecionado;
	
	@Inject
	private ServicoSistelemarketingClient servicoSisLaraClient;
	
	@PostConstruct
	public void inicializar() {
		carregarRamais();
	}
	
	private void carregarRamais() {
		RamalResultadoDTO r = servicoSisLaraClient.ramalListar();
		ramaisDto = converterDTOParaSelectItemComDescricao(r.getRamaisDto());
	}

	public void logar(ActionEvent actionEvent) {
		ContaAcessoResultadoAutenticacaoDTO resultadoAutenticacaoDto = servicoSisLaraClient
				.contaAcessoAutenticar(new CredencialDTO(login, senha, new RamalDTO(ramalDtoSelecionado)));
		if (resultadoAutenticacaoDto.sucesso()) {
			gravarContaAcessoEToken(resultadoAutenticacaoDto.obterContaAcessoDto(), resultadoAutenticacaoDto.getToken());
			redirecionarPrincipal();
		}else {
			exibirMensagemErro(resultadoAutenticacaoDto.getMensagem());
		}
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public List<SelectItem> getRamaisDto() {
		return ramaisDto;
	}

	public void setRamaisDto(List<SelectItem> ramaisDto) {
		this.ramaisDto = ramaisDto;
	}

	public String getRamalDtoSelecionado() {
		return ramalDtoSelecionado;
	}

	public void setRamalDtoSelecionado(String ramalDtoSelecionado) {
		this.ramalDtoSelecionado = ramalDtoSelecionado;
	}
	
	
}


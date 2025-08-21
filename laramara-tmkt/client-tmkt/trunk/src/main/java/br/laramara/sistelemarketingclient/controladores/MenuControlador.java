package br.laramara.sistelemarketingclient.controladores;

import java.io.Serializable;

import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.laramara.sistelemarketingcommons.dtos.seguranca.ContaAcessoDTO;
import br.laramara.sistelemarketingcommons.dtos.seguranca.IdentificacaoPermissao;

@Named
@ViewScoped
public class MenuControlador extends Controlador implements Serializable {

	private static final long serialVersionUID = -4528681923857645971L;
	
	public String getNomeUsuario() {
		ContaAcessoDTO contaAcessoDto = obterContaAcesso();
		try {
			return "Usuário: " + contaAcessoDto.getNome()
					+ (contaAcessoDto.getRamalDto() != null ? ", Ramal: " + obterContaAcesso().getRamalDto().getNumero() : "");
		} catch (Exception e) {
			return "";
		}
	}
	
	public boolean isExibirSupervisao(){
		return isExibirCampanhas();
	}
	
	public boolean isExibirAdministracaoDeUsuarios(){
		return isExibirNiveis() || isExibirUsuarios();
	}
	
	public boolean isExibirOperacao(){
		return isExibirListagem();
	}
	
	public boolean isExibirNiveis() {
		return possuiAutorizacao(IdentificacaoPermissao.ID_PERMISSAO_NIVEL_VISUALIZAR);
	}

	public boolean isExibirUsuarios() {
		return possuiAutorizacao(IdentificacaoPermissao.ID_PERMISSAO_CONTA_ACESSO_VISUALIZAR);
	}
	
	public boolean isExibirCampanhas() {
		return possuiAutorizacao(IdentificacaoPermissao.ID_PERMISSAO_CAMPANHA_VISUALIZAR);
	}
	
	public boolean isExibirListagem() {
		return possuiAutorizacao(IdentificacaoPermissao.ID_PERMISSAO_LISTAGEM_VISUALIZAR);
	}
	
	public void sair(ActionEvent actionEvent) {
		sair();
		redirecionarLogin();
	}
}

package br.laramara.ti.sislaraserver.dominio.doacao;

import br.laramara.ti.sislaraserver.dominio.EspecificacaoPesquisaBase;
import br.laramara.ti.sislaraserver.dominio.ValidavelObrigatoriedadeETamanhoMaximo;
import br.laramara.ti.sislaraserver.dominio.grupo.Recurso;
import br.laramara.ti.sislaraserver.dominio.precadastro.PreCadastro;

public class EspecificacaoPesquisaDemanda extends EspecificacaoPesquisaBase
		implements ValidavelObrigatoriedadeETamanhoMaximo {

	private Recurso recurso;
	private PreCadastro preCadastro;
	private String cpf;
	private StatusDemanda statusDemanda;
	private String numeroRecibo;

	public StatusDemanda getStatusDemanda() {
		return statusDemanda;
	}

	public void setStatusDemanda(StatusDemanda statusDemanda) {
		this.statusDemanda = statusDemanda;
	}

	public void setRecurso(Recurso recurso) {
		this.recurso = recurso;
	}

	public Recurso getRecurso() {
		return recurso;
	}

	public boolean possuiRecurso() {
		return recurso != null;
	}

	public PreCadastro getPreCadastro() {
		return preCadastro;
	}

	public void setPreCadastro(PreCadastro preCadastro) {
		this.preCadastro = preCadastro;
	}

	public boolean possuiPreCadastro(){
		return preCadastro != null;
	}
	
	public boolean possuiCpf() {
		return cpf != null && !cpf.trim().isEmpty();
	}
	
	public boolean possuiStatusDemanda(){
		return statusDemanda != null;
	}
	
	public String getCpf() {
		return cpf;
	}
	
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getNumeroRecibo() {
		return numeroRecibo;
	}

	public void setNumeroRecibo(String numeroRecibo) {
		this.numeroRecibo = numeroRecibo;
	}

	public boolean possuiNumeroRecibo() {
		return numeroRecibo != null && !numeroRecibo.trim().isEmpty();
	}
	
	@Override
	public void validarObrigatoriedadeETamanhoMaximoDeDados() {
		if (possuiTodosItensBaseNulos() && recurso == null && preCadastro == null && !possuiCpf() && prontuario == null && statusDemanda == null) {
			adicionarErro("Insira um parâmetro para pesquisa.");
		}
	}

	@Override
	public String toString() {
		return "EspecificacaoPesquisaDemanda [recurso=" + recurso + ", preCadastro=" + preCadastro + ", cpf=" + cpf
				+ ", statusDemanda=" + statusDemanda  +", numeroRecibo=" + numeroRecibo + "]";
	}
}

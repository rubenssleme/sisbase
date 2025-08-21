package br.laramara.ti.sislaraserver.dominio.doacao;

import java.math.BigDecimal;

import br.laramara.ti.sislaracommons.utilitarios.DinheiroUtils;
import br.laramara.ti.sislaraserver.dominio.ValidavelObrigatoriedadeETamanhoMaximo;
import br.laramara.ti.sislaraserver.dominio.endereco.Validavel;

public class EspecificacaoCaptacaoDemanda extends Validavel implements ValidavelObrigatoriedadeETamanhoMaximo {

	private Demanda demanda;
	private BigDecimal valor;
	private Recibo recibo;

	public Demanda getDemanda() {
		return demanda;
	}

	public void setDemanda(Demanda demanda) {
		this.demanda = demanda;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = DinheiroUtils.obterDinheiroOuInvalido(valor);
	}
	
	public Recibo getRecibo() {
		return recibo;
	}

	public void setRecibo(Recibo recibo) {
		this.recibo = recibo;
	}

	@Override
	public void validarObrigatoriedadeETamanhoMaximoDeDados() {
		if (recibo == null) {
			adicionarErro("Insira um recibo.");
		}
		if (recibo != null && !recibo.possuiId()) {
			adicionarErro("Insira um número de recibo.");
		}
		if (demanda == null) {
			adicionarErro("Insira uma demanda.");
		}
		if (valor == null || (valor != null && valor.equals(DinheiroUtils.obterDinheiroInvalido()))) {
			adicionarErro("Insira um valor da captação válido.");
		}
		if (demanda != null && !demanda.isCarteloDeSelos()) {
			adicionarErro("Somente o projeto carteda de selos permite captação.");
		}
	}

	@Override
	public String toString() {
		return "EspecificacaoCaptacaoDemanda [demanda=" + demanda + ", valor="
				+ DinheiroUtils.obterDinheiro(valor) + ", recibo=" + recibo + "]";
	}
}

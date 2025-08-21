package br.laramara.ti.sismovimentacaoserver.repositorios;

import br.laramara.ti.sismovimentacaoserver.dominio.Validavel;
import br.laramara.ti.sismovimentacaoserver.dominio.ValidavelObrigatoriedadeETamanhoMaximo;

public class EspecificacaoPesquisaMovimentacao extends Validavel implements
		ValidavelObrigatoriedadeETamanhoMaximo {

	private Integer quantidadeResultado;

	public Integer getQuantidadeResultado() {
		return quantidadeResultado;
	}

	public void setQuantidadeResultado(Integer quantidadeResultado) {
		this.quantidadeResultado = quantidadeResultado;
	}

	@Override
	public void validarObrigatoriedadeETamanhoMaximoDeDados() {
		if (quantidadeResultado == null) {
			adicionarErro("Insira a quantidade de resultados a exibir.");
		}

	}
}

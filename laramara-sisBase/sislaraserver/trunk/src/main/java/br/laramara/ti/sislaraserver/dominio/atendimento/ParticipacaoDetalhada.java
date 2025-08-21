package br.laramara.ti.sislaraserver.dominio.atendimento;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.laramara.ti.sislaracommons.utilitarios.NumeroUtils;
import br.laramara.ti.sislaraserver.dominio.TamanhoMaximoGenerico;
import br.laramara.ti.sislaraserver.dominio.ValidavelObrigatoriedadeETamanhoMaximo;
import br.laramara.ti.sislaraserver.dominio.endereco.Validavel;

@Entity
@Table(name = "participacao_detalhada")
public class ParticipacaoDetalhada extends Validavel implements ValidavelObrigatoriedadeETamanhoMaximo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Enumerated(EnumType.STRING)
	@Column(name = "participacao", length = TamanhoMaximoGenerico.DESCRICAO)
	private Participacao participacao;
	
	@Column(name = "quantidade", nullable = false)
	private Integer quantidade;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Participacao getParticipacao() {
		return participacao;
	}

	public void setParticipacao(Participacao participacao) {
		this.participacao = participacao;
	}

	public String getQuantidade() {
		return NumeroUtils.obterTexto(quantidade);
	}

	public void setQuantidade(String quantidade) {
		this.quantidade = NumeroUtils.retornaInteiroOuInvalido(quantidade);
	}
	
	public void setQuantidadePadrao() {
		this.quantidade = 1;
	}

	@Override
	public void validarObrigatoriedadeETamanhoMaximoDeDados() {
		validarObrigatoriedade();
	}

	private void validarObrigatoriedade() {
		if (participacao == null) {
			adicionarErro("Selecione uma Participação.");
		}
		if (quantidade == null || NumeroUtils.numeroInteiroInvalido(quantidade)) {
			adicionarErro("Insira uma Quantidade válida.");
		}
	}
	
	@Override
	public String toString() {
		return "ParticipacaoDetalhada [id=" + id + ", participacao=" + participacao + ", quantidade=" + quantidade
				+ "]";
	}
}

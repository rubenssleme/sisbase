package br.laramara.ti.sislaraserver.dominio.usuario;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.laramara.ti.sislaracommons.Identificavel;
import br.laramara.ti.sislaraserver.dominio.TamanhoMaximoGenerico;
import br.laramara.ti.sislaraserver.dominio.ValidavelObrigatoriedadeETamanhoMaximo;
import br.laramara.ti.sislaraserver.dominio.endereco.Validavel;

@Entity
@Table(name = "origem_encaminhamento_detalhado")
public class OrigemEncaminhamentoDetalhado extends Validavel implements Identificavel, ValidavelObrigatoriedadeETamanhoMaximo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "id_origem_encaminhamento")
	private OrigemEncaminhamento origemEncaminhamento;

	@Column(name = "profissional_liberal", length = TamanhoMaximoGenerico.NOME)
	private String profissionalLiberal;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public OrigemEncaminhamento getOrigemEncaminhamento() {
		return origemEncaminhamento;
	}

	public void setOrigemEncaminhamento(OrigemEncaminhamento origemEncaminhamento) {
		this.origemEncaminhamento = origemEncaminhamento;
	}

	public String getProfissionalLiberal() {
		return profissionalLiberal;
	}

	public void setProfissionalLiberal(String profissionalLiberal) {
		this.profissionalLiberal = profissionalLiberal;
	}

	protected void validarTamanhoMaximoDeDados() {
		if (tamanhoMaximoViolado(profissionalLiberal, TamanhoMaximoGenerico.NOME)) {
			adicionarErro("Insira o Profissional liberal contendo até " + TamanhoMaximoGenerico.NOME + " caracteres.");
		}
	}
	
	@Override
	public void validarObrigatoriedadeETamanhoMaximoDeDados() {
		validarTamanhoMaximoDeDados();
	}

	@Override
	public String toString() {
		return "OrigemEncaminhamentoDetalhado [id=" + id + ", origemEncaminhamento=" + origemEncaminhamento + ", profissionalLiberal=" + profissionalLiberal + "]";
	}
}

package br.laramara.ti.sislaraserver.dominio.atendimento;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.laramara.ti.sislaraserver.dominio.ValidavelObrigatoriedadeETamanhoMaximo;
import br.laramara.ti.sislaraserver.dominio.precadastro.PreCadastro;

@Entity
@Table(name = "atendimento_comunidade")
public class AtendimentoComunidade extends AtendimentoAuxiliarBase implements
		ValidavelObrigatoriedadeETamanhoMaximo {

	@ManyToOne
	@JoinColumn(name = "id_pre_cadastro", nullable = false)
	private PreCadastro preCadastro;
	
	@ManyToOne
	@JoinColumn(name = "id_tipo_vinculo", nullable = false)
	private TipoVinculo tipoVinculo;
	
	public PreCadastro getPreCadastro() {
		return preCadastro;
	}

	public void setPreCadastro(PreCadastro preCadastro) {
		this.preCadastro = preCadastro;
	}

	public TipoVinculo getTipoVinculo() {
		return tipoVinculo;
	}

	public void setTipoVinculo(TipoVinculo tipoVinculo) {
		this.tipoVinculo = tipoVinculo;
	}

	@Override
	public void validarObrigatoriedadeETamanhoMaximoDeDados() {
		validarObrigatoriedade();	
		validarInformacaoAtendimento();
	}
	
	private void validarObrigatoriedade() {
		if (preCadastro == null) {
			adicionarErro("Insira um Pr� Cadastro.");
		}
		if (tipoVinculo == null){
			adicionarErro("Insira uma Forma��o.");
		}
	}

	@Override
	public String toString() {
		return "AtendimentoComunidade [id=" + id + ", preCadastro="
				+ preCadastro + ", tipoVinculo=" + tipoVinculo
				+ ", informa��oAtendimento=" + informacaoAtendimento + "]";
	}
}

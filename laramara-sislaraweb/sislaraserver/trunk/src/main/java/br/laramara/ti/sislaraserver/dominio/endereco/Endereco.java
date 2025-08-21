package br.laramara.ti.sislaraserver.dominio.endereco;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import br.laramara.ti.sislaracommons.utilitarios.DataHoraUtils;
import br.laramara.ti.sislaracommons.utilitarios.TextoUtils;
import br.laramara.ti.sislaraserver.dominio.Historico;

@Entity
@Table(name = "endereco")
public class Endereco extends Historico {

	@Enumerated(EnumType.STRING)
	@Column(name = "uf", length = TamanhoMaximoEndereco.UF)
	private UF uf;

	@Column(name = "cep", length = TamanhoMaximoEndereco.CEP)
	private String cep;

	@OneToOne(optional = true)
	@JoinColumn(name = "id_municipio")
	private Municipio municipio;

	@Column(name = "bairro", length = TamanhoMaximoEndereco.BAIRRO)
	private String bairro;

	@Column(name = "endereco", length = TamanhoMaximoEndereco.ENDERECO)
	private String endereco;

	@Column(name = "numero", length = TamanhoMaximoEndereco.NUMERO)
	private String numero;

	@Column(name = "complemento", length = TamanhoMaximoEndereco.COMPLEMENTO)
	private String complemento;

	@Enumerated(EnumType.STRING)
	@Column(name = "zona", length = TamanhoMaximoEndereco.ZONA)
	private Zona zona;

	@OneToOne(optional = true)
	@JoinColumn(name = "id_pais")
	private Pais pais;

	@Enumerated(EnumType.STRING)
	@Column(name = "tipo_endereco", length = TamanhoMaximoEndereco.TIPO_ENDERECO)
	private TipoEndereco tipoEndereco;

	public UF getUf() {
		return uf;
	}

	public void setUf(UF uf) {
		this.uf = uf;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = TextoUtils.removerCaracteresInvalidosEAnularVazio(cep);
	}

	public Municipio getMunicipio() {
		return municipio;
	}

	public void setMunicipio(Municipio municipio) {
		this.municipio = municipio;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	private boolean possuiNumero() {
		return numero != null && !numero.isEmpty();
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = TextoUtils.anularVazio(complemento);
	}

	public Zona getZona() {
		return zona;
	}

	public void setZona(Zona zona) {
		this.zona = zona;
	}

	public Pais getPais() {
		return pais;
	}

	public void setPais(Pais pais) {
		this.pais = pais;
	}

	public TipoEndereco getTipoEndereco() {
		return tipoEndereco;
	}

	public void setTipoEndereco(TipoEndereco tipoEndereco) {
		this.tipoEndereco = tipoEndereco;
	}

	public void atribuirTipoEnderecoResidencial() {
		setTipoEndereco(TipoEndereco.RESIDENCIAL);
	}
	
	public String obterEnderecoComNumeroEComplemento() {
		return getEndereco()
				+ (possuiNumero() || possuiComplemento() ? ", " + getNumero() + " " + getComplemento() : "");
	}

	private boolean possuiComplemento() {
		return complemento != null && !complemento.isEmpty();
	}

	public void validarObrigatoriedadeETamanhoMaximoDeDadosSemZonaESemPais() {
		validarObrigatoriedade(false, false);
		validarTamanhoMaximoDeDados();
	}

	public void validarObrigatoriedadeETamanhoMaximoDeDadosSemZona() {
		validarObrigatoriedade(false);
		validarTamanhoMaximoDeDados();
	}

	public void validarObrigatoriedadeETamanhoMaximoDeDados() {
		validarObrigatoriedade(true);
		validarTamanhoMaximoDeDados();
	}

	private void validarObrigatoriedade(boolean validarZona) {
		validarObrigatoriedade(validarZona, true);
	}

	private void validarObrigatoriedade(boolean validarZona, boolean validarPais) {
		if (cep != null && !TextoUtils.estaVazio(cep) && cep.length() != 8) {
			adicionarErro("Insira um CEP válido.");
		}
		if (endereco == null || TextoUtils.estaVazio(endereco)) {
			adicionarErro("Insira o Endereço.");
		}
		if (numero == null || TextoUtils.estaVazio(numero)) {
			adicionarErro("Insira o Número.");
		}
		if (zona == null && validarZona) {
			adicionarErro("Insira a Zona.");
		}
		if (bairro == null || TextoUtils.estaVazio(bairro)) {
			adicionarErro("Insira o Bairro.");
		}
		if (uf == null) {
			adicionarErro("Insira o UF.");
		}
		if (municipio == null) {
			adicionarErro("Insira o Município.");
		}
		if (pais == null && validarPais) {
			adicionarErro("Insira o País.");
		}
	}

	private void validarTamanhoMaximoDeDados() {
		if (endereco != null && endereco.length() > TamanhoMaximoEndereco.ENDERECO) {
			adicionarErro("Insira um Endereço contendo até " + TamanhoMaximoEndereco.ENDERECO + " caracteres.");
		}

		if (numero != null && numero.length() > TamanhoMaximoEndereco.NUMERO) {
			adicionarErro("Insira um Número contendo até " + TamanhoMaximoEndereco.NUMERO + " caracteres.");
		}

		if (complemento != null && complemento.length() > TamanhoMaximoEndereco.COMPLEMENTO) {
			adicionarErro("Insira um Complemento contendo até " + TamanhoMaximoEndereco.COMPLEMENTO + " caracteres.");
		}

		if (bairro != null && bairro.length() > TamanhoMaximoEndereco.BAIRRO) {
			adicionarErro("Insira um Bairro contendo até " + TamanhoMaximoEndereco.BAIRRO + " caracteres.");
		}
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Endereco other = (Endereco) obj;
		if (bairro == null) {
			if (other.bairro != null)
				return false;
		} else if (!bairro.equals(other.bairro))
			return false;
		if (cep == null) {
			if (other.cep != null)
				return false;
		} else if (!cep.equals(other.cep))
			return false;
		if (complemento == null) {
			if (other.complemento != null)
				return false;
		} else if (!complemento.equals(other.complemento))
			return false;
		if (endereco == null) {
			if (other.endereco != null)
				return false;
		} else if (!endereco.equals(other.endereco))
			return false;
		if (municipio == null) {
			if (other.municipio != null)
				return false;
		} else if (!municipio.equals(other.municipio))
			return false;
		if (numero == null) {
			if (other.numero != null)
				return false;
		} else if (!numero.equals(other.numero))
			return false;
		if (pais == null) {
			if (other.pais != null)
				return false;
		} else if (!pais.equals(other.pais))
			return false;
		if (uf != other.uf)
			return false;
		if (zona != other.zona)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Endereco [id=" + id + ", dataInicialVigencia=" + DataHoraUtils.formatarDataHora(dataInicialVigencia)
				+ ", dataFinalVigencia=" + DataHoraUtils.formatarDataHora(dataFinalVigencia) + ", uf=" + uf + ", cep="
				+ cep + ", municipio=" + municipio + ", bairro=" + bairro + ", endereco=" + endereco + ", numero="
				+ numero + ", complemento=" + complemento + ", zona=" + zona + ", pais=" + pais + ", tipoEndereco=" 
				+ tipoEndereco + "]";
	}

}

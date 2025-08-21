package br.laramara.ti.sislaraserver.dominio.usuario;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import br.laramara.ti.sislaracommons.utilitarios.TextoUtils;
import br.laramara.ti.sislaraserver.dominio.ValidavelObrigatoriedadeETamanhoMaximo;
import br.laramara.ti.sislaraserver.dominio.endereco.Municipio;
import br.laramara.ti.sislaraserver.dominio.endereco.TamanhoMaximoEndereco;
import br.laramara.ti.sislaraserver.dominio.endereco.UF;
import br.laramara.ti.sislaraserver.dominio.endereco.Validavel;

@Entity
@Table(name = "certidao")
public class Certidao extends Validavel implements
		ValidavelObrigatoriedadeETamanhoMaximo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Enumerated(EnumType.STRING)
	@Column(name = "tipo_certidao", length = TamanhoMaximoCertidao.TIPO_CERTIDAO, nullable = false)
	private TipoCertidao tipoCertidao;

	@Column(name = "numero", length = TamanhoMaximoCertidao.NUMERO, nullable = false)
	private String numero;

	@Column(name = "livro", length = TamanhoMaximoCertidao.LIVRO, nullable = false)
	private String livro;

	@Column(name = "folha", length = TamanhoMaximoCertidao.FOLHA, nullable = false)
	private String folha;

	@Column(name = "distrito", length = TamanhoMaximoCertidao.DISTRITO, nullable = false)
	private String distrito;

	@Enumerated(EnumType.STRING)
	@Column(name = "uf", length = TamanhoMaximoEndereco.UF, nullable = false)
	private UF uf;

	@OneToOne(optional = false)
	@JoinColumn(name = "id_municipio")
	private Municipio municipio;

	public Certidao() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TipoCertidao getTipoCertidao() {
		return tipoCertidao;
	}

	public void setTipoCertidao(TipoCertidao tipoCertidao) {
		this.tipoCertidao = tipoCertidao;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getLivro() {
		return livro;
	}

	public void setLivro(String livro) {
		this.livro = livro;
	}

	public String getFolha() {
		return folha;
	}

	public void setFolha(String folha) {
		this.folha = folha;
	}

	public String getDistrito() {
		return distrito;
	}

	public void setDistrito(String distrito) {
		this.distrito = distrito;
	}

	public UF getUf() {
		return uf;
	}

	public void setUf(UF uf) {
		this.uf = uf;
	}

	public Municipio getMunicipio() {
		return municipio;
	}

	public void setMunicipio(Municipio municipio) {
		this.municipio = municipio;
	}

	public void validarObrigatoriedadeETamanhoMaximoDeDados() {
		validarObrigatoriedade();
		validarTamanhoMaximoDeDados();
	}

	private void validarTamanhoMaximoDeDados() {
		if (tamanhoMaximoViolado(numero, TamanhoMaximoCertidao.NUMERO)) {
			adicionarErro("Insira um numero da Certidão contendo até "
					+ TamanhoMaximoCertidao.NUMERO + " caracteres.");
		}
		if (tamanhoMaximoViolado(livro, TamanhoMaximoCertidao.LIVRO)) {
			adicionarErro("Insira o número do Livro contendo até "
					+ TamanhoMaximoCertidao.LIVRO + " caracteres.");
		}
		if (tamanhoMaximoViolado(folha, TamanhoMaximoCertidao.FOLHA)) {
			adicionarErro("Insira a número da Folha contendo até "
					+ TamanhoMaximoCertidao.FOLHA + " caracteres.");
		}
		if (tamanhoMaximoViolado(distrito, TamanhoMaximoCertidao.DISTRITO)) {
			adicionarErro("Insira o Distrito contendo até "
					+ TamanhoMaximoCertidao.DISTRITO + " caracteres.");
		}
	}

	private void validarObrigatoriedade() {
		if (tipoCertidao == null) {
			adicionarErro("Insira o Tipo de Certidão.");
		}
		if (numero == null || TextoUtils.estaVazio(numero)) {
			adicionarErro("Insira o número da Certidão.");
		}
		if (livro == null || TextoUtils.estaVazio(livro)) {
			adicionarErro("Insira o número do Livro.");
		}
		if (folha == null || TextoUtils.estaVazio(folha)) {
			adicionarErro("Insira o número da Folha.");
		}
		if (uf == null) {
			adicionarErro("Insira um UF.");
		}
		if (municipio == null) {
			adicionarErro("Insira um Município.");
		}
		if (distrito == null || TextoUtils.estaVazio(distrito)) {
			adicionarErro("Insira o Distrito da Certidão.");
		}
	}

	@Override
	public String toString() {
		return "Certidao [id=" + id + ", tipoCertidao=" + tipoCertidao
				+ ", numero=" + numero + ", livro=" + livro + ", folha="
				+ folha + ", distrito=" + distrito + ", uf=" + uf
				+ ", municipio=" + municipio + "]";
	}
}

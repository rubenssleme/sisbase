package br.laramara.ti.sislaraserver.dominio;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import br.laramara.ti.sislaracommons.utilitarios.TextoUtils;
import br.laramara.ti.sislaraserver.dominio.endereco.Validavel;

@Embeddable
public class Telefone extends Validavel implements
		ValidavelObrigatoriedadeETamanhoMaximo {

	@Enumerated(EnumType.STRING)
	@Column(name = "tipo", length = TamanhoMaximoGenerico.TIPO_TELEFONE, nullable = false)
	private TipoTelefone tipo;

	@Column(name = "ddd", length = TamanhoMaximoGenerico.DDD, nullable = false)
	private String ddd;

	@Column(name = "telefone", length = TamanhoMaximoGenerico.TELEFONE_MAXIMO, nullable = false)
	private String telefone;
	
	@Column(name = "ramal", length = TamanhoMaximoGenerico.RAMAL)
	private String ramal;

	@Column(name = "nome_contato", length = TamanhoMaximoGenerico.NOME)
	private String nomeContato;

	public Telefone() {
	}

	public Telefone(TipoTelefone tipo, String dddTelefone, String ramal, String nomeContato) {
		String dddETelefone = TextoUtils
				.removerCaracteresInvalidosEAnularVazio(dddTelefone);
		this.tipo = tipo;
		if (dddETelefone != null) {
			if (dddETelefone.length() >= 2){
				this.ddd = (String) dddETelefone.subSequence(0, 2);
				this.telefone = (String) dddETelefone.subSequence(2, dddETelefone.length());
			}
		}
		this.ramal = TextoUtils.removerCaracteresInvalidosEAnularVazio(ramal);
		this.nomeContato = nomeContato;
	}

	public String getDDDTelefone() {
		return ddd + telefone;
	}

	public TipoTelefone getTipo() {
		return tipo;
	}

	public String getRamal() {
		return ramal;
	}
	
	public String getNomeContato() {
		return nomeContato;
	}

	public void validarObrigatoriedadeETamanhoMaximoDeDados(){
		validarObrigatoriedade();
		validarTamanhoMaximoDeDados();
	}
	
	public void validarObrigatoriedade() {
		if (tipo == null) {
			adicionarErro("Insira o Tipo do Telefone.");
		}
		if (ddd == null || telefone == null) {
			adicionarErro("Insira o Número do Telefone.");
		}
	}

	public void validarTamanhoMaximoDeDados() {
		if (tamanhoMinimoEMaximoViolado(telefone,
				TamanhoMaximoGenerico.TELEFONE_MINIMO,
				TamanhoMaximoGenerico.TELEFONE_MAXIMO)) {
			adicionarErro("O Telefone (" + ddd + ")" + telefone
					+ " é inválido. Insira o Telefone contendo de "
					+ TamanhoMaximoGenerico.TELEFONE_MINIMO + " a "
					+ TamanhoMaximoGenerico.TELEFONE_MAXIMO + " dígitos.");
		}
		if (tamanhoMaximoViolado(ramal, TamanhoMaximoGenerico.RAMAL)) {
			adicionarErro("Insira um Ramal contendo até "
					+ TamanhoMaximoGenerico.RAMAL + " caracteres.");
		}
		if (tamanhoMaximoViolado(nomeContato, TamanhoMaximoGenerico.NOME)) {
			adicionarErro("Insira um Nome de Contato contendo até "
					+ TamanhoMaximoGenerico.NOME + " caracteres.");
		}
	}

	@Override
	public String toString() {
		return "Telefone [tipo=" + tipo + ", ddd=" + ddd + ", telefone="
				+ telefone + ", rama=" + ramal + ", nomeContato=" + nomeContato + "]";
	}
}

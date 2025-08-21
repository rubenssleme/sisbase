package br.laramara.ti.sislaraserver.dominio.evento;

import java.util.Comparator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.laramara.ti.sislaracommons.Identificavel;
import br.laramara.ti.sislaracommons.utilitarios.TextoUtils;
import br.laramara.ti.sislaraserver.dominio.TamanhoMaximoGenerico;
import br.laramara.ti.sislaraserver.dominio.ValidavelObrigatoriedadeETamanhoMaximo;
import br.laramara.ti.sislaraserver.dominio.endereco.Validavel;

@Entity
@Table(name = "descricao_evento")
public class DescricaoEvento extends Validavel implements Identificavel, ValidavelObrigatoriedadeETamanhoMaximo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Enumerated(EnumType.STRING)
	@Column(name = "tipo_descricao_evento", length = TamanhoMaximoDescricaoEvento.TIPO_DESCRICAO_EVENTO)
	private TipoDescricaoEvento tipoDescricaoEvento;
	@Column(name = "conteudo", length = TamanhoMaximoGenerico.TEXTO_GRANDE)
	private String conteudo;

	public DescricaoEvento() {
	}

	@Override
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TipoDescricaoEvento getTipoDescricaoEvento() {
		return tipoDescricaoEvento;
	}

	public void setTipoDescricaoEvento(TipoDescricaoEvento tipoDescricaoEvento) {
		this.tipoDescricaoEvento = tipoDescricaoEvento;
	}

	public String getConteudo() {
		return conteudo;
	}

	public void setConteudo(String conteudo) {
		this.conteudo = conteudo;
	}

	@Override
	public void validarObrigatoriedadeETamanhoMaximoDeDados() {
		validarObrigatoriedadeDeDados();
		validarTamanhoMaximoDeDados();
	}

	private void validarTamanhoMaximoDeDados() {
		if (tamanhoMaximoViolado(conteudo, TamanhoMaximoGenerico.TEXTO_GRANDE)) {
			adicionarErro("Insira um conteúdo contendo até " + TamanhoMaximoGenerico.TEXTO_GRANDE + " caracteres.");
		}
	}

	public void validarObrigatoriedadeDeDados() {
		if (tipoDescricaoEvento == null) {
			adicionarErro("Informe um tipo de descrição válido.");
		}
		if (conteudo == null || TextoUtils.estaVazio(conteudo)) {
			adicionarErro("Informe um conteúdo válido.");
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
		DescricaoEvento other = (DescricaoEvento) obj;
		if (tipoDescricaoEvento != other.tipoDescricaoEvento)
			return false;
		return true;
	}
	
	public static final Comparator<DescricaoEvento> obterComparador() {
		return (o1, o2) -> TipoDescricaoEvento.obterComparador().compare(o1.getTipoDescricaoEvento(),
				o2.getTipoDescricaoEvento());
	}

	@Override
	public String toString() {
		return "DescricaoEvento [id=" + id + ", tipoDescricaoEvento=" + tipoDescricaoEvento + ", conteudo=" + conteudo + "]";
	}

}
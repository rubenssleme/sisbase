package br.laramara.ti.sislaraserver.dominio.doacao;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.laramara.ti.sislaraserver.dominio.Arquivo;
import br.laramara.ti.sislaraserver.dominio.TamanhoMaximoGenerico;
import br.laramara.ti.sislaraserver.dominio.ValidavelObrigatoriedadeETamanhoMaximo;
import br.laramara.ti.sislaraserver.dominio.endereco.Validavel;

@Entity
@Table(name = "documento_solicitacao_doacao")
public class DocumentoSolicitacaoDoacao extends Validavel implements ValidavelObrigatoriedadeETamanhoMaximo{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Long id;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "nome_documento", length = TamanhoMaximoGenerico.NOME_DOCUMENTO)
	private NomeDocumento nomeDocumento;
	
	@ManyToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST}, optional = false)
	@JoinColumn(name = "id_arquivo")
	private Arquivo arquivo;

	public DocumentoSolicitacaoDoacao(){
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public NomeDocumento getNomeDocumento() {
		return nomeDocumento;
	}

	public void setNomeDocumento(NomeDocumento nomeDocumento) {
		this.nomeDocumento = nomeDocumento;
	}

	public Arquivo getArquivo() {
		return arquivo;
	}

	public void setArquivo(Arquivo arquivo) {
		this.arquivo = arquivo;
	}

	@Override
	public void validarObrigatoriedadeETamanhoMaximoDeDados() {
		if (nomeDocumento == null && arquivo == null){
			adicionarErro("Adicione um nome de documento e um arquivo.");
		}
	}

	@Override
	public String toString() {
		return "DocumentoSolicitacaoDoacao [id=" + id + ", nomeDocumento=" + nomeDocumento + ", arquivo=" + arquivo
				+ "]";
	}
}

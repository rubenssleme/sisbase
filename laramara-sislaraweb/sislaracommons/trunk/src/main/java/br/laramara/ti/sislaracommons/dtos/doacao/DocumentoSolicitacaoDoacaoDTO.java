package br.laramara.ti.sislaracommons.dtos.doacao;

import br.laramara.ti.sislaracommons.dtos.ArquivoDTO;
import br.laramara.ti.sislaracommons.dtos.ModeloDTO;

public class DocumentoSolicitacaoDoacaoDTO extends ModeloDTO {

	private static final long serialVersionUID = 4369207113593258456L;

	private Long id;
	
	private NomeDocumentoDTO nomeDocumentoDTO;
	
	private ArquivoDTO arquivoDTO;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public NomeDocumentoDTO getNomeDocumentoDTO() {
		return nomeDocumentoDTO;
	}

	public void setNomeDocumentoDTO(NomeDocumentoDTO nomeDocumentoDTO) {
		this.nomeDocumentoDTO = nomeDocumentoDTO;
	}
	
	public ArquivoDTO getArquivoDTO() {
		return arquivoDTO;
	}

	public void setArquivoDTO(ArquivoDTO arquivoDTO) {
		this.arquivoDTO = arquivoDTO;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DocumentoSolicitacaoDoacaoDTO other = (DocumentoSolicitacaoDoacaoDTO) obj;
		if (nomeDocumentoDTO == null) {
			if (other.nomeDocumentoDTO != null)
				return false;
		} else if (!nomeDocumentoDTO.equals(other.nomeDocumentoDTO))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return nomeDocumentoDTO.toString();
	}
}

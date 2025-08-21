package br.laramara.ti.sislaracommons.dtos.doacao;

import br.laramara.ti.sislaracommons.dtos.ModeloDTO;

public class NomeDocumentoDTO extends ModeloDTO {

	private static final long serialVersionUID = -6713895447532677928L;

	private String nomeDocumento;

	public NomeDocumentoDTO(String nomeDocumento) {
		this.nomeDocumento = nomeDocumento;
	}

	@Override
	public String toString() {
		return nomeDocumento;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NomeDocumentoDTO other = (NomeDocumentoDTO) obj;
		if (nomeDocumento == null) {
			if (other.nomeDocumento != null)
				return false;
		} else if (!nomeDocumento.equals(other.nomeDocumento))
			return false;
		return true;
	}
}

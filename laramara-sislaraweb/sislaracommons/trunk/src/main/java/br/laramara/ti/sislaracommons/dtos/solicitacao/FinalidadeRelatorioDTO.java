package br.laramara.ti.sislaracommons.dtos.solicitacao;

import br.laramara.ti.sislaracommons.dtos.ModeloDTO;

public class FinalidadeRelatorioDTO extends ModeloDTO {

	private static final long serialVersionUID = -8666416599177559523L;

	private String finalidadeRelatorio;

	public FinalidadeRelatorioDTO(String finalidadeRelatorio) {
		this.finalidadeRelatorio = finalidadeRelatorio;
	}

	@Override
	public String toString() {
		return finalidadeRelatorio;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FinalidadeRelatorioDTO other = (FinalidadeRelatorioDTO) obj;
		if (finalidadeRelatorio == null) {
			if (other.finalidadeRelatorio != null)
				return false;
		} else if (!finalidadeRelatorio.equals(other.finalidadeRelatorio))
			return false;
		return true;
	}
}

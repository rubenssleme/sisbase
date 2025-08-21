package br.laramara.ti.sislaracommons.dtos.atendimento;

import br.laramara.ti.sislaracommons.dtos.ModeloDTO;

public class ParticipacaoDTO extends ModeloDTO {

	private static final long serialVersionUID = -8666416599177559523L;

	private String participacao;

	public ParticipacaoDTO(String participacao) {
		this.participacao = participacao;
	}

	@Override
	public String toString() {
		return participacao;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ParticipacaoDTO other = (ParticipacaoDTO) obj;
		if (participacao == null) {
			if (other.participacao != null)
				return false;
		} else if (!participacao.equals(other.participacao))
			return false;
		return true;
	}

}

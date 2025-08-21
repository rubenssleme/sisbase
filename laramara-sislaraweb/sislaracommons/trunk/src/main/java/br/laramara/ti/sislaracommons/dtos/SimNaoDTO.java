package br.laramara.ti.sislaracommons.dtos;


public class SimNaoDTO extends ModeloDTO {

	private static final long serialVersionUID = -2437803202291845337L;

	private String simNao;

	public SimNaoDTO(String simNao) {
		this.simNao = simNao;
	}

	public String toString() {
		return simNao;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SimNaoDTO other = (SimNaoDTO) obj;
		if (simNao == null) {
			if (other.simNao != null)
				return false;
		} else if (!simNao.equals(other.simNao))
			return false;
		return true;
	}
}

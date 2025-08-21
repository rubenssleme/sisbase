package br.laramara.ti.sislaraserver.dominio.seguranca;

import java.io.Serializable;

public abstract class ChaveBloqueio implements Serializable {

	private static final long serialVersionUID = -7448560741470871333L;
	
	protected String chave;

	protected ChaveBloqueio(String chave) {
		this.chave = chave;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((chave == null) ? 0 : chave.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ChaveBloqueio other = (ChaveBloqueio) obj;
		if (chave == null) {
			if (other.chave != null)
				return false;
		} else if (!chave.equals(other.chave))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return chave;
	}
}

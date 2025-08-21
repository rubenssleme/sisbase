package br.laramara.ti.sislaracommons.dtos.seguranca;

import br.laramara.ti.sislaracommons.dtos.ModeloDTO;

public class TokenDTO extends ModeloDTO {

	private static final long serialVersionUID = 8292640301733159861L;

	private String token;

	public TokenDTO(String token) {
		this.token = token;
	}

	public String getToken() {
		return token;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TokenDTO other = (TokenDTO) obj;
		if (token == null) {
			if (other.token != null)
				return false;
		} else if (!token.equals(other.token))
			return false;
		return true;
	}
}

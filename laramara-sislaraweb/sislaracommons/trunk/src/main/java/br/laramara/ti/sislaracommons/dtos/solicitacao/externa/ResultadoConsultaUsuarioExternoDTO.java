package br.laramara.ti.sislaracommons.dtos.solicitacao.externa;

import br.laramara.ti.sislaracommons.dtos.ResultadoDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.externo.UsuarioExternoDTO;

public class ResultadoConsultaUsuarioExternoDTO extends ResultadoDTO {

	private static final long serialVersionUID = -7568803219374834057L;

	private UsuarioExternoDTO usuarioExternoDto;
	
	public void efetuadoComSucesso(UsuarioExternoDTO usuarioExternoDto) {
		super.efetuadoComSucesso();
		setUsuarioExternoDto(usuarioExternoDto);
	}
	
	public void setUsuarioExternoDto(UsuarioExternoDTO usuarioExternoDto) {
		this.usuarioExternoDto = usuarioExternoDto;
	}
	
	public UsuarioExternoDTO getUsuarioExternoDto() {
		return usuarioExternoDto;
	}

}

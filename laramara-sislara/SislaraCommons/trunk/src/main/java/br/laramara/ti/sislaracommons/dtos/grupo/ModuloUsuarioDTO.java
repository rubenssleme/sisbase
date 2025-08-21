package br.laramara.ti.sislaracommons.dtos.grupo;

import br.laramara.ti.sislaracommons.dtos.usuario.UsuarioDTO;

public class ModuloUsuarioDTO extends ModuloRelacaoBaseDTO {

	private static final long serialVersionUID = -4979806406812030347L;

	private UsuarioDTO usuarioDto;
	
	public ModuloUsuarioDTO() {
	}

	public UsuarioDTO getUsuarioDto() {
		return usuarioDto;
	}

	public void setUsuarioDto(UsuarioDTO usuarioDto) {
		this.usuarioDto = usuarioDto;
	}

	@Override
	protected String obterNome() {
		return usuarioDto.getInformacaoEssencialDto().getNome();
	}
}

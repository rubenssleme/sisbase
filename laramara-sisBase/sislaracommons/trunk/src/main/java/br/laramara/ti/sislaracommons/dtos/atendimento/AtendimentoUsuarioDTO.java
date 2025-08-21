package br.laramara.ti.sislaracommons.dtos.atendimento;

import br.laramara.ti.sislaracommons.Identificavel;
import br.laramara.ti.sislaracommons.dtos.usuario.UsuarioDTO;

public class AtendimentoUsuarioDTO extends AtendimentoBaseDTO implements
		Identificavel {

	private static final long serialVersionUID = -4056874624307112528L;

	private UsuarioDTO usuarioDto;

	public AtendimentoUsuarioDTO() {
		super();
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

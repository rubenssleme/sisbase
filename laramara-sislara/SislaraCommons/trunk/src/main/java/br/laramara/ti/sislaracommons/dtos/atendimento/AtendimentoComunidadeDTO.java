package br.laramara.ti.sislaracommons.dtos.atendimento;

import br.laramara.ti.sislaracommons.dtos.precadastro.PreCadastroDTO;

public class AtendimentoComunidadeDTO extends AtendimentoBaseDTO {

	private static final long serialVersionUID = 2459921741110923595L;

	private PreCadastroDTO preCadastroDto;

	private TipoVinculoDTO tipoVinculoDto;

	public AtendimentoComunidadeDTO() {
		super();
	}

	public PreCadastroDTO getPreCadastroDto() {
		return preCadastroDto;
	}

	public void setPreCadastroDto(PreCadastroDTO preCadastroDto) {
		this.preCadastroDto = preCadastroDto;
	}

	public TipoVinculoDTO getTipoVinculoDto() {
		return tipoVinculoDto;
	}

	public void setTipoVinculoDto(TipoVinculoDTO tipoVinculoDto) {
		this.tipoVinculoDto = tipoVinculoDto;
	}

	@Override
	protected String obterNome() {
		return preCadastroDto.getInformacaoEssencialDto().getNome();
	}
}

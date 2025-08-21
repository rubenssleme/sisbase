package br.laramara.ti.sislaracommons.dtos.atendimento;

import br.laramara.ti.sislaracommons.Identificavel;
import br.laramara.ti.sislaracommons.dtos.escola.DiretoriaEnsinoDTO;
import br.laramara.ti.sislaracommons.dtos.escola.DreCefaiDTO;
import br.laramara.ti.sislaracommons.dtos.instituicao.InstituicaoDTO;
import br.laramara.ti.sislaracommons.dtos.precadastro.PreCadastroDTO;

public class AtendimentoPreCadastroDTO extends AtendimentoBaseDTO implements
		Identificavel {

	private static final long serialVersionUID = -4056874624307112528L;

	private PreCadastroDTO preCadastroDto;
	private InstituicaoDTO instituicaoDto;
	private DreCefaiDTO dreCefaiDto;
	private DiretoriaEnsinoDTO diretoriaEnsinoDto;
	private TipoVinculoDTO tipoVinculoDto;
	private String nomeOrigem;
	
	public AtendimentoPreCadastroDTO() {
		super();
	}

	public PreCadastroDTO getPreCadastroDto() {
		return preCadastroDto;
	}

	public void setPreCadastroDto(PreCadastroDTO preCadastroDto) {
		this.preCadastroDto = preCadastroDto;
	}

	public InstituicaoDTO getInstituicaoDto() {
		return instituicaoDto;
	}

	public void setInstituicaoDto(InstituicaoDTO instituicaoDto) {
		this.instituicaoDto = instituicaoDto;
	}
	
	public DreCefaiDTO getDreCefaiDto() {
		return dreCefaiDto;
	}

	public void setDreCefaiDto(DreCefaiDTO dreCefaiDto) {
		this.dreCefaiDto = dreCefaiDto;
	}

	public DiretoriaEnsinoDTO getDiretoriaEnsinoDto() {
		return diretoriaEnsinoDto;
	}

	public void setDiretoriaEnsinoDto(DiretoriaEnsinoDTO diretoriaEnsinoDto) {
		this.diretoriaEnsinoDto = diretoriaEnsinoDto;
	}
	
	public TipoVinculoDTO getTipoVinculoDto() {
		return tipoVinculoDto;
	}

	public void setTipoVinculoDto(TipoVinculoDTO tipoVinculoDto) {
		this.tipoVinculoDto = tipoVinculoDto;
	}

	public String getNomeOrigem() {
		return nomeOrigem;
	}

	public void setNomeOrigem(String nomeOrigem) {
		this.nomeOrigem = nomeOrigem;
	}

	@Override
	protected String obterNome() {
		return preCadastroDto.getInformacaoEssencialDto().getNome();
	}
}

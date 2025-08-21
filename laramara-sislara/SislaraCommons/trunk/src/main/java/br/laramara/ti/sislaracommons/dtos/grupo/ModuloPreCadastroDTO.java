package br.laramara.ti.sislaracommons.dtos.grupo;

import br.laramara.ti.sislaracommons.dtos.atendimento.TipoVinculoDTO;
import br.laramara.ti.sislaracommons.dtos.escola.DiretoriaEnsinoDTO;
import br.laramara.ti.sislaracommons.dtos.escola.DreCefaiDTO;
import br.laramara.ti.sislaracommons.dtos.instituicao.InstituicaoDTO;
import br.laramara.ti.sislaracommons.dtos.precadastro.PreCadastroDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.UsuarioDTO;

public class ModuloPreCadastroDTO extends ModuloRelacaoBaseDTO {

	private static final long serialVersionUID = -4979806406812030347L;

	private PreCadastroDTO preCadastroDto;
	private UsuarioDTO usuarioVinculadoDto;
	private TipoVinculoDTO tipoVinculoDto;
	private InstituicaoDTO instituicaoDto;
	private DiretoriaEnsinoDTO diretoriaEnsinoDto;
	private DreCefaiDTO dreCefaiDto;
	private InstituicaoDTO instituicaoComSRMsDto;
	private InstituicaoDTO instituicaoComSalaDeRecursoDto;
	private InstituicaoDTO instituicaoComOutrosAEEDto;
	private String nomeOrigemComunidade;
	private String curso;
	private String quantidadeCriancas;
	private String quantidadeAdultos;
	
	public ModuloPreCadastroDTO() {
	}

	public PreCadastroDTO getPreCadastroDto() {
		return preCadastroDto;
	}

	public void setPreCadastroDto(PreCadastroDTO preCadastroDto) {
		this.preCadastroDto = preCadastroDto;
	}
	
	public UsuarioDTO getUsuarioVinculadoDto() {
		return usuarioVinculadoDto;
	}

	public void setUsuarioVinculadoDto(UsuarioDTO usuarioVinculadoDto) {
		this.usuarioVinculadoDto = usuarioVinculadoDto;
	}

	public TipoVinculoDTO getTipoVinculoDto() {
		return tipoVinculoDto;
	}

	public void setTipoVinculoDto(TipoVinculoDTO tipoVinculoDto) {
		this.tipoVinculoDto = tipoVinculoDto;
	}

	public String getNomeOrigemComunidade() {
		return nomeOrigemComunidade;
	}

	public void setNomeOrigemComunidade(String nomeOrigemComunidade) {
		this.nomeOrigemComunidade = nomeOrigemComunidade;
	}
	
	public String getCurso() {
		return curso;
	}

	public void setCurso(String curso) {
		this.curso = curso;
	}
	
	public InstituicaoDTO getInstituicaoDto() {
		return instituicaoDto;
	}

	public void setInstituicaoDto(InstituicaoDTO instituicaoDto) {
		this.instituicaoDto = instituicaoDto;
	}

	public DiretoriaEnsinoDTO getDiretoriaEnsinoDto() {
		return diretoriaEnsinoDto;
	}

	public void setDiretoriaEnsinoDto(DiretoriaEnsinoDTO diretoriaEnsinoDto) {
		this.diretoriaEnsinoDto = diretoriaEnsinoDto;
	}

	public DreCefaiDTO getDreCefaiDto() {
		return dreCefaiDto;
	}

	public void setDreCefaiDto(DreCefaiDTO dreCefaiDto) {
		this.dreCefaiDto = dreCefaiDto;
	}
	
	public InstituicaoDTO getInstituicaoComSRMsDto() {
		return instituicaoComSRMsDto;
	}

	public void setInstituicaoComSrmsDto(InstituicaoDTO instituicaoComSRMsDto) {
		this.instituicaoComSRMsDto = instituicaoComSRMsDto;
	}

	public InstituicaoDTO getInstituicaoComSalaDeRecursoDto() {
		return instituicaoComSalaDeRecursoDto;
	}

	public void setInstituicaoComSalaDeRecursoDto(
			InstituicaoDTO instituicaoComSalaDeRecursoDto) {
		this.instituicaoComSalaDeRecursoDto = instituicaoComSalaDeRecursoDto;
	}

	public InstituicaoDTO getInstituicaoComOutrosAEEDto() {
		return instituicaoComOutrosAEEDto;
	}

	public void setInstituicaoComOutrosAEEDto(
			InstituicaoDTO instituicaoComOutrosAEEDto) {
		this.instituicaoComOutrosAEEDto = instituicaoComOutrosAEEDto;
	}
	
	public String getQuantidadeCriancas() {
		return quantidadeCriancas;
	}

	public void setQuantidadeCriancas(String quantidadeCriancas) {
		this.quantidadeCriancas = quantidadeCriancas;
	}

	public String getQuantidadeAdultos() {
		return quantidadeAdultos;
	}

	public void setQuantidadeAdultos(String quantidadeAdultos) {
		this.quantidadeAdultos = quantidadeAdultos;
	}

	@Override
	protected String obterNome() {
		return preCadastroDto.getInformacaoEssencialDto().getNome();
	}
}

package br.laramara.ti.sislaracommons.dtos.usuario;

import java.util.ArrayList;
import java.util.List;

import br.laramara.ti.sislaracommons.Bloqueavel;
import br.laramara.ti.sislaracommons.Identificavel;
import br.laramara.ti.sislaracommons.dtos.InformacaoEssencialDTO;
import br.laramara.ti.sislaracommons.dtos.ModeloDTO;
import br.laramara.ti.sislaracommons.dtos.SimNaoDTO;
import br.laramara.ti.sislaracommons.dtos.endereco.UfDTO;
import br.laramara.ti.sislaracommons.dtos.escola.InformacaoEducacionalDTO;
import br.laramara.ti.sislaracommons.dtos.familiar.FamiliarDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.RecursoDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.StatusRelacaoComModuloDTO;
import br.laramara.ti.sislaracommons.dtos.instituicao.InstituicaoDTO;
import br.laramara.ti.sislaracommons.dtos.trabalho.InformacaoTrabalhoCompletaDTO;

public class UsuarioDTO extends ModeloDTO implements Identificavel, Bloqueavel {

	private static final long serialVersionUID = -6890670949381185096L;
	
	private Long id;
	private String dataCadastro;
	private InformacaoEssencialDTO informacaoEssencialDto;
	private ClassificacaoSocialDTO classificacaoSocialDto;
	private StatusDTO statusDto;
	private StatusRelacaoComModuloDTO statusUsuarioAtualDto;
	private GeneroDTO generoDto;
	private String naturalidade;
	private String nacionalidade;
	private UfDTO ufRgDto;
	private String dataExpedicaoRg;
	private String orgaoEmissorRg;
	private EstadoCivilDTO estadoCivilDto;
	private List<InformacaoEducacionalDTO> informacoesEducacionaisDto;
	private boolean naoAlfabetizado;
	private boolean naoEstaNaEscola;
	private boolean responsavelPorSiMesmo;
	private List<FamiliarDTO> familiaresDto;
	private List<SituacaoGuardaDTO> situacoesGuardaDto;
	private List<PeriodoBeneficioDTO> periodoBeneficiosDto;
	private List<PeriodoDeficienciaDTO> periodoDeficienciaDto;
	private List<PeriodoDoencaDTO> periodoDoencaDto;
	private InstituicaoDTO instituicaoComSRMsDto;
	private InstituicaoDTO instituicaoComSalaRecursoDto;
	private InstituicaoDTO instituicaoComOutrosAEE;
	private List<ConvenioDTO> conveniosDto;
	private boolean associadoAoSetorProceja;
	private boolean associadoAoSetorCTO;
	private GrupoEtnicoDTO grupoEtnicoDto;
	private boolean multiplaDeficiencia;
	private boolean cadeiraDeRoda;
	private String outrosApoiosServicos;
	private List<CertidaoDTO> certidoes;
	private byte[] foto;
	private String obs;
	private TipoLeituraDTO tipoLeituraDto;
	private String tamanhoFonte;
	private List<RecursoDTO> recursosDto;
	private SimNaoDTO atualmenteTrabalhandoDto;
	private SimNaoDTO possuiConsanguinidadeDto;
	private List<InformacaoTrabalhoCompletaDTO> informacaoTrabalhoCompletaDto;
	private String renda;
	private String rendaTotalFamiliar;
	private String cirurgias;
	private String medicamentos;
	private String consanguinidade;
	private List<CustoDTO> custosDoencaDto;
	private List<CustoDTO> custosDeficienciaDto;
	private boolean falecido;
	
	public UsuarioDTO(){
		informacaoEssencialDto = new InformacaoEssencialDTO();
		informacoesEducacionaisDto = new ArrayList<>();
		familiaresDto = new ArrayList<>();
		situacoesGuardaDto = new ArrayList<>();
		periodoBeneficiosDto = new ArrayList<>();
		periodoDeficienciaDto = new ArrayList<>();
		conveniosDto = new ArrayList<>();
		recursosDto = new ArrayList<>();
		informacaoTrabalhoCompletaDto = new ArrayList<>();
		custosDoencaDto = new ArrayList<>();
		custosDeficienciaDto = new ArrayList<>();
	}
	
	public UsuarioDTO(Long id){
		this();
		this.id = id;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long prontuario) {
		this.id = prontuario;
	}
	
	@Override
	public String obterNome() {
		return id != null ? getId().toString() : "";
	}

	public String getDataCadastro() {
		return dataCadastro;
	}
	
	public void setDataCadastro(String dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
	
	public ClassificacaoSocialDTO getClassificacaoSocialDto() {
		return classificacaoSocialDto;
	}

	public void setClassificacaoSocialDto(
			ClassificacaoSocialDTO classificacaoSocial) {
		this.classificacaoSocialDto = classificacaoSocial;
	}

	public StatusDTO getStatusDto() {
		return statusDto;
	}

	public void setStatusDto(StatusDTO status) {
		this.statusDto = status;
	}

	public InformacaoEssencialDTO getInformacaoEssencialDto() {
		return informacaoEssencialDto;
	}

	public void setInformacaoEssencialDto(
			InformacaoEssencialDTO informacaoEssencialDto) {
		this.informacaoEssencialDto = informacaoEssencialDto;
	}

	public GeneroDTO getGeneroDto() {
		return generoDto;
	}

	public void setGeneroDto(GeneroDTO generoDto) {
		this.generoDto = generoDto;
	}

	public String getNaturalidade() {
		return naturalidade;
	}

	public void setNaturalidade(String naturalidade) {
		this.naturalidade = naturalidade;
	}

	public String getNacionalidade() {
		return nacionalidade;
	}

	public void setNacionalidade(String nacionalidade) {
		this.nacionalidade = nacionalidade;
	}

	public UfDTO getUfRgDto() {
		return ufRgDto;
	}

	public void setUfRgDto(UfDTO ufRg) {
		this.ufRgDto = ufRg;
	}

	public String getDataExpedicaoRg() {
		return dataExpedicaoRg;
	}

	public void setDataExpedicaoRg(String dataExpedicaoRg) {
		this.dataExpedicaoRg = dataExpedicaoRg;
	}

	public String getOrgaoEmissorRg() {
		return orgaoEmissorRg;
	}

	public void setOrgaoEmissorRg(String orgaoEmissorRg) {
		this.orgaoEmissorRg = orgaoEmissorRg;
	}

	public EstadoCivilDTO getEstadoCivilDto() {
		return estadoCivilDto;
	}

	public void setEstadoCivilDto(EstadoCivilDTO estadoCivilDto) {
		this.estadoCivilDto = estadoCivilDto;
	}
	
	public GrupoEtnicoDTO getGrupoEtnicoDto(){
		return grupoEtnicoDto;
	}
	
	public void setGrupoEtnicoDto(GrupoEtnicoDTO grupoEtnicoDto){
		this.grupoEtnicoDto = grupoEtnicoDto;
	}

	public boolean isNaoAlfabetizado() {
		return naoAlfabetizado;
	}

	public void setNaoAlfabetizado(boolean naoAlfabetizado) {
		this.naoAlfabetizado = naoAlfabetizado;
	}

	public List<InformacaoEducacionalDTO> getInformacoesEducacionaisDto() {
		return informacoesEducacionaisDto;
	}

	public void setInformacaoEducacionaisDto(List<InformacaoEducacionalDTO> informacoesEducacionais) {
		this.informacoesEducacionaisDto = informacoesEducacionais;
	}

	public List<FamiliarDTO> getFamiliaresDto() {
		return familiaresDto;
	}

	public void setFamiliaresDto(List<FamiliarDTO> familiaresDto) {
		this.familiaresDto = familiaresDto;
	}
	
	public List<SituacaoGuardaDTO> getSituacoesGuardaDto() {
		return situacoesGuardaDto;
	}

	public void setSituacoesGuardaDto(List<SituacaoGuardaDTO> situacoesGuardaDto) {
		this.situacoesGuardaDto = situacoesGuardaDto;
	}

	public List<PeriodoBeneficioDTO> getPeriodoBeneficiosDto() {
		return periodoBeneficiosDto;
	}

	public void setPeriodoBeneficiosDto(List<PeriodoBeneficioDTO> periodoBeneficiosDto) {
		this.periodoBeneficiosDto = periodoBeneficiosDto;
	}

	public List<ConvenioDTO> getConveniosDto() {
		return conveniosDto;
	}

	public void setConveniosDto(List<ConvenioDTO> conveniosDto) {
		this.conveniosDto = conveniosDto;
	}

	public boolean isAssociadoAoSetorProceja() {
		return associadoAoSetorProceja;
	}

	public void setAssociadoAoSetorProceja(boolean associadoAoSetorProceja) {
		this.associadoAoSetorProceja = associadoAoSetorProceja;
	}

	public boolean isAssociadoAoSetorCTO() {
		return associadoAoSetorCTO;
	}

	public void setAssociadoAoSetorCTO(boolean associadoAoSetorCTO) {
		this.associadoAoSetorCTO = associadoAoSetorCTO;
	}

	public boolean isNaoEstaNaEscola() {
		return naoEstaNaEscola;
	}
	
	public void setNaoEstaNaEscola(boolean naoEstaNaEscola) {
		this.naoEstaNaEscola = naoEstaNaEscola;
	}

	public boolean isResponsavelPorSiMesmo() {
		return responsavelPorSiMesmo;
	}

	public void setResponsavelPorSiMesmo(boolean responsavelPorSiMesmo) {
		this.responsavelPorSiMesmo = responsavelPorSiMesmo;
	}

	public InstituicaoDTO getInstituicaoComSRMsDto() {
		return instituicaoComSRMsDto;
	}

	public void setInstituicaoComSRMSDto(InstituicaoDTO instituicaoComSRMsDto) {
		this.instituicaoComSRMsDto = instituicaoComSRMsDto;
	}

	public InstituicaoDTO getInstituicaoComSalaRecursoDto() {
		return instituicaoComSalaRecursoDto;
	}

	public void setInstituicaoComSalaRecursoDto(
			InstituicaoDTO instituicaoComSalaRecursoDto) {
		this.instituicaoComSalaRecursoDto = instituicaoComSalaRecursoDto;
	}
	
	public InstituicaoDTO getInstituicaoComOutrosAEE() {
		return instituicaoComOutrosAEE;
	}

	public void setInstituicaoComOutrosAEE(InstituicaoDTO instituicaoComOutrosAEE) {
		this.instituicaoComOutrosAEE = instituicaoComOutrosAEE;
	}

	public boolean isMultiplaDeficiencia() {
		return multiplaDeficiencia;
	}

	public void setMultiplaDeficiencia(boolean multiplaDeficiencia) {
		this.multiplaDeficiencia = multiplaDeficiencia;
	}
	
	public boolean isCadeiraDeRodas() {
		return cadeiraDeRoda;
	}
	
	public void setCadeiraDeRodas(boolean cadeiraDeRoda) {
		this.cadeiraDeRoda = cadeiraDeRoda;
	}

	public List<CertidaoDTO> getCertidoes() {
		return certidoes;
	}

	public void setCertidoes(List<CertidaoDTO> certidoes) {
		this.certidoes = certidoes;
	}

	public byte[] getFoto() {
		return foto;
	}

	public void setFoto(byte[] foto) {
		this.foto = foto;
	}

	public List<PeriodoDeficienciaDTO> getPeriodoDeficienciaDto() {
		return periodoDeficienciaDto;
	}

	public void setPeriodoDeficienciaDto(
			List<PeriodoDeficienciaDTO> periodoDeficienciaDto) {
		this.periodoDeficienciaDto = periodoDeficienciaDto;
	}
	
	public List<PeriodoDoencaDTO> getPeriodoDoencasDto() {
		return periodoDoencaDto;
	}

	public void setPeriodoDoencasDto(List<PeriodoDoencaDTO> periodoDoencaDto) {
		this.periodoDoencaDto = periodoDoencaDto;
	}

	public String getObs() {
		return obs;
	}

	public void setObs(String obs) {
		this.obs = obs;
	}

	public String getOutrosApoiosServicos() {
		return outrosApoiosServicos;
	}

	public void setOutrosApoiosServicos(String outrosApoiosServicos) {
		this.outrosApoiosServicos = outrosApoiosServicos;
	}

	public TipoLeituraDTO getTipoLeituraDto() {
		return tipoLeituraDto;
	}

	public void setTipoLeituraDto(TipoLeituraDTO tipoLeituraDto) {
		this.tipoLeituraDto = tipoLeituraDto;
	}

	public String getTamanhoFonte() {
		return tamanhoFonte;
	}

	public void setTamanhoFonte(String tamanhoFonte) {
		this.tamanhoFonte = tamanhoFonte;
	}
	
	public List<RecursoDTO> getRecursosDto() {
		return recursosDto;
	}

	public void setRecursosDto(List<RecursoDTO> recursosDto) {
		this.recursosDto = recursosDto;
	}

	public SimNaoDTO getAtualmenteTrabalhandoDto() {
		return atualmenteTrabalhandoDto;
	}

	public void setAtualmenteTrabalhandoDto(SimNaoDTO atualmenteTrabalhandoDto) {
		this.atualmenteTrabalhandoDto = atualmenteTrabalhandoDto;
	}
	
	public SimNaoDTO getPossuiConsanguinidadeDto() {
		return possuiConsanguinidadeDto;
	}

	public void setPossuiConsanguinidadeDto(SimNaoDTO possuiConsanguinidadeDto) {
		this.possuiConsanguinidadeDto = possuiConsanguinidadeDto;
	}
		
	public List<InformacaoTrabalhoCompletaDTO> getInformacaoTrabalhoCompletaDto() {
		return informacaoTrabalhoCompletaDto;
	}

	public void setInformacaoTrabalhoCompletaDto(
			List<InformacaoTrabalhoCompletaDTO> informacaoTrabalhoCompletaDto) {
		this.informacaoTrabalhoCompletaDto = informacaoTrabalhoCompletaDto;
	}
	
	public String getRenda() {
		return renda;
	}

	public void setRenda(String renda) {
		this.renda = renda;
	}
	
	public String getRendaTotalFamiliar() {
		return rendaTotalFamiliar;
	}

	public void setRendaTotalFamiliar(String rendaTotalFamiliar) {
		this.rendaTotalFamiliar = rendaTotalFamiliar;
	}
	
	public String getCirurgias() {
		return cirurgias;
	}

	public void setCirurgias(String cirurgias) {
		this.cirurgias = cirurgias;
	}

	public String getMedicamentos() {
		return medicamentos;
	}

	public void setMedicamentos(String medicamentos) {
		this.medicamentos = medicamentos;
	}

	public String getConsanguinidade() {
		return consanguinidade;
	}

	public void setConsanguinidade(String consanguinidade) {
		this.consanguinidade = consanguinidade;
	}

	public List<CustoDTO> getCustosDoencaDto() {
		return custosDoencaDto;
	}

	public void setCustosDoencaDto(List<CustoDTO> custosDoencaDto) {
		this.custosDoencaDto = custosDoencaDto;
	}
	
	public List<CustoDTO> getCustosDeficienciaDto() {
		return custosDeficienciaDto;
	}

	public void setCustosDeficienciaDto(List<CustoDTO> custosDeficienciaDto) {
		this.custosDeficienciaDto = custosDeficienciaDto;
	}

	public StatusRelacaoComModuloDTO getStatusUsuarioAtualDto() {
		return statusUsuarioAtualDto;
	}

	public void setStatusUsuarioAtualDto(StatusRelacaoComModuloDTO statusUsuarioAtualDto) {
		this.statusUsuarioAtualDto = statusUsuarioAtualDto;
	}

	public boolean isFalecido() {
		return falecido;
	}

	public void setFalecido(boolean falecido) {
		this.falecido = falecido;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UsuarioDTO other = (UsuarioDTO) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return id + " - " + informacaoEssencialDto.getNome();
	}

}
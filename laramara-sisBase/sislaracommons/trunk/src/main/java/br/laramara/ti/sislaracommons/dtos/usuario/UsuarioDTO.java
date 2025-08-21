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
	private List<PeriodoComprometimentoDTO> periodoComprometimentoDto;
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
	private List<RecursoMoradiaDTO> recursosProximoAMoradia;
	private List<EncaminhamentoDTO> encaminhamentosDto;
	private List<ServicoDTO> servicosDto;
	private List<NecessidadeDTO> necessidadesDto;
	private List<ExpectativaDTO> expectativasDto;
	private CondicaoMoradiaDTO condicaoMoradiaDto;
	private SituacaoHabitacionalDTO situacaoHabitacionalDto;
	private HabitacaoDTO habitacaoDto;
	private TipoConstrucaoDTO tipoConstrucaoDto;
	private List<InfraestruturaBasicaDTO> infraestruturaBasicaDtos;
	private String historico;
	private String funcionalidade;
	private String reacaoFrenteADeficiencia;
	private String reacaoFrenteADeficienciaFamiliar;
	private String redeDeApoio;
	private String redeDeAmigos;
	private String namoroCasamentoSexualidade;
	private String necessidadesExpectativasQueixas;
	private String educacional;
	private String comunicacao;
	private String religiaoLazerCulturaRotina;
	private String parecer;
	private String resumoAtendimentosPsicossocial;
		
	public UsuarioDTO(){
		informacaoEssencialDto = new InformacaoEssencialDTO();
		informacoesEducacionaisDto = new ArrayList<>();
		familiaresDto = new ArrayList<>();
		situacoesGuardaDto = new ArrayList<>();
		periodoBeneficiosDto = new ArrayList<>();
		periodoDeficienciaDto = new ArrayList<>();
		periodoComprometimentoDto = new ArrayList<>();
		conveniosDto = new ArrayList<>();
		recursosDto = new ArrayList<>();
		informacaoTrabalhoCompletaDto = new ArrayList<>();
		custosDoencaDto = new ArrayList<>();
		custosDeficienciaDto = new ArrayList<>();
		recursosProximoAMoradia = new ArrayList<>();
		encaminhamentosDto = new ArrayList<>();
		servicosDto = new ArrayList<>();
		necessidadesDto = new ArrayList<>();
		expectativasDto = new ArrayList<>();
		infraestruturaBasicaDtos = new ArrayList<>();
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
	
	public List<EncaminhamentoDTO> getEncaminhamentosDto() {
		return encaminhamentosDto;
	}

	public void setEncaminhamentosDto(List<EncaminhamentoDTO> encaminhamentosDto) {
		this.encaminhamentosDto = encaminhamentosDto;
	}

	public List<ServicoDTO> getServicosDto() {
		return servicosDto;
	}

	public void setServicosDto(List<ServicoDTO> servicosDto) {
		this.servicosDto = servicosDto;
	}
	
	public List<NecessidadeDTO> getNecessidadesDto() {
		return necessidadesDto;
	}

	public void setNecessidadesDto(List<NecessidadeDTO> necessidadesDto) {
		this.necessidadesDto = necessidadesDto;
	}

	public List<ExpectativaDTO> getExpectativasDto() {
		return expectativasDto;
	}

	public void setExpectativasDto(List<ExpectativaDTO> expectativasDto) {
		this.expectativasDto = expectativasDto;
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
	
	public List<PeriodoComprometimentoDTO> getPeriodoComprometimentoDto() {
		return periodoComprometimentoDto;
	}

	public void setPeriodoComprometimentoDto(List<PeriodoComprometimentoDTO> periodoComprometimentoDto) {
		this.periodoComprometimentoDto = periodoComprometimentoDto;
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
	
	public List<RecursoMoradiaDTO> getRecursosProximoAMoradia() {
		return recursosProximoAMoradia;
	}

	public void setRecursosProximoAMoradia(List<RecursoMoradiaDTO> recursosProximoAMoradia) {
		this.recursosProximoAMoradia = recursosProximoAMoradia;
	}

	public CondicaoMoradiaDTO getCondicaoMoradiaDto() {
		return condicaoMoradiaDto;
	}

	public void setCondicaoMoradiaDto(CondicaoMoradiaDTO condicaoMoradiaDto) {
		this.condicaoMoradiaDto = condicaoMoradiaDto;
	}

	public SituacaoHabitacionalDTO getSituacaoHabitacionalDto() {
		return situacaoHabitacionalDto;
	}

	public void setSituacaoHabitacionalDto(SituacaoHabitacionalDTO situacaoHabitacionalDto) {
		this.situacaoHabitacionalDto = situacaoHabitacionalDto;
	}

	public HabitacaoDTO getHabitacaoDto() {
		return habitacaoDto;
	}

	public void setHabitacaoDto(HabitacaoDTO habitacaoDto) {
		this.habitacaoDto = habitacaoDto;
	}

	public TipoConstrucaoDTO getTipoConstrucaoDto() {
		return tipoConstrucaoDto;
	}

	public void setTipoConstrucaoDto(TipoConstrucaoDTO tipoConstrucaoDto) {
		this.tipoConstrucaoDto = tipoConstrucaoDto;
	}

	public List<InfraestruturaBasicaDTO> getInfraestruturaBasicaDtos() {
		return infraestruturaBasicaDtos;
	}

	public void setInfraestruturaBasicaDtos(List<InfraestruturaBasicaDTO> infraestruturaBasicaDtos) {
		this.infraestruturaBasicaDtos = infraestruturaBasicaDtos;
	}
	
	public String getHistorico() {
		return historico;
	}

	public void setHistorico(String historico) {
		this.historico = historico;
	}

	public String getFuncionalidade() {
		return funcionalidade;
	}

	public void setFuncionalidade(String funcionalidade) {
		this.funcionalidade = funcionalidade;
	}

	public String getReacaoFrenteADeficiencia() {
		return reacaoFrenteADeficiencia;
	}

	public void setReacaoFrenteADeficiencia(String reacaoFrenteADeficiencia) {
		this.reacaoFrenteADeficiencia = reacaoFrenteADeficiencia;
	}

	public String getReacaoFrenteADeficienciaFamiliar() {
		return reacaoFrenteADeficienciaFamiliar;
	}

	public void setReacaoFrenteADeficienciaFamiliar(String reacaoFrenteADeficienciaFamiliar) {
		this.reacaoFrenteADeficienciaFamiliar = reacaoFrenteADeficienciaFamiliar;
	}

	public String getRedeDeApoio() {
		return redeDeApoio;
	}

	public void setRedeDeApoio(String redeDeApoio) {
		this.redeDeApoio = redeDeApoio;
	}

	public String getRedeDeAmigos() {
		return redeDeAmigos;
	}

	public void setRedeDeAmigos(String redeDeAmigos) {
		this.redeDeAmigos = redeDeAmigos;
	}

	public String getNamoroCasamentoSexualidade() {
		return namoroCasamentoSexualidade;
	}

	public void setNamoroCasamentoSexualidade(String namoroCasamentoSexualidade) {
		this.namoroCasamentoSexualidade = namoroCasamentoSexualidade;
	}

	public String getNecessidadesExpectativasQueixas() {
		return necessidadesExpectativasQueixas;
	}

	public void setNecessidadesExpectativasQueixas(String necessidadesExpectativasQueixas) {
		this.necessidadesExpectativasQueixas = necessidadesExpectativasQueixas;
	}

	public String getEducacional() {
		return educacional;
	}

	public void setEducacional(String educacional) {
		this.educacional = educacional;
	}

	public String getComunicacao() {
		return comunicacao;
	}

	public void setComunicacao(String comunicacao) {
		this.comunicacao = comunicacao;
	}

	public String getReligiaoLazerCulturaRotina() {
		return religiaoLazerCulturaRotina;
	}

	public void setReligiaoLazerCulturaRotina(String religiaoLazerCulturaRotina) {
		this.religiaoLazerCulturaRotina = religiaoLazerCulturaRotina;
	}

	public String getParecer() {
		return parecer;
	}

	public void setParecer(String parecer) {
		this.parecer = parecer;
	}
	
	public String getResumoAtendimentosPsicossocial() {
		return resumoAtendimentosPsicossocial;
	}

	public void setResumoAtendimentosPsicossocial(String resumoAtendimentosPsicossocial) {
		this.resumoAtendimentosPsicossocial = resumoAtendimentosPsicossocial;
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
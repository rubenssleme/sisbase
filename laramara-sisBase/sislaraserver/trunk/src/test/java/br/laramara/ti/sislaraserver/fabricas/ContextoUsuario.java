package br.laramara.ti.sislaraserver.fabricas;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.laramara.ti.sislaracommons.dtos.ContatoDTO;
import br.laramara.ti.sislaracommons.dtos.InformacaoEssencialDTO;
import br.laramara.ti.sislaracommons.dtos.SimNaoDTO;
import br.laramara.ti.sislaracommons.dtos.TelefoneDTO;
import br.laramara.ti.sislaracommons.dtos.TipoTelefoneDTO;
import br.laramara.ti.sislaracommons.dtos.endereco.EnderecoDTO;
import br.laramara.ti.sislaracommons.dtos.endereco.MunicipioDTO;
import br.laramara.ti.sislaracommons.dtos.endereco.PaisDTO;
import br.laramara.ti.sislaracommons.dtos.endereco.UfDTO;
import br.laramara.ti.sislaracommons.dtos.endereco.ZonaDTO;
import br.laramara.ti.sislaracommons.dtos.escola.InformacaoEducacionalDTO;
import br.laramara.ti.sislaracommons.dtos.familiar.FamiliarDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.RecursoDTO;
import br.laramara.ti.sislaracommons.dtos.instituicao.InstituicaoDTO;
import br.laramara.ti.sislaracommons.dtos.trabalho.InformacaoTrabalhoCompletaDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.BeneficioDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.CertidaoDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.ClassificacaoSocialDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.ConvenioDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.EncaminhamentoDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.EstadoCivilDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.GeneroDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.PeriodoBeneficioDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.PeriodoDeficienciaDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.PeriodoDoencaDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.SituacaoGuardaDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.StatusBeneficioDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.StatusDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.TipoLeituraDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.UsuarioDTO;
import br.laramara.ti.sislaraserver.dominio.InformacaoEssencial;
import br.laramara.ti.sislaraserver.dominio.SimNao;
import br.laramara.ti.sislaraserver.dominio.endereco.Endereco;
import br.laramara.ti.sislaraserver.dominio.endereco.UF;
import br.laramara.ti.sislaraserver.dominio.endereco.Zona;
import br.laramara.ti.sislaraserver.dominio.escola.InformacaoEducacional;
import br.laramara.ti.sislaraserver.dominio.familiar.Familiar;
import br.laramara.ti.sislaraserver.dominio.grupo.Recurso;
import br.laramara.ti.sislaraserver.dominio.instituicao.Instituicao;
import br.laramara.ti.sislaraserver.dominio.trabalho.InformacaoTrabalhoCompleta;
import br.laramara.ti.sislaraserver.dominio.usuario.Certidao;
import br.laramara.ti.sislaraserver.dominio.usuario.ClassificacaoSocial;
import br.laramara.ti.sislaraserver.dominio.usuario.Convenio;
import br.laramara.ti.sislaraserver.dominio.usuario.Encaminhamento;
import br.laramara.ti.sislaraserver.dominio.usuario.EstadoCivil;
import br.laramara.ti.sislaraserver.dominio.usuario.Genero;
import br.laramara.ti.sislaraserver.dominio.usuario.PeriodoBeneficio;
import br.laramara.ti.sislaraserver.dominio.usuario.PeriodoDeficiencia;
import br.laramara.ti.sislaraserver.dominio.usuario.PeriodoDoenca;
import br.laramara.ti.sislaraserver.dominio.usuario.SituacaoGuarda;
import br.laramara.ti.sislaraserver.dominio.usuario.Status;
import br.laramara.ti.sislaraserver.dominio.usuario.TipoLeitura;
import br.laramara.ti.sislaraserver.dominio.usuario.Usuario;

public class ContextoUsuario {

	public static UsuarioDTO construirUsuarioDTOComSRMseSalaRecursoEOutrosAEE() {
		UsuarioDTO usuarioDTO = construirUsuarioDTO();
		usuarioDTO.setInstituicaoComSalaRecursoDto(usuarioDTO
				.getInstituicaoComSRMsDto());
		usuarioDTO.setInstituicaoComOutrosAEE(usuarioDTO
				.getInstituicaoComSRMsDto());
		return usuarioDTO;
	}

	public static UsuarioDTO construirUsuarioDTOComIdentificacao() {
		UsuarioDTO usuarioDto = construirUsuarioDTO();
		usuarioDto.setId(new Long(12222));
		return usuarioDto;
	}
	
	public static UsuarioDTO construirUsuarioDTOComIdentificacaoAlternativoC() {
		UsuarioDTO usuarioDto = construirUsuarioDTO();
		usuarioDto.setId(new Long(17777));
		return usuarioDto;
	}
	
	public static UsuarioDTO construirUsuarioDTOComIdentificacaoAlternativoA() {
		UsuarioDTO usuarioDto = construirUsuarioDTO();
		usuarioDto.setId(new Long(72222));
		return usuarioDto;
	}
	
	public static UsuarioDTO construirUsuarioDTOComIdentificacaoAlternativoB() {
		UsuarioDTO usuarioDto = construirUsuarioDTO();
		usuarioDto.setId(new Long(82222));
		return usuarioDto;
	}

	public static UsuarioDTO construirUsuarioDTO() {
		String texto = "Texto de exemplo.";
		List<SituacaoGuardaDTO> situacoesGuardaDto = new ArrayList<>();
		situacoesGuardaDto.add(new SituacaoGuardaDTO(new Long(1), "Adoção"));

		PeriodoBeneficioDTO periodoBeneficioDTO = new PeriodoBeneficioDTO();
		periodoBeneficioDTO.setBeneficioDto(new BeneficioDTO(new Long(1),
				"Aposentado por Invalidez"));
		periodoBeneficioDTO.setDataInicial("01/01/2011");
		periodoBeneficioDTO.setDataFinal("02/01/2011");
		periodoBeneficioDTO.setStatusDto(new StatusBeneficioDTO("SIM"));

		List<PeriodoBeneficioDTO> periodoBeneficiosDto = new ArrayList<>();
		periodoBeneficiosDto.add(periodoBeneficioDTO);
		
		List<PeriodoDeficienciaDTO> periodoDeficienciasDto = new ArrayList<>();
		periodoDeficienciasDto.add(ContextoPeriodoDeficiencia.construirPeriodoDeficienciaDTO());
		
		List<PeriodoDoencaDTO> periodoDoencasDto = new ArrayList<>();
		periodoDoencasDto.add(ContextoPeriodoDoenca.construirPeriodoDoencaDTO());

		List<ConvenioDTO> conveniosDto = new ArrayList<>();
		conveniosDto.add(new ConvenioDTO(new Long(1), "SESC"));

		List<EncaminhamentoDTO> encaminhamentosDTO = new ArrayList<>();
		encaminhamentosDTO.add(ContextoEncaminhamento.fabricarEncaminhamentoDto());
		
		EnderecoDTO enderecoDto = new EnderecoDTO();
		enderecoDto.setCep("01151000");
		enderecoDto.setEndereco("Rua Brigadeiro Galvão");
		enderecoDto.setNumero("344A");
		enderecoDto.setComplemento("AP444");
		enderecoDto.setZonaDto(new ZonaDTO(Zona.LESTE.toString()));
		enderecoDto.setBairro("Marambaia");
		enderecoDto.setMunicipioDto(new MunicipioDTO(new Long(4850), "São Paulo",
				new UfDTO("SP")));
		enderecoDto.setUfDto(new UfDTO(UF.SP.toString()));
		enderecoDto.setPaisDto(new PaisDTO(new Long(1), "Brasil"));

		List<TelefoneDTO> telefonesDto = new ArrayList<>();
		telefonesDto.add(new TelefoneDTO(new TipoTelefoneDTO("RESIDENCIAL"),
				"1187433443"));
		telefonesDto.add(new TelefoneDTO(new TipoTelefoneDTO("CELULAR"),
				"1193483434"));
		telefonesDto.add(new TelefoneDTO(new TipoTelefoneDTO("CONTATO"),
				"1127332323"));

		ContatoDTO contatoDto = new ContatoDTO();
		contatoDto.setTelefonesDto(telefonesDto);
		contatoDto.setRamal("3232");
		contatoDto.setNomeContato("Tereza Cristina");
		contatoDto.setEmail("paulo_bandeira2007@yahoo.com.br");

		InformacaoEssencialDTO informacaoEssencialDto = new InformacaoEssencialDTO();
		informacaoEssencialDto.setNome("Paulo");
		informacaoEssencialDto.setDataNascimento("27/07/1982");
		informacaoEssencialDto.setRg("2323232");
		informacaoEssencialDto.setContatoDto(contatoDto);
		informacaoEssencialDto.setEnderecoDto(enderecoDto);
		
		SimNaoDTO simNaoDto = new SimNaoDTO("SIM");
		
		UsuarioDTO usuarioDto = new UsuarioDTO();
		usuarioDto.setInformacaoEssencialDto(informacaoEssencialDto);
		usuarioDto.setClassificacaoSocialDto(new ClassificacaoSocialDTO(
				ClassificacaoSocial.I.toString()));
		usuarioDto.setStatusDto(new StatusDTO(Status.DESISTENTE.toString()));
		usuarioDto.setGeneroDto(new GeneroDTO(Genero.MASCULINO.toString()));
		usuarioDto.setNaturalidade("Belém");
		usuarioDto.setNacionalidade("Italiana");
		usuarioDto.setUfRgDto(new UfDTO(UF.AL.toString()));
		usuarioDto.setDataExpedicaoRg("31/12/1982");
		usuarioDto.setOrgaoEmissorRg("SSP-SP");
		usuarioDto.setEstadoCivilDto(new EstadoCivilDTO(new Long(1), "CASADO"));
		usuarioDto.setAssociadoAoSetorProceja(true);
		usuarioDto.setAssociadoAoSetorCTO(true);
		usuarioDto.setNaoAlfabetizado(true);
		usuarioDto.setNaoEstaNaEscola(true);
		usuarioDto.setSituacoesGuardaDto(situacoesGuardaDto);
		usuarioDto.setPeriodoBeneficiosDto(periodoBeneficiosDto);
		usuarioDto.setPeriodoDeficienciaDto(periodoDeficienciasDto);
		usuarioDto.setPeriodoDoencasDto(periodoDoencasDto);
		usuarioDto.setConveniosDto(conveniosDto);
		usuarioDto.setFoto(new byte[12]);
		usuarioDto.setObs("Texto de observação");
		usuarioDto.setOutrosApoiosServicos("Outro apoio");
		usuarioDto.setTamanhoFonte("23 Verdana");
		usuarioDto.setTipoLeituraDto(new TipoLeituraDTO("AMPLIADO"));
		usuarioDto.setAtualmenteTrabalhandoDto(simNaoDto);
		usuarioDto.setRenda("20,00");
		usuarioDto.setPossuiConsanguinidadeDto(simNaoDto);
		usuarioDto.setHistorico(texto);
		usuarioDto.setEncaminhamentosDto(encaminhamentosDTO);
		usuarioDto.setFuncionalidade(texto);
		usuarioDto.setReacaoFrenteADeficiencia(texto);
		usuarioDto.setReacaoFrenteADeficienciaFamiliar(texto);
		usuarioDto.setRedeDeApoio(texto);
		usuarioDto.setRedeDeAmigos(texto);
		usuarioDto.setNamoroCasamentoSexualidade(texto);	
		usuarioDto.setNecessidadesExpectativasQueixas(texto);
		usuarioDto.setEducacional(texto);
		usuarioDto.setComunicacao(texto);
		usuarioDto.setReligiaoLazerCulturaRotina(texto);
		usuarioDto.setParecer(texto);
		
		InformacaoEducacionalDTO informacaoEducacionalDTO = ContextoInformacaoEducacional
				.construirInformacaoEducacionalDTO();
		List<InformacaoEducacionalDTO> informacoesEscolaresDTO = new ArrayList<>();
		informacoesEscolaresDTO.add(informacaoEducacionalDTO);
		usuarioDto.setInformacaoEducacionaisDto(informacoesEscolaresDTO);

		InstituicaoDTO instituicaoDTOComSRMS = new InstituicaoDTO();
		instituicaoDTOComSRMS.setId(new Long(133333));
		usuarioDto.setInstituicaoComSRMSDto(instituicaoDTOComSRMS);

		List<FamiliarDTO> familiaresDto = new ArrayList<>();
		familiaresDto.add(ContextoFamiliar
				.construirFamiliarDTOSemIdentificacao());
		FamiliarDTO familiarDtoNaoResponsavel = ContextoFamiliar
				.construirFamiliarDTOSemIdentificacao();
		familiarDtoNaoResponsavel.setPrincipalResponsavel(false);
		familiaresDto.add(familiarDtoNaoResponsavel);
		usuarioDto.setFamiliaresDto(familiaresDto);

		List<CertidaoDTO> certidoes = new ArrayList<>();
		certidoes.add(ContextoCertidao.fabricarCertidaoDTOComTodosOsDados());
		usuarioDto.setCertidoes(certidoes);
		
		List<RecursoDTO> recursosDto = new ArrayList<>();
		recursosDto.add(new RecursoDTO(new Long(100), "BENGALA", false, "80,00"));
		usuarioDto.setRecursosDto(recursosDto);
		
		List<InformacaoTrabalhoCompletaDTO> informacaoTrabalhoCompletaDto = new ArrayList<>();
		informacaoTrabalhoCompletaDto.add(ContextoInformacaoTrabalhoCompleta.fabricarInformacaoTrabalhoDTOCompletaComTodosOsDados());
		usuarioDto.setInformacaoTrabalhoCompletaDto(informacaoTrabalhoCompletaDto);
		
		usuarioDto.setCirurgias("Cirurgias");
		usuarioDto.setMedicamentos("Medicamentos");
		usuarioDto.setConsanguinidade("Consanguinidade");
		
		usuarioDto.setFalecido(true);
		usuarioDto.setRecursosProximoAMoradia(Arrays.asList(ContextoRecursoMoradia.construirRecursoMoradiaDTO()));
		usuarioDto.setServicosDto(Arrays.asList(ContextoServico.construirServicoDTO()));
		usuarioDto.setNecessidadesDto(Arrays.asList(ContextoNecessidade.construirNecessidadeDTO()));
		usuarioDto.setExpectativasDto(Arrays.asList(ContextoExpectativa.construirExpectativaDTO()));
		
		usuarioDto.setCondicaoMoradiaDto(ContextoCondicaoMoradia.contruirCondicaoMoradiaDto());
		usuarioDto.setSituacaoHabitacionalDto(ContextoSituacaoHabitacional.construirSituacaoHabitacionalDto());
		usuarioDto.setHabitacaoDto(ContextoHabitacao.construirhabitacaoDto());
		usuarioDto.setTipoConstrucaoDto(ContextoTipoConstrucao.construirTipoConstrucao());
		usuarioDto.setInfraestruturaBasicaDtos(ContextoInfraestruturaBasica.construirInfraestruturaBasica());
		return usuarioDto;
	}

	public static Usuario fabricarUsuarioComTodosOsDadosESRMseSalaDeRecursoEOutrosAEE() {
		Usuario usuario = fabricarUsuarioComTodosOsDados();
		usuario.setInstituicaoComSalaRecurso(usuario.getInstituicaoComSRMs());
		usuario.setInstituicaoComOutrosAEE(usuario.getInstituicaoComSRMs());
		return usuario;
	}

	public static Usuario fabricarUsuarioComTodosOsDados() {
		Usuario usuario = fabricarUsuarioSemClassificacaoSocial();
		usuario.adicionarClassificacaoSocial(ClassificacaoSocial.I);
		return usuario;
	}

	public static Usuario fabricaUsuarioComTodosOsDadosEProntuario() {
		Usuario usuario = fabricarUsuarioComTodosOsDados();
		usuario.setId(new Long(92222));
		return usuario;
	}

	public static Usuario fabricaUsuarioComTodosOsDadosEProntuarioAlternativo() {
		Usuario usuario = fabricarUsuarioComTodosOsDados();
		usuario.setId(new Long(12222));
		return usuario;
	}

	public static Usuario fabricarUsuarioSemClassificacaoSocial() {
		String texto = "Texto de obs.";
		InformacaoEssencial informacaoEssencial = ContextoInformacaoEssencial
				.fabricarInformacaoEssencialComTodosOsDados();
		informacaoEssencial.adicionarRg("98429847AX");

		List<SituacaoGuarda> situacoesGuarda = new ArrayList<>();
		situacoesGuarda.add(new SituacaoGuarda(new Long(1), "Adoção"));

		List<PeriodoBeneficio> periodoBeneficios = new ArrayList<>();
		periodoBeneficios.add(ContextoPeriodoBeneficio
				.construirComTodosOsDados());

		List<PeriodoDeficiencia> periodoDeficiencias = new ArrayList<>();
		periodoDeficiencias.add(ContextoPeriodoDeficiencia
				.construirComTodosOsDados());
		
		List<PeriodoDoenca> periodoDoenca = new ArrayList<>();
		periodoDoenca.add(ContextoPeriodoDoenca.construirComTodosOsDados());

		List<Convenio> convenios = new ArrayList<>();
		convenios.add(new Convenio(new Long(1), "SESC"));
		
		List<Encaminhamento> encaminhamentos = new ArrayList<>();
		encaminhamentos.add(ContextoEncaminhamento.fabricarEncaminhamento());

		Usuario usuario = new Usuario();
		usuario.setVersao("1233");
		usuario.setInformacaoEssencial(informacaoEssencial);
		usuario.adicionarStatus(Status.FALECIDO);
		usuario.setGenero(Genero.MASCULINO);
		usuario.setDataNascimento("27/07/1982");
		usuario.setNaturalidade("Belém");
		usuario.setNacionalidade("Italiana");
		usuario.setUfRg(UF.PA);
		usuario.setDataExpedicaoRg("31/12/2001");
		usuario.setOrgaoEmissorRg("SSP-SP");
		usuario.setEstadoCivil(new EstadoCivil(new Long(1), "SOLTEIRO"));
		usuario.setNaoAlfabetizado(true);
		usuario.setNaoEstaNaEscola(true);
		usuario.associarAoSetorProceja(true);
		usuario.setSituacoesGuarda(situacoesGuarda);
		usuario.setPeriodoBeneficios(periodoBeneficios);
		usuario.setPeriodoDeficiencias(periodoDeficiencias);
		usuario.setPeriodoDoencas(periodoDoenca);
		usuario.setConvenios(convenios);
		usuario.setFoto(new byte[12]);
		usuario.setObs("Texto de observação");
		usuario.setOutrosApoiosServicos("Outros Apoios");
		usuario.setTipoLeitura(TipoLeitura.AMPLIADO);
		usuario.setTamanhoFonte("29 Verdana");
		usuario.setAtualmenteTrabalhando(SimNao.SIM);
		usuario.setRenda("20,00");
		usuario.setPossuiConsanguinidade(SimNao.SIM);
		usuario.setEncaminhamentos(encaminhamentos);
		usuario.setHistorico(texto);
		usuario.setFuncionalidade(texto);
		usuario.setReacaoFrenteADeficiencia(texto);
		usuario.setReacaoFrenteADeficienciaFamiliar(texto);
		usuario.setRedeDeApoio(texto);
		usuario.setRedeDeAmigos(texto);
		usuario.setNamoroCasamentoSexualidade(texto);	
		usuario.setNecessidadesExpectativasQueixas(texto);
		usuario.setEducacional(texto);
		usuario.setComunicacao(texto);
		usuario.setReligiaoLazerCulturaRotina(texto);
		usuario.setParecer(texto);
		
		Endereco endereco = ContextoEndereco.fabricarEnderecoComTodosOsDados();
		usuario.getInformacaoEssencial().adicionarEndereco(endereco);

		List<InformacaoEducacional> informacoesEscolares = new ArrayList<>();
		informacoesEscolares.add(ContextoInformacaoEducacional
				.fabricarInformacaoEscolarComTodosOsDados());
		usuario.adicionarInformacoesEducacionais(informacoesEscolares);

		Instituicao instituicaoComSRMSeSalaRecurso = ContextoInstituicao
				.fabricarInstituicaoComTodosOsDados();
		usuario.setInstituicaoComSRMs(instituicaoComSRMSeSalaRecurso);

		Familiar familiarResponsavel = ContextoFamiliar
				.fabricarFamiliarComTodosOsDados();
		Familiar familiarNaoResponsavel = ContextoFamiliar
				.fabricarFamiliarNaoResponsavelApenasComDadosObrigatorios();
		List<Familiar> familiares = new ArrayList<>();
		familiares.add(familiarResponsavel);
		familiares.add(familiarNaoResponsavel);

		usuario.setFamiliares(familiares);
		
		List<Certidao> certidoes = new ArrayList<>();
		certidoes.add(ContextoCertidao.fabricarCertidaoComTodosOsDados());
		usuario.setCertidoes(certidoes);
		
		List<Recurso> recursos = new ArrayList<>();
		recursos.add(new Recurso(new Long(100), "BENGALA", false, "80,00"));
		usuario.setRecursos(recursos);
		
		List<InformacaoTrabalhoCompleta> informacaoTrabalhoCompleto = new ArrayList<>();
		informacaoTrabalhoCompleto.add(ContextoInformacaoTrabalhoCompleta
				.fabricarInformacaoTrabalhoCompletaComTodosOsDados());
		usuario.setInformacoesTrabalhoCompleta(informacaoTrabalhoCompleto);
		
		usuario.setCirurgias("Cirurgias");
		usuario.setMedicamentos("Medicamentos");
		usuario.setConsanguinidade("Consanguinidade");
		usuario.setRecursosProximoAMoradia(Arrays.asList(ContextoRecursoMoradia.fabricarRecursoMoradiaComTodosOsDados()));
		usuario.setServicos(Arrays.asList(ContextoServico.fabricarServicoComTodosOsDados()));
		usuario.setNecessidades(Arrays.asList(ContextoNecessidade.fabricarNecessidadeComTodosOsDados()));
		usuario.setExpectativas(Arrays.asList(ContextoExpectativa.fabricarExpectativaComTodosOsDados()));
		
		usuario.setCondicaoMoradia(ContextoCondicaoMoradia.fabricarCondicaoMoradia());
		usuario.setSituacaoHabitacional(ContextoSituacaoHabitacional.fabricarSituacaoHabitacional());
		usuario.setHabitacao(ContextoHabitacao.fabricarHabitacao());
		usuario.setTipoConstrucao(ContextoTipoConstrucao.fabricarTipoConstrucao());
		usuario.setInfraestruturaBasica(ContextoInfraestruturaBasica.fabricarInfraestruturaBasica());
		usuario.setPeriodoComprometimentos(Arrays.asList(ContextoPeriodoComprometimento.construirPeriodoComprometimento()));
		return usuario;
	}

}

package br.laramara.ti.sislaraserver.fabricas;

import br.laramara.ti.sislaracommons.dtos.usuario.UsuarioDTO;
import br.laramara.ti.sislaraserver.dominio.usuario.Usuario;

public class FabricaUsuario extends FabricaRecursiva<UsuarioDTO, Usuario> {

	@Override
	public final Usuario converterParaDominio(UsuarioDTO usuarioDto,
			Usuario usuario) {
		if (usuarioDto != null) {
			if (usuario == null) {
				usuario = new Usuario();
			}
			usuario.setId(usuarioDto.getId());
			usuario.setInformacaoEssencial(new FabricaInformacaoEssencial()
					.converterParaDominio(
							usuarioDto.getInformacaoEssencialDto(),
							usuario.getInformacaoEssencial()));
			usuario.adicionarClassificacaoSocial(new FabricaClassificacaoSocial()
					.converterParaDominio(usuarioDto
							.getClassificacaoSocialDto()));
			usuario.setGenero(new FabricaGenero()
					.converterParaDominio(usuarioDto.getGeneroDto()));
			usuario.setNaturalidade(usuarioDto.getNaturalidade());
			usuario.setNacionalidade(usuarioDto.getNacionalidade());
			usuario.setUfRg(new FabricaUf().converterParaDominio(usuarioDto
					.getUfRgDto()));
			usuario.setDataExpedicaoRg(usuarioDto.getDataExpedicaoRg());
			usuario.setOrgaoEmissorRg(usuarioDto.getOrgaoEmissorRg());
			usuario.setEstadoCivil(new FabricaEstadoCivil()
					.converterParaDominio(usuarioDto.getEstadoCivilDto()));
			usuario.setGrupoEtnico(new FabricaGrupoEtnico()
					.converterParaDominio(usuarioDto.getGrupoEtnicoDto()));
			usuario.setResponsavelPorSiMesmo(usuarioDto
					.isResponsavelPorSiMesmo());
			usuario.associarAoSetorCTO(usuarioDto.isAssociadoAoSetorCTO());
			usuario.associarAoSetorProceja(usuarioDto
					.isAssociadoAoSetorProceja());
			usuario.setSituacoesGuarda(new FabricaSituacaoGuarda()
					.converterParaDominio(usuarioDto.getSituacoesGuardaDto()));
			usuario.setPeriodoBeneficios(new FabricaPeriodoBeneficio()
					.converterParaDominio(usuarioDto.getPeriodoBeneficiosDto()));
			usuario.setPeriodoComprometimentos(new FabricaPeriodoComprometimento()
					.converterParaDominio(usuarioDto.getPeriodoComprometimentoDto()));
			usuario.setPeriodoDeficiencias(new FabricaPeriodoDeficiencia()
					.converterParaDominio(usuarioDto.getPeriodoDeficienciaDto()));
			usuario.setPeriodoDoencas(new FabricaPeriodoDoenca()
					.converterParaDominio(usuarioDto.getPeriodoDoencasDto()));
			usuario.setCustosDoenca(new FabricaCusto()
					.converterParaDominio(usuarioDto.getCustosDoencaDto()));
			usuario.setCustosDeficiencia(new FabricaCusto()
					.converterParaDominio(usuarioDto.getCustosDeficienciaDto()));
			usuario.setConvenios(new FabricaConvenio()
					.converterParaDominio(usuarioDto.getConveniosDto()));
			usuario.setVulnerabilidadeUsuario(
					new FabricaVulnerabilidade().converterParaDominio(usuarioDto.getVulnerabilidadeUsuarioDto()));
			usuario.setFoto(usuarioDto.getFoto());
			usuario.setObs(usuarioDto.getObs());
			usuario.adicionarInformacoesEducacionais(new FabricaInformacaoEducacional()
					.converterParaDominio(usuarioDto
							.getInformacoesEducacionaisDto()));
			usuario.setInstituicaoComSRMs(new FabricaInstituicao()
					.converterParaDominio(usuarioDto.getInstituicaoComSRMsDto()));
			usuario.setInstituicaoComSalaRecurso(new FabricaInstituicao()
					.converterParaDominio(usuarioDto
							.getInstituicaoComSalaRecursoDto()));
			usuario.setInstituicaoComOutrosAEE(new FabricaInstituicao()
					.converterParaDominio(usuarioDto
							.getInstituicaoComOutrosAEE()));
			usuario.setFamiliares(new FabricaFamiliar().converterParaDominio(
					usuarioDto.getFamiliaresDto(), usuario.getFamiliares()));
			usuario.setOutrosApoiosServicos(usuarioDto
					.getOutrosApoiosServicos());
			usuario.setCertidoes(new FabricaCertidao()
					.converterParaDominio(usuarioDto.getCertidoes()));
			usuario.setTipoLeitura(new FabricaTipoLeitura()
					.converterParaDominio(usuarioDto.getTipoLeituraDto()));
			usuario.setTamanhoFonte(usuarioDto.getTamanhoFonte());
			usuario.setRecursosRelacionamentos(new FabricaRecursoRelacionamento()
					.converterParaDominio(usuarioDto.getRecursosRelacionamentosDto()));
			usuario.setAtualmenteTrabalhando(new FabricaSimNao()
					.converterParaDominio(usuarioDto
							.getAtualmenteTrabalhandoDto()));
			usuario.setPossuiConsanguinidade(new FabricaSimNao()
					.converterParaDominio(usuarioDto
							.getPossuiConsanguinidadeDto()));
			usuario.setInformacoesTrabalhoCompleta(new FabricaInformacaoTrabalhoCompleta()
					.converterParaDominio(usuarioDto
							.getInformacaoTrabalhoCompletaDto()));
			usuario.setRenda(usuarioDto.getRenda());
			usuario.setMedicamentos(usuarioDto.getMedicamentos());
			usuario.setCirurgias(usuarioDto.getCirurgias());
			usuario.setConsanguinidade(usuarioDto.getConsanguinidade());
			usuario.setFalecido(usuarioDto.isFalecido());
			usuario.setOrigemEncaminhamentosDetalhados(
					new FabricaOrigemEncaminhamentoDetalhado().converterParaDominio(usuarioDto.getOrigemEncaminhamentosDetalhadosDto()));
			usuario.setEncaminhamentos(
					new FabricaEncaminhamento().converterParaDominio(usuarioDto.getEncaminhamentosDto()));
			usuario.setServicos(new FabricaServico().converterParaDominio(usuarioDto.getServicosDto()));
			usuario.setRecursosProximoAMoradia(
					new FabricaRecursoMoradia().converterParaDominio(usuarioDto.getRecursosProximoAMoradia()));
			usuario.setNecessidades(new FabricaNecessidade().converterParaDominio(usuarioDto.getNecessidadesDto()));
			usuario.setExpectativas(new FabricaExpectativa().converterParaDominio(usuarioDto.getExpectativasDto()));
			usuario.setCondicaoMoradia(new FabricaCondicaoMoradia().converterParaDominio(usuarioDto.getCondicaoMoradiaDto()));
			usuario.setSituacaoHabitacional(new FabricaSituacaoHabitacional().converterParaDominio(usuarioDto.getSituacaoHabitacionalDto()));
			usuario.setHabitacao(new FabricaHabitacao().converterParaDominio(usuarioDto.getHabitacaoDto()));
			usuario.setTipoConstrucao(new FabricaTipoConstrucao().converterParaDominio(usuarioDto.getTipoConstrucaoDto()));
			usuario.setInfraestruturaBasica(new FabricaInfraestruturaBasica().converterParaDominio(usuarioDto.getInfraestruturaBasicaDtos()));
			usuario.setHistorico(usuarioDto.getHistorico());
			usuario.setFuncionalidade(usuarioDto.getFuncionalidade());
			usuario.setReacaoFrenteADeficiencia(usuarioDto.getReacaoFrenteADeficiencia());
			usuario.setReacaoFrenteADeficienciaFamiliar(usuarioDto.getReacaoFrenteADeficienciaFamiliar());
			usuario.setRedeDeApoio(usuarioDto.getRedeDeApoio());
			usuario.setRedeDeAmigos(usuarioDto.getRedeDeAmigos());
			usuario.setNamoroCasamentoSexualidade(usuarioDto.getNamoroCasamentoSexualidade());	
			usuario.setNecessidadesExpectativasQueixas(usuarioDto.getNecessidadesExpectativasQueixas());
			usuario.setEducacional(usuarioDto.getEducacional());
			usuario.setComunicacao(usuarioDto.getComunicacao());
			usuario.setReligiaoLazerCulturaRotina(usuarioDto.getReligiaoLazerCulturaRotina());
			usuario.setParecer(usuarioDto.getParecer());
		}
		return usuario;
	}
	
	public final synchronized UsuarioDTO converterParaDTO(Usuario usuario) {
		UsuarioDTO usuarioDto = null;
		if (usuario != null) {
			usuarioDto = new UsuarioDTO();
			usuarioDto.setId(usuario.getId());
			usuarioDto.setVersao(usuario.getVersao());
			usuarioDto.setDataCadastro(usuario.getDataCadastro());
			usuarioDto
					.setClassificacaoSocialDto(new FabricaClassificacaoSocial()
							.converterParaDTO(usuario.getClassificacaoSocial()));
			usuarioDto
					.setInformacaoEssencialDto(new FabricaInformacaoEssencial()
							.converterParaDTO(usuario.getInformacaoEssencial()));
			usuarioDto.setStatusUsuarioAtualDto(new FabricaStatusRelacaoComModulo()
					.converterParaDTO(usuario.getStatusUsuarioAtual()));
			usuarioDto.setGeneroDto(new FabricaGenero()
					.converterParaDTO(usuario.getGenero()));
			usuarioDto.setNaturalidade(usuario.getNaturalidade());
			usuarioDto.setNacionalidade(usuario.getNacionalidade());
			usuarioDto.setUfRgDto(new FabricaUf().converterParaDTO(usuario
					.getUfRg()));
			usuarioDto.setDataExpedicaoRg(usuario.getDataExpedicaoRg());
			usuarioDto.setOrgaoEmissorRg(usuario.getOrgaoEmissorRg());
			usuarioDto.setEstadoCivilDto(new FabricaEstadoCivil()
					.converterParaDTO(usuario.getEstadoCivil()));
			usuarioDto.setGrupoEtnicoDto(new FabricaGrupoEtnico()
					.converterParaDTO(usuario.getGrupoEtnico()));
			usuarioDto.setAssociadoAoSetorCTO(usuario.possuiVigenciaAtivaCTO());
			usuarioDto.setAssociadoAoSetorProceja(usuario
					.possuiVigenciaAtivaProceja());
			usuarioDto.setSituacoesGuardaDto(new FabricaSituacaoGuarda()
					.converterParaDTO(usuario.getSituacoesGuarda()));
			usuarioDto.setResponsavelPorSiMesmo(usuario
					.isResponsavelPorSiMesmo());
			usuarioDto.setPeriodoBeneficiosDto(new FabricaPeriodoBeneficio()
					.converterParaDTO(usuario.getPeriodoBeneficios()));
			usuarioDto.setPeriodoDeficienciaDto(new FabricaPeriodoDeficiencia()
					.converterParaDTO(usuario.getPeriodoDeficiencias()));
			usuarioDto.setPeriodoComprometimentoDto(
					new FabricaPeriodoComprometimento().converterParaDTO(usuario.getPeriodoComprometimentos()));
			usuarioDto.setPeriodoDoencasDto(new FabricaPeriodoDoenca()
					.converterParaDTO(usuario.getPeriodoDoencas()));
			usuarioDto.setCustosDoencaDto(new FabricaCusto()
					.converterParaDTO(usuario.getCustosDoenca()));
			usuarioDto.setCustosDeficienciaDto(new FabricaCusto()
					.converterParaDTO(usuario.getCustosDeficiencia()));
			usuarioDto.setConveniosDto(new FabricaConvenio()
					.converterParaDTO(usuario.getConvenios()));
			usuarioDto.setFoto(usuario.getFoto());
			usuarioDto.setVulnerabilidadeUsuarioDto(
					new FabricaVulnerabilidade().converterParaDTO(usuario.getVulnerabilidadeUsuario()));
			usuarioDto.setObs(usuario.getObs());
			usuarioDto
					.setInformacaoEducacionaisDto(new FabricaInformacaoEducacional()
							.converterParaDTO(usuario
									.obterInformacoesEducacionais()));
			usuarioDto.setInstituicaoComSRMSDto(new FabricaInstituicao()
					.converterParaDTO(usuario.getInstituicaoComSRMs()));
			usuarioDto.setInstituicaoComSalaRecursoDto(new FabricaInstituicao()
					.converterParaDTO(usuario.getInstituicaoComSalaRecurso()));
			usuarioDto.setInstituicaoComOutrosAEE(new FabricaInstituicao()
					.converterParaDTO(usuario.getInstituicaoComOutrosAEE()));
			usuarioDto.setFamiliaresDto(new FabricaFamiliar()
					.converterParaDTO(usuario.getFamiliares()));
			usuarioDto.setOutrosApoiosServicos(usuario
					.getOutrosApoiosServicos());
			usuarioDto.setCertidoes(new FabricaCertidao()
					.converterParaDTO(usuario.getCertidoes()));
			usuarioDto.setTipoLeituraDto(new FabricaTipoLeitura()
					.converterParaDTO(usuario.getTipoLeitura()));
			usuarioDto.setTamanhoFonte(usuario.getTamanhoFonte());
			usuarioDto.setRecursosRelacionamentoDto(new FabricaRecursoRelacionamento()
					.converterParaDTO(usuario.getRecursosRelacionamentos()));
			usuarioDto.setAtualmenteTrabalhandoDto(new FabricaSimNao()
					.converterParaDTO(usuario.getAtualmenteTrabalhando()));
			usuarioDto.setPossuiConsanguinidadeDto(new FabricaSimNao()
					.converterParaDTO(usuario.possuiConsanguinidade()));
			usuarioDto
					.setInformacaoTrabalhoCompletaDto(new FabricaInformacaoTrabalhoCompleta()
							.converterParaDTO(usuario
									.getInformacoesTrabalhoCompleta()));
			usuarioDto.setRenda(usuario.getRenda());
			usuarioDto.setRendaTotalFamiliar(usuario.obterRendaTotalFamiliar());
			usuarioDto.setMedicamentos(usuario.getMedicamentos());
			usuarioDto.setCirurgias(usuario.getCirurgias());
			usuarioDto.setConsanguinidade(usuario.getConsanguinidade());
			usuarioDto.setFalecido(usuario.isFalecido());
			usuarioDto
					.setOrigemEncaminhamentosDetalhadosDto(new FabricaOrigemEncaminhamentoDetalhado().converterParaDTO(usuario.getOrigemEncaminhamentosDetalhados()));
			usuarioDto.setEncaminhamentosDto(new FabricaEncaminhamento().converterParaDTO(usuario.getEncaminhamentos()));
			usuarioDto.setServicosDto(new FabricaServico().converterParaDTO(usuario.getServicos()));
			usuarioDto.setRecursosProximoAMoradia(
					new FabricaRecursoMoradia().converterParaDTO(usuario.getRecursosProximoAMoradia()));
			usuarioDto.setNecessidadesDto(new FabricaNecessidade().converterParaDTO(usuario.getNecessidades()));
			usuarioDto.setExpectativasDto(new FabricaExpectativa().converterParaDTO(usuario.getExpectativas()));
			usuarioDto.setCondicaoMoradiaDto(new FabricaCondicaoMoradia().converterParaDTO(usuario.getCondicaoMoradia()));
			usuarioDto.setSituacaoHabitacionalDto(new FabricaSituacaoHabitacional().converterParaDTO(usuario.getSituacaoHabitacional()));
			usuarioDto.setHabitacaoDto(new FabricaHabitacao().converterParaDTO(usuario.getHabitacao()));
			usuarioDto.setTipoConstrucaoDto(new FabricaTipoConstrucao().converterParaDTO(usuario.getTipoConstrucao()));
			usuarioDto.setInfraestruturaBasicaDtos(new FabricaInfraestruturaBasica().converterParaDTO(usuario.getInfraestruturaBasica()));
			usuarioDto.setHistorico(usuario.getHistorico());
			usuarioDto.setFuncionalidade(usuario.getFuncionalidade());
			usuarioDto.setReacaoFrenteADeficiencia(usuario.getReacaoFrenteADeficiencia());
			usuarioDto.setReacaoFrenteADeficienciaFamiliar(usuario.getReacaoFrenteADeficienciaFamiliar());
			usuarioDto.setRedeDeApoio(usuario.getRedeDeApoio());
			usuarioDto.setRedeDeAmigos(usuario.getRedeDeAmigos());
			usuarioDto.setNamoroCasamentoSexualidade(usuario.getNamoroCasamentoSexualidade());	
			usuarioDto.setNecessidadesExpectativasQueixas(usuario.getNecessidadesExpectativasQueixas());
			usuarioDto.setEducacional(usuario.getEducacional());
			usuarioDto.setComunicacao(usuario.getComunicacao());
			usuarioDto.setReligiaoLazerCulturaRotina(usuario.getReligiaoLazerCulturaRotina());
			usuarioDto.setParecer(usuario.getParecer());
			usuarioDto.setResumoAtendimentosPsicossocial(
					usuario.getResumoAtendimentosPsicossocial());
		}
		return usuarioDto;
	}

	@Override
	public Usuario obterNovo() {
		return new Usuario();
	}
}

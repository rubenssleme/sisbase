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
			usuario.setNaoAlfabetizado(usuarioDto.isNaoAlfabetizado());
			usuario.setNaoEstaNaEscola(usuarioDto.isNaoEstaNaEscola());
			usuario.setResponsavelPorSiMesmo(usuarioDto
					.isResponsavelPorSiMesmo());
			usuario.associarAoSetorCTO(usuarioDto.isAssociadoAoSetorCTO());
			usuario.associarAoSetorProceja(usuarioDto
					.isAssociadoAoSetorProceja());
			usuario.setSituacoesGuarda(new FabricaSituacaoGuarda()
					.converterParaDominio(usuarioDto.getSituacoesGuardaDto()));
			usuario.setPeriodoBeneficios(new FabricaPeriodoBeneficio()
					.converterParaDominio(usuarioDto.getPeriodoBeneficiosDto()));
			usuario.setPeriodoDeficiencias(new FabricaPeriodoDeficiencia()
					.converterParaDominio(usuarioDto.getPeriodoDeficienciaDto()));
			usuario.setPeriodoDoencas(new FabricaPeriodoDoenca()
					.converterParaDominio(usuarioDto.getPeriodoDoencasDto()));
			usuario.setMultiplaDeficiencia(usuarioDto.isMultiplaDeficiencia());
			usuario.setCadeiraDeRodas(usuarioDto.isCadeiraDeRodas());
			usuario.setCustosDoenca(new FabricaCusto()
					.converterParaDominio(usuarioDto.getCustosDoencaDto()));
			usuario.setCustosDeficiencia(new FabricaCusto()
					.converterParaDominio(usuarioDto.getCustosDeficienciaDto()));
			usuario.setConvenios(new FabricaConvenio()
					.converterParaDominio(usuarioDto.getConveniosDto()));
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
			usuario.setRecursos(new FabricaRecurso()
					.converterParaDominio(usuarioDto.getRecursosDto()));
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
			usuarioDto.setStatusDto(new FabricaStatus()
					.converterParaDTO(usuario.getStatus()));
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
			usuarioDto.setNaoAlfabetizado(usuario.isNaoAlfabetizado());
			usuarioDto.setNaoEstaNaEscola(usuario.isNaoEstaNaEscola());
			usuarioDto.setResponsavelPorSiMesmo(usuario
					.isResponsavelPorSiMesmo());
			usuarioDto.setPeriodoBeneficiosDto(new FabricaPeriodoBeneficio()
					.converterParaDTO(usuario.getPeriodoBeneficios()));
			usuarioDto.setPeriodoDeficienciaDto(new FabricaPeriodoDeficiencia()
					.converterParaDTO(usuario.getPeriodoDeficiencias()));
			usuarioDto.setPeriodoDoencasDto(new FabricaPeriodoDoenca()
					.converterParaDTO(usuario.getPeriodoDoencas()));
			usuarioDto.setCadeiraDeRodas(usuario.isCadeiraDeRodas());
			usuarioDto.setMultiplaDeficiencia(usuario.isMultiplaDeficiencia());
			usuarioDto.setCustosDoencaDto(new FabricaCusto()
					.converterParaDTO(usuario.getCustosDoenca()));
			usuarioDto.setCustosDeficienciaDto(new FabricaCusto()
					.converterParaDTO(usuario.getCustosDeficiencia()));
			usuarioDto.setConveniosDto(new FabricaConvenio()
					.converterParaDTO(usuario.getConvenios()));
			usuarioDto.setFoto(usuario.getFoto());
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
			usuarioDto.setRecursosDto(new FabricaRecurso()
					.converterParaDTO(usuario.getRecursos()));
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
		}
		return usuarioDto;
	}

	@Override
	public Usuario obterNovo() {
		return new Usuario();
	}
}

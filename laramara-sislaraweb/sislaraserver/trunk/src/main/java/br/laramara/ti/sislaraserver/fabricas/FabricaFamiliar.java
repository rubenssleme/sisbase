package br.laramara.ti.sislaraserver.fabricas;

import br.laramara.ti.sislaracommons.dtos.familiar.FamiliarDTO;
import br.laramara.ti.sislaraserver.dominio.familiar.Familiar;

public class FabricaFamiliar extends FabricaRecursiva<FamiliarDTO, Familiar> {

	@Override
	public final Familiar converterParaDominio(FamiliarDTO familiarDto,
			Familiar familiar) {
		if (familiarDto != null) {
			if (familiar == null) {
				familiar = new Familiar();
			}
			familiar.setId(familiarDto.getId());
			familiar.setParentesco(new FabricaParentesco()
					.converterParaDominio(familiarDto.getParentescoDto()));
			familiar.setInformacaoEssencial(new FabricaInformacaoEssencial()
					.converterParaDominio(
							familiarDto.getInformacaoEssencialDto(),
							familiar.getInformacaoEssencial()));
			familiar.setCpf(familiarDto.getCpf());

			familiar.setDataNascimento(familiarDto.getDataNascimento());
			familiar.setEstadoCivil(new FabricaEstadoCivil()
					.converterParaDominio(familiarDto.getEstadoCivilDto()));
			familiar.setInformacaoTrabalho(new FabricaInformacaoTrabalho()
					.converterParaDominio(familiarDto
							.getInformacaoTrabalhoDto()));
			familiar.setObs(familiarDto.getObs());
			familiar.setNaoAlfabetizado(familiarDto.isNaoAlfabetizado());
			familiar.adicionarInformacoesEducacionais(new FabricaInformacaoEducacional()
					.converterParaDominio(familiarDto
							.getInformacoesEducacionaisDto()));
			familiar.setRenda(familiarDto.getRenda());

			familiar.setPrincipalResponsavel(familiarDto
					.isPrincipalResponsavel());
			familiar.setMoraNaCasa(familiarDto.isMoraNaCasa());
			familiar.setGenero(new FabricaGenero().converterParaDominio(familiarDto.getGeneroDto()));
			familiar.setResponsavelPelaAvaliacaoSocial(familiarDto.isResponsavelPelaAvaliacaoSocial());
			familiar.setAcompanhante(familiarDto.isAcompanhante());
			familiar.setResposavelPeloUsuario(familiarDto.isResponsavelPeloUsuario());
			familiar.setParadeiroIgnorado(familiarDto.isParadeiroIgnorado());
			familiar.setPeriodoBeneficios(
					new FabricaPeriodoBeneficio().converterParaDominio(familiarDto.getPeriodoBeneficiosDto()));
			familiar.setFalecido(familiarDto.isFalecido());
			familiar.setVulnerabilidades(
					new FabricaVulnerabilidade().converterParaDominio(familiarDto.getVulnerabilidadesDto()));
		}
		return familiar;
	}

	@Override
	public Familiar obterNovo() {
		return new Familiar();
	}

	@Override
	public FamiliarDTO converterParaDTO(Familiar familiarDominio) {
		FamiliarDTO familiarDto = new FamiliarDTO();
		if (familiarDominio.getId() != null) {
			familiarDto.setId(familiarDominio.getId());
		}
		familiarDto.setInformacaoEssencialDto(new FabricaInformacaoEssencial()
				.converterParaDTO(familiarDominio.getInformacaoEssencial()));
		familiarDto.setParentescoDto(new FabricaParentesco()
				.converterParaDTO(familiarDominio.getParentesco()));
		familiarDto.setCpf(familiarDominio.getCpf());
		familiarDto.setDataNascimento(familiarDominio.getDataNascimento());
		familiarDto.setEstadoCivilDto(new FabricaEstadoCivil()
				.converterParaDTO(familiarDominio.getEstadoCivil()));
		familiarDto.setInformacaoTrabalhoDto((new FabricaInformacaoTrabalho()
				.converterParaDTO(familiarDominio.getInformacaoTrabalho())));
		familiarDto.setObs(familiarDominio.getObs());
		familiarDto.setNaoAlfabetizado(familiarDominio.isNaoAlfabetizado());
		familiarDto
				.setInformacoesEducacionaisDto(new FabricaInformacaoEducacional()
						.converterParaDTO(familiarDominio
								.obterInformacoesEducacionais()));
		familiarDto.setRenda(familiarDominio.getRenda());
		familiarDto.setPrincipalResponsavel(familiarDominio
				.isPrincipalResponsavel());
		familiarDto.setMoraNaCasa(familiarDominio.isMoraNaCasa());
		familiarDto.setGeneroDto(new FabricaGenero().converterParaDTO(familiarDominio.getGenero()));
		familiarDto.setResponsavelPelaAvaliacaoSocial(familiarDominio.isResponsavelPelaAvaliacaoSocial());
		familiarDto.setAcompanhante(familiarDominio.isAcompanhante());
		familiarDto.setResponsavelPeloUsuario(familiarDominio.isResponsavelPeloUsuario());
		familiarDto.setParadeiroIgnorado(familiarDominio.isParadeiroIgnorado());
		familiarDto.setPeriodoBeneficiosDto(
				new FabricaPeriodoBeneficio().converterParaDTO(familiarDominio.getPeriodoBeneficios()));
		familiarDto.setFalecido(familiarDominio.isFalecido());
		familiarDto.setVulnerabilidadesDto(
				new FabricaVulnerabilidade().converterParaDTO(familiarDominio.getVulnerabilidades()));
		return familiarDto;
	}
}

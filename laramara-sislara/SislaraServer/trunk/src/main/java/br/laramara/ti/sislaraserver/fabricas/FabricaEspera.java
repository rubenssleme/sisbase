package br.laramara.ti.sislaraserver.fabricas;

import br.laramara.ti.sislaracommons.dtos.espera.EsperaDTO;
import br.laramara.ti.sislaraserver.dominio.espera.Espera;

public class FabricaEspera extends FabricaRecursiva<EsperaDTO, Espera> {

	@Override
	public EsperaDTO converterParaDTO(Espera espera) {
		EsperaDTO esperaDto = new EsperaDTO();
		esperaDto.setId(espera.getId());
		esperaDto.setUsuarioDto(new FabricaUsuario().converterParaDTO(espera
				.getUsuario()));
		esperaDto.setPreCadastroDto(new FabricaPreCadastro()
				.converterParaDTO(espera.getPreCadastro()));
		esperaDto.setDataExpectativa(espera.getDataExpectativa());
		esperaDto.setDataCadastro(espera.obterDataCadastro());
		esperaDto
				.setDescricaoTipoAtendimentoDto(new FabricaDescricaoTipoAtendimento()
						.converterParaDTO(espera.getDescricaoTipoAtendimento()));
		esperaDto.setModuloDto(new FabricaModulo().converterParaDTO(espera
				.getModulo()));
		esperaDto.setSetorDto(new FabricaSetor().converterParaDTO(espera
				.getSetor()));
		esperaDto.setNomeGrupoDto(new FabricaNomeGrupo()
				.converterParaDTO(espera.getNomeGrupo()));
		esperaDto.setProfissionalSolicitouDto(new FabricaProfissional()
				.converterParaDTO(espera.getProfissionalSolicitou()));
		esperaDto.setTipoEsperaDto(new FabricaTipoEspera()
				.converterParaDTO(espera.getTipoEspera()));
		esperaDto.setStatusDto(new FabricaStatusEspera()
				.converterParaDTO(espera.obterStatus()));
		esperaDto.setEstaCancelado(espera.estaCancelado());
		esperaDto.setEstaAguardando(espera.estaAguardando());
		esperaDto.setEstaTriando(espera.estaTriando());
		esperaDto.setObs(espera.getObs());
		esperaDto.setJustificativaCancelamento(espera
				.getJustificativaCancelamento());
		esperaDto.setHistorioOperacoes(espera.getHistoricoOperacoes());
		esperaDto.setSetoresUsuario(new FabricaSetor().converterParaDTO(espera
				.obterSetoresUsuario()));
		esperaDto.setUsuarioCriadoPeloPreCadastro(new FabricaUsuario()
				.converterParaDTO(espera.obterUsuarioCriadoPeloPreCadastro()));
		esperaDto.setInteresse(espera.isInteresse());
		esperaDto.setLmLigou(espera.isLmLigou());
		esperaDto.setPendencias(espera.isPendencias());
		esperaDto.setDataUltimaLigacaoInteresse(espera.getDataUltimaLigacaoInteresse());
		return esperaDto;
	}

	@Override
	public Espera converterParaDominio(EsperaDTO esperaDto, Espera espera) {
		if (esperaDto != null) {
			if (espera == null) {
				espera = new Espera();
			}
			espera.setId(esperaDto.getId());
			espera.setUsuario(new FabricaUsuario().converterParaDominio(
					esperaDto.getUsuarioDto(), espera.getUsuario()));
			espera.setPreCadastro(new FabricaPreCadastro()
					.converterParaDominio(esperaDto.getPreCadastroDto(),
							espera.getPreCadastro()));
			espera.setDataExpectativa(esperaDto.getDataExpectativa());
			espera.setDescricaoTipoAtendimento(new FabricaDescricaoTipoAtendimento()
					.converterParaDominio(esperaDto
							.getDescricaoTipoAtendimentoDto()));
			espera.setModulo(new FabricaModulo().converterParaDominio(esperaDto
					.getModuloDto()));
			espera.setSetor(new FabricaSetor().converterParaDominio(esperaDto
					.getSetorDto()));
			espera.setNomeGrupo(new FabricaNomeGrupo()
					.converterParaDominio(esperaDto.getNomeGrupoDto()));
			espera.setProfissionalSolicitou(new FabricaProfissional()
					.converterParaDominio(esperaDto
							.getProfissionalSolicitioDto()));
			espera.setTipoEspera(new FabricaTipoEspera()
					.converterParaDominio(esperaDto.getTipoEsperaDto()));
			espera.setObs(esperaDto.getObs());
			espera.setJustificativaCancelamento(esperaDto
					.getJustificativaCancelamento());
			espera.setInteresse(esperaDto.isInteresse());
			espera.setLmLigou(esperaDto.isLmLigou());
			espera.setPendencias(esperaDto.isPendencias());
		}
		return espera;
	}

	@Override
	public Espera obterNovo() {
		return new Espera();
	}
}

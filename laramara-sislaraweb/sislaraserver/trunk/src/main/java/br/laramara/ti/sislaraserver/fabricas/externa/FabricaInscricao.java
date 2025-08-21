package br.laramara.ti.sislaraserver.fabricas.externa;

import br.laramara.ti.sislaracommons.dtos.inscricao.InscricaoDTO;
import br.laramara.ti.sislaraserver.dominio.inscricao.Inscricao;
import br.laramara.ti.sislaraserver.fabricas.FabricaDetalheCurso;
import br.laramara.ti.sislaraserver.fabricas.FabricaEndereco;
import br.laramara.ti.sislaraserver.fabricas.FabricaRecursiva;

public class FabricaInscricao extends FabricaRecursiva<InscricaoDTO, Inscricao> {

	@Override
	public InscricaoDTO converterParaDTO(Inscricao inscricao) {
		return null;
	}
	
	@Override
	public Inscricao converterParaDominio(InscricaoDTO inscricaoDto, Inscricao inscricao) {
		if (inscricaoDto != null) {
			if (inscricaoDto.getId() != null) {
				inscricao.setId(inscricaoDto.getId());
			}

			inscricao.setEndereco(new FabricaEndereco().converterParaDominio(inscricaoDto.getEnderecoDto()));
			inscricao.setNomeParaCracha(inscricaoDto.getNomeParaCracha());
			inscricao.setObservacoes(inscricaoDto.getObservacoes());
			inscricao.setAreaFormacao(inscricaoDto.getAreaFormacao());
			inscricao.setLocalTrabalho(inscricaoDto.getLocalTrabalho());
			inscricao.setCargoOuFuncao(inscricaoDto.getCargoOuFuncao());
			inscricao.setUsuarioExternoPossuiCadeiraDeRodas(inscricaoDto.isUsuarioExternoPossuiCadeiraDeRodas());
			inscricao.setUsuarioExternoPossuiCaoGuia(inscricaoDto.isUsuarioExternoPossuiCaoGuia());
			inscricao.setGrupo(new FabricaDetalheCurso().converterParaDominio(inscricaoDto.getDetalheCursoDto()));
			inscricao.setValorTotalAlmocoIncluso(inscricaoDto.isValorTotalAlmocoIncluso());
		}

		return inscricao;
	}

	@Override
	public Inscricao converterParaDominio(InscricaoDTO inscricaoDto) {
		return converterParaDominio(inscricaoDto, obterNovo());
	}
	
	@Override
	public Inscricao obterNovo() {
		return new Inscricao();
	}
}

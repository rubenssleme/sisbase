package br.laramara.ti.sislaraserver.fabricas;

import br.laramara.ti.sislaracommons.dtos.doacao.EspecificacaoGeracaoDemandaDTO;
import br.laramara.ti.sislaraserver.dominio.doacao.EspecificacaoGeracaoDemanda;

public class FabricaEspecificacaoGeracaoDemanda
		extends
		FabricaBase<EspecificacaoGeracaoDemandaDTO, EspecificacaoGeracaoDemanda> {

	@Override
	public EspecificacaoGeracaoDemandaDTO converterParaDTO(
			EspecificacaoGeracaoDemanda especificacaoGeracaoDemanda) {
		return null;
	}

	@Override
	public EspecificacaoGeracaoDemanda converterParaDominio(
			EspecificacaoGeracaoDemandaDTO especificacaoGeracaoDemandaDto) {
		EspecificacaoGeracaoDemanda especificacaoGeracaoDemanda = new EspecificacaoGeracaoDemanda();
		if (especificacaoGeracaoDemandaDto != null) {
			especificacaoGeracaoDemanda.setUsuario(new FabricaUsuario()
					.converterParaDominio(especificacaoGeracaoDemandaDto
							.getUsuariosDto()));
			especificacaoGeracaoDemanda.setPreCadastro(new FabricaPreCadastro()
					.converterParaDominio(especificacaoGeracaoDemandaDto
							.getPreCadastrosDto()));
			especificacaoGeracaoDemanda.setGrupo(new FabricaGrupo()
					.converterParaDominio(
							especificacaoGeracaoDemandaDto.getGrupoDto(),
							especificacaoGeracaoDemanda.getGrupo()));
			especificacaoGeracaoDemanda.setProjeto(new FabricaProjeto()
					.converterParaDominio(especificacaoGeracaoDemandaDto
							.getProjetoDto()));
			especificacaoGeracaoDemanda.setRecursosEDescricaoRecurso(new FabricaRecursoEDescricaoDeRecurso()
					.converterParaDominio(especificacaoGeracaoDemandaDto
							.getRecursosEDescricaoRecursoDto()));
			especificacaoGeracaoDemanda.setDocumentosSolicitacaoDoacao(new FabricaDocumentoSolicitacaoDoacao()
					.converterParaDominio(especificacaoGeracaoDemandaDto.getDocumentosSolicitacaoDocacaoDto()));
			especificacaoGeracaoDemanda.setCartelaDeSelos(especificacaoGeracaoDemandaDto.isCartelaDeSelos());
			especificacaoGeracaoDemanda.setDataExpectativa(especificacaoGeracaoDemandaDto.getDataExpectativa());
		}
		return especificacaoGeracaoDemanda;
	}
}

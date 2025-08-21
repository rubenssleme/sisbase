package br.laramara.ti.sislaraserver.fabricas;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.dtos.contribuicao.MotivoDesativacaoDTO;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.dominio.contribuicao.MotivoDesativacao;

public class TestesFabricaMotivoDesativacao {

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void fabrica_motivo_desativacao_converte_objeto_de_dominio_para_dto() {
		MotivoDesativacao motivoDesativacao = new MotivoDesativacao(new Long(1), "Crise");

		MotivoDesativacaoDTO motivoDesativacaoDTO = new FabricaMotivoDesativacao().converterParaDTO(motivoDesativacao);

		Assert.assertEquals(motivoDesativacaoDTO.getId(), motivoDesativacao.getId());
		Assert.assertEquals(motivoDesativacaoDTO.toString(), motivoDesativacao.getDescricao());
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void fabrica_motivo_desativacao_converte_objeto_de_dto_para_domino() {
		MotivoDesativacaoDTO motivoDesativacaoDto = new MotivoDesativacaoDTO(new Long(1), "Crise");

		MotivoDesativacao motivoDesativacao = new FabricaMotivoDesativacao().converterParaDominio(motivoDesativacaoDto);

		Assert.assertEquals(motivoDesativacao.getId(), motivoDesativacaoDto.getId());
		Assert.assertEquals(motivoDesativacao.getDescricao(), motivoDesativacaoDto.toString());
	}
}

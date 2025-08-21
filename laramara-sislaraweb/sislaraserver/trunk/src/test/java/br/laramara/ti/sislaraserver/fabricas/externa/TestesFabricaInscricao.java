package br.laramara.ti.sislaraserver.fabricas.externa;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.dtos.inscricao.InscricaoDTO;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.dominio.inscricao.Inscricao;

public class TestesFabricaInscricao {
	
	@Test(groups = { TiposDeTeste.INTEGRACAO_SEM_BD })
	public void fabrica_inscricao_converte_dto_para_dominio() {
		InscricaoDTO inscricaoDto = ContextoInscricao.fabricarInscricaoDTOComTodosOsDados();

		Inscricao inscricao = new FabricaInscricao().converterParaDominio(inscricaoDto);

		Assert.assertEquals(inscricaoDto.getId(), inscricao.getId());
		Assert.assertNotNull(inscricaoDto.getEnderecoDto());
		Assert.assertEquals(inscricaoDto.getNomeParaCracha(), inscricao.getNomeParaCracha());
		Assert.assertEquals(inscricaoDto.getObservacoes(), inscricao.getObservacoes());
		Assert.assertEquals(inscricaoDto.getAreaFormacao(), inscricao.getAreaFormacao());
		Assert.assertEquals(inscricaoDto.getLocalTrabalho(), inscricao.getLocalTrabalho());
		Assert.assertEquals(inscricaoDto.getCargoOuFuncao(), inscricao.getCargoOuFuncao());
		Assert.assertEquals(inscricaoDto.isUsuarioExternoPossuiCadeiraDeRodas(), inscricao.isUsuarioExternoPossuiCadeiraDeRodas());
		Assert.assertEquals(inscricaoDto.isUsuarioExternoPossuiCaoGuia(), inscricao.isUsuarioExternoPossuiCaoGuia());
		Assert.assertEquals(inscricaoDto.isValorTotalAlmocoIncluso(), inscricao.isValorTotalAlmocoIncluso());
		Assert.assertNotNull(inscricaoDto.getDetalheCursoDto());
	}
}

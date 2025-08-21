package br.laramara.sistelemarketingserver.fabrica;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.sistelemarketingcommons.dtos.telefonia.LigacaoDTO;
import br.laramara.sistelemarketingcommons.utilitarios.TiposDeTeste;
import br.laramara.sistelemarketingserver.dominio.telefonia.Ligacao;
import br.laramara.sistelemarketingserver.fabricas.LigacaoFabrica;

public class TestesLigacaoFabrica {

	@Test(groups = { TiposDeTeste.INTEGRACAO_SEM_BD })
	public void fabrica_ligacao_converte_objeto_dto_para_dominio() {
		LigacaoDTO ligacaoDto = ContextoLigacao.fabricarDto();

		Ligacao ligacaoCovertido = new LigacaoFabrica().converterParaDominio(ligacaoDto);

		Assert.assertEquals(ligacaoCovertido.getToken(), ligacaoDto.getToken());
		Assert.assertEquals(ligacaoCovertido.getTelefone().getTelefone(), ligacaoDto.getTelefoneDto().getTelefone());
	}
}

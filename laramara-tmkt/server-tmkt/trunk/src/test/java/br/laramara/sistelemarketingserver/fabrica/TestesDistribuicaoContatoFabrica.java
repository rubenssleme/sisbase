package br.laramara.sistelemarketingserver.fabrica;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.sistelemarketingcommons.dtos.contato.DistribuicaoContatoDTO;
import br.laramara.sistelemarketingcommons.utilitarios.TiposDeTeste;
import br.laramara.sistelemarketingserver.dominio.contato.DistribuicaoContato;
import br.laramara.sistelemarketingserver.fabricas.DistribuicaoContatoFabrica;

public class TestesDistribuicaoContatoFabrica {
	@Test(groups = { TiposDeTeste.INTEGRACAO_SEM_BD })
	public void fabrica_distribuicao_contato_converte_objeto_de_dominio_para_dto() {
		DistribuicaoContato distribuicaoContato = ContextoDistriucaoContato.fabricarDistribuicaoContato();

		DistribuicaoContatoDTO distribuicaoContatoDtoCovertido = new DistribuicaoContatoFabrica()
				.converterParaDTO(distribuicaoContato);

		Assert.assertEquals(distribuicaoContatoDtoCovertido.getContatoDto().getId(),
				distribuicaoContato.getContato().getId());
	}
}

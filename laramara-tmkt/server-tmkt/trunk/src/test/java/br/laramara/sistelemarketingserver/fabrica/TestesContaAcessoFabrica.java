package br.laramara.sistelemarketingserver.fabrica;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.sistelemarketingcommons.dtos.seguranca.ContaAcessoDTO;
import br.laramara.sistelemarketingcommons.utilitarios.TiposDeTeste;
import br.laramara.sistelemarketingserver.dominio.seguranca.ContaAcesso;
import br.laramara.sistelemarketingserver.dominio.seguranca.ContextoContaAcesso;
import br.laramara.sistelemarketingserver.fabricas.ContaAcessoFabrica;

public class TestesContaAcessoFabrica {
	@Test(groups = { TiposDeTeste.INTEGRACAO_SEM_BD })
	public void fabrica_conta_acesso_converte_objeto_de_dominio_para_dto() {
		ContaAcesso contaAcesso = ContextoContaAcesso.fabricarContaAcessoCarlos();

		ContaAcessoDTO contaAcessoDtoCovertido = new ContaAcessoFabrica().converterParaDTO(contaAcesso);

		Assert.assertEquals(contaAcessoDtoCovertido.getId(), contaAcesso.getId());
		Assert.assertEquals(contaAcessoDtoCovertido.getNome(), contaAcesso.getNome());
		Assert.assertEquals(contaAcessoDtoCovertido.getLogin(), contaAcesso.getLogin());
		Assert.assertEquals(contaAcessoDtoCovertido.getNivelDto().getId(), contaAcesso.getNivel().getId());
		Assert.assertEquals(contaAcessoDtoCovertido.isAtivo(), contaAcesso.isAtivo());
		Assert.assertEquals(contaAcessoDtoCovertido.getTurnoDto().toString(), contaAcesso.getTurno().toString());
		Assert.assertEquals(contaAcessoDtoCovertido.getRamalDto().getNumero(), contaAcesso.getRamal().getNumero());
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_SEM_BD })
	public void fabrica_conta_acesso_converte_objeto_dto_para_dominio() {
		ContaAcessoDTO contaAcessoDto = ContextoContaAcesso.fabricar();

		ContaAcesso contaAcessoCovertido = new ContaAcessoFabrica().converterParaDominio(contaAcessoDto);

		Assert.assertEquals(contaAcessoCovertido.getLogin(), contaAcessoDto.getLogin());
		Assert.assertEquals(contaAcessoCovertido.getNome(), contaAcessoDto.getNome());
		Assert.assertEquals(contaAcessoCovertido.getNivel().getId(), contaAcessoDto.getNivelDto().getId());
		Assert.assertEquals(contaAcessoCovertido.isAtivo(), contaAcessoDto.isAtivo());
		Assert.assertEquals(contaAcessoCovertido.getTurno().toString(), contaAcessoDto.getTurnoDto().toString());
	}
}

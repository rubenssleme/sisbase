package br.laramara.sistelemarketingcommons.dtos.seguranca;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.sistelemarketingcommons.dtos.telefonia.RamalDTO;
import br.laramara.sistelemarketingcommons.utilitarios.TiposDeTeste;

public class TestesContaAcessoDTO {

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void conta_acesso_dto_foi_construida_com_sucesso() {
		Long id = new Long(2);
		String nome = "Paulo Augusto";
		String login = "pabsantos";
		String senha = "1234";
		boolean ativo = true;

		NivelDTO nivelDto = ContextoNivelDTO.construir();
		TurnoDTO turnoDto = ContextoTurnoDTO.construir();
		RamalDTO ramalDto = new RamalDTO("6459");


		ContaAcessoDTO contaAcessoDto = new ContaAcessoDTO();
		contaAcessoDto.setId(id);
		contaAcessoDto.setNome(nome);
		contaAcessoDto.setLogin(login);
		contaAcessoDto.setSenha(senha);
		contaAcessoDto.setAtivo(ativo);
		contaAcessoDto.setNivelDto(nivelDto);
		contaAcessoDto.setTurnoDto(turnoDto);
		contaAcessoDto.setRamalDto(ramalDto);

		Assert.assertEquals(contaAcessoDto.getId(), id);
		Assert.assertEquals(contaAcessoDto.getNome(), nome);
		Assert.assertEquals(contaAcessoDto.getLogin(), login);
		Assert.assertEquals(contaAcessoDto.getSenha(), senha);
		Assert.assertEquals(contaAcessoDto.isAtivo(), ativo);
		Assert.assertEquals(contaAcessoDto.getNivelDto().getId(), nivelDto.getId());
		Assert.assertEquals(contaAcessoDto.getTurnoDto().toString(), turnoDto.toString());
		Assert.assertEquals(contaAcessoDto.getRamalDto().getNumero(), ramalDto.getNumero());
	}
}

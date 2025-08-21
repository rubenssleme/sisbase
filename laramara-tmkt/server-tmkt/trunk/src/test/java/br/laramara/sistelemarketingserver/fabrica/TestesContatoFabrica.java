package br.laramara.sistelemarketingserver.fabrica;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.sistelemarketingcommons.dtos.contato.ContatoDTO;
import br.laramara.sistelemarketingcommons.utilitarios.TiposDeTeste;
import br.laramara.sistelemarketingserver.ContextoContato;
import br.laramara.sistelemarketingserver.dominio.contato.Contato;
import br.laramara.sistelemarketingserver.fabricas.ContatoFabrica;

public class TestesContatoFabrica {

	@Test(groups = { TiposDeTeste.INTEGRACAO_SEM_BD })
	public void fabrica_contato_converte_objeto_de_dominio_para_dto() {
		Contato contato = ContextoContato.fabricarContato();

		ContatoDTO contatoDtoCovertido = new ContatoFabrica().converterParaDTO(contato);

		Assert.assertEquals(contatoDtoCovertido.getId(), contato.getId());
		Assert.assertEquals(contatoDtoCovertido.getNome(), contato.getNome());
		Assert.assertEquals(contatoDtoCovertido.getCep().getCep(), contato.getCep().getCep());
		Assert.assertEquals(contatoDtoCovertido.getNumero(), contato.getNumero());
		Assert.assertEquals(contatoDtoCovertido.getComplemento(), contato.getComplemento());
		Assert.assertEquals(contatoDtoCovertido.getEmail(), contato.getEmail());
		Assert.assertEquals(contatoDtoCovertido.getCpf(), contato.getCpf());
		Assert.assertEquals(contatoDtoCovertido.getTelefonesDto().size(), contato.getTelefones().size());
	}
}

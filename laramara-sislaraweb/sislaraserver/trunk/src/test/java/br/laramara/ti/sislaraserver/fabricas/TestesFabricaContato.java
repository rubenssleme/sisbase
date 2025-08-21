package br.laramara.ti.sislaraserver.fabricas;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.dtos.ContatoDTO;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.dominio.Contato;

public class TestesFabricaContato {

	@Test(groups = { TiposDeTeste.INTEGRACAO_SEM_BD })
	public void fabrica_contato_converte_objeto_de_dto_para_dominio() {
		Long id = new Long(34324);
		ContatoDTO contatoDto = ContextoContato.construirContatoDTO();
		contatoDto.setId(id);

		Contato contatoConvertido = new FabricaContato()
				.converterParaDominio(contatoDto);

		Assert.assertEquals(contatoConvertido.getId().toString(), contatoDto
				.getId().toString());
		Assert.assertEquals(contatoConvertido.getTelefones().size(), 3);
		Assert.assertEquals(contatoConvertido.getRamal(), contatoDto.getRamal());
		Assert.assertEquals(contatoConvertido.getNomeContato(),
				contatoDto.getNomeContato());
		Assert.assertEquals(contatoConvertido.getEmail(), contatoDto.getEmail());
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_SEM_BD })
	public void fabrica_endereco_converte_objeto_de_dominio_para_dto() {
		Long id = new Long("34324");
		Contato contatoDominio = ContextoContato
				.fabricarContatoComTodosOsDados();
		contatoDominio.setId(id);
		ContatoDTO contatoConvertido = new FabricaContato()
				.converterParaDTO(contatoDominio);

		Assert.assertEquals(contatoConvertido.getId().toString(),
				contatoDominio.getId().toString());
		Assert.assertEquals(contatoConvertido.getTelefonesDto().size(), 3);
		Assert.assertEquals(contatoConvertido.getRamal(),
				contatoDominio.getRamal());
		Assert.assertEquals(contatoConvertido.getNomeContato(),
				contatoDominio.getNomeContato());
		Assert.assertEquals(contatoConvertido.getEmail(),
				contatoDominio.getEmail());

	}
}

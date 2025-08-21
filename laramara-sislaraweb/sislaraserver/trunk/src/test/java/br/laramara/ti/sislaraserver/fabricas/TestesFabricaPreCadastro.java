package br.laramara.ti.sislaraserver.fabricas;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.dtos.precadastro.PreCadastroDTO;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.dominio.precadastro.PreCadastro;

public class TestesFabricaPreCadastro {

	@Test(groups = { TiposDeTeste.INTEGRACAO_SEM_BD })
	public void fabrica_precadastro_converte_objeto_de_dto_novo_para_dominio() {
		PreCadastroDTO preCadastroDto = ContextoPreCadastro
				.construirPreCadastroDTOsemIdentificacao();

		PreCadastro preCadastro = new FabricaPreCadastro()
				.converterParaDominio(preCadastroDto);

		Assert.assertEquals(preCadastro.getId(), preCadastroDto.getId());
		Assert.assertEquals(preCadastro.getInformacaoEssencial().getNome(),
				preCadastroDto.getInformacaoEssencialDto().getNome());
		Assert.assertNotNull(preCadastro.getInformacaoEssencial().getContato());
		Assert.assertEquals(preCadastro.getObs(), preCadastroDto.getObs());
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_SEM_BD })
	public void fabrica_precadastro_converte_objeto_de_dominio_para_dto() {
		PreCadastro preCadastro = ContextoPreCadastro
				.fabricarPreCadastroComTodosOsDados();

		PreCadastroDTO preCadastroDto = new FabricaPreCadastro()
				.converterParaDTO(preCadastro);

		Assert.assertEquals(preCadastroDto.getId(), preCadastro.getId());
		Assert.assertNotNull(preCadastroDto.getDataCadastro());
		Assert.assertEquals(preCadastroDto.getInformacaoEssencialDto()
				.getNome(), preCadastro.getInformacaoEssencial().getNome());
		Assert.assertEquals(preCadastroDto.getInformacaoEssencialDto()
				.getContatoDto().getNomeContato(), preCadastro
				.getInformacaoEssencial().getContato().getNomeContato());
		Assert.assertEquals(preCadastroDto.getObs(), preCadastro.getObs());
	}
}

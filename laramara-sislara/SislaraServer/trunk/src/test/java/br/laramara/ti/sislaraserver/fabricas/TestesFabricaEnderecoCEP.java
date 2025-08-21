package br.laramara.ti.sislaraserver.fabricas;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.dtos.endereco.EnderecoCEPDTO;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.dominio.endereco.EnderecoCEP;

public class TestesFabricaEnderecoCEP {

	@Test(groups = { TiposDeTeste.INTEGRACAO_SEM_BD })
	public void fabrica_enderecocep_converte_objeto_de_dominio_para_dto() {
		EnderecoCEP enderecoDominio = ContextoEnderecoCEP.fabricarEnderecoComTodosOsDados();
		EnderecoCEPDTO enderecoCepDtoConvertido = new FabricaEnderecoCEP()
				.converterParaDTO(enderecoDominio);

		Assert.assertEquals(enderecoCepDtoConvertido.getEndereco(),
				enderecoDominio.getEndereco());
		Assert.assertEquals(enderecoCepDtoConvertido.getBairro(),
				enderecoDominio.getBairro());
		Assert.assertEquals(enderecoCepDtoConvertido.getMunicipioDto().toString(),
				enderecoDominio.getMunicipio().getNome());
		Assert.assertEquals(enderecoCepDtoConvertido.getUfDto().toString(),
				enderecoDominio.getUf().toString());
		Assert.assertEquals(enderecoCepDtoConvertido.getPaisDto().toString(),
				enderecoDominio.getPais().getNome());
	}
}

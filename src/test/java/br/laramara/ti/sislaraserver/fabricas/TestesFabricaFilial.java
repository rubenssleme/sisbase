package br.laramara.ti.sislaraserver.fabricas;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.dtos.doacao.FilialDTO;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.dominio.doacao.Filial;

public class TestesFabricaFilial {

	@Test(groups = { TiposDeTeste.INTEGRACAO_SEM_BD })
	public void fabrica_filial_converte_objeto_de_dto_para_dominio() {
		FilialDTO filialDTO = ContextoFilial.fabricarDto();

		Filial filialConvertido = new FabricaFilial().converterParaDominio(filialDTO);

		Assert.assertEquals(filialConvertido.getId(), filialDTO.getId());
		Assert.assertEquals(filialConvertido.getCep(), filialDTO.getCep());
		Assert.assertEquals(filialConvertido.getCnpj(), filialDTO.getCnpj());
		Assert.assertEquals(filialConvertido.getEndereco(), filialDTO.getEndereco());
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_SEM_BD })
	public void fabrica_filial_converte_objeto_de_dominio_para_dto() {
		Filial filial = ContextoFilial.fabricarDominio();

		FilialDTO filialDtoConvertido = new FabricaFilial().converterParaDTO(filial);

		Assert.assertEquals(filialDtoConvertido.getId(), filial.getId());
		Assert.assertEquals(filialDtoConvertido.getCep(), filial.getCep());
		Assert.assertEquals(filialDtoConvertido.getCnpj(), filial.getCnpj());
		Assert.assertEquals(filialDtoConvertido.getEndereco(), filial.getEndereco());
	}
}

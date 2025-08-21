package br.laramara.ti.sislaracommons.dtos.doacao;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.dtos.doacao.ReciboDTO;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesReciboDTO {
	
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void recibodto_foi_construida_com_sucesso() {
		Long id = new Long(122222);
		String nome = "JOSEP MEAZA";
		String data = "01/01/2000";
		String valor = "334,44";
		String cpfCnpj = "71894810287";
		String descricao = "Descrição do texto.";
		FilialDTO filialDto = new FilialDTO();
		filialDto.setId(id);
		
		ReciboDTO reciboDTO = new ReciboDTO();
		reciboDTO.setId(id);
		reciboDTO.setCpfCnpj(cpfCnpj);
		reciboDTO.setData(data);
		reciboDTO.setMotivoDoCancelamento(descricao);
		reciboDTO.setFilialDTO(filialDto);
		reciboDTO.setNome(nome);
		reciboDTO.setValorTotalRecibo(valor);

		Assert.assertEquals(reciboDTO.getId(), id);
		Assert.assertEquals(reciboDTO.getCpfCnpj(), cpfCnpj);
		Assert.assertEquals(reciboDTO.getData(), data);
		Assert.assertEquals(reciboDTO.getMotivoDoCancelamento(), descricao);
		Assert.assertEquals(reciboDTO.getNome(), nome);
		Assert.assertEquals(reciboDTO.getValorTotalRecibo(), valor);
		Assert.assertEquals(reciboDTO.getFilial().getId(), filialDto.getId());
	}
}

package br.laramara.ti.sislaracommons.dtos;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesInformacaoEssencialDTO {
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void informacaoessencialdto_foi_construida_com_sucesso() {
		Long id = new Long(122222);
		String nome = "JOSEP MEAZA";
		String data = "31/12/2013";
		String idade = "23";
		String rg = "12345";
		String cpf = "12312312312";
		boolean usuarioAssociado = true;

		InformacaoEssencialDTO informacaoEssencialDto = new InformacaoEssencialDTO();
		informacaoEssencialDto.setId(id);
		informacaoEssencialDto.setNome(nome);
		informacaoEssencialDto.setIdade(idade);
		informacaoEssencialDto.setDataNascimento(data);
		informacaoEssencialDto.setUsuarioAssociado(usuarioAssociado);
		informacaoEssencialDto.setRg(rg);
		informacaoEssencialDto.setCpf(cpf);

		Assert.assertEquals(informacaoEssencialDto.getId(), id);
		Assert.assertEquals(informacaoEssencialDto.getNome(), nome);
		Assert.assertEquals(informacaoEssencialDto.getIdade(), idade);
		Assert.assertEquals(informacaoEssencialDto.getDataNascimento(), data);
		Assert.assertEquals(informacaoEssencialDto.possuiUsuarioAssociado(), usuarioAssociado);
		Assert.assertEquals(informacaoEssencialDto.getRg(), rg);
		Assert.assertEquals(informacaoEssencialDto.getCpf(), cpf);
	}
}

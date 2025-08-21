package br.laramara.ti.sislaracommons.dtos.precadastro;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.dtos.ContatoDTO;
import br.laramara.ti.sislaracommons.dtos.InformacaoEssencialDTO;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesPreCadastroDTO {

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void precadastrodto_foi_construida_pelo_server_com_sucesso() {
		Long id = new Long(12222);
		String dataCadastro = "31/12/1982";
		String nome = "Josep Meaza";
		ContatoDTO contatoDto = new ContatoDTO();
		contatoDto.setNomeContato("Josep Contato");
		String textoObs = "Grande texto de OBS.";

		InformacaoEssencialDTO informacaoEssencialDto = new InformacaoEssencialDTO();
		informacaoEssencialDto.setNome("Josep Meaza");
		informacaoEssencialDto.setContatoDto(contatoDto);

		PreCadastroDTO preCadastroDto = new PreCadastroDTO();
		preCadastroDto.setId(id);
		preCadastroDto.setDataCadastro(dataCadastro);
		preCadastroDto.setInformacaoEssencialDto(informacaoEssencialDto);
		preCadastroDto.setObs(textoObs);

		Assert.assertEquals(preCadastroDto.getId(), id);
		Assert.assertEquals(preCadastroDto.getDataCadastro(), dataCadastro);
		Assert.assertEquals(preCadastroDto.getInformacaoEssencialDto()
				.getNome(), nome);
		Assert.assertNotNull(preCadastroDto.getInformacaoEssencialDto()
				.getContatoDto().getNomeContato());
		Assert.assertEquals(preCadastroDto.getObs(), textoObs);
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void precadastrodto_foi_construida_pelo_client_com_sucesso() {
		Long id = new Long(12222);
		String dataCadastro = "31/12/1982";
		String nome = "Josep Meaza";
		String rg = "12345";
		String cpf = "12312312312";
		ContatoDTO contatoDto = new ContatoDTO();
		contatoDto.setNomeContato("Josep Contato");

		InformacaoEssencialDTO informacaoEssencialDto = new InformacaoEssencialDTO();
		informacaoEssencialDto.setNome("Josep Meaza");
		informacaoEssencialDto.setContatoDto(contatoDto);
		informacaoEssencialDto.setRg(rg);
		informacaoEssencialDto.setCpf(cpf);

		PreCadastroDTO preCadastroDto = new PreCadastroDTO();
		preCadastroDto.setId(id);
		preCadastroDto.setDataCadastro(dataCadastro);
		preCadastroDto.setInformacaoEssencialDto(informacaoEssencialDto);

		Assert.assertEquals(preCadastroDto.getId(), id);
		Assert.assertEquals(preCadastroDto.getDataCadastro(), dataCadastro);
		Assert.assertEquals(preCadastroDto.getInformacaoEssencialDto()
				.getNome(), nome);
		Assert.assertNotNull(preCadastroDto.getInformacaoEssencialDto()
				.getContatoDto().getNomeContato());
		Assert.assertEquals(preCadastroDto.getInformacaoEssencialDto().getRg(), rg);
		Assert.assertEquals(preCadastroDto.getInformacaoEssencialDto().getCpf(), cpf);
	}
}

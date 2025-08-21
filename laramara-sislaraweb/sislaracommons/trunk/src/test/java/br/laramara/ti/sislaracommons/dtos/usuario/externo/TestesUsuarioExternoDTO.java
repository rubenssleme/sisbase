package br.laramara.ti.sislaracommons.dtos.usuario.externo;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.dtos.endereco.EnderecoDTO;
import br.laramara.ti.sislaracommons.dtos.endereco.MunicipioDTO;
import br.laramara.ti.sislaracommons.dtos.endereco.UfDTO;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesUsuarioExternoDTO {
	
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void usuarioexternodto_foi_construido_com_sucesso() {
		Long id = 111111L;
		String email = "usuario.externo@gmail.com";
		String nomeCompleto = "Carlos Kafka";
		String dddETelefoneCelular = "11955442211";
		String outroTelefone = "11911223355";
		String cpf = "86890477006";
		String senha = "81dc9bdb52d04dc20036dbd8313ed055";
		boolean autorizoRecebimentoInformativo = true;
		boolean bloqueado = false;
		String versao = "1";
		String dataNascimento = "27/09/1993";
		String rgRne = "333474685";
		boolean ePessoaComDeficiencia = true;
		boolean possuiBaixaVisao = true;
		boolean possuiCegueira = false;
		String outraDeficiencia = "";

		UsuarioExternoDTO usuarioExternoDto = new UsuarioExternoDTO(email, nomeCompleto, dddETelefoneCelular, autorizoRecebimentoInformativo);
		
		usuarioExternoDto.setId(id);
		usuarioExternoDto.setOutroTelefone(outroTelefone);
		usuarioExternoDto.setCpf(cpf);
		usuarioExternoDto.setSenha("1234");
		usuarioExternoDto.setBloqueado(bloqueado);
		usuarioExternoDto.setVersao(versao);
		usuarioExternoDto.setDataNascimento(dataNascimento);
		usuarioExternoDto.setRgRne(rgRne);
		
		EnderecoDTO enderecoResidencial = new EnderecoDTO();
		
		enderecoResidencial.setId(4L);
		enderecoResidencial.setCep("99922222");
		enderecoResidencial.setEndereco("Rua do Paraiso");
		enderecoResidencial.setNumero("333");
		enderecoResidencial.setComplemento("AP22");
		enderecoResidencial.setBairro("Santa Cecilia");
		enderecoResidencial.setMunicipioDto(new MunicipioDTO(1L, "São Paulo", new UfDTO("SP")));
		enderecoResidencial.setUfDto(new UfDTO("SP"));
		
		usuarioExternoDto.setEnderecoResidencial(enderecoResidencial);
		
		usuarioExternoDto.setePessoaComDeficiencia(ePessoaComDeficiencia);
		usuarioExternoDto.setPossuiBaixaVisao(possuiBaixaVisao);
		usuarioExternoDto.setPossuiCegueira(possuiCegueira);
		usuarioExternoDto.setOutraDeficiencia(outraDeficiencia);
		
		Assert.assertNotNull(usuarioExternoDto.toString());
		Assert.assertEquals(usuarioExternoDto.getNomeCompleto(), nomeCompleto);
		Assert.assertEquals(usuarioExternoDto.getId(), id);
		Assert.assertEquals(usuarioExternoDto.getEmail(), email);
		Assert.assertEquals(usuarioExternoDto.getSenha(), senha);
		Assert.assertEquals(usuarioExternoDto.isBloqueado(), bloqueado);
		Assert.assertEquals(usuarioExternoDto.getVersao(), versao);
		Assert.assertEquals(usuarioExternoDto.getDddETelefoneCelular(), dddETelefoneCelular);
		Assert.assertEquals(usuarioExternoDto.getOutroTelefone(), outroTelefone);
		Assert.assertEquals(usuarioExternoDto.getDataNascimento(), dataNascimento);
		Assert.assertEquals(usuarioExternoDto.getRgRne(), rgRne);
		Assert.assertEquals(usuarioExternoDto.getCpf(), cpf);
		Assert.assertEquals(usuarioExternoDto.isAutorizoRecebimentoInformativo(), autorizoRecebimentoInformativo);
		Assert.assertNotNull(usuarioExternoDto.getEnderecoResidencial());
		Assert.assertEquals(usuarioExternoDto.isePessoaComDeficiencia(), ePessoaComDeficiencia);
		Assert.assertEquals( usuarioExternoDto.isPossuiBaixaVisao(), possuiBaixaVisao);
		Assert.assertEquals(usuarioExternoDto.isPossuiCegueira(), possuiCegueira);
		Assert.assertEquals(usuarioExternoDto.getOutraDeficiencia(), outraDeficiencia);
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void usuarioexternodto_capaCPF_com_sucesso() {
		UsuarioExternoDTO usuarioExternoDto = new UsuarioExternoDTO();
		usuarioExternoDto.setCpf("22111111155");
		
		Assert.assertEquals(usuarioExternoDto.obterCpfCapado(), "22*******55");
		
	}
	

}

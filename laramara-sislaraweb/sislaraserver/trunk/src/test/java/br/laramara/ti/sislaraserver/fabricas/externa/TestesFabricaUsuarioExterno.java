package br.laramara.ti.sislaraserver.fabricas.externa;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.dtos.usuario.externo.UsuarioExternoDTO;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.dominio.usuario.externo.UsuarioExterno;

public class TestesFabricaUsuarioExterno {

	@Test(groups = { TiposDeTeste.INTEGRACAO_SEM_BD })
	public void fabrica_usuarioexterno_converte_dominio_para_dto() {
		UsuarioExterno usuarioExterno = ContextoUsuarioExterno.fabricarUsuarioExternoComTodosOsDados();

		UsuarioExternoDTO usuarioExternoDto = new FabricaUsuarioExterno().converterParaDTO(usuarioExterno);

		Assert.assertEquals(usuarioExterno.getId(), usuarioExternoDto.getId());
		Assert.assertEquals(usuarioExterno.getEmail(), usuarioExternoDto.getEmail());
		Assert.assertEquals(usuarioExterno.getNomeCompleto(), usuarioExternoDto.getNomeCompleto());
		Assert.assertEquals(usuarioExterno.getDddETelefoneCelular(), usuarioExternoDto.getDddETelefoneCelular());
		Assert.assertEquals(usuarioExterno.getOutroTelefone(), usuarioExternoDto.getOutroTelefone());
		Assert.assertEquals(usuarioExterno.getCpf(), usuarioExternoDto.getCpf());
		Assert.assertNull(usuarioExternoDto.getSenha());
		Assert.assertEquals(usuarioExterno.isAutorizoRecebimentoInformativo(), usuarioExternoDto.isAutorizoRecebimentoInformativo());
		Assert.assertEquals(usuarioExterno.isBloqueado(), usuarioExternoDto.isBloqueado());
		Assert.assertEquals(usuarioExterno.getDataNascimento(), usuarioExternoDto.getDataNascimento());
		Assert.assertEquals(usuarioExterno.getRgRne(), usuarioExternoDto.getRgRne());
		Assert.assertNotNull(usuarioExterno.getEnderecoResidencial());
		Assert.assertEquals(usuarioExterno.isePessoaComDeficiencia(), usuarioExternoDto.isePessoaComDeficiencia());
		Assert.assertEquals(usuarioExterno.isPossuiBaixaVisao(), usuarioExternoDto.isPossuiBaixaVisao());
		Assert.assertEquals(usuarioExterno.isPossuiCegueira(), usuarioExternoDto.isPossuiCegueira());
		Assert.assertEquals(usuarioExterno.getOutraDeficiencia(), usuarioExternoDto.getOutraDeficiencia());
	}
	
	@Test(groups = { TiposDeTeste.INTEGRACAO_SEM_BD })
	public void fabrica_usuarioexterno_converte_dto_para_dominio() {
		UsuarioExternoDTO usuarioExternoDto = ContextoUsuarioExterno.fabricarUsuarioExternoDTOComTodosOsDados();

		UsuarioExterno usuarioExterno = new FabricaUsuarioExterno().converterParaDominio(usuarioExternoDto);

		Assert.assertEquals(usuarioExternoDto.getId(), usuarioExterno.getId());
		Assert.assertEquals(usuarioExternoDto.getEmail(), usuarioExterno.getEmail());
		Assert.assertEquals(usuarioExternoDto.getNomeCompleto(), usuarioExterno.getNomeCompleto());
		Assert.assertEquals(usuarioExternoDto.getDddETelefoneCelular(), usuarioExterno.getDddETelefoneCelular());
		Assert.assertEquals(usuarioExternoDto.getOutroTelefone(), usuarioExterno.getOutroTelefone());
		Assert.assertEquals(usuarioExternoDto.getCpf(), usuarioExterno.getCpf());
		Assert.assertEquals(usuarioExternoDto.isAutorizoRecebimentoInformativo(), usuarioExterno.isAutorizoRecebimentoInformativo());
		Assert.assertEquals(usuarioExternoDto.isBloqueado(), usuarioExterno.isBloqueado());
		Assert.assertEquals(usuarioExternoDto.getDataNascimento(), usuarioExterno.getDataNascimento());
		Assert.assertEquals(usuarioExternoDto.getRgRne(), usuarioExterno.getRgRne());
		Assert.assertNotNull(usuarioExternoDto.getEnderecoResidencial());
		Assert.assertEquals(usuarioExternoDto.isePessoaComDeficiencia(), usuarioExterno.isePessoaComDeficiencia());
		Assert.assertEquals(usuarioExternoDto.isPossuiBaixaVisao(), usuarioExterno.isPossuiBaixaVisao());
		Assert.assertEquals(usuarioExternoDto.isPossuiCegueira(), usuarioExterno.isPossuiCegueira());
		Assert.assertEquals(usuarioExternoDto.getOutraDeficiencia(), usuarioExterno.getOutraDeficiencia());
	}
}
package br.laramara.ti.sislaraserver.fabricas.externa;

import br.laramara.ti.sislaracommons.dtos.usuario.externo.UsuarioExternoDTO;
import br.laramara.ti.sislaraserver.dominio.usuario.externo.UsuarioExterno;
import br.laramara.ti.sislaraserver.fabricas.ContextoEndereco;

public class ContextoUsuarioExterno {

	public static UsuarioExterno fabricarUsuarioExternoComTodosOsDados() {
		UsuarioExterno usuarioExterno = new UsuarioExterno();

		usuarioExterno.setId(1111L);
		usuarioExterno.setEmail("carloskafka7@gmail.com");
		usuarioExterno.setNomeCompleto("Carlos Henrique da Silva Kafka");
		usuarioExterno.setDddETelefone("11981847597");
		usuarioExterno.setOutroTelefone("11953442968");
		usuarioExterno.setSenha("1234");
		usuarioExterno.setCpf("04091664059");
		usuarioExterno.setAutorizoRecebimentoInformativo(true);
		usuarioExterno.setBloqueado(false);
		usuarioExterno.setDataNascimento("27/09/1993");
		usuarioExterno.setRgRne("333474685");
		usuarioExterno.setEnderecoResidencial(ContextoEndereco.construirEnderecoSemPaisEZona());
		usuarioExterno.setePessoaComDeficiencia(true);
		usuarioExterno.setPossuiBaixaVisao(true);	
		usuarioExterno.setPossuiCegueira(false);
		usuarioExterno.setOutraDeficiencia("");

		return usuarioExterno;
	}

	public static UsuarioExternoDTO fabricarUsuarioExternoDTOComTodosOsDados() {
		UsuarioExternoDTO usuarioExternoDto = new UsuarioExternoDTO();

		usuarioExternoDto.setId(1111L);
		usuarioExternoDto.setEmail("carloskafka7@gmail.com");
		usuarioExternoDto.setNomeCompleto("Carlos Henrique da Silva Kafka");
		usuarioExternoDto.setDddETelefoneCelular("11981847597");
		usuarioExternoDto.setOutroTelefone("11953442968");
		usuarioExternoDto.setSenha("1234");
		usuarioExternoDto.setCpf("04091664059");
		usuarioExternoDto.setAutorizoRecebimentoInformativo(true);
		usuarioExternoDto.setBloqueado(false);
		usuarioExternoDto.setDataNascimento("27/09/1993");
		usuarioExternoDto.setRgRne("333474685");
		usuarioExternoDto.setEnderecoResidencial(ContextoEndereco.construirEnderecoDTOSemPaisEZona());
		usuarioExternoDto.setePessoaComDeficiencia(true);
		usuarioExternoDto.setPossuiBaixaVisao(true);	
		usuarioExternoDto.setPossuiCegueira(false);
		usuarioExternoDto.setOutraDeficiencia("");
		
		return usuarioExternoDto;
	}
	
	public static UsuarioExternoDTO fabricarUsuarioExternoInvalidoDTO() {
		UsuarioExternoDTO usuarioExternoDto = fabricarUsuarioExternoDTOComTodosOsDados();

		usuarioExternoDto.setId(1222L);
		usuarioExternoDto.setEmail("a@gmail.com");
		usuarioExternoDto.setNomeCompleto("Carlos Henrique da Silva Kafka");
		usuarioExternoDto.setOutroTelefone("1234");
		usuarioExternoDto.setCpf("123456");
		usuarioExternoDto.setDataNascimento("48372974");
		usuarioExternoDto.setRgRne("102427872");
		usuarioExternoDto.setEnderecoResidencial(null);

		return usuarioExternoDto;
	}
	
	public static UsuarioExternoDTO fabricarSegundoUsuarioExternoDTOComTodosOsDados() {
		UsuarioExternoDTO usuarioExternoDto = fabricarUsuarioExternoDTOComTodosOsDados();

		usuarioExternoDto.setId(10001L);
		usuarioExternoDto.setEmail("carlos_kafka@hotmail.com");
		usuarioExternoDto.setNomeCompleto("Carlos Henrique da Silva Kafka");
		usuarioExternoDto.setOutroTelefone("1234");
		usuarioExternoDto.setCpf("123456");
		usuarioExternoDto.setBloqueado(false);
		usuarioExternoDto.setDataNascimento("48372974");
		usuarioExternoDto.setRgRne("102427872");
		
		return usuarioExternoDto;
	}
}


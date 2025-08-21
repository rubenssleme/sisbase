package br.laramara.ti.sislaraserver.fabricas.externa;

import br.laramara.ti.sislaracommons.dtos.usuario.externo.UsuarioExternoDTO;
import br.laramara.ti.sislaraserver.dominio.usuario.externo.UsuarioExterno;
import br.laramara.ti.sislaraserver.fabricas.FabricaEndereco;
import br.laramara.ti.sislaraserver.fabricas.FabricaRecursiva;

public class FabricaUsuarioExterno extends FabricaRecursiva<UsuarioExternoDTO, UsuarioExterno> {

	@Override
	public UsuarioExternoDTO converterParaDTO(UsuarioExterno usuarioExterno) {
		UsuarioExternoDTO usuarioExternoDTO = null;
		if (usuarioExterno != null) {
			usuarioExternoDTO = new UsuarioExternoDTO();

			if (usuarioExterno.getId() != null) {
				usuarioExternoDTO.setId(usuarioExterno.getId());
			}

			usuarioExternoDTO.setNomeCompleto(usuarioExterno.getNomeCompleto());
			usuarioExternoDTO.setDddETelefoneCelular(usuarioExterno.getDddETelefoneCelular());
			usuarioExternoDTO.setOutroTelefone(usuarioExterno.getOutroTelefone());
			usuarioExternoDTO.setCpf(usuarioExterno.getCpf());
			usuarioExternoDTO.setEmail(usuarioExterno.getEmail());
			usuarioExternoDTO.setDataNascimento(usuarioExterno.getDataNascimento());
			usuarioExternoDTO.setRgRne(usuarioExterno.getRgRne());
			usuarioExternoDTO.setAutorizoRecebimentoInformativo(usuarioExterno.isAutorizoRecebimentoInformativo());
			usuarioExternoDTO.setBloqueado(usuarioExterno.isBloqueado());
			usuarioExternoDTO.setEnderecoResidencial(new FabricaEndereco().converterParaDTO(usuarioExterno.getEnderecoResidencial()));
			usuarioExternoDTO.setePessoaComDeficiencia(usuarioExterno.isePessoaComDeficiencia());
			usuarioExternoDTO.setPossuiBaixaVisao(usuarioExterno.isPossuiBaixaVisao());
			usuarioExternoDTO.setPossuiCegueira(usuarioExterno.isPossuiCegueira());
			usuarioExternoDTO.setOutraDeficiencia(usuarioExterno.getOutraDeficiencia());
		}

		return usuarioExternoDTO;
	}
	
	@Override
	public UsuarioExterno converterParaDominio(UsuarioExternoDTO usuarioExternoDto, UsuarioExterno usuarioExterno) {
		if (usuarioExterno != null) {
			if (usuarioExternoDto != null) {
				if (usuarioExternoDto.getId() != null) {
					usuarioExterno.setId(usuarioExternoDto.getId());
				}

				usuarioExterno.setNomeCompleto(usuarioExternoDto.getNomeCompleto());
				usuarioExterno.setSenha(usuarioExternoDto.getSenha());
				usuarioExterno.setDddETelefone(usuarioExternoDto.getDddETelefoneCelular());
				usuarioExterno.setOutroTelefone(usuarioExternoDto.getOutroTelefone());
				usuarioExterno.setCpf(usuarioExternoDto.getCpf());
				usuarioExterno.setEmail(usuarioExternoDto.getEmail());
				usuarioExterno.setDataNascimento(usuarioExternoDto.getDataNascimento());
				usuarioExterno.setRgRne(usuarioExternoDto.getRgRne());
				usuarioExterno.setAutorizoRecebimentoInformativo(usuarioExternoDto.isAutorizoRecebimentoInformativo());
				usuarioExterno.setBloqueado(usuarioExternoDto.isBloqueado());
				usuarioExterno.setEnderecoResidencial(
						new FabricaEndereco().converterParaDominio(usuarioExternoDto.getEnderecoResidencial()));
				usuarioExterno.setePessoaComDeficiencia(usuarioExternoDto.isePessoaComDeficiencia());
				usuarioExterno.setPossuiBaixaVisao(usuarioExternoDto.isPossuiBaixaVisao());
				usuarioExterno.setPossuiCegueira(usuarioExternoDto.isPossuiCegueira());
				usuarioExterno.setOutraDeficiencia(usuarioExternoDto.getOutraDeficiencia());
			}
		}

		return usuarioExterno;
	}

	@Override
	public UsuarioExterno converterParaDominio(UsuarioExternoDTO usuarioExternoDto) {
		return converterParaDominio(usuarioExternoDto, obterNovo());
	}
	
	@Override
	public UsuarioExterno obterNovo() {
		return new UsuarioExterno();
	}

}
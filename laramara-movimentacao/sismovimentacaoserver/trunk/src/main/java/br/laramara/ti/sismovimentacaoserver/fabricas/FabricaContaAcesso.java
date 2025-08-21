package br.laramara.ti.sismovimentacaoserver.fabricas;

import br.laramara.ti.sismovimentacaocommons.dtos.seguranca.ContaAcessoDTO;
import br.laramara.ti.sismovimentacaoserver.dominio.seguranca.ContaAcesso;

public class FabricaContaAcesso extends FabricaRecursiva<ContaAcessoDTO, ContaAcesso> {

	public final ContaAcessoDTO converterParaDTO(ContaAcesso contaAcesso) {
		ContaAcessoDTO contaAcessoDto = null;
		if (contaAcesso != null) {
			contaAcessoDto = new ContaAcessoDTO();
			if (contaAcesso.getId() != null) {
				contaAcessoDto.setId(contaAcesso.getId());
			}
			contaAcessoDto.setUsuario(contaAcesso.getLogin());
			contaAcessoDto.setPerfilDto(new FabricaPerfil()
					.converterParaDTO(contaAcesso.getPerfil()));
			contaAcessoDto.setBloqueado(contaAcesso.isBloqueado());
			contaAcessoDto.setProfissionalDto(new FabricaProfissional()
					.converterParaDTO(contaAcesso.getProfissional()));
			contaAcessoDto.setPalavraChaveGrupo(contaAcesso
					.getPalavraChaveGrupo());
		}
		return contaAcessoDto;
	}

	@Override
	public ContaAcesso converterParaDominio(ContaAcessoDTO contaAcessoDto,
			ContaAcesso contaAcesso) {
		if (contaAcessoDto != null) {
			if (contaAcesso == null) {
				contaAcesso = new ContaAcesso();
			}
			contaAcesso.setId(contaAcessoDto.getId());
			contaAcesso.setLogin(contaAcessoDto.getLogin());
			contaAcesso.setSenha(contaAcessoDto.getSenha());
			contaAcesso.setPerfil(new FabricaPerfil()
					.converterParaDominio(contaAcessoDto.getPerfilDto()));
			contaAcesso.setBloqueado(contaAcessoDto.isBloqueado());
			contaAcesso.setProfissional(new FabricaProfissional()
					.converterParaDominio(contaAcessoDto.getProfissionalDto()));
			contaAcesso.setPalavraChaveGrupo(contaAcessoDto
					.getPalavraChaveGrupo());
		}
		return contaAcesso;
	}

	@Override
	public ContaAcesso obterNovo() {
		return new ContaAcesso();
	}
}

package br.laramara.sistelemarketingserver.fabricas;

import br.laramara.sistelemarketingcommons.dtos.seguranca.ContaAcessoDTO;
import br.laramara.sistelemarketingserver.dominio.seguranca.ContaAcesso;

public class ContaAcessoFabrica extends FabricaRecursiva<ContaAcessoDTO, ContaAcesso> {
	public final ContaAcessoDTO converterParaDTO(ContaAcesso contaAcesso) {
		ContaAcessoDTO contaAcessoDto = new ContaAcessoDTO();
		if (contaAcesso != null) {
			contaAcessoDto.setId(contaAcesso.getId());
			contaAcessoDto.setNome(contaAcesso.getNome());
			contaAcessoDto.setLogin(contaAcesso.getLogin());
			contaAcessoDto.setAtivo(contaAcesso.isAtivo());
			contaAcessoDto.setNivelDto(new NivelFabrica().converterParaDTO(contaAcesso.getNivel()));
			contaAcessoDto.setTurnoDto(new TurnoFabrica().converterParaDTO(contaAcesso.getTurno()));
			contaAcessoDto.setRamalDto(new RamalFabrica().converterParaDTO(contaAcesso.getRamal()));
		}
		return contaAcessoDto;
	}
	
	@Override
	public ContaAcesso converterParaDominio(ContaAcessoDTO contaAcessoDto, ContaAcesso contaAcesso) {
		if (contaAcessoDto != null) {
			if (contaAcesso == null) {
				contaAcesso = obterNovo();
			}
			contaAcesso.setId(contaAcessoDto.getId());
			contaAcesso.setNome(contaAcessoDto.getNome());
			contaAcesso.setLogin(contaAcessoDto.getLogin());
			contaAcesso.setSenha(contaAcessoDto.getSenha());
			contaAcesso.setAtivo(contaAcessoDto.isAtivo());
			contaAcesso.setNivel(new NivelFabrica().converterParaDominio(contaAcessoDto.getNivelDto()));
			contaAcesso.setTurno(new TurnoFabrica().converterParaDominio(contaAcessoDto.getTurnoDto()));
		}
		return contaAcesso;
	}

	@Override
	public ContaAcesso obterNovo() {
		return new ContaAcesso();
	}
}

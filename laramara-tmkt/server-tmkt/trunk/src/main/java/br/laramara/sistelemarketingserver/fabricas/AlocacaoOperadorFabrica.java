package br.laramara.sistelemarketingserver.fabricas;

import br.laramara.sistelemarketingcommons.dtos.campanha.AlocacaoOperadorDTO;
import br.laramara.sistelemarketingserver.dominio.campanha.AlocacaoOperador;

public class AlocacaoOperadorFabrica extends FabricaBase<AlocacaoOperadorDTO, AlocacaoOperador> {
	public final AlocacaoOperadorDTO converterParaDTO(AlocacaoOperador alocacaoOperador) {
		AlocacaoOperadorDTO alocacaoOperadorDto = new AlocacaoOperadorDTO();
		if (alocacaoOperador != null) {
			alocacaoOperadorDto.setId(alocacaoOperador.getId());
			alocacaoOperadorDto
					.setContaAcessoDto(new ContaAcessoFabrica().converterParaDTO(alocacaoOperador.getContaAcesso()));
			alocacaoOperadorDto.setMetaFinanceira(alocacaoOperador.getMetaFinanceira());
			alocacaoOperadorDto.setMetaQuantidadeLigacoes(alocacaoOperador.getMetaQuantidadeLigacoes());
		}
		return alocacaoOperadorDto;
	}

	public final AlocacaoOperador converterParaDominio(AlocacaoOperadorDTO alocacaoOperadorDto) {
		AlocacaoOperador alocacaoOperador = new AlocacaoOperador();
		if (alocacaoOperadorDto != null) {
			alocacaoOperador.setId(alocacaoOperadorDto.getId());
			alocacaoOperador.setContaAcesso(
					new ContaAcessoFabrica().converterParaDominio(alocacaoOperadorDto.getContaAcessoDto()));
			alocacaoOperador.setMetaFinanceira(alocacaoOperadorDto.getMetaFinanceira());
			alocacaoOperador.setMetaQuantidadeLigacoes(alocacaoOperadorDto.getMetaQuantidadeLigacoes());
		}
		return alocacaoOperador;
	}
}

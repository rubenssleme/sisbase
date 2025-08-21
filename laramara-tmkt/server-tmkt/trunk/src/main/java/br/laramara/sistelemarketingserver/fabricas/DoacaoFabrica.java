package br.laramara.sistelemarketingserver.fabricas;

import br.laramara.sistelemarketingcommons.dtos.doacao.DoacaoDTO;
import br.laramara.sistelemarketingserver.dominio.doacao.Doacao;

public class DoacaoFabrica extends FabricaRecursiva<DoacaoDTO, Doacao> {
	public final DoacaoDTO converterParaDTO(Doacao doacao) {
		DoacaoDTO doacaoDto = new DoacaoDTO();
		if (doacao != null) {
			doacaoDto.setId(doacao.getId());
			doacaoDto.setValoresDetalhadosDto(
					new ValorDetalhadoFabrica().converterParaDTO(doacao.getValoresDetalhados()));
			doacaoDto.setDistribuicaoContatoDto(
					new DistribuicaoContatoFabrica().converterParaDTO(doacao.getDistribuicaoContato()));
		}
		return doacaoDto;
	}

	@Override
	public Doacao converterParaDominio(DoacaoDTO doacaoDto, Doacao doacao) {
		if (doacaoDto != null) {
			if (doacao == null) {
				doacao = obterNovo();
			}
			doacao.setValoresDetalhados(
					new ValorDetalhadoFabrica().converterParaDominio(doacaoDto.getValoresDetalhadosDto()));
			doacao.setDistribuicaoContato(
					new DistribuicaoContatoFabrica().converterParaDominio(doacaoDto.getDistribuicaoContatoDto()));
		}
		return doacao;
	}

	@Override
	public Doacao obterNovo() {
		return new Doacao();
	}
}

package br.laramara.ti.sislaraserver.fabricas;

import br.laramara.ti.sislaracommons.dtos.doacao.ReciboDTO;
import br.laramara.ti.sislaraserver.dominio.doacao.Recibo;

public class FabricaRecibo extends FabricaRecursiva<ReciboDTO, Recibo>  {
	
	public final ReciboDTO converterParaDTO(Recibo recibo) {
		ReciboDTO reciboDto = null;
		if (recibo != null) {
			reciboDto = new ReciboDTO();
			reciboDto.setId(recibo.getId());
			reciboDto.setCpfCnpj(recibo.getCpfCnpj());
			reciboDto.setData(recibo.obterData());
			reciboDto.setDataRegistro(recibo.obterDataRegistro());
			reciboDto.setDescricao(recibo.getDescricao());
			reciboDto.setCancelado(recibo.estaCancelado());
			reciboDto.setMotivoDoCancelamento(recibo.getMotivoDoCancelamento());
			reciboDto.setFilialDTO(new FabricaFilial().converterParaDTO(recibo.getFilial()));
			reciboDto.setNome(recibo.getNome());
			reciboDto.setValorTotalRecibo(recibo.obterValorTotalRecibo());
			reciboDto.setHistoricoOperacoes(recibo.getHistoricoOperacoes());
		}
		return reciboDto;
	}

	public final Recibo converterParaDominio(ReciboDTO reciboDto, Recibo recibo) {
		if (reciboDto != null) {
			if (recibo == null) {
				recibo = new Recibo();
			}
			recibo.setId(reciboDto.getId());
			recibo.setCpfCnpj(reciboDto.getCpfCnpj());
			recibo.setData(reciboDto.getData());
			recibo.setDescricao(reciboDto.getDescricao());
			recibo.setMotivoDoCancelamento(reciboDto.getMotivoDoCancelamento());
			recibo.setFilial(new FabricaFilial().converterParaDominio(reciboDto.getFilial()));
			recibo.setNome(reciboDto.getNome());
			recibo.setValorTotalRecibo(reciboDto.getValorTotalRecibo());
		}
		return recibo;
	}
	
	@Override
	public Recibo obterNovo() {
		return new Recibo();
	}
}

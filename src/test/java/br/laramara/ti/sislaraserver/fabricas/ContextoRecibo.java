package br.laramara.ti.sislaraserver.fabricas;

import br.laramara.ti.sislaracommons.dtos.doacao.ReciboDTO;
import br.laramara.ti.sislaraserver.dominio.doacao.Recibo;
import br.laramara.ti.sislaraserver.dominio.doacao.StatusRecibo;
import br.laramara.ti.sislaraserver.dominio.seguranca.ContaAcesso;

public class ContextoRecibo {

	public static Recibo fabricarRecibo() {
		Recibo recibo = new Recibo();
		recibo.setId(new Long(9999));
		recibo.setCpfCnpj("71894810287");
		recibo.setData("01/01/2000");
		recibo.setDescricao("Descrição");
		recibo.setFilial(ContextoFilial.fabricarDominio());
		recibo.setNome("Paulo Augusto");
		recibo.setValorTotalRecibo("1000,00");
		recibo.setContaAcessoResponsavelOperacao(ContaAcesso.obterAcessoRoot());
		recibo.adicionarStatusRecibo(StatusRecibo.NORMAL);
		return recibo;
	}

	public static ReciboDTO fabricarReciboDto() {
		ReciboDTO reciboDto = new ReciboDTO();
		reciboDto.setId(new Long(9999));
		reciboDto.setCpfCnpj("71894810287");
		reciboDto.setData("01/01/2000");
		reciboDto.setDescricao("Descricao");
		reciboDto.setMotivoDoCancelamento("Descrição do texto.");
		reciboDto.setFilialDTO(ContextoFilial.fabricarDto());
		reciboDto.setNome("Paulo Augusto");
		reciboDto.setValorTotalRecibo("1000,00");
		return reciboDto;
	}

	public static ReciboDTO fabricarReciboAlternativoDto() {
		ReciboDTO reciboDTO = fabricarReciboDto();
		reciboDTO.setValorTotalRecibo("10000,00");
		return reciboDTO;
	}
}

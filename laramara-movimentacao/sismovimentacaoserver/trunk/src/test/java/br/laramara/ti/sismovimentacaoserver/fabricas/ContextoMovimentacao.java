package br.laramara.ti.sismovimentacaoserver.fabricas;

import br.laramara.ti.sismovimentacaocommons.dtos.movimentacao.AbdbDTO;
import br.laramara.ti.sismovimentacaocommons.dtos.movimentacao.FibraDTO;
import br.laramara.ti.sismovimentacaocommons.dtos.movimentacao.MovimentacaoDTO;
import br.laramara.ti.sismovimentacaocommons.dtos.movimentacao.PapelDTO;
import br.laramara.ti.sismovimentacaocommons.dtos.movimentacao.SimNaoDTO;
import br.laramara.ti.sismovimentacaoserver.dominio.SimNao;
import br.laramara.ti.sismovimentacaoserver.dominio.movimentacao.ABDB;
import br.laramara.ti.sismovimentacaoserver.dominio.movimentacao.Fibra;
import br.laramara.ti.sismovimentacaoserver.dominio.movimentacao.Movimentacao;
import br.laramara.ti.sismovimentacaoserver.dominio.movimentacao.Papel;

public class ContextoMovimentacao {

	public static MovimentacaoDTO construirMovimentacaoDTO() {
		MovimentacaoDTO movimentacaoDto = new MovimentacaoDTO();
		movimentacaoDto.setId(new Long(12));
		movimentacaoDto.setGl("GL23443");
		movimentacaoDto.setCliente("JOSEP");
		movimentacaoDto.setCodigoProduto("7346");
		movimentacaoDto.setDescricao("EMXICLAN");
		movimentacaoDto.setDescricaoProduto("BULA EXONA");
		movimentacaoDto.setQuantidadeCor("1X1");
		movimentacaoDto.setCor("PRETO");
		movimentacaoDto.setDirecaoFibra("233");
		movimentacaoDto.setFormato("43x34");
		movimentacaoDto.setCodigoAnterior("343434");
		movimentacaoDto.setGramatura("43");
		movimentacaoDto.setLaetus("2323");
		movimentacaoDto.setObsArte("Obs arete");
		movimentacaoDto.setObsEspecificacao("Obs espe");
		movimentacaoDto.setFibraDto(new FibraDTO(Fibra.HORIZONTAL.toString()));
		movimentacaoDto.setAbdbDto(new AbdbDTO(ABDB.AB.toString()));
		movimentacaoDto.setPapelDto(new PapelDTO(Papel.COUCHE.toString()));
		movimentacaoDto.setEspecificacaoSimNaoDto(new SimNaoDTO(SimNao.NAO
				.toString()));
		movimentacaoDto
				.setSangriaSimNaoDto(new SimNaoDTO(SimNao.NAO.toString()));
		movimentacaoDto.setGr("123232");
		movimentacaoDto.setPasta("23434");
		movimentacaoDto.setBobina("3489");
		movimentacaoDto.setPlanaPapel("2323");
		movimentacaoDto.setTipoProva("CD");
		return movimentacaoDto;
	}

	public static MovimentacaoDTO construirMovimentacaoDtoSemId(){
		MovimentacaoDTO movimentacaoDTO = construirMovimentacaoDTO();
		movimentacaoDTO.setId(null);
		return movimentacaoDTO;
	}
	public static Movimentacao fabricarComTodosOsDados() {
		Movimentacao movimentacao = new Movimentacao();
		movimentacao.setId(new Long(13));
		movimentacao.setGl("Gl473624");
		movimentacao.setCliente("JOSEP");
		movimentacao.setCodigoProduto("43786");
		movimentacao.setDescricao("MEXICLAN");
		movimentacao.setDescricaoProduto("BULA EXONA");
		movimentacao.setQuantidadeCor("1X1");
		movimentacao.setCor("PRETO");
		movimentacao.setDirecaoFibra("233");
		movimentacao.setFormato("43x34");
		movimentacao.setCodigoAnterior("343434");
		movimentacao.setGramatura("43");
		movimentacao.setLaetus("34987");
		movimentacao.setObsEspecificacao("Obs arte");
		movimentacao.setObsArte("Obs arte");
		movimentacao.setFibra(Fibra.HORIZONTAL);
		movimentacao.setAbdb(ABDB.AB);
		movimentacao.setPapel(Papel.COUCHE);
		movimentacao.setEspecificacao(SimNao.NAO);
		movimentacao.setSangria(SimNao.NAO);
		movimentacao.setGr("123232");
		movimentacao.setPasta("23434");
		movimentacao.setBobina("3489");
		movimentacao.setPlanaPapel("2323");
		movimentacao.setTipoProva("CD");
		return movimentacao;
	}

}

package br.laramara.ti.sismovimentacaoserver.fachadas.operacoes;

import br.laramara.ti.sismovimentacaocommons.dtos.ResultadoDTO;
import br.laramara.ti.sismovimentacaoserver.dominio.seguranca.ContaAcesso;

public interface OperacaoFachada {
	public ResultadoDTO processar(ContaAcesso contaAcesso, ResultadoDTO resultadoDto);
}

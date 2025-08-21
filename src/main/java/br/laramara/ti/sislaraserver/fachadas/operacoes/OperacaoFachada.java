package br.laramara.ti.sislaraserver.fachadas.operacoes;

import br.laramara.ti.sislaracommons.dtos.ResultadoDTO;
import br.laramara.ti.sislaraserver.dominio.seguranca.ContaAcesso;

public interface OperacaoFachada {
	public ResultadoDTO processar(ContaAcesso contaAcesso, ResultadoDTO resultadoDto);
}

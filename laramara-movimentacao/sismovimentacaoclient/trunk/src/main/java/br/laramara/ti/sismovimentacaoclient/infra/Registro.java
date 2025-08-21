package br.laramara.ti.sismovimentacaoclient.infra;

import br.laramara.ti.sismovimentacaocommons.servicos.ServicoSisMovimentacaoServer;

public class Registro {
    public static ServicoSisMovimentacaoServer obterServicoSisMovimentacaoServer() {
        return (ServicoSisMovimentacaoServer) (SpringInfra.obterInstancia().getBean("servicoSisMovimentacaoServer"));
    }
}

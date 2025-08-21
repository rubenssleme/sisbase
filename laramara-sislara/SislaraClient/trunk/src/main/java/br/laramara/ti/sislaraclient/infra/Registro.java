package br.laramara.ti.sislaraclient.infra;

import br.laramara.ti.sislaracommons.servicos.ServicoSisLaraServer;

public class Registro {
    public static ServicoSisLaraServer obterServicoSisLaraServer() {
        return (ServicoSisLaraServer) (SpringInfra.obterInstancia().getBean("servicoSisLaraServer"));
    }
}

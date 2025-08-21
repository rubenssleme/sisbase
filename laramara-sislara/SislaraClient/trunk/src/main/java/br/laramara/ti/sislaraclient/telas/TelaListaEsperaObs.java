
package br.laramara.ti.sislaraclient.telas;

import br.laramara.ti.sislaraclient.controladores.ControladorTelaListaEsperaObs;
import br.laramara.ti.sislaracommons.dtos.espera.EsperaDTO;
import javax.swing.JDialog;

public class TelaListaEsperaObs extends TelaListaEsperaMudancaStatus {

    public TelaListaEsperaObs(JDialog telaPai, EsperaDTO esperaDto) {
        super("Ver/Alterar a OBS/Data de Expectativa", telaPai, esperaDto);
        exibirCampoData();
        controlador = new ControladorTelaListaEsperaObs(this, esperaDto, jepJustificativa, jepObservacoesHistoricas, jftDataExpectativa, jchInteresse, jftDataLigacaoInteresseUsuario, jchLigou, jchPendencias);
        controlador.abrirTela();
    }
}

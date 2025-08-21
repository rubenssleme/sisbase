package br.laramara.ti.sislaraclient.telas;

import br.laramara.ti.sislaraclient.controladores.ControladorTelaListaEsperaConclusao;
import br.laramara.ti.sislaracommons.dtos.espera.EsperaDTO;
import javax.swing.JDialog;

public class TelaListaEsperaConclusao extends TelaListaEsperaMudancaStatus {

    public TelaListaEsperaConclusao(JDialog telaPai, EsperaDTO esperaDto) {
        super("Conclusão de Espera", telaPai, esperaDto);
        controlador = new ControladorTelaListaEsperaConclusao(this, esperaDto, jepJustificativa);
        controlador.abrirTela();
    }
}

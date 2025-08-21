
package br.laramara.ti.sislaraclient.telas;

import br.laramara.ti.sislaraclient.controladores.ControladorTelaListaEsperaCancelamento;
import br.laramara.ti.sislaracommons.dtos.espera.EsperaDTO;
import javax.swing.JDialog;

public class TelaListaEsperaCancelamento extends TelaListaEsperaMudancaStatus {
 
    public TelaListaEsperaCancelamento(JDialog telaPai, EsperaDTO esperaDto){
        super("Cancelamento de Espera", telaPai, esperaDto);
        controlador = new ControladorTelaListaEsperaCancelamento(this, esperaDto, jepJustificativa);
        controlador.abrirTela();
    }
}

package br.laramara.ti.sislaraclient.telas;

import br.laramara.ti.sislaraclient.controladores.ControladorRelatorioApartirDeProntuario;
import br.laramara.ti.sislaraclient.controladores.ControladorTelaRelatorioFolhaDeRosto;
import javax.swing.JFrame;

public class TelasRelatorioFolhaDeRosto extends TelaRelatorioApartirDeProntuario {
    
    public TelasRelatorioFolhaDeRosto(JFrame telaPai){
        super(telaPai, "Relatório de Folha de Rosto");
    }

    @Override
    protected ControladorRelatorioApartirDeProntuario obterControlador() {
        return new ControladorTelaRelatorioFolhaDeRosto(this, jftProntuario);
    }
}

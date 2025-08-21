package br.laramara.ti.sislaraclient.telas;

import br.laramara.ti.sislaraclient.controladores.ControladorTelaRetirada;
import br.laramara.ti.sislaraclient.controladores.ControladorTelaRetiradaBaixar;
import javax.swing.JFrame;

public class TelaRetiradaBaixar extends TelaRetirada {
    public TelaRetiradaBaixar(JFrame telaPai){
        super(telaPai, false, "Baixar Retirada");
    }

    @Override
    protected ControladorTelaRetirada obterControlador() {
         return new ControladorTelaRetiradaBaixar(this, jftProntuario, jtfNome);
    }
}

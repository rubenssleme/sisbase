
package br.laramara.ti.sislaraclient.telas;

import br.laramara.ti.sislaraclient.controladores.ControladorTelaRetirada;
import br.laramara.ti.sislaraclient.controladores.ControladorTelaRetiradaEfetuar;
import javax.swing.JFrame;

public class TelaRetiradaEfetuar extends TelaRetirada {
    public TelaRetiradaEfetuar(JFrame telaPai){
        super(telaPai, true, "Efetuar Retirada");
    }

    @Override
    protected ControladorTelaRetirada obterControlador() {
        return new ControladorTelaRetiradaEfetuar(this, jftProntuario, jtfNome, jcbProfissional,  jcbVoluntario);
    }
}


package br.laramara.ti.sislaraclient.telas;

import br.laramara.ti.sislaraclient.controladores.ControladorTelaEditar;
import br.laramara.ti.sislaraclient.controladores.ControladorTelaEditarPeriodoDoenca;
import br.laramara.ti.sislaracommons.dtos.ModeloDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.PeriodoDoencaDTO;
import javax.swing.JDialog;

public class TelaEditarPeriodoDoenca extends TelaEditarComboEDataInicioFinal{
    
    public TelaEditarPeriodoDoenca(JDialog parent, PeriodoDoencaDTO periodoDoencaDTO){
        super("Doença", parent, periodoDoencaDTO, false, false, true);
    }

    @Override
    protected ControladorTelaEditar obterControlador(ModeloDTO modeloDto) {
        return new ControladorTelaEditarPeriodoDoenca(this, (PeriodoDoencaDTO)modeloDto, jcbObjetos, jftDataIncial, jftDataFinal, jepObservacao);
    }
}

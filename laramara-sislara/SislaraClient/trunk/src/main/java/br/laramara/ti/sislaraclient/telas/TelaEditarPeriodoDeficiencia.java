
package br.laramara.ti.sislaraclient.telas;

import br.laramara.ti.sislaraclient.controladores.ControladorTelaEditar;
import br.laramara.ti.sislaraclient.controladores.ControladorTelaEditarPeriodoDeficiencia;
import br.laramara.ti.sislaracommons.dtos.ModeloDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.PeriodoDeficienciaDTO;
import javax.swing.JDialog;

public class TelaEditarPeriodoDeficiencia extends TelaEditarComboEDataInicioFinal{
    
    public TelaEditarPeriodoDeficiencia(JDialog parent, PeriodoDeficienciaDTO periodoDeficienciaDTO){
        super("Deficiência", parent, periodoDeficienciaDTO, true, false, false);
    }

    @Override
    protected ControladorTelaEditar obterControlador(ModeloDTO modeloDto) {
        return new ControladorTelaEditarPeriodoDeficiencia(this, (PeriodoDeficienciaDTO)modeloDto, jcbObjetos, jftDataIncial, jftDataFinal, jtfCidEtiologia, jlaCidEtiologia, jliEtiologiasAdicionadas, jtfCID, jlaCid, jcbEpocaIncidencia);
    }
}

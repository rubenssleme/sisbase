package br.laramara.ti.sislaraclient.telas;

import br.laramara.ti.sislaraclient.controladores.ControladorTelaEditar;
import br.laramara.ti.sislaraclient.controladores.ControladorTelaEditarPeriodoBeneficio;
import br.laramara.ti.sislaracommons.dtos.ModeloDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.PeriodoBeneficioDTO;
import javax.swing.JDialog;

public class TelaEditarPeriodoBeneficio extends TelaEditarComboEDataInicioFinal{
    
    public TelaEditarPeriodoBeneficio(JDialog parent, PeriodoBeneficioDTO periodoBeneficioDTO){
        super("Benefícios", parent, periodoBeneficioDTO, false, true, false);
    }

    @Override
    protected ControladorTelaEditar obterControlador(ModeloDTO modeloDto) {
        return new ControladorTelaEditarPeriodoBeneficio(this, (PeriodoBeneficioDTO)modeloDto, jcbObjetos, jftDataIncial, jftDataFinal, jcbStatus);
    }
}

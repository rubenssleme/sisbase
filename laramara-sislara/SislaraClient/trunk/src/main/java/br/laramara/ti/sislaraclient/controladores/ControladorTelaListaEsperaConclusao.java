
package br.laramara.ti.sislaraclient.controladores;

import br.laramara.ti.sislaracommons.dtos.ResultadoDTO;
import br.laramara.ti.sislaracommons.dtos.espera.EsperaDTO;
import br.laramara.ti.sislaracommons.utilitarios.Sessao;
import javax.swing.JDialog;
import javax.swing.JEditorPane;

public class ControladorTelaListaEsperaConclusao extends ControladorTelaListaEsperaMudancaStatus {
 
    private JEditorPane jepJustificativa;
            
    public ControladorTelaListaEsperaConclusao(JDialog telaPai, EsperaDTO esperaDto, JEditorPane jepJustificativa){
        super(telaPai, esperaDto);
        this.jepJustificativa = jepJustificativa;
        jepJustificativa.setText(obterObjetoDTO().getObs());
    }

    @Override
    protected ResultadoDTO solicitarEdicao() throws Exception {
       return servicoSisLaraServer.concluirEspera(obterObjetoDTO(), Sessao.obterInstancia().obterToken());
    }
        
    @Override
    protected void prepararDtoParaEditar() {
        EsperaDTO esperaDto = obterObjetoDTO();
        esperaDto.setObs(jepJustificativa.getText());
    }
}

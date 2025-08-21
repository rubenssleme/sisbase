package br.laramara.ti.sislaraclient.controladores;

import br.laramara.ti.sislaracommons.dtos.ResultadoDTO;
import br.laramara.ti.sislaracommons.dtos.espera.EsperaDTO;
import br.laramara.ti.sislaracommons.utilitarios.Sessao;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JFormattedTextField;

public class ControladorTelaListaEsperaObs extends ControladorTelaListaEsperaMudancaStatus {
    
    private JFormattedTextField jftData;
    private JEditorPane jepJustificativa;
    private JCheckBox jchInteresse;
    private JFormattedTextField jftDataUltimaLigacaoInteresse;
    private JCheckBox jchLigou;
    private JCheckBox jcbPendencias;
    
    public ControladorTelaListaEsperaObs(JDialog telaPai, EsperaDTO esperaDto, JEditorPane jepJustificativa, JEditorPane jepObservacoesHistoricas, JFormattedTextField jftData, JCheckBox jchInteresse, JFormattedTextField jftDataUltimaLigacaoInteresse, JCheckBox jchLigou, JCheckBox jcbPendencias){
        super(telaPai, esperaDto);
        this.jftData = jftData;
        this.jepJustificativa = jepJustificativa;
        this.jchInteresse = jchInteresse;
        this.jftDataUltimaLigacaoInteresse = jftDataUltimaLigacaoInteresse;
        this.jchLigou = jchLigou;
        this.jcbPendencias = jcbPendencias;
        jepJustificativa.setText(obterObjetoDTO().getObs());
        carregarObservacoesHistoricas(obterObjetoDTO().getInformacaoEssencialDTO(), jepObservacoesHistoricas);
        jftData.setText(obterObjetoDTO().getDataExpectativa());
        jchInteresse.setSelected(obterObjetoDTO().isInteresse());
        jftDataUltimaLigacaoInteresse.setText(obterObjetoDTO().getDataUltimaLigacaoInteresse());
        jchLigou.setSelected(obterObjetoDTO().isLmLigou());
        jcbPendencias.setSelected(obterObjetoDTO().isPendencias());
    }

    @Override
    protected ResultadoDTO solicitarEdicao() throws Exception {
       return servicoSisLaraServer.editarEspera(obterObjetoDTO(), Sessao.obterInstancia().obterToken());
    }
    
    
    @Override
    protected void prepararDtoParaEditar() {
        EsperaDTO esperaDto = obterObjetoDTO();
        esperaDto.setObs(jepJustificativa.getText());
        esperaDto.setDataExpectativa(jftData.getText());
        esperaDto.setInteresse(jchInteresse.isSelected());
        esperaDto.setDataUltimaLigacaoInteresse(jftDataUltimaLigacaoInteresse.getText());
        esperaDto.setLmLigou(jchLigou.isSelected());
        esperaDto.setPendencias(jcbPendencias.isSelected());
    }
}

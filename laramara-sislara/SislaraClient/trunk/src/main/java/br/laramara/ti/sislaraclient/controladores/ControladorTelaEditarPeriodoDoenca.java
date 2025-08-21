
package br.laramara.ti.sislaraclient.controladores;

import br.laramara.ti.sislaracommons.dtos.ResultadoDTO;
import br.laramara.ti.sislaracommons.dtos.ResultadoListagemDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.DoencaDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.PeriodoDoencaDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.ResultadoValidacaoPeriodoDoencaDTO;
import br.laramara.ti.sislaracommons.utilitarios.Sessao;
import java.rmi.RemoteException;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JFormattedTextField;


public class ControladorTelaEditarPeriodoDoenca extends ControladorTelaEditarComboEDataInicioFinal {

    public ControladorTelaEditarPeriodoDoenca(JDialog telaPai, PeriodoDoencaDTO periodoDoencaDto, JComboBox jcbDoenca, JFormattedTextField jftDataInicial, JFormattedTextField jftDataFinal, JEditorPane jepObs){
        super(telaPai, periodoDoencaDto, jcbDoenca, jftDataInicial, jftDataFinal, jepObs);
    }

    @Override
    protected ResultadoListagemDTO obterListagemAuxiliares() throws RemoteException {
        return servicoSisLaraServer.obterListagemDoenca();
    }
    
    @Override
    public String obterChaveSessao() {
        return Sessao.CHAVE_PERIODO_DOENCA;
    }
    
    @Override
    public void inicializarCombosDadosAuxiliares() {
        super.inicializarCombosDadosAuxiliares();
    }
     
    @Override
    protected void prepararDtoParaEditar() {
        obterObjetoDTO().setDoencaDto((DoencaDTO) obterDtoSelecionado (jcbDominio));
        obterObjetoDTO().setDataInicial(jftDataInicial.getText());
        obterObjetoDTO().setDataFinal(jftDataFinal.getText()); 
        obterObjetoDTO().setObs(jepObs.getText());
    }

    protected PeriodoDoencaDTO obterObjetoDTO() {
        return (PeriodoDoencaDTO) objetoDto;
    }
    
    @Override
    public void carregarCampos() {
        selecionarDto(jcbDominio, obterObjetoDTO().getDoencaDto());
        jftDataInicial.setText(obterObjetoDTO().getDataInicial());
        jftDataFinal.setText(obterObjetoDTO().getDataFinal());
        jepObs.setText(obterObjetoDTO().getObs());
    }
    
    @Override
    protected ResultadoDTO solicitarEdicaoDto() throws Exception {
        return (ResultadoValidacaoPeriodoDoencaDTO) servicoSisLaraServer.validarPeriodoDoenca(obterObjetoDTO());
    }
}


package br.laramara.ti.sislaraclient.controladores;

import br.laramara.ti.sislaraclient.utilitarios.JOptionPanePersonalizado;
import br.laramara.ti.sislaracommons.dtos.ModeloDTO;
import br.laramara.ti.sislaracommons.dtos.ResultadoListagemDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.ResultadoListagemEpocaIncidenciaDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.ResultadoListagemStatusBeneficioDTO;
import java.rmi.RemoteException;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTextField;


public abstract class ControladorTelaEditarComboEDataInicioFinal extends ControladorTelaEditar{
    
    protected ResultadoListagemDTO resultadoListagemDTO;
    protected ResultadoListagemEpocaIncidenciaDTO resultadoListagemEpocaIncidenciaDTO;
    protected ResultadoListagemStatusBeneficioDTO resultadoListagemStatusBeneficioDTO;
    
    protected JComboBox jcbDominio;
    protected JFormattedTextField jftDataInicial;
    protected JFormattedTextField jftDataFinal;
    protected JTextField jtfCidEtiologia;
    protected JLabel jlaCidEtiologia;
    protected JList jliEtiologiasAdicionadas;
    protected JTextField jtfCid;
    protected JLabel jlaCid;
    protected JComboBox jcbEpocaIncidencia;
    protected JComboBox jcbStatus;
    protected JEditorPane jepObs;

    public ControladorTelaEditarComboEDataInicioFinal (JDialog telaPai, ModeloDTO objetoDto, JComboBox jcbDominio, JFormattedTextField jftDataInicial, JFormattedTextField jftDataFinal, JTextField jtfCidEtiologia, JLabel jlaCidEtiologia, JList jliEtiologiasAdicionadas, JTextField jtfCid, JLabel jlaCid, JComboBox jcbEpocaIncidencia){
       this(telaPai, objetoDto, jcbDominio, jftDataInicial, jftDataFinal, jtfCidEtiologia, jlaCidEtiologia, jliEtiologiasAdicionadas, jtfCid, jlaCid, jcbEpocaIncidencia, null, null);
    } 
        
    public ControladorTelaEditarComboEDataInicioFinal (JDialog telaPai, ModeloDTO objetoDto, JComboBox jcbDominio, JFormattedTextField jftDataInicial, JFormattedTextField jftDataFinal, JComboBox jcbStatus){
       this(telaPai, objetoDto, jcbDominio, jftDataInicial, jftDataFinal, null, null, null, null, null, null, jcbStatus, null);
    } 
    
   public ControladorTelaEditarComboEDataInicioFinal (JDialog telaPai, ModeloDTO objetoDto, JComboBox jcbDominio, JFormattedTextField jftDataInicial, JFormattedTextField jftDataFinal, JEditorPane jepObs){
       this(telaPai, objetoDto, jcbDominio, jftDataInicial, jftDataFinal, null, null, null, null, null, null, null, jepObs);
    } 


    private ControladorTelaEditarComboEDataInicioFinal (JDialog telaPai, ModeloDTO objetoDto, JComboBox jcbDominio, JFormattedTextField jftDataInicial, JFormattedTextField jftDataFinal, JTextField jtfCidEtiologia, JLabel jlaCidEtiologia, JList jliEtiologiasAdicionadas, JTextField jtfCid, JLabel jlaCid, JComboBox jcbEpocaIncidencia, JComboBox jcbStatus, JEditorPane jepObs){
        super(telaPai, objetoDto);
        this.jcbDominio = jcbDominio;
        this.jftDataInicial = jftDataInicial;
        this.jftDataFinal = jftDataFinal;
        this.jtfCidEtiologia = jtfCidEtiologia;
        this.jlaCidEtiologia = jlaCidEtiologia;
        this.jliEtiologiasAdicionadas = jliEtiologiasAdicionadas;
        this.jtfCid = jtfCid;
        this.jlaCid = jlaCid;
        this.jcbEpocaIncidencia = jcbEpocaIncidencia;
        this.jcbStatus = jcbStatus;
        this.jepObs = jepObs;
        this.inicializarCombosDadosAuxiliares();
        carregarCampos();      
    }  
    
    @Override
    protected boolean verificarSeDtoEstaPreenchido() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    protected void executarAcaoAposInclusao() {
    }

    @Override
    public void inicializarCombosDadosAuxiliares() {
        obterDadosAuxiliares();
        if (jcbDominio != null){
            adicionarDtos(jcbDominio, resultadoListagemDTO.getObjetosDto());
        }
        if (jcbStatus != null){
            adicionarDtos(jcbStatus, resultadoListagemStatusBeneficioDTO.getObjetosDto());
        }
    }
        
    @Override
    public void obterDadosAuxiliares() {
        try {
            resultadoListagemDTO = obterListagemAuxiliares();
            resultadoListagemEpocaIncidenciaDTO = servicoSisLaraServer.obterListagemEpocaIncidencia();
            resultadoListagemStatusBeneficioDTO = servicoSisLaraServer.obterListagemStatusBeneficio();
        } catch (Exception ex) {
            JOptionPanePersonalizado.mostrarTelaErro(telaPai, JOptionPanePersonalizado.ERRO_NA_OBTENCAO_DADOS_AUXILIARES);
            logger.error(JOptionPanePersonalizado.ERRO_NA_OBTENCAO_DADOS_AUXILIARES + ex.getMessage());
        }
    }

    protected abstract ResultadoListagemDTO obterListagemAuxiliares() throws RemoteException;
}

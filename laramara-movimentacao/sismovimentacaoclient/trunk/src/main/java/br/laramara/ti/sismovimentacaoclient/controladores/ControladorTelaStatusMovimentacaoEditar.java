
package br.laramara.ti.sismovimentacaoclient.controladores;

import br.laramara.ti.sismovimentacaocommons.dtos.ResultadoDTO;
import br.laramara.ti.sismovimentacaocommons.dtos.movimentacao.MovimentacaoDTO;
import br.laramara.ti.sismovimentacaocommons.dtos.movimentacao.StatusDTO;
import br.laramara.ti.sismovimentacaocommons.utilitarios.Sessao;
import java.rmi.RemoteException;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;

public class ControladorTelaStatusMovimentacaoEditar extends ControladorTelaEditarStatus {

    private JFormattedTextField jftData;
    private JFormattedTextField jftHora;
    private JComboBox jcbStatus;
    
    public ControladorTelaStatusMovimentacaoEditar(JDialog telaPai, MovimentacaoDTO movimentacaoDto, JComboBox jcbStatus, JFormattedTextField jftData, JFormattedTextField jftHora){
        super(telaPai, movimentacaoDto, jcbStatus);
        this.jftData = jftData;
        this.jftHora = jftHora;
        this.jcbStatus = jcbStatus;
    }
    
    @Override
    protected void prepararDtoParaEditar() {
    }
        
    @Override
    protected ResultadoDTO solicitarEdicaoDto() throws RemoteException {
        return servicoSisMovimentacaoServer.editarStatusMovimentacao(obterObjetoDTO(), (StatusDTO)obterDtoSelecionado(jcbStatus), jftData.getText()+" "+jftHora.getText(), Sessao.obterInstancia().obterToken());
    }
    
    
    @Override
    public void carregarCampos() {
    }
    
    @Override
    protected boolean verificarSeDtoEstaPreenchido() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    protected void atualizarCamposTelaAposInclusao() {
    }
    
    private MovimentacaoDTO obterObjetoDTO() {
        return (MovimentacaoDTO)objetoDto;
    }
}

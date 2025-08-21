
package br.laramara.ti.sislaraclient.controladores;

import br.laramara.ti.sislaracommons.dtos.grupo.ResultadoListagemNomeCompletoGrupoDTO;
import java.awt.event.KeyEvent;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JTextField;

public abstract class ControladorTelaRelatorioApartirDeNomeGrupo extends ControladorTela{
    
    private JTextField jtfNomeGrupo;
    private JList jliNomesCompletosGrupo;
    
    public ControladorTelaRelatorioApartirDeNomeGrupo(JDialog telaPai, JTextField jtfNomeGrupo, JList jliNomesCompletosGrupo){
        super(telaPai);
        this.jtfNomeGrupo = jtfNomeGrupo;
        this.jliNomesCompletosGrupo = jliNomesCompletosGrupo;
    }
    
    public void pesquisarNomeCompletoGrupo() {
         try {
            ResultadoListagemNomeCompletoGrupoDTO resultadoDto = servicoSisLaraServer.pesquisarNomeGrupoPor(jtfNomeGrupo.getText());
            jliNomesCompletosGrupo.setListData(resultadoDto.getObjetosDto().toArray());
        } catch (Exception ex) {
            logger.error("Erro durante solicitação de pesquisa de nome completo do grupo. \nDetalhes:" + ex);
        }
    }

    public void selecionarNomeCompleto(KeyEvent evt) {
        if (teclaEnterPressionada(evt)){
            atualizarCampoNomeCompleto();
        }
    }

    public void selecionarNomeCompleto() {
        atualizarCampoNomeCompleto();
    }
    
    private void atualizarCampoNomeCompleto(){
        jtfNomeGrupo.setText((String)jliNomesCompletosGrupo.getSelectedValue());
    }

    public void pesquidarNomeCompletoGrupo(KeyEvent evt) {
        if (Character.isAlphabetic(evt.getKeyChar()) || Character.isDigit(evt.getKeyChar())){
            pesquisarNomeCompletoGrupo();
        }
    }
    
    public abstract void exibir();
}

package br.laramara.ti.sislaraclient.controladores;

import br.laramara.ti.sislaraclient.modelos.ModeloTabela;
import br.laramara.ti.sislaraclient.modelos.ModeloTabelaInformacaoEducacional;
import br.laramara.ti.sislaraclient.telas.TelaEditarEducacional;
import br.laramara.ti.sislaracommons.dtos.escola.InformacaoEducacionalDTO;
import br.laramara.ti.sislaracommons.utilitarios.Sessao;
import java.awt.event.KeyEvent;
import javax.swing.JDialog;
import javax.swing.JTable;

public class ControladorInformacoesEducacionais extends ControladorTela {
    
    private JTable jtaInformacoesEducacionaisAdicionadas;
    
    public ControladorInformacoesEducacionais(JDialog telaPai, JTable jtaInformacoesEducacionaisAdicionadas){
        super(telaPai);
        this.jtaInformacoesEducacionaisAdicionadas = jtaInformacoesEducacionaisAdicionadas;
    }

    void adicionarInformacaoEducacional() {
        new TelaEditarEducacional((JDialog) telaPai, new InformacaoEducacionalDTO());
        String chave = Sessao.CHAVE_INFORMACAO_EDUCACIONAL;
        if (Sessao.obterInstancia().possuiDto(chave)) {
            obterModeloTabelaInformacaoEducacional().adicionarDTO((InformacaoEducacionalDTO) Sessao.obterInstancia().obterDto(chave));
        }
    }
    
        
    private ModeloTabela obterModeloTabelaInformacaoEducacional() {
        return ((ModeloTabelaInformacaoEducacional)jtaInformacoesEducacionaisAdicionadas.getModel());
    }

    void alterarInformacaoEducacional() {
        if (estaComItemValidoSelecionado(jtaInformacoesEducacionaisAdicionadas)) {
            new TelaEditarEducacional((JDialog) telaPai, (InformacaoEducacionalDTO) obterDtoSelecionado(jtaInformacoesEducacionaisAdicionadas));
            String chave = Sessao.CHAVE_INFORMACAO_EDUCACIONAL;
            if (Sessao.obterInstancia().possuiDto(chave)) {
                adicionarDtoNoItemSelecionado(jtaInformacoesEducacionaisAdicionadas, (InformacaoEducacionalDTO) Sessao.obterInstancia().obterDto(chave));
            }
        }
    }

    void removerInformacaoEducacionalSelecionada() {
        removerDtoSelecionado(jtaInformacoesEducacionaisAdicionadas);
    }

    void abrirTelaDeAlteracaoInformacaoEducacional(KeyEvent evt) {
        if (teclaEnterPressionada(evt)){
            alterarInformacaoEducacional();
        }
    }
}

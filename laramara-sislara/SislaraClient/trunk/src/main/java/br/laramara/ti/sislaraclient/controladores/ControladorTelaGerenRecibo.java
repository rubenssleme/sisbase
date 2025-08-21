package br.laramara.ti.sislaraclient.controladores;

import br.laramara.ti.sislaraclient.telas.TelaEditarRecibo;
import br.laramara.ti.sislaracommons.dtos.EspecificacaoPesquisaDTO;
import br.laramara.ti.sislaracommons.dtos.ModeloDTO;
import br.laramara.ti.sislaracommons.dtos.ResultadoListagemDTO;
import br.laramara.ti.sislaracommons.dtos.doacao.EspecificacaoPesquisaReciboDTO;
import br.laramara.ti.sislaracommons.dtos.doacao.ReciboDTO;
import br.laramara.ti.sislaracommons.utilitarios.Sessao;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JTable;
import javax.swing.JTextField;

public class ControladorTelaGerenRecibo extends ControladorTelaGeren {
    
    public ControladorTelaGerenRecibo(JDialog telaPai, JTable jtaRecibo, JComboBox jcbTipoPesquisa, JTextField jtfDadosPesquisar){
        super(telaPai, jcbTipoPesquisa, jtfDadosPesquisar, jtaRecibo);
    }
    
    @Override
    public void abrirTelaParaInclusao() {
        new TelaEditarRecibo((JDialog) telaPai, new ReciboDTO());
    }

    @Override
    protected void abrirTelaParaEdicao(ModeloDTO objetoDto) {
        new TelaEditarRecibo((JDialog) telaPai, (ReciboDTO)objetoDto);
    }

    
    @Override
    protected ResultadoListagemDTO obterListagem(EspecificacaoPesquisaDTO especificacao) throws Exception {
        return servicoSisLaraServer.pesquisarReciboPor((EspecificacaoPesquisaReciboDTO)especificacao);
    }

    @Override
    public EspecificacaoPesquisaDTO obterEspecificacao() {
        return new EspecificacaoPesquisaReciboDTO();
    }

    @Override
    public String obterChaveSessao() {
        return Sessao.CHAVE_RECIBO;
    }
}

package br.laramara.ti.sislaraclient.controladores;

import br.laramara.ti.sislaraclient.telas.TelaEditarContribuintes;
import br.laramara.ti.sislaracommons.dtos.EspecificacaoPesquisaDTO;
import br.laramara.ti.sislaracommons.dtos.ModeloDTO;
import br.laramara.ti.sislaracommons.dtos.ResultadoListagemDTO;
import br.laramara.ti.sislaracommons.dtos.contribuicao.ContribuinteDTO;
import br.laramara.ti.sislaracommons.dtos.contribuicao.EspecificacaoPesquisaContribuinteDTO;
import br.laramara.ti.sislaracommons.utilitarios.Sessao;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JTable;
import javax.swing.JTextField;

public class ControladorTelaGerenContribuintes extends ControladorTelaGeren {
    
    public ControladorTelaGerenContribuintes(JDialog telaPai, JTable jtaContribuintes, JComboBox jcbTipoPesquisa, JTextField jtfDadosPesquisar){
        super(telaPai, jcbTipoPesquisa, jtfDadosPesquisar, jtaContribuintes);
    }
    
    @Override
    public void abrirTelaParaInclusao() {
         new TelaEditarContribuintes((JDialog) telaPai, new ContribuinteDTO());
    }

    @Override
    protected void abrirTelaParaEdicao(ModeloDTO objetoDto) {
         new TelaEditarContribuintes((JDialog) telaPai, (ContribuinteDTO)objetoDto);
    }

    
    @Override
    protected ResultadoListagemDTO obterListagem(EspecificacaoPesquisaDTO especificacao) throws Exception {
        return servicoSisLaraServer.pesquisarContribuintePor((EspecificacaoPesquisaContribuinteDTO)especificacao);
    }

    @Override
    public EspecificacaoPesquisaDTO obterEspecificacao() {
        return new EspecificacaoPesquisaContribuinteDTO();
    }

    @Override
    public String obterChaveSessao() {
        return Sessao.CHAVE_CONTRIBUINTE;
    }
}

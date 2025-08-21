
package br.laramara.ti.sismovimentacaoclient.controladores;

import br.laramara.ti.sismovimentacaoclient.telas.TelaEditarContaAcesso;
import br.laramara.ti.sismovimentacaocommons.dtos.EspecificacaoPesquisaDTO;
import br.laramara.ti.sismovimentacaocommons.dtos.ModeloDTO;
import br.laramara.ti.sismovimentacaocommons.dtos.ResultadoListagemDTO;
import br.laramara.ti.sismovimentacaocommons.dtos.seguranca.ContaAcessoDTO;
import br.laramara.ti.sismovimentacaocommons.dtos.seguranca.EspecificacaoPesquisaContaAcessoDTO;
import br.laramara.ti.sismovimentacaocommons.utilitarios.Sessao;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JTable;
import javax.swing.JTextField;


public class ControladorTelaGerenContaAcesso extends ControladorTelaGeren{
 
    public ControladorTelaGerenContaAcesso(JDialog telaPai, JTable jtaGrupos, JComboBox jcbTipoPesquisa, JTextField jtfDadosPesquisar){
        super(telaPai, jcbTipoPesquisa, jtfDadosPesquisar, jtaGrupos);
    }

    @Override
    public void abrirTelaParaInclusao() {
        new TelaEditarContaAcesso((JDialog) telaPai, new ContaAcessoDTO());
    }

    @Override
    protected void abrirTelaParaEdicao(ModeloDTO contaAcessoDto) {
        new TelaEditarContaAcesso((JDialog) telaPai, (ContaAcessoDTO)contaAcessoDto);
    }

    @Override
    public EspecificacaoPesquisaDTO obterEspecificacao() {
        return new EspecificacaoPesquisaContaAcessoDTO();
    }

    @Override
    protected ResultadoListagemDTO obterListagem(EspecificacaoPesquisaDTO especificacao) throws Exception {
        return servicoSisMovimentacaoServer.pesquisarContaAcessoPor((EspecificacaoPesquisaContaAcessoDTO)especificacao);
    }

    @Override
    public String obterChaveSessao() {
        return Sessao.CHAVE_CONTA_ACESSO;
    }
}


package br.laramara.ti.sislaraclient.controladores;

import br.laramara.ti.sislaraclient.telas.TelaEditarContaAcesso;
import br.laramara.ti.sislaracommons.dtos.EspecificacaoPesquisaDTO;
import br.laramara.ti.sislaracommons.dtos.ModeloDTO;
import br.laramara.ti.sislaracommons.dtos.ResultadoListagemDTO;
import br.laramara.ti.sislaracommons.dtos.seguranca.ContaAcessoDTO;
import br.laramara.ti.sislaracommons.dtos.seguranca.EspecificacaoPesquisaContaAcessoDTO;
import br.laramara.ti.sislaracommons.utilitarios.Sessao;
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
        return servicoSisLaraServer.pesquisarContaAcessoPor((EspecificacaoPesquisaContaAcessoDTO)especificacao);
    }

    @Override
    public String obterChaveSessao() {
        return Sessao.CHAVE_CONTA_ACESSO;
    }
}

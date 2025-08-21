package br.laramara.ti.sislaraclient.controladores;

import br.laramara.ti.sislaraclient.telas.TelaEditarInstituicoes;
import br.laramara.ti.sislaracommons.dtos.EspecificacaoPesquisaDTO;
import br.laramara.ti.sislaracommons.dtos.ModeloDTO;
import br.laramara.ti.sislaracommons.dtos.ResultadoListagemDTO;
import br.laramara.ti.sislaracommons.dtos.instituicao.EspecificacaoPesquisaInstituicaoDTO;
import br.laramara.ti.sislaracommons.dtos.instituicao.InstituicaoDTO;
import br.laramara.ti.sislaracommons.utilitarios.Sessao;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JTable;
import javax.swing.JTextField;

public class ControladorTelaGerenInstituicoes extends ControladorTelaGeren {
    
    public ControladorTelaGerenInstituicoes(JDialog telaPai, JTable jtaInstituicoes, JComboBox jcbTipoPesquisa, JTextField jtfDadosPesquisar){
        super(telaPai, jcbTipoPesquisa, jtfDadosPesquisar, jtaInstituicoes);
    }
    
    @Override
    public void abrirTelaParaInclusao() {
         new TelaEditarInstituicoes((JDialog) telaPai, new InstituicaoDTO());
    }

    @Override
    protected void abrirTelaParaEdicao(ModeloDTO objetoDto) {
         new TelaEditarInstituicoes((JDialog) telaPai, (InstituicaoDTO)objetoDto);
    }

    
    @Override
    protected ResultadoListagemDTO obterListagem(EspecificacaoPesquisaDTO especificacao) throws Exception {
        return servicoSisLaraServer.pesquisarInstituicaoPor((EspecificacaoPesquisaInstituicaoDTO)especificacao);
    }

    @Override
    public EspecificacaoPesquisaDTO obterEspecificacao() {
        return new EspecificacaoPesquisaInstituicaoDTO();
    }

    @Override
    public String obterChaveSessao() {
        return Sessao.CHAVE_INSTITUICAO;
    }
}

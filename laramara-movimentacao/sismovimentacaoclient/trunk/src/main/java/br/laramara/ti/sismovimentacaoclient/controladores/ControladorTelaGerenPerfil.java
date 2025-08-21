package br.laramara.ti.sismovimentacaoclient.controladores;

import br.laramara.ti.sismovimentacaoclient.telas.TelaEditarPerfil;
import br.laramara.ti.sismovimentacaocommons.dtos.EspecificacaoPesquisaDTO;
import br.laramara.ti.sismovimentacaocommons.dtos.ModeloDTO;
import br.laramara.ti.sismovimentacaocommons.dtos.ResultadoListagemDTO;
import br.laramara.ti.sismovimentacaocommons.dtos.seguranca.EspecificacaoPesquisaPerfilDTO;
import br.laramara.ti.sismovimentacaocommons.dtos.seguranca.PerfilDTO;
import br.laramara.ti.sismovimentacaocommons.utilitarios.Sessao;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JTable;
import javax.swing.JTextField;

public class ControladorTelaGerenPerfil extends ControladorTelaGeren {
    
    public ControladorTelaGerenPerfil(JDialog telaPai, JTable jtaPerfis, JComboBox jcbTipoPesquisa, JTextField jtfDadosPesquisar){
        super(telaPai, jcbTipoPesquisa, jtfDadosPesquisar, jtaPerfis);
    }
    
    @Override
    public void abrirTelaParaInclusao() {
         new TelaEditarPerfil((JDialog) telaPai, new PerfilDTO());
    }

    @Override
    protected void abrirTelaParaEdicao(ModeloDTO objetoDto) {
         new TelaEditarPerfil((JDialog) telaPai, (PerfilDTO)objetoDto);
    }

    
    @Override
    protected ResultadoListagemDTO obterListagem(EspecificacaoPesquisaDTO especificacao) throws Exception {
        return servicoSisMovimentacaoServer.pesquisarPerfilPor((EspecificacaoPesquisaPerfilDTO)especificacao);
    }

    @Override
    public EspecificacaoPesquisaDTO obterEspecificacao() {
        return new EspecificacaoPesquisaPerfilDTO();
    }

    @Override
    public String obterChaveSessao() {
        return Sessao.CHAVE_PERFIL;
    }
}

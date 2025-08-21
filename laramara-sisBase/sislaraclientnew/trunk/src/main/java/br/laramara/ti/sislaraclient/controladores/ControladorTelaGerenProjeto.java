package br.laramara.ti.sislaraclient.controladores;

import br.laramara.ti.sislaraclient.telas.TelaProjetoEditar;
import br.laramara.ti.sislaracommons.dtos.EspecificacaoPesquisaDTO;
import br.laramara.ti.sislaracommons.dtos.ModeloDTO;
import br.laramara.ti.sislaracommons.dtos.ResultadoListagemDTO;
import br.laramara.ti.sislaracommons.dtos.doacao.EspecificacaoPesquisaProjetoDTO;
import br.laramara.ti.sislaracommons.dtos.doacao.ProjetoDTO;
import br.laramara.ti.sislaracommons.utilitarios.Sessao;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JTable;
import javax.swing.JTextField;


public class ControladorTelaGerenProjeto extends ControladorTelaGeren {
    
    public ControladorTelaGerenProjeto(JDialog telaPai, JTable jtaProjetos, JComboBox jcbTipoPesquisa, JTextField jtfDadosPesquisar){
        super(telaPai, jcbTipoPesquisa, jtfDadosPesquisar, jtaProjetos);
    }

    @Override
    public void abrirTelaParaInclusao() {
        new TelaProjetoEditar((JDialog) telaPai, new ProjetoDTO());
    }

    @Override
    protected void abrirTelaParaEdicao(ModeloDTO projetoDto) {
        new TelaProjetoEditar((JDialog) telaPai, (ProjetoDTO)projetoDto);
    }

    @Override
    public EspecificacaoPesquisaDTO obterEspecificacao() {
        return new EspecificacaoPesquisaProjetoDTO();
    }

    @Override
    protected ResultadoListagemDTO obterListagem(EspecificacaoPesquisaDTO especificacao) throws Exception {
        return servicoSisLaraServer.pesquisarProjetoPor((EspecificacaoPesquisaProjetoDTO)especificacao);
    }

    @Override
    public String obterChaveSessao() {
        return Sessao.CHAVE_PROJETO;
    }
}

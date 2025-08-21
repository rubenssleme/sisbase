package br.laramara.ti.sislaraclient.controladores;

import br.laramara.ti.sislaraclient.telas.TelaEditarPreCadastro;
import br.laramara.ti.sislaracommons.dtos.EspecificacaoPesquisaDTO;
import br.laramara.ti.sislaracommons.dtos.ModeloDTO;
import br.laramara.ti.sislaracommons.dtos.ResultadoListagemDTO;
import br.laramara.ti.sislaracommons.dtos.precadastro.EspecificacaoPesquisaPreCadastroDTO;
import br.laramara.ti.sislaracommons.dtos.precadastro.PreCadastroDTO;
import br.laramara.ti.sislaracommons.utilitarios.Sessao;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JTable;
import javax.swing.JTextField;

public class ControladorTelaGerenPreCadastro extends ControladorTelaGeren {

    public ControladorTelaGerenPreCadastro(JDialog telaPai, JTable jtaPreCadastro, JComboBox jcbTipoPesquisa, JTextField jtfDadosPesquisar) {
        super(telaPai, jcbTipoPesquisa, jtfDadosPesquisar, jtaPreCadastro);
    }

    @Override
    public void abrirTelaParaInclusao() {
        new TelaEditarPreCadastro((JDialog) telaPai, new PreCadastroDTO());
    }

    @Override
    protected void abrirTelaParaEdicao(ModeloDTO objetoDto) {
        new TelaEditarPreCadastro((JDialog) telaPai, (PreCadastroDTO) objetoDto);
    }

    @Override
    protected ResultadoListagemDTO obterListagem(EspecificacaoPesquisaDTO especificacao) throws Exception {
        return servicoSisLaraServer.pesquisarPreCadastroPor((EspecificacaoPesquisaPreCadastroDTO)especificacao);
    }

    @Override
    public EspecificacaoPesquisaDTO obterEspecificacao() {
        return new EspecificacaoPesquisaPreCadastroDTO();
    }

    @Override
    public String obterChaveSessao() {
        return Sessao.CHAVE_PRE_CADASTRO;
    }
}

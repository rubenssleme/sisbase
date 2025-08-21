package br.laramara.ti.sislaraclient.controladores;

import br.laramara.ti.sislaraclient.telas.TelaGruposEditar;
import br.laramara.ti.sislaracommons.dtos.EspecificacaoPesquisaDTO;
import br.laramara.ti.sislaracommons.dtos.ModeloDTO;
import br.laramara.ti.sislaracommons.dtos.ResultadoListagemDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.EspecificacaoPesquisaGrupoDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.GrupoDTO;
import br.laramara.ti.sislaracommons.utilitarios.Sessao;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JTable;
import javax.swing.JTextField;

public class ControladorTelaGerenGrupos extends ControladorTelaGeren {
    
    public ControladorTelaGerenGrupos(JDialog telaPai, JTable jtaGrupos, JComboBox jcbTipoPesquisa, JTextField jtfDadosPesquisar){
        super(telaPai, jcbTipoPesquisa, jtfDadosPesquisar, jtaGrupos);
    }

    @Override
    public void abrirTelaParaInclusao() {
        new TelaGruposEditar((JDialog) telaPai, new GrupoDTO());
    }

    @Override
    protected void abrirTelaParaEdicao(ModeloDTO grupoDto) {
        new TelaGruposEditar((JDialog) telaPai, (GrupoDTO)grupoDto);
    }

    @Override
    public EspecificacaoPesquisaDTO obterEspecificacao() {
        return new EspecificacaoPesquisaGrupoDTO();
    }

    @Override
    protected ResultadoListagemDTO obterListagem(EspecificacaoPesquisaDTO especificacao) throws Exception {
        return servicoSisLaraServer.pesquisarGrupoPor((EspecificacaoPesquisaGrupoDTO)especificacao);
    }

    @Override
    public String obterChaveSessao() {
        return Sessao.CHAVE_GRUPO;
    }
}

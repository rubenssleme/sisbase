package br.laramara.ti.sislaraclient.controladores;

import br.laramara.ti.sislaraclient.utilitarios.JOptionPanePersonalizado;
import br.laramara.ti.sislaracommons.dtos.EspecificacaoPesquisaDTO;
import br.laramara.ti.sislaracommons.dtos.ResultadoListagemDTO;
import br.laramara.ti.sislaracommons.dtos.precadastro.EspecificacaoPesquisaPreCadastroDTO;
import br.laramara.ti.sislaracommons.dtos.precadastro.PreCadastroDTO;
import br.laramara.ti.sislaracommons.utilitarios.Sessao;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JTable;
import javax.swing.JTextField;

public class ControladorTelaUtilizarPreCadastro extends ControladorTelaUtilizar {

    private boolean bloquearPreCadastroJaUtilizado;
    
    public ControladorTelaUtilizarPreCadastro(JDialog telaPai, boolean bloquearPreCadastroJaUtilizado, JComboBox jcbTipoPesquisa, JTextField jtfDadosPesquisar, JTable jtaListagemPreCadastro) {
        super(telaPai, jcbTipoPesquisa, jtfDadosPesquisar, jtaListagemPreCadastro);
        this.bloquearPreCadastroJaUtilizado = bloquearPreCadastroJaUtilizado;
    }

    @Override
    public void utilizar() {
        if (estaComItemValidoSelecionado(jtaListagem)) {
            PreCadastroDTO preCadastroDto = ((PreCadastroDTO) obterDtoSelecionado(jtaListagem));
            if (!bloquearPreCadastroJaUtilizado || (bloquearPreCadastroJaUtilizado && !preCadastroDto.getInformacaoEssencialDto().possuiUsuarioAssociado())) {
                Sessao.obterInstancia().armazenarDTO(Sessao.CHAVE_PRE_CADASTRO_SELECIONADO, preCadastroDto);
                telaPai.dispose();
            } else {
                JOptionPanePersonalizado.mostrarTelaErro(telaPai, JOptionPanePersonalizado.ERRO_USUARIO_JA_CADASTRADO);
            }
        } else {
            JOptionPanePersonalizado.mostrarTelaErro(telaPai, JOptionPanePersonalizado.SELECIONAR_REGISTRO);
        }
    }

    @Override
    public ResultadoListagemDTO obterListagem(EspecificacaoPesquisaDTO especificacao) throws Exception {
        return servicoSisLaraServer.pesquisarPreCadastroPor((EspecificacaoPesquisaPreCadastroDTO)especificacao);
    }

    @Override
    public EspecificacaoPesquisaDTO obterEspecificacao() {
        return new EspecificacaoPesquisaPreCadastroDTO();
    }
}

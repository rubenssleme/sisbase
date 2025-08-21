package br.laramara.ti.sislaraclient.controladores;

import br.laramara.ti.sislaraclient.telas.TelaSolicitacaoRelatorioCancelamento;
import br.laramara.ti.sislaraclient.telas.TelaSolicitacaoRelatorioEditar;
import br.laramara.ti.sislaraclient.telas.TelaSolicitacaoRelatorioEmissaoProfissional;
import br.laramara.ti.sislaraclient.telas.TelaSolicitacaoRelatorioEntrega;
import br.laramara.ti.sislaraclient.utilitarios.EsperaUtils;
import br.laramara.ti.sislaraclient.utilitarios.JOptionPanePersonalizado;
import br.laramara.ti.sislaraclient.utilitarios.TabelaUtils;
import br.laramara.ti.sislaracommons.dtos.EspecificacaoPesquisaDTO;
import br.laramara.ti.sislaracommons.dtos.ResultadoListagemDTO;
import br.laramara.ti.sislaracommons.dtos.solicitacao.EspecificacaoPesquisaSolicitacaoRelatorioDTO;
import br.laramara.ti.sislaracommons.dtos.solicitacao.ResultadoEdicaoSolicitacaoRelatorioDTO;
import br.laramara.ti.sislaracommons.dtos.solicitacao.ResultadoListagemStatusSolicitacaoRelatorioDTO;
import br.laramara.ti.sislaracommons.dtos.solicitacao.SolicitacaoRelatorioDTO;
import br.laramara.ti.sislaracommons.dtos.solicitacao.StatusSolicitacaoRelatorioDTO;
import br.laramara.ti.sislaracommons.utilitarios.Sessao;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class ControladorTelaSolicitacaoRelatorioGeren extends ControladorTelaPesquisarBase {

    private ResultadoListagemStatusSolicitacaoRelatorioDTO resultadoListagemStatusSolicitacaoRelatorioDTO;
    
    private JComboBox jcbStatus;
    private JTextField jtfProntuario;
    private JTextField jtfProtocolo;
    private JTable jtaSolicitacaoRelatorios;

    public ControladorTelaSolicitacaoRelatorioGeren(JDialog telaPai, JComboBox jcbStatus, JTextField jtfProtocolo, JTextField jtfProntuario, JTable jtaSolicitacaoRelatorios) {
        super(telaPai, jtaSolicitacaoRelatorios);
        this.jcbStatus = jcbStatus;
        this.jtfProntuario = jtfProntuario;
        this.jtfProtocolo = jtfProtocolo;
        this.jtaSolicitacaoRelatorios = jtaSolicitacaoRelatorios;
        inicializarCombosDadosAuxiliares();
        configurarColunasTabela();
    }
    
    private void inicializarCombosDadosAuxiliares() {
        obterDadosAuxiliares();
        adicionarDtos(jcbStatus, resultadoListagemStatusSolicitacaoRelatorioDTO.getObjetosDto());
    }

    private void obterDadosAuxiliares() {
        try {
            resultadoListagemStatusSolicitacaoRelatorioDTO = servicoSisLaraServer.obterListagemStatusSolicitacaoRelatorio();
        } catch (Exception ex) {
            JOptionPanePersonalizado.mostrarTelaErro(telaPai, JOptionPanePersonalizado.ERRO_NA_OBTENCAO_DADOS_AUXILIARES);
            logger.error(JOptionPanePersonalizado.ERRO_NA_OBTENCAO_DADOS_AUXILIARES + ex.getMessage());
        }
    }
    
    public void configurarColunasTabela() {
        TabelaUtils.configurarTamanhoERenderizadorDeColunas(jtaSolicitacaoRelatorios);
    }

    @Override
    protected ResultadoListagemDTO obterListagem(EspecificacaoPesquisaDTO especificacaoPesquisaDto) throws Exception {
        return servicoSisLaraServer.pesquisarSolicitacaoRelatorioPor((EspecificacaoPesquisaSolicitacaoRelatorioDTO) especificacaoPesquisaDto);
    }

    public void efetuarPesquisa() {
        final EspecificacaoPesquisaSolicitacaoRelatorioDTO especificacao = new EspecificacaoPesquisaSolicitacaoRelatorioDTO();
        especificacao.setProntuario(jtfProntuario.getText());
        especificacao.setProtocolo(jtfProtocolo.getText());
        especificacao.setStatusSolicitacaoRelatorio((StatusSolicitacaoRelatorioDTO)obterDtoSelecionado(jcbStatus));
        Runnable comando = new Runnable() {
            @Override
            public void run() {
                pesquisar(especificacao);
            }
        };
        new EsperaUtils(comando, (JDialog) telaPai).execute();
    }

    public void novaSolicitacaoRelatorio() {
        new TelaSolicitacaoRelatorioEditar((JDialog) telaPai, new SolicitacaoRelatorioDTO());
        String chave = Sessao.CHAVE_SOLICITACAO_RELATORIO;
        if (Sessao.obterInstancia().possuiDto(chave)) {
            adicionarDtos((SolicitacaoRelatorioDTO) Sessao.obterInstancia().obterDto(chave), jtaSolicitacaoRelatorios);
        }
    }

    public void efetuarEncaminhamentoRecepcao() {
        if ((estaComItemValidoSelecionado(jtaSolicitacaoRelatorios)) && ((SolicitacaoRelatorioDTO) obterDtoSelecionado(jtaSolicitacaoRelatorios)).estaSolicitado()) {
            int resposta = JOptionPanePersonalizado.mostrarTelaPergunta(telaPai, JOptionPanePersonalizado.CONFIRMACAO);
            if (resposta == JOptionPane.OK_OPTION) {
                try {
                    ResultadoEdicaoSolicitacaoRelatorioDTO resultado = servicoSisLaraServer.efetuarEncaminhamentoRecepcao((SolicitacaoRelatorioDTO) obterDtoSelecionado(jtaSolicitacaoRelatorios), Sessao.obterInstancia().obterToken());
                    if (resultado.sucesso()){
                        adicionarDtoNoItemSelecionado(jtaSolicitacaoRelatorios, (SolicitacaoRelatorioDTO) resultado.obterObjetoDtoEditado());
                        JOptionPanePersonalizado.mostrarTelaSucesso(telaPai, resultado.obterMensagens());
                    }
                } catch (Exception e) {
                    logger.fatal("Error: " + e);
                    JOptionPanePersonalizado.mostrarTelaErro(telaPai, JOptionPanePersonalizado.ERRO_NO_ENCAMINHAMENTO_RECEPCAO);
                }
            }
        }
    }

    public void efetuarEmissaoProfissional() {
        if ((estaComItemValidoSelecionado(jtaSolicitacaoRelatorios)) && ((SolicitacaoRelatorioDTO) obterDtoSelecionado(jtaSolicitacaoRelatorios)).estaEncaminhadoPelaRecepcao()) {
            new TelaSolicitacaoRelatorioEmissaoProfissional((JDialog) telaPai, (SolicitacaoRelatorioDTO) obterDtoSelecionado(jtaSolicitacaoRelatorios));
            if (Sessao.obterInstancia().possuiDto(Sessao.CHAVE_SOLICITACAO_RELATORIO)) {
                adicionarDtoNoItemSelecionado(jtaSolicitacaoRelatorios, (SolicitacaoRelatorioDTO) Sessao.obterInstancia().obterDto(Sessao.CHAVE_SOLICITACAO_RELATORIO));
            }
        }
    }

    public void efetuarEntrega() {
        if ((estaComItemValidoSelecionado(jtaSolicitacaoRelatorios)) && (((SolicitacaoRelatorioDTO) obterDtoSelecionado(jtaSolicitacaoRelatorios)).estaEmitidoPorProfissional() || ((SolicitacaoRelatorioDTO) obterDtoSelecionado(jtaSolicitacaoRelatorios)).estaEntregueParaRecepcao())) {
            new TelaSolicitacaoRelatorioEntrega((JDialog) telaPai, (SolicitacaoRelatorioDTO) obterDtoSelecionado(jtaSolicitacaoRelatorios));
            if (Sessao.obterInstancia().possuiDto(Sessao.CHAVE_SOLICITACAO_RELATORIO)) {
                adicionarDtoNoItemSelecionado(jtaSolicitacaoRelatorios, (SolicitacaoRelatorioDTO) Sessao.obterInstancia().obterDto(Sessao.CHAVE_SOLICITACAO_RELATORIO));
            }
        }
    }

    public void cancelar() {
        if ((estaComItemValidoSelecionado(jtaSolicitacaoRelatorios)) && !((SolicitacaoRelatorioDTO) obterDtoSelecionado(jtaSolicitacaoRelatorios)).estaCancelado()) {
            new TelaSolicitacaoRelatorioCancelamento((JDialog) telaPai, (SolicitacaoRelatorioDTO) obterDtoSelecionado(jtaSolicitacaoRelatorios));
            if (Sessao.obterInstancia().possuiDto(Sessao.CHAVE_SOLICITACAO_RELATORIO)) {
                adicionarDtoNoItemSelecionado(jtaSolicitacaoRelatorios, (SolicitacaoRelatorioDTO) Sessao.obterInstancia().obterDto(Sessao.CHAVE_SOLICITACAO_RELATORIO));
            }
        }
    }

    public void imprimir() {
        SolicitacaoRelatorioDTO solicitacaoRelatorioDTO = (SolicitacaoRelatorioDTO) obterDtoSelecionado(jtaSolicitacaoRelatorios);
        try {
            exibir(servicoSisLaraServer.gerarRelatorioSolicitacaoRelatorio(solicitacaoRelatorioDTO.getId(), Sessao.obterInstancia().obterToken()));
        } catch (Exception ex) {
            logger.error("Erro durante exibição de relatório. \nDetalhes:" + ex);
        }
    }
}

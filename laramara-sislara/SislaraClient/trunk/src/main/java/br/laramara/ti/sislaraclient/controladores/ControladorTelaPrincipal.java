package br.laramara.ti.sislaraclient.controladores;

import br.laramara.ti.sislaraclient.telas.TelaRelatorioComasAtendidosEAtendimentosPorBeneficio;
import br.laramara.ti.sislaraclient.infra.PermissaoDeTelas;
import br.laramara.ti.sislaraclient.telas.TelaAgendaGerenAgendamento;
import br.laramara.ti.sislaraclient.telas.TelaAtendimentoIndividualEditar;
import br.laramara.ti.sislaraclient.telas.TelaAtendimentoIndividualGeren;
import br.laramara.ti.sislaraclient.telas.TelaDemandaGeren;
import br.laramara.ti.sislaraclient.telas.TelaDesbloqueio;
import br.laramara.ti.sislaraclient.telas.TelaEditarReciboCancelar;
import br.laramara.ti.sislaraclient.telas.TelaEditarSenha;
import br.laramara.ti.sislaraclient.telas.TelaGerenArquivoDisponivel;
import br.laramara.ti.sislaraclient.telas.TelaGerenContaAcesso;
import br.laramara.ti.sislaraclient.telas.TelaGerenContribuintes;
import br.laramara.ti.sislaraclient.telas.TelaGerenGrupos;
import br.laramara.ti.sislaraclient.telas.TelaGerenInstituicoes;
import br.laramara.ti.sislaraclient.telas.TelaGerenPerfil;
import br.laramara.ti.sislaraclient.telas.TelaGerenPreCadastro;
import br.laramara.ti.sislaraclient.telas.TelaGerenProjeto;
import br.laramara.ti.sislaraclient.telas.TelaGerenRecibo;
import br.laramara.ti.sislaraclient.telas.TelaGerenServicoSocial;
import br.laramara.ti.sislaraclient.telas.TelaGruposEditarAtendimentos;
import br.laramara.ti.sislaraclient.telas.TelaGruposGerenAtedimentos;
import br.laramara.ti.sislaraclient.telas.TelaGruposGerenIntegrantes;
import br.laramara.ti.sislaraclient.telas.TelaInfoSistema;
import br.laramara.ti.sislaraclient.telas.TelaListaEsperaGeren;
import br.laramara.ti.sislaraclient.telas.TelaLogin;
import br.laramara.ti.sislaraclient.telas.TelaMailing;
import br.laramara.ti.sislaraclient.telas.TelaRelatorioAPartirFaixaIdade;
import br.laramara.ti.sislaraclient.telas.TelaRelatorioAtendimentoPorUsuarioDetalhado;
import br.laramara.ti.sislaraclient.telas.TelaRelatorioAtendimentoPorUsuarioSimples;
import br.laramara.ti.sislaraclient.telas.TelaRelatorioContatosIntegrantesGrupos;
import br.laramara.ti.sislaraclient.telas.TelaRelatorioListaFrequenciaPorGrupo;
import br.laramara.ti.sislaraclient.telas.TelaRelatorioParticipacaoUsuarioEmGrupo;
import br.laramara.ti.sislaraclient.telas.TelaRelatorioProgramacaoDoGrupoParaFamilia;
import br.laramara.ti.sislaraclient.telas.TelaRelatorioQuantidadeAtendidosPorTiposVinculos;
import br.laramara.ti.sislaraclient.telas.TelaRelatorioQuantidadeAtendidosPorTiposVinculosPorGrupo;
import br.laramara.ti.sislaraclient.telas.TelaRelatorioQuantidadeAtendimentosIndividuais;
import br.laramara.ti.sislaraclient.telas.TelaRelatorioQuantidadeGeracaoAtendimentosIndividuaisEGrupo;
import br.laramara.ti.sislaraclient.telas.TelaRelatorioRetiradaProntuariosNoAgendamento;
import br.laramara.ti.sislaraclient.telas.TelaRelatorioRetiradasPorProntuario;
import br.laramara.ti.sislaraclient.telas.TelaRelatorioTodosAtendimentosDoUsuarioOrdenadoPorDataLancamento;
import br.laramara.ti.sislaraclient.telas.TelaRelatorioTodosAtendimentosDoUsuarioOrdenadoPorDataLancamentoLegado;
import br.laramara.ti.sislaraclient.telas.TelaRelatorioTodosAtendimentosDoUsuarioOrdenadoPorGrupo;
import br.laramara.ti.sislaraclient.telas.TelaRelatorioTodosAtendimentosIndividuaisDoUsuarioOrdenadoPorDataLancamento;
import br.laramara.ti.sislaraclient.telas.TelaRetiradaBaixar;
import br.laramara.ti.sislaraclient.telas.TelaRetiradaEfetuar;
import br.laramara.ti.sislaraclient.telas.TelaRelatorioAcompanhamentoProgramacaoPorGrupo;
import br.laramara.ti.sislaraclient.telas.TelaRelatorioAtendidosSemInformacaoDeficiencia;
import br.laramara.ti.sislaraclient.telas.TelaRelatorioCargaHorariaUsuarioPorGrupo;
import br.laramara.ti.sislaraclient.telas.TelaRelatorioComasAtendidosEAtendimentosPorClassificacaoInstituicao;
import br.laramara.ti.sislaraclient.telas.TelaRelatorioComasAtendidosEAtendimentosPorDeficiencia;
import br.laramara.ti.sislaraclient.telas.TelaRelatorioComasAtendidosEAtendimentosPorEscolaridade;
import br.laramara.ti.sislaraclient.telas.TelaRelatorioComasAtendidosEAtendimentosPorMunicipioSP;
import br.laramara.ti.sislaraclient.telas.TelaRelatorioComasAtendidosEAtendimentosPorRegiaoSP;
import br.laramara.ti.sislaraclient.telas.TelaRelatorioComasAtendidosEAtendimentosPorRenda;
import br.laramara.ti.sislaraclient.telas.TelaRelatorioComasAtendidosEAtendimentosPorTipoAtendimento;
import br.laramara.ti.sislaraclient.telas.TelaRelatorioComasAtendidosEAtendimentosPorUF;
import br.laramara.ti.sislaraclient.telas.TelaRelatorioComasAtendidosPorIdadeEGenero;
import br.laramara.ti.sislaraclient.telas.TelaRelatorioFluxoAtendimentoAvaliacaoFuncionalCasosNovos;
import br.laramara.ti.sislaraclient.telas.TelaRelatorioFluxoAtendimentoAvaliacaoFuncionalRetornos;
import br.laramara.ti.sislaraclient.telas.TelaRelatorioQuantidadeAtendidosPorComunidadeDeVisitaMonitorada;
import br.laramara.ti.sislaraclient.telas.TelaRelatorioQuantidadeAtendimentosParaPrefeitura;
import br.laramara.ti.sislaraclient.telas.TelaRelatorioQuantidadesAtendimentosAvaliacaoFuncionais;
import br.laramara.ti.sislaraclient.telas.TelaRelatorioRecibo;
import br.laramara.ti.sislaraclient.telas.TelaRelatorioTodasFrequenciasOrdenadaPorDataLancamento;
import br.laramara.ti.sislaraclient.telas.TelaSolicitacaoRelatorioGeren;
import br.laramara.ti.sislaraclient.telas.TelasRelatorioFolhaDeRosto;
import br.laramara.ti.sislaraclient.utilitarios.JOptionPanePersonalizado;
import br.laramara.ti.sislaraclient.utilitarios.SomUtils;
import br.laramara.ti.sislaracommons.dtos.grupo.ModuloPeriodoDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.ResultadoGeracaoAtendimentoDTO;
import br.laramara.ti.sislaracommons.dtos.pendencia.PendenciaDTO;
import br.laramara.ti.sislaracommons.dtos.seguranca.ResultadoAlteracaoExtensaoRelatorioDTO;
import br.laramara.ti.sislaracommons.dtos.seguranca.ResultadoOperacaoFiltroGrupoDTO;
import br.laramara.ti.sislaracommons.utilitarios.Sessao;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class ControladorTelaPrincipal extends ControladorTela {

    private JLabel fundo;
    private JPanel painel;
    private JMenuItem jmiFinalizarServico;
    private JMenuItem jmiAtivarDesativarAviso;
    private JButton jbuContribuintes;
    private JButton jbuRecibos;
    private JList jliPendencias;
    private ExecutorService pollDeThreads;

    public ControladorTelaPrincipal(JFrame telaPai, JLabel fundo, JPanel painel, JMenuItem jmiFinalizarServico, JMenuItem jmiAtivarDesativarAviso, JButton jbuContribuintes, JButton jbuRecibos, JList jliPendencias) {
        super(telaPai);
        TelaLogin telaLogin = new TelaLogin((JFrame) telaPai);
        telaLogin.setLocationRelativeTo(telaPai);
        telaLogin.setVisible(true);
        this.fundo = fundo;
        this.painel = painel;
        this.jmiFinalizarServico = jmiFinalizarServico;
        this.jmiAtivarDesativarAviso = jmiAtivarDesativarAviso;
        this.jbuContribuintes = jbuContribuintes;
        this.jbuRecibos = jbuRecibos;
        this.jliPendencias = jliPendencias;
        this.pollDeThreads = Executors.newCachedThreadPool();
        pollDeThreads.submit(new ThreadAtualizacaoPendencias(jliPendencias));
        habilitarMenus();
        ajustarTitulo(telaPai, telaLogin.obterLogin());
        atualizarListaPendencia();
    }

    private void ajustarTitulo(JFrame telaPai, String login) {
        String tituloAtual = telaPai.getTitle();
        telaPai.setTitle(tituloAtual + " - " + login);
    }

    public void exibirTelaInformacoes() {
        new TelaInfoSistema((JFrame) telaPai);
    }

    public void exibirTelaGerenUsuario() {
        new TelaGerenServicoSocial((JFrame) telaPai);
    }

    public void exibirTelaGerenInstituicoes() {
        new TelaGerenInstituicoes((JFrame) telaPai);
    }

    public void exibirTelaGerenPreCadastro() {
        new TelaGerenPreCadastro((JFrame) telaPai);
    }

    public void exibirTelaDesbloqueioUsuario() {
        new TelaDesbloqueio((JFrame) telaPai);
    }

    public void exibirTelaGerenAtendimentos() {
        new TelaGruposGerenAtedimentos((JFrame) telaPai);
    }

    public void finalizar() {
        try {
            servicoSisLaraServer.finalizar();
        } catch (Exception ex) {
            logger.error("Erro durante solicitação de finalização do serviço. \nDetalhes: " + ex);
        }
    }

    private void habilitarMenus() {
        habilitarComponente(jmiFinalizarServico, PermissaoDeTelas.FINALIZAR_SERVICO);
        habilitarComponente(jmiAtivarDesativarAviso, PermissaoDeTelas.ATIVACAO_DESATIVACAO_AVISO_ATUALIZACAO);
        habilitarComponente(jbuContribuintes, PermissaoDeTelas.CONTRIBUINTE_TELA_GEREN_VISUALIZAR);
        habilitarComponente(jbuRecibos, PermissaoDeTelas.RECIBO_TELA_GEREN_VISUALIZAR);
    }
    
    private void habilitarComponente(JComponent jcoComponente, PermissaoDeTelas permissaoDeTelas){
         try {
            if (servicoSisLaraServer.possuiAutorizacao(Sessao.obterInstancia().obterToken(), permissaoDeTelas.toString())) {
                habilitar(jcoComponente);
            }
        } catch (Exception e) {
            logger.error("Erro durante habilitação de menus. \nDetalhes: " + e);
        }
    }

    public void exibirTelaGerenGrupos() {
        new TelaGerenGrupos((JFrame) telaPai);
    }

    public void exiterTelaGerenIntegrantesGrupo() {
        new TelaGruposGerenIntegrantes((JFrame) telaPai);
    }

    public void redimensionarFundo() {
        fundo.setSize(painel.getWidth(), fundo.getHeight());
    }

    public void exibirTelaRelatorioAtendimentoPorUsuarioSimples() {
        new TelaRelatorioAtendimentoPorUsuarioSimples((JFrame) telaPai);
    }

    public void fecharAplicacao() {
        removerArquivosTemporarios();
        System.exit(0);
    }

    public void alterarExtensaoRelatorioParaXLS() {
        try {
            ResultadoAlteracaoExtensaoRelatorioDTO resultado = servicoSisLaraServer.alterarExtensaoRelatorioParaXLS(Sessao.obterInstancia().obterToken());
            if (resultado.sucesso()) {
                JOptionPanePersonalizado.mostrarTelaSucesso(telaPai, resultado.obterMensagens());
            } else {
                JOptionPanePersonalizado.mostrarTelaErro(telaPai, resultado.obterMensagens());
            }
        } catch (Exception ex) {
            logger.error(JOptionPanePersonalizado.ERRO + ex.getMessage());
            JOptionPanePersonalizado.mostrarTelaErro(telaPai, JOptionPanePersonalizado.IMPOSSIVEL_REALIZAR_CONEXAO);
        }
    }

    public void alterarExtensaoRelatorioParaPDF() {
        try {
            ResultadoAlteracaoExtensaoRelatorioDTO resultado = servicoSisLaraServer.alterarExtensaoRelatorioParaPDF(Sessao.obterInstancia().obterToken());
            if (resultado.sucesso()) {
                JOptionPanePersonalizado.mostrarTelaSucesso(telaPai, resultado.obterMensagens());
            } else {
                JOptionPanePersonalizado.mostrarTelaErro(telaPai, resultado.obterMensagens());
            }
        } catch (Exception ex) {
            logger.error(JOptionPanePersonalizado.ERRO + ex.getMessage());
            JOptionPanePersonalizado.mostrarTelaErro(telaPai, JOptionPanePersonalizado.IMPOSSIVEL_REALIZAR_CONEXAO);
        }
    }

    public void alterarSenha() {
        new TelaEditarSenha((JFrame) telaPai);
    }

    public void exibirTelaRelatorioAtendimentoPorUsuarioDetalhado() {
        new TelaRelatorioAtendimentoPorUsuarioDetalhado((JFrame) telaPai);
    }

    public void exibirTelaRelatorioTodosAtendimentoDoUsuarioOrdenadoPorGrupo() {
        new TelaRelatorioTodosAtendimentosDoUsuarioOrdenadoPorGrupo((JFrame) telaPai);
    }

    public void exibirTelaRelatorioTodosAtendimentoDoUsuarioOrdenadoPorDataLancamento() {
        new TelaRelatorioTodosAtendimentosDoUsuarioOrdenadoPorDataLancamento((JFrame) telaPai);
    }

    public void exibirTelaGerenContaAcesso() {
        new TelaGerenContaAcesso((JFrame) telaPai);
    }

    public void exibirTelaAgendaEditarAgendamento() {
        new TelaAgendaGerenAgendamento((JFrame) telaPai);
    }

    public void exibirTelaGerenListaEspera() {
        new TelaListaEsperaGeren((JFrame) telaPai);
    }

    public void exibirTelaGerenAtendimentoIndividualGeren() {
        new TelaAtendimentoIndividualGeren((JFrame) telaPai);
    }

    public void exibirTelaGerenPerfil() {
        new TelaGerenPerfil((JFrame) telaPai);
    }

    public void exibirTelaRelatorioTodosAtendimentoDoUsuarioOrdenadoPorDataLancamentoLegado() {
        new TelaRelatorioTodosAtendimentosDoUsuarioOrdenadoPorDataLancamentoLegado((JFrame) telaPai);
    }

    public void exibirTelaRelatorioQuantidadeDeUsuarioOrdenadoPorIdade() {
        try {
            exibir(servicoSisLaraServer.gerarRelatorioQuantidadeUsuarioOrdenadoPorIdade(Sessao.obterInstancia().obterToken()));
        } catch (Exception ex) {
            logger.error("Erro durante exibição de relatório. \nDetalhes:" + ex);
        }
    }

    public void exibirTelasGerenDemandas() {
        new TelaDemandaGeren((JFrame) telaPai);
    }

    public void exibirTelaGerenProjeto() {
        new TelaGerenProjeto((JFrame) telaPai);
    }

    public void exibirRelatorioRetiradaDeProntuariosNoAgendamento() {
        new TelaRelatorioRetiradaProntuariosNoAgendamento((JFrame) telaPai);
    }

    public void exibirRelatorioConferenciaAgendamentosEAtendimentosIndividuais() {
        try {
            exibir(servicoSisLaraServer.gerarRelatorioConferenciaDeAgendamentosEAtendimentosIndividuais(Sessao.obterInstancia().obterToken()));
        } catch (Exception ex) {
            logger.error("Erro durante exibição de relatório. \nDetalhes:" + ex);
        }
    }

    public void exibirRelatorioQuantidadesAtendimentosIndividuais() {
        new TelaRelatorioQuantidadeAtendimentosIndividuais((JFrame) telaPai);
    }

    public void exibirRelatorioFolhaDeRosto() {
        new TelasRelatorioFolhaDeRosto((JFrame) telaPai);
    }

    public void exibirTelaRelatorioTodosAtendimentosIndividuaisDoUsuarioOrdenadoPorDataLancamento() {
        new TelaRelatorioTodosAtendimentosIndividuaisDoUsuarioOrdenadoPorDataLancamento((JFrame) telaPai);
    }

    public void exibirTelaRelatorioContatosIntegrantesGrupo() {
        new TelaRelatorioContatosIntegrantesGrupos((JFrame) telaPai);
    }

    public void ativarFiltroDeGrupo() {
        try {
            ResultadoOperacaoFiltroGrupoDTO resultado = servicoSisLaraServer.ativarFiltroGrupo(Sessao.obterInstancia().obterToken());
            if (resultado.sucesso()) {
                JOptionPanePersonalizado.mostrarTelaSucesso(telaPai, resultado.obterMensagens());
            } else {
                JOptionPanePersonalizado.mostrarTelaErro(telaPai, resultado.obterMensagens());
            }
        } catch (Exception ex) {
            logger.error("Erro durante ativação de filtro de Grupo. \nDetalhes:" + ex);
        }
    }

    public void desativarFiltroGrupo() {
        try {
            ResultadoOperacaoFiltroGrupoDTO resultado = servicoSisLaraServer.desativarFiltroGrupo(Sessao.obterInstancia().obterToken());
            if (resultado.sucesso()) {
                JOptionPanePersonalizado.mostrarTelaSucesso(telaPai, resultado.obterMensagens());
            } else {
                JOptionPanePersonalizado.mostrarTelaErro(telaPai, resultado.obterMensagens());
            }
        } catch (Exception ex) {
            logger.error("Erro durante ativação de filtro de Grupo. \nDetalhes:" + ex);
        }
    }

    public void exibirRelatorioAcompanhamentoProgramacao() {
        new TelaRelatorioAcompanhamentoProgramacaoPorGrupo((JFrame) telaPai);
    }

    public void exibirRelatorioCargaHorariaUsuarioPorGrupo() {
        new TelaRelatorioCargaHorariaUsuarioPorGrupo((JFrame) telaPai);
    }

    public void exibirRelatorioQuantidadeGeracaoAtendimentosIndividuaisEGrupo() {
        new TelaRelatorioQuantidadeGeracaoAtendimentosIndividuaisEGrupo((JFrame) telaPai);
    }

    public void exibirRelatorioQuantidadesPessoasNaListaDeEsperaAguardando() {
        try {
            exibir(servicoSisLaraServer.gerarRelatorioQuantidadePessoasNaListaDeEsperaOrdenadoPorExpectativa(Sessao.obterInstancia().obterToken()));
        } catch (Exception ex) {
            logger.error("Erro durante exibição de relatório. \nDetalhes:" + ex);
        }
    }

    public void exibirRelatorioQuantidadeAtendidosPorTiposVinculos() {
        new TelaRelatorioQuantidadeAtendidosPorTiposVinculos((JFrame) telaPai);
    }

    public void exibirRelatorioQuantidadeAtendidosPorTiposVinculosPorGrupo() {
        new TelaRelatorioQuantidadeAtendidosPorTiposVinculosPorGrupo((JFrame) telaPai);
    }

    public void exibirTelaRetiradaEfetuar() {
        new TelaRetiradaEfetuar((JFrame) telaPai);
    }

    public void exibirTelaRetiradaBaixar() {
        new TelaRetiradaBaixar((JFrame) telaPai);
    }

    public void exibirRelatorioTodasRetiradasPendentes() {
        try {
            exibir(servicoSisLaraServer.gerarRelatorioTodasRetiradasPendentes(Sessao.obterInstancia().obterToken()));
        } catch (Exception ex) {
            logger.error("Erro durante exibição de relatório. \nDetalhes:" + ex);
        }
    }

    public void exibirRelatorioTodasRetiradasPorProntuario() {
        new TelaRelatorioRetiradasPorProntuario((JFrame) telaPai);
    }

    public void exibirTelaRelatorioProgramacaoDoGrupoParaFamilia() {
        new TelaRelatorioProgramacaoDoGrupoParaFamilia((JFrame) telaPai);
    }

    public void exibirTelaRelatorioParticipacaoUsuarioEmGrupo() {
        new TelaRelatorioParticipacaoUsuarioEmGrupo((JFrame) telaPai);
    }

    public void exibirTelaRelatorioQuantidadeDeUsuarioEmEsperaPorFaixaDeIdade() {
        new TelaRelatorioAPartirFaixaIdade((JFrame) telaPai);
    }

    public void exibirTelaRelatorioListaFrequenciaPorGrupo() {
        new TelaRelatorioListaFrequenciaPorGrupo((JFrame) telaPai);
    }

    public void exibirRelatorioQuantidadeAtendimentoParaPrefetura() {
        new TelaRelatorioQuantidadeAtendimentosParaPrefeitura((JFrame) telaPai);
    }

    public void exibirTelaRelatorioComasAtendidosEAtendimentosPorBeneficio() {
        new TelaRelatorioComasAtendidosEAtendimentosPorBeneficio((JFrame) telaPai);
    }

    public void exibirTelaRelatorioComasAtendidosPorIdadeEGenero() {
        new TelaRelatorioComasAtendidosPorIdadeEGenero((JFrame) telaPai);
    }

    public void exibirTelaRelatorioComasAtendidosEAtendimentosPorClassificacaoInstituicao() {
        new TelaRelatorioComasAtendidosEAtendimentosPorClassificacaoInstituicao((JFrame) telaPai);
    }

    public void exibirTelaRelatorioComasAtendidosEAtendimentosPorDeficiencia() {
        new TelaRelatorioComasAtendidosEAtendimentosPorDeficiencia((JFrame) telaPai);
    }

    public void exibirTelaRelatorioComasAtendidosEAtendimentosPorEscolaridade() {
        new TelaRelatorioComasAtendidosEAtendimentosPorEscolaridade((JFrame) telaPai);
    }

    public void exibirTelaRelatorioComasAtendidosEAtendimentosPorMunicipioSP() {
        new TelaRelatorioComasAtendidosEAtendimentosPorMunicipioSP((JFrame) telaPai);
    }

    public void exibirTelaRelatorioComasAtendidosEAtendimentosPorRegiaoSP() {
        new TelaRelatorioComasAtendidosEAtendimentosPorRegiaoSP((JFrame) telaPai);
    }

    public void exibirTelaRelatorioComasAtendidosEAtendimentosPorRenda() {
        new TelaRelatorioComasAtendidosEAtendimentosPorRenda((JFrame) telaPai);
    }

    public void exibirTelaRelatorioComasAtendidosEAtendimentosPorTipoAtendimento() {
        new TelaRelatorioComasAtendidosEAtendimentosPorTipoAtendimento((JFrame) telaPai);
    }

    public void exibirTelaRelatorioComasAtendidosEAtendimentosPorUF() {
        new TelaRelatorioComasAtendidosEAtendimentosPorUF((JFrame) telaPai);
    }

    public void exibirTelaRelatorioAtendidosPorVisitaMonitorada() {
        new TelaRelatorioQuantidadeAtendidosPorComunidadeDeVisitaMonitorada((JFrame) telaPai);
    }

    public void exibirTelaSolicitacaoRelatorio() {
        new TelaSolicitacaoRelatorioGeren((JFrame) telaPai);
    }

    public void acionarAvisoAtualizacao() {
        try {
            if (servicoSisLaraServer.ativarDesativarAvisoDeAtualizacao()) {
                JOptionPanePersonalizado.mostrarTelaInformacao(telaPai, JOptionPanePersonalizado.ATIVACAO_AVISO_CONFIRMADA);
            } else {
                JOptionPanePersonalizado.mostrarTelaInformacao(telaPai, JOptionPanePersonalizado.DESATIVACAO_AVISO_CONFIRMADA);
            }
        } catch (Exception ex) {
            logger.error("Erro durante aviso de atualização. \nDetalhes:" + ex);
        }
    }

    public void exibirTelaRelatorioAtendidosSemInformacaoDeficiencias() {
        new TelaRelatorioAtendidosSemInformacaoDeficiencia((JFrame) telaPai);
    }

    public void exibirTelaGerenContribuintes() {
        new TelaGerenContribuintes((JFrame)telaPai);
    }

    public void exibirTelaRelatorioTodasFrequenciasOrdenadaPorDataLancamento() {
        new TelaRelatorioTodasFrequenciasOrdenadaPorDataLancamento((JFrame)telaPai);
    }

    public void exibirTelaRelatorioTodosUsuariosComStatusProvisorioOuAcessoNoGrupo() {
         try {
            exibir(servicoSisLaraServer.gerarRelatorioTodosUsuarioComStatusDeGrupoProvisorioOuAcesso(Sessao.obterInstancia().obterToken()));
        } catch (Exception ex) {
            logger.error("Erro durante exibição de relatório. \nDetalhes:" + ex);
        }
    }

    public void gerarERecebarArquivoCobranca() {
        try {
            if (servicoSisLaraServer.solicitarGeracaoArquivoCobranca(Sessao.obterInstancia().obterToken())) {
                JOptionPanePersonalizado.mostrarTelaSucesso(telaPai, JOptionPanePersonalizado.ARQUIVO_GERADO_COM_SUCESSO);
            } else {
                JOptionPanePersonalizado.mostrarTelaErro(telaPai, JOptionPanePersonalizado.ERRO_DURANTE_GERACAO_ARQUIVO);
            }
        } catch (Exception ex) {
            logger.error("Erro durante solicitação de geração do arquivo. \nDetalhes:" + ex);
        }
    }

    public void atualizarListaPendencia() {
    }

    public void resolverPendencia() {
        if (estaComItemValidoSelecionado(jliPendencias)) {
            PendenciaDTO pendenciaDto = (PendenciaDTO) obterDtoSelecionado(jliPendencias);
            if (pendenciaDto.ePendenciaAtendimentoIndividual()) {
                new TelaAtendimentoIndividualEditar(telaPai, true, pendenciaDto.getAtendimentoIndividualDTOReferenciaParaGeracao());
            } else if (pendenciaDto.ePendenciaAtendimentoGrupo()) {
                if (JOptionPanePersonalizado.mostrarTelaPergunta(telaPai, JOptionPanePersonalizado.CONFIRMACAO) == JOptionPane.OK_OPTION) {
                    try {
                        SomUtils.iniciar();
                        ResultadoGeracaoAtendimentoDTO resultadoGeracaoAtendimentoDTO = servicoSisLaraServer.gerarAtendimentos(pendenciaDto, Sessao.obterInstancia().obterToken());
                        SomUtils.parar();
                        if (resultadoGeracaoAtendimentoDTO.sucesso()) {
                            new TelaGruposEditarAtendimentos(telaPai, resultadoGeracaoAtendimentoDTO.getGrupoDto(), (ModuloPeriodoDTO) resultadoGeracaoAtendimentoDTO.obterObjetoDtoEditado(), resultadoGeracaoAtendimentoDTO.getAtendimentoGrupoGeradoDTO(), true);
                            jliPendencias.requestFocusInWindow();
                        } else {
                            JOptionPanePersonalizado.mostrarTelaErro(telaPai, resultadoGeracaoAtendimentoDTO.obterMensagens());
                        }
                    } catch (Exception ex) {
                        JOptionPanePersonalizado.mostrarTelaErro(telaPai, JOptionPanePersonalizado.IMPOSSIVEL_REALIZAR_CONEXAO);
                        logger.fatal(ex);
                    }
                }
            }
        } else {
            JOptionPanePersonalizado.mostrarTelaErro(telaPai, JOptionPanePersonalizado.SELECIONAR_PENDENCIA);
        }
    }

    boolean estaComListaDeselecionada() {
        return !jliPendencias.isFocusOwner();
    }

    public void exibirTelaRelatorioQuantidadesDeAtendimentosDeAvaliacoesFuncionais() {
        new TelaRelatorioQuantidadesAtendimentosAvaliacaoFuncionais((JFrame)telaPai);
    }

    public void abrirTelaRelatorioFluxoAtendimentoAvaliacaoFuncionalCasosNovos() {
        new TelaRelatorioFluxoAtendimentoAvaliacaoFuncionalCasosNovos((JFrame)telaPai);
    }

    public void abrirTelaRelatorioFluxoAtendimentoAvaliacaoFuncionalRetornos() {
        new TelaRelatorioFluxoAtendimentoAvaliacaoFuncionalRetornos((JFrame)telaPai);
    }

    public void exibirRelatorioVagasEmGruposAtivos() {
        try {
            exibir(servicoSisLaraServer.gerarRelatorioVagasEmGruposAtivos(Sessao.obterInstancia().obterToken()));
        } catch (Exception ex) {
            logger.error("Erro durante exibição de relatório. \nDetalhes:" + ex);
        }
    }

    public void abrirTelaArquivos() {
        new TelaGerenArquivoDisponivel((JFrame)telaPai);
    }

    public void abrirTelaRelatorioMailingVisitaMonitorada() {
        new TelaMailing((JFrame)telaPai);
    }

    public void abrirTelaGerenRecibo() {
        new TelaGerenRecibo((JFrame)telaPai);
    }

    public void exibirTelaRelatorioRecibo() {
        new TelaRelatorioRecibo((JFrame)telaPai);
    }

    public void exibirTelaEditarReciboCancelar() {
        new TelaEditarReciboCancelar((JFrame)telaPai);
    }
}

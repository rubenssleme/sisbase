
package br.laramara.ti.sismovimentacaoclient.controladores;


import br.laramara.ti.sismovimentacaoclient.infra.PermissaoDeTelas;
import br.laramara.ti.sismovimentacaoclient.telas.TelaDesbloqueio;
import br.laramara.ti.sismovimentacaoclient.telas.TelaEditarSenha;
import br.laramara.ti.sismovimentacaoclient.telas.TelaGerenContaAcesso;
import br.laramara.ti.sismovimentacaoclient.telas.TelaGerenMovimentacao;
import br.laramara.ti.sismovimentacaoclient.telas.TelaGerenPerfil;
import br.laramara.ti.sismovimentacaoclient.telas.TelaInfoSistema;
import br.laramara.ti.sismovimentacaoclient.telas.TelaLogin;
import br.laramara.ti.sismovimentacaoclient.utilitarios.JOptionPanePersonalizado;
import br.laramara.ti.sismovimentacaocommons.dtos.seguranca.ResultadoAlteracaoExtensaoRelatorioDTO;
import br.laramara.ti.sismovimentacaocommons.dtos.seguranca.ResultadoOperacaoFiltroGrupoDTO;
import br.laramara.ti.sismovimentacaocommons.utilitarios.Sessao;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;


public class ControladorTelaPrincipal extends ControladorTela {
    
    private JLabel fundo;
    private JPanel painel;
    private JMenuItem jmFinalizarServico;

    public ControladorTelaPrincipal(JFrame telaPai, JLabel fundo, JPanel painel, JMenuItem jmFinalizarServico) {
        super(telaPai);
        TelaLogin telaLogin = new TelaLogin((JFrame) telaPai);
        telaLogin.setLocationRelativeTo(telaPai);
        telaLogin.setVisible(true);
        this.fundo = fundo;
        this.painel = painel;
        this.jmFinalizarServico = jmFinalizarServico;
        habilitarMenus();
        ajustarTitulo(telaPai);
    }

    private void ajustarTitulo(JFrame telaPai){
        String tituloAtual = telaPai.getTitle();
        telaPai.setTitle(tituloAtual+" - Número da sessão: " + Sessao.obterInstancia().obterToken().getToken());
    }
    
    public void exibirTelaInformacoes() {
        new TelaInfoSistema((JFrame) telaPai);
    }

    public void exibirTelaDesbloqueioUsuario() {
        new TelaDesbloqueio((JFrame)telaPai);
    }
        
    public void finalizar() {
        try {
            servicoSisMovimentacaoServer.finalizar();
        } catch (Exception ex) {
            logger.error("Erro durante solicitação de finalização do serviço. \nDetalhes: " + ex);
        }
    }

    private void habilitarMenus() {
        try {
            if (servicoSisMovimentacaoServer.possuiAutorizacao(Sessao.obterInstancia().obterToken(), PermissaoDeTelas.FINALIZAR_SERVICO.toString())) {
                habilitar(jmFinalizarServico);
            }
        } catch (Exception e) {
            logger.error("Erro durante habilitação de menus. \nDetalhes: " + e);
        }
    }

    public void redimensionarFundo() {
        fundo.setSize(painel.getWidth(), fundo.getHeight());
    }

    public void fecharAplicacao() {
        removerArquivosTemporarios();
        System.exit(0);
    }

    public void alterarExtensaoRelatorioParaXLS() {
       try {
            ResultadoAlteracaoExtensaoRelatorioDTO resultado = servicoSisMovimentacaoServer.alterarExtensaoRelatorioParaXLS(Sessao.obterInstancia().obterToken());
            if (resultado.sucesso()){
                JOptionPanePersonalizado.mostrarTelaInformacao(telaPai, JOptionPanePersonalizado.SUCESSO, resultado.obterMensagens());
            } else{
                JOptionPanePersonalizado.mostrarTelaInformacao(telaPai, JOptionPanePersonalizado.ERRO, resultado.obterMensagens());
            }
        } catch (Exception ex) {
            logger.error(JOptionPanePersonalizado.ERRO + ex.getMessage());
            JOptionPanePersonalizado.mostrarTelaErro(telaPai, JOptionPanePersonalizado.ERRO, JOptionPanePersonalizado.IMPOSSIVEL_REALIZAR_CONEXAO);
        }
    }

    public void alterarExtensaoRelatorioParaPDF() {
        try {
            ResultadoAlteracaoExtensaoRelatorioDTO resultado = servicoSisMovimentacaoServer.alterarExtensaoRelatorioParaPDF(Sessao.obterInstancia().obterToken());
            if (resultado.sucesso()){
                JOptionPanePersonalizado.mostrarTelaInformacao(telaPai, JOptionPanePersonalizado.SUCESSO, resultado.obterMensagens());
            } else{
                JOptionPanePersonalizado.mostrarTelaInformacao(telaPai, JOptionPanePersonalizado.ERRO, resultado.obterMensagens());
            }
        } catch (Exception ex) {
            logger.error(JOptionPanePersonalizado.ERRO + ex.getMessage());
            JOptionPanePersonalizado.mostrarTelaErro(telaPai, JOptionPanePersonalizado.ERRO, JOptionPanePersonalizado.IMPOSSIVEL_REALIZAR_CONEXAO);
        }
    }

    public void alterarSenha() {
        new TelaEditarSenha((JFrame)telaPai);
    }

    public void exibirTelaGerenContaAcesso() {
        new TelaGerenContaAcesso((JFrame)telaPai);
    }

    public void exibirTelaGerenPerfil() {
        new TelaGerenPerfil((JFrame)telaPai);
    }
    
    public void ativarFiltroDeGrupo() {
        try {
            ResultadoOperacaoFiltroGrupoDTO resultado = servicoSisMovimentacaoServer.ativarFiltroGrupo(Sessao.obterInstancia().obterToken());
            if (resultado.sucesso()){
                JOptionPanePersonalizado.mostrarTelaInformacao(telaPai, JOptionPanePersonalizado.SUCESSO, resultado.obterMensagens());
            } else{
                JOptionPanePersonalizado.mostrarTelaInformacao(telaPai, JOptionPanePersonalizado.ERRO_NA_ATIVACAO_FILTRO, resultado.obterMensagens());
            }
        } catch (Exception ex) {
            logger.error("Erro durante ativação de filtro de Grupo. \nDetalhes:" + ex);
        }
    }

    public void desativarFiltroGrupo() {
        try {
            ResultadoOperacaoFiltroGrupoDTO resultado = servicoSisMovimentacaoServer.desativarFiltroGrupo(Sessao.obterInstancia().obterToken());
            if (resultado.sucesso()){
                JOptionPanePersonalizado.mostrarTelaInformacao(telaPai, JOptionPanePersonalizado.SUCESSO, resultado.obterMensagens());
            } else{
                JOptionPanePersonalizado.mostrarTelaInformacao(telaPai, JOptionPanePersonalizado.ERRO_NA_ATIVACAO_FILTRO, resultado.obterMensagens());
            }
        } catch (Exception ex) {
            logger.error("Erro durante ativação de filtro de Grupo. \nDetalhes:" + ex);
        }
    }

    public void exibirTelaGerenMovimentacao() {
        new TelaGerenMovimentacao((JFrame)telaPai);
    }
}
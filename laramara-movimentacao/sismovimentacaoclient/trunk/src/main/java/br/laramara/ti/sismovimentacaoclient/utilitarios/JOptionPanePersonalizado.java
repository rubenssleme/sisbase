package br.laramara.ti.sismovimentacaoclient.utilitarios;

import br.laramara.ti.sismovimentacaoclient.telas.TelaErro;
import br.laramara.ti.sismovimentacaoclient.telas.TelaInformacao;
import br.laramara.ti.sismovimentacaoclient.telas.TelaPergunta;
import br.laramara.ti.sismovimentacaoclient.telas.TelaSelecionarData;
import br.laramara.ti.sismovimentacaocommons.utilitarios.DataHoraUtils;
import java.awt.Window;
import java.util.Calendar;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import org.apache.log4j.Logger;

public class JOptionPanePersonalizado {
    
    protected static final Logger logger = Logger.getLogger(JOptionPanePersonalizado.class);

    public static final String ERRO_NA_ABERTURA_DE_TELA = "Houve um erro durante a abertura da tela. \n";
    public static final String ERRO_NA_OBTENCAO_DADOS_AUXILIARES = "Erro durante obtenção de dados auxiliares. \n";
    public static final String ERRO_NA_RESOLUCAO_DE_CAMPOS_DEPENDENTES = "Erro durante resolução de campos dependentes. \n";
    public static final String NAO_POSSUI_ACESSO_A_AREA = "Você não tem permissão para acessar essa área do sistema. \n";
    public static final String IMPOSSIVEL_REALIZAR_CONEXAO = "Não foi possível realizar a conexão com o servidor. \n";
    public static final String ERRO_NA_REALIZACAO_PESQUISA = "Erro na realização da pesquisa. \n";
    public static final String SELECIONAR_REGISTRO = "Selecione um registro. \n";
    public static final String ERRO_NA_GERACAO_RELATORIO = "Erro durante a geração de relatório. \n";
    public static final String ERRO_ADICAO_FAMILIAR = "Erro durante adição de Familiar. \n";
    public static final String ERRO_ADICAO_TELEFONE = "Erro durante adição de Telefone. \n";
    public static final String ERRO_ADICAO_MODULO_PERIODO = "Erro durante adição de Módulo e Período. \n";
    public static final String ERRO_USUARIO_JA_CADASTRADO = "Usuário já cadastrado. \n";
    public static final String ERRO_COORDENACAO_BLOQUEIO = "Erro durante o bloqueio de objeto.";
    public static final String ERRO_COORDENACAO_DESBLOQUEIO = "Erro durante o desbloqueio de objeto.";
    public static final String ERRO_LISTAGEM_USUARIOS_E_GRUPOS_BLOQUEADOS = "Erro durante listagem de Usuários e Grupos bloqueados.";
    public static final String ERRO_NA_SINCRONIZACAO_PROFISSIONAIS = "Erro durante sincronização de Profissionais. \n";
    public static final String ERRO_DURANTE_GERACAO_ATENDIMENTOS = "Erro durante geração de atendimentos. \n";
    public static final String ERRO_DURANTE_CANCELAMENTO_ATENDIMENTO = "Erro durante cancelamento de atendimento. \n";
    public static final String ERRO_DURANTE_SELECAO_FOTO = "Erro durante seleção de foto. \n";
    public static final String ERRO_DURANTE_CARREGAMENTO_PRONTUARIO_ESCANEADO = "Erro durante carregamento de prontuários escaneados. \n";
    public static final String ERRO_DURANTE_CARREGAMENTO_DO_ENDERECO = "Erro durante carregamento do endereço. \n";
    public static final String ERRO_DURANTE_ABERTURA_PRONTUARIO_ESCANEADO = "Erro durante abertura de prontuários escaneados. \n";
    public static final String ERRO_NA_GERACAO_AGENDAMENTO = "Erro durante solicitação de agendamento. \n";    
    public static final String ERRO_NA_GERACAO_DEMANDA = "Erro durante solicitação de demanda. \n";  
    public static final String ERRO_DURANTE_LIBERACAO_AGENDAMENTO = "Erro durante liberação de Agendamento.";
    public static final String ACESSO_NEGADO = "Acesso Negado";
    public static final String ERRO = "Erro";
    public static final String INFORMACAO = "Informação";
    public static final String ERRO_ADICAO_LOTE_RECURSO =  "Erro durante adição de Lote Recurso. \n";
    public static final String ERRO_DURANTE_SELECAO_AGENDAMENTO = "Selecione um Agendamento.";
    public static final String ERRO_NA_ATIVACAO_FILTRO = "Erro na ativação do filtro de Grupos. \n";
    public static final String ERRO_NA_EFETUACAO_RETIRADA = "Erro na efetuação da retirada. \n";
    public static final String ERRO_NA_EFETUACAO_BAIXA = "Erro na efetuação da baixa. \n";
    public static final String ERRO_SEM_TODOS_PARAMETROS_PESQUISA = "Selecione todos os itens para efetuar a pesquisa. \n";

    public static final String SUCESSO = "Sucesso";
    public static final String PERGUNTA = "Pergunta";
    public static final String CONFIRMACAO = "Tem certeza que deseja realizar essa operação?";
    
    public static void mostrarTelaInformacao(Window pai, String titulo, String informacao) {
        new TelaInformacao(pai, titulo, informacao);
        aplicarRepaint(pai);
    }
    
    public static void mostrarTelaErro(Window pai, String titulo, String erro) {
        new TelaErro(pai, titulo, erro);
        aplicarRepaint(pai);
    }
    public static int mostrarTelaPergunta(Window pai, String titulo, String pergunta){
        TelaPergunta telaPergunta = new TelaPergunta(pai, titulo, pergunta);
        aplicarRepaint(pai);
        return telaPergunta.resposta();
    }
    public static void mostrarTelaSelecionarData(JDialog telaPai, JFormattedTextField jft) {
        Calendar dataSelecionada = DataHoraUtils.obterDataValidaInvalidaOuNulo(jft.getText());
        TelaSelecionarData telaSelecionarData = new TelaSelecionarData(telaPai, dataSelecionada);
        if (telaSelecionarData.possuiDataSelecionada()) {
            jft.setText(telaSelecionarData.obterDataSelecionada());
        }
        aplicarRepaint(telaPai);
    }

    public static String mostrarTelaInclusao(JDialog telaPai, String titulo) {
        return JOptionPane.showInputDialog(telaPai, titulo);
    }
    
    private static void aplicarRepaint(Window tela){
        tela.repaint();
    }
}

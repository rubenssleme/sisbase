package br.laramara.ti.sislaraclient.utilitarios;

import br.laramara.ti.sislaraclient.modelos.MeuModelListener;
import br.laramara.ti.sislaraclient.modelos.ModeloTabela;
import java.awt.Color;
import java.io.File;
import java.util.List;
import java.util.Map;
import javax.swing.JTable;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;

public class TabelaUtils {

    public static String SEPARADOR_DADOS = ";";
    protected static Logger logger;

    public TabelaUtils() {
        this.logger = Logger.getLogger(getClass());
    }

    public static void configurarCorTabela(String colunaReferencia, Map<String, Color> relacaoTipoCor, JTable tabela) {
        CorRenderer renderizador = new CorRenderer(colunaReferencia, relacaoTipoCor);
        for (int i = 0; i < tabela.getColumnCount(); i++) {
            tabela.getColumnModel().getColumn(i).setCellRenderer(renderizador);
        }
    }
        
    public static void configurarTamanhoERenderizadorDeColunas(JTable jta) {
        ModeloTabela modeloTabela = (ModeloTabela) jta.getModel();
        int numeroColunas = jta.getColumnCount();
        for (int i = 0; i < numeroColunas; i++) {
            int tamanhoPreferidoColuna = modeloTabela.obterTamanhoPreferido(i);
            ((TableColumn) jta.getColumnModel().getColumn(i)).setPreferredWidth(tamanhoPreferidoColuna);
            if (modeloTabela.getColumnClass(i) == Boolean.class) {
                TableColumn modelo = jta.getColumnModel().getColumn(i);
                modelo.setCellRenderer(new RespostaRenderer());
            }
        }
        jta.getColumnModel().addColumnModelListener(new MeuModelListener(jta));
        carregarOrdemERedimensionarColunas(jta);
    }

    public static String obterNomeModelo(JTable jta) {
        return jta.getModel().getClass().getSimpleName();
    }

    private static void carregarOrdemERedimensionarColunas(JTable jtaTabela) {
        File arquivo = new File(Configuracoes.DIRETORIO_PERMANENTE_ARQUIVOS + obterNomeModelo(jtaTabela));
        if (arquivo.exists()) {
            try {
                List<String> ordem = FileUtils.readLines(arquivo);
                reorganizarTamanhoEPosicaoColunas(jtaTabela, ordem);
            } catch (Exception e) {
                logger.fatal("Erro durante leitura de arquivo de ordem das colunas da tabela. \nDetalhes: " + e);
            }
        }
    }

    private static void reorganizarTamanhoEPosicaoColunas(JTable table, List<String> columnNames) {
        TableColumnModel model = table.getColumnModel();
        int indiceNovo = 0;
        for (String coluna : columnNames) {
            String[] nomeETamanhoColunas = coluna.split(SEPARADOR_DADOS);
            try {
                if (existeColuna(table, nomeETamanhoColunas[0])) {
                    int indiceAntigo = model.getColumnIndex(nomeETamanhoColunas[0]);
                    redimensionarColuna(model, nomeETamanhoColunas, indiceAntigo);
                    model.moveColumn(indiceAntigo, indiceNovo);
                    indiceNovo++;
                }
            } catch (Exception e) {
                logger.error("Erro durante reorganização de colunas. \nDetalhes: " + e);
            }
        }
    }

    private static void redimensionarColuna(TableColumnModel model, String[] nomeETamanhoColunas, int indice) {
        if (nomeETamanhoColunas.length != 1) {
            model.getColumn(indice).setPreferredWidth(Integer.valueOf(nomeETamanhoColunas[1]));
        }
    }

    private static boolean existeColuna(JTable tabela, String nomeColuna) {
        TableColumnModel model = tabela.getColumnModel();
        try {
            model.getColumnIndex(nomeColuna);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}

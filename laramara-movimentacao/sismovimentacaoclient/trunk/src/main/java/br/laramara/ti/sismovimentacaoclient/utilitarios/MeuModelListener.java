package br.laramara.ti.sismovimentacaoclient.utilitarios;

import java.io.File;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;
import javax.swing.JTable;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.TableColumnModelEvent;
import javax.swing.event.TableColumnModelListener;
import javax.swing.table.TableColumn;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;

public class MeuModelListener implements TableColumnModelListener {

    protected Logger logger;
    private JTable table;

    public MeuModelListener(JTable table) {
        this.table = table;
        this.logger = Logger.getLogger(getClass());
    }

    public void columnAdded(TableColumnModelEvent e) {
    }

    public void columnRemoved(TableColumnModelEvent e) {
    }

    public void columnMoved(TableColumnModelEvent e) {
        armazenarOrdemETamanho();
    }

    public void columnMarginChanged(ChangeEvent e) {
        armazenarOrdemETamanho();
    }

    public void columnSelectionChanged(ListSelectionEvent e) {
    }

    public void armazenarOrdemETamanho() {
        File arquivo = new File(Configuracoes.DIRETORIO_PERMANENTE_ARQUIVOS + TabelaUtils.obterNomeModelo(table));
        List<String> ordem = obterColunas(table);
        try {
            FileUtils.writeLines(arquivo, ordem);
        } catch (Exception e) {
            logger.fatal("Erro durante gravação de arquivo de ordem das colunas da tabela. \nDetalhes: " + e);
        }
    }

    private List<String> obterColunas(JTable jtaTabela) {
        String[] retorno = new String[jtaTabela.getColumnModel().getColumnCount()];
        int contador = 0;
        Enumeration<TableColumn> colunas = jtaTabela.getColumnModel().getColumns();
        while (colunas.hasMoreElements()) {
            TableColumn coluna = colunas.nextElement();
            retorno[contador] = (String) coluna.getIdentifier() + TabelaUtils.SEPARADOR_DADOS + String.valueOf(coluna.getWidth());
            contador++;
        }
        return Arrays.asList(retorno);
    }
}

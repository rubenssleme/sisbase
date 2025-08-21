package br.laramara.ti.sislaraclient.utilitarios;

import java.awt.Color;
import java.awt.Component;
import java.util.Map;
import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableCellRenderer;

public class CorRenderer extends DefaultTableCellRenderer {

    private String nomeColunaReferencia;
    private Map<String, Color> relacaoTipoCor;
            
    public CorRenderer(String nomeColunaReferencia, Map<String, Color> relacaoTipoCor) {
        super.setOpaque(true);
        this.nomeColunaReferencia = nomeColunaReferencia;
        this.relacaoTipoCor = relacaoTipoCor;
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
            boolean hasFocus, int row, int column) {
        Component componente = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        
        Border outside = new MatteBorder(1, 0, 1, 0, Color.RED);
	Border inside = new EmptyBorder(0, 1, 0, 1);
	Border highlight = new CompoundBorder(outside, inside);
                        
        Color corPadraoFrente = Color.BLACK;
        Color corPadraoFundo = Color.WHITE;
        setBackground(corPadraoFundo);

        int indicePosicaoColuna = 0;
        for (int i = 0; i < table.getColumnCount(); i++) {
            String nomeColuna = table.getColumnName(i);
            if (nomeColuna.equalsIgnoreCase(nomeColunaReferencia)) {
                indicePosicaoColuna = i;
            }
        }
        Object dadoNaTabela = table.getValueAt(row, indicePosicaoColuna);

        if (relacaoTipoCor.containsKey(dadoNaTabela)) {
            setBackground(relacaoTipoCor.get(dadoNaTabela));
        }else{
            setBackground(corPadraoFrente);
        }
        if (isSelected) {
            setBorder(highlight);
            setForeground(corPadraoFrente);
        } else {
            setForeground(corPadraoFrente);
        }
        return componente;
    }
}

package br.laramara.ti.sismovimentacaoclient.utilitarios;

import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class RespostaRenderer extends DefaultTableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        if (value instanceof Boolean) {
            if ((Boolean) value) {
                this.setText("Sim");
            } else {
                this.setText("Não");
            }
        }
        return this;
    }
}

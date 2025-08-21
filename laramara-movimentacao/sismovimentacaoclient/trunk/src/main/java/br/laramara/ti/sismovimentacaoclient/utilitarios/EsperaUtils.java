package br.laramara.ti.sismovimentacaoclient.utilitarios;

import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDayChooser;
import com.toedter.calendar.JMonthChooser;
import com.toedter.calendar.JYearChooser;
import java.awt.Component;
import java.awt.Container;
import java.awt.Cursor;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.SwingWorker;

public class EsperaUtils extends SwingWorker<Void, Void> {

    private JButton jbu;
    private JDialog tela;
    private Runnable comando;
    private String textoBotao;

    public EsperaUtils(Runnable comando, JDialog tela) {
        this(null, comando, tela);
    }
    
    private EsperaUtils(JButton jbu, Runnable comando, JDialog tela) {
        this.jbu = jbu;
        this.tela = tela;
        this.textoBotao = jbu != null ? jbu.getText() : "";
        this.comando = comando;
    }

    @Override
    public Void doInBackground() throws InterruptedException {
        if (jbu != null){
            jbu.setText("Aguarde");
        }
        desabilitarComponentes();
        comando.run();
        return null;
    }

    private void habilitarComponentes() {
        configurarComponentes(true, tela);
        tela.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }

    private void desabilitarComponentes() {
        configurarComponentes(false, tela);
        tela.setCursor(new Cursor(Cursor.WAIT_CURSOR));
    }

    private void configurarComponentes(boolean valor, Container container) {
        Component[] components = container.getComponents();
        for (Component component : components) {
            if (!(component instanceof JCalendar) && !(component instanceof  JDayChooser) && !(component instanceof JMonthChooser) &&  !(component instanceof JYearChooser)){
                component.setEnabled(valor);
            }
            configurarComponentes(valor, (Container)component);
        }
    }

    @Override
    public void done() {
        habilitarComponentes();
        if (jbu != null){
            jbu.setText(textoBotao);
            jbu.requestFocusInWindow();
        }
    }
}
package br.laramara.ti.sislaraclient.utilitarios;

import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDayChooser;
import com.toedter.calendar.JMonthChooser;
import com.toedter.calendar.JYearChooser;
import java.awt.Component;
import java.awt.Container;
import java.awt.Cursor;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.SwingWorker;

public class EsperaUtils extends SwingWorker<Void, Void> {

    private JButton jbu;
    private JDialog tela;
    private Runnable comando;
    private String textoBotao;
    private Map<String, Boolean> estadosHistoricoComponentes;

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
        estadosHistoricoComponentes = new HashMap<>();
        inicializarHistoricoComponentes(tela);
        configurarComponentes(false, tela);
        tela.setCursor(new Cursor(Cursor.WAIT_CURSOR));
    }

    private void configurarComponentes(boolean valor, Container container) {
        Component[] components = container.getComponents();
        for (Component component : components) {
            if (!(component instanceof JCalendar) && !(component instanceof  JDayChooser) && !(component instanceof JMonthChooser) &&  !(component instanceof JYearChooser)){
                String chave = obterNomeExtenso(component);
                if (valor == true && estadosHistoricoComponentes.containsKey(chave)){
                    component.setEnabled(estadosHistoricoComponentes.get(chave));
                }else{
                    component.setEnabled(valor);
                }
            }
            configurarComponentes(valor, (Container)component);
        }
    }
    
    private void inicializarHistoricoComponentes(Container container){
        for (Component component : container.getComponents()) {
            if (!(component instanceof JCalendar) && !(component instanceof  JDayChooser) && !(component instanceof JMonthChooser) &&  !(component instanceof JYearChooser)){
                estadosHistoricoComponentes.put(obterNomeExtenso(component), component.isEnabled());
            }
            inicializarHistoricoComponentes((Container)component);
        }
    }
    
    private String obterNomeExtenso(Component componente) {
        if (componente.getParent() != null) {
            return componente.getName() + "-" + obterNomeExtenso(componente.getParent());
        } else {
            return "";
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
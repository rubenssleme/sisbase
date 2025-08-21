package br.laramara.ti.sislaraclient.utilitarios;

import com.toedter.calendar.JDayChooser;
import com.toedter.calendar.JMonthChooser;
import com.toedter.calendar.JYearChooser;
import java.awt.Component;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.lang.reflect.Field;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JViewport;
import javax.swing.text.AbstractDocument;
import org.apache.log4j.Logger;

public class TelaUtils {

    protected static final Logger logger = Logger.getLogger(TelaUtils.class);

    private static void habilitarUpperCase(JTextField componente) {
        ((AbstractDocument) componente.getDocument()).setDocumentFilter(new FiltroUpcase());
    }

    public static void habilitarSomenteUpperCaseNosCamposTexto(Object controlador) {
        final Field fields[] = controlador.getClass().getDeclaredFields();
        for (int i = 0; i < fields.length; ++i) {
            if (fields[i].getType().equals(JTextField.class)) {
                try {
                    fields[i].setAccessible(true);
                    habilitarUpperCase((JTextField) fields[i].get(controlador));
                } catch (IllegalAccessException ex) {
                    logger.error("Erro durante habilitação de uppercase. \nDetalhes: " + ex);
                }
            }
        }
    }

    public static void habilitarSensibilidadeMouse(JScrollPane jspServicoSocial) {
        jspServicoSocial.getVerticalScrollBar().setUnitIncrement(50);
    }

    public BufferedImage obterIcone() {
        BufferedImage imagem = null;
        try {
            InputStream is = this.getClass().getClassLoader().getResourceAsStream("br/laramara/ti/sislaraclient/telas/resources/icones/Logo_Sislara.jpg");
            imagem = ImageIO.read(is);
        } catch (Exception e) {
            logger.error("Erro no carregamento do icone da aplicação. \nDetalhes: " + e);
        }
        return imagem;
    }

    public static String obterData(JDayChooser jdcData, JMonthChooser jmcData, JYearChooser jycData) {
        String dia = ajustarData(String.valueOf(jdcData.getDay()));
        String mes = ajustarData(String.valueOf(jmcData.getMonth() + 1));
        String ano = ajustarData(String.valueOf(jycData.getYear()));
        return dia + "/" + mes + "/" + ano;
    }

    private static String ajustarData(String data) {
        return data.length() == 1 ? "0" + data : data;
    }

    public static void gerarEventoAutoFoco(final JPanel jpa) {
        Component[] components = jpa.getComponents();
        for (final Component component : components) {
            if (component instanceof JScrollPane) {
                final JScrollPane jsp = ((JScrollPane) component);
                JViewport jvp = jsp.getViewport();
                Component componente = jvp.getView();
                componente.addFocusListener(new FocusAdapter() {
                    @Override
                    public void focusGained(FocusEvent evt) {
                        jpa.scrollRectToVisible(jsp.getBounds());
                    }
                });
            }
            if ((component instanceof JTextField) || (component instanceof JFormattedTextField) || (component instanceof JButton) || (component instanceof JCheckBox) || (component instanceof JComboBox)) {
                component.addFocusListener(new FocusAdapter() {
                    @Override
                    public void focusGained(FocusEvent evt) {
                        jpa.scrollRectToVisible(evt.getComponent().getBounds());
                    }
                });
            }
        }
    }
}

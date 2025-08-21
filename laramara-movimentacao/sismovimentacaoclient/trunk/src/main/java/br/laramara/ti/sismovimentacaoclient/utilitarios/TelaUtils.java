package br.laramara.ti.sismovimentacaoclient.utilitarios;

import com.toedter.calendar.JDayChooser;
import com.toedter.calendar.JMonthChooser;
import com.toedter.calendar.JYearChooser;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.lang.reflect.Field;
import javax.imageio.ImageIO;
import javax.swing.JDialog;
import javax.swing.JTextField;
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

    public BufferedImage obterIcone() {
        BufferedImage imagem = null;
        try {
            InputStream is = this.getClass().getClassLoader().getResourceAsStream("br/laramara/ti/sismovimentacaoclient/telas/resources/icones/Logo_SisMovimentacao.jpg");
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

    public static void maximizar(JDialog tela) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double width = screenSize.getWidth();
        double height = screenSize.getHeight();
        tela.setSize((int) width, (int) height - 50);
    }
}

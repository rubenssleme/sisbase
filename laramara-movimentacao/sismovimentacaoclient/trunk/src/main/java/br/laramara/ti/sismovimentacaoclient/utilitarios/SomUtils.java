package br.laramara.ti.sismovimentacaoclient.utilitarios;

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import org.apache.log4j.Logger;

public class SomUtils {

    protected static final Logger logger = Logger.getLogger(SomUtils.class);
    public static final String CAMINHO_SOM = "sons/";
    public static Clip clip;

    private SomUtils(){
    }
    
    public static void iniciar() {
        try {
            obterClip().setFramePosition(0);
            obterClip().loop(1000);
            obterClip().start();
        } catch (Exception e) {
            logger.error("Eror durante beep. \nDetalhes: " + e);
        }
    }
    
    public static void parar() {
        try {
            obterClip().stop();
        } catch (Exception e) {
            logger.error("Eror durante beep. \nDetalhes: " + e);
        }
    }

    private static Clip obterClip() throws LineUnavailableException, IOException, UnsupportedAudioFileException {
        File arquivo = new File(CAMINHO_SOM + "beep.wav");
        AudioInputStream inputStream = AudioSystem.getAudioInputStream(arquivo);
        if (clip == null) {
            clip = AudioSystem.getClip();
            clip.open(inputStream);
        }
        return clip;
    }
}

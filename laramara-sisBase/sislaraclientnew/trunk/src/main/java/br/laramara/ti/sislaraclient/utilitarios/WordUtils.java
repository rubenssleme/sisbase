package br.laramara.ti.sislaraclient.utilitarios;

import java.awt.event.KeyEvent;
import java.io.File;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.util.UUID;
import javax.swing.JTextArea;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;

public class WordUtils {

    protected static final Logger logger = Logger.getLogger(WordUtils.class);

    private static String instalacoes = "";

    private static String obterLocalizacoesWord() {
        File arquivoLocalizacaoOffice = new File(Configuracoes.DIRETORIO_PERMANENTE_ARQUIVOS + Configuracoes.PASTA_LOCALIZACAO_OFFICE);
        try {
            if (arquivoLocalizacaoOffice.exists()) {
                instalacoes = FileUtils.readFileToString(arquivoLocalizacaoOffice);
            } else {
                obterPastas(new File(Configuracoes.HD_PROCURAR_OFFICE));
                FileUtils.writeStringToFile(arquivoLocalizacaoOffice, instalacoes);
            }
        } catch (Exception e) {
            logger.fatal("Erro durante leitura/gravação de arquivo de localização do Office.");
        }
        return instalacoes;
    }

    private static void obterPastas(File root) {
        File arquivo = new File(root.getAbsolutePath() + "\\" + Configuracoes.APLICATIVO_WORD);
        if (arquivo.exists() && instalacoes.isEmpty()) {
            instalacoes += arquivo.getAbsolutePath();
        }
        File[] list = root.listFiles();
        if (list == null) {
            return;
        }
        for (File f : list) {
            if (f.isDirectory()
                    && !contem(f, System.getenv("SystemRoot"))
                    && !contem(f, System.getProperty("user.home"))) {
                obterPastas(f);
            }
        }
    }

    private static boolean contem(File arquivo, String texto) {
        return arquivo.getAbsolutePath().toLowerCase().contains(texto.toLowerCase());
    }

    private static void localizarECarregarWord(JTextArea jat) {
        obterLocalizacoesWord();
        String nomeArquivo = UUID.randomUUID().toString() + Configuracoes.EXTENSAO_EXPORTACAO_TEXTO;
        boolean travado = true;
        while (travado) {
            try {
                File arquivoTexto = new File(Configuracoes.DIRETORIO_TEMPORARIO_ARQUIVOS + nomeArquivo);
                if (jat.isEditable()) {
                    jat.setEditable(false);
                    FileUtils.writeStringToFile(arquivoTexto, jat.getText());
                    new ProcessBuilder(instalacoes, arquivoTexto.getAbsolutePath()).start();
                }
                Thread.sleep(2000);
                FileChannel channel = new RandomAccessFile(arquivoTexto, "rw").getChannel();
                FileLock fileLock = channel.lock();
                jat.setEditable(true);
                travado = false;
                fileLock.close();
                jat.setText(FileUtils.readFileToString(arquivoTexto));
                jat.requestFocusInWindow();
            } catch (Exception e) {
                travado = true;
            }
        }
    }
    
    public static void abrirWord(KeyEvent evt, JTextArea jat) {
        if (evt.isControlDown() && evt.getKeyCode() == KeyEvent.VK_G) {
            WordUtils.localizarECarregarWord(jat);
        }
    }
}

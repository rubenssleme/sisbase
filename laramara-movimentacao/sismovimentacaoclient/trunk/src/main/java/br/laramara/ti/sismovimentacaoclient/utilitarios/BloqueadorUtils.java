package br.laramara.ti.sismovimentacaoclient.utilitarios;

import java.io.File;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;

public class BloqueadorUtils {

    public static final String CAMINHO = "/";
    public static final long TEMPO_MAXIMO = 1000;

    public static boolean liberado() {
        if (obterTempo() < TEMPO_MAXIMO) {
            return true;
        } else {
            return false;
        }
    }

    private static long obterTempo() {
        try {
            long tempoInicio = System.currentTimeMillis();
            File arquivo = new File("cadeado.obj");
            FileChannel channel = new RandomAccessFile(arquivo, "rw").getChannel();
            channel.lock();
            long tempoTermino = System.currentTimeMillis();
            return tempoTermino - tempoInicio;
        } catch (Exception e) {
            return Long.MAX_VALUE;
        }
    }
}

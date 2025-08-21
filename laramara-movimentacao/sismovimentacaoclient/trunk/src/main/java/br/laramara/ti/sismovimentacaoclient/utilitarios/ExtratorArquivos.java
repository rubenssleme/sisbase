package br.laramara.ti.sismovimentacaoclient.utilitarios;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;

public class ExtratorArquivos {

    private static final Logger logger = Logger.getLogger(ExtratorArquivos.class);

    public void extrairArquivosSom() {
        try {
            String caminhoDoJar = getClass().getProtectionDomain().getCodeSource().getLocation().getPath();
            logger.info("Jar com arquivos localizado em " + caminhoDoJar);
            JarFile arquivoJar = new JarFile(caminhoDoJar);
            Enumeration<JarEntry> entries = arquivoJar.entries();
            while (entries.hasMoreElements()) {
                JarEntry arquivo = entries.nextElement();
                String extensao = arquivo.getName().substring(
                        arquivo.getName().lastIndexOf(".") + 1);
                if (extensao.equals("wav")) {
                    String nomeArquivo = arquivo.getName();
                    FileUtils.copyInputStreamToFile(arquivoJar.getInputStream(arquivo), new File(SomUtils.CAMINHO_SOM
                            + File.separator + nomeArquivo));
                    logger.info("Arquivo " + arquivo.getName()
                            + " extraido com sucesso no diretorio "
                            + SomUtils.CAMINHO_SOM);
                }
            }
        } catch (FileNotFoundException c) {
            logger.warn("Arquivo Jar da aplicação não foi localidado.");
        } catch (Exception e) {
            logger.error("Erro durante extração de arquivos de relatório. \nDetalhes: "
                    + e);
        }
    }
}

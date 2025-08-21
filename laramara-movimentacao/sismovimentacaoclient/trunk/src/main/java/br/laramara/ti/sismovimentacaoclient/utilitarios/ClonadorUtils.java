package br.laramara.ti.sismovimentacaoclient.utilitarios;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import org.apache.log4j.Logger;

public class ClonadorUtils {

    protected static final Logger logger = Logger.getLogger(ClonadorUtils.class);

    public static Object copiar(Object orig) {
        Object obj = null;
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream out = new ObjectOutputStream(bos);
            out.writeObject(orig);
            out.flush();
            out.close();

            ObjectInputStream in = new ObjectInputStream(
                    new ByteArrayInputStream(bos.toByteArray()));
            obj = in.readObject();
            in.close();
        } catch (Exception e) {
            logger.fatal("Erro durante clonagem de DTO.");
        }
        return obj;
    }
}

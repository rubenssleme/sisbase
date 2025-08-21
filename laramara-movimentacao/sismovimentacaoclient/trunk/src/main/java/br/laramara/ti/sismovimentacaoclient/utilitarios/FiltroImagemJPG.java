package br.laramara.ti.sismovimentacaoclient.utilitarios;

import java.io.File;
import javax.swing.filechooser.FileFilter;


public class FiltroImagemJPG extends FileFilter {

    @Override
    public boolean accept(File f) {
        return f.getName().toLowerCase().endsWith(".jpg")
                || f.isDirectory();
    }

    @Override
    public String getDescription() {
        return "";
    }
}

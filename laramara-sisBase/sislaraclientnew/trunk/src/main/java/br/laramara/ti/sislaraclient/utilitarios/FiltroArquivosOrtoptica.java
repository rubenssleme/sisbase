package br.laramara.ti.sislaraclient.utilitarios;

import java.io.File;
import javax.swing.filechooser.FileFilter;

public class FiltroArquivosOrtoptica extends FileFilter {

    private final String prontuario;
    
    public FiltroArquivosOrtoptica(String prontuario){
        this.prontuario = prontuario;
    }
    
    @Override
    public boolean accept(File f) {
        return ((f.getName().toLowerCase().contains("cat") 
                || f.getName().toLowerCase().contains("tr") 
                || f.getName().toLowerCase().contains("to"))&&
                f.getName().toLowerCase().contains(prontuario.trim()))
                || f.isDirectory();
    }

    @Override
    public String getDescription() {
        return "Relatório(s) da ortóptica";
    }
}

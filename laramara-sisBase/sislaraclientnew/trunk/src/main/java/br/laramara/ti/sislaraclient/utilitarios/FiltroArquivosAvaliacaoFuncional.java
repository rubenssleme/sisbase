package br.laramara.ti.sislaraclient.utilitarios;

import java.io.File;
import javax.swing.filechooser.FileFilter;

public class FiltroArquivosAvaliacaoFuncional extends FileFilter {

    private final String prontuario;
    
    public FiltroArquivosAvaliacaoFuncional(String prontuario){
        this.prontuario = prontuario;
    }
    
    @Override
    public boolean accept(File f) {
        return (f.getName().toLowerCase().contains("avfun")&& f.getName().toLowerCase().contains(prontuario.trim()))
                || f.isDirectory();
    }

    @Override
    public String getDescription() {
        return "Relatório(s) da avaliação funcional";
    }
}

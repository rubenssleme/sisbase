package br.laramara.ti.sismovimentacaoclient.telas;

import br.laramara.ti.sismovimentacaoclient.utilitarios.BloqueadorUtils;
import br.laramara.ti.sismovimentacaoclient.utilitarios.ExtratorArquivos;
import java.net.ProxySelector;
import org.jdesktop.application.Application;
import org.jdesktop.application.SingleFrameApplication;

public class SisMovimentacaoClient extends SingleFrameApplication {

    @Override protected void startup() {
        show(new TelaPrincipal(this));
    }

    @Override protected void configureWindow(java.awt.Window root) {
    }

    public static SisMovimentacaoClient getApplication() {
        return Application.getInstance(SisMovimentacaoClient.class);
    }

    public static void main(String[] args) {
        if (BloqueadorUtils.liberado()) {
            //Remove configurações do proxy da JVM. 
            ProxySelector.setDefault(null);
            new ExtratorArquivos().extrairArquivosSom();
            launch(SisMovimentacaoClient.class, args);
        }
    }
}

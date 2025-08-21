package br.laramara.ti.sislaraclient.telas;

import br.laramara.ti.sislaraclient.controladores.ThreadFechamentoAutomatico;
import br.laramara.ti.sislaraclient.utilitarios.BloqueadorUtils;
import br.laramara.ti.sislaraclient.utilitarios.ExtratorArquivos;
import br.laramara.ti.sislaraclient.controladores.ThreadTelaAviso;
import java.net.ProxySelector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.jdesktop.application.Application;
import org.jdesktop.application.SingleFrameApplication;

public class SisLaraClient extends SingleFrameApplication {

    @Override
    protected void startup() {
        TelaPrincipal telaPrincipal = new TelaPrincipal(this);
        show(telaPrincipal);
        
        ExecutorService executor = Executors.newFixedThreadPool(5);
        executor.submit(new ThreadTelaAviso(telaPrincipal));
        executor.submit(new ThreadFechamentoAutomatico(telaPrincipal));
    }

    @Override
    protected void configureWindow(java.awt.Window root) {
    }

    public static SisLaraClient getApplication() {
        return Application.getInstance(SisLaraClient.class);
    }

    public static void main(String[] args) {
        if (BloqueadorUtils.liberado()) {
            //Remove configurações do proxy da JVM. 
            ProxySelector.setDefault(null);
            new ExtratorArquivos().extrairArquivosSom();
            launch(SisLaraClient.class, args);
        }
    }
}

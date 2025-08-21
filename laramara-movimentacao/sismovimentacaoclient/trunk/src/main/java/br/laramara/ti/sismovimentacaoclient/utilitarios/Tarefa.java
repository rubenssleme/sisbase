package br.laramara.ti.sismovimentacaoclient.utilitarios;

import java.util.concurrent.Callable;

public class Tarefa implements Callable<Void> {

    private Runnable acao = null;
    
    public Tarefa(Runnable acao) {
        this.acao = acao;
    }

    public Void call() throws Exception {
        acao.run();
        return null;
    }
}
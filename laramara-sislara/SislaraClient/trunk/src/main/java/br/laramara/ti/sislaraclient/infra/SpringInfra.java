package br.laramara.ti.sislaraclient.infra;

import org.apache.log4j.Logger;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringInfra {
    private static final Logger logger = Logger.getLogger(SpringInfra.class);

    private static ClassPathXmlApplicationContext contexto;
    private static boolean carregado;

    public static ClassPathXmlApplicationContext obterInstancia(){
        if (!carregado){
             try{
                contexto = new ClassPathXmlApplicationContext("applicationContext.xml");
                carregado = true;
             }catch(Exception e){
                logger.fatal("Erro durante inicialização da aplicação. Detalhes: " + e);
             }
        }
        return contexto;
    }
}

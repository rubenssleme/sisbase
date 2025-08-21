public class Cliente {

    public static void main(String[] args) {

        /**
         *  criação de uma mensagem Expecifica usando padrão Factory variação:
         *  Padrão Factory Method
         */
        Mensagem aviso = MaquinaDeMensagens.exibeMensagem("aviso");
        aviso.Mensagem();

        Mensagem erro = MaquinaDeMensagens.exibeMensagem("erro");
        erro.Mensagem();



    }


}

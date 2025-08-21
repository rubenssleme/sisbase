public abstract class MaquinaDeMensagens {
    public abstract Mensagem entregaMensagem();

    public static Mensagem exibeMensagem(String criterio) {

        if ( criterio.equals("aviso") )
            return new MensagemAviso();
        else if ( criterio.equals("erro") )
            return new MensagemErro();
        return null;
    }
}

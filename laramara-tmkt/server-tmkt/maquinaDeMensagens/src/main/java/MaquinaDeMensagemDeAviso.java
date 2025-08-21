public class MaquinaDeMensagemDeAviso extends MaquinaDeMensagens {

    public Mensagem entregaMensagem() {
        return new MensagemAviso();
    }
}

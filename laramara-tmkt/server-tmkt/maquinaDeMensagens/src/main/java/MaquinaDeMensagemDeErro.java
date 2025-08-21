public class MaquinaDeMensagemDeErro extends MaquinaDeMensagens {

    public Mensagem entregaMensagem() {
        return new MensagemErro();
    }

}

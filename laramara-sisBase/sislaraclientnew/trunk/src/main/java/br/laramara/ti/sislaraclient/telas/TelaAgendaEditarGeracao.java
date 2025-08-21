
package br.laramara.ti.sislaraclient.telas;

import br.laramara.ti.sislaraclient.controladores.ControladorTelaAgendaEditarGeracao;
import br.laramara.ti.sislaraclient.infra.PermissaoDeTelas;
import javax.swing.JDialog;

public class TelaAgendaEditarGeracao extends TelaAgendaEditar {
 
    public TelaAgendaEditarGeracao(JDialog tela){
        super(tela, false);
        controlador = new ControladorTelaAgendaEditarGeracao(this, jtfNomePreCadastro, jcbProfissional, jliProfissionalAgendados, jcbReservaPara, jcbTipoAtendimento, jcbDescricaoTipoAtendimento, jcbModuloAtividade, jcbSetor, jcbLocalAtendimento, jftDataInicio, jftDataTermino, jcbDiaSemana, jftHoraInicio, jftHoraTermino, jepObservacoes);
        controlador.abrirSomenteSeHouverPermissao(PermissaoDeTelas.AGENDA_TELA_EDICAO_VISUALIZAR);
    }
}

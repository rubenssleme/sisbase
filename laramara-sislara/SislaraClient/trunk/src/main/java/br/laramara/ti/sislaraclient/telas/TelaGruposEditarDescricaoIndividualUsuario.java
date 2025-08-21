
package br.laramara.ti.sislaraclient.telas;

import br.laramara.ti.sislaraclient.controladores.ControladorTelaGruposEditarDescricaoIndividualUsuario;
import br.laramara.ti.sislaracommons.dtos.atendimento.AtendimentoBaseDTO;
import br.laramara.ti.sislaracommons.dtos.atendimento.AtendimentoGrupoDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.GrupoDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.ModuloPeriodoDTO;
import javax.swing.JDialog;

public class TelaGruposEditarDescricaoIndividualUsuario extends TelaGruposEditarDescricaoIndividual{
    
    public TelaGruposEditarDescricaoIndividualUsuario(JDialog parent, GrupoDTO grupoDto, ModuloPeriodoDTO moduloPeriodoDto, AtendimentoGrupoDTO atendimentoDto, AtendimentoBaseDTO atendimentoBaseDto){
        super(parent, "Usuário", true);
        controlador = new ControladorTelaGruposEditarDescricaoIndividualUsuario(this, grupoDto, moduloPeriodoDto, atendimentoDto, atendimentoBaseDto, jtfGrupo, jtfModuloAtividade, jtfData, jtfHoraInicio, jtfHoraTermino, jtfNome, jlaParticipacao, jcbParticipacao, jatDescricaoIndividual, jcbFrequencia, jatJustificativa, jliArquivos);
        controlador.abrirTela();
    }
}

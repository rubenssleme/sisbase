package br.laramara.ti.sislaraclient.telas;

import br.laramara.ti.sislaraclient.controladores.ControladorTelaGruposEditarDescricaoIndividualComunidade;
import br.laramara.ti.sislaracommons.dtos.atendimento.AtendimentoBaseDTO;
import br.laramara.ti.sislaracommons.dtos.atendimento.AtendimentoGrupoDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.GrupoDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.ModuloPeriodoDTO;
import javax.swing.JDialog;

public class TelaGruposEditarDescricaoIndividualComunidade extends TelaGruposEditarDescricaoIndividual{
    
    public TelaGruposEditarDescricaoIndividualComunidade(JDialog parent, GrupoDTO grupoDto, ModuloPeriodoDTO moduloPeriodoDto, AtendimentoGrupoDTO atendimentoDto, AtendimentoBaseDTO atendimentoBaseDto){
        super(parent, "Comunidade", false);
        controlador = new ControladorTelaGruposEditarDescricaoIndividualComunidade(this, grupoDto, moduloPeriodoDto, atendimentoDto, atendimentoBaseDto, jtfGrupo, jtfModuloAtividade, jtfData, jtfHoraInicio, jtfHoraTermino, jtfNome, jlaParticipacao, jcbParticipacao, jatDescricaoIndividual, jcbFrequencia, jatJustificativa, jliArquivos);
        controlador.abrirTela();
    }
}

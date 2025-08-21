package br.laramara.ti.sislaraclient.telas;

import br.laramara.ti.sislaraclient.controladores.ControladorTelaGruposEditarDescricaoIndividualProfissional;
import br.laramara.ti.sislaracommons.dtos.atendimento.AtendimentoBaseDTO;
import br.laramara.ti.sislaracommons.dtos.atendimento.AtendimentoGrupoDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.GrupoDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.ModuloPeriodoDTO;
import javax.swing.JDialog;

public class TelaGruposEditarDescricaoIndividualProfissional extends TelaGruposEditarDescricaoIndividual {

    public TelaGruposEditarDescricaoIndividualProfissional(JDialog parent, GrupoDTO grupoDto, ModuloPeriodoDTO moduloPeriodoDto, AtendimentoGrupoDTO atendimentoDto, AtendimentoBaseDTO atendimentoBaseDto) {
        super(parent, "Profissional", false);
        controlador = new ControladorTelaGruposEditarDescricaoIndividualProfissional(this, grupoDto, moduloPeriodoDto, atendimentoDto, atendimentoBaseDto, jtfGrupo, jtfModuloAtividade, jtfData, jtfHoraInicio, jtfHoraTermino, jtfNome, jlaParticipacao, jcbParticipacao, jatDescricaoIndividual, jcbFrequencia, jatJustificativa, jliArquivos);
        controlador.abrirTela();
    }
}
package br.laramara.ti.sislaraclient.modelos;

import br.laramara.ti.sislaracommons.dtos.grupo.ModuloPeriodoDTO;
import br.laramara.ti.sislaracommons.utilitarios.TextoUtils;

public class ModeloTabelaModuloPeriodo extends ModeloTabela<ModuloPeriodoDTO> {

    public ModeloTabelaModuloPeriodo() {
        super(new String[]{"Módulo/Atividade", "Dia da Semana e Horario", "Profissionais", "Local de Atendimento", "Vagas"},
                new Class[]{String.class, String.class, String.class, String.class, String.class, String.class, String.class},
                new int[]{300, 100, 100, 100, 300, 150, 100});
    }

    @Override
    protected void adicionarDado(ModuloPeriodoDTO objetoDto) {
        addRow(new Object[]{objetoDto.getModuloDto(),
                    TextoUtils.removerChaves(objetoDto.getDiasSemanaEHorariosDaAtividadeDto().toString()),
                    TextoUtils.removerChaves(objetoDto.getProfissionaisDto().toString()),
                    objetoDto.getLocalAtendimentoDTO(),
                    objetoDto.getVagas()});
    }
}

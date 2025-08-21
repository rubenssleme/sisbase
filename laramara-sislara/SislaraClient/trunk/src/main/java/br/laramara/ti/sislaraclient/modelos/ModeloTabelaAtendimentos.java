package br.laramara.ti.sislaraclient.modelos;

import br.laramara.ti.sislaracommons.dtos.atendimento.AtendimentoGrupoDTO;
import br.laramara.ti.sislaracommons.dtos.atendimento.StatusAtendimentoDTO;
import br.laramara.ti.sislaracommons.utilitarios.DataHoraUtils;
import java.util.Date;

public class ModeloTabelaAtendimentos extends ModeloTabela<AtendimentoGrupoDTO> {
    
    public ModeloTabelaAtendimentos() {
        super(new String[]{"Data", "Hora de Início","Hora de Término", "Status", "ID"},
                new Class[]{Date.class, String.class, String.class, StatusAtendimentoDTO.class, Long.class},
                new int[]{110, 110, 110, 140, 50});
    }

    @Override
    protected void adicionarDado(AtendimentoGrupoDTO objetoDto) {
        addRow(new Object[]{DataHoraUtils.obterDataOuNulo(objetoDto.getData()),
                    objetoDto.getHorarioDto().getHoraInicio(),
                    objetoDto.getHorarioDto().getHoraTermino(),
                    objetoDto.getStatusAtendimentoDto(),
                    objetoDto.getId()});
    }
}

package br.laramara.ti.sislaraclient.modelos;

import br.laramara.ti.sislaracommons.dtos.grupo.GrupoDTO;
import br.laramara.ti.sislaracommons.utilitarios.DataHoraUtils;
import br.laramara.ti.sislaracommons.utilitarios.TextoUtils;
import java.util.Date;

public class ModeloTabelaGrupo extends ModeloTabela<GrupoDTO> {

    public ModeloTabelaGrupo() {
        super(new String[]{"Nome do Grupo", "Nível", "Data Início", "Data Término", "Descrição", "Tipo de Atendimento", "Descrição do Tipo de Atendimento", "Modulos", "ID"},
                new Class[]{String.class, String.class, Date.class, Date.class, String.class, String.class, String.class, String.class, Long.class},
                new int[]{100, 60, 100, 100, 500, 350, 500, 2000, 50});
    }

    @Override
    protected void adicionarDado(GrupoDTO objetoDto) {
        addRow(new Object[]{objetoDto.getNomeGrupoDto() + "-" + objetoDto.getTurma(),
                    objetoDto.getNivel(),
                    DataHoraUtils.obterDataValidaInvalidaOuNulo(objetoDto.getDataInicio()).getTime(),
                    DataHoraUtils.obterDataValidaInvalidaOuNulo(objetoDto.getDataTermino()).getTime(),
                    objetoDto.getDescricao(),
                    objetoDto.getDescricaoTipoAtendimentoDTO().getTipoAtendimentoDto(),
                    objetoDto.getDescricaoTipoAtendimentoDTO().getNome(),
                    TextoUtils.removerChaves(objetoDto.getModulosPeriodosDto().toString()),
                    objetoDto.getId()});
    }
}

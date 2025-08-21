package br.laramara.ti.sislaraclient.modelos;

import br.laramara.ti.sislaracommons.dtos.ArquivoDisponivelDTO;
import br.laramara.ti.sislaracommons.utilitarios.DataHoraUtils;
import java.util.Date;

public class ModeloTabelaArquivoDisponivel extends ModeloTabela<ArquivoDisponivelDTO> {

    public ModeloTabelaArquivoDisponivel() {
        super(new String[]{"Grupo", "Data de atendimento", "Hora Início", "Hora Término", "Nome Arquivo"},
                new Class[]{String.class, Date.class, String.class, String.class, String.class},
                new int[]{50, 50, 10, 10, 50});
    }

    @Override
    protected void adicionarDado(ArquivoDisponivelDTO objetoDto) {
        addRow(new Object[]{
            objetoDto.getNomeGrupo() != null ? objetoDto.getNomeGrupo() : null,
            objetoDto.getDataAtendimento() != null ? DataHoraUtils.obterDataOuNulo(objetoDto.getDataAtendimento()) : null,
            objetoDto.getHoraInicio(),
            objetoDto.getHoraTermino(),
            objetoDto.getNomeArquivo()
        });
    }
}

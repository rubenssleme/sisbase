package br.laramara.ti.sislaraclient.modelos;

import br.laramara.ti.sislaracommons.dtos.atendimento.AtendimentoProfissionalDTO;

public class ModeloTabelaAtendimentoProfissional extends ModeloTabela<AtendimentoProfissionalDTO> {

    public ModeloTabelaAtendimentoProfissional() {
        super(new String[]{"Nome", "Descrição", "Frequência", "Justificativa/OBS"},
                new Class[]{String.class, String.class, String.class, String.class},
                new int[]{200, 190, 60, 190});
    }

    @Override
    protected void adicionarDado(AtendimentoProfissionalDTO objetoDto) {
        addRow(new Object[]{objetoDto.getProfissionalDto().toString(),
                    objetoDto.getInformacaoAtendimentoDto().getDescricao(),
                    objetoDto.getInformacaoAtendimentoDto().getFrequenciaDto().toString(),
                    objetoDto.getInformacaoAtendimentoDto().getJustificativa()});
    }
}

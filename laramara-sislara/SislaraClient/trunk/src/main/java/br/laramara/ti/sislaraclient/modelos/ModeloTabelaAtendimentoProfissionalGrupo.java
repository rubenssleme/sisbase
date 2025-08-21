package br.laramara.ti.sislaraclient.modelos;

import br.laramara.ti.sislaracommons.dtos.atendimento.AtendimentoProfissionalDTO;


public class ModeloTabelaAtendimentoProfissionalGrupo extends ModeloTabela<AtendimentoProfissionalDTO> {

    public ModeloTabelaAtendimentoProfissionalGrupo() {
        super(new String[]{"Nome", "Descrição", "Frequência", "Justificativa/OBS", "É apenas participante"},
                new Class[]{String.class, String.class, String.class, String.class, Boolean.class},
                new int[]{200, 190, 60, 190, 50});
    }

    @Override
    protected void adicionarDado(AtendimentoProfissionalDTO objetoDto) {
        addRow(new Object[]{objetoDto.getProfissionalDto().toString(),
                    objetoDto.getInformacaoAtendimentoDto().getDescricao(),
                    objetoDto.getInformacaoAtendimentoDto().getFrequenciaDto().toString(),
                    objetoDto.getInformacaoAtendimentoDto().getJustificativa(),
                    objetoDto.isApenasParticipante()});
    }
}

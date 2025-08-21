
package br.laramara.ti.sislaraclient.modelos;

import br.laramara.ti.sislaracommons.dtos.atendimento.AtendimentoComunidadeDTO;

public class ModeloTabelaAtendimentoAcompanhante extends ModeloTabela<AtendimentoComunidadeDTO> {

    public ModeloTabelaAtendimentoAcompanhante() {
        super(new String[]{"Nome", "Formação", "Descrição", "Frequência", "Justificativa"},
                new Class[]{String.class, String.class, String.class, String.class, String.class},
                new int[]{200, 100, 190, 60, 190});
    }

    @Override
    protected void adicionarDado(AtendimentoComunidadeDTO objetoDto) {
        addRow(new Object[]{objetoDto.getPreCadastroDto().getInformacaoEssencialDto().getNome(),
                            objetoDto.getTipoVinculoDto().toString(),
                            objetoDto.getInformacaoAtendimentoDto().getDescricao(),
                            objetoDto.getInformacaoAtendimentoDto().getFrequenciaDto().toString(),
                            objetoDto.getInformacaoAtendimentoDto().getJustificativa()});
    }
}

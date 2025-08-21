package br.laramara.ti.sislaraclient.modelos;

import br.laramara.ti.sislaracommons.dtos.atendimento.AtendimentoUsuarioDTO;

public class ModeloTabelaAtendimentoUsuario extends ModeloTabela<AtendimentoUsuarioDTO> {

    public ModeloTabelaAtendimentoUsuario() {
        super(new String[]{"Prontuario", "Nome", "Frequência", "Descrição Individual", "Participação Detalhada"},
                new Class[]{Long.class, String.class, String.class, String.class, String.class},
                new int[]{70, 200, 60, 190, 100});
    }

    @Override
    protected void adicionarDado(AtendimentoUsuarioDTO objetoDto) {
        addRow(new Object[]{objetoDto.getUsuarioDto().getId(),
                    objetoDto.getUsuarioDto().getInformacaoEssencialDto().getNome(),
                    objetoDto.getInformacaoAtendimentoDto().getFrequenciaDto().toString(),
                    objetoDto.getInformacaoAtendimentoDto().getDescricao(),
                    objetoDto.getInformacaoAtendimentoDto().obterParticipacoesDetalhadas()});
    }
}

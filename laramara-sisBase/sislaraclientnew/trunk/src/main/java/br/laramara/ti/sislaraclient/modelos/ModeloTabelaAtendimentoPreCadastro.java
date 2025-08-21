
package br.laramara.ti.sislaraclient.modelos;

import br.laramara.ti.sislaracommons.dtos.atendimento.AtendimentoPreCadastroDTO;

public class ModeloTabelaAtendimentoPreCadastro extends ModeloTabela<AtendimentoPreCadastroDTO> {

    public ModeloTabelaAtendimentoPreCadastro() {
        super(new String[]{"Nome", "Frequência", "Descrição Individual", "Tipo", "Instituição", "DRE / CEFAI", "Diretoria de Ensino", "Formação", "Nome da Origem"},
                new Class[]{String.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class},
                new int[]{200, 60, 190, 150, 300, 300, 300, 300, 300});
    }

    @Override
    protected void adicionarDado(AtendimentoPreCadastroDTO objetoDto) {
        addRow(new Object[]{objetoDto.getPreCadastroDto().getInformacaoEssencialDto().getNome(),
                    objetoDto.getInformacaoAtendimentoDto().getFrequenciaDto().toString(),
                    objetoDto.getInformacaoAtendimentoDto().getDescricao(),
                    objetoDto.getInstituicaoDto() != null ? objetoDto.getInstituicaoDto().getTipoInstituicaoDto().toString() : null,
                    objetoDto.getInstituicaoDto() != null ? objetoDto.getInstituicaoDto().getNome() : null,
                    objetoDto.getDreCefaiDto() != null ? objetoDto.getDreCefaiDto().toString() : null,
                    objetoDto.getDiretoriaEnsinoDto() != null ? objetoDto.getDiretoriaEnsinoDto().toString() : null,
                    objetoDto.getTipoVinculoDto() != null ? objetoDto.getTipoVinculoDto().toString() : null,
                    objetoDto.getNomeOrigem()});
    }
}

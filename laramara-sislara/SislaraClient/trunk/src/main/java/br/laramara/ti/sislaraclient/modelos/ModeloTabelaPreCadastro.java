package br.laramara.ti.sislaraclient.modelos;

import br.laramara.ti.sislaracommons.dtos.precadastro.PreCadastroDTO;

public class ModeloTabelaPreCadastro extends ModeloTabela<PreCadastroDTO> {

    public ModeloTabelaPreCadastro() {
        super(new String[]{"Nome", "RG", "Telefone(s)"},
                new Class[]{String.class, String.class, String.class},
                new int[]{450, 110, 600});
    }

    @Override
    protected void adicionarDado(PreCadastroDTO objetoDto) {
        addRow(new Object[]{objetoDto.getInformacaoEssencialDto().getNome(),
                    objetoDto.getInformacaoEssencialDto().getRg(),
                    objetoDto.getInformacaoEssencialDto().getContatoDto()});
    }
}

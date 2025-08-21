package br.laramara.ti.sislaraclient.modelos;

import br.laramara.ti.sislaracommons.dtos.instituicao.ClassificacaoInstituicaoDTO;
import br.laramara.ti.sislaracommons.dtos.instituicao.InstituicaoDTO;
import br.laramara.ti.sislaracommons.dtos.instituicao.TipoInstituicaoDTO;

public class ModeloTabelaInstituicoes extends ModeloTabela<InstituicaoDTO> {

    public ModeloTabelaInstituicoes() {
        super(new String[]{"Tipo", "Nome", "Classificação"},
                new Class[]{TipoInstituicaoDTO.class, String.class, ClassificacaoInstituicaoDTO.class},
                new int[]{200, 500, 100});
    }

    @Override
    protected void adicionarDado(InstituicaoDTO objetoDto) {
        addRow(new Object[]{objetoDto.getTipoInstituicaoDto(), 
                            objetoDto.getNome(), 
                            objetoDto.getClassificacaoInstituicaoDto()});
    }
}

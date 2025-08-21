package br.laramara.ti.sislaraclient.modelos;

import br.laramara.ti.sislaracommons.dtos.familiar.FamiliarDTO;
import br.laramara.ti.sislaracommons.dtos.familiar.ParentescoDTO;

public class ModeloTabelaFamiliar extends ModeloTabela<FamiliarDTO> {
    
    public ModeloTabelaFamiliar() {
        super(new String[]{"Principal Respons�vel", "Parentesco","Nome","Telefone(s)","Status", "Renda", "Mora na casa", "Genero", "Profiss�o", "Respons�vel pela avalia��o social", "Acompanhante", "Respons�vel pelo usu�rio", "Paradeiro ignorado"},
                new Class[]{Boolean.class, ParentescoDTO.class, String.class, String.class, String.class, String.class, Boolean.class, String.class, String.class, Boolean.class, Boolean.class, Boolean.class, Boolean.class},
                new int[]{110, 70, 400, 600, 100, 20, 50, 100, 200, 100, 100, 100, 100});
    }

    @Override
    protected void adicionarDado(FamiliarDTO objetoDto) {
        addRow(new Object[]{objetoDto.isPrincipalResponsavel(), 
                            objetoDto.getParentescoDto(), 
                            objetoDto.getInformacaoEssencialDto().getNome(), 
                            objetoDto.getInformacaoEssencialDto().getContatoDto(),
                            objetoDto.getStatusDto(),
                            objetoDto.getRenda(),
                            objetoDto.isMoraNaCasa(),
                            objetoDto.getGeneroDto(),
                            objetoDto.getInformacaoTrabalhoDto().getCargoDto(),
                            objetoDto.isResponsavelPelaAvaliacaoSocial(),
                            objetoDto.isAcompanhante(),
                            objetoDto.isResponsavelPeloUsuario(),
                            objetoDto.isParadeiroIgnorado()});
    }
}

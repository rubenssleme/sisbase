
package br.laramara.ti.sislaraclient.modelos;

import br.laramara.ti.sislaracommons.dtos.usuario.CertidaoDTO;

public class ModeloTabelaCertidao extends ModeloTabela<CertidaoDTO> {
    
    public ModeloTabelaCertidao() {
        super(new String[]{"Tipo de Certidão", "Nº da Certidão", "Livro", "Folha", "UF", "Município", "Distrito"},
                new Class[]{String.class, String.class, String.class, String.class, String.class, String.class, String.class},
                new int[]{110, 10, 10, 10, 4, 100, 100});
    }

    @Override
    protected void adicionarDado(CertidaoDTO objetoDto) {
        addRow(new Object[]{objetoDto.getTipoCertidaoDto(), 
                            objetoDto.getNumero(), 
                            objetoDto.getLivro(), 
                            objetoDto.getFolha(),
                            objetoDto.getUfDto(),
                            objetoDto.getMunicipioDto(),
                            objetoDto.getDistrito()});
    }
}

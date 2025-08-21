
package br.laramara.ti.sismovimentacaoclient.modelos;

import br.laramara.ti.sismovimentacaocommons.dtos.seguranca.PerfilDTO;

public class ModeloTabelaPerfil extends ModeloTabela<PerfilDTO> {

    public ModeloTabelaPerfil() {
        super(new String[]{"Nome"},
                new Class[]{String.class},
                new int[]{200});
    }

    @Override
    protected void adicionarDado(PerfilDTO objetoDto) {
        addRow(new Object[]{objetoDto.toString()});
    }
}

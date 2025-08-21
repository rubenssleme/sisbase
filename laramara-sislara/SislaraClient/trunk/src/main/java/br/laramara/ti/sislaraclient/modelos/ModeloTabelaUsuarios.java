package br.laramara.ti.sislaraclient.modelos;

import br.laramara.ti.sislaracommons.dtos.usuario.StatusDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.UsuarioDTO;

public class ModeloTabelaUsuarios extends ModeloTabela<UsuarioDTO> {

    public ModeloTabelaUsuarios() {
        super(  new String[]{"Prontuário", "Nome do Usuário", "Status", "Telefone(s)"},
                new Class[]{Long.class, String.class, StatusDTO.class, String.class},
                new int[]{100, 500, 100, 600});
    }

    @Override
    protected void adicionarDado(UsuarioDTO usuarioDto){
        addRow(new Object[]{usuarioDto.getId(), 
                            usuarioDto.getInformacaoEssencialDto().getNome(), 
                            usuarioDto.getStatusUsuarioAtualDto(), 
                            usuarioDto.getInformacaoEssencialDto().getContatoDto()});
    };
}

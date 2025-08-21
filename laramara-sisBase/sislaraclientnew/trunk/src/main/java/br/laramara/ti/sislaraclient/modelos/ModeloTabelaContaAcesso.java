package br.laramara.ti.sislaraclient.modelos;

import br.laramara.ti.sislaracommons.dtos.seguranca.ContaAcessoDTO;


public class ModeloTabelaContaAcesso extends ModeloTabela<ContaAcessoDTO> {
    
    public ModeloTabelaContaAcesso() {
        super(new String[]{"Login", "Perfil", "Profissional", "Bloqueado"},
                new Class[]{String.class, String.class, String.class, Boolean.class},
                new int[]{200, 280, 400, 100});
    }

    @Override
    protected void adicionarDado(ContaAcessoDTO objetoDto) {
        addRow(new Object[]{
            objetoDto.getLogin(), 
            objetoDto.getPerfilDto().toString(),
            objetoDto.getProfissionalDto() != null ? objetoDto.getProfissionalDto() : null,
            objetoDto.isBloqueado()});
    }
}

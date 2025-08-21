package br.laramara.ti.sislaraclient.modelos;

import br.laramara.ti.sislaracommons.dtos.grupo.ProfissionalVinculoDTO;

public class ModeloTabelaProfissionalVinculo extends ModeloTabela<ProfissionalVinculoDTO> {
    
    public ModeloTabelaProfissionalVinculo (){
        super(new String[]{"Profissional", "É apenas participante"},
                new Class[] {String.class, Boolean.class},
                new int[]{650, 140});
    }
                
    @Override
    protected void adicionarDado(ProfissionalVinculoDTO objetoDto) {
        addRow(new Object[]{
                    objetoDto.getProfissionalDto().toString(),
                    objetoDto.isParticipante()});
    }
}

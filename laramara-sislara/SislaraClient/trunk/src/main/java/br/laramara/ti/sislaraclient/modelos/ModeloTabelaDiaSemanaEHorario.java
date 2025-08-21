package br.laramara.ti.sislaraclient.modelos;

import br.laramara.ti.sislaracommons.dtos.grupo.DiaSemanaEHorarioDTO;

public class ModeloTabelaDiaSemanaEHorario extends ModeloTabela<DiaSemanaEHorarioDTO> {
    
    public ModeloTabelaDiaSemanaEHorario (){
        super(new String[]{"Dia da semana", "Hora de início", "Hora de término"},
                new Class[] {String.class, String.class, String.class},
                new int[]{50, 50, 50});
    }
                
    @Override
    protected void adicionarDado(DiaSemanaEHorarioDTO objetoDto) {
        addRow(new Object[]{
                    objetoDto.getDiaSemanaDto(),
                    objetoDto.getHorarioDto().getHoraInicio(),
                    objetoDto.getHorarioDto().getHoraTermino()});
    }
    
}

package br.laramara.ti.sislaraclient.modelos;

import br.laramara.ti.sislaracommons.dtos.trabalho.InformacaoTrabalhoCompletaDTO;

public class ModeloTabelaTrabalho extends ModeloTabela<InformacaoTrabalhoCompletaDTO> {
    
    public ModeloTabelaTrabalho() {
        super(new String[]{"Data Início", "Data Témino", "Cargo", "Local de Trabalho", "Volutário", "Encaminhado pela Laramara", "Estagiario", "OBS"},
                new Class[]{String.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class},
                new int[]{100, 100, 200, 300, 50, 50, 20, 50});
    }

    @Override
    protected void adicionarDado(InformacaoTrabalhoCompletaDTO objetoDto) {
        addRow(new Object[]{objetoDto.getDataInicialVigencia(),
                            objetoDto.getDataFinalVigencia(), 
                            objetoDto.getCargoDto(), 
                            objetoDto.getLocalTrabalhoDto(), 
                            objetoDto.getVoluntarioDto(), 
                            objetoDto.getEncaminhadoPorLaramaraDto(),
                            objetoDto.getEstagiarioDto(),
                            objetoDto.getObs()
        });
    }
}

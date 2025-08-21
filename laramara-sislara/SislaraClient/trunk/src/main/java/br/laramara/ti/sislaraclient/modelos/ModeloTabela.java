package br.laramara.ti.sislaraclient.modelos;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;

public abstract class ModeloTabela<T> extends DefaultTableModel {

    private Class[] tiposColunas;
    private int[] tamanhosColunas;
    protected List<T> objetosDto;

    public ModeloTabela(String[] tituloColunas, Class[] tiposColunas, int[] tamanhosColunas) {
        setColumnIdentifiers(tituloColunas);
        this.tiposColunas = tiposColunas;
        this.tamanhosColunas = tamanhosColunas;
        objetosDto = new ArrayList<>();
    }

    public int obterTamanhoPreferido(int coluna) {
        return tamanhosColunas[coluna];
    }

    @Override
    public Class<?> getColumnClass(int coluna) {
        return tiposColunas[coluna];
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }
    
    public T obterObjetoDto(int index) {
        return objetosDto.get(index);
    }
    
    public void atualizarObjetoDto(T objetoDto) {
        int indice = objetosDto.indexOf(objetoDto);
        objetosDto.set(indice, objetoDto);
        adicionarDTOS(objetosDto);
    }

    public void removerDTO(int indice){
        objetosDto.remove(indice);
        adicionarDTOS(objetosDto);
    }
    
    public void incluirObjetoDto(int indice, T objetoDto){
        objetosDto.set(indice, objetoDto);
        adicionarDTOS(objetosDto);
    }
    
    public void limparDados() {
        setRowCount(0);
    }
    
    public void adicionarDTOS(List<T> objetosDto) {
        limparDados();
        if (objetosDto != null && !objetosDto.isEmpty()) {
            this.objetosDto = objetosDto;

            for (T objetoDto : objetosDto) {
                adicionarDado(objetoDto);
            }
        }
    }
    
    public void adicionarDTO(T objetoDto){
        this.objetosDto.add(objetoDto);
        adicionarDado(objetoDto);
    }
    
    public List<T> obterDTOS(){
        return this.objetosDto;
    }
    
    public boolean existe(T objeto){
        return objetosDto.contains(objeto);
    }
    
    protected abstract void adicionarDado(T objetoDto);
}

package br.laramara.ti.sismovimentacaoclient.controladores;

import br.laramara.ti.sismovimentacaoclient.infra.PermissaoDeTelas;
import br.laramara.ti.sismovimentacaoclient.infra.Registro;
import br.laramara.ti.sismovimentacaoclient.modelos.ModeloTabela;
import br.laramara.ti.sismovimentacaoclient.utilitarios.Configuracoes;
import br.laramara.ti.sismovimentacaoclient.utilitarios.JOptionPanePersonalizado;
import br.laramara.ti.sismovimentacaoclient.utilitarios.SomUtils;
import br.laramara.ti.sismovimentacaocommons.dtos.ModeloDTO;
import br.laramara.ti.sismovimentacaocommons.dtos.ResultadoListagemDTO;
import br.laramara.ti.sismovimentacaocommons.dtos.seguranca.PermissaoDTO;
import br.laramara.ti.sismovimentacaocommons.servicos.ServicoSisMovimentacaoServer;
import br.laramara.ti.sismovimentacaocommons.utilitarios.Sessao;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import java.awt.Component;
import java.awt.Desktop;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.KeyEvent;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListModel;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;

abstract class ControladorTela {

    protected static Logger logger;
    
    private static final int TAMANHO_ITENS_COMBO = 18; 
    private static final String ITEM_EM_BRANCO = " ";
    protected ServicoSisMovimentacaoServer servicoSisMovimentacaoServer;
    protected Window telaPai;

    protected ControladorTela(Window telaPai) {
        this.telaPai = telaPai;
        this.logger = Logger.getLogger(getClass());
        try {
            servicoSisMovimentacaoServer = Registro.obterServicoSisMovimentacaoServer();
        } catch (Exception e) {
            logger.fatal(JOptionPanePersonalizado.IMPOSSIVEL_REALIZAR_CONEXAO + "Detalhes: " + e);
            JOptionPanePersonalizado.mostrarTelaErro(telaPai, JOptionPanePersonalizado.ERRO, JOptionPanePersonalizado.IMPOSSIVEL_REALIZAR_CONEXAO);
            System.exit(0);
        }
    }

    public void abrirSomenteSeHouverPermissao(PermissaoDeTelas permissaoExigida) {
        try {
            if (servicoSisMovimentacaoServer.possuiAutorizacao(Sessao.obterInstancia().obterToken(), permissaoExigida.toString())) {
                abrirTela();
            } else {
                JOptionPanePersonalizado.mostrarTelaErro(telaPai, JOptionPanePersonalizado.ACESSO_NEGADO, JOptionPanePersonalizado.NAO_POSSUI_ACESSO_A_AREA);
            }
        } catch (Exception ex) {
            logger.fatal(JOptionPanePersonalizado.ERRO_NA_ABERTURA_DE_TELA + ex);
        }
    }
   
    public void abrirTela(){
        telaPai.setVisible(true);
    }
    
    public void fecharTela(){
        telaPai.dispose();
    }

    protected void removerArquivosTemporarios(){
        FileUtils.deleteQuietly(new File(Configuracoes.DIRETORIO_TEMPORARIO_ARQUIVOS));
    }
    
    protected ModeloDTO obterDtoSelecionadoObrigatorio(JComboBox comboBox){
        return (ModeloDTO)comboBox.getSelectedItem();
    }
    
    protected ModeloDTO obterDtoSelecionado(JComboBox comboBox){
        return estaComItemValidoSelecionado(comboBox) ? (ModeloDTO)comboBox.getSelectedItem() : null;
    }
    
    protected boolean estaComItemValidoSelecionado(JComboBox jComboBox){
        return jComboBox.getSelectedIndex() != 0;
    }
    
    protected boolean estaComTextoValido(JTextField jTextField){
        return jTextField.getText() != "";
    }
    
    protected boolean estaComItemValidoSelecionado(JList jList){
        return jList.getSelectedIndex() > -1;
    }
        
    protected boolean estaComItemValidoSelecionado(JTable jTable){
        return jTable.getSelectedRowCount() != 0;
    }
        
    protected void abrirArquivo(File arquivo) throws IOException {
        Desktop.getDesktop().open(arquivo);
    }
        
    protected void verificarAba(int posicao, PermissaoDeTelas permissao, List<PermissaoDTO> permissoes, JTabbedPane jtp) {
        PermissaoDTO permissaoDto = new PermissaoDTO(permissao.toString());
        if (!permissoes.contains(permissaoDto)) {
            jtp.setEnabledAt(posicao, false);
        }
    }
    
    protected void adicionarNaListaDtoSelecionadoPeloCombo(JList lista, JComboBox combo) {
        if (estaComItemValidoSelecionado(combo)) {
            DefaultListModel modeloLista = ((DefaultListModel) lista.getModel());
            ModeloDTO modeloDto = (ModeloDTO) combo.getSelectedItem();
            if (!modeloLista.contains(modeloDto)) {
                modeloLista.addElement(modeloDto);
                deselecionarDto(combo);
            }
        }
    }
    
    protected void removerDtos(JComboBox jcb) {
        DefaultComboBoxModel modeloComboBox = ((DefaultComboBoxModel) jcb.getModel());

        for (int i = 0; i < modeloComboBox.getSize(); i++) {
            Object modeloDto = modeloComboBox.getElementAt(i);
            if (!(modeloDto instanceof String)) {
                modeloComboBox.removeElementAt(i);
                i--;
            }
        }
    }
    
    private void adicionarNovosDtosERemoveAntigos(DefaultComboBoxModel modeloComboBox, List<? extends ModeloDTO> dtos) {
        int quantidadeDtos = dtos.size();
        //Adiciona novos DTOS ou Atualiza Existentes.
        for (int i = 1; i <= quantidadeDtos; i++) {
            ModeloDTO modeloDto = dtos.get(i - 1);
            int indiceDto = modeloComboBox.getIndexOf(modeloDto);
            if (modeloComboBox.getIndexOf(modeloDto) == -1) {
                modeloComboBox.insertElementAt(modeloDto, i);
            } else if (indiceDto > 0) {
                modeloComboBox.removeElementAt(indiceDto);
                modeloComboBox.insertElementAt(modeloDto, indiceDto);
            }
        }

        //Remove DTOS antigos
        for (int i = 0; i < modeloComboBox.getSize(); i++) {
            Object modeloDto = modeloComboBox.getElementAt(i);
            if (!(modeloDto instanceof String) && !dtos.contains(modeloDto)) {
                modeloComboBox.removeElementAt(i);
                i--;
            }
        }
    }
    
    protected void carregarCombosDependentes(JComboBox jcbBase, JComboBox jcbDependente, List<? extends ModeloDTO> objetosDto) {
        try {
            if (objetosDto != null && preparadoParaResolucaoDependencia(jcbBase, jcbDependente)) {
                adicionarDtos(jcbDependente, objetosDto);
            } else {
                removerDtos(jcbDependente);
            }
        } catch (Exception ex) {
            JOptionPanePersonalizado.mostrarTelaErro(telaPai, JOptionPanePersonalizado.ERRO, JOptionPanePersonalizado.ERRO_NA_RESOLUCAO_DE_CAMPOS_DEPENDENTES);
            logger.error(JOptionPanePersonalizado.ERRO_NA_RESOLUCAO_DE_CAMPOS_DEPENDENTES + ex.getMessage());
        }
    }
    
    private void adicionarNovosDtos(DefaultComboBoxModel modeloComboBox, List<? extends ModeloDTO> dtos){
        for(ModeloDTO modeloDTO : dtos){
            modeloComboBox.addElement(modeloDTO);
        }
    }
    
    protected void adicionarDtos(JComboBox jcb, List<? extends ModeloDTO> dtos) {
        DefaultComboBoxModel modeloComboBox = ((DefaultComboBoxModel) jcb.getModel());
        if (modeloComboBox.getSize() > 0) {
            adicionarNovosDtosERemoveAntigos(modeloComboBox, dtos);
        }else{
            adicionarNovosDtos(modeloComboBox, dtos);
        }
        jcb.setMaximumRowCount(TAMANHO_ITENS_COMBO);
    }
    
    protected void adicionarDtoNoItemSelecionado(JComboBox jcb, ModeloDTO objetoDto) {
        int indice = jcb.getSelectedIndex();
        ((DefaultComboBoxModel) jcb.getModel()).removeElementAt(indice);
        ((DefaultComboBoxModel) jcb.getModel()).insertElementAt(objetoDto, indice);
        jcb.setSelectedIndex(indice);
        jcb.setMaximumRowCount(TAMANHO_ITENS_COMBO);
    }
    
    protected void adicionarTextosESelecionar(JDialog telaPai, String titulo, JComboBox jcb) {
        String textoMaisRecente = JOptionPanePersonalizado.mostrarTelaInclusao(telaPai, titulo);
        if (textoMaisRecente != null && !textoMaisRecente.isEmpty()) {
            List<String> textoAAdicionar = new ArrayList<>();
            textoAAdicionar.add(textoMaisRecente);

            adicionarTextos(jcb, textoAAdicionar);
            selecionarTexto(jcb, textoMaisRecente);
            jcb.requestFocusInWindow();
        }
    }
    
    protected void adicionarTextos(JComboBox jcb, List<String> textos){
        for(String texto : textos){
                System.out.println(texto.toUpperCase());
             ((DefaultComboBoxModel) jcb.getModel()).addElement(texto.toUpperCase());
        }
    }
    
    protected void selecionarTexto(JComboBox jcb, String texto){
        if (texto != null && !texto.isEmpty()){
            jcb.setSelectedItem(texto.trim());
        }else{
            jcb.setSelectedItem("");
        }
    }
    
    protected void adicionarDtoNoItemSelecionado(JTable jtb, ModeloDTO objetoDto){
        int indice = obterIndiceDTOSelecionado(jtb);
        ((ModeloTabela) obterModeloTabela(jtb)).incluirObjetoDto(indice, objetoDto);
    }
    
    private void adicionarDto(JComboBox jcb, ModeloDTO objetoDto){
        jcb.addItem(objetoDto);
    }
    
    protected void adicionarESelecionadDto(JComboBox jcb, ModeloDTO objetoDto){
        adicionarDto(jcb, objetoDto);
        selecionarDto(jcb, objetoDto);
        jcb.setMaximumRowCount(TAMANHO_ITENS_COMBO);
    }
    
    protected void removerDtoSelecionado(JList lista) {
        if (estaComItemValidoSelecionado(lista)) {
            int indiceDoDtoARemover = lista.getSelectedIndex();
            ((DefaultListModel) lista.getModel()).remove(indiceDoDtoARemover);
        }
    }
    
    protected void removerDtoSelecionado(JTable jta){
        if (estaComItemValidoSelecionado(jta)){
            ModeloTabela modeloTabela = (ModeloTabela)jta.getModel();
            modeloTabela.removerDTO(obterIndiceDTOSelecionado(jta));
        }
    }
    
    protected List<? extends ModeloDTO> obterDtos(JList lista){
        return Collections.list((Enumeration<? extends ModeloDTO>) ((DefaultListModel)lista.getModel()).elements());
    }
    
    protected ModeloDTO obterDtoSelecionado(JTable tabela){
        int indiceObjetoDtoSelecionado = obterIndiceDTOSelecionado(tabela);
        return (ModeloDTO) obterModeloTabela(tabela).obterObjetoDto(indiceObjetoDtoSelecionado);
    }
    
    protected ModeloTabela obterModeloTabela(JTable tabela){
        return ((ModeloTabela)tabela.getModel());
    }
    
    protected List<? extends ModeloDTO> obterDtos(JTable tabela){
        return (List<? extends ModeloDTO>)((ModeloTabela)tabela.getModel()).obterDTOS();
    }
    
    protected void adicionarDtos(List<? extends ModeloDTO> objetosDto, JList lista){
        limparLista(lista);
        for(ModeloDTO objetoDto : objetosDto){
            ((DefaultListModel)lista.getModel()).addElement(objetoDto);
        }
    }
    
    protected void adicionarDtos(ModeloDTO objetoDto, JList lista){
        ((DefaultListModel)lista.getModel()).addElement(objetoDto);
    }
    
    protected void adicionarDtosSemRepetir(List<? extends ModeloDTO> objetosDto, JList lista){
        for(ModeloDTO modeloDto : objetosDto){
            adicionarDtoSemRepetir(modeloDto, lista);
        }
    }
    
    protected void adicionarDtoSemRepetir(ModeloDTO objetoDto, JList lista){
        DefaultListModel modeloLista = ((DefaultListModel) lista.getModel()); 
        if (!modeloLista.contains(objetoDto)) {
            adicionarDtos(objetoDto, lista);  
        }
    }
    
    protected void adicionarDtos(List<? extends ModeloDTO> objetosDto, JTable tabela){
        ((ModeloTabela)tabela.getModel()).adicionarDTOS(objetosDto);
    }
    
    protected void adicionarDtos(ModeloDTO objetoDto, JTable tabela){
        ((ModeloTabela)tabela.getModel()).adicionarDTO(objetoDto);
    }
    
    protected void removerDtos(JTable tabela){
        ((ModeloTabela)tabela.getModel()).limparDados();
    }
    
    protected void selecionarDto(JComboBox jcb, ModeloDTO objetoDto){
        if (objetoDto != null){
            jcb.setSelectedItem(objetoDto);
        }else{
            deselecionarDto(jcb);
        }
    }
    
    public void transferirFocoNaTabulacao(KeyEvent evt){
      if ((evt.isShiftDown() == false) && (evt.getKeyCode() == KeyEvent.VK_TAB)) {
            ((Component) evt.getSource()).transferFocus();
            evt.consume();
        } else if ((evt.isShiftDown() == true) && (evt.getKeyCode() == KeyEvent.VK_TAB)) {
            ((Component) evt.getSource()).transferFocusBackward();
            evt.consume();
        } 
    }
    
    protected boolean teclaEnterPressionada(KeyEvent evt) {
        boolean retorno = false;
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            evt.consume();
            retorno = true;
        }
        return retorno;
    }
    
    protected void deselecionarDto(JComboBox jcb){
        jcb.setSelectedIndex(0);
    }
    
    protected boolean estaMarcado(JCheckBox jchBox){
        return jchBox.getSelectedObjects() != null;
    }
    
    protected int obterIndiceDTOSelecionado(JTable jta){
        return jta.convertRowIndexToModel(jta.getSelectedRow());
    }
    
    private int obterIndiceDTOSelecionado(JList jli){
        return jli.getSelectedIndex();
    }
    
    protected int obterIndiceDTOSelecionado(JComboBox jcb){
        return jcb.getSelectedIndex();
    }
    
    protected ModeloDTO obterDtoSelecionado(JList jli){
        ListModel listModel = jli.getModel();
        return (ModeloDTO)listModel.getElementAt(obterIndiceDTOSelecionado(jli));
    }
    
    protected void incluirObjetoDto(ModeloDTO modeloDto, JList jli){
        ((DefaultListModel)jli.getModel()).set(obterIndiceDTOSelecionado(jli), modeloDto);
    }
    
    protected boolean preparadoParaResolucaoDependencia(JComboBox jcbBase, JComboBox jcbDependente){
        ((DefaultComboBoxModel) jcbDependente.getModel()).setSelectedItem(ITEM_EM_BRANCO);
        return estaComItemValidoSelecionado(jcbBase); 
    }
            
    protected void limparTabela(JTable jta){
        ((ModeloTabela)jta.getModel()).limparDados();
    }
    
    protected void limparLista(JList jli){
        ((DefaultListModel)jli.getModel()).removeAllElements();
    }
    
    protected void desabilitar(JComponent jcp){
        jcp.setEnabled(false);
    }
    
    protected void habilitar(JComponent jcp){
        jcp.setEnabled(true);
    }
    
    protected void apresentarResultado(ResultadoListagemDTO resultado, JTable jta) throws InterruptedException {
        jta.setEnabled(true);
        SomUtils.parar();
        Thread.sleep(50);
        if (resultado.sucesso()) {
            adicionarDtos(resultado.getObjetosDto(), jta);
        } else {
            limparTabela(jta);
            JOptionPanePersonalizado.mostrarTelaErro(telaPai, JOptionPanePersonalizado.ERRO, resultado.obterMensagens());
        }
        jta.requestFocusInWindow();
    }
    
    protected void mostrarTelaErroPesquisa(){
        JOptionPanePersonalizado.mostrarTelaErro(telaPai, JOptionPanePersonalizado.ERRO, JOptionPanePersonalizado.ERRO_NA_REALIZACAO_PESQUISA);
    }
    
    protected byte[] obterTela() {
        byte[] datas = null;
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(new Robot().createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize())), "jpg", baos);
            baos.flush();
            String base64String = Base64.encode(baos.toByteArray());
            baos.close();
            datas = Base64.decode(base64String);
        } catch (Exception ex) {
            logger.error("Erro durante efetuação de screenshot da tela. Detalhes: " + ex);
        }
        return datas;
    }
}

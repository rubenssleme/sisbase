package br.laramara.ti.sislaraclient.controladores;

import br.laramara.ti.sislaraclient.infra.PermissaoDeTelas;
import br.laramara.ti.sislaraclient.infra.Registro;
import br.laramara.ti.sislaraclient.modelos.ModeloTabela;
import br.laramara.ti.sislaraclient.utilitarios.Configuracoes;
import br.laramara.ti.sislaraclient.utilitarios.JOptionPanePersonalizado;
import br.laramara.ti.sislaraclient.utilitarios.SomUtils;
import br.laramara.ti.sislaracommons.dtos.ArquivoDTO;
import br.laramara.ti.sislaracommons.dtos.ModeloDTO;
import br.laramara.ti.sislaracommons.dtos.ResultadoListagemDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.GrupoDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.ResultadoListagemGrupoDTO;
import br.laramara.ti.sislaracommons.dtos.seguranca.PermissaoDTO;
import br.laramara.ti.sislaracommons.servicos.ServicoSisLaraServer;
import br.laramara.ti.sislaracommons.utilitarios.DataHoraUtils;
import br.laramara.ti.sislaracommons.utilitarios.Sessao;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import java.awt.Component;
import java.awt.Desktop;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
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
    protected ServicoSisLaraServer servicoSisLaraServer;
    protected Window telaPai;
    private String nomeObjetoABloquearEDesbloquear;

    protected ControladorTela(){
        this.logger = Logger.getLogger(getClass());
        try {
            servicoSisLaraServer = Registro.obterServicoSisLaraServer();
        } catch (Exception e) {
            logger.fatal(JOptionPanePersonalizado.IMPOSSIVEL_REALIZAR_CONEXAO + "Detalhes: " + e);
            JOptionPanePersonalizado.mostrarTelaErro(telaPai, JOptionPanePersonalizado.IMPOSSIVEL_REALIZAR_CONEXAO);
            System.exit(0);
        }
    }
    
    protected ControladorTela(Window telaPai) {
        this();
        this.telaPai = telaPai;
    }
    
    protected boolean estaComColunaSelecionada(JTable jta, String nomeColuna){
        return jta.getColumnName(jta.getSelectedColumn()).equals(nomeColuna);
    }
    
    protected boolean clickouUmaVez(MouseEvent evt){
        return evt.getClickCount() == 1;
    }

    public void abrirSomenteSeHouverPermissao(PermissaoDeTelas permissaoExigida) {
        try {
            if (servicoSisLaraServer.possuiAutorizacao(Sessao.obterInstancia().obterToken(), permissaoExigida.toString())) {
                abrirTela();
            } else {
                JOptionPanePersonalizado.mostrarTelaErro(telaPai, JOptionPanePersonalizado.NAO_POSSUI_ACESSO_A_AREA);
            }
        } catch (Exception ex) {
            logger.fatal(JOptionPanePersonalizado.ERRO_NA_ABERTURA_DE_TELA + ex);
        }
    }
    
    protected ResultadoListagemGrupoDTO obterGrupo(String texto) {
        ResultadoListagemGrupoDTO resultadoListagemGrupoDto = null;
        try {
            resultadoListagemGrupoDto = servicoSisLaraServer.obterListagemGrupoAtivo(texto);
        } catch (Exception ex) {
            JOptionPanePersonalizado.mostrarTelaErro(telaPai, JOptionPanePersonalizado.ERRO_NA_OBTENCAO_DADOS_AUXILIARES);
            logger.error(JOptionPanePersonalizado.ERRO_NA_OBTENCAO_DADOS_AUXILIARES + ex.getMessage());
        }
        return resultadoListagemGrupoDto;
    }
    
    protected GrupoDTO inicializarModuloPeriodo(JComboBox jcbModuloPeriodo, JTextField jtfNomeGrupo) {
        int indiceItemSelecionado = jcbModuloPeriodo.getSelectedIndex();
        GrupoDTO grupoDtoSelecionado = null;
        ResultadoListagemGrupoDTO resultadoListagemGrupoDTO = obterGrupo(jtfNomeGrupo.getText());
        if ((resultadoListagemGrupoDTO.sucesso() && resultadoListagemGrupoDTO.getObjetosDto().size() == 1)) {
            grupoDtoSelecionado = resultadoListagemGrupoDTO.getObjetosDto().get(0);
            adicionarDtos(jcbModuloPeriodo, grupoDtoSelecionado.getModulosPeriodosDto());
            jcbModuloPeriodo.setSelectedIndex(indiceItemSelecionado);
        } else {
            removerDtos(jcbModuloPeriodo);
            if (telaPai.isShowing()){
                JOptionPanePersonalizado.mostrarTelaErro(telaPai, resultadoListagemGrupoDTO.obterMensagens());
            }
            jtfNomeGrupo.requestFocusInWindow();
        }
        return grupoDtoSelecionado;
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
    
    protected boolean estaVazio(JTextField jTextField){
        return jTextField.getText().trim().isEmpty();
    }
    
    protected boolean estaComItemValidoSelecionado(JList jList){
        return jList.getSelectedIndex() > -1;
    }
        
    protected boolean estaComItemValidoSelecionado(JTable jTable){
        return jTable.getSelectedRowCount() != 0;
    }
    
    protected void exibirArquivo(ArquivoDTO resultadoRelatorioDto) {
        try {
            if (resultadoRelatorioDto.sucesso()) {
                abrirArquivo(gravarArquivo(resultadoRelatorioDto));
            } else {
                JOptionPanePersonalizado.mostrarTelaErro(telaPai, resultadoRelatorioDto.obterMensagens());
            }
        } catch (Exception e) {
            logger.fatal("Erro durante abertura do arquivo. Detalhes: " + e);
        }
    }

    protected File gravarArquivo(ArquivoDTO resultadoRelatorioDto){
        return gravarArquivo(resultadoRelatorioDto, true);
    }
    
    protected File gravarArquivoUsandoNomeArquivo(ArquivoDTO resultadoRelatorioDto){
        return gravarArquivo(resultadoRelatorioDto, false);
    }
            
    private File gravarArquivo(ArquivoDTO resultadoRelatorioDto, boolean habilitarNomeTemporario){
        File arquivo = null;
        try {
            String nome;
            if (habilitarNomeTemporario){
                nome  = new SimpleDateFormat("ddMMyyyy_HHmmss.SSS").format(new Date()) + "." + resultadoRelatorioDto.obterExtensao();
            } else {
                nome = resultadoRelatorioDto.getNome();
            }
            arquivo = new File(Configuracoes.DIRETORIO_TEMPORARIO_ARQUIVOS + nome);
            FileUtils.copyInputStreamToFile(new ByteArrayInputStream(resultadoRelatorioDto.obterConteudo()), arquivo);
          } catch (Exception e) {
            logger.fatal("Erro durante gravação do arquivo. Detalhes: " + e);
        }
        return arquivo;
    }
    
    protected void abrirArquivo(File arquivo) throws IOException {
        Desktop.getDesktop().open(arquivo);
    }
        
    protected void verificarAba(String nomeColuna, PermissaoDeTelas permissao, List<PermissaoDTO> permissoes, JTabbedPane jtp) {
        PermissaoDTO permissaoDto = new PermissaoDTO(permissao.toString());
        if (!permissoes.contains(permissaoDto)) {
            jtp.setEnabledAt(jtp.indexOfTab(nomeColuna), false);
        }
    }
    
    protected void adicionarNaListaDtoSelecionadoPeloCombo(JList lista, JComboBox combo) {
        if (estaComItemValidoSelecionado(combo)) {
            ModeloDTO modeloDto = (ModeloDTO) combo.getSelectedItem();
            adicionarNaLista(lista, modeloDto);
            deselecionarDto(combo);
        }
    }
    
    protected void adicionarNaListaDtoSelecionadoPeloComboECheck(JTable tabela, JComboBox combo, JCheckBox checkbox, ModeloDTO modeloDto) {
        if (estaComItemValidoSelecionado(combo) && modeloDto != null) {
            adicionarDtoSemRepetir(modeloDto, tabela);
            deselecionarDto(combo);
            checkbox.setSelected(false);
        }
    }
        
    private void adicionarNaLista(JList lista, ModeloDTO modeloDto) {
        DefaultListModel modeloLista = ((DefaultListModel) lista.getModel());
        if (!modeloLista.contains(modeloDto)) {
            modeloLista.addElement(modeloDto);
        }
    }
    
    protected boolean existe(JList jlist, ModeloDTO modeloDto){
        DefaultListModel modeloLista = ((DefaultListModel) jlist.getModel());
        return modeloLista.contains(modeloDto); 
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
            JOptionPanePersonalizado.mostrarTelaErro(telaPai, JOptionPanePersonalizado.ERRO_NA_RESOLUCAO_DE_CAMPOS_DEPENDENTES);
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
    
    protected void atualizarDtosSelecionados(JTable jtb, List<? extends ModeloDTO> objetosDto){
        for(ModeloDTO objetoDto : objetosDto){
            ((ModeloTabela) obterModeloTabela(jtb)).atualizarObjetoDto(objetoDto);
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
    
    protected void adicionarDtos(List<? extends ModeloDTO> objetosDto, JList lista) {
        if (objetosDto != null) {
            limparLista(lista);
            for (ModeloDTO objetoDto : objetosDto) {
                ((DefaultListModel) lista.getModel()).addElement(objetoDto);
            }
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
    
    protected void adicionarDtoSemRepetir(ModeloDTO objetoDto, JTable tabela) {
        if (!((ModeloTabela) tabela.getModel()).existe(objetoDto)) {
            ((ModeloTabela) tabela.getModel()).adicionarDTO(objetoDto);
        }else{
            JOptionPanePersonalizado.mostrarTelaErro(telaPai, JOptionPanePersonalizado.ERRO_ITEM_EXISTENTE);
        }
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
    
    public void copiarDadosTabelaESalvar(KeyEvent evt, JTable jta) {
        if ((evt.isControlDown() == true) && (evt.getKeyCode() == KeyEvent.VK_A)) {
            salvarTextoArquivoCSV(obterTextoTabelaSemQuebraLinha(jta));
        }
    }
    
    private String obterTextoTabelaSemQuebraLinha(JTable jta) {
        String dados = "";

        int tamanhoColuna = jta.getModel().getColumnCount();
        int tamanhoLinha = jta.getModel().getRowCount();

        for (int i = 0; i < tamanhoColuna; i++) {
            dados += jta.getModel().getColumnName(i) + ";";
        }

        dados += "\n";

        for (int i = 0; i < tamanhoLinha; i++) {
            for (int z = 0; z < tamanhoColuna; z++) {
                Object objeto = jta.getModel().getValueAt(i, z);
                if (objeto instanceof Date) {
                    dados += DataHoraUtils.formatarData((Date) objeto);
                } else if (objeto instanceof String) {
                    dados += ((String) objeto).replaceAll("\\r\\n|\\r|\\n", " ");
                } else {
                    dados += objeto;
                }
                dados += ";";
            }
            dados += "\n";
        }
        return dados;
    }
    
    private void salvarTextoArquivoCSV(String dados) {
        JFileChooser c = new JFileChooser();
        c.setApproveButtonText("Salvar");
        c.setDialogTitle("Exportar resultado");
        int opcao = c.showOpenDialog(c);
        if (opcao == JFileChooser.APPROVE_OPTION){
            File arquivo = c.getSelectedFile();
            String nomeArquivo = arquivo.getAbsolutePath();
            if (!nomeArquivo.endsWith(Configuracoes.EXTENSAO_EXPORTACAO_TABELA)) {
                arquivo = new File(nomeArquivo + Configuracoes.EXTENSAO_EXPORTACAO_TABELA);
            }
            try {
                FileWriter fw = new FileWriter(arquivo);
                BufferedWriter bw = new BufferedWriter(fw);
                bw.append(dados);
                bw.close();
                fw.close();
            } catch (Exception e) {
                logger.error(JOptionPanePersonalizado.ERRO + e.getMessage());
            }
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
        
    protected boolean multiplosItensSelecionados(JTable jta){
        return jta.getSelectedRowCount() > 1;
    }
    
    protected List<? extends ModeloDTO> obterDtosSelecionados(JTable jta) {
        List<ModeloDTO> objetosDtoSelecionados = new ArrayList<>();
        for (int indice : jta.getSelectedRows()) {
            objetosDtoSelecionados.add((ModeloDTO) obterModeloTabela(jta).obterObjetoDto(jta.convertRowIndexToModel(indice)));
        }
        return objetosDtoSelecionados;
    }
    
    protected boolean preparadoParaResolucaoDependencia(JComboBox jcbBase, JComboBox jcbDependente){
        ((DefaultComboBoxModel) jcbDependente.getModel()).setSelectedItem(ITEM_EM_BRANCO);
        return estaComItemValidoSelecionado(jcbBase); 
    }
            
    protected void limparTabela(JTable jta){
        ((ModeloTabela)jta.getModel()).limparDados();
    }
    
    protected void adicionarNaLista(List<String> textos, JList jli){
        for(String texto : textos){
            ((DefaultListModel)jli.getModel()).addElement(texto);
        }
    }
    
    protected void esvaziarLista(JList jli){
        ((DefaultListModel)jli.getModel()).clear();
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
            JOptionPanePersonalizado.mostrarTelaErro(telaPai, resultado.obterMensagens());
        }
        jta.requestFocusInWindow();
    }

    protected void mostrarTelaErroPesquisa(){
        JOptionPanePersonalizado.mostrarTelaErro(telaPai, JOptionPanePersonalizado.ERRO_NA_REALIZACAO_PESQUISA);
    }
    
    protected void exibir(ArquivoDTO arquivoDto) {
        try {
            exibirArquivo(arquivoDto);
        } catch (Exception ex) {
            logger.error("Erro durante abertura de relatório. \nDetalhes:" + ex);
        }
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
    
    protected void bloquearParaEdicao(String nomeObjetoABloquear) {
       nomeObjetoABloquearEDesbloquear = nomeObjetoABloquear;
        try {
            servicoSisLaraServer.bloquearEdicao(nomeObjetoABloquear,  Sessao.obterInstancia().obterToken());
        } catch (Exception ex) {
            JOptionPanePersonalizado.mostrarTelaErro(telaPai, JOptionPanePersonalizado.ERRO_COORDENACAO_BLOQUEIO);
            logger.error(JOptionPanePersonalizado.ERRO_COORDENACAO_BLOQUEIO + ex.getMessage());
        }
    }
    
    public void desbloquearDTOeFecharTela() {
        desbloquearDTO();
        fecharTela();
    }

    public void desbloquearDTO() {
        try {
            if (nomeObjetoABloquearEDesbloquear != null) {
                servicoSisLaraServer.desbloquearEdicao(nomeObjetoABloquearEDesbloquear, Sessao.obterInstancia().obterToken());
            }
        } catch (RemoteException ex) {
            JOptionPanePersonalizado.mostrarTelaErro(telaPai, JOptionPanePersonalizado.ERRO_COORDENACAO_DESBLOQUEIO);
            logger.error(JOptionPanePersonalizado.ERRO_COORDENACAO_DESBLOQUEIO + ex.getMessage());
        }
    }
    
}

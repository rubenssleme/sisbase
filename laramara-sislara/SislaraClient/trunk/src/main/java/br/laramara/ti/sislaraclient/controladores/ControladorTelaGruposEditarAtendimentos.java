
package br.laramara.ti.sislaraclient.controladores;

import static br.laramara.ti.sislaraclient.controladores.ControladorTela.logger;
import br.laramara.ti.sislaraclient.telas.TelaGruposEditarDescricaoIndividualComunidade;
import br.laramara.ti.sislaraclient.telas.TelaGruposEditarDescricaoIndividualProfissional;
import br.laramara.ti.sislaraclient.telas.TelaGruposEditarDescricaoIndividualUsuario;
import br.laramara.ti.sislaraclient.utilitarios.JOptionPanePersonalizado;
import br.laramara.ti.sislaraclient.utilitarios.TabelaUtils;
import br.laramara.ti.sislaracommons.Bloqueavel;
import br.laramara.ti.sislaracommons.dtos.ArquivoDTO;
import br.laramara.ti.sislaracommons.dtos.ResultadoDTO;
import br.laramara.ti.sislaracommons.dtos.atendimento.AtendimentoGrupoDTO;
import br.laramara.ti.sislaracommons.dtos.atendimento.AtendimentoPreCadastroDTO;
import br.laramara.ti.sislaracommons.dtos.atendimento.AtendimentoProfissionalDTO;
import br.laramara.ti.sislaracommons.dtos.atendimento.AtendimentoUsuarioDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.GrupoDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.ModuloPeriodoDTO;
import br.laramara.ti.sislaracommons.utilitarios.Sessao;
import java.awt.event.KeyEvent;
import java.util.List;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ControladorTelaGruposEditarAtendimentos extends ControladorTelaEditar implements TabelaConfiguravel {
    
    private GrupoDTO grupoDto;
    private ModuloPeriodoDTO moduloPeriodoDTO;

    private JTextField jtfGrupo; 
    private JTextField jtfModuloAtividade;
    private JTextField jtfData;
    private JTextField jtfHoraInicio;
    private JTextField jtfHoraTermino;
    private JTextArea jatDescricao;
    private JTextArea jatInterdisciplinar;
    private JTable jtaAtendimentosUsuario;
    private JTable jtaAtendimentosComunidade;
    private JTable jtaAtendimentosProfissinais;
    private JList jliArquivos;
        
    public ControladorTelaGruposEditarAtendimentos(JDialog telaPai, GrupoDTO grupoDto, ModuloPeriodoDTO moduloPeriodoDto, AtendimentoGrupoDTO atendimentoGrupoDto, boolean bloquearGrupo, JTextField jtfGrupo, JTextField jtfModuloAtividade, JTextField jtfData, JTextField jtfHoraInicio, JTextField jtfHoraTermino, JTextArea jatDescricao, JTextArea jatInterdisciplinar, JTable jtaAtendimentosUsuario, JTable jtaAtendimentosComunidade, JTable jtaAtendimentosProfissinais, JList jliArquivos){
        super(telaPai, atendimentoGrupoDto);
        this.grupoDto = grupoDto;
        this.moduloPeriodoDTO = moduloPeriodoDto;
        this.jtfGrupo = jtfGrupo;
        this.jtfModuloAtividade = jtfModuloAtividade;
        this.jtfData = jtfData;
        this.jtfHoraInicio = jtfHoraInicio;
        this.jtfHoraTermino = jtfHoraTermino;
        this.jatDescricao = jatDescricao;
        this.jatInterdisciplinar = jatInterdisciplinar;
        this.jtaAtendimentosUsuario = jtaAtendimentosUsuario;
        this.jtaAtendimentosComunidade = jtaAtendimentosComunidade;
        this.jtaAtendimentosProfissinais = jtaAtendimentosProfissinais;
        this.jliArquivos = jliArquivos;
        bloquearGrupoSeNecessario(bloquearGrupo);
        carregarCampos();
        configurarColunasTabela();
    }

    @Override
    public void configurarColunasTabela(){
        TabelaUtils.configurarTamanhoERenderizadorDeColunas(jtaAtendimentosUsuario);
        TabelaUtils.configurarTamanhoERenderizadorDeColunas(jtaAtendimentosComunidade);
        TabelaUtils.configurarTamanhoERenderizadorDeColunas(jtaAtendimentosProfissinais);
    }
        
    @Override
    protected ResultadoDTO solicitarEdicaoDto() throws Exception {
        return servicoSisLaraServer.editarAtendimentoGrupo(obterObjetoDTO(), Sessao.obterInstancia().obterToken());
    }

    @Override
    protected void prepararDtoParaEditar() {
        AtendimentoGrupoDTO atendimentoAAlterarDto = obterObjetoDTO();
        atendimentoAAlterarDto.setDescricao(jatDescricao.getText());
        atendimentoAAlterarDto.setInterdisciplinar(jatInterdisciplinar.getText());
        atendimentoAAlterarDto.setArquivos((List<ArquivoDTO>)obterDtos(jliArquivos));
    }

    @Override
    protected boolean verificarSeDtoEstaPreenchido() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    protected void executarAcaoAposInclusao() {
    }

    @Override
    public String obterChaveSessao() {
        return Sessao.CHAVE_ATENDIMENTO_GRUPO;
    }

    @Override
    public void inicializarCombosDadosAuxiliares() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void obterDadosAuxiliares() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void carregarCampos() {
        jtfGrupo.setText(grupoDto.toStringApenasNomeETurma());
        jtfModuloAtividade.setText(moduloPeriodoDTO.toString());
        jtfData.setText(obterObjetoDTO().getData());
        jtfHoraInicio.setText(obterObjetoDTO().getHorarioDto().getHoraInicio());
        jtfHoraTermino.setText(obterObjetoDTO().getHorarioDto().getHoraTermino());
        jatDescricao.setText(obterObjetoDTO().getDescricao());
        jatInterdisciplinar.setText(obterObjetoDTO().getInterdisciplinar());
         adicionarDtos(obterObjetoDTO().getArquivos(), jliArquivos);
        
        adicionarDtos(obterObjetoDTO().getAtendimentosUsuariosDto(), jtaAtendimentosUsuario);
        adicionarDtos(obterObjetoDTO().getAtendimentosPreCadastroDto(), jtaAtendimentosComunidade);
        adicionarDtos(obterObjetoDTO().getAtendimentosProfissionaisDto(), jtaAtendimentosProfissinais);
    }
    
    private AtendimentoGrupoDTO obterObjetoDTO(){
        return (AtendimentoGrupoDTO)objetoDto;
    }
    
    public void alterarAtendimentoUsuario() {
        if (estaComItemValidoSelecionado(jtaAtendimentosUsuario)) {
            new TelaGruposEditarDescricaoIndividualUsuario((JDialog) telaPai, grupoDto, moduloPeriodoDTO, (AtendimentoGrupoDTO) obterObjetoDTO(), (AtendimentoUsuarioDTO) obterDtoSelecionado(jtaAtendimentosUsuario));
            String chave = Sessao.CHAVE_ATENDIMENTO_USUARIO;
            if (Sessao.obterInstancia().possuiDto(chave)) {
                adicionarDtoNoItemSelecionado(jtaAtendimentosUsuario, (AtendimentoUsuarioDTO) Sessao.obterInstancia().obterDto(chave));
            }
        }
    }
    

    public void alterarAtendimentoProfissional() {
        if (estaComItemValidoSelecionado(jtaAtendimentosProfissinais)) {
            new TelaGruposEditarDescricaoIndividualProfissional((JDialog) telaPai, grupoDto, moduloPeriodoDTO, (AtendimentoGrupoDTO) obterObjetoDTO(), (AtendimentoProfissionalDTO) obterDtoSelecionado(jtaAtendimentosProfissinais));
            String chave = Sessao.CHAVE_ATENDIMENTO_PROFISSIONAL;
            if (Sessao.obterInstancia().possuiDto(chave)) {
                 adicionarDtoNoItemSelecionado(jtaAtendimentosProfissinais, (AtendimentoProfissionalDTO) Sessao.obterInstancia().obterDto(chave));
            }
        }
    }
    
    public void abrirTelaDeAlteracaoAtendimentoUsuario(KeyEvent evt){
        if (teclaEnterPressionada(evt)){
            alterarAtendimentoUsuario();
        }
    }
    
    public void abrirTelaDeAlteracaoAtendimentoPreCadastro(KeyEvent evt) {
        if (teclaEnterPressionada(evt)) {
            alterarAtendimentoComunidade();
        }
    }

    public void abrirTelaDeAlteracaoAtendimentoProfissional(KeyEvent evt) {
        if (teclaEnterPressionada(evt)){
            alterarAtendimentoProfissional();
        }
    }

    public void alterarAtendimentoComunidade() {
        if (estaComItemValidoSelecionado(jtaAtendimentosComunidade)) {
            new TelaGruposEditarDescricaoIndividualComunidade((JDialog) telaPai, grupoDto, moduloPeriodoDTO, (AtendimentoGrupoDTO) obterObjetoDTO(), (AtendimentoPreCadastroDTO) obterDtoSelecionado(jtaAtendimentosComunidade));
            String chave = Sessao.CHAVE_ATENDIMENTO_COMUNIDADE;
            if (Sessao.obterInstancia().possuiDto(chave)) {
                adicionarDtoNoItemSelecionado(jtaAtendimentosComunidade, (AtendimentoPreCadastroDTO) Sessao.obterInstancia().obterDto(chave));
            }
        }
    }
    
    public void abrirArquivo(){
        ArquivoDTO arquivoSelecionadoDto = (ArquivoDTO) obterDtoSelecionado(jliArquivos);
        try {
            abrirArquivo(jliArquivos, arquivoSelecionadoDto, !arquivoSelecionadoDto.possuiConteudo() ? servicoSisLaraServer.obterArquivoAtendimentoGrupoDTO(obterObjetoDTO(), arquivoSelecionadoDto) : null);
        } catch (Exception e) {
            logger.error(JOptionPanePersonalizado.ERRO_DURANTE_ABERTURA_DE_ARQUIVO + "\n Detalhes: " + e);
            JOptionPanePersonalizado.mostrarTelaErro(telaPai, JOptionPanePersonalizado.ERRO_DURANTE_ABERTURA_DE_ARQUIVO + "\nDetalhes: " + e.getMessage());
        }
    }
    
    private void bloquearGrupoSeNecessario(boolean bloquearGrupo) {
        if (bloquearGrupo) {
            bloquearParaEdicao(((Bloqueavel) grupoDto).obterNome());
        }
    }

    public void adicionarArquivo() {
        adicionarArquivo(jliArquivos);
    }

    public void removerArquivoSelecionado() {
        removerArquivoSelecionado(jliArquivos);
    }
}

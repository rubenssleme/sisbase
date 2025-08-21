package br.laramara.ti.sislaraclient.telas;

import br.laramara.ti.sislaraclient.controladores.ControladorTelaAtendimentoIndividualProfissionalEditar;
import br.laramara.ti.sislaracommons.dtos.atendimento.AtendimentoProfissionalDTO;
import javax.swing.JDialog;

public class TelaAtendimentoIndividualProfissionalEditar extends javax.swing.JDialog {

    /** Creates new form TelaEditarAtendimentoIndividualProfissional */
    public TelaAtendimentoIndividualProfissionalEditar(JDialog parent, AtendimentoProfissionalDTO atendimentoProfissionalDto) {
        super(parent, true);
        initComponents();
        setLocationRelativeTo(parent);
        controlador = new ControladorTelaAtendimentoIndividualProfissionalEditar(this, atendimentoProfissionalDto, jcbProfissional, jatDescricao, jcbFrequencia, jatJustificativa);
        controlador.abrirTela();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        TelaAtendimentosInvidualProfissionalEditar = new javax.swing.JPanel();
        jpaTitulo = new javax.swing.JPanel();
        jlaTitulo = new javax.swing.JLabel();
        jbuFecharTitulo = new javax.swing.JButton();
        jlaProfissional = new javax.swing.JLabel();
        jcbProfissional = new javax.swing.JComboBox();
        jlaDescricao = new javax.swing.JLabel();
        jlaFrequencia = new javax.swing.JLabel();
        jcbFrequencia = new javax.swing.JComboBox();
        jlaJustificativa = new javax.swing.JLabel();
        jbuSalvar = new javax.swing.JButton();
        jbuFechar = new javax.swing.JButton();
        jspDescricao = new javax.swing.JScrollPane();
        jatDescricao = new javax.swing.JTextArea();
        jspJustificativa = new javax.swing.JScrollPane();
        jatJustificativa = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance().getContext().getResourceMap(TelaAtendimentoIndividualProfissionalEditar.class);
        setTitle(resourceMap.getString("Form.title")); // NOI18N
        setName("Form"); // NOI18N
        setUndecorated(true);

        TelaAtendimentosInvidualProfissionalEditar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        TelaAtendimentosInvidualProfissionalEditar.setName("TelaAtendimentosInvidualProfissionalEditar"); // NOI18N
        TelaAtendimentosInvidualProfissionalEditar.setLayout(null);

        jpaTitulo.setBackground(resourceMap.getColor("jpaTitulo.background")); // NOI18N
        jpaTitulo.setName("jpaTitulo"); // NOI18N
        jpaTitulo.setLayout(null);

        jlaTitulo.setFont(resourceMap.getFont("jlaTitulo.font")); // NOI18N
        jlaTitulo.setForeground(resourceMap.getColor("jlaTitulo.foreground")); // NOI18N
        jlaTitulo.setText(resourceMap.getString("jlaTitulo.text")); // NOI18N
        jlaTitulo.setName("jlaTitulo"); // NOI18N
        jpaTitulo.add(jlaTitulo);
        jlaTitulo.setBounds(10, 0, 500, 26);

        jbuFecharTitulo.setBackground(resourceMap.getColor("jbuFecharTitulo.background")); // NOI18N
        jbuFecharTitulo.setIcon(resourceMap.getIcon("jbuFecharTitulo.icon")); // NOI18N
        jbuFecharTitulo.setText(resourceMap.getString("jbuFecharTitulo.text")); // NOI18N
        jbuFecharTitulo.setBorder(null);
        jbuFecharTitulo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jbuFecharTitulo.setFocusable(false);
        jbuFecharTitulo.setName("jbuFecharTitulo"); // NOI18N
        jbuFecharTitulo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbuFecharTituloActionPerformed(evt);
            }
        });
        jpaTitulo.add(jbuFecharTitulo);
        jbuFecharTitulo.setBounds(1002, 0, 25, 25);

        TelaAtendimentosInvidualProfissionalEditar.add(jpaTitulo);
        jpaTitulo.setBounds(1, 1, 1027, 26);

        jlaProfissional.setFont(resourceMap.getFont("jlaProfissional.font")); // NOI18N
        jlaProfissional.setText(resourceMap.getString("jlaProfissional.text")); // NOI18N
        jlaProfissional.setName("jlaProfissional"); // NOI18N
        TelaAtendimentosInvidualProfissionalEditar.add(jlaProfissional);
        jlaProfissional.setBounds(10, 40, 110, 20);

        jcbProfissional.setFont(resourceMap.getFont("jcbProfissional.font")); // NOI18N
        jcbProfissional.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " " }));
        jcbProfissional.setName("jcbProfissional"); // NOI18N
        TelaAtendimentosInvidualProfissionalEditar.add(jcbProfissional);
        jcbProfissional.setBounds(10, 60, 750, 23);

        jlaDescricao.setFont(resourceMap.getFont("jlaDescricao.font")); // NOI18N
        jlaDescricao.setText(resourceMap.getString("jlaDescricao.text")); // NOI18N
        jlaDescricao.setName("jlaDescricao"); // NOI18N
        TelaAtendimentosInvidualProfissionalEditar.add(jlaDescricao);
        jlaDescricao.setBounds(10, 90, 120, 17);

        jlaFrequencia.setFont(resourceMap.getFont("jlaJustificativa.font")); // NOI18N
        jlaFrequencia.setText(resourceMap.getString("jlaFrequencia.text")); // NOI18N
        jlaFrequencia.setName("jlaFrequencia"); // NOI18N
        TelaAtendimentosInvidualProfissionalEditar.add(jlaFrequencia);
        jlaFrequencia.setBounds(10, 320, 81, 17);

        jcbFrequencia.setFont(resourceMap.getFont("jcbFrequencia.font")); // NOI18N
        jcbFrequencia.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " " }));
        jcbFrequencia.setName("jcbFrequencia"); // NOI18N
        TelaAtendimentosInvidualProfissionalEditar.add(jcbFrequencia);
        jcbFrequencia.setBounds(10, 340, 130, 23);
        jcbFrequencia.getAccessibleContext().setAccessibleName(resourceMap.getString("jcbFrequencia.AccessibleContext.accessibleName")); // NOI18N

        jlaJustificativa.setFont(resourceMap.getFont("jlaJustificativa.font")); // NOI18N
        jlaJustificativa.setText(resourceMap.getString("jlaJustificativa.text")); // NOI18N
        jlaJustificativa.setName("jlaJustificativa"); // NOI18N
        TelaAtendimentosInvidualProfissionalEditar.add(jlaJustificativa);
        jlaJustificativa.setBounds(10, 370, 150, 17);

        jbuSalvar.setFont(resourceMap.getFont("jbuSalvar.font")); // NOI18N
        jbuSalvar.setIcon(resourceMap.getIcon("jbuSalvar.icon")); // NOI18N
        jbuSalvar.setMnemonic('S');
        jbuSalvar.setText(resourceMap.getString("jbuSalvar.text")); // NOI18N
        jbuSalvar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jbuSalvar.setName("jbuSalvar"); // NOI18N
        jbuSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbuSalvarActionPerformed(evt);
            }
        });
        TelaAtendimentosInvidualProfissionalEditar.add(jbuSalvar);
        jbuSalvar.setBounds(10, 670, 107, 33);
        jbuSalvar.getAccessibleContext().setAccessibleName(resourceMap.getString("jbuSalvar.AccessibleContext.accessibleName")); // NOI18N

        jbuFechar.setFont(resourceMap.getFont("jbuFechar.font")); // NOI18N
        jbuFechar.setIcon(resourceMap.getIcon("jbuFechar.icon")); // NOI18N
        jbuFechar.setMnemonic('F');
        jbuFechar.setText(resourceMap.getString("jbuFechar.text")); // NOI18N
        jbuFechar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jbuFechar.setName("jbuFechar"); // NOI18N
        jbuFechar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbuFecharActionPerformed(evt);
            }
        });
        TelaAtendimentosInvidualProfissionalEditar.add(jbuFechar);
        jbuFechar.setBounds(900, 670, 120, 30);
        jbuFechar.getAccessibleContext().setAccessibleName(resourceMap.getString("jbuFechar.AccessibleContext.accessibleName")); // NOI18N

        jspDescricao.setName("jspDescricao"); // NOI18N

        jatDescricao.setColumns(20);
        jatDescricao.setFont(resourceMap.getFont("jatDescricao.font")); // NOI18N
        jatDescricao.setLineWrap(true);
        jatDescricao.setRows(5);
        jatDescricao.setWrapStyleWord(true);
        jatDescricao.setName("jatDescricao"); // NOI18N
        jspDescricao.setViewportView(jatDescricao);
        jatDescricao.getAccessibleContext().setAccessibleName(resourceMap.getString("jatDescricao.AccessibleContext.accessibleName")); // NOI18N
        jatDescricao.getAccessibleContext().setAccessibleDescription(resourceMap.getString("jatDescricao.AccessibleContext.accessibleDescription")); // NOI18N

        TelaAtendimentosInvidualProfissionalEditar.add(jspDescricao);
        jspDescricao.setBounds(10, 110, 1010, 210);

        jspJustificativa.setName("jspJustificativa"); // NOI18N

        jatJustificativa.setColumns(20);
        jatJustificativa.setFont(resourceMap.getFont("jatJustificativa.font")); // NOI18N
        jatJustificativa.setLineWrap(true);
        jatJustificativa.setRows(5);
        jatJustificativa.setWrapStyleWord(true);
        jatJustificativa.setName("jatJustificativa"); // NOI18N
        jspJustificativa.setViewportView(jatJustificativa);
        jatJustificativa.getAccessibleContext().setAccessibleName(resourceMap.getString("jatJustificativa.AccessibleContext.accessibleName")); // NOI18N
        jatJustificativa.getAccessibleContext().setAccessibleDescription(resourceMap.getString("jatJustificativa.AccessibleContext.accessibleDescription")); // NOI18N

        TelaAtendimentosInvidualProfissionalEditar.add(jspJustificativa);
        jspJustificativa.setBounds(10, 390, 1010, 270);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(TelaAtendimentosInvidualProfissionalEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 1029, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(TelaAtendimentosInvidualProfissionalEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 715, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbuFecharTituloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbuFecharTituloActionPerformed
        controlador.fecharTela();
    }//GEN-LAST:event_jbuFecharTituloActionPerformed

    private void jbuFecharActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbuFecharActionPerformed
        controlador.fecharTela();
    }//GEN-LAST:event_jbuFecharActionPerformed

    private void jbuSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbuSalvarActionPerformed
        controlador.salvar();
    }//GEN-LAST:event_jbuSalvarActionPerformed

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel TelaAtendimentosInvidualProfissionalEditar;
    private javax.swing.JTextArea jatDescricao;
    private javax.swing.JTextArea jatJustificativa;
    private javax.swing.JButton jbuFechar;
    private javax.swing.JButton jbuFecharTitulo;
    private javax.swing.JButton jbuSalvar;
    protected javax.swing.JComboBox jcbFrequencia;
    protected javax.swing.JComboBox jcbProfissional;
    private javax.swing.JLabel jlaDescricao;
    private javax.swing.JLabel jlaFrequencia;
    private javax.swing.JLabel jlaJustificativa;
    private javax.swing.JLabel jlaProfissional;
    private javax.swing.JLabel jlaTitulo;
    private javax.swing.JPanel jpaTitulo;
    private javax.swing.JScrollPane jspDescricao;
    private javax.swing.JScrollPane jspJustificativa;
    // End of variables declaration//GEN-END:variables
    private ControladorTelaAtendimentoIndividualProfissionalEditar controlador;
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package VIEWS;

import MODELS.CLASS.Consumivel;
import MODELS.CLASS.NaoConsumivel;

/**
 *
 * @author gonca
 */
public class PanelFormularioRecursos extends javax.swing.JPanel {

    private PaginaInicial janelaPrincipal;
    private String top, mensagem, imagem;
    private int idRecursoEditando = -1;
    private boolean ativoAtual = true;
    java.awt.Window win = javax.swing.SwingUtilities.getWindowAncestor(this);
    CONTROLLERS.HomeController controller;
    /**
     * Creates new form PanelFormularioRecursos
     */
    public PanelFormularioRecursos(PaginaInicial paginaInicial) {
        this.janelaPrincipal = paginaInicial;
        this.controller = new CONTROLLERS.HomeController();
        initComponents();
        
        

        // No initComponents ou Construtor, garanta que o combo tem o listener:
        comboTipo.addActionListener(e -> {
            int sel = comboTipo.getSelectedIndex();
            // Consumível
            lblValidade.setVisible(sel == 0);
            txtValidade.setVisible(sel == 0);
            // Não Consumível
            lblPrecoAluguer.setVisible(sel == 1);
            spPrecoAluguer.setVisible(sel == 1);
        });

        lblPrecoAluguer.setVisible(false);
        lblValidade.setVisible(false);
        txtValidade.setVisible(false);
        spPrecoAluguer.setVisible(false);
    }
    
    private void mostrarMensagem(String msg, String titulo, String img) {
        PaginaDialogo dialogo = new PaginaDialogo((java.awt.Frame) win, true);
        dialogo.setMensagem(msg, titulo, img);
        dialogo.setLocationRelativeTo(win);
        dialogo.setVisible(true);
    }
    
    public void prepararFormulario(int tipoAba, Object recurso) {
        comboTipo.setSelectedIndex(tipoAba);

        if (recurso == null) {
            idRecursoEditando = -1;
            ativoAtual = true; // Novo recurso entra sempre como ativo
            limparCampos();
        } else {
            if (recurso instanceof Consumivel) {
                Consumivel c = (Consumivel) recurso;
                idRecursoEditando = c.getIdRecurso();
                ativoAtual = c.isAtivo(); // CARREGA O ESTADO REAL (1)

                txtNome.setText(c.getNome());
                spPreco.setValue(c.getPreco());
                spQuantidade.setValue(c.getQuantidade());

                // Formatar a data para o utilizador ver em PT
                if (c.getDataValidade() != null) {
                    java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd/MM/yyyy");
                    txtValidade.setText(sdf.format(c.getDataValidade()));
                }
            } 
            else if (recurso instanceof NaoConsumivel) {
                NaoConsumivel nc = (NaoConsumivel) recurso;
                idRecursoEditando = nc.getIdRecurso();
                ativoAtual = nc.isAtivo();

                txtNome.setText(nc.getNome());
                spPreco.setValue(nc.getPreco());
                spQuantidade.setValue(nc.getQuantidade());
                spPrecoAluguer.setValue(nc.getPrecoAluguer());
            }
        }
    }

    private void limparCampos() {
        txtNome.setText("");
        spPreco.setValue(0.0);
        spQuantidade.setValue(0);
        txtValidade.setText("");
        spPrecoAluguer.setValue(0.0);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtNome = new javax.swing.JTextField();
        btnGuardar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        txtValidade = new javax.swing.JTextField();
        comboTipo = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        lblPrecoAluguer = new javax.swing.JLabel();
        lblValidade = new javax.swing.JLabel();
        spPrecoAluguer = new javax.swing.JSpinner();
        spPreco = new javax.swing.JSpinner();
        spQuantidade = new javax.swing.JSpinner();

        setBackground(new java.awt.Color(232, 235, 238));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtNome.setText("Nome");
        txtNome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNomeActionPerformed(evt);
            }
        });
        add(txtNome, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 120, 655, 35));

        btnGuardar.setText("Guardar");
        btnGuardar.setPreferredSize(new java.awt.Dimension(81, 23));
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        add(btnGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 480, 150, 40));

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        add(btnCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 480, 150, 40));
        add(txtValidade, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 290, 180, 35));

        comboTipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Consumível", "Não Consumível" }));
        comboTipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboTipoActionPerformed(evt);
            }
        });
        add(comboTipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 290, 190, 35));

        jLabel2.setText("Nome");
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 100, -1, -1));

        jLabel3.setText("Preço de Compra");
        add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 180, -1, -1));

        jLabel5.setText("Quantidade");
        add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 270, -1, -1));

        jLabel6.setText("Tipo de Recurso");
        add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 270, -1, -1));

        lblPrecoAluguer.setText("Preço Aluguer");
        add(lblPrecoAluguer, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 270, -1, -1));

        lblValidade.setText("Validade");
        add(lblValidade, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 270, -1, -1));
        add(spPrecoAluguer, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 290, 154, 35));
        add(spPreco, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 200, 660, 40));
        add(spQuantidade, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 290, 180, 35));
    }// </editor-fold>//GEN-END:initComponents

    private void txtNomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNomeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNomeActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        // 1. RECOLHA DE DADOS COMUNS
        String nome = txtNome.getText().trim();
        double precoCompra = ((Number) spPreco.getValue()).doubleValue();
        int quantidade = (int) spQuantidade.getValue();

        // Detetar tipo pelo combo (0 = Consumível, 1 = Não Consumível)
        int tipoRecurso = comboTipo.getSelectedIndex();

        // Se for edição, mantém o estado ativo atual; se for novo, define como true
        boolean ativo = (idRecursoEditando > 0) ? this.ativoAtual : true;

        // 2. VALIDAÇÃO DE CAMPOS COMUNS
        if (nome.isEmpty() || nome.equals("Nome")) {
            mostrarMensagem("O nome do recurso é obrigatório.", "Erro", "src/main/java/Recursos/erro.png");
            return;
        }
        
        boolean sucesso = false;

        // 4. LOGICA ESPECÍFICA POR TIPO
        if (tipoRecurso == 0) { // --- MODO CONSUMÍVEL ---
            String dataStr = txtValidade.getText().trim();
            java.util.Date validade = null;

            try {
                if (!dataStr.isEmpty()) {
                    validade = new java.text.SimpleDateFormat("dd/MM/yyyy").parse(dataStr);
                } else {
                    mostrarMensagem("A data de validade é obrigatória.", "Erro", "src/main/java/Recursos/erro.png");
                    return;
                }
            } catch (java.text.ParseException e) {
                mostrarMensagem("Formato de data inválido (use dd/MM/yyyy).", "Erro", "src/main/java/Recursos/erro.png");
                return;
            }

            Consumivel consumivel = new Consumivel();
            consumivel.setNome(nome);
            consumivel.setPreco(precoCompra);
            consumivel.setQuantidade(quantidade);
            consumivel.setAtivo(ativo);
            consumivel.setDataValidade(validade);

            if (idRecursoEditando > 0) {
                consumivel.setIdRecurso(idRecursoEditando);
                // Chama o método que trata a edição e a possível troca de tabela
                sucesso = controller.editarRecurso(consumivel, 0);
            } else {
                sucesso = controller.criarConsumivel(consumivel);
            }

        } else { // --- MODO NÃO CONSUMÍVEL ---
            double precoAluguer = ((Number) spPrecoAluguer.getValue()).doubleValue();

            if (precoAluguer <= 0) {
                mostrarMensagem("O preço de aluguer deve ser superior a 0.", "Erro", "src/main/java/Recursos/erro.png");
                return;
            }

            NaoConsumivel naoConsumivel = new NaoConsumivel();
            naoConsumivel.setNome(nome);
            naoConsumivel.setPreco(precoCompra);
            naoConsumivel.setQuantidade(quantidade);
            naoConsumivel.setAtivo(ativo);
            naoConsumivel.setPrecoAluguer(precoAluguer);

            if (idRecursoEditando > 0) {
                naoConsumivel.setIdRecurso(idRecursoEditando);
                // Chama o método que trata a edição e a possível troca de tabela
                sucesso = controller.editarRecurso(naoConsumivel, 1);
            } else {
                sucesso = controller.criarNaoConsumivel(naoConsumivel);
            }
        }

        // 5. FEEDBACK E NAVEGAÇÃO
        if (sucesso) {
            mostrarMensagem("Dados guardados com sucesso!", "Sucesso", "src/main/java/Recursos/info.png");
            if (janelaPrincipal != null) {
                janelaPrincipal.mostrarListaRecursos(); 
            }
        } else {
            mostrarMensagem("Erro ao comunicar com a Base de Dados.", "Erro", "src/main/java/Recursos/erro.png");
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // Apenas pedimos à janela principal para mostrar a lista novamente
        if (janelaPrincipal != null) {
            janelaPrincipal.mostrarListaRecursos();
        }
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void comboTipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboTipoActionPerformed
        String tipoSelecionado = (String) comboTipo.getSelectedItem();

        if ("Consumível".equals(tipoSelecionado)) {

            lblValidade.setVisible(true);
            txtValidade.setVisible(true);

            lblPrecoAluguer.setVisible(false);
            spPrecoAluguer.setVisible(false);

        } else {

            lblValidade.setVisible(false);
            txtValidade.setVisible(false);
            txtValidade.setText(""); 

            lblPrecoAluguer.setVisible(true);
            spPrecoAluguer.setVisible(true);
        }
    }//GEN-LAST:event_comboTipoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JComboBox<String> comboTipo;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel lblPrecoAluguer;
    private javax.swing.JLabel lblValidade;
    private javax.swing.JSpinner spPreco;
    private javax.swing.JSpinner spPrecoAluguer;
    private javax.swing.JSpinner spQuantidade;
    private javax.swing.JTextField txtNome;
    private javax.swing.JTextField txtValidade;
    // End of variables declaration//GEN-END:variables
}

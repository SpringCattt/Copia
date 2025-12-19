package VIEWS;

import CONTROLLERS.HomeController;
import MODELS.CLASS.CategoriaTrabalho;
import java.util.List;

/**
 *
 * @author gonca
 */
public class PanelFormularioFuncionario extends javax.swing.JPanel {

    private HomeController controller;
    private PaginaInicial janelaPrincipal;

    /**
     * Creates new form PanelAdicionarFuncionario
     */
    public PanelFormularioFuncionario(PaginaInicial paginaInicial) {
        this.janelaPrincipal = paginaInicial;
        this.controller = new HomeController();
        initComponents();
        txtID.setVisible(false);
        List<CategoriaTrabalho> categorias = controller.getCategoriasTrabalho();

        for (CategoriaTrabalho c : categorias) {
            System.out.println("A carregar: " + c.getNome() + " " + c.getIdCategoria());
            comboCategoria.addItem(c);
        }
    }

    public void preencherDadosParaEdicao(String id, String nome, String email, String categoria) {
        //Muda o título da página
        lblTitulo.setText("Editar Funcionário");

        //Preenche os campos com os dados recebidos
        txtID.setText(id);
        txtNome.setText(nome);
        txtEmailPessoal.setText(email);

        comboCategoria.setSelectedItem(categoria);

        txtPassword.setText("");

        txtPassword.setToolTipText("Deixa vazio para manter a password atual");

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnGuardar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        txtID = new javax.swing.JTextField();
        txtNome = new javax.swing.JTextField();
        txtEmailPessoal = new javax.swing.JTextField();
        checkAtividade = new javax.swing.JCheckBox();
        comboCategoria = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        txtPassword = new javax.swing.JPasswordField();
        lblTitulo = new javax.swing.JLabel();
        txtEmailEmpresa = new javax.swing.JTextField();

        setBackground(new java.awt.Color(232, 235, 238));
        setPreferredSize(new java.awt.Dimension(740, 530));

        btnGuardar.setText("Guardar");
        btnGuardar.setPreferredSize(new java.awt.Dimension(81, 23));
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        txtID.setText("ID");
        txtID.setEnabled(false);

        txtNome.setText("Nome Completo");
        txtNome.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtNomeMouseClicked(evt);
            }
        });
        txtNome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNomeActionPerformed(evt);
            }
        });

        txtEmailPessoal.setText("Email Pessoal");
        txtEmailPessoal.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtEmailPessoalMouseClicked(evt);
            }
        });
        txtEmailPessoal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEmailPessoalActionPerformed(evt);
            }
        });

        jLabel1.setText("Atividade");

        txtPassword.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtPasswordMouseClicked(evt);
            }
        });

        lblTitulo.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblTitulo.setText("Adicionar Funcionário");

        txtEmailEmpresa.setText("Email Empresa");
        txtEmailEmpresa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtEmailEmpresaMouseClicked(evt);
            }
        });
        txtEmailEmpresa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEmailEmpresaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTitulo)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(comboCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtEmailPessoal)
                        .addComponent(txtNome, javax.swing.GroupLayout.DEFAULT_SIZE, 638, Short.MAX_VALUE)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel1)
                            .addGap(18, 18, 18)
                            .addComponent(checkAtividade))
                        .addComponent(txtPassword)
                        .addComponent(txtEmailEmpresa)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(387, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(7, Short.MAX_VALUE)
                .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblTitulo)
                .addGap(18, 18, 18)
                .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtEmailPessoal, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtEmailEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(comboCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(checkAtividade))
                .addGap(72, 72, 72)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(42, 42, 42))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        String nome = txtNome.getText().trim();
        String emailpessoal = txtEmailPessoal.getText().trim();
        String emailempresa = txtEmailEmpresa.getText().trim();
        String password = new String(txtPassword.getPassword());
        boolean atividade = checkAtividade.isSelected();

        CategoriaTrabalho selecionada = (CategoriaTrabalho) comboCategoria.getSelectedItem();
        int idCategoria = 0;

        if (selecionada != null) {
            idCategoria = selecionada.getIdCategoria();
            System.out.println("ID selecionado: " + idCategoria);
        }

        if (nome.isEmpty() || emailempresa.isEmpty() || password.isEmpty() || nome.equals("Nome Completo") || emailempresa.equals("Email Empresa")) {
            javax.swing.JOptionPane.showMessageDialog(
                    this,
                    "Preencha todos os campos obrigatórios",
                    "Erro",
                    javax.swing.JOptionPane.ERROR_MESSAGE
            );
            return;
    }//GEN-LAST:event_btnGuardarActionPerformed

        if (controller.verificarDuplicidadeEmail(emailempresa)) {
            javax.swing.JOptionPane.showMessageDialog(
                    this,
                    "Email ja existente em outro trabalhador!",
                    "Erro",
                    javax.swing.JOptionPane.ERROR_MESSAGE
            );
            return;
        }

        System.out.println("Nome: " + nome);
        System.out.println("Email: " + emailempresa);
        System.out.println("Categoria: " + idCategoria);
        System.out.println("Atividade: " + atividade);

        boolean sucesso = controller.criarFuncionario(
                nome,
                emailpessoal,
                emailempresa,
                password,
                idCategoria,
                atividade
        );

        if (sucesso) {
            javax.swing.JOptionPane.showMessageDialog(
                    this,
                    "Funcionário criado com sucesso!",
                    "Sucesso",
                    javax.swing.JOptionPane.INFORMATION_MESSAGE
            );

            if (janelaPrincipal != null) {
                txtNome.setText("Nome Completo");
                txtEmailPessoal.setText("Email Pessoal");
                txtEmailEmpresa.setText("Email Empresa");
                janelaPrincipal.mostrarListaFuncionarios();
            }

        } else {
            javax.swing.JOptionPane.showMessageDialog(
                    this,
                    "Erro ao criar funcionário",
                    "Erro",
                    javax.swing.JOptionPane.ERROR_MESSAGE
            );
        }

    }

    private void txtNomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNomeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNomeActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // Apenas pedimos à janela principal para mostrar a lista novamente
        if (janelaPrincipal != null) {
            txtNome.setText("Nome Completo");
            txtEmailPessoal.setText("Email Pessoal");
            txtEmailEmpresa.setText("Email Empresa");
            janelaPrincipal.mostrarListaFuncionarios();
        }
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void txtNomeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtNomeMouseClicked
        if ("Nome Completo".equals(txtNome.getText())) {
            txtNome.setText("");
        }

    }//GEN-LAST:event_txtNomeMouseClicked

    private void txtEmailPessoalMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtEmailPessoalMouseClicked
        if ("Email Pessoal".equals(txtEmailPessoal.getText())) {
            txtEmailPessoal.setText("");
        }
    }//GEN-LAST:event_txtEmailPessoalMouseClicked

    private void txtEmailPessoalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEmailPessoalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEmailPessoalActionPerformed

    private void txtEmailEmpresaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtEmailEmpresaMouseClicked
        if ("Email Empresa".equals(txtEmailEmpresa.getText())) {
            txtEmailEmpresa.setText("");
        }
    }//GEN-LAST:event_txtEmailEmpresaMouseClicked

    private void txtEmailEmpresaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEmailEmpresaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEmailEmpresaActionPerformed

    private void txtPasswordMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtPasswordMouseClicked

    }//GEN-LAST:event_txtPasswordMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JCheckBox checkAtividade;
    private javax.swing.JComboBox<CategoriaTrabalho> comboCategoria;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JTextField txtEmailEmpresa;
    private javax.swing.JTextField txtEmailPessoal;
    private javax.swing.JTextField txtID;
    private javax.swing.JTextField txtNome;
    private javax.swing.JPasswordField txtPassword;
    // End of variables declaration//GEN-END:variables
}

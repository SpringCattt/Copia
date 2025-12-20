package VIEWS;

import CONTROLLERS.HomeController;
import MODELS.CLASS.CategoriaTrabalho;
import MODELS.CLASS.Trabalhador;
import MODELS.CLASS.Credenciais;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

public class PanelFormularioFuncionario extends javax.swing.JPanel {

    private HomeController controller;
    private PaginaInicial janelaPrincipal;
    
    private int idFuncionarioEditando = -1;
    
    private boolean atividadeAtual = true; 

    public PanelFormularioFuncionario(PaginaInicial paginaInicial) {
        this.janelaPrincipal = paginaInicial;
        this.controller = new HomeController();
        initComponents();
        
        txtID.setVisible(false); 
        
        carregarCategorias();
    }
    
    private void carregarCategorias() {
        comboCategoria.removeAllItems();
        List<CategoriaTrabalho> categorias = controller.getCategoriasTrabalho();
        for (CategoriaTrabalho c : categorias) {
            comboCategoria.addItem(c); 
        }
    }

    public void preencherDadosParaEdicao(String idStr, String nome, String emailPessoal, String emailEmpresa, String password, String categoria) {
        try {
            int id = Integer.parseInt(idStr);
            this.idFuncionarioEditando = id;
            
            txtID.setText(idStr);
            txtNome.setText(nome);
            txtEmailPessoal.setText(emailPessoal);
            txtEmailEmpresa.setText(emailEmpresa);
            txtPassword.setText(password); 
            
            for (int i = 0; i < comboCategoria.getItemCount(); i++) {
                CategoriaTrabalho cat = comboCategoria.getItemAt(i);
                if (cat.getNome().equals(categoria)) {
                    comboCategoria.setSelectedIndex(i);
                    break;
                }
            }
            
            btnGuardar.setText("Atualizar");
            lblTitulo.setText("Editar Funcionário (ID: " + id + ")");
            
        } catch (NumberFormatException e) {
            System.err.println("Erro ID inválido: " + e.getMessage());
        }
    }

    private void limparFormulario() {
        idFuncionarioEditando = -1;
        txtID.setText("");
        txtNome.setText("");
        txtEmailPessoal.setText("");
        txtEmailEmpresa.setText("");
        txtPassword.setText("");
        btnGuardar.setText("Guardar");
        lblTitulo.setText("Adicionar Funcionário");
        if (comboCategoria.getItemCount() > 0) comboCategoria.setSelectedIndex(0);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnGuardar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        txtID = new javax.swing.JTextField();
        txtNome = new javax.swing.JTextField();
        txtEmailPessoal = new javax.swing.JTextField();
        comboCategoria = new javax.swing.JComboBox<>();
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
                .addGap(109, 109, 109)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(42, 42, 42))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        String nome = txtNome.getText().trim();
        String emailPessoal = txtEmailPessoal.getText().trim();
        String emailEmpresa = txtEmailEmpresa.getText().trim();
        String password = String.valueOf(txtPassword.getPassword()).trim();
        
        CategoriaTrabalho catSelecionada = (CategoriaTrabalho) comboCategoria.getSelectedItem();
        int idCategoria = 1; 
        if (catSelecionada != null) {
            idCategoria = catSelecionada.getIdCategoria();
        }

        if (nome.isEmpty() || emailPessoal.isEmpty() || emailEmpresa.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "Todos os campos são obrigatórios.\nPor favor, preencha tudo.", 
                "Erro de Validação", 
                JOptionPane.ERROR_MESSAGE);
            return;
        }

        boolean sucesso = false;

        if (idFuncionarioEditando == -1) {
            sucesso = controller.criarFuncionario(nome, emailPessoal, emailEmpresa, password, idCategoria, true);
        } else {
            sucesso = controller.editarFuncionario(idFuncionarioEditando, nome, emailPessoal, emailEmpresa, password, idCategoria, true);
        }

        if (sucesso) {
            JOptionPane.showMessageDialog(this, "Funcionário guardado com sucesso!");
            limparFormulario();
            if (janelaPrincipal != null) {
                janelaPrincipal.mostrarListaFuncionarios();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Erro ao guardar funcionário.\nVerifique se o email já existe.");
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void txtNomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNomeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNomeActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
       if (janelaPrincipal != null) {
           limparFormulario();
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
    private javax.swing.JComboBox<CategoriaTrabalho> comboCategoria;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JTextField txtEmailEmpresa;
    private javax.swing.JTextField txtEmailPessoal;
    private javax.swing.JTextField txtID;
    private javax.swing.JTextField txtNome;
    private javax.swing.JPasswordField txtPassword;
    // End of variables declaration//GEN-END:variables
}

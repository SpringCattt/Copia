package VIEWS;

import CONTROLLERS.HomeController;
import CONTROLLERS.PasswordUtil;
import MODELS.CLASS.CategoriaTrabalho;
import MODELS.CLASS.Trabalhador;
import MODELS.CLASS.Credenciais;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

public class PanelFormularioFuncionario extends javax.swing.JPanel {

    private HomeController controller;
    private PaginaInicial janelaPrincipal;
    private String top, mensagem, imagem;
    java.awt.Window win = javax.swing.SwingUtilities.getWindowAncestor(this);
    
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
    
    private void mostrarDialogo(String top, String mensagem, String imagem) {
        PaginaDialogo dialogo = new PaginaDialogo((java.awt.Frame) win, true);
        dialogo.setMensagem(mensagem, top, imagem);
        dialogo.setLocationRelativeTo(win);
        dialogo.setVisible(true);
    }

    public void preencherDadosParaEdicao(String idStr, String nomeIgnorado, String emailIgnorado, String catIgnorada) {
        try {
            int id = Integer.parseInt(idStr);
            this.idFuncionarioEditando = id;

            Trabalhador t = controller.buscarTrabalhadorPorId(id);
            Credenciais c = controller.buscarCredenciaisPorId(id);

            if (t != null && c != null) {
                txtID.setText(String.valueOf(t.getIdTrabalhador()));
                txtNome.setText(t.getNome());
                txtEmailPessoal.setText(t.getEmailPessoal());
                txtEmailEmpresa.setText(c.getEmail());

                // --- ALTERAÇÃO AQUI: Não carregar a password ---
                txtPassword.setText(""); 
                // -----------------------------------------------

                this.atividadeAtual = t.isAtivo(); 

                // Lógica da categoria (mantém-se igual)
                int idCat = t.getCategoria();
                for (int i = 0; i < comboCategoria.getItemCount(); i++) {
                    CategoriaTrabalho cat = comboCategoria.getItemAt(i);
                    if (cat.getIdCategoria() == idCat) {
                        comboCategoria.setSelectedIndex(i);
                        break;
                    }
                }

                btnGuardar.setText("Atualizar");
                lblTitulo.setText("Editar Funcionário (ID: " + id + ")");
                txtPassword.setToolTipText("Deixe vazio para manter a password atual");

            } else {
                // ... lógica de erro ...
            }
        } catch (NumberFormatException e) {
            System.err.println("Erro ID inválido: " + e.getMessage());
        }
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
        lblTitulo2 = new javax.swing.JLabel();
        lblTitulo3 = new javax.swing.JLabel();
        lblTitulo4 = new javax.swing.JLabel();
        lblTitulo5 = new javax.swing.JLabel();
        lblTitulo6 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(232, 235, 238));
        setPreferredSize(new java.awt.Dimension(740, 530));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnGuardar.setText("Guardar");
        btnGuardar.setPreferredSize(new java.awt.Dimension(81, 23));
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        add(btnGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(555, 448, 150, 40));

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        add(btnCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(387, 448, 150, 40));

        txtID.setText("ID");
        txtID.setEnabled(false);
        add(txtID, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 7, -1, 35));

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
        add(txtNome, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 120, 640, 35));

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
        add(txtEmailPessoal, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 200, 640, 35));

        add(comboCategoria, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 360, 290, 35));

        txtPassword.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtPasswordMouseClicked(evt);
            }
        });
        add(txtPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 360, 300, 35));

        lblTitulo.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblTitulo.setText("Adicionar Funcionário");
        add(lblTitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(51, 54, -1, -1));

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
        add(txtEmailEmpresa, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 280, 640, 35));

        lblTitulo2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblTitulo2.setText("Categoria:");
        add(lblTitulo2, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 330, -1, 20));

        lblTitulo3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblTitulo3.setText("Nome Completo:");
        add(lblTitulo3, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 90, -1, 20));

        lblTitulo4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblTitulo4.setText("Email Pessoal:");
        add(lblTitulo4, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 170, -1, 20));

        lblTitulo5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblTitulo5.setText("Email Empresa:");
        add(lblTitulo5, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 250, -1, 20));

        lblTitulo6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblTitulo6.setText("Password:");
        add(lblTitulo6, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 330, -1, 20));
    }// </editor-fold>//GEN-END:initComponents

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        // 1. Captura de dados
        String nome = txtNome.getText().trim();
        String emailpessoal = txtEmailPessoal.getText().trim();
        String emailempresa = txtEmailEmpresa.getText().trim();
        String passwordPlana = new String(txtPassword.getPassword()).trim();

        // Variável que estava faltando
        boolean sucesso = false; 

        // Define o estado de atividade (preserva o atual se estiver editando)
        boolean ativo = (idFuncionarioEditando > 0) ? this.atividadeAtual : true;

        // Obtém a categoria selecionada
        CategoriaTrabalho selecionada = (CategoriaTrabalho) comboCategoria.getSelectedItem();
        int idCategoria = (selecionada != null) ? selecionada.getIdCategoria() : 1;

        // 2. VALIDAÇÕES DE INTERFACE
        if (nome.isEmpty() || emailpessoal.isEmpty() || emailempresa.isEmpty() || nome.equals("Nome Completo")) {
            mostrarDialogo("Erro", "Todos os campos são obrigatórios.", "src/main/java/Recursos/erro.png");
            return;
        }

        if (idFuncionarioEditando == -1 && passwordPlana.isEmpty()) {
            mostrarDialogo("Erro", "Password é obrigatória para novos funcionários.", "src/main/java/Recursos/erro.png");
            return;
        }

        if (emailpessoal.equalsIgnoreCase(emailempresa)) {
            mostrarDialogo("Erro", "O Email Pessoal não pode ser igual ao da Empresa.", "src/main/java/Recursos/erro.png");
            return;
        }

        // 3. VALIDAÇÕES DE BANCO (DUPLICIDADE)
        if (controller.verificarDuplicidadeEmail(emailempresa, idFuncionarioEditando)) {
            mostrarDialogo("Erro", "O email de empresa já está em uso.", "src/main/java/Recursos/erro.png");
            return;
        }

        if (controller.verificarDuplicidadeEmailPessoal(emailpessoal, idFuncionarioEditando)) {
            mostrarDialogo("Erro", "O email pessoal já está registado.", "src/main/java/Recursos/erro.png");
            return;
        }

        // 4. LÓGICA DA PASSWORD (ENCRIPTAÇÃO OU MANUTENÇÃO)
        String passwordFinal;
        if (idFuncionarioEditando > 0 && passwordPlana.isEmpty()) {
            // Campo vazio na edição: envia null para o controller não alterar a senha atual
            passwordFinal = null; 
        } else {
            // Novo funcionário ou alteração de senha: gera novo hash
            passwordFinal = PasswordUtil.hashPassword(passwordPlana);
        }

        // 5. EXECUÇÃO DA PERSISTÊNCIA
        if (idFuncionarioEditando > 0) {
            // ATUALIZAR
            sucesso = controller.editarFuncionario(
                idFuncionarioEditando, nome, emailpessoal, emailempresa, passwordFinal, idCategoria, ativo
            );
        } else {
            // CRIAR NOVO
            sucesso = controller.criarFuncionario(
                nome, emailpessoal, emailempresa, passwordFinal, idCategoria, ativo
            );
        }

        // 6. FEEDBACK FINAL
        if (sucesso) {
            mostrarDialogo("Informação", "Operação realizada com sucesso!", "src/main/java/Recursos/info.png");
            if (janelaPrincipal != null) {
                janelaPrincipal.mostrarListaFuncionarios(); 
            }
        } else {
            mostrarDialogo("Erro", "Erro ao guardar dados na base de dados.", "src/main/java/Recursos/erro.png");
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void txtNomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNomeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNomeActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
       if (janelaPrincipal != null) {
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
    private javax.swing.JLabel lblTitulo2;
    private javax.swing.JLabel lblTitulo3;
    private javax.swing.JLabel lblTitulo4;
    private javax.swing.JLabel lblTitulo5;
    private javax.swing.JLabel lblTitulo6;
    private javax.swing.JTextField txtEmailEmpresa;
    private javax.swing.JTextField txtEmailPessoal;
    private javax.swing.JTextField txtID;
    private javax.swing.JTextField txtNome;
    private javax.swing.JPasswordField txtPassword;
    // End of variables declaration//GEN-END:variables
}

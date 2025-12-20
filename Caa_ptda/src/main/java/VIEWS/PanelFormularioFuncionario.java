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
                txtPassword.setText(c.getPassword()); 
                               
                this.atividadeAtual = t.isAtivo(); 
                
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
                txtPassword.setToolTipText("Deixa vazio para manter a password atual");
                
            } else {
                PaginaDialogo dialogo = new PaginaDialogo((java.awt.Frame) win, true);

                mensagem = "Erro ao buscar dados do funcionário na BD.";
                top = "Erro";
                imagem = "src/main/java/Recursos/erro.png";

                dialogo.setMensagem(mensagem, top, imagem);
                dialogo.setLocationRelativeTo(win);
                dialogo.setVisible(true);
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
        String emailpessoal = txtEmailPessoal.getText().trim();
        String emailempresa = txtEmailEmpresa.getText().trim();
        String password = new String(txtPassword.getPassword()).trim();
        
        boolean ativo = (idFuncionarioEditando > 0) ? this.atividadeAtual : true;

        CategoriaTrabalho selecionada = (CategoriaTrabalho) comboCategoria.getSelectedItem();
        int idCategoria = (selecionada != null) ? selecionada.getIdCategoria() : 1;

        // 1. CAMPOS VAZIOS
        if (nome.isEmpty() || emailpessoal.isEmpty() || emailempresa.isEmpty() || nome.equals("Nome Completo")) {
            PaginaDialogo dialogo = new PaginaDialogo((java.awt.Frame) win, true);

            mensagem = "Todos os campos são obrigatórios.";
            top = "Erro";
            imagem = "src/main/java/Recursos/erro.png";

            dialogo.setMensagem(mensagem, top, imagem);
            dialogo.setLocationRelativeTo(win);
            dialogo.setVisible(true);
            return;
        }
        
        // 2. PASSWORD OBRIGATÓRIA (SÓ PARA NOVOS)
        if (idFuncionarioEditando == -1 && password.isEmpty()) {
            PaginaDialogo dialogo = new PaginaDialogo((java.awt.Frame) win, true);

            mensagem = "Password é obrigatória para o novo utilizador.";
            top = "Erro";
            imagem = "src/main/java/Recursos/erro.png";

            dialogo.setMensagem(mensagem, top, imagem);
            dialogo.setLocationRelativeTo(win);
            dialogo.setVisible(true);
             return;
        }

        // 3. EMAILS IGUAIS
        if (emailpessoal.equalsIgnoreCase(emailempresa)) {
            PaginaDialogo dialogo = new PaginaDialogo((java.awt.Frame) win, true);

            mensagem = "O Email Pessoal não pode ser igual ao Email da Empresa.";
            top = "Erro";
            imagem = "src/main/java/Recursos/erro.png";

            dialogo.setMensagem(mensagem, top, imagem);
            dialogo.setLocationRelativeTo(win);
            dialogo.setVisible(true);
            return;
        }
        
        // 4. DUPLICIDADE EMAIL EMPRESA
        if (controller.verificarDuplicidadeEmail(emailempresa, idFuncionarioEditando)) {
            PaginaDialogo dialogo = new PaginaDialogo((java.awt.Frame) win, true);

            mensagem = "O email de empresa já está em uso por outro funcionário.";
            top = "Erro";
            imagem = "src/main/java/Recursos/erro.png";

            dialogo.setMensagem(mensagem, top, imagem);
            dialogo.setLocationRelativeTo(win);
            dialogo.setVisible(true);
            return;
        }

        // 5. DUPLICIDADE EMAIL PESSOAL (NOVO!)
        if (controller.verificarDuplicidadeEmailPessoal(emailpessoal, idFuncionarioEditando)) {
            PaginaDialogo dialogo = new PaginaDialogo((java.awt.Frame) win, true);

            mensagem = "O email pessoal já está registado noutro funcionário.";
            top = "Erro";
            imagem = "src/main/java/Recursos/erro.png";

            dialogo.setMensagem(mensagem, top, imagem);
            dialogo.setLocationRelativeTo(win);
            dialogo.setVisible(true);
            return;
        }

        // --- GUARDAR ---
        boolean sucesso;
        
        if (idFuncionarioEditando > 0) {
            // EDITAR
            System.out.println("A atualizar funcionário ID: " + idFuncionarioEditando);
            sucesso = controller.editarFuncionario(
                    idFuncionarioEditando, nome, emailpessoal, emailempresa, password, idCategoria, ativo
            );
        } else {
            // CRIAR
            System.out.println("A criar novo funcionário");
            sucesso = controller.criarFuncionario(
                    nome, emailpessoal, emailempresa, password, idCategoria, ativo
            );
        }

        if (sucesso) {
            PaginaDialogo dialogo = new PaginaDialogo((java.awt.Frame) win, true);

            mensagem = "Operação realizada com sucesso!";
            top = "Informação";
            imagem = "src/main/java/Recursos/info.png";

            dialogo.setMensagem(mensagem, top, imagem);
            dialogo.setLocationRelativeTo(win);
            dialogo.setVisible(true);
            // Limpar formulário se necessário, ou voltar
            if (janelaPrincipal != null) {
                janelaPrincipal.mostrarListaFuncionarios(); 
            }
        } else {
            PaginaDialogo dialogo = new PaginaDialogo((java.awt.Frame) win, true);

            mensagem = "Erro ao guardar dados na base de dados.";
            top = "Erro";
            imagem = "src/main/java/Recursos/erro.png";

            dialogo.setMensagem(mensagem, top, imagem);
            dialogo.setLocationRelativeTo(win);
            dialogo.setVisible(true);
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
    private javax.swing.JTextField txtEmailEmpresa;
    private javax.swing.JTextField txtEmailPessoal;
    private javax.swing.JTextField txtID;
    private javax.swing.JTextField txtNome;
    private javax.swing.JPasswordField txtPassword;
    // End of variables declaration//GEN-END:variables
}

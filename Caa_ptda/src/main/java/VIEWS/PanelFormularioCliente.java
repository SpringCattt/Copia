package VIEWS;

import CONTROLLERS.HomeController;
import MODELS.CLASS.CategoriaTrabalho;
import MODELS.CLASS.Trabalhador;
import MODELS.CLASS.Credenciais;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

public class PanelFormularioCliente extends javax.swing.JPanel {

    private HomeController controller;
    private PaginaInicial janelaPrincipal;
    private String top, mensagem, imagem;
    java.awt.Window win = javax.swing.SwingUtilities.getWindowAncestor(this);
    
    private int idFuncionarioEditando = -1;

    public PanelFormularioCliente(PaginaInicial paginaInicial) {
        this.janelaPrincipal = paginaInicial;
        this.controller = new HomeController();
        initComponents();
    }




    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnGuardar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        lblTitulo = new javax.swing.JLabel();
        txtNomeCliente = new javax.swing.JTextField();
        lblTitulo1 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        lblTitulo2 = new javax.swing.JLabel();
        txtNTelefone = new javax.swing.JTextField();
        lblTitulo3 = new javax.swing.JLabel();

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

        lblTitulo.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblTitulo.setText("Adicionar Funcionário");

        txtNomeCliente.setText("Nome Cliente");
        txtNomeCliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtNomeClienteMouseClicked(evt);
            }
        });
        txtNomeCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNomeClienteActionPerformed(evt);
            }
        });

        lblTitulo1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblTitulo1.setText("Nome Cliente");

        txtEmail.setText("Email");
        txtEmail.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtEmailMouseClicked(evt);
            }
        });
        txtEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEmailActionPerformed(evt);
            }
        });

        lblTitulo2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblTitulo2.setText("Email");

        txtNTelefone.setText("Telefone");
        txtNTelefone.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtNTelefoneMouseClicked(evt);
            }
        });
        txtNTelefone.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNTelefoneActionPerformed(evt);
            }
        });

        lblTitulo3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblTitulo3.setText("Nº Telefone");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(387, Short.MAX_VALUE)
                .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35))
            .addGroup(layout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTitulo3)
                    .addComponent(txtNTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, 638, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTitulo2)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 638, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTitulo1)
                    .addComponent(txtNomeCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 638, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTitulo))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(58, Short.MAX_VALUE)
                .addComponent(lblTitulo)
                .addGap(37, 37, 37)
                .addComponent(lblTitulo1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNomeCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblTitulo2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblTitulo3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(126, 126, 126)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(42, 42, 42))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        
    }//GEN-LAST:event_btnGuardarActionPerformed

        
    

            

       


    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
       if (janelaPrincipal != null) {
            janelaPrincipal.irParaFormularioBilhete();
        }
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void txtNomeClienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtNomeClienteMouseClicked
        if ("Nome Cliente".equals(txtNomeCliente.getText())) {
            txtNomeCliente.setText("");
        }
    }//GEN-LAST:event_txtNomeClienteMouseClicked

    private void txtNomeClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNomeClienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNomeClienteActionPerformed

    private void txtEmailMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtEmailMouseClicked
       if ("Email".equals(txtEmail.getText())) {
            txtEmail.setText("");
        }
    }//GEN-LAST:event_txtEmailMouseClicked

    private void txtEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEmailActionPerformed
        if ("Nº Telefone".equals(txtNTelefone.getText())) {
            txtNTelefone.setText("");
        }
    }//GEN-LAST:event_txtEmailActionPerformed

    private void txtNTelefoneMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtNTelefoneMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNTelefoneMouseClicked

    private void txtNTelefoneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNTelefoneActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNTelefoneActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JLabel lblTitulo1;
    private javax.swing.JLabel lblTitulo2;
    private javax.swing.JLabel lblTitulo3;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtNTelefone;
    private javax.swing.JTextField txtNomeCliente;
    // End of variables declaration//GEN-END:variables
}

package VIEWS;

import CONTROLLERS.HomeController;
import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Login extends javax.swing.JFrame {

    /**
     * Creates new form Login
     */
    private final Color COR_NORMAL_TOP = new Color(255, 252, 252);
    private final Color COR_NORMAL = new Color(97, 99, 103);
    private final Color COR_HOVER = new Color(131, 136, 141);
    private final Color COR_HOVER_FECHAR = new Color(193, 18, 31);
    private final Color COR_SELECIONADO = new Color(238, 238, 238);
    private HomeController controller;

    public Login() {
        this.controller = new HomeController();
        initComponents();
        panelPagina.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
    }
    
    private void exibirMensagemDialogo(String titulo, String mensagem, String caminhoImagem) {
        PaginaDialogo dialogo = new PaginaDialogo(this, true);
        dialogo.setMensagem(mensagem, titulo, caminhoImagem);
        dialogo.setVisible(true);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelPagina = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtPassword = new javax.swing.JPasswordField();
        btnLogin = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        panelFechar = new javax.swing.JPanel();
        labelFechar = new javax.swing.JLabel();
        panelMinimizar = new javax.swing.JPanel();
        labelMinimizar = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setUndecorated(true);

        panelPagina.setBackground(new java.awt.Color(255, 252, 252));
        panelPagina.setMaximumSize(new java.awt.Dimension(451, 666));
        panelPagina.setMinimumSize(new java.awt.Dimension(451, 666));
        panelPagina.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setText("Email");
        panelPagina.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(113, 258, 37, -1));

        txtEmail.setText("admin@caa.pt");
        panelPagina.add(txtEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(113, 296, 225, 35));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("Password");
        panelPagina.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(113, 349, 225, -1));

        txtPassword.setText("Admin2025@");
        panelPagina.add(txtPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(113, 387, 225, 35));

        btnLogin.setBackground(new java.awt.Color(51, 121, 232));
        btnLogin.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnLogin.setForeground(new java.awt.Color(255, 255, 255));
        btnLogin.setText("Login");
        btnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginActionPerformed(evt);
            }
        });
        panelPagina.add(btnLogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(113, 466, 225, 43));

        jLabel3.setIcon(new javax.swing.ImageIcon("src/main/java/Recursos/CAA_Logo_Pequeno.png"));
        panelPagina.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(113, 15, 225, 225));

        panelFechar.setBackground(new java.awt.Color(255, 252, 252));
        panelFechar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelFecharMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                panelFecharMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                panelFecharMouseExited(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                panelFecharMouseReleased(evt);
            }
        });
        panelFechar.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        labelFechar.setIcon(new javax.swing.ImageIcon("src/main/java/Recursos/fechar.png"));
        labelFechar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelFecharpanelFecharMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                labelFecharpanelFecharMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                labelFecharpanelFecharMouseExited(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                labelFecharpanelFecharMouseReleased(evt);
            }
        });
        panelFechar.add(labelFechar, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 15, 20, 20));

        panelPagina.add(panelFechar, new org.netbeans.lib.awtextra.AbsoluteConstraints(394, 15, 50, 50));

        panelMinimizar.setBackground(new java.awt.Color(255, 252, 252));
        panelMinimizar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelMinimizarMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                panelMinimizarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                panelMinimizarMouseExited(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                panelMinimizarMouseReleased(evt);
            }
        });
        panelMinimizar.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        labelMinimizar.setIcon(new javax.swing.ImageIcon("src/main/java/Recursos/minimizar.png"));
        labelMinimizar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelMinimizarpanelMinimizarMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                labelMinimizarpanelMinimizarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                labelMinimizarpanelMinimizarMouseExited(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                labelMinimizarpanelMinimizarMouseReleased(evt);
            }
        });
        panelMinimizar.add(labelMinimizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 15, 20, 20));

        panelPagina.add(panelMinimizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(344, 15, 50, 50));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelPagina, javax.swing.GroupLayout.DEFAULT_SIZE, 451, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelPagina, javax.swing.GroupLayout.DEFAULT_SIZE, 666, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoginActionPerformed
        // 1. Captura de dados da VIEW
        String email = txtEmail.getText().trim();
        // Uso de char[] convertido para String por segurança momentânea
        String passwordDigitada = new String(txtPassword.getPassword());

        // 2. Validação de campos vazios (Teste de Condição Fronteira [cite: 381])
        if (email.isEmpty() || passwordDigitada.isEmpty()) {
            exibirMensagemDialogo("Aviso", "Por favor, preencha o email e a password.", "src/main/java/Recursos/aviso.png");
            return;
        }

        // 3. Chamada ao CONTROLLER para efetuar a lógica de autenticação [cite: 755]
        // O controlador agora tratará a comparação do Hash internamente
        Integer idTrabalhador = controller.efetuarLogin(email, passwordDigitada);

        if (idTrabalhador != null) {
            // 4. Verificação de estado (Observabilidade [cite: 27])
            boolean estaAtivo = controller.verificarAtividadeTrabalhador(idTrabalhador);

            if (estaAtivo) {
                PaginaInicial janela = new PaginaInicial(idTrabalhador);
                janela.setVisible(true);
                this.dispose(); // Fecha a tela de login
            } else {
                exibirMensagemDialogo("Aviso", "Esta conta encontra-se desativada!", "src/main/java/Recursos/aviso.png");
            }
        } else {
            // Falha na autenticação (Resultado Esperado em caso de dados incorretos [cite: 42])
            exibirMensagemDialogo("Erro", "Email ou Password incorretos.", "src/main/java/Recursos/erro.png");
        }
    }//GEN-LAST:event_btnLoginActionPerformed

    private void labelFecharpanelFecharMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelFecharpanelFecharMouseClicked
        System.exit(0);
    }//GEN-LAST:event_labelFecharpanelFecharMouseClicked

    private void labelFecharpanelFecharMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelFecharpanelFecharMouseEntered
        panelFechar.setBackground(COR_HOVER_FECHAR);
        labelFechar.setIcon(new ImageIcon("src/main/java/Recursos/fechar_b.png"));
    }//GEN-LAST:event_labelFecharpanelFecharMouseEntered

    private void labelFecharpanelFecharMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelFecharpanelFecharMouseExited
        panelFechar.setBackground(COR_NORMAL_TOP);
        labelFechar.setIcon(new ImageIcon("src/main/java/Recursos/fechar.png"));
    }//GEN-LAST:event_labelFecharpanelFecharMouseExited

    private void labelFecharpanelFecharMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelFecharpanelFecharMouseReleased

    }//GEN-LAST:event_labelFecharpanelFecharMouseReleased

    private void panelFecharMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelFecharMouseClicked
        System.exit(0);
    }//GEN-LAST:event_panelFecharMouseClicked

    private void panelFecharMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelFecharMouseEntered
        panelFechar.setBackground(COR_HOVER_FECHAR);
        labelFechar.setIcon(new ImageIcon("src/main/java/Recursos/fechar_b.png"));
    }//GEN-LAST:event_panelFecharMouseEntered

    private void panelFecharMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelFecharMouseExited
        panelFechar.setBackground(COR_NORMAL_TOP);
        labelFechar.setIcon(new ImageIcon("src/main/java/Recursos/fechar.png"));
    }//GEN-LAST:event_panelFecharMouseExited

    private void panelFecharMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelFecharMouseReleased

    }//GEN-LAST:event_panelFecharMouseReleased

    private void labelMinimizarpanelMinimizarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelMinimizarpanelMinimizarMouseClicked
        this.setState(JFrame.ICONIFIED);
    }//GEN-LAST:event_labelMinimizarpanelMinimizarMouseClicked

    private void labelMinimizarpanelMinimizarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelMinimizarpanelMinimizarMouseEntered
        panelMinimizar.setBackground(COR_SELECIONADO);
    }//GEN-LAST:event_labelMinimizarpanelMinimizarMouseEntered

    private void labelMinimizarpanelMinimizarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelMinimizarpanelMinimizarMouseExited
        panelMinimizar.setBackground(COR_NORMAL_TOP);
    }//GEN-LAST:event_labelMinimizarpanelMinimizarMouseExited

    private void labelMinimizarpanelMinimizarMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelMinimizarpanelMinimizarMouseReleased

    }//GEN-LAST:event_labelMinimizarpanelMinimizarMouseReleased

    private void panelMinimizarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelMinimizarMouseClicked
        this.setState(JFrame.ICONIFIED);
    }//GEN-LAST:event_panelMinimizarMouseClicked

    private void panelMinimizarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelMinimizarMouseEntered
        panelMinimizar.setBackground(COR_SELECIONADO);
    }//GEN-LAST:event_panelMinimizarMouseEntered

    private void panelMinimizarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelMinimizarMouseExited
        panelMinimizar.setBackground(COR_NORMAL_TOP);
    }//GEN-LAST:event_panelMinimizarMouseExited

    private void panelMinimizarMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelMinimizarMouseReleased

    }//GEN-LAST:event_panelMinimizarMouseReleased

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        try {
            com.formdev.flatlaf.FlatLightLaf.setup();
        } catch (Exception ex) {
            System.err.println("Erro ao iniciar FlatLaf");
        }
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLogin;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel labelFechar;
    private javax.swing.JLabel labelMinimizar;
    private javax.swing.JPanel panelFechar;
    private javax.swing.JPanel panelMinimizar;
    private javax.swing.JPanel panelPagina;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JPasswordField txtPassword;
    // End of variables declaration//GEN-END:variables
}

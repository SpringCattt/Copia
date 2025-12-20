/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package VIEWS;

import MODELS.DAO.BaseDados;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Point;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 *
 * @author filip
 */
public class EntrarBD extends javax.swing.JFrame {

    private final Color COR_NORMAL_TOP = new Color(97, 99, 103);
    private final Color COR_HOVER = new Color(131, 136, 141);
    private final Color COR_HOVER_FECHAR = new Color(193, 18, 31);
    private final Color COR_ENTRAR_NORMAL = new Color(51, 121, 232);
    private final Color COR_ENTRAR_HOVER = new Color(96, 150, 237);
    private final Color COR_ENTRAR_SELECIONADO = new Color(66, 106, 169);
    
    public EntrarBD() {
        estiloJanela();
        initComponents();
    }

    public void estiloJanela() {
        JPanel contentPane = new JPanel();
        contentPane.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        contentPane.setLayout(new BorderLayout());
        setContentPane(contentPane);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelPagina = new javax.swing.JPanel();
        labelUser = new javax.swing.JLabel();
        labelPassword = new javax.swing.JLabel();
        tfUser = new javax.swing.JTextField();
        panelTop = new javax.swing.JPanel();
        panelMinimizar = new javax.swing.JPanel();
        labelMinimizar = new javax.swing.JLabel();
        panelFechar = new javax.swing.JPanel();
        labelFechar = new javax.swing.JLabel();
        labelTop = new javax.swing.JLabel();
        tfPassword = new javax.swing.JPasswordField();
        panelBotaoEntrar = new javax.swing.JPanel();
        labelBotaoEntrar = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setAutoRequestFocus(false);
        setBackground(new java.awt.Color(238, 238, 238));
        setFocusable(false);
        setMaximumSize(new java.awt.Dimension(300, 316));
        setMinimumSize(new java.awt.Dimension(300, 316));
        setUndecorated(true);
        setPreferredSize(new java.awt.Dimension(300, 316));
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelPagina.setBackground(new java.awt.Color(255, 255, 255));
        panelPagina.setAlignmentX(1.0F);
        panelPagina.setAlignmentY(1.0F);

        labelUser.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        labelUser.setText("USER");

        labelPassword.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        labelPassword.setText("PASSWORD");

        tfUser.setText("PTDA25_02");

        panelTop.setBackground(new java.awt.Color(97, 99, 103));
        panelTop.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                panelTopMouseDragged(evt);
            }
        });
        panelTop.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                panelTopMousePressed(evt);
            }
        });

        panelMinimizar.setBackground(new java.awt.Color(97, 99, 103));
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

        labelMinimizar.setIcon(new javax.swing.ImageIcon("src/main/java/Recursos/minimizar_b.png"));
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

        panelFechar.setBackground(new java.awt.Color(97, 99, 103));
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

        labelFechar.setIcon(new javax.swing.ImageIcon("src/main/java/Recursos/fechar_b.png"));
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

        labelTop.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        labelTop.setForeground(new java.awt.Color(255, 255, 255));
        labelTop.setText("BASE DE DADOS");
        labelTop.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                panelTopMouseDragged(evt);
            }
        });
        labelTop.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                panelTopMousePressed(evt);
            }
        });

        javax.swing.GroupLayout panelTopLayout = new javax.swing.GroupLayout(panelTop);
        panelTop.setLayout(panelTopLayout);
        panelTopLayout.setHorizontalGroup(
            panelTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelTopLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(labelTop, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
                .addComponent(panelMinimizar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(panelFechar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        panelTopLayout.setVerticalGroup(
            panelTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelTopLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(panelTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelMinimizar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panelFechar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelTopLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelTop, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        tfPassword.setText("Jkis$985x");

        panelBotaoEntrar.setBackground(new java.awt.Color(51, 121, 232));
        panelBotaoEntrar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                panelBotaoEntrarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                panelBotaoEntrarMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                panelBotaoEntrarMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                panelBotaoEntrarMouseReleased(evt);
            }
        });

        labelBotaoEntrar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        labelBotaoEntrar.setForeground(new java.awt.Color(255, 255, 255));
        labelBotaoEntrar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelBotaoEntrar.setText("ENTRAR");
        labelBotaoEntrar.setToolTipText("");
        labelBotaoEntrar.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                labelBotaoEntrarpanelTopMouseDragged(evt);
            }
        });
        labelBotaoEntrar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                panelBotaoEntrarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                panelBotaoEntrarMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                labelBotaoEntrarpanelTopMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                panelBotaoEntrarMouseReleased(evt);
            }
        });

        javax.swing.GroupLayout panelBotaoEntrarLayout = new javax.swing.GroupLayout(panelBotaoEntrar);
        panelBotaoEntrar.setLayout(panelBotaoEntrarLayout);
        panelBotaoEntrarLayout.setHorizontalGroup(
            panelBotaoEntrarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBotaoEntrarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelBotaoEntrar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelBotaoEntrarLayout.setVerticalGroup(
            panelBotaoEntrarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBotaoEntrarLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(labelBotaoEntrar)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panelPaginaLayout = new javax.swing.GroupLayout(panelPagina);
        panelPagina.setLayout(panelPaginaLayout);
        panelPaginaLayout.setHorizontalGroup(
            panelPaginaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelTop, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(panelPaginaLayout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addGroup(panelPaginaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(tfUser, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                    .addComponent(labelUser, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfPassword)
                    .addComponent(panelBotaoEntrar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelPaginaLayout.setVerticalGroup(
            panelPaginaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelPaginaLayout.createSequentialGroup()
                .addComponent(panelTop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(labelUser)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfUser, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(labelPassword)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(panelBotaoEntrar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(30, Short.MAX_VALUE))
        );

        getContentPane().add(panelPagina, new org.netbeans.lib.awtextra.AbsoluteConstraints(1, 1, 298, 314));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void labelMinimizarpanelMinimizarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelMinimizarpanelMinimizarMouseClicked
        this.setState(JFrame.ICONIFIED);
    }//GEN-LAST:event_labelMinimizarpanelMinimizarMouseClicked

    private void labelMinimizarpanelMinimizarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelMinimizarpanelMinimizarMouseEntered
        panelMinimizar.setBackground(COR_HOVER);
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
        panelMinimizar.setBackground(COR_HOVER);
    }//GEN-LAST:event_panelMinimizarMouseEntered

    private void panelMinimizarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelMinimizarMouseExited
        panelMinimizar.setBackground(COR_NORMAL_TOP);
    }//GEN-LAST:event_panelMinimizarMouseExited

    private void panelMinimizarMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelMinimizarMouseReleased

    }//GEN-LAST:event_panelMinimizarMouseReleased

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
    }//GEN-LAST:event_panelFecharMouseEntered

    private void panelFecharMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelFecharMouseExited
        panelFechar.setBackground(COR_NORMAL_TOP);
    }//GEN-LAST:event_panelFecharMouseExited

    private void panelFecharMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelFecharMouseReleased

    }//GEN-LAST:event_panelFecharMouseReleased

    Point initialClick;
    
    private void panelTopMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelTopMousePressed
        initialClick = evt.getPoint();
    }//GEN-LAST:event_panelTopMousePressed

    private void panelTopMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelTopMouseDragged
        Frame frame = (JFrame) SwingUtilities.getWindowAncestor(panelPagina);

        int deltaX = evt.getX() - initialClick.x;
        int deltaY = evt.getY() - initialClick.y;
        frame.setLocation(frame.getX() + deltaX, frame.getY() + deltaY);
    }//GEN-LAST:event_panelTopMouseDragged

    private void labelBotaoEntrarpanelTopMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelBotaoEntrarpanelTopMouseDragged
        // TODO add your handling code here:
    }//GEN-LAST:event_labelBotaoEntrarpanelTopMouseDragged

    private void labelBotaoEntrarpanelTopMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelBotaoEntrarpanelTopMousePressed
        panelBotaoEntrar.setBackground(COR_ENTRAR_SELECIONADO);
    }//GEN-LAST:event_labelBotaoEntrarpanelTopMousePressed

    private void panelBotaoEntrarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelBotaoEntrarMouseEntered
        panelBotaoEntrar.setBackground(COR_ENTRAR_HOVER);
    }//GEN-LAST:event_panelBotaoEntrarMouseEntered

    private void panelBotaoEntrarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelBotaoEntrarMouseExited
        panelBotaoEntrar.setBackground(COR_ENTRAR_NORMAL);
    }//GEN-LAST:event_panelBotaoEntrarMouseExited

    private void panelBotaoEntrarMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelBotaoEntrarMousePressed
        panelBotaoEntrar.setBackground(COR_ENTRAR_SELECIONADO);
        String user = tfUser.getText();
        String pass = new String(tfPassword.getPassword());

        if (user.isEmpty() || pass.isEmpty()) {
            javax.swing.JOptionPane.showMessageDialog(this, "Preencha o utilizador e a password.");
            return;
        }

        // 1. Envia os dados para a classe BaseDados
        BaseDados.configurarCredenciais(user, pass);

        // 2. Tenta estabelecer a ligação para validar
        try {
            Connection testeCon = BaseDados.getConnection();

            if (testeCon != null) {
                javax.swing.JOptionPane.showMessageDialog(this, "Ligação ao servidor MySQL com sucesso!");

                Login janela = new Login();
                janela.setVisible(true);
                this.dispose();
            }
        } catch (SQLException e) {
            // Se chegar aqui, as credenciais de acesso ao servidor MySQL estão erradas
            javax.swing.JOptionPane.showMessageDialog(this, "Erro de Acesso: Utilizador ou Password do Banco de Dados inválidos.\n" + e.getMessage());
            e.printStackTrace();
        }
    }//GEN-LAST:event_panelBotaoEntrarMousePressed

    private void panelBotaoEntrarMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelBotaoEntrarMouseReleased
        if (panelBotaoEntrar.getBounds().contains(evt.getPoint())) {
            panelBotaoEntrar.setBackground(COR_ENTRAR_HOVER);
        } else {
            panelBotaoEntrar.setBackground(COR_ENTRAR_NORMAL);
        }
    }//GEN-LAST:event_panelBotaoEntrarMouseReleased

    
    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) { // Corrigido de 'panelTopch' para '} catch'
            java.util.logging.Logger.getLogger(EntrarBD.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EntrarBD.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EntrarBD.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EntrarBD.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(() -> {
            new EntrarBD().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel labelBotaoEntrar;
    private javax.swing.JLabel labelFechar;
    private javax.swing.JLabel labelMinimizar;
    private javax.swing.JLabel labelPassword;
    private javax.swing.JLabel labelTop;
    private javax.swing.JLabel labelUser;
    private javax.swing.JPanel panelBotaoEntrar;
    private javax.swing.JPanel panelFechar;
    private javax.swing.JPanel panelMinimizar;
    private javax.swing.JPanel panelPagina;
    private javax.swing.JPanel panelTop;
    private javax.swing.JPasswordField tfPassword;
    private javax.swing.JTextField tfUser;
    // End of variables declaration//GEN-END:variables
}

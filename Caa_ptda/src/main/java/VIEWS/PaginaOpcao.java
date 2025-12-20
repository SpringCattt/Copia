/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package VIEWS;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 *
 * @author filip
 */
public class PaginaOpcao extends javax.swing.JDialog {

    private final Color COR_NORMAL_FECHAR = new Color(97, 99, 103);
    private final Color COR_HOVER_FECHAR = new Color(193, 18, 31);
    private final Color COR_OK_NORMAL = new Color(51, 121, 232);
    private final Color COR_OK_HOVER = new Color(96, 150, 237);
    private final Color COR_OK_SELECIONADO = new Color(66, 106, 169);
    private boolean selecionouSim = false;
    
    public PaginaOpcao(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        estiloJanela();
    }

    public void estiloJanela() {
        JPanel contentPane = new JPanel();
        contentPane.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        contentPane.setLayout(new BorderLayout());
        setContentPane(contentPane);
    }
    
    public void setMensagem(String texto, String top) {
        labelMensagem.setText("<html><body style='text-align: center; width: 250px;'>" 
                              + texto.replace("\n", "<br>") + "</body></html>");

        labelTitulo.setText(top);
    }
    
    public boolean clicouSim() {
        return selecionouSim;
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelPagina = new javax.swing.JPanel();
        panelTop = new javax.swing.JPanel();
        panelFechar = new javax.swing.JPanel();
        labelFechar = new javax.swing.JLabel();
        labelTitulo = new javax.swing.JLabel();
        labelImagem = new javax.swing.JLabel();
        labelMensagem = new javax.swing.JLabel();
        panelSIM = new javax.swing.JPanel();
        labelSIM = new javax.swing.JLabel();
        panelNAO = new javax.swing.JPanel();
        labelNAO = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(400, 230));
        setMinimumSize(new java.awt.Dimension(400, 230));
        setUndecorated(true);
        setPreferredSize(new java.awt.Dimension(400, 230));
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelPagina.setBackground(new java.awt.Color(255, 255, 255));
        panelPagina.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelTop.setBackground(new java.awt.Color(97, 99, 103));
        panelTop.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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
        });
        panelFechar.add(labelFechar, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 15, 20, 20));

        panelTop.add(panelFechar, new org.netbeans.lib.awtextra.AbsoluteConstraints(348, 0, 50, 50));

        labelTitulo.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        labelTitulo.setForeground(new java.awt.Color(255, 255, 255));
        labelTitulo.setText("ERRO");
        panelTop.add(labelTitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 10, 180, 30));

        labelImagem.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelImagem.setIcon(new javax.swing.ImageIcon("src/main/java/Recursos/aviso.png"));
        labelImagem.setToolTipText("");
        panelTop.add(labelImagem, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 30, 30));

        panelPagina.add(panelTop, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 400, 50));

        labelMensagem.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        labelMensagem.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelMensagem.setText("jLabel1");
        panelPagina.add(labelMensagem, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 380, 100));

        panelSIM.setBackground(new java.awt.Color(51, 121, 232));
        panelSIM.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelSIMMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                panelSIMMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                panelSIMMouseExited(evt);
            }
        });

        labelSIM.setBackground(new java.awt.Color(255, 255, 255));
        labelSIM.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        labelSIM.setForeground(new java.awt.Color(255, 255, 255));
        labelSIM.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelSIM.setText("SIM");
        labelSIM.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelSIMpanelOKMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                labelSIMpanelOKMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                labelSIMpanelOKMouseExited(evt);
            }
        });

        javax.swing.GroupLayout panelSIMLayout = new javax.swing.GroupLayout(panelSIM);
        panelSIM.setLayout(panelSIMLayout);
        panelSIMLayout.setHorizontalGroup(
            panelSIMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelSIMLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelSIM, javax.swing.GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelSIMLayout.setVerticalGroup(
            panelSIMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(labelSIM, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
        );

        panelPagina.add(panelSIM, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 180, 100, 30));

        panelNAO.setBackground(new java.awt.Color(51, 121, 232));
        panelNAO.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelNAOMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                panelNAOMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                panelNAOMouseExited(evt);
            }
        });

        labelNAO.setBackground(new java.awt.Color(255, 255, 255));
        labelNAO.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        labelNAO.setForeground(new java.awt.Color(255, 255, 255));
        labelNAO.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelNAO.setText("NÃO");
        labelNAO.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelNAOpanelOKMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                labelNAOpanelOKMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                labelNAOpanelOKMouseExited(evt);
            }
        });

        javax.swing.GroupLayout panelNAOLayout = new javax.swing.GroupLayout(panelNAO);
        panelNAO.setLayout(panelNAOLayout);
        panelNAOLayout.setHorizontalGroup(
            panelNAOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelNAOLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelNAO, javax.swing.GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelNAOLayout.setVerticalGroup(
            panelNAOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(labelNAO, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
        );

        panelPagina.add(panelNAO, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 180, -1, -1));

        getContentPane().add(panelPagina, new org.netbeans.lib.awtextra.AbsoluteConstraints(1, 1, 398, 228));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void labelFecharpanelFecharMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelFecharpanelFecharMouseClicked
        this.dispose();
    }//GEN-LAST:event_labelFecharpanelFecharMouseClicked

    private void labelFecharpanelFecharMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelFecharpanelFecharMouseEntered
        panelFechar.setBackground(COR_HOVER_FECHAR);
    }//GEN-LAST:event_labelFecharpanelFecharMouseEntered

    private void labelFecharpanelFecharMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelFecharpanelFecharMouseExited
        panelFechar.setBackground(COR_NORMAL_FECHAR);
    }//GEN-LAST:event_labelFecharpanelFecharMouseExited

    private void panelFecharMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelFecharMouseClicked
        this.dispose();
    }//GEN-LAST:event_panelFecharMouseClicked

    private void panelFecharMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelFecharMouseEntered
        panelFechar.setBackground(COR_HOVER_FECHAR);
    }//GEN-LAST:event_panelFecharMouseEntered

    private void panelFecharMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelFecharMouseExited
        panelFechar.setBackground(COR_NORMAL_FECHAR);
    }//GEN-LAST:event_panelFecharMouseExited

    private void labelSIMpanelOKMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelSIMpanelOKMouseClicked
        selecionouSim = true;
        this.dispose();
    }//GEN-LAST:event_labelSIMpanelOKMouseClicked

    private void labelSIMpanelOKMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelSIMpanelOKMouseEntered
        panelSIM.setBackground(COR_OK_HOVER);
    }//GEN-LAST:event_labelSIMpanelOKMouseEntered

    private void labelSIMpanelOKMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelSIMpanelOKMouseExited
        panelSIM.setBackground(COR_OK_NORMAL);
    }//GEN-LAST:event_labelSIMpanelOKMouseExited

    private void panelSIMMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelSIMMouseClicked
        selecionouSim = true;
        this.dispose();
    }//GEN-LAST:event_panelSIMMouseClicked

    private void panelSIMMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelSIMMouseEntered
        panelSIM.setBackground(COR_OK_HOVER);
    }//GEN-LAST:event_panelSIMMouseEntered

    private void panelSIMMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelSIMMouseExited
        panelSIM.setBackground(COR_OK_NORMAL);
    }//GEN-LAST:event_panelSIMMouseExited

    private void labelNAOpanelOKMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelNAOpanelOKMouseClicked
        selecionouSim = false;
        this.dispose();
    }//GEN-LAST:event_labelNAOpanelOKMouseClicked

    private void labelNAOpanelOKMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelNAOpanelOKMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_labelNAOpanelOKMouseEntered

    private void labelNAOpanelOKMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelNAOpanelOKMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_labelNAOpanelOKMouseExited

    private void panelNAOMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelNAOMouseClicked
        selecionouSim = false;
        this.dispose();
    }//GEN-LAST:event_panelNAOMouseClicked

    private void panelNAOMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelNAOMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_panelNAOMouseEntered

    private void panelNAOMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelNAOMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_panelNAOMouseExited

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(PaginaOpcao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PaginaOpcao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PaginaOpcao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PaginaOpcao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                PaginaOpcao dialog = new PaginaOpcao(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel labelFechar;
    private javax.swing.JLabel labelImagem;
    private javax.swing.JLabel labelMensagem;
    private javax.swing.JLabel labelNAO;
    private javax.swing.JLabel labelSIM;
    private javax.swing.JLabel labelTitulo;
    private javax.swing.JPanel panelFechar;
    private javax.swing.JPanel panelNAO;
    private javax.swing.JPanel panelPagina;
    private javax.swing.JPanel panelSIM;
    private javax.swing.JPanel panelTop;
    // End of variables declaration//GEN-END:variables
}

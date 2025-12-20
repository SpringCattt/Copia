package VIEWS;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Point;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 *
 * @author filip
 */
public class PaginaDialogo extends javax.swing.JDialog {

    private final Color COR_NORMAL_FECHAR = new Color(97, 99, 103);
    private final Color COR_HOVER_FECHAR = new Color(193, 18, 31);
    private final Color COR_OK_NORMAL = new Color(51, 121, 232);
    private final Color COR_OK_HOVER = new Color(96, 150, 237);
    private final Color COR_OK_SELECIONADO = new Color(66, 106, 169);
    
    public PaginaDialogo(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        estiloJanela();
        initComponents();
    }

    public void estiloJanela() {
        JPanel contentPane = new JPanel();
        contentPane.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        contentPane.setLayout(new BorderLayout());
        setContentPane(contentPane);
    }
    
    public void setMensagem(String texto, String top, String imagem) {
        labelMensagem.setText("<html><body style='text-align: center; width: 250px;'>" 
                              + texto.replace("\n", "<br>") + "</body></html>");

        labelTitulo.setText(top);
        
        try {
            labelImagem.setIcon(new ImageIcon(imagem));
        } catch (Exception e) {
            System.err.println("Erro ao carregar imagem: " + e.getMessage());
        }
    }

    public void setIcone(String caminhoImagem) {
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
        panelOK = new javax.swing.JPanel();
        labelOK = new javax.swing.JLabel();

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
                panelFecharMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                panelFecharMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                panelFecharMouseExited(evt);
            }
        });
        panelFechar.add(labelFechar, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 15, 20, 20));

        panelTop.add(panelFechar, new org.netbeans.lib.awtextra.AbsoluteConstraints(348, 0, 50, 50));

        labelTitulo.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        labelTitulo.setForeground(new java.awt.Color(255, 255, 255));
        labelTitulo.setText("ERRO");
        panelTop.add(labelTitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 10, 180, 30));

        labelImagem.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelImagem.setToolTipText("");
        panelTop.add(labelImagem, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 30, 30));

        panelPagina.add(panelTop, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 400, 50));

        labelMensagem.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        labelMensagem.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelMensagem.setText("jLabel1");
        panelPagina.add(labelMensagem, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 380, 100));

        panelOK.setBackground(new java.awt.Color(51, 121, 232));
        panelOK.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelOKMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                panelOKMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                panelOKMouseExited(evt);
            }
        });

        labelOK.setBackground(new java.awt.Color(255, 255, 255));
        labelOK.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        labelOK.setForeground(new java.awt.Color(255, 255, 255));
        labelOK.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelOK.setText("OK");
        labelOK.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelOKMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                panelOKMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                panelOKMouseExited(evt);
            }
        });

        javax.swing.GroupLayout panelOKLayout = new javax.swing.GroupLayout(panelOK);
        panelOK.setLayout(panelOKLayout);
        panelOKLayout.setHorizontalGroup(
            panelOKLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelOKLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelOK, javax.swing.GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelOKLayout.setVerticalGroup(
            panelOKLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(labelOK, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
        );

        panelPagina.add(panelOK, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 180, 100, 30));

        getContentPane().add(panelPagina, new org.netbeans.lib.awtextra.AbsoluteConstraints(1, 1, 398, 228));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void panelFecharMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelFecharMouseEntered
        panelFechar.setBackground(COR_HOVER_FECHAR);
    }//GEN-LAST:event_panelFecharMouseEntered

    private void panelFecharMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelFecharMouseExited
        panelFechar.setBackground(COR_NORMAL_FECHAR);
    }//GEN-LAST:event_panelFecharMouseExited

    private void panelFecharMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelFecharMouseClicked
        this.dispose();
    }//GEN-LAST:event_panelFecharMouseClicked

    private void panelOKMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelOKMouseEntered
        panelOK.setBackground(COR_OK_HOVER);
    }//GEN-LAST:event_panelOKMouseEntered

    private void panelOKMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelOKMouseExited
        panelOK.setBackground(COR_OK_NORMAL);
    }//GEN-LAST:event_panelOKMouseExited

    private void panelOKMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelOKMouseClicked
        this.dispose();
    }//GEN-LAST:event_panelOKMouseClicked

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
            java.util.logging.Logger.getLogger(PaginaDialogo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PaginaDialogo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PaginaDialogo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PaginaDialogo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                PaginaDialogo dialog = new PaginaDialogo(new javax.swing.JFrame(), true);
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
    private javax.swing.JLabel labelOK;
    private javax.swing.JLabel labelTitulo;
    private javax.swing.JPanel panelFechar;
    private javax.swing.JPanel panelOK;
    private javax.swing.JPanel panelPagina;
    private javax.swing.JPanel panelTop;
    // End of variables declaration//GEN-END:variables
}

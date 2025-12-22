package VIEWS;

import CONTROLLERS.HomeController;
import MODELS.CLASS.CategoriaTrabalho;
import MODELS.CLASS.Espaco;
import MODELS.CLASS.Sala;
import java.util.List;

/**
 *
 * @author gonca
 */
public class PanelFormularioEspacos extends javax.swing.JPanel {

    private HomeController controller;
    private PaginaInicial janelaPrincipal;
    private String top, mensagem, imagem;
    java.awt.Window win = javax.swing.SwingUtilities.getWindowAncestor(this);

    /**
     * Creates new form PanelAdicionarFuncionario
     */
    public PanelFormularioEspacos(PaginaInicial paginaInicial, int idEspaco) {
        this.janelaPrincipal = paginaInicial;
        this.controller = new HomeController();
        initComponents();
        lblId.setVisible(false);
        carregarDados(idEspaco);
    }

    public PanelFormularioEspacos(PaginaInicial paginaInicial) {
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
        txtNomeEspaco = new javax.swing.JTextField();
        lblTitulo1 = new javax.swing.JLabel();
        lblId = new javax.swing.JLabel();

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
        add(btnGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 450, 150, 40));

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        add(btnCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 450, 150, 40));

        lblTitulo.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblTitulo.setText("Adicionar Espaço");
        add(lblTitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(46, 173, -1, -1));

        txtNomeEspaco.setText("Nome Espaço");
        txtNomeEspaco.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtNomeEspacoMouseClicked(evt);
            }
        });
        txtNomeEspaco.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNomeEspacoActionPerformed(evt);
            }
        });
        add(txtNomeEspaco, new org.netbeans.lib.awtextra.AbsoluteConstraints(46, 233, 659, 35));

        lblTitulo1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblTitulo1.setText("Nome do espaço:");
        add(lblTitulo1, new org.netbeans.lib.awtextra.AbsoluteConstraints(46, 211, -1, -1));

        lblId.setText("-1");
        add(lblId, new org.netbeans.lib.awtextra.AbsoluteConstraints(694, 73, -1, -1));
    }// </editor-fold>//GEN-END:initComponents

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        String nome = txtNomeEspaco.getText().trim();

        if (nome.isEmpty()) {
            PaginaDialogo dialogo = new PaginaDialogo((java.awt.Frame) win, true);

            mensagem = "Preencha todos os campos obrigatórios.";
            top = "Aviso";
            imagem = "src/main/java/Recursos/aviso.png";

            dialogo.setMensagem(mensagem, top, imagem);
            dialogo.setLocationRelativeTo(win);
            dialogo.setVisible(true);

            return;
        }
        int idAtual;
        try {
            idAtual = Integer.parseInt(lblId.getText());
        } catch (NumberFormatException e) {
            idAtual = -1; // Caso a label esteja vazia ou com texto inválido
        }

        if (idAtual == -1) {
            long novoId = controller.adicionarEspaco(nome, true);

            // 4. Feedback ao utilizador
            if (novoId > 0) {
                if (janelaPrincipal != null) {
                    txtNomeEspaco.setText("Nome Espaco");
                    janelaPrincipal.mostrarListaSalas();
                }
            } else {
                PaginaDialogo dialogo = new PaginaDialogo((java.awt.Frame) win, true);

                mensagem = "Erro ao inserir o espaço.";
                top = "Erro";
                imagem = "src/main/java/Recursos/erro.png";

                dialogo.setMensagem(mensagem, top, imagem);
                dialogo.setLocationRelativeTo(win);
                dialogo.setVisible(true);

                return;
            }
        } else {
            boolean novoId = controller.atualizarEspaco(idAtual, nome);

            if (novoId) {
                if (janelaPrincipal != null) {
                    txtNomeEspaco.setText("Nome Espaco");
                    janelaPrincipal.mostrarListaSalas();
                }
            } else {
                PaginaDialogo dialogo = new PaginaDialogo((java.awt.Frame) win, true);

                mensagem = "Erro ao editar o espaço.";
                top = "Erro";
                imagem = "src/main/java/Recursos/erro.png";

                dialogo.setMensagem(mensagem, top, imagem);
                dialogo.setLocationRelativeTo(win);
                dialogo.setVisible(true);

                return;
            }
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // Apenas pedimos à janela principal para mostrar a lista novamente
        if (janelaPrincipal != null) {
            txtNomeEspaco.setText("Nome Completo");
            janelaPrincipal.mostrarListaSalas();
        }
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void txtNomeEspacoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtNomeEspacoMouseClicked
        if ("Nome Espaço".equals(txtNomeEspaco.getText())) {
            txtNomeEspaco.setText("");
        }
    }//GEN-LAST:event_txtNomeEspacoMouseClicked

    private void txtNomeEspacoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNomeEspacoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNomeEspacoActionPerformed

    private void carregarDados(int idEspaco) {
        Espaco espaco = controller.procurarEspaco(idEspaco);

        if (espaco != null) {
            // Preenchimento básico
            lblId.setText(String.valueOf(espaco.getIdEspaco()));
            txtNomeEspaco.setText(espaco.getNome());
        } else {
            PaginaDialogo dialogo = new PaginaDialogo((java.awt.Frame) win, true);

            mensagem = "Erro ao carregar dados do Espaco.";
            top = "Erro";
            imagem = "src/main/java/Recursos/erro.png";

            dialogo.setMensagem(mensagem, top, imagem);
            dialogo.setLocationRelativeTo(win);
            dialogo.setVisible(true);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JLabel lblId;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JLabel lblTitulo1;
    private javax.swing.JTextField txtNomeEspaco;
    // End of variables declaration//GEN-END:variables
}

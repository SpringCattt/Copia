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
        lblTitulo.setText("Adicionar Espaço");

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

        lblTitulo1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblTitulo1.setText("Nome do espaço:");

        lblId.setText("-1");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblTitulo1)
                            .addComponent(lblTitulo))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(lblId))
                            .addComponent(txtNomeEspaco)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 381, Short.MAX_VALUE)
                                .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(35, 35, 35))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(73, 73, 73)
                .addComponent(lblId)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 104, Short.MAX_VALUE)
                .addComponent(lblTitulo)
                .addGap(18, 18, 18)
                .addComponent(lblTitulo1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNomeEspaco, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(180, 180, 180)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(42, 42, 42))
        );
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

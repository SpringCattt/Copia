package VIEWS;

import CONTROLLERS.HomeController;
import MODELS.CLASS.Espaco;
import MODELS.CLASS.Sala;
import java.util.List;
import javax.swing.SpinnerNumberModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author gonca
 */
public class PanelFormularioSalas extends javax.swing.JPanel {

    private HomeController controller;
    private PaginaInicial janelaPrincipal;
    private String top, mensagem, imagem;
    java.awt.Window win = javax.swing.SwingUtilities.getWindowAncestor(this);

    /**
     * Creates new form PanelAdicionarFuncionario
     */
    public PanelFormularioSalas(PaginaInicial paginaInicial, int idsala) {
        this.janelaPrincipal = paginaInicial;
        this.controller = new HomeController();
        initComponents();
        lblId.setVisible(false);
        SpinnerNumberModel model = new SpinnerNumberModel(1, 1, 999, 1);
        SpnQuantos.setModel(model);
        lblQuantos.setVisible(false);
        SpnQuantos.setVisible(false);
        List<Espaco> espacos = controller.obterTodosEspacos();

        for (Espaco e : espacos) {
            comboEspaco.addItem(e);
        }
        carregarDados(idsala);
    }

    public PanelFormularioSalas(PaginaInicial paginaInicial) {
        this.janelaPrincipal = paginaInicial;
        this.controller = new HomeController();
        initComponents();
        lblId.setVisible(false);
        SpinnerNumberModel model = new SpinnerNumberModel(1, 1, 999, 1);
        SpnQuantos.setModel(model);
        lblQuantos.setVisible(false);
        SpnQuantos.setVisible(false);
        List<Espaco> espacos = controller.obterTodosEspacos();

        for (Espaco e : espacos) {
            comboEspaco.addItem(e);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnGuardar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        txtNomeSala = new javax.swing.JTextField();
        checkLugares = new javax.swing.JCheckBox();
        comboEspaco = new javax.swing.JComboBox<>();
        lblQuantos = new javax.swing.JLabel();
        lblTitulo1 = new javax.swing.JLabel();
        lblTitulo2 = new javax.swing.JLabel();
        lblTitulo3 = new javax.swing.JLabel();
        lblTitulo4 = new javax.swing.JLabel();
        SpnQuantos = new javax.swing.JSpinner();
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
        add(btnGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(555, 448, 150, 40));

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        add(btnCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(387, 448, 150, 40));

        txtNomeSala.setText("Nome Sala");
        txtNomeSala.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtNomeSalaMouseClicked(evt);
            }
        });
        txtNomeSala.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNomeSalaActionPerformed(evt);
            }
        });
        add(txtNomeSala, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 180, 638, 40));

        checkLugares.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkLugaresActionPerformed(evt);
            }
        });
        add(checkLugares, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 370, -1, -1));

        add(comboEspaco, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 280, 300, 40));

        lblQuantos.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblQuantos.setText("-Quantos?");
        add(lblQuantos, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 370, -1, -1));

        lblTitulo1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblTitulo1.setText("Adicionar Sala");
        add(lblTitulo1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 100, -1, -1));

        lblTitulo2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblTitulo2.setText("Nome Sala:");
        add(lblTitulo2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 150, -1, 20));

        lblTitulo3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblTitulo3.setText("Tipo de Espaço");
        add(lblTitulo3, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 250, -1, -1));

        lblTitulo4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblTitulo4.setText("A sala tem lugares?");
        add(lblTitulo4, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 370, -1, -1));
        add(SpnQuantos, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 360, 90, 40));

        lblId.setText("-1");
        add(lblId, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 10, 50, 30));
    }// </editor-fold>//GEN-END:initComponents

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        try {
            String nome = txtNomeSala.getText().trim();

            Espaco espacoSelecionado = (Espaco) comboEspaco.getSelectedItem();
            int idEspaco = 0;

            if (espacoSelecionado != null) {
                idEspaco = espacoSelecionado.getIdEspaco();
            }

            int lugares = 0;
            boolean temLugares = checkLugares.isSelected();
            if (temLugares) {
                lugares = (Integer) SpnQuantos.getValue();
                if (lugares < 0) {
                    PaginaDialogo dialogo = new PaginaDialogo((java.awt.Frame) win, true);

                    mensagem = "Número inválido de lugares.";
                    top = "Erro";
                    imagem = "src/main/java/Recursos/erro.png";

                    dialogo.setMensagem(mensagem, top, imagem);
                    dialogo.setLocationRelativeTo(win);
                    dialogo.setVisible(true);
                    return;
                }
            }

            if (nome.isEmpty() || espacoSelecionado == null) {
                PaginaDialogo dialogo = new PaginaDialogo((java.awt.Frame) win, true);

                mensagem = "Preencha todos os campos obrigatórios.";
                top = "Erro";
                imagem = "src/main/java/Recursos/erro.png";

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

                boolean salacriada = controller.insertSala(nome, idEspaco, lugares, temLugares, true, false);

                if (salacriada) {
                    if (janelaPrincipal != null) {
                        txtNomeSala.setText("Nome Sala");

                        checkLugares.setSelected(false);

                        lblQuantos.setVisible(false);
                        SpnQuantos.setVisible(false);
                        lblId.setText("-1");

                        janelaPrincipal.mostrarListaSalas();

                        this.revalidate();
                        this.repaint();
                    }
                } else {
                    PaginaDialogo dialogo = new PaginaDialogo((java.awt.Frame) win, true);

                    mensagem = "Erro ao inserir sala.";
                    top = "Erro";
                    imagem = "src/main/java/Recursos/erro.png";

                    dialogo.setMensagem(mensagem, top, imagem);
                    dialogo.setLocationRelativeTo(win);
                    dialogo.setVisible(true);
                    return;
                }
            } else {
                boolean sucesso = controller.atualizarSala(idAtual, nome, idEspaco, lugares, temLugares);

                if (sucesso) {
                    if (janelaPrincipal != null) {
                        txtNomeSala.setText("Nome Sala");

                        checkLugares.setSelected(false);

                        lblQuantos.setVisible(false);
                        SpnQuantos.setVisible(false);
                        lblId.setText("-1");

                        janelaPrincipal.mostrarListaSalas();

                        this.revalidate();
                        this.repaint();
                    }
                } else {
                    PaginaDialogo dialogo = new PaginaDialogo((java.awt.Frame) win, true);

                    mensagem = "Erro ao atualizar a base de dados.";
                    top = "Erro";
                    imagem = "src/main/java/Recursos/erro.png";

                    dialogo.setMensagem(mensagem, top, imagem);
                    dialogo.setLocationRelativeTo(win);
                    dialogo.setVisible(true);
                    return;
                }
            }

        } catch (Exception e) {
            PaginaDialogo dialogo = new PaginaDialogo((java.awt.Frame) win, true);

            mensagem = "Erro nos dados introduzidos: " + e.getMessage();
            top = "Erro";
            imagem = "src/main/java/Recursos/erro.png";

            dialogo.setMensagem(mensagem, top, imagem);
            dialogo.setLocationRelativeTo(win);
            dialogo.setVisible(true);
            return;
        }
    }//GEN-LAST:event_btnGuardarActionPerformed


    private void txtNomeSalaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNomeSalaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNomeSalaActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // Apenas pedimos à janela principal para mostrar a lista novamente
        if (janelaPrincipal != null) {
            janelaPrincipal.mostrarListaSalas();
        }
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void txtNomeSalaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtNomeSalaMouseClicked
        if ("Nome Sala".equals(txtNomeSala.getText())) {
            txtNomeSala.setText("");
        }

    }//GEN-LAST:event_txtNomeSalaMouseClicked

    private void checkLugaresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkLugaresActionPerformed
        // Obtemos o estado atual (se um está true, o outro também deverá estar)
        boolean novoEstado = !lblQuantos.isVisible();

        lblQuantos.setVisible(novoEstado);
        SpnQuantos.setVisible(novoEstado);

        this.revalidate();
        this.repaint();
    }//GEN-LAST:event_checkLugaresActionPerformed

    private void carregarDados(int idSala) {
        Sala sala = controller.getSalaPorId(idSala);

        if (sala != null) {
            // Preenchimento básico
            lblId.setText(String.valueOf(sala.getIdSala()));
            txtNomeSala.setText(sala.getNome());
            for (int i = 0; i < comboEspaco.getItemCount(); i++) {
                Espaco item = comboEspaco.getItemAt(i);

                if (item.getIdEspaco() == sala.getTipoEspaco()) {
                    comboEspaco.setSelectedIndex(i);
                }
            }

            boolean temLugares = sala.isTemLugares();
            checkLugares.setSelected(temLugares);

            if (temLugares) {
                lblQuantos.setVisible(true);
                SpnQuantos.setVisible(true);
                SpnQuantos.setValue(sala.getLugares());
            }

        } else {
            PaginaDialogo dialogo = new PaginaDialogo((java.awt.Frame) win, true);

            mensagem = "Erro ao carregar dados da sala.";
            top = "Erro";
            imagem = "src/main/java/Recursos/erro.png";

            dialogo.setMensagem(mensagem, top, imagem);
            dialogo.setLocationRelativeTo(win);
            dialogo.setVisible(true);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JSpinner SpnQuantos;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JCheckBox checkLugares;
    private javax.swing.JComboBox<Espaco> comboEspaco;
    private javax.swing.JLabel lblId;
    private javax.swing.JLabel lblQuantos;
    private javax.swing.JLabel lblTitulo1;
    private javax.swing.JLabel lblTitulo2;
    private javax.swing.JLabel lblTitulo3;
    private javax.swing.JLabel lblTitulo4;
    private javax.swing.JTextField txtNomeSala;
    // End of variables declaration//GEN-END:variables
}

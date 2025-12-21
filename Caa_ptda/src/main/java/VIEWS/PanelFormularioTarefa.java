package VIEWS;

import CONTROLLERS.HomeController;
import MODELS.CLASS.Espaco;
import MODELS.CLASS.Evento;
import MODELS.CLASS.Sala;
import MODELS.CLASS.Trabalhador;
import java.util.List;
import javax.swing.SpinnerNumberModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author gonca
 */
public class PanelFormularioTarefa extends javax.swing.JPanel {

    private HomeController controller;
    private PaginaInicial janelaPrincipal;
    private String top, mensagem, imagem;
    java.awt.Window win = javax.swing.SwingUtilities.getWindowAncestor(this);

    public PanelFormularioTarefa(PaginaInicial paginaInicial) {
        this.janelaPrincipal = paginaInicial;
        this.controller = new HomeController();
        initComponents();
        carregarCombos();
    }

    private void carregarCombos() {
        comboStaff.removeAllItems();
        List<Trabalhador> trabalhadores = controller.obterTrabalhadorEspecifico("Staff");
        for (Trabalhador t : trabalhadores) {
            comboStaff.addItem(t);
        }

        comboEvento.removeAllItems();
        List<Evento> eventos = controller.listarEventosDisponiveis();
        for (Evento e : eventos) {
            comboEvento.addItem(e);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnGuardar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        txtDescricao = new javax.swing.JTextField();
        txtTitulo = new javax.swing.JTextField();
        comboEvento = new javax.swing.JComboBox<>();
        lblTitulo1 = new javax.swing.JLabel();
        lblTitulo2 = new javax.swing.JLabel();
        lblTitulo3 = new javax.swing.JLabel();
        lblTitulo4 = new javax.swing.JLabel();
        lblTitulo5 = new javax.swing.JLabel();
        comboStaff = new javax.swing.JComboBox<>();

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

        txtDescricao.setText("Descricao");
        txtDescricao.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtDescricaoMouseClicked(evt);
            }
        });
        txtDescricao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDescricaoActionPerformed(evt);
            }
        });
        add(txtDescricao, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 240, 638, 35));

        txtTitulo.setText("Titulo Tarefa");
        txtTitulo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtTituloMouseClicked(evt);
            }
        });
        txtTitulo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTituloActionPerformed(evt);
            }
        });
        add(txtTitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 160, 638, 35));

        add(comboEvento, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 330, 310, 35));

        lblTitulo1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblTitulo1.setText("Adicionar Tarefa");
        add(lblTitulo1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 80, -1, -1));

        lblTitulo2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblTitulo2.setText("Titulo da Tarefa");
        add(lblTitulo2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 130, -1, -1));

        lblTitulo3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblTitulo3.setText("Evento");
        add(lblTitulo3, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 300, -1, -1));

        lblTitulo4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblTitulo4.setText("Descrição");
        add(lblTitulo4, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 210, -1, -1));

        lblTitulo5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblTitulo5.setText("Staff");
        add(lblTitulo5, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 300, -1, -1));

        add(comboStaff, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 330, 300, 35));
    }// </editor-fold>//GEN-END:initComponents

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed

        Trabalhador staffSelecionado = (Trabalhador) comboStaff.getSelectedItem();
        int idStaff = (staffSelecionado != null) ? staffSelecionado.getIdTrabalhador() : 0;

        Evento eventoSelecionado = (Evento) comboEvento.getSelectedItem();
        int idEvento = (eventoSelecionado != null) ? eventoSelecionado.getIdEvento() : 0;

        String titulo = txtTitulo.getText().trim();
        String descricao = txtDescricao.getText().trim();

        if (titulo.isEmpty() || idStaff == 0 || idEvento == 0) {
            PaginaDialogo dialogo = new PaginaDialogo((java.awt.Frame) win, true);

            mensagem = "Preencha todos os campos obrigatórios.";
            top = "Erro";
            imagem = "src/main/java/Recursos/erro.png";

            dialogo.setMensagem(mensagem, top, imagem);
            dialogo.setLocationRelativeTo(win);
            dialogo.setVisible(true);
            return;
        }

        long novoId = controller.criarTarefa(titulo, descricao, idStaff, idEvento);

        if (novoId > 0) {
            txtTitulo.setText("Titulo Tarefa");
            txtDescricao.setText("Descrição");
            comboStaff.setSelectedIndex(-1);
            if (janelaPrincipal != null) {
                janelaPrincipal.mostrarListaEventos();
            }

        } else {
            PaginaDialogo dialogo = new PaginaDialogo((java.awt.Frame) win, true);

            mensagem = "Erro ao inserir a tarefa na base de dados.";
            top = "Erro";
            imagem = "src/main/java/Recursos/erro.png";

            dialogo.setMensagem(mensagem, top, imagem);
            dialogo.setLocationRelativeTo(win);
            dialogo.setVisible(true);
        }
    }//GEN-LAST:event_btnGuardarActionPerformed


    private void txtTituloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTituloActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTituloActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // Apenas pedimos à janela principal para mostrar a lista novamente
        if (janelaPrincipal != null) {
            janelaPrincipal.mostrarListaEventos();
        }
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void txtTituloMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtTituloMouseClicked
        if ("Titulo Tarefa".equals(txtTitulo.getText())) {
            txtTitulo.setText("");
        }

    }//GEN-LAST:event_txtTituloMouseClicked

    private void txtDescricaoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtDescricaoMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDescricaoMouseClicked

    private void txtDescricaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDescricaoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDescricaoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JComboBox<Evento> comboEvento;
    private javax.swing.JComboBox<Trabalhador> comboStaff;
    private javax.swing.JLabel lblTitulo1;
    private javax.swing.JLabel lblTitulo2;
    private javax.swing.JLabel lblTitulo3;
    private javax.swing.JLabel lblTitulo4;
    private javax.swing.JLabel lblTitulo5;
    private javax.swing.JTextField txtDescricao;
    private javax.swing.JTextField txtTitulo;
    // End of variables declaration//GEN-END:variables
}

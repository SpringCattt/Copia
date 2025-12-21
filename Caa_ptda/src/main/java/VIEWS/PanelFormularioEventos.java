package VIEWS;

import CONTROLLERS.HomeController;
import MODELS.CLASS.Evento;
import MODELS.CLASS.Sala;
import MODELS.CLASS.Trabalhador;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.text.SimpleDateFormat;

public class PanelFormularioEventos extends javax.swing.JPanel {

    private HomeController controller;
    private PaginaInicial janelaPrincipal;
    private int idEventoEditando = -1;

    // Listas auxiliares para gestionar los objetos reales
    private List<Sala> listaSalas;
    private List<Trabalhador> listaResponsaveis;

    public PanelFormularioEventos(PaginaInicial paginaInicial) {
        this.janelaPrincipal = paginaInicial;
        this.controller = new HomeController();
        
        // Inicializamos las listas
        this.listaSalas = new ArrayList<>();
        this.listaResponsaveis = new ArrayList<>();
        
        initComponents();
        txtNome.setText("Nome Evento");
        txtDescricao.setText("Descrição");
        configurarRelogio();
        carregarCombos();
    }
    
    private void configurarRelogio() {
        javax.swing.JSpinner.DateEditor editor = new javax.swing.JSpinner.DateEditor(spinHoraInicio, "HH:mm");
        spinHoraInicio.setEditor(editor);
        spinHoraInicio.setValue(new java.util.Date());
    }

    private void carregarCombos() {
        comboSala.removeAllItems();
        listaSalas = controller.listarTodasSalasAtivas(); 
        
        for (Sala s : listaSalas) {
            comboSala.addItem(s.getNome());
        }
        

        comboResponsavel.removeAllItems();
        listaResponsaveis = controller.obterTrabalhadorEspecifico("Gestor Eventos");
        if(listaResponsaveis.isEmpty()) {
            listaResponsaveis = controller.obterTodosFuncionarios();
        }
        
        for (Trabalhador t : listaResponsaveis) {
            comboResponsavel.addItem(t.getNome()); 
        }
    }
    
    private void mostrarAviso(String msg, String titulo, String img) {
        java.awt.Frame parent = (java.awt.Frame) javax.swing.SwingUtilities.getWindowAncestor(this);
        PaginaDialogo d = new PaginaDialogo(parent, true);
        d.setMensagem(msg, titulo, img);
        d.setVisible(true);
    }

    public void preencherDados(Evento e) {
        this.idEventoEditando = e.getIdEvento();
        btnGuardar.setText("Atualizar");
        
        txtID.setText(String.valueOf(e.getIdEvento()));
        txtNome.setText(e.getNome());
        txtDescricao.setText(e.getDescricao());
        
        if (e.getData() != null) dateEvento.setDate(e.getData());
        if (e.getHora() != null) spinHoraInicio.setValue(e.getHora());
        
        // Seleccionar Sala correcta
        if (listaSalas != null) {
            for (int i = 0; i < listaSalas.size(); i++) {
                if (listaSalas.get(i).getIdSala() == e.getSala()) {
                    comboSala.setSelectedIndex(i);
                    break;
                }
            }
        }
        
        // Seleccionar Responsable correcto
        if (listaResponsaveis != null) {
            for (int i = 0; i < listaResponsaveis.size(); i++) {
                if (listaResponsaveis.get(i).getIdTrabalhador() == e.getResponsavel()) {
                    comboResponsavel.setSelectedIndex(i);
                    break;
                }
            }
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtID = new javax.swing.JTextField();
        txtNome = new javax.swing.JTextField();
        txtDescricao = new javax.swing.JTextField();
        btnCancelar = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        comboSala = new javax.swing.JComboBox<>();
        comboResponsavel = new javax.swing.JComboBox<>();
        dateEvento = new com.toedter.calendar.JDateChooser();
        spinHoraInicio = new javax.swing.JSpinner();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        spinDuracao = new javax.swing.JSpinner();
        lblDuracao = new javax.swing.JLabel();

        setBackground(new java.awt.Color(232, 235, 238));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtID.setText("ID");
        txtID.setEnabled(false);
        add(txtID, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 6, -1, 35));

        txtNome.setText("Nome Evento");
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
        add(txtNome, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 110, 638, 35));

        txtDescricao.setText("Descrição");
        txtDescricao.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtDescricaoMouseClicked(evt);
            }
        });
        add(txtDescricao, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 250, 638, 35));

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        add(btnCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(427, 468, 150, 40));

        btnGuardar.setText("Guardar");
        btnGuardar.setPreferredSize(new java.awt.Dimension(81, 23));
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        add(btnGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(595, 468, 150, 40));

        add(comboSala, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 390, 325, 35));

        comboResponsavel.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        add(comboResponsavel, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 320, 325, 35));
        add(dateEvento, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 180, -1, 35));

        spinHoraInicio.setModel(new javax.swing.SpinnerDateModel(new java.util.Date(), null, null, java.util.Calendar.HOUR_OF_DAY));
        spinHoraInicio.setEditor(new javax.swing.JSpinner.DateEditor(spinHoraInicio, "HH:mm"));
        add(spinHoraInicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 180, -1, 35));

        jLabel2.setText("Hora");
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 160, -1, -1));

        jLabel3.setText("Nome Evento");
        add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 90, -1, -1));

        jLabel4.setText("Sala");
        add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 370, -1, -1));

        jLabel5.setText("Data");
        add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 160, -1, -1));

        jLabel6.setText("Descrição");
        add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 230, -1, -1));

        jLabel7.setText("Responsável");
        add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 300, -1, -1));

        spinDuracao.setModel(new javax.swing.SpinnerDateModel(new java.util.Date(1766278800000L), null, null, java.util.Calendar.HOUR));
        spinDuracao.setEditor(new javax.swing.JSpinner.DateEditor(spinDuracao, "HH:mm"));
        add(spinDuracao, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 180, -1, 35));

        lblDuracao.setText("Duração");
        add(lblDuracao, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 160, -1, -1));
    }// </editor-fold>//GEN-END:initComponents

    private void txtNomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNomeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNomeActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // Apenas pedimos à janela principal para mostrar a lista novamente
        if (janelaPrincipal != null) {
            janelaPrincipal.mostrarListaEventos();
        }
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        String nome = txtNome.getText().trim();
        String descricao = txtDescricao.getText().trim();
        java.util.Date dataCal = dateEvento.getDate();
        java.util.Date horaVal = (java.util.Date) spinHoraInicio.getValue();

        // 1. RECOLHER DURAÇÃO
        java.util.Date duracaoVal = (java.util.Date) spinDuracao.getValue();

        int indexSala = comboSala.getSelectedIndex();
        int indexResp = comboResponsavel.getSelectedIndex();

        if (indexSala == -1 || indexResp == -1) {
            mostrarAviso("Selecione uma sala e um responsável.", "Erro", "src/main/java/Recursos/erro.png");
            return;
        }

        Sala sala = listaSalas.get(indexSala);
        Trabalhador resp = listaResponsaveis.get(indexResp);

        if (nome.isEmpty() || dataCal == null) {
            mostrarAviso("Preencha todos os campos obrigatórios.", "Erro", "src/main/java/Recursos/erro.png");
            return;
        }

        // Validação Data Futura
        Calendar calAtual = Calendar.getInstance();
        Calendar calSel = Calendar.getInstance();
        calSel.setTime(dataCal);

        Calendar calHora = Calendar.getInstance();
        calHora.setTime(horaVal);

        calSel.set(Calendar.HOUR_OF_DAY, calHora.get(Calendar.HOUR_OF_DAY));
        calSel.set(Calendar.MINUTE, calHora.get(Calendar.MINUTE));

        if (calSel.before(calAtual)) {
            mostrarAviso("A data e hora devem ser posteriores ao momento atual.", "Data Inválida", "src/main/java/Recursos/aviso.png");
            return;
        }

        // 2. FORMATAR DURAÇÃO
        SimpleDateFormat sdfData = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat sdfHora = new SimpleDateFormat("HH:mm");

        String dataFormatada = sdfData.format(dataCal);
        String horaFormatada = sdfHora.format(horaVal);
        String duracaoFormatada = sdfHora.format(duracaoVal); // Formata HH:mm

        boolean sucesso;

        // 3. PASSAR duracaoFormatada PARA O CONTROLLER
        if (idEventoEditando == -1) {
            sucesso = controller.criarEvento(nome, dataFormatada, descricao, resp.getIdTrabalhador(), sala.getIdSala(), horaFormatada, duracaoFormatada);
        } else {
            sucesso = controller.editarEvento(idEventoEditando, nome, dataFormatada, descricao, resp.getIdTrabalhador(), sala.getIdSala(), horaFormatada, duracaoFormatada);
        }

        if (sucesso) {
            mostrarAviso("Operação realizada com sucesso!", "Sucesso", "src/main/java/Recursos/info.png");
            if (janelaPrincipal != null) janelaPrincipal.mostrarListaEventos();
        } else {
            mostrarAviso("Erro ao guardar na base de dados.", "Erro", "src/main/java/Recursos/erro.png");
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void txtNomeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtNomeMouseClicked
       if(txtNome.equals("Nome Evento")){
           txtNome.setText(" ");
       }
    }//GEN-LAST:event_txtNomeMouseClicked

    private void txtDescricaoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtDescricaoMouseClicked
        if(txtDescricao.equals("Descrição")){
           txtDescricao.setText(" ");
       }
    }//GEN-LAST:event_txtDescricaoMouseClicked

    private void txtHoraActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JComboBox<String> comboResponsavel;
    private javax.swing.JComboBox<String> comboSala;
    private com.toedter.calendar.JDateChooser dateEvento;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel lblDuracao;
    private javax.swing.JSpinner spinDuracao;
    private javax.swing.JSpinner spinHoraInicio;
    private javax.swing.JTextField txtDescricao;
    private javax.swing.JTextField txtID;
    private javax.swing.JTextField txtNome;
    // End of variables declaration//GEN-END:variables
}

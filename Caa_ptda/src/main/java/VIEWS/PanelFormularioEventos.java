package VIEWS;

import CONTROLLERS.HomeController;
import MODELS.CLASS.Sala;
import MODELS.CLASS.Trabalhador;
import MODELS.CLASS.Evento;
import java.util.List;
import java.text.SimpleDateFormat;
import java.awt.Frame;
import javax.swing.SwingUtilities;

public class PanelFormularioEventos extends javax.swing.JPanel {

    private HomeController controller;
    private PaginaInicial janelaPrincipal;
    private int idEventoEditando = -1;

    public PanelFormularioEventos(PaginaInicial paginaInicial) {
        this.janelaPrincipal = paginaInicial;
        this.controller = new HomeController();
        initComponents();
        
        carregarCombos();
    }
    
    private void carregarCombos() {
        comboSala.removeAllItems();
        List<Sala> salas = controller.listarTodasSalasAtivas();
        for (Sala s : salas) {
            comboSala.addItem(s.getNome());
        }
    }
    
    public void preencherDados(Evento e) {
        this.idEventoEditando = e.getIdEvento();
        btnGuardar.setText("Atualizar");
        
        txtID.setText(String.valueOf(e.getIdEvento()));
        txtNome.setText(e.getNome());
        txtDescricao.setText(e.getDescricao());
        
        if (e.getData() != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            txtDate.setText(sdf.format(e.getData()));
        }
        
        if (e.getHora() != null) {
             SimpleDateFormat sdfHora = new SimpleDateFormat("HH:mm");
             txtHora.setText(sdfHora.format(e.getHora()));
        }
        
        // Seleccionar Sala
        Sala s = controller.buscarSalaPorId(e.getSala());
        if (s != null) {
            comboSala.setSelectedItem(s.getNome());
        }
        
        // Rellenar Responsable
        Trabalhador t = controller.buscarTrabalhadorPorId(e.getResponsavel());
        if (t != null) {
            txtResponsavel.setText(t.getNome());
        }
    }

    // --- MÉTODO AUXILIAR PARA MOSTRAR TUS POP-UPS ---
    private void mostrarMensagem(String mensagem, String titulo, boolean isErro) {
        Frame parent = (Frame) SwingUtilities.getWindowAncestor(this);
        PaginaDialogo dialogo = new PaginaDialogo(parent, true);
        
        // Configuramos el icono según sea error o éxito
        String iconoPath = isErro ? "src/main/java/Recursos/erro.png" : "src/main/java/Recursos/info.png";
        
        dialogo.setMensagem(mensagem, titulo, iconoPath);
        dialogo.setVisible(true);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtID = new javax.swing.JTextField();
        txtNome = new javax.swing.JTextField();
        txtDate = new javax.swing.JFormattedTextField();
        txtDescricao = new javax.swing.JTextField();
        btnCancelar = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        txtResponsavel = new javax.swing.JTextField();
        txtHora = new javax.swing.JFormattedTextField();
        jCheckBox1 = new javax.swing.JCheckBox();
        jLabel1 = new javax.swing.JLabel();
        comboSala = new javax.swing.JComboBox<>();

        setBackground(new java.awt.Color(232, 235, 238));

        txtID.setText("ID");
        txtID.setEnabled(false);

        txtNome.setText("Nome Completo");
        txtNome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNomeActionPerformed(evt);
            }
        });

        txtDate.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(java.text.DateFormat.getDateInstance(java.text.DateFormat.SHORT))));
        txtDate.setText("18/12/2025");
        txtDate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDateActionPerformed(evt);
            }
        });

        txtDescricao.setText("Descrição");

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        btnGuardar.setText("Guardar");
        btnGuardar.setPreferredSize(new java.awt.Dimension(81, 23));
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        txtResponsavel.setText("Responsável");
        txtResponsavel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtResponsavelActionPerformed(evt);
            }
        });

        txtHora.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(java.text.DateFormat.getTimeInstance(java.text.DateFormat.SHORT))));
        txtHora.setText("18:00");
        txtHora.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtHoraActionPerformed(evt);
            }
        });

        jLabel1.setText("Estado:");

        comboSala.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboSalaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(jCheckBox1))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(txtDate, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(txtHora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(txtDescricao)
                        .addComponent(txtNome)
                        .addComponent(txtResponsavel, javax.swing.GroupLayout.PREFERRED_SIZE, 638, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(comboSala, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(91, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(83, 83, 83)
                .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDate, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtHora, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(txtDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtResponsavel, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(comboSala, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jCheckBox1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 60, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(42, 42, 42))
        );
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
        String data = txtDate.getText().trim();
        String hora = txtHora.getText().trim();
        String descricao = txtDescricao.getText().trim();
        String nomeResponsavel = txtResponsavel.getText().trim();
        String nomeSala = (String) comboSala.getSelectedItem();

        // 1. Validaciones básicas
        if (nome.isEmpty() || data.isEmpty() || hora.isEmpty() || nomeSala == null || nomeResponsavel.isEmpty()) {
            mostrarMensagem("Por favor, preencha todos os campos obrigatórios.", "Campos Vazios", true);
            return;
        }
        
        // 2. Buscar IDs
        int idSala = -1;
        List<Sala> salas = controller.listarTodasSalasAtivas();
        for (Sala s : salas) {
            if (s.getNome().equals(nomeSala)) {
                idSala = s.getIdSala();
                break;
            }
        }
        
        int idResponsavel = -1;
        List<Trabalhador> trabalhadores = controller.obterTodosFuncionarios();
        for (Trabalhador t : trabalhadores) {
            if (t.getNome().equalsIgnoreCase(nomeResponsavel)) {
                idResponsavel = t.getIdTrabalhador();
                break;
            }
        }
        
        if (idResponsavel == -1) {
            mostrarMensagem("Responsável não encontrado com esse nome exato.", "Erro Responsável", true);
            return;
        }

        // 3. Guardar
        boolean sucesso;
        if (idEventoEditando == -1) {
            sucesso = controller.criarEvento(nome, data, descricao, idResponsavel, idSala, hora);
        } else {
            sucesso = controller.editarEvento(idEventoEditando, nome, data, descricao, idResponsavel, idSala, hora);
        }

        if (sucesso) {
            mostrarMensagem("Evento guardado com sucesso!", "Sucesso", false);
            if (janelaPrincipal != null) janelaPrincipal.mostrarListaEventos();
        } else {
            mostrarMensagem("Erro ao guardar.\nVerifique se a Data é (dd/MM/yyyy) e Hora (HH:mm).", "Erro Formato", true);
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void txtDateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDateActionPerformed

    private void txtResponsavelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtResponsavelActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtResponsavelActionPerformed

    private void comboSalaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboSalaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboSalaActionPerformed

    private void txtHoraActionPerformed(java.awt.event.ActionEvent evt) {                                        
        // TODO add your handling code here:
    } 

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JComboBox<String> comboSala;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JFormattedTextField txtDate;
    private javax.swing.JTextField txtDescricao;
    private javax.swing.JFormattedTextField txtHora;
    private javax.swing.JTextField txtID;
    private javax.swing.JTextField txtNome;
    private javax.swing.JTextField txtResponsavel;
    // End of variables declaration//GEN-END:variables
}

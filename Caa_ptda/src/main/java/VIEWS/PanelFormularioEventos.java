package VIEWS;

import CONTROLLERS.HomeController;
import MODELS.CLASS.Sala;
import MODELS.CLASS.Trabalhador;
import MODELS.CLASS.Evento;
import com.toedter.calendar.JDateChooser; 
import java.util.List;
import java.util.ArrayList; // Necesario para las listas auxiliares
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.JOptionPane;
import org.netbeans.lib.awtextra.AbsoluteLayout;
import org.netbeans.lib.awtextra.AbsoluteConstraints;

public class PanelFormularioEventos extends javax.swing.JPanel {

    private HomeController controller;
    private PaginaInicial janelaPrincipal;
    private int idEventoEditando = -1;

    // LISTAS AUXILIARES (Para guardar los objetos reales ya que los Combos son String)
    private List<Sala> listaSalas;
    private List<Trabalhador> listaResponsaveis;

    public PanelFormularioEventos(PaginaInicial paginaInicial) {
        this.janelaPrincipal = paginaInicial;
        this.controller = new HomeController();
        
        // Inicializamos las listas para evitar NullPointerException
        this.listaSalas = new ArrayList<>();
        this.listaResponsaveis = new ArrayList<>();
        
        initComponents(); // Método generado por NetBeans
        configurarRelogio();
        carregarCombos();
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

        setBackground(new java.awt.Color(232, 235, 238));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtID.setText("ID");
        txtID.setEnabled(false);
        add(txtID, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 6, -1, 35));

        txtNome.setText("Nome Completo");
        txtNome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNomeActionPerformed(evt);
            }
        });
        add(txtNome, new org.netbeans.lib.awtextra.AbsoluteConstraints(51, 124, 638, 35));

        txtDescricao.setText("Descrição");
        add(txtDescricao, new org.netbeans.lib.awtextra.AbsoluteConstraints(51, 230, 638, 35));

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

        add(comboSala, new org.netbeans.lib.awtextra.AbsoluteConstraints(51, 336, 325, 35));

        comboResponsavel.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        add(comboResponsavel, new org.netbeans.lib.awtextra.AbsoluteConstraints(51, 283, 325, 35));
        add(dateEvento, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 180, -1, 35));

        spinHoraInicio.setModel(new javax.swing.SpinnerDateModel(new java.util.Date(), null, null, java.util.Calendar.HOUR_OF_DAY));
        spinHoraInicio.setEditor(new javax.swing.JSpinner.DateEditor(spinHoraInicio, "HH:mm"));
        add(spinHoraInicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 180, -1, 35));
    }// </editor-fold>//GEN-END:initComponents
private void carregarCombos() {
        // 1. Cargar Salas
        comboSala.removeAllItems();
        listaSalas = controller.listarTodasSalasAtivas(); // Guardamos los objetos en la lista
        
        for (Sala s : listaSalas) {
            // Añadimos solo el TEXTO (String) al combo para evitar el error de tipos
            // Asegúrate de que tu clase Sala tenga un método toString() útil, o usa s.getNome()
            comboSala.addItem(s.toString()); 
        }
        
        // 2. Cargar Responsables
        comboResponsavel.removeAllItems();
        listaResponsaveis = controller.obterGestoresEventos();
        if(listaResponsaveis.isEmpty()) {
            listaResponsaveis = controller.obterTodosFuncionarios();
        }
        
        for (Trabalhador t : listaResponsaveis) {
            // Añadimos solo el TEXTO (String)
            comboResponsavel.addItem(t.toString()); 
        }
    }
    
    private void configurarRelogio() {
        javax.swing.JSpinner.DateEditor editor = new javax.swing.JSpinner.DateEditor(spinHoraInicio, "HH:mm");
        spinHoraInicio.setEditor(editor);
        spinHoraInicio.setValue(new java.util.Date());
    }
    
    // --- POP-UP ---
    private void mostrarAviso(String msg, String titulo, String img) {
        java.awt.Frame parent = (java.awt.Frame) javax.swing.SwingUtilities.getWindowAncestor(this);
        PaginaDialogo d = new PaginaDialogo(parent, true);
        d.setMensagem(msg, titulo, img);
        d.setVisible(true);
    }

    // --- PREENCHER PARA EDICAO ---
    public void preencherDados(Evento e) {
        this.idEventoEditando = e.getIdEvento();
        btnGuardar.setText("Atualizar");
        
        txtID.setText(String.valueOf(e.getIdEvento()));
        txtNome.setText(e.getNome());
        txtDescricao.setText(e.getDescricao());
        
        if (e.getData() != null) dateEvento.setDate(e.getData());
        if (e.getHora() != null) spinHoraInicio.setValue(e.getHora());
        
        // Seleccionar Sala buscando en la lista auxiliar por ID
        for (int i=0; i < listaSalas.size(); i++) {
            if (listaSalas.get(i).getIdSala() == e.getSala()) {
                comboSala.setSelectedIndex(i);
                break;
            }
        }
        
        // Seleccionar Responsable buscando en la lista auxiliar por ID
        for (int i=0; i < listaResponsaveis.size(); i++) {
            if (listaResponsaveis.get(i).getIdTrabalhador() == e.getResponsavel()) {
                comboResponsavel.setSelectedIndex(i);
                break;
            }
        }
    }
    
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
        
        Sala sala = (Sala) comboSala.getSelectedItem();
        Trabalhador resp = (Trabalhador) comboResponsavel.getSelectedItem();

        // 1. Validação Campos Obrigatórios
        if (nome.isEmpty() || dataCal == null || sala == null || resp == null) {
            mostrarAviso("Preencha todos os campos obrigatórios.", "Erro", "src/main/java/Recursos/erro.png");
            return;
        }

        // 2. Validação Data Futura
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

        java.sql.Date sqlDate = new java.sql.Date(dataCal.getTime());
        java.sql.Time sqlTime = new java.sql.Time(horaVal.getTime());

        boolean sucesso;
        
        // FIX 3: Converted dates to String using .toString() to match Controller requirements
        if (idEventoEditando == -1) {
            sucesso = controller.criarEvento(nome, sqlDate.toString(), descricao, resp.getIdTrabalhador(), sala.getIdSala(), sqlTime.toString());
        } else {
            sucesso = controller.editarEvento(idEventoEditando, nome, sqlDate.toString(), descricao, resp.getIdTrabalhador(), sala.getIdSala(), sqlTime.toString());
        }

        if (sucesso) {
            mostrarAviso("Operação realizada com sucesso!", "Sucesso", "src/main/java/Recursos/info.png");
            if (janelaPrincipal != null) janelaPrincipal.mostrarListaEventos();
        } else {
            mostrarAviso("Erro ao guardar na base de dados.", "Erro", "src/main/java/Recursos/erro.png");
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void txtHoraActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JComboBox<String> comboResponsavel;
    private javax.swing.JComboBox<String> comboSala;
    private com.toedter.calendar.JDateChooser dateEvento;
    private javax.swing.JSpinner spinHoraInicio;
    private javax.swing.JTextField txtDescricao;
    private javax.swing.JTextField txtID;
    private javax.swing.JTextField txtNome;
    // End of variables declaration//GEN-END:variables
}

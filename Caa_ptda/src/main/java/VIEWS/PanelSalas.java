package VIEWS;

import CONTROLLERS.HomeController;
import MODELS.CLASS.Espaco;
import MODELS.CLASS.Sala;
import java.awt.Color;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class PanelSalas extends javax.swing.JPanel {

    private PaginaInicial janelaPrincipal;
    private HomeController controller;
    private String top, mensagem, imagem;
    java.awt.Window win = javax.swing.SwingUtilities.getWindowAncestor(this);

    public PanelSalas(PaginaInicial janelaPrincipal) {
        this.janelaPrincipal = janelaPrincipal;
        this.controller = new HomeController();
        initComponents();
        
        tabelaSalas.getTableHeader().setResizingAllowed(false);
        tabelaSalas.getTableHeader().setReorderingAllowed(false);
        tabelaSalas.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        
        tabelaEspacos.getTableHeader().setResizingAllowed(false);
        tabelaEspacos.getTableHeader().setReorderingAllowed(false);
        tabelaEspacos.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        
        carregarDadosTabelaEspacos(null);
        carregarDadosTabelaSalas(null);
        
        btnAdicionar.setVisible(true);
        jScrollPane1.setVisible(true);
        jScrollPane2.setVisible(false);
        
        txtPesquisar.setText("Pesquisar");
        txtPesquisar.setForeground(java.awt.Color.GRAY);

        txtPesquisar.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                // Apenas apaga se o texto for o padrão e a cor for cinza (indica placeholder)
                if (txtPesquisar.getText().equals("Pesquisar") && txtPesquisar.getForeground().equals(java.awt.Color.GRAY)) {
                    txtPesquisar.setText("");
                    txtPesquisar.setForeground(java.awt.Color.BLACK);
                }
            }

            @Override
            public void focusLost(java.awt.event.FocusEvent evt) {
                if (txtPesquisar.getText().isEmpty()) {
                    txtPesquisar.setForeground(java.awt.Color.GRAY);
                    txtPesquisar.setText("Pesquisar");
                }
            }
        });
        
        txtPesquisar.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            @Override
            public void insertUpdate(javax.swing.event.DocumentEvent e) { verificarEFiltrar(); }
            @Override
            public void removeUpdate(javax.swing.event.DocumentEvent e) { verificarEFiltrar(); }
            @Override
            public void changedUpdate(javax.swing.event.DocumentEvent e) { verificarEFiltrar(); }
        });
    }
    
    private void verificarEFiltrar() {
        String termo = txtPesquisar.getText();

        if (txtPesquisar.getForeground().equals(java.awt.Color.GRAY) && termo.equals("Pesquisar")) {
            aplicarFiltro("");
        } else {
            aplicarFiltro(termo);
        }
    }

    private void aplicarFiltro(String termo) {
        // 1. Configurar Sorters para as duas tabelas
        DefaultTableModel modS = (DefaultTableModel) tabelaSalas.getModel();
        TableRowSorter<DefaultTableModel> sorterS = new TableRowSorter<>(modS);
        tabelaSalas.setRowSorter(sorterS);

        DefaultTableModel modE = (DefaultTableModel) tabelaEspacos.getModel();
        TableRowSorter<DefaultTableModel> sorterE = new TableRowSorter<>(modE);
        tabelaEspacos.setRowSorter(sorterE);

        if (termo.trim().isEmpty()) {
            sorterS.setRowFilter(null);
            sorterE.setRowFilter(null);
        } else {
            sorterS.setRowFilter(RowFilter.regexFilter("(?i)" + termo));
            sorterE.setRowFilter(RowFilter.regexFilter("(?i)" + termo));
        }
    }
    
    private void mostrarAviso(java.awt.Frame parent, String msg, String titulo, String imgPath) {
        PaginaDialogo dialogo = new PaginaDialogo(parent, true);
        dialogo.setMensagem(msg, titulo, imgPath);
        dialogo.setLocationRelativeTo(parent);
        dialogo.setVisible(true);
    }

    private void carregarDadosTabelaEspacos(List<Espaco> listaFornecida) {
        DefaultTableModel modelo = (DefaultTableModel) tabelaEspacos.getModel();
        modelo.setRowCount(0);

        List<Espaco> lista = (listaFornecida != null) ? listaFornecida : controller.obterTodosEspacos();

        for (Espaco e : lista) {
            modelo.addRow(new Object[]{e.getIdEspaco(), e.getNome()});
        }
    }

    private void carregarDadosTabelaSalas(List<Sala> listaFornecida) {
        DefaultTableModel modelo = (DefaultTableModel) tabelaSalas.getModel();
        modelo.setRowCount(0);

        List<Sala> lista = (listaFornecida != null) ? listaFornecida : controller.listarTodasSalasAtivas();

        for (Sala s : lista) {
            modelo.addRow(new Object[]{
                s.getIdSala(),
                s.getNome(),
                s.getTipoEspaco(),
                s.getLugares(),
                s.isOcupada() ? "Ocupada" : "Livre",
                s.isTemLugares() ? "Sim" : "Não"
            });
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtPesquisar = new javax.swing.JTextField();
        btnAdicionar = new javax.swing.JButton();
        btnDesativarSala = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        tabPaneSE = new javax.swing.JTabbedPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaSalas = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabelaEspacos = new javax.swing.JTable();

        setBackground(new java.awt.Color(232, 235, 238));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtPesquisar.setText("Pesquisar");
        txtPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPesquisarActionPerformed(evt);
            }
        });
        add(txtPesquisar, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 50, 180, 40));

        btnAdicionar.setBackground(new java.awt.Color(51, 121, 232));
        btnAdicionar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnAdicionar.setForeground(new java.awt.Color(255, 255, 255));
        btnAdicionar.setText("Adicionar");
        btnAdicionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdicionarActionPerformed(evt);
            }
        });
        add(btnAdicionar, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 490, 150, 40));

        btnDesativarSala.setBackground(new java.awt.Color(51, 121, 232));
        btnDesativarSala.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnDesativarSala.setForeground(new java.awt.Color(255, 255, 255));
        btnDesativarSala.setText("Desativar ");
        btnDesativarSala.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDesativarSalaActionPerformed(evt);
            }
        });
        add(btnDesativarSala, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 490, 150, 40));

        btnEditar.setBackground(new java.awt.Color(51, 121, 232));
        btnEditar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnEditar.setForeground(new java.awt.Color(255, 255, 255));
        btnEditar.setText("Editar");
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });
        add(btnEditar, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 490, 150, 40));

        tabelaSalas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nome", "Tipo", "Capacidade", "Ocupação", "TemLugares"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tabelaSalas);

        tabPaneSE.addTab("Salas", jScrollPane1);

        tabelaEspacos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nome"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tabelaEspacos);

        tabPaneSE.addTab("Espaços", jScrollPane2);

        add(tabPaneSE, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 100, 680, 380));
    }// </editor-fold>//GEN-END:initComponents

    private void txtPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPesquisarActionPerformed

    }//GEN-LAST:event_txtPesquisarActionPerformed

    private void btnAdicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdicionarActionPerformed
        int index = tabPaneSE.getSelectedIndex();
        if (index == 0 && janelaPrincipal != null) { 
            janelaPrincipal.irParaFormularioSalas();
        } else if (index == 1 && janelaPrincipal != null) { 
            janelaPrincipal.irParaFormularioEspacos();
        }
    }//GEN-LAST:event_btnAdicionarActionPerformed

    private void btnDesativarSalaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDesativarSalaActionPerformed
        java.awt.Frame parent = (java.awt.Frame) javax.swing.SwingUtilities.getWindowAncestor(this);
        int indexAba = tabPaneSE.getSelectedIndex(); 

        if (indexAba == 0) { // SALAS
            int linha = tabelaSalas.getSelectedRow();
            if (linha == -1) {
                mostrarAviso(parent, "Selecione uma sala para eliminar.", "Atenção", "src/main/java/Recursos/aviso.png");
                return;
            }
            int modelRow = tabelaSalas.convertRowIndexToModel(linha);
            int idSala = (int) tabelaSalas.getModel().getValueAt(modelRow, 0);
            String nomeSala = (String) tabelaSalas.getModel().getValueAt(modelRow, 1);

            if (controller.podeSerEliminada(idSala)) {
                mostrarAviso(parent, "Impossível eliminar: Esta sala possui eventos associados!", "Bloqueio", "src/main/java/Recursos/aviso.png");
                return;
            }

            PaginaOpcao confirmacao = new PaginaOpcao(parent, true);
            confirmacao.setMensagem("Deseja desativar a sala '" + nomeSala + "'?", "Desativar Sala");
            confirmacao.setVisible(true);

            if (confirmacao.clicouSim()) {
                if (controller.eliminarSala(idSala)) {
                    mostrarAviso(parent, "Sala desativada com sucesso.", "Sucesso", "src/main/java/Recursos/info.png");
                    carregarDadosTabelaSalas(null);
                } else {
                    mostrarAviso(parent, "Erro ao desativar sala.", "Erro", "src/main/java/Recursos/erro.png");
                }
            }
        } else if (indexAba == 1) { // ESPAÇOS
            int linha = tabelaEspacos.getSelectedRow();
            if (linha == -1) {
                mostrarAviso(parent, "Selecione um espaço para eliminar.", "Atenção", "src/main/java/Recursos/aviso.png");
                return;
            }
            int modelRow = tabelaEspacos.convertRowIndexToModel(linha);
            int idEspaco = (int) tabelaEspacos.getModel().getValueAt(modelRow, 0);
            String nomeEspaco = (String) tabelaEspacos.getModel().getValueAt(modelRow, 1);

            if (controller.temSalasVinculadas(idEspaco)) {
                mostrarAviso(parent, "Impossível eliminar: Este espaço possui salas associadas!", "Bloqueio", "src/main/java/Recursos/aviso.png");
                return;
            }

            PaginaOpcao confirmacao = new PaginaOpcao(parent, true);
            confirmacao.setMensagem("Deseja desativar o espaço '" + nomeEspaco + "'?", "Desativar Espaço");
            confirmacao.setVisible(true);

            if (confirmacao.clicouSim()) {
                if (controller.desativarEspaco(idEspaco)) {
                    mostrarAviso(parent, "Espaço desativado com sucesso.", "Sucesso", "src/main/java/Recursos/info.png");
                    carregarDadosTabelaEspacos(null);
                } else {
                    mostrarAviso(parent, "Erro ao desativar espaço.", "Erro", "src/main/java/Recursos/erro.png");
                }
            }
        }       
    }//GEN-LAST:event_btnDesativarSalaActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        java.awt.Frame parent = (java.awt.Frame) javax.swing.SwingUtilities.getWindowAncestor(this);
        int index = tabPaneSE.getSelectedIndex();

        if (index == 0) { // SALAS
            int linha = tabelaSalas.getSelectedRow();
            if (linha == -1) {
                mostrarAviso(parent, "Selecione uma sala para editar.", "Atenção", "src/main/java/Recursos/aviso.png");
                return;
            }
            int modelRow = tabelaSalas.convertRowIndexToModel(linha);
            int idSala = (int) tabelaSalas.getModel().getValueAt(modelRow, 0);
            if (janelaPrincipal != null) janelaPrincipal.irParaFormularioSalas(idSala);
        } else if (index == 1) { // ESPAÇOS
            int linha = tabelaEspacos.getSelectedRow();
            if (linha == -1) {
                mostrarAviso(parent, "Selecione um espaço para editar.", "Atenção", "src/main/java/Recursos/aviso.png");
                return;
            }
            int modelRow = tabelaEspacos.convertRowIndexToModel(linha);
            int idEspaco = (int) tabelaEspacos.getModel().getValueAt(modelRow, 0);
            if (janelaPrincipal != null) janelaPrincipal.irParaFormularioEspacos(idEspaco);
        }
    }//GEN-LAST:event_btnEditarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdicionar;
    private javax.swing.JButton btnDesativarSala;
    private javax.swing.JButton btnEditar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane tabPaneSE;
    private javax.swing.JTable tabelaEspacos;
    private javax.swing.JTable tabelaSalas;
    private javax.swing.JTextField txtPesquisar;
    // End of variables declaration//GEN-END:variables
}

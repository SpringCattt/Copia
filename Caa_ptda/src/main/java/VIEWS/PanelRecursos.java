/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package VIEWS;

import MODELS.CLASS.Consumivel;
import MODELS.CLASS.NaoConsumivel;
import java.awt.Component;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author gonca
 */
public class PanelRecursos extends javax.swing.JPanel {

    private PaginaInicial janelaPrincipal;
    CONTROLLERS.HomeController controller;
    private String top, mensagem, imagem;
    java.awt.Window win = javax.swing.SwingUtilities.getWindowAncestor(this);
    
    public PanelRecursos(PaginaInicial janelaPrincipal) {
        this.janelaPrincipal = janelaPrincipal;
        this.controller = new CONTROLLERS.HomeController();
        initComponents();
        
        tabelaConsumiveis.getTableHeader().setResizingAllowed(false);
        tabelaConsumiveis.getTableHeader().setReorderingAllowed(false);
        tabelaConsumiveis.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        
        tabelaNaoConsumiveis.getTableHeader().setResizingAllowed(false);
        tabelaNaoConsumiveis.getTableHeader().setReorderingAllowed(false);
        tabelaNaoConsumiveis.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        
        atualizarTabelas();
        configurarEsteticaTabelas();
        
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

        // Se o texto for o placeholder cinzento, não filtramos nada
        if (txtPesquisar.getForeground().equals(java.awt.Color.GRAY) && termo.equals("Pesquisar")) {
            aplicarFiltro("");
        } else {
            aplicarFiltro(termo);
        }
    }

    private void aplicarFiltro(String termo) {
        // 1. Configurar Sorters para as duas tabelas
        DefaultTableModel modC = (DefaultTableModel) tabelaConsumiveis.getModel();
        TableRowSorter<DefaultTableModel> sorterC = new TableRowSorter<>(modC);
        tabelaConsumiveis.setRowSorter(sorterC);

        DefaultTableModel modNC = (DefaultTableModel) tabelaNaoConsumiveis.getModel();
        TableRowSorter<DefaultTableModel> sorterNC = new TableRowSorter<>(modNC);
        tabelaNaoConsumiveis.setRowSorter(sorterNC);

        if (termo.trim().isEmpty()) {
            sorterC.setRowFilter(null);
            sorterNC.setRowFilter(null);
        } else {
            // O regex "(?i)" ignora maiúsculas/minúsculas
            // Não passar índice de coluna faz com que pesquise em todas as rows/columns
            sorterC.setRowFilter(RowFilter.regexFilter("(?i)" + termo));
            sorterNC.setRowFilter(RowFilter.regexFilter("(?i)" + termo));
        }
    }

    private void configurarEsteticaTabelas() {
        tabelaConsumiveis.getColumnModel().getColumn(2).setCellRenderer(rendererPreco);
        tabelaNaoConsumiveis.getColumnModel().getColumn(2).setCellRenderer(rendererPreco);
        
        tabelaConsumiveis.getColumnModel().getColumn(4).setCellRenderer(rendererData);
        
        tabelaNaoConsumiveis.getColumnModel().getColumn(4).setCellRenderer(rendererPreco);
    }

    public void atualizarTabelas() {
        carregarConsumiveis();
        carregarNaoConsumiveis();
    }

    private void carregarConsumiveis() {
        DefaultTableModel modelo = (DefaultTableModel) tabelaConsumiveis.getModel();
        modelo.setNumRows(0);
        // Agora passa pelo Controller:
        for (Consumivel c : controller.listarConsumiveis()) {
            modelo.addRow(new Object[]{c.getIdRecurso(), c.getNome(), c.getPreco(), c.getQuantidade(), c.getDataValidade()});
        }
    }

    private void carregarNaoConsumiveis() {
        DefaultTableModel modelo = (DefaultTableModel) tabelaNaoConsumiveis.getModel();
        modelo.setNumRows(0);
        // Agora passa pelo Controller:
        for (NaoConsumivel nc : controller.listarNaoConsumiveis()) {
            modelo.addRow(new Object[]{nc.getIdRecurso(), nc.getNome(), nc.getPreco(), nc.getQuantidade(), nc.getPrecoAluguer()});
        }
    }

    // --- RENDERIZADORES REUTILIZÁVEIS ---

    private final DefaultTableCellRenderer rendererData = new DefaultTableCellRenderer() {
        private final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        @Override
        public Component getTableCellRendererComponent(JTable t, Object v, boolean s, boolean f, int r, int c) {
            super.getTableCellRendererComponent(t, v, s, f, r, c);
            if (v instanceof java.util.Date) setText(sdf.format(v));
            setHorizontalAlignment(JLabel.CENTER);
            return this;
        }
    };

    private final DefaultTableCellRenderer rendererPreco = new DefaultTableCellRenderer() {
        @Override
        public Component getTableCellRendererComponent(JTable t, Object v, boolean s, boolean f, int r, int c) {
            super.getTableCellRendererComponent(t, v, s, f, r, c);
            if (v instanceof Number) setText(String.format("%.2f €", ((Number) v).doubleValue()));
            setHorizontalAlignment(JLabel.CENTER);
            return this;
        }
    };
    
    private void mostrarDialogo(String mensagem, String titulo, String caminhoImagem) {
        PaginaDialogo dialogo = new PaginaDialogo((java.awt.Frame) win, true);
        dialogo.setMensagem(mensagem, titulo, caminhoImagem);
        dialogo.setLocationRelativeTo(win);
        dialogo.setVisible(true);
    }
    
    
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtPesquisar = new javax.swing.JTextField();
        btnCriar = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        tabPaneCNC = new javax.swing.JTabbedPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabelaConsumiveis = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        tabelaNaoConsumiveis = new javax.swing.JTable();

        setBackground(new java.awt.Color(232, 235, 238));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtPesquisar.setText("Pesquisar");
        txtPesquisar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtPesquisarMouseClicked(evt);
            }
        });
        txtPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPesquisarActionPerformed(evt);
            }
        });
        txtPesquisar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPesquisarKeyReleased(evt);
            }
        });
        add(txtPesquisar, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 50, 180, 40));

        btnCriar.setBackground(new java.awt.Color(51, 121, 232));
        btnCriar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnCriar.setForeground(new java.awt.Color(255, 255, 255));
        btnCriar.setText("Adicionar");
        btnCriar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCriarActionPerformed(evt);
            }
        });
        add(btnCriar, new org.netbeans.lib.awtextra.AbsoluteConstraints(585, 488, 150, 40));

        btnEditar.setBackground(new java.awt.Color(51, 121, 232));
        btnEditar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnEditar.setForeground(new java.awt.Color(255, 255, 255));
        btnEditar.setText("Editar");
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });
        add(btnEditar, new org.netbeans.lib.awtextra.AbsoluteConstraints(417, 488, 150, 40));

        btnEliminar.setBackground(new java.awt.Color(51, 121, 232));
        btnEliminar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnEliminar.setForeground(new java.awt.Color(255, 255, 255));
        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(249, 488, 150, 40));

        tabelaConsumiveis.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Id", "Nome", "Preco", "Quantidade", "Validade"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Float.class, java.lang.Integer.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tabelaConsumiveis);

        tabPaneCNC.addTab("Consumíveis", jScrollPane2);

        tabelaNaoConsumiveis.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Id", "Nome", "Preco", "Quantidade", "Preco Aluguer"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Float.class, java.lang.Integer.class, java.lang.Float.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(tabelaNaoConsumiveis);

        tabPaneCNC.addTab("Não Consumíveis", jScrollPane3);

        add(tabPaneCNC, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 100, 690, 362));
    }// </editor-fold>//GEN-END:initComponents

    private void txtPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPesquisarActionPerformed

    }//GEN-LAST:event_txtPesquisarActionPerformed

    private void btnCriarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCriarActionPerformed
        // 0 para Consumível, 1 para Não Consumível
        int abaAtual = tabPaneCNC.getSelectedIndex();

        // Chama o método na Janela Principal para trocar o painel
        janelaPrincipal.irParaFormularioRecursos(abaAtual, null);
    }//GEN-LAST:event_btnCriarActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        int abaAtual = tabPaneCNC.getSelectedIndex();
        JTable tabelaAtiva = (abaAtual == 0) ? tabelaConsumiveis : tabelaNaoConsumiveis;

        int linhaVista = tabelaAtiva.getSelectedRow();
        if (linhaVista == -1) {
            mostrarDialogo("Selecione um item para editar.", "Aviso", "src/main/java/Recursos/aviso.png");
            return;
        }

        int linhaModelo = tabelaAtiva.convertRowIndexToModel(linhaVista);
        int id = (int) tabelaAtiva.getModel().getValueAt(linhaModelo, 0);
        Object recurso;

        // Chamada via Controller:
        if (abaAtual == 0) {
            recurso = controller.buscarConsumivelPorId(id);
        } else {
            recurso = controller.buscarNaoConsumivelPorId(id);
        }

        if (recurso != null) {
            janelaPrincipal.irParaFormularioRecursos(abaAtual, recurso);
        } else {
            mostrarDialogo("Erro ao carregar os dados.", "Erro", "src/main/java/Recursos/erro.png");
        }
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        int abaAtual = tabPaneCNC.getSelectedIndex();
        JTable tabelaAtiva = (abaAtual == 0) ? tabelaConsumiveis : tabelaNaoConsumiveis;

        int linhaVista = tabelaAtiva.getSelectedRow();
        if (linhaVista == -1) {
            mostrarDialogo("Por favor, selecione um recurso.", "Aviso", "src/main/java/Recursos/aviso.png");
            return;
        }

        int linhaModelo = tabelaAtiva.convertRowIndexToModel(linhaVista);
        long id = Long.parseLong(tabelaAtiva.getModel().getValueAt(linhaModelo, 0).toString());
        String nome = tabelaAtiva.getModel().getValueAt(linhaModelo, 1).toString();

        PaginaOpcao dialog = new PaginaOpcao((java.awt.Frame) javax.swing.SwingUtilities.getWindowAncestor(this), true);
        dialog.setMensagem("Deseja eliminar: " + nome + "?", "Atenção!");
        dialog.setVisible(true); 

        if (dialog.clicouSim()) {
            // Chamada via Controller:
            if (controller.eliminarRecurso(id)) {
                mostrarDialogo("Eliminado com sucesso.", "Informação", "src/main/java/Recursos/info.png");
                txtPesquisar.setText("Pesquisar");
                txtPesquisar.setForeground(java.awt.Color.GRAY);
                atualizarTabelas(); 
            } else {
                mostrarDialogo("Erro ao eliminar.", "Erro", "src/main/java/Recursos/erro.png");
            }
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void txtPesquisarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtPesquisarMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPesquisarMouseClicked

    private void txtPesquisarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPesquisarKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPesquisarKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCriar;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane tabPaneCNC;
    private javax.swing.JTable tabelaConsumiveis;
    private javax.swing.JTable tabelaNaoConsumiveis;
    private javax.swing.JTextField txtPesquisar;
    // End of variables declaration//GEN-END:variables
}

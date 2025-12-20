/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package VIEWS;

import MODELS.CLASS.CategoriaTrabalho;
import MODELS.CLASS.Consumivel;
import MODELS.CLASS.NaoConsumivel;
import MODELS.CLASS.Trabalhador;
import MODELS.DAO.ConsumiveisDAO;
import MODELS.DAO.NaoConsumiveisDAO;
import MODELS.DAO.RecursoDAO;
import java.awt.Component;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author gonca
 */
public class PanelRecursos extends javax.swing.JPanel {

    private PaginaInicial janelaPrincipal;
    private String top, mensagem, imagem;
    java.awt.Window win = javax.swing.SwingUtilities.getWindowAncestor(this);
    /**
     * Creates new form PanelRecursos
     */
    public PanelRecursos(PaginaInicial janelaPrincipal) {
        this.janelaPrincipal = janelaPrincipal;
        initComponents();
        
        // 1. Carregar Dados
        atualizarTabelas();
        
        // 2. Aplicar Estética (Centrar, Moeda e Data PT)
        configurarEsteticaTabelas();
    }

    private void configurarEsteticaTabelas() {
        // Aplicar às colunas de Preço (Índice 2 em ambas)
        tabelaConsumiveis.getColumnModel().getColumn(2).setCellRenderer(rendererPreco);
        tabelaNaoConsumiveis.getColumnModel().getColumn(2).setCellRenderer(rendererPreco);
        
        // Aplicar à coluna de Data (Índice 4 nos Consumíveis)
        tabelaConsumiveis.getColumnModel().getColumn(4).setCellRenderer(rendererData);
        
        // Aplicar ao Preço de Aluguer (Índice 4 nos Não Consumíveis)
        tabelaNaoConsumiveis.getColumnModel().getColumn(4).setCellRenderer(rendererPreco);
    }

    public void atualizarTabelas() {
        carregarConsumiveis();
        carregarNaoConsumiveis();
    }

    private void carregarConsumiveis() {
        DefaultTableModel modelo = (DefaultTableModel) tabelaConsumiveis.getModel();
        modelo.setNumRows(0);
        for (Consumivel c : new ConsumiveisDAO().getAllConsumiveis()) {
            modelo.addRow(new Object[]{c.getIdRecurso(), c.getNome(), c.getPreco(), c.getQuantidade(), c.getDataValidade()});
        }
    }

    private void carregarNaoConsumiveis() {
        DefaultTableModel modelo = (DefaultTableModel) tabelaNaoConsumiveis.getModel();
        modelo.setNumRows(0);
        for (NaoConsumivel nc : new NaoConsumiveisDAO().getAllNaoConsumiveis()) {
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

        txtPesquisar.setText("Pesquisar");
        txtPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPesquisarActionPerformed(evt);
            }
        });

        btnCriar.setBackground(new java.awt.Color(51, 121, 232));
        btnCriar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnCriar.setForeground(new java.awt.Color(255, 255, 255));
        btnCriar.setText("Adicionar");
        btnCriar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCriarActionPerformed(evt);
            }
        });

        btnEditar.setBackground(new java.awt.Color(51, 121, 232));
        btnEditar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnEditar.setForeground(new java.awt.Color(255, 255, 255));
        btnEditar.setText("Editar");
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });

        btnEliminar.setBackground(new java.awt.Color(51, 121, 232));
        btnEliminar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnEliminar.setForeground(new java.awt.Color(255, 255, 255));
        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

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
                false, true, true, true, true
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
                false, true, true, true, true
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(tabPaneCNC, javax.swing.GroupLayout.PREFERRED_SIZE, 690, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnCriar, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(510, 510, 510)))
                .addGap(45, 45, 45))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(txtPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(tabPaneCNC, javax.swing.GroupLayout.PREFERRED_SIZE, 362, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCriar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPesquisarActionPerformed

    }//GEN-LAST:event_txtPesquisarActionPerformed

    private void btnCriarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCriarActionPerformed
        // 0 para Consumível, 1 para Não Consumível
        int abaAtual = tabPaneCNC.getSelectedIndex();

        // Chama o método na Janela Principal para trocar o painel
        janelaPrincipal.irParaFormularioRecurso(abaAtual, null);
    }//GEN-LAST:event_btnCriarActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        // Verifica qual a tabela ativa
        int abaAtual = tabPaneCNC.getSelectedIndex();
        JTable tabelaAtiva = (abaAtual == 0) ? tabelaConsumiveis : tabelaNaoConsumiveis;

        int linha = tabelaAtiva.getSelectedRow();
        if (linha == -1) {
            mostrarDialogo("Selecione um item para editar.", "Aviso", "src/main/java/Recursos/aviso.png");
            return;
        }

        // Procura o objeto completo (ou passa os dados individuais)
        // Se usar DAO, pode buscar pelo ID que está na coluna 0
        int id = (int) tabelaAtiva.getValueAt(linha, 0);
        Object recurso;

        if (abaAtual == 0) {
            recurso = new ConsumiveisDAO().getConsumivelById(id); // Exemplo
        } else {
            recurso = new NaoConsumiveisDAO().getNaoConsumivelById(id); // Exemplo
        }

        janelaPrincipal.irParaFormularioRecurso(abaAtual, recurso);
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        javax.swing.JTable tabelaAtiva = (tabPaneCNC.getSelectedIndex() == 0) ? tabelaConsumiveis : tabelaNaoConsumiveis;

        int linhaSelecionada = tabelaAtiva.getSelectedRow();
        if (linhaSelecionada == -1) {
            mostrarDialogo("Por favor, selecione um recurso para eliminar.", "Aviso", "src/main/java/Recursos/aviso.png");
            return;
        }

        // O ID está na coluna 0. Convertemos para Long pois o DAO usa Long
        long id = Long.parseLong(tabelaAtiva.getValueAt(linhaSelecionada, 0).toString());
        String nome = tabelaAtiva.getValueAt(linhaSelecionada, 1).toString();

        PaginaOpcao dialog = new PaginaOpcao((java.awt.Frame) win, true);
        dialog.setMensagem("Tem a certeza que deseja eliminar o recurso: " + nome + "?", "Atenção!");
        dialog.setLocationRelativeTo(win);
        dialog.setVisible(true); 

        if (dialog.clicouSim()) {
            RecursoDAO dao = new RecursoDAO();
            if (dao.deleteRecurso(id)) {
                mostrarDialogo("Recurso eliminado com sucesso.", "Informação", "src/main/java/Recursos/info.png");

                // 5. Atualizar as tabelas para refletir o Soft Delete
                atualizarTabelas(); 
            } else {
                mostrarDialogo("Erro ao eliminar recurso na Base de Dados.", "Erro", "src/main/java/Recursos/erro.png");
            }
        }
    }//GEN-LAST:event_btnEliminarActionPerformed


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

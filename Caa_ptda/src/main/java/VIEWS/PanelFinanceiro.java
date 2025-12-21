package VIEWS;

import CONTROLLERS.HomeController;
import MODELS.CLASS.Espaco;
import MODELS.CLASS.Evento;
import MODELS.CLASS.EventoRecurso;
import MODELS.CLASS.NaoConsumivel;
import MODELS.CLASS.Recurso;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author gonca
 */
public class PanelFinanceiro extends javax.swing.JPanel {

    private PaginaInicial janelaPrincipal;
    private HomeController controller;
    private String top, mensagem, imagem;
    java.awt.Window win = javax.swing.SwingUtilities.getWindowAncestor(this);

    /**
     * Creates new form PanelFinanceiro
     */
    public PanelFinanceiro(PaginaInicial janelaPrincipal) {
        this.janelaPrincipal = janelaPrincipal;
        this.controller = new HomeController();
        initComponents();
        DefaultTableModel modelo = (DefaultTableModel) tabelaReceitas.getModel();
        modelo.setRowCount(0);

        List<EventoRecurso> lista = controller.listarEventoRecursoNaoConsumiveis();
        float soma = 0;

        for (EventoRecurso er : lista) {

            int idEvento = er.getIdEvento();
            int idRecurso = er.getIdRecurso();
            String nomeEvento = null, nomeRecurso = null;
            Date data = null;
            float preco = 0;

            Evento evento = controller.buscarEventoPorId(idEvento);

            if (evento != null) {
                nomeEvento = evento.getNome();
                data = evento.getData();
            }

            NaoConsumivel nc = controller.buscarNaoConsumivelPorId(idRecurso);

            if (nc != null) {
                nomeRecurso = nc.getNome() + "(Aluguel)";
                preco = (float) nc.getPrecoAluguer();
            }

            soma = soma + preco;

            modelo.addRow(new Object[]{
                idEvento,
                nomeEvento,
                nomeRecurso,
                data,
                preco
            });
        }

        DecimalFormat df = new DecimalFormat("0.00");
        lblTotalReceitas.setText(df.format(soma) + "€");

        float soma2 = 0;

        DefaultTableModel modeloDespesas = (DefaultTableModel) tabelaDespesas.getModel();
        modeloDespesas.setRowCount(0);

        List<Recurso> listaDespesas = controller.listarTodosOsRecursosSemConsiderarAtivo();

        for (Recurso r : listaDespesas) {
            String tipo = controller.identificarTipoRecurso(r.getIdRecurso());
            float subtotal = (float) ((float) r.getQuantidade() * r.getPreco());


            soma2 += subtotal;
            
            Object[] linha = {
                r.getIdRecurso(),
                r.getNome(),
                tipo,
                r.getQuantidade(),
                String.format("%.2f €", subtotal)
            };
            modeloDespesas.addRow(linha);
        }
        
        lvlTotalDespesas.setText(df.format(soma2) + "€");
        jLabel4.setText(df.format(soma-soma2) + "€");
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
            public void insertUpdate(javax.swing.event.DocumentEvent e) {
                verificarEFiltrar();
            }

            @Override
            public void removeUpdate(javax.swing.event.DocumentEvent e) {
                verificarEFiltrar();
            }

            @Override
            public void changedUpdate(javax.swing.event.DocumentEvent e) {
                verificarEFiltrar();
            }
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
        DefaultTableModel modR = (DefaultTableModel) tabelaReceitas.getModel();
        TableRowSorter<DefaultTableModel> sorterR = new TableRowSorter<>(modR);
        tabelaReceitas.setRowSorter(sorterR);

        DefaultTableModel modD = (DefaultTableModel) tabelaDespesas.getModel();
        TableRowSorter<DefaultTableModel> sorterD = new TableRowSorter<>(modD);
        tabelaDespesas.setRowSorter(sorterD);

        if (termo.trim().isEmpty()) {
            sorterR.setRowFilter(null);
            sorterD.setRowFilter(null);
        } else {
            sorterR.setRowFilter(RowFilter.regexFilter("(?i)" + termo));
            sorterD.setRowFilter(RowFilter.regexFilter("(?i)" + termo));
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        lblTotalReceitas = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        lvlTotalDespesas = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaReceitas = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabelaDespesas = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        txtPesquisar = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtDataInicio = new javax.swing.JFormattedTextField();
        jLabel7 = new javax.swing.JLabel();
        txtDataFim = new javax.swing.JFormattedTextField();
        jButton1 = new javax.swing.JButton();

        setBackground(new java.awt.Color(232, 235, 238));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(153, 255, 153));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setText("Total Receitas");

        lblTotalReceitas.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblTotalReceitas.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTotalReceitas.setText("0.00€");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 82, Short.MAX_VALUE)
                        .addComponent(jLabel1)
                        .addGap(0, 83, Short.MAX_VALUE))
                    .addComponent(lblTotalReceitas, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(lblTotalReceitas)
                .addContainerGap(74, Short.MAX_VALUE))
        );

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 260, 160));

        jPanel2.setBackground(new java.awt.Color(255, 102, 102));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("Total Despesas");

        lvlTotalDespesas.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lvlTotalDespesas.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lvlTotalDespesas.setText("0.00€");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(82, 82, 82)
                .addComponent(jLabel2)
                .addContainerGap(87, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lvlTotalDespesas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(lvlTotalDespesas)
                .addContainerGap(73, Short.MAX_VALUE))
        );

        add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 0, 260, 160));

        jPanel3.setBackground(new java.awt.Color(102, 153, 255));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("Saldo Atual");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("0.00€");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(94, 94, 94)
                .addComponent(jLabel3)
                .addContainerGap(97, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addContainerGap(72, Short.MAX_VALUE))
        );

        add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 0, 260, 160));

        tabelaReceitas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Id Evento", "Nome Evento", "Tipo(Bilhete/Aluguer)", "Data", "Valor"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Float.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tabelaReceitas);

        jTabbedPane1.addTab("Receitas", jScrollPane1);

        tabelaDespesas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Id Recurso", "Nome Recurso", "Categoria", "Quantidade Comprada", "Custo total"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tabelaDespesas);

        jTabbedPane1.addTab("Despesas", jScrollPane2);

        add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 230, 780, 260));

        jLabel5.setText("Pesquisar");
        add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 170, -1, -1));
        add(txtPesquisar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 190, 190, 35));

        jLabel6.setText("Data Inicio:");
        add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 170, -1, -1));

        txtDataInicio.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(java.text.DateFormat.getDateInstance(java.text.DateFormat.SHORT))));
        add(txtDataInicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 190, 90, 35));

        jLabel7.setText("Data Fim:");
        add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 170, -1, -1));
        add(txtDataFim, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 190, 90, 35));

        jButton1.setText("Filtrar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 190, 100, 35));
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lblTotalReceitas;
    private javax.swing.JLabel lvlTotalDespesas;
    private javax.swing.JTable tabelaDespesas;
    private javax.swing.JTable tabelaReceitas;
    private javax.swing.JFormattedTextField txtDataFim;
    private javax.swing.JFormattedTextField txtDataInicio;
    private javax.swing.JTextField txtPesquisar;
    // End of variables declaration//GEN-END:variables
}

package VIEWS;

import CONTROLLERS.HomeController;
import MODELS.CLASS.Evento;
import MODELS.CLASS.Sala;
import MODELS.CLASS.Trabalhador;
import java.awt.Color;
import java.awt.event.*;
import java.util.List;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author gonca
 */
public class PanelEventos extends javax.swing.JPanel {

    private PaginaInicial janelaPrincipal;
    private HomeController controller;

    public PanelEventos(PaginaInicial janelaPrincipal) {
        this.janelaPrincipal = janelaPrincipal;
        this.controller = new HomeController();
        initComponents();

        tabelaEventos.getTableHeader().setResizingAllowed(false);

        tabelaEventos.getTableHeader().setReorderingAllowed(false);

        tabelaEventos.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        
        int idLogado = janelaPrincipal.getIdTrabalhador();
        MODELS.CLASS.Trabalhador t = controller.procurarTrabalhador(idLogado);

        if (t != null) {
            configurarPermissoesBotoes(t.getCategoria());
        }
        
        controller.verificarEAtualizarEventosPassados();

        txtPesquisar.setText("Pesquisar");
        txtPesquisar.setForeground(Color.GRAY);

        configurarPesquisa();
        carregarTabela(null);

        tabelaEventos.getColumnModel().getColumn(8).setCellRenderer(new javax.swing.table.DefaultTableCellRenderer() {
            @Override
            public java.awt.Component getTableCellRendererComponent(javax.swing.JTable table, Object value,
                    boolean isSelected, boolean hasFocus, int row, int column) {

                java.awt.Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

                if (value != null) {
                    String status = value.toString();
                    switch (status) {
                        case "Cancelado":
                            c.setForeground(java.awt.Color.RED);
                            break;
                        case "Por Decorrer":
                            c.setForeground(new java.awt.Color(0, 153, 0)); // Verde escuro para leitura
                            break;
                        case "Decorrido":
                            c.setForeground(new java.awt.Color(204, 153, 0)); // Amarelo/Dourado
                            break;
                        default:
                            c.setForeground(table.getForeground());
                    }
                }

                // Mantém o alinhamento centralizado para ficar mais bonito
                setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

                return c;
            }
        });
    }
    
    private void configurarPermissoesBotoes(int categoria) {
        // Primeiro, escondemos todos os botões de ação para segurança
        btnCriar.setVisible(false);
        btnEditar.setVisible(false);
        btnEliminar.setVisible(false);
        btnCancelar.setVisible(false);
        btnAssociarRecurso.setVisible(false);
        btnAssociarTarefa.setVisible(false);

        switch (categoria) {
            case 2: // GESTOR DE EVENTO
                btnCriar.setVisible(true);
                btnEditar.setVisible(true);
                btnEliminar.setVisible(true);
                btnCancelar.setVisible(true);
                break;

            case 3: // GESTOR DE RECURSOS
                btnAssociarRecurso.setVisible(true);
                btnAssociarTarefa.setVisible(true);
                break;

            case 5: // ADMINISTRADOR (Assume-se que vê tudo)
                btnCriar.setVisible(true);
                btnEditar.setVisible(true);
                btnEliminar.setVisible(true);
                btnCancelar.setVisible(true);
                btnAssociarRecurso.setVisible(true);
                btnAssociarTarefa.setVisible(true);
                break;

            case 1: // FINANCEIRO
            case 4: // STAFF
                // Não fazemos nada, os botões permanecem todos false (invisíveis)
                break;
        }
    }
    
    // --- PESQUISA ---
    private void configurarPesquisa() {
        txtPesquisar.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (txtPesquisar.getText().equals("Pesquisar") && txtPesquisar.getForeground().equals(Color.GRAY)) {
                    txtPesquisar.setText("");
                    txtPesquisar.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (txtPesquisar.getText().isEmpty()) {
                    txtPesquisar.setForeground(Color.GRAY);
                    txtPesquisar.setText("Pesquisar");
                }
            }
        });

        // Listener para atualizar a tabela enquanto escreve
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
        if (txtPesquisar.getForeground().equals(Color.GRAY) && termo.equals("Pesquisar")) {
            aplicarFiltro("");
        } else {
            aplicarFiltro(termo);
        }
    }

    private void aplicarFiltro(String termo) {
        DefaultTableModel modelo = (DefaultTableModel) tabelaEventos.getModel();
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(modelo);
        tabelaEventos.setRowSorter(sorter);

        if (termo.trim().isEmpty()) {
            sorter.setRowFilter(null);
        } else {
            // Filtra ignorando maiúsculas/minúsculas
            sorter.setRowFilter(RowFilter.regexFilter("(?i)" + termo));
        }
    }

    private void carregarTabela(List<Evento> listaParaMostrar) {
        DefaultTableModel modelo = (DefaultTableModel) tabelaEventos.getModel();
        modelo.setRowCount(0);

        List<Evento> listaEventos = (listaParaMostrar != null) ? listaParaMostrar : controller.obterTodosEventos();
        List<Trabalhador> todosTrabs = controller.obterTodosFuncionarios();
        List<Sala> todasSalas = controller.listarTodasSalasAtivas();

        java.text.SimpleDateFormat sdfData = new java.text.SimpleDateFormat("dd/MM/yyyy");
        java.text.SimpleDateFormat sdfHora = new java.text.SimpleDateFormat("HH:mm"); // Para formatar Hora e Duração

        for (Evento e : listaEventos) {
            String estado = "";
            String nomeResp = "N/A";
            for (Trabalhador t : todosTrabs) {
                if (t.getIdTrabalhador() == e.getResponsavel()) {
                    nomeResp = t.getNome();
                    break;
                }
            }

            String nomeSala = "N/A";
            for (Sala s : todasSalas) {
                if (s.getIdSala() == e.getSala()) {
                    nomeSala = s.getNome();
                    break;
                }
            }

            // Formatação de Datas e Tempos
            String dataStr = (e.getData() != null) ? sdfData.format(e.getData()) : "";
            String horaStr = (e.getHora() != null) ? sdfHora.format(e.getHora()) : "";
            String duracaoStr = (e.getDuracao() != null) ? sdfHora.format(e.getDuracao()) : "00:00";

            boolean isCancelado = e.isCancelado();

            if (isCancelado) {
                estado = "Cancelado";
            } else if (!e.isEstado()) { // Estado = 1 (true)
                estado = "Decorrido";
            } else { // Estado = 0 (false)
                estado = "Por Decorrer";
            }

            modelo.addRow(new Object[]{
                e.getIdEvento(),
                e.getNome(),
                dataStr,
                e.getDescricao(),
                nomeResp,
                nomeSala,
                horaStr,
                duracaoStr,
                estado // Agora passamos a String com o estado
            });
        }
    }

    // --- POP-UP AUXILIAR ---
    private void mostrarAviso(String msg, String titulo, String img) {
        java.awt.Frame parent = (java.awt.Frame) javax.swing.SwingUtilities.getWindowAncestor(this);
        PaginaDialogo d = new PaginaDialogo(parent, true);
        d.setMensagem(msg, titulo, img);
        d.setVisible(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtPesquisar = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaEventos = new javax.swing.JTable();
        btnCriar = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnAssociarRecurso = new javax.swing.JButton();
        btnAssociarTarefa = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();

        setBackground(new java.awt.Color(232, 235, 238));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtPesquisar.setText("Pesquisar");
        txtPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPesquisarActionPerformed(evt);
            }
        });
        add(txtPesquisar, new org.netbeans.lib.awtextra.AbsoluteConstraints(45, 50, 180, 40));

        tabelaEventos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Nome", "Data", "Descrição", "Responsável", "Sala", "Hora", "Duração", "Estado"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Object.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tabelaEventos);

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(45, 108, 690, 350));

        btnCriar.setBackground(new java.awt.Color(51, 121, 232));
        btnCriar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnCriar.setForeground(new java.awt.Color(255, 255, 255));
        btnCriar.setText("Adicionar");
        btnCriar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCriarActionPerformed(evt);
            }
        });
        add(btnCriar, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 480, 150, 40));

        btnEditar.setBackground(new java.awt.Color(51, 121, 232));
        btnEditar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnEditar.setForeground(new java.awt.Color(255, 255, 255));
        btnEditar.setText("Editar");
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });
        add(btnEditar, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 480, 150, 40));

        btnAssociarRecurso.setBackground(new java.awt.Color(51, 121, 232));
        btnAssociarRecurso.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnAssociarRecurso.setForeground(new java.awt.Color(255, 255, 255));
        btnAssociarRecurso.setText("Associar Recurso");
        btnAssociarRecurso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAssociarRecursoActionPerformed(evt);
            }
        });
        add(btnAssociarRecurso, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 480, 150, 40));

        btnAssociarTarefa.setBackground(new java.awt.Color(51, 121, 232));
        btnAssociarTarefa.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnAssociarTarefa.setForeground(new java.awt.Color(255, 255, 255));
        btnAssociarTarefa.setText("Associar Tarefa");
        btnAssociarTarefa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAssociarTarefaActionPerformed(evt);
            }
        });
        add(btnAssociarTarefa, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 480, 150, 40));

        btnEliminar.setBackground(new java.awt.Color(51, 121, 232));
        btnEliminar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnEliminar.setForeground(new java.awt.Color(255, 255, 255));
        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 480, 150, 40));

        btnCancelar.setBackground(new java.awt.Color(51, 121, 232));
        btnCancelar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnCancelar.setForeground(new java.awt.Color(255, 255, 255));
        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        add(btnCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 480, 150, 40));
    }// </editor-fold>//GEN-END:initComponents

    private void txtPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPesquisarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPesquisarActionPerformed

    private void btnCriarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCriarActionPerformed
        if (janelaPrincipal != null) {
            janelaPrincipal.irParaFormularioEventos();
        }
    }//GEN-LAST:event_btnCriarActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        int linhaSelecionada = tabelaEventos.getSelectedRow();

        if (linhaSelecionada == -1) {
            mostrarAviso("Selecione um evento para editar.", "Atenção", "src/main/java/Recursos/aviso.png");
            return;
        }

        // Obtem os dados da tabela
        // (Nota: precisamos do ID como Inteiro, não String, para o controller)
        int idEvento = (int) tabelaEventos.getValueAt(linhaSelecionada, 0);

        Evento eventoParaEditar = controller.buscarEventoPorId(idEvento);

        if (eventoParaEditar != null) {
            if (janelaPrincipal != null) {
                janelaPrincipal.irParaEditarEvento(eventoParaEditar);
            }
        } else {
            mostrarAviso("Erro ao carregar dados do evento.", "Erro", "src/main/java/Recursos/erro.png");
        }
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        int linhaSelecionada = tabelaEventos.getSelectedRow();
        if (linhaSelecionada == -1) {
            mostrarAviso("Selecione um evento para eliminar.", "Atenção", "src/main/java/Recursos/aviso.png");
            return;
        }

        java.awt.Frame parent = (java.awt.Frame) javax.swing.SwingUtilities.getWindowAncestor(this);
        PaginaOpcao popUp = new PaginaOpcao(parent, true);

        popUp.setMensagem("Tem a certeza que deseja eliminar este evento?", "Confirmar Eliminação");
        popUp.setVisible(true);

        if (popUp.clicouSim()) {
            int idEvento = (int) tabelaEventos.getValueAt(linhaSelecionada, 0);

            boolean sucesso = controller.eliminarEvento(idEvento);

            if (sucesso) {
                mostrarAviso("Evento eliminado com sucesso!", "Sucesso", "src/main/java/Recursos/info.png");
                carregarTabela(null); // Atualiza a tabela
            } else {
                mostrarAviso("Erro ao eliminar o evento.", "Erro", "src/main/java/Recursos/erro.png");
            }
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnAssociarTarefaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAssociarTarefaActionPerformed
        if (janelaPrincipal != null) {
            janelaPrincipal.irParaFormularioTarefa();
        }
    }//GEN-LAST:event_btnAssociarTarefaActionPerformed

    private void btnAssociarRecursoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAssociarRecursoActionPerformed
        if (janelaPrincipal != null) {
            janelaPrincipal.irParaAssociarRecurso();
        }
    }//GEN-LAST:event_btnAssociarRecursoActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        int linhaSelecionada = tabelaEventos.getSelectedRow();

        if (linhaSelecionada == -1) {
            mostrarAviso("Selecione um evento para cancelar.", "Atenção", "src/main/java/Recursos/aviso.png");
            return;
        }

        // 1. Obter o texto do estado da linha selecionada (Coluna 8)
        String estadoAtual = tabelaEventos.getValueAt(linhaSelecionada, 8).toString();

        // 2. Verificar se o estado é "Por Decorrer"
        // Nota: Se o texto for exatamente "Por Decorrer", impedimos o cancelamento
        if (estadoAtual.equalsIgnoreCase("Decorrido")) {
            mostrarAviso("Não é possível cancelar um evento que tenha sido 'Decorrido'.",
                    "Operação Inválida", "src/main/java/Recursos/aviso.png");
            return;
        }

        // 3. Se passar a validação, prossegue com o pop-up
        java.awt.Frame parent = (java.awt.Frame) javax.swing.SwingUtilities.getWindowAncestor(this);
        PaginaOpcao popUp = new PaginaOpcao(parent, true);

        popUp.setMensagem("Tem a certeza que deseja cancelar este evento?", "Confirmar Cancelamento");
        popUp.setVisible(true);

        if (popUp.clicouSim()) {
            int idEvento = (int) tabelaEventos.getValueAt(linhaSelecionada, 0);

            boolean sucesso = controller.cancelarEvento(idEvento);

            if (sucesso) {
                mostrarAviso("Evento cancelado com sucesso!", "Sucesso", "src/main/java/Recursos/info.png");
                carregarTabela(null);
            } else {
                mostrarAviso("Erro ao cancelar o evento.", "Erro", "src/main/java/Recursos/erro.png");
            }
        }
    }//GEN-LAST:event_btnCancelarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAssociarRecurso;
    private javax.swing.JButton btnAssociarTarefa;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnCriar;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabelaEventos;
    private javax.swing.JTextField txtPesquisar;
    // End of variables declaration//GEN-END:variables
}

package VIEWS;

import CONTROLLERS.HomeController;
import MODELS.CLASS.Trabalhador;
import java.awt.BorderLayout;
import java.awt.*;
import javax.swing.*;

public class PaginaInicial extends javax.swing.JFrame {

    //Variaveis Globais
    private JPanel painelSelecionado = null;

    private final Color COR_NORMAL_TOP = new Color(255, 252, 252);
    private final Color COR_NORMAL = new Color(97, 99, 103);
    private final Color COR_HOVER = new Color(131, 136, 141);
    private final Color COR_HOVER_FECHAR = new Color(193, 18, 31);
    private final Color COR_SELECIONADO = new Color(238, 238, 238);
    private JLabel labelIconeSelecionado = null;
    private JLabel labelTextoSelecionado = null;
    private String iconNormalSelecionado = null;
    private String linkN, linkP;
    private int idTrabalhador;
    private HomeController controller;

    public PaginaInicial(int idTrabalhador) {
        estiloJanela();
        initComponents();

        this.idTrabalhador = idTrabalhador;

        this.controller = new HomeController();
    }

    public PaginaInicial() {
        estiloJanela();
        initComponents();

        this.controller = new HomeController();
    }

    public void estiloJanela() {
        JPanel contentPane = new JPanel();
        contentPane.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        contentPane.setLayout(new BorderLayout());
        setContentPane(contentPane);
    }

    private void selecionarPainel(JPanel painel, JLabel labelI, JLabel labelP, String linkN, String linkP) {
        // Reseta o painel anterior
        if (painelSelecionado != null) {
            painelSelecionado.setBackground(COR_NORMAL);
            labelIconeSelecionado.setIcon(new ImageIcon(iconNormalSelecionado));
            labelTextoSelecionado.setForeground(Color.WHITE);
        }

        // Seleciona o novo painel
        painel.setBackground(COR_SELECIONADO);
        labelI.setIcon(new ImageIcon(linkP));
        labelP.setForeground(Color.BLACK);

        // Guarda referências do painel atual
        painelSelecionado = painel;
        labelIconeSelecionado = labelI;
        labelTextoSelecionado = labelP;
        iconNormalSelecionado = linkN;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        panelBotaoFuncionarios = new javax.swing.JPanel();
        labelBotaoFuncionarios = new javax.swing.JLabel();
        imagemBotaoFuncionarios = new javax.swing.JLabel();
        panelBotaoSalas = new javax.swing.JPanel();
        labelBotaoSalas = new javax.swing.JLabel();
        imagemBotaoSalas = new javax.swing.JLabel();
        panelBotaoEventos = new javax.swing.JPanel();
        labelBotaoEventos = new javax.swing.JLabel();
        imagemBotaoEventos = new javax.swing.JLabel();
        panelBotaoFinanceiro = new javax.swing.JPanel();
        labelBotaoFinanceiro = new javax.swing.JLabel();
        imagemBotaoFinanceiro = new javax.swing.JLabel();
        panelBotaoRecursos = new javax.swing.JPanel();
        labelBotaoRecursos = new javax.swing.JLabel();
        imagemBotaoRecursos = new javax.swing.JLabel();
        panelBotaoDefinicoes = new javax.swing.JPanel();
        labelBotaoDefinicoes = new javax.swing.JLabel();
        imagemBotaoDefinicoes = new javax.swing.JLabel();
        panelBotaoDashboard = new javax.swing.JPanel();
        labelBotaoDashbord = new javax.swing.JLabel();
        imagemBotaoDashboard = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        labelPagina = new javax.swing.JLabel();
        panelFechar = new javax.swing.JPanel();
        labelFechar = new javax.swing.JLabel();
        panelMinimizar = new javax.swing.JPanel();
        labelMinimizar = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1002, 602));
        setName("PaginaInicial"); // NOI18N
        setUndecorated(true);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(97, 99, 103));
        jPanel1.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                labelPaginaMouseDragged(evt);
            }
        });
        jPanel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                labelPaginaMousePressed(evt);
            }
        });
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setIcon(new javax.swing.ImageIcon("src/main/java/Recursos/CAA_LOGO.png"));
        jLabel1.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                labelPaginaMouseDragged(evt);
            }
        });
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                labelPaginaMousePressed(evt);
            }
        });
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 200, 60));

        panelBotaoFuncionarios.setBackground(new java.awt.Color(97, 99, 103));
        panelBotaoFuncionarios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                imagemBotaoFuncionariosMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                imagemBotaoFuncionariosMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                imagemBotaoFuncionariosMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                labelBotaoFuncionariosMousePressed(evt);
            }
        });
        panelBotaoFuncionarios.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        labelBotaoFuncionarios.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        labelBotaoFuncionarios.setForeground(new java.awt.Color(255, 255, 255));
        labelBotaoFuncionarios.setText("Funcionários");
        labelBotaoFuncionarios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                imagemBotaoFuncionariosMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                imagemBotaoFuncionariosMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                labelBotaoFuncionariosMousePressed(evt);
            }
        });
        panelBotaoFuncionarios.add(labelBotaoFuncionarios, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 0, 140, 50));

        imagemBotaoFuncionarios.setIcon(new javax.swing.ImageIcon("src/main/java/Recursos/funcionarios.png"));
        imagemBotaoFuncionarios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                imagemBotaoFuncionariosMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                imagemBotaoFuncionariosMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                imagemBotaoFuncionariosMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                labelBotaoFuncionariosMousePressed(evt);
            }
        });
        panelBotaoFuncionarios.add(imagemBotaoFuncionarios, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 30, 30));

        jPanel1.add(panelBotaoFuncionarios, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 170, 200, 50));

        panelBotaoSalas.setBackground(new java.awt.Color(97, 99, 103));
        panelBotaoSalas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                labelBotaoSalasMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                labelBotaoSalasMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                panelBotaoSalasMousePressed(evt);
            }
        });
        panelBotaoSalas.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        labelBotaoSalas.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        labelBotaoSalas.setForeground(new java.awt.Color(255, 255, 255));
        labelBotaoSalas.setText("Salas");
        labelBotaoSalas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelBotaoSalasMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                labelBotaoSalasMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                labelBotaoSalasMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                panelBotaoSalasMousePressed(evt);
            }
        });
        panelBotaoSalas.add(labelBotaoSalas, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 0, 140, 50));

        imagemBotaoSalas.setIcon(new javax.swing.ImageIcon("src/main/java/Recursos/salas.png"));
        imagemBotaoSalas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelBotaoSalasMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                labelBotaoSalasMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                labelBotaoSalasMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                panelBotaoSalasMousePressed(evt);
            }
        });
        panelBotaoSalas.add(imagemBotaoSalas, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 30, 30));

        jPanel1.add(panelBotaoSalas, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 230, 200, 50));

        panelBotaoEventos.setBackground(new java.awt.Color(97, 99, 103));
        panelBotaoEventos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelBotaoEventosMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                labelBotaoEventosMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                labelBotaoEventosMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                labelBotaoEventosMousePressed(evt);
            }
        });
        panelBotaoEventos.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        labelBotaoEventos.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        labelBotaoEventos.setForeground(new java.awt.Color(255, 255, 255));
        labelBotaoEventos.setText("Eventos");
        labelBotaoEventos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                labelBotaoEventosMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                labelBotaoEventosMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                labelBotaoEventosMousePressed(evt);
            }
        });
        panelBotaoEventos.add(labelBotaoEventos, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 0, 140, 50));

        imagemBotaoEventos.setIcon(new javax.swing.ImageIcon("src/main/java/Recursos/eventos.png"));
        imagemBotaoEventos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelBotaoEventosMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                labelBotaoEventosMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                labelBotaoEventosMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                labelBotaoEventosMousePressed(evt);
            }
        });
        panelBotaoEventos.add(imagemBotaoEventos, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 30, 30));

        jPanel1.add(panelBotaoEventos, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 290, 200, 50));

        panelBotaoFinanceiro.setBackground(new java.awt.Color(97, 99, 103));
        panelBotaoFinanceiro.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                imagemBotaoFinanceiroMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                imagemBotaoFinanceiroMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                imagemBotaoFinanceiroMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                imagemBotaoFinanceiroMousePressed(evt);
            }
        });
        panelBotaoFinanceiro.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        labelBotaoFinanceiro.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        labelBotaoFinanceiro.setForeground(new java.awt.Color(255, 255, 255));
        labelBotaoFinanceiro.setText("Financeiro");
        labelBotaoFinanceiro.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                imagemBotaoFinanceiroMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                imagemBotaoFinanceiroMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                imagemBotaoFinanceiroMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                imagemBotaoFinanceiroMousePressed(evt);
            }
        });
        panelBotaoFinanceiro.add(labelBotaoFinanceiro, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 0, 140, 50));

        imagemBotaoFinanceiro.setIcon(new javax.swing.ImageIcon("src/main/java/Recursos/financeiro.png"));
        imagemBotaoFinanceiro.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                imagemBotaoFinanceiroMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                imagemBotaoFinanceiroMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                imagemBotaoFinanceiroMousePressed(evt);
            }
        });
        panelBotaoFinanceiro.add(imagemBotaoFinanceiro, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 30, 30));

        jPanel1.add(panelBotaoFinanceiro, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 350, 200, 50));

        panelBotaoRecursos.setBackground(new java.awt.Color(97, 99, 103));
        panelBotaoRecursos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                imagemBotaoRecursosMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                imagemBotaoRecursosMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                imagemBotaoRecursosMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                imagemBotaoRecursosMousePressed(evt);
            }
        });
        panelBotaoRecursos.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        labelBotaoRecursos.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        labelBotaoRecursos.setForeground(new java.awt.Color(255, 255, 255));
        labelBotaoRecursos.setText("Recursos");
        labelBotaoRecursos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                imagemBotaoRecursosMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                imagemBotaoRecursosMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                imagemBotaoRecursosMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                imagemBotaoRecursosMousePressed(evt);
            }
        });
        panelBotaoRecursos.add(labelBotaoRecursos, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 0, 140, 50));

        imagemBotaoRecursos.setIcon(new javax.swing.ImageIcon("src/main/java/Recursos/recursos.png"));
        imagemBotaoRecursos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                imagemBotaoRecursosMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                imagemBotaoRecursosMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                imagemBotaoRecursosMousePressed(evt);
            }
        });
        panelBotaoRecursos.add(imagemBotaoRecursos, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 30, 30));

        jPanel1.add(panelBotaoRecursos, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 410, -1, 50));

        panelBotaoDefinicoes.setBackground(new java.awt.Color(97, 99, 103));
        panelBotaoDefinicoes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelBotaoDefinicoesMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                labelBotaoDefinicoesMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                labelBotaoDefinicoesMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                labelBotaoDefinicoesMousePressed(evt);
            }
        });
        panelBotaoDefinicoes.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        labelBotaoDefinicoes.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        labelBotaoDefinicoes.setForeground(new java.awt.Color(255, 255, 255));
        labelBotaoDefinicoes.setText("Definições");
        labelBotaoDefinicoes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                labelBotaoDefinicoesMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                labelBotaoDefinicoesMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                labelBotaoDefinicoesMousePressed(evt);
            }
        });
        panelBotaoDefinicoes.add(labelBotaoDefinicoes, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 0, 140, 50));

        imagemBotaoDefinicoes.setIcon(new javax.swing.ImageIcon("src/main/java/Recursos/definicoes.png"));
        imagemBotaoDefinicoes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelBotaoDefinicoesMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                labelBotaoDefinicoesMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                labelBotaoDefinicoesMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                labelBotaoDefinicoesMousePressed(evt);
            }
        });
        panelBotaoDefinicoes.add(imagemBotaoDefinicoes, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 30, 30));

        jPanel1.add(panelBotaoDefinicoes, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 470, 200, 50));

        panelBotaoDashboard.setBackground(new java.awt.Color(97, 99, 103));
        panelBotaoDashboard.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelBotaoDashboardMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                panelBotaoDashboardMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                panelBotaoDashboardMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                imagemBotaoDashboardMousePressed(evt);
            }
        });
        panelBotaoDashboard.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        labelBotaoDashbord.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        labelBotaoDashbord.setForeground(new java.awt.Color(255, 255, 255));
        labelBotaoDashbord.setText("Dashboard");
        labelBotaoDashbord.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelBotaoDashboardMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                panelBotaoDashboardMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                panelBotaoDashboardMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                imagemBotaoDashboardMousePressed(evt);
            }
        });
        panelBotaoDashboard.add(labelBotaoDashbord, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 0, 140, 50));

        imagemBotaoDashboard.setIcon(new javax.swing.ImageIcon("src/main/java/Recursos/dashboard.png"));
        imagemBotaoDashboard.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                panelBotaoDashboardMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                panelBotaoDashboardMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                imagemBotaoDashboardMousePressed(evt);
            }
        });
        panelBotaoDashboard.add(imagemBotaoDashboard, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 30, 30));

        jPanel1.add(panelBotaoDashboard, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, 200, 50));

        jLabel2.setIcon(new javax.swing.ImageIcon("src/main/java/Recursos/exit_branco.png"));
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 560, 30, 30));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1, 1, 220, 600));

        jPanel2.setBackground(new java.awt.Color(232, 235, 238));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(221, 51, 780, 550));

        jPanel3.setBackground(new java.awt.Color(255, 252, 252));
        jPanel3.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                labelPaginaMouseDragged(evt);
            }
        });
        jPanel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                labelPaginaMousePressed(evt);
            }
        });
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        labelPagina.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        labelPagina.setText("Centro de Artes de Águeda");
        labelPagina.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                labelPaginaMouseDragged(evt);
            }
        });
        labelPagina.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                labelPaginaMousePressed(evt);
            }
        });
        jPanel3.add(labelPagina, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, 250, 50));

        panelFechar.setBackground(new java.awt.Color(255, 252, 252));
        panelFechar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelFecharMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                panelFecharMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                panelFecharMouseExited(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                panelFecharMouseReleased(evt);
            }
        });
        panelFechar.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        labelFechar.setIcon(new javax.swing.ImageIcon("src/main/java/Recursos/fechar.png"));
        labelFechar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelFecharMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                panelFecharMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                panelFecharMouseExited(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                panelFecharMouseReleased(evt);
            }
        });
        panelFechar.add(labelFechar, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 15, 20, 20));

        jPanel3.add(panelFechar, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 0, 50, 50));

        panelMinimizar.setBackground(new java.awt.Color(255, 252, 252));
        panelMinimizar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelMinimizarMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                panelMinimizarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                panelMinimizarMouseExited(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                panelMinimizarMouseReleased(evt);
            }
        });
        panelMinimizar.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        labelMinimizar.setIcon(new javax.swing.ImageIcon("src/main/java/Recursos/minimizar.png"));
        labelMinimizar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelMinimizarMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                panelMinimizarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                panelMinimizarMouseExited(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                panelMinimizarMouseReleased(evt);
            }
        });
        panelMinimizar.add(labelMinimizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 15, 20, 20));

        jPanel3.add(panelMinimizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 0, 50, 50));

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(221, 1, 780, 50));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void panelBotaoDashboardMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelBotaoDashboardMouseEntered
        if (panelBotaoDashboard != painelSelecionado) {
            panelBotaoDashboard.setBackground(COR_HOVER);
        }
    }//GEN-LAST:event_panelBotaoDashboardMouseEntered

    private void panelBotaoDashboardMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelBotaoDashboardMouseExited
        if (panelBotaoDashboard != painelSelecionado) {
            panelBotaoDashboard.setBackground(COR_NORMAL);
        }
    }//GEN-LAST:event_panelBotaoDashboardMouseExited

    private void panelBotaoDashboardMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelBotaoDashboardMouseClicked
    }//GEN-LAST:event_panelBotaoDashboardMouseClicked

    private void imagemBotaoFuncionariosMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_imagemBotaoFuncionariosMouseEntered
        if (panelBotaoFuncionarios != painelSelecionado) {
            panelBotaoFuncionarios.setBackground(COR_HOVER);
        }
    }//GEN-LAST:event_imagemBotaoFuncionariosMouseEntered

    private void imagemBotaoFuncionariosMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_imagemBotaoFuncionariosMouseExited
        if (panelBotaoFuncionarios != painelSelecionado) {
            panelBotaoFuncionarios.setBackground(COR_NORMAL);
        }
    }//GEN-LAST:event_imagemBotaoFuncionariosMouseExited

    private void imagemBotaoFuncionariosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_imagemBotaoFuncionariosMouseClicked
    }//GEN-LAST:event_imagemBotaoFuncionariosMouseClicked

    private void labelBotaoSalasMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelBotaoSalasMouseEntered
        if (panelBotaoSalas != painelSelecionado) {
            panelBotaoSalas.setBackground(COR_HOVER);
        }
    }//GEN-LAST:event_labelBotaoSalasMouseEntered

    private void labelBotaoSalasMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelBotaoSalasMouseExited
        if (panelBotaoSalas != painelSelecionado) {
            panelBotaoSalas.setBackground(COR_NORMAL);
        }
    }//GEN-LAST:event_labelBotaoSalasMouseExited

    private void labelBotaoSalasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelBotaoSalasMouseClicked
    }//GEN-LAST:event_labelBotaoSalasMouseClicked

    private void labelBotaoEventosMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelBotaoEventosMouseEntered
        if (panelBotaoEventos != painelSelecionado) {
            panelBotaoEventos.setBackground(COR_HOVER);
        }
    }//GEN-LAST:event_labelBotaoEventosMouseEntered

    private void labelBotaoEventosMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelBotaoEventosMouseExited
        if (panelBotaoEventos != painelSelecionado) {
            panelBotaoEventos.setBackground(COR_NORMAL);
        }
    }//GEN-LAST:event_labelBotaoEventosMouseExited

    private void labelBotaoEventosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelBotaoEventosMouseClicked
    }//GEN-LAST:event_labelBotaoEventosMouseClicked

    private void imagemBotaoFinanceiroMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_imagemBotaoFinanceiroMouseEntered
        if (panelBotaoFinanceiro != painelSelecionado) {
            panelBotaoFinanceiro.setBackground(COR_HOVER);
        }
    }//GEN-LAST:event_imagemBotaoFinanceiroMouseEntered

    private void imagemBotaoFinanceiroMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_imagemBotaoFinanceiroMouseExited
        if (panelBotaoFinanceiro != painelSelecionado) {
            panelBotaoFinanceiro.setBackground(COR_NORMAL);
        }
    }//GEN-LAST:event_imagemBotaoFinanceiroMouseExited

    private void imagemBotaoFinanceiroMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_imagemBotaoFinanceiroMouseClicked
    }//GEN-LAST:event_imagemBotaoFinanceiroMouseClicked

    private void imagemBotaoRecursosMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_imagemBotaoRecursosMouseEntered
        if (panelBotaoRecursos != painelSelecionado) {
            panelBotaoRecursos.setBackground(COR_HOVER);
        }
    }//GEN-LAST:event_imagemBotaoRecursosMouseEntered

    private void imagemBotaoRecursosMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_imagemBotaoRecursosMouseExited
        if (panelBotaoRecursos != painelSelecionado) {
            panelBotaoRecursos.setBackground(COR_NORMAL);
        }
    }//GEN-LAST:event_imagemBotaoRecursosMouseExited

    private void imagemBotaoRecursosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_imagemBotaoRecursosMouseClicked
    }//GEN-LAST:event_imagemBotaoRecursosMouseClicked

    private void labelBotaoDefinicoesMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelBotaoDefinicoesMouseEntered
        if (panelBotaoDefinicoes != painelSelecionado) {
            panelBotaoDefinicoes.setBackground(COR_HOVER);
        }
    }//GEN-LAST:event_labelBotaoDefinicoesMouseEntered

    private void labelBotaoDefinicoesMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelBotaoDefinicoesMouseExited
        if (panelBotaoDefinicoes != painelSelecionado) {
            panelBotaoDefinicoes.setBackground(COR_NORMAL);
        }
    }//GEN-LAST:event_labelBotaoDefinicoesMouseExited

    private void labelBotaoDefinicoesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelBotaoDefinicoesMouseClicked
    }//GEN-LAST:event_labelBotaoDefinicoesMouseClicked

    private void imagemBotaoDashboardMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_imagemBotaoDashboardMousePressed
        linkN = "src/main/java/Recursos/dashboard.png";
        linkP = "src/main/java/Recursos/dashboard_p.png";
        labelPagina.setText("Dashboard");
        selecionarPainel(panelBotaoDashboard, imagemBotaoDashboard, labelBotaoDashbord, linkN, linkP);

        limparPainelCentral();
    }//GEN-LAST:event_imagemBotaoDashboardMousePressed

    private void labelBotaoFuncionariosMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelBotaoFuncionariosMousePressed
        int flag = verificarAcesso(5);
        if (flag == 1) {
            linkN = "src/main/java/Recursos/funcionarios.png";
            linkP = "src/main/java/Recursos/funcionarios_p.png";
            labelPagina.setText("Funcionarios");
            selecionarPainel(panelBotaoFuncionarios, imagemBotaoFuncionarios, labelBotaoFuncionarios, linkN, linkP);

            limparPainelCentral();

            trocarParaPainel(new PanelListaFuncionarios(this));
        }
        if (flag == 2) {
            PaginaDialogo dialogo = new PaginaDialogo(this, true);

            String mensagem = "O utilizador não tem permissão para aceder a esta aba.";
            String top = "Erro";
            String imagem = "src/main/java/Recursos/erro.png";
            dialogo.setMensagem(mensagem, top, imagem);
            dialogo.setVisible(true);
        }
        if (flag == 3) {
            PaginaDialogo dialogo = new PaginaDialogo(this, true);

            String mensagem = "O utilizador não tem permissão para aceder a esta aba.";
            String top = "Erro";
            String imagem = "src/main/java/Recursos/erro.png";
            dialogo.setMensagem(mensagem, top, imagem);
            dialogo.setVisible(true);
        }
    }//GEN-LAST:event_labelBotaoFuncionariosMousePressed

    private void panelBotaoSalasMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelBotaoSalasMousePressed
        int flag = verificarAcesso(4);
        if (flag == 1) {
            linkN = "src/main/java/Recursos/salas.png";
            linkP = "src/main/java/Recursos/salas_p.png";
            labelPagina.setText("Salas");
            selecionarPainel(panelBotaoSalas, imagemBotaoSalas, labelBotaoSalas, linkN, linkP);

            limparPainelCentral();

            trocarParaPainel(new PanelSalas(this));
        } else {
            flag = verificarAcesso(5);
            if (flag == 1) {
                linkN = "src/main/java/Recursos/salas.png";
                linkP = "src/main/java/Recursos/salas_p.png";
                labelPagina.setText("Salas");
                selecionarPainel(panelBotaoSalas, imagemBotaoSalas, labelBotaoSalas, linkN, linkP);

                limparPainelCentral();

                trocarParaPainel(new PanelSalas(this));
            } else {
                flag = verificarAcesso(2);
                if (flag == 1) {
                    linkN = "src/main/java/Recursos/salas.png";
                    linkP = "src/main/java/Recursos/salas_p.png";
                    labelPagina.setText("Salas");
                    selecionarPainel(panelBotaoSalas, imagemBotaoSalas, labelBotaoSalas, linkN, linkP);

                    limparPainelCentral();

                    trocarParaPainel(new PanelSalas(this));
                }
                if (flag == 2) {
                    PaginaDialogo dialogo = new PaginaDialogo(this, true);

                    String mensagem = "O utilizador não tem permissão para aceder a esta aba.";
                    String top = "Erro";
                    String imagem = "src/main/java/Recursos/erro.png";
                    dialogo.setMensagem(mensagem, top, imagem);
                    dialogo.setVisible(true);
                }
                if (flag == 3) {
                    PaginaDialogo dialogo = new PaginaDialogo(this, true);

                    String mensagem = "O utilizador não tem permissão para aceder a esta aba.";
                    String top = "Erro";
                    String imagem = "src/main/java/Recursos/erro.png";
                    dialogo.setMensagem(mensagem, top, imagem);
                    dialogo.setVisible(true);
                }
            }

        }
    }//GEN-LAST:event_panelBotaoSalasMousePressed

    private void labelBotaoEventosMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelBotaoEventosMousePressed
        int flag = verificarAcesso(2);
        if (flag == 1) {
            linkN = "src/main/java/Recursos/eventos.png";
            linkP = "src/main/java/Recursos/eventos_p.png";
            labelPagina.setText("Eventos");
            selecionarPainel(panelBotaoEventos, imagemBotaoEventos, labelBotaoEventos, linkN, linkP);

            limparPainelCentral();

            trocarParaPainel(new PanelEventos(this));
        } else {
            flag = verificarAcesso(5);
            if (flag == 1) {
                linkN = "src/main/java/Recursos/eventos.png";
                linkP = "src/main/java/Recursos/eventos_p.png";
                labelPagina.setText("Eventos");
                selecionarPainel(panelBotaoEventos, imagemBotaoEventos, labelBotaoEventos, linkN, linkP);

                limparPainelCentral();

                trocarParaPainel(new PanelEventos(this));
            }
            if (flag == 2) {
                PaginaDialogo dialogo = new PaginaDialogo(this, true);

                String mensagem = "O utilizador não tem permissão para aceder a esta aba.";
                String top = "Erro";
                String imagem = "src/main/java/Recursos/erro.png";
                dialogo.setMensagem(mensagem, top, imagem);
                dialogo.setVisible(true);
            }
            if (flag == 3) {
                PaginaDialogo dialogo = new PaginaDialogo(this, true);

                String mensagem = "O utilizador não tem permissão para aceder a esta aba.";
                String top = "Erro";
                String imagem = "src/main/java/Recursos/erro.png";
                dialogo.setMensagem(mensagem, top, imagem);
                dialogo.setVisible(true);
            }
        }


    }//GEN-LAST:event_labelBotaoEventosMousePressed

    private void imagemBotaoFinanceiroMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_imagemBotaoFinanceiroMousePressed
        int flag = verificarAcesso(1);
        if (flag == 1) {
            linkN = "src/main/java/Recursos/financeiro.png";
            linkP = "src/main/java/Recursos/financeiro_p.png";
            labelPagina.setText("Financeiro");
            selecionarPainel(panelBotaoFinanceiro, imagemBotaoFinanceiro, labelBotaoFinanceiro, linkN, linkP);

            limparPainelCentral();

            trocarParaPainel(new PanelFinanceiro(this));
        } else {
            flag = verificarAcesso(5);
            if (flag == 1) {
                linkN = "src/main/java/Recursos/financeiro.png";
                linkP = "src/main/java/Recursos/financeiro_p.png";
                labelPagina.setText("Financeiro");
                selecionarPainel(panelBotaoFinanceiro, imagemBotaoFinanceiro, labelBotaoFinanceiro, linkN, linkP);

                limparPainelCentral();

                trocarParaPainel(new PanelFinanceiro(this));
            }
            if (flag == 2) {
                PaginaDialogo dialogo = new PaginaDialogo(this, true);

                String mensagem = "O utilizador não tem permissão para aceder a esta aba.";
                String top = "Erro";
                String imagem = "src/main/java/Recursos/erro.png";
                dialogo.setMensagem(mensagem, top, imagem);
                dialogo.setVisible(true);
            }
            if (flag == 3) {
                PaginaDialogo dialogo = new PaginaDialogo(this, true);

                String mensagem = "O utilizador não tem permissão para aceder a esta aba.";
                String top = "Erro";
                String imagem = "src/main/java/Recursos/erro.png";
                dialogo.setMensagem(mensagem, top, imagem);
                dialogo.setVisible(true);
            }
        }
    }//GEN-LAST:event_imagemBotaoFinanceiroMousePressed

    private void imagemBotaoRecursosMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_imagemBotaoRecursosMousePressed
        int flag = verificarAcesso(3);
        if (flag == 1) {
            linkN = "src/main/java/Recursos/recursos.png";
            linkP = "src/main/java/Recursos/recursos_p.png";
            labelPagina.setText("Recursos");
            selecionarPainel(panelBotaoRecursos, imagemBotaoRecursos, labelBotaoRecursos, linkN, linkP);

            limparPainelCentral();

            trocarParaPainel(new PanelRecursos(this));
        } else {
            flag = verificarAcesso(5);
            if (flag == 1) {
                linkN = "src/main/java/Recursos/recursos.png";
                linkP = "src/main/java/Recursos/recursos_p.png";
                labelPagina.setText("Recursos");
                selecionarPainel(panelBotaoRecursos, imagemBotaoRecursos, labelBotaoRecursos, linkN, linkP);

                limparPainelCentral();

                trocarParaPainel(new PanelRecursos(this));
            }
            if (flag == 2) {
                PaginaDialogo dialogo = new PaginaDialogo(this, true);

                String mensagem = "O utilizador não tem permissão para aceder a esta aba.";
                String top = "Erro";
                String imagem = "src/main/java/Recursos/erro.png";
                dialogo.setMensagem(mensagem, top, imagem);
                dialogo.setVisible(true);
            }
            if (flag == 3) {
                PaginaDialogo dialogo = new PaginaDialogo(this, true);

                String mensagem = "O utilizador não tem permissão para aceder a esta aba.";
                String top = "Erro";
                String imagem = "src/main/java/Recursos/erro.png";
                dialogo.setMensagem(mensagem, top, imagem);
                dialogo.setVisible(true);
            }
        }
    }//GEN-LAST:event_imagemBotaoRecursosMousePressed

    private void labelBotaoDefinicoesMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelBotaoDefinicoesMousePressed
        linkN = "src/main/java/Recursos/definicoes.png";
        linkP = "src/main/java/Recursos/definicoes_p.png";
        labelPagina.setText("Definções");
        selecionarPainel(panelBotaoDefinicoes, imagemBotaoDefinicoes, labelBotaoDefinicoes, linkN, linkP);

        limparPainelCentral();
    }//GEN-LAST:event_labelBotaoDefinicoesMousePressed

    Point initialClick;

    private void labelPaginaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelPaginaMousePressed
        initialClick = evt.getPoint();
    }//GEN-LAST:event_labelPaginaMousePressed

    private void labelPaginaMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelPaginaMouseDragged
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(jPanel1);

        int deltaX = evt.getX() - initialClick.x;
        int deltaY = evt.getY() - initialClick.y;
        frame.setLocation(frame.getX() + deltaX, frame.getY() + deltaY);
    }//GEN-LAST:event_labelPaginaMouseDragged

    private void panelMinimizarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelMinimizarMouseEntered
        panelMinimizar.setBackground(COR_SELECIONADO);
    }//GEN-LAST:event_panelMinimizarMouseEntered

    private void panelMinimizarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelMinimizarMouseExited
        panelMinimizar.setBackground(COR_NORMAL_TOP);
    }//GEN-LAST:event_panelMinimizarMouseExited

    private void panelFecharMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelFecharMouseEntered
        panelFechar.setBackground(COR_HOVER_FECHAR);
        labelFechar.setIcon(new ImageIcon("src/main/java/Recursos/fechar_b.png"));
    }//GEN-LAST:event_panelFecharMouseEntered

    private void panelFecharMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelFecharMouseExited
        panelFechar.setBackground(COR_NORMAL_TOP);
        labelFechar.setIcon(new ImageIcon("src/main/java/Recursos/fechar.png"));
    }//GEN-LAST:event_panelFecharMouseExited

    private void panelFecharMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelFecharMouseClicked
        System.exit(0);
    }//GEN-LAST:event_panelFecharMouseClicked

    private void panelMinimizarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelMinimizarMouseClicked
        this.setState(JFrame.ICONIFIED);
    }//GEN-LAST:event_panelMinimizarMouseClicked

    private void panelMinimizarMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelMinimizarMouseReleased
    }//GEN-LAST:event_panelMinimizarMouseReleased

    private void panelFecharMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelFecharMouseReleased
    }//GEN-LAST:event_panelFecharMouseReleased

    // Método auxiliar para limpar o painel central
    private void limparPainelCentral() {
        jPanel2.removeAll();
        jPanel2.revalidate();
        jPanel2.repaint();
    }

    private int verificarAcesso(int categoria) {
        Trabalhador t = controller.procurarTrabalhador(idTrabalhador);

        if (t != null) {

            if (t.getCategoria() == categoria) {
                return 1;
            } else {
                return 2;
            }

        }
        return 3;
    }

    // Método genérico que troca o conteúdo do jPanel2 por qualquer painel que lhe dês
    private void trocarParaPainel(javax.swing.JPanel novoPainel) {
        jPanel2.removeAll(); // Limpa
        jPanel2.setLayout(new java.awt.BorderLayout()); // Prepara layout
        jPanel2.add(novoPainel, java.awt.BorderLayout.CENTER); // Adiciona o novo
        jPanel2.revalidate(); // Atualiza
        jPanel2.repaint();    // Desenha
    }

    // Método público para ser chamado pelos botões "Adicionar"
    public void irParaFormularioFuncionarios() {
        //Cria o painel do formulário
        PanelFormularioFuncionario form = new PanelFormularioFuncionario(this);
        trocarParaPainel(form);
    }

    public void irParaFormularioEspacos() {
        //Cria o painel do formulário
        PanelFormularioEspacos form = new PanelFormularioEspacos(this);
        trocarParaPainel(form);
    }

    public void irParaFormularioEspacos(int idEspaco) {
        //Cria o painel do formulário
        PanelFormularioEspacos form = new PanelFormularioEspacos(this, idEspaco);
        trocarParaPainel(form);
    }

    public void irParaFormularioSalas() {
        //Cria o painel do formulário
        PanelFormularioSalas form = new PanelFormularioSalas(this);
        trocarParaPainel(form);
    }
    
    public void irParaFormularioSalas(int idSala) {
        //Cria o painel do formulário
        PanelFormularioSalas form = new PanelFormularioSalas(this, idSala);
        trocarParaPainel(form);
    }

    public void irParaEditarFuncionario(String id, String nome, String email, String categoria) {
        //Cria o painel do formulário
        PanelFormularioFuncionario form = new PanelFormularioFuncionario(this);

        //Chama o método que criámos acima para preencher os dados e mudar o título
        form.preencherDadosParaEdicao(id, nome, email, categoria);

        //Troca o painel
        trocarParaPainel(form);
    }

    public void irParaFormularioEventos() {
        //Cria o painel do evento
        PanelFormularioEventos form = new PanelFormularioEventos(this);
        trocarParaPainel(form);
    }

    public void irParaFormularioRecursos(int tipoAba, Object recurso) {
        PanelFormularioRecursos form = new PanelFormularioRecursos(this);
        form.prepararFormulario(tipoAba, recurso); // ESTA LINHA É ESSENCIAL
        trocarParaPainel(form);
    }

    public void irParaFormularioFinanceiro() {
        //Cria o painel do formulario do recurso
        PanelFormularioFinanceiro form = new PanelFormularioFinanceiro(this);
        trocarParaPainel(form);
    }

    public void mostrarListaFuncionarios() {
        //Instancia o painel da lista
        PanelListaFuncionarios lista = new PanelListaFuncionarios(this);

        //troca o painel
        trocarParaPainel(lista);
    }

    public void mostrarListaEventos() {
        //Instancia o painel da lista
        PanelEventos lista = new PanelEventos(this);

        //troca o painel
        trocarParaPainel(lista);
    }

    public void mostrarListaRecursos() {
        //Instancia o painel da lista
        PanelRecursos lista = new PanelRecursos(this);

        //troca o painel
        trocarParaPainel(lista);
    }

    public void mostrarListaFinanceiro() {
        //Instancia o painel da lista
        PanelFinanceiro lista = new PanelFinanceiro(this);

        //troca o painel
        trocarParaPainel(lista);
    }

    public void mostrarListaSalas() {
        //Instancia o painel da lista
        PanelSalas lista = new PanelSalas(this);

        //troca o painel
        trocarParaPainel(lista);
    }

    public static void main(String args[]) {
        try {
            com.formdev.flatlaf.FlatLightLaf.setup();
        } catch (Exception ex) {
            System.err.println("Falha ao iniciar o FlatLaf");
        }
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PaginaInicial().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel imagemBotaoDashboard;
    private javax.swing.JLabel imagemBotaoDefinicoes;
    private javax.swing.JLabel imagemBotaoEventos;
    private javax.swing.JLabel imagemBotaoFinanceiro;
    private javax.swing.JLabel imagemBotaoFuncionarios;
    private javax.swing.JLabel imagemBotaoRecursos;
    private javax.swing.JLabel imagemBotaoSalas;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel labelBotaoDashbord;
    private javax.swing.JLabel labelBotaoDefinicoes;
    private javax.swing.JLabel labelBotaoEventos;
    private javax.swing.JLabel labelBotaoFinanceiro;
    private javax.swing.JLabel labelBotaoFuncionarios;
    private javax.swing.JLabel labelBotaoRecursos;
    private javax.swing.JLabel labelBotaoSalas;
    private javax.swing.JLabel labelFechar;
    private javax.swing.JLabel labelMinimizar;
    private javax.swing.JLabel labelPagina;
    private javax.swing.JPanel panelBotaoDashboard;
    private javax.swing.JPanel panelBotaoDefinicoes;
    private javax.swing.JPanel panelBotaoEventos;
    private javax.swing.JPanel panelBotaoFinanceiro;
    private javax.swing.JPanel panelBotaoFuncionarios;
    private javax.swing.JPanel panelBotaoRecursos;
    private javax.swing.JPanel panelBotaoSalas;
    private javax.swing.JPanel panelFechar;
    private javax.swing.JPanel panelMinimizar;
    // End of variables declaration//GEN-END:variables
}

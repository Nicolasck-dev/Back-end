package view;
import controller.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TelaDePesquisaView extends JFrame {
    public static final JTextField txtPesquisa = new JTextField(20);
    public final JButton btnPesquisar;
    public final JButton btnReiniciarPesquisa;

    public final JLabel lblId;
    public static final JTextField txtId = new JTextField(10);

    public final JLabel lblNome;
    public static final JTextField txtNome = new JTextField(10);

    public final JLabel lblEmail;
    public static final JTextField txtEmail = new JTextField(10);

    public static final JButton btnPrimeiro = new JButton("<<");;
    public static final JButton btnAnterior = new JButton("<");;
    public static final JButton btnProximo = new JButton(">");;
    public static final JButton btnUltimo = new JButton(">>");;

    public static final JLabel lblNotificacoes = new JLabel("Notificações", SwingConstants.CENTER);

    public TelaDePesquisaView() {
        super("Tela de Pesquisa");
        setLayout(new GridLayout(7,1,5,5));

        JPanel linhaInputPesquisa = new JPanel(new GridLayout(1,1));
        // txtPesquisa = new JTextField(20);
        linhaInputPesquisa.add(txtPesquisa);
        add(linhaInputPesquisa);

        JPanel linhaBotaoPesquisar = new JPanel(new GridLayout(1,2));

        btnPesquisar = new JButton("Pesquisar");
        btnPesquisar.setEnabled(false);
        linhaBotaoPesquisar.add(btnPesquisar);

        btnReiniciarPesquisa = new JButton("Reiniciar Pesquisa");
        linhaBotaoPesquisar.add(btnReiniciarPesquisa);
        add(linhaBotaoPesquisar);

        JPanel linhaId = new JPanel(new GridLayout(1,2));
        lblId = new JLabel("Id:", SwingConstants.RIGHT);
        // txtId = new JTextField(10);
        txtId.setEditable(false);
        linhaId.add(lblId);
        linhaId.add(txtId);
        add(linhaId);

        JPanel linhaNome = new JPanel(new GridLayout(1,2));
        lblNome = new JLabel("Nome:", SwingConstants.RIGHT);
        // txtNome = new JTextField(10);
        txtNome.setEditable(false);
        linhaNome.add(lblNome);
        linhaNome.add(txtNome);
        add(linhaNome);

        JPanel linhaEmail = new JPanel(new GridLayout(1,2));
        lblEmail = new JLabel("Email:", SwingConstants.RIGHT);
        // txtEmail = new JTextField(10);
        txtEmail.setEditable(false);
        linhaEmail.add(lblEmail);
        linhaEmail.add(txtEmail);
        add(linhaEmail);

        JPanel linhaBotoes = new JPanel(new GridLayout(1,4));
        // btnPrimeiro = new JButton("<<");
        // btnAnterior = new JButton("<");
        // btnProximo = new JButton(">");
        // btnUltimo = new JButton(">>");
        linhaBotoes.add(btnPrimeiro);
        linhaBotoes.add(btnAnterior);
        linhaBotoes.add(btnProximo);
        linhaBotoes.add(btnUltimo);
        add(linhaBotoes);

        JPanel linhaNotificacoes = new JPanel(new GridLayout(1,1));
        // lblNotificacoes = new JLabel("Notificações", SwingConstants.CENTER);
        linhaNotificacoes.add(lblNotificacoes);
        add(linhaNotificacoes);

        btnPrimeiro.addActionListener(
            new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent event) {
                    TelaDePesquisaController.vaParaPrimeiroRegistro();
                }
            }
        );

        btnAnterior.addActionListener(
            new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent event) {
                    TelaDePesquisaController.vaParaRegistroAnterior();
                }
            }
        );

        btnProximo.addActionListener(
            new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent event) {
                    TelaDePesquisaController.vaParaProximoRegistro();
                }
            }
        );

        btnUltimo.addActionListener(
            new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent event) {
                    TelaDePesquisaController.vaParaUltimoRegistro();
                }
            }
        );

        btnPesquisar.addActionListener(
            new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent event) {
                    TelaDePesquisaController.registrarPesquisa();
                    txtPesquisa.setText(txtPesquisa.getText().trim());
                }
            }
        );

        txtPesquisa.addKeyListener(
            new KeyAdapter() {
                @Override
                public void keyReleased(KeyEvent e) {
                    btnPesquisar.setEnabled(detectarPesquisa());
                    if (e.getKeyCode() == 10 && txtPesquisa.getText().trim().length() > 0) {
                        TelaDePesquisaController.registrarPesquisa();
                        txtPesquisa.setText(txtPesquisa.getText().trim());
                    }
                    // if (e.getKeyCode() == 39) { // seta para direita
                    //     TelaDePesquisaController.vaParaProximoRegistro();
                    // }
                    // if (e.getKeyCode() == 40) { // seta para baixo
                    //     TelaDePesquisaController.vaParaPrimeiroRegistro();
                    // }
                    // if (e.getKeyCode() == 37) { // seta para esquerda
                    //     TelaDePesquisaController.vaParaRegistroAnterior();
                    // }
                    // if (e.getKeyCode() == 38) { // seta para cima
                    //     TelaDePesquisaController.vaParaUltimoRegistro();
                    // }
                }
            }
        );

        btnReiniciarPesquisa.addActionListener(
            new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    txtPesquisa.setText("");
                    TelaDePesquisaController.registroDePesquisa = "";
                    TelaDePesquisaController.clausulasDePesquisaComWhere = "";
                    TelaDePesquisaController.clausulasDePesquisaSemWhere = "";
                    TelaDePesquisaController.vaParaPrimeiroRegistro();
                    btnPesquisar.setEnabled(false);
                    txtPesquisa.requestFocus();
                }
            }
        );

        setSize(300,300);
        setVisible(true);

        TelaDePesquisaController.inicializacaoDeRegistros();
    }

    public static void notificarUsuario(String strTexto) {
        lblNotificacoes.setText(setHtmlFormat(strTexto));
    }

    public static String setHtmlFormat(String strTexto) {
        return "<html><body>" + strTexto + "</body></html>";
    }

    public static void habilitarVoltar() {
        btnPrimeiro.setEnabled(true);
        btnAnterior.setEnabled(true);
        btnProximo.setEnabled(false);
        btnUltimo.setEnabled(false);
    }

    public static void habilitarAvancar() {
        btnPrimeiro.setEnabled(false);
        btnAnterior.setEnabled(false);
        btnProximo.setEnabled(true);
        btnUltimo.setEnabled(true);
    }

    public static void habilitarTodos() {
        btnPrimeiro.setEnabled(true);
        btnAnterior.setEnabled(true);
        btnProximo.setEnabled(true);
        btnUltimo.setEnabled(true);
    }

    public static void desabilitarTodos() {
        btnPrimeiro.setEnabled(false);
        btnAnterior.setEnabled(false);
        btnProximo.setEnabled(false);
        btnUltimo.setEnabled(false);
    }

    public static boolean detectarPesquisa() {
        if (txtPesquisa.getText().trim().length() > 0) {
            return true;
        } else {
            return false;
        }
    }

    public static void limparCampos() {
        txtId.setText("");
        txtNome.setText("");
        txtEmail.setText("");
    }
    public static TelaDePesquisaView appTelaDePesquisaView;
    public static void main(String[] args) {
        appTelaDePesquisaView = new TelaDePesquisaView();
        appTelaDePesquisaView.setDefaultCloseOperation(EXIT_ON_CLOSE);

        // appTelaDePesquisa.addKeyListener(
        //     new KeyAdapter() {
        //         @Override
        //         public void keyReleased(KeyEvent e) {
        //             System.out.println(e.getKeyCode());
        //             if (e.getKeyCode() == 2) {
                    
        //             }
        //         }
        //     }
        // );
    }
}
package view;
import controller.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TelaDePesquisaView extends JFrame {
    public static JLabel lblFoto;
    public static final JTextField txtPesquisa = new JTextField(20);
    public final JButton btnPesquisar;
    public final JButton btnReiniciarPesquisa;
    public final JButton btnHistoricoPesquisa;

    public final JLabel lblId;
    public static final JTextField txtId = new JTextField(10);

    public final JLabel lblNome;
    public static final JTextField txtNome = new JTextField(10);

    public final JLabel lblEmail;
    public static final JTextField txtEmail = new JTextField(10);

    public static final JButton btnPrimeiro = new JButton("<<");
    public static final JButton btnAnterior = new JButton("<");
    public static final JButton btnProximo = new JButton(">");
    public static final JButton btnUltimo = new JButton(">>");

    public static final JLabel lblNotificacoes = new JLabel("Notificações", SwingConstants.CENTER);

    public static GridBagLayout gbLayout;
    public static GridBagConstraints gbConstraints;

    public TelaDePesquisaView() {
        super("Tela de Pesquisa");
        gbLayout = new GridBagLayout();
        setLayout(gbLayout);
        gbConstraints = new GridBagConstraints();

        try{
            ImageIcon orifinalIcon = new ImageIcon("src\\view\\senac-logo.png");
            Image resizedIcon = orifinalIcon.getImage().getScaledInstance(94, 95,Image.SCALE_SMOOTH);
            setIconImage(resizedIcon);
        } catch (Exception e) {
            e.printStackTrace();
        }

        lblFoto = new JLabel("", SwingConstants.CENTER);
        lblFoto.setIcon(new ImageIcon(new ImageIcon(InterfaceView.localViewFolder + "\\imagem-padrao.jpg").getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT)));
        InterfaceView.addComponent(lblFoto, 0, 0, 4, 2, gbLayout, gbConstraints, this);

        InterfaceView.addComponent(txtPesquisa, 2, 0, 4, 1, gbLayout, gbConstraints, this);

        btnPesquisar = new JButton("Pesquisar");
        btnPesquisar.setEnabled(false);
        InterfaceView.addComponent(btnPesquisar, 3, 0, 1, 1, gbLayout, gbConstraints, this);

        btnReiniciarPesquisa = new JButton("Reiniciar");
        InterfaceView.addComponent(btnReiniciarPesquisa, 3, 1, 2, 1, gbLayout, gbConstraints, this);

        btnHistoricoPesquisa = new JButton("Histórico");
        InterfaceView.addComponent(btnHistoricoPesquisa, 3, 3, 1, 1, gbLayout, gbConstraints, this);

        lblId = new JLabel("Id:", SwingConstants.RIGHT);
        InterfaceView.addComponent(lblId, 4, 0, 2, 1, gbLayout, gbConstraints, this);

        txtId.setEditable(false);
        InterfaceView.addComponent(txtId, 4, 2, 2, 1, gbLayout, gbConstraints, this);

        lblNome = new JLabel("Nome:", SwingConstants.RIGHT);
        InterfaceView.addComponent(lblNome, 5, 0, 2, 1, gbLayout, gbConstraints, this);

        txtNome.setEditable(false);
        InterfaceView.addComponent(txtNome, 5, 2, 2, 1, gbLayout, gbConstraints, this);

        lblEmail = new JLabel("Email:", SwingConstants.RIGHT);
        InterfaceView.addComponent(lblEmail, 6, 0, 2, 1, gbLayout, gbConstraints, this);

        txtEmail.setEditable(false);
        InterfaceView.addComponent(txtEmail, 6, 2, 2, 1, gbLayout, gbConstraints, this);

        InterfaceView.addComponent(btnPrimeiro, 7, 0, 1, 1, gbLayout, gbConstraints, this);
        InterfaceView.addComponent(btnAnterior, 7, 1, 1, 1, gbLayout, gbConstraints, this);
        InterfaceView.addComponent(btnProximo, 7, 2, 1, 1, gbLayout, gbConstraints, this);
        InterfaceView.addComponent(btnUltimo, 7, 3, 1, 1, gbLayout, gbConstraints, this);

        InterfaceView.addComponent(lblNotificacoes, 8, 0, 4, 1, gbLayout, gbConstraints, this);

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

        btnHistoricoPesquisa.addActionListener(
            new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    TelaDeHistoricoView.appTelaDeHistoricoView = new TelaDeHistoricoView();
                    TelaDeHistoricoView.appTelaDeHistoricoView.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

                    TelaDeHistoricoView.appTelaDeHistoricoView.addWindowListener(
                        new WindowAdapter() {
                            public void windowClosing(WindowEvent e) {
                                System.out.println("ok");
                                TelaDePesquisaView.appTelaDePesquisaView.setVisible(true);
                                TelaDeHistoricoView.appTelaDeHistoricoView.dispose();
                            }
                        }
                    );

                    setVisible(false);
                }
            }
        );

        setSize(300,340);
        setVisible(true);

        TelaDePesquisaController.inicializacaoDeRegistros();
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
        // InterfaceView.idLoginAtual = "16";
        if (InterfaceView.idLoginAtual.equals("")) {
            TelaDeLoginView.appTelaDeLoginView = new TelaDeLoginView();
            TelaDeLoginView.appTelaDeLoginView.setDefaultCloseOperation(EXIT_ON_CLOSE);
        } else {
            appTelaDePesquisaView = new TelaDePesquisaView();
            appTelaDePesquisaView.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            // InterfaceView.verificarLarguraEAltura(appTelaDeAtualizacaoView,lblNotificacoes);
        }
    }
}
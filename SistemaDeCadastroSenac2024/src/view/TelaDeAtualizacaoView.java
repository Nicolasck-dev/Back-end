package view;
import controller.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TelaDeAtualizacaoView extends JFrame {
    public static JLabel lblFoto;
    public static JButton btnCarregarFoto;
    public static JButton btnRemoverFoto;
    public static String nomeArquivoFoto = "";

    public static JLabel lblId;
    public static JComboBox<String> cbxId;

    public static JLabel lblNome;
    public static JTextField txtNome;
    public static String txtNomeCarregado;

    public static JLabel lblEmail;
    public static JTextField txtEmail;
    public static String txtEmailCarregado;

    public static JLabel lblSenha;
    public static JPasswordField txtSenha;

    public static JButton btnAtualizar;
    public static JButton btnCancelar;

    public static JLabel lblNotificacoes;

    public static GridBagLayout gbLayout;
    public static GridBagConstraints gbConstraints;

    public TelaDeAtualizacaoView() {
        super("Tela de Atualização");

        gbLayout = new GridBagLayout();
        setLayout(gbLayout);
        gbConstraints = new GridBagConstraints();

        lblFoto = new JLabel("", SwingConstants.CENTER);
        lblFoto.setIcon(new ImageIcon(new ImageIcon(InterfaceView.localViewFolder + "\\imagem-padrao.jpg").getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT)));
        InterfaceView.addComponent(lblFoto, 0, 0, 2, 2, gbLayout, gbConstraints, this);

        btnCarregarFoto = new JButton("Carregar foto");
        InterfaceView.addComponent(btnCarregarFoto, 2, 0, 1, 1, gbLayout, gbConstraints, this);

        btnRemoverFoto = new JButton("Remover foto");
        InterfaceView.addComponent(btnRemoverFoto, 2, 1, 1, 1, gbLayout, gbConstraints, this);

        lblId = new JLabel("Id:", SwingConstants.RIGHT);
        InterfaceView.addComponent(lblId, 3, 0, 1, 1, gbLayout, gbConstraints, this);

        cbxId = new JComboBox<String>();
        TelaDeAtualizacaoController.popularCbxIdController();
        InterfaceView.addComponent(cbxId, 3, 1, 1, 1, gbLayout, gbConstraints, this);

        lblNome = new JLabel("Nome:", SwingConstants.RIGHT);
        InterfaceView.addComponent(lblNome, 4, 0, 1, 1, gbLayout, gbConstraints, this);

        txtNome = new JTextField(10);
        InterfaceView.addComponent(txtNome, 4, 1, 1, 1, gbLayout, gbConstraints, this);

        lblEmail = new JLabel("Email:", SwingConstants.RIGHT);
        InterfaceView.addComponent(lblEmail, 5, 0, 1, 1, gbLayout, gbConstraints, this);

        txtEmail = new JTextField(10);
        InterfaceView.addComponent(txtEmail, 5, 1, 1, 1, gbLayout, gbConstraints, this);

        TelaDeAtualizacaoController.atualizarCamposController();

        lblSenha = new JLabel("Senha:", SwingConstants.RIGHT);
        InterfaceView.addComponent(lblSenha, 6, 0, 1, 1, gbLayout, gbConstraints, this);

        txtSenha = new JPasswordField(10);
        InterfaceView.addComponent(txtSenha, 6, 1, 1, 1, gbLayout, gbConstraints, this);

        btnAtualizar = new JButton("Atualizar");
        btnAtualizar.setEnabled(false);
        InterfaceView.addComponent(btnAtualizar, 7, 0, 1, 1, gbLayout, gbConstraints, this);

        btnCancelar = new JButton("Cancelar");
        InterfaceView.addComponent(btnCancelar, 7, 1, 1, 1, gbLayout, gbConstraints, this);

        lblNotificacoes = new JLabel("Notificações", SwingConstants.CENTER);
        InterfaceView.addComponent(lblNotificacoes, 8, 0, 2, 1, gbLayout, gbConstraints, this);

        cbxId.addItemListener(
            new ItemListener() {
                @Override
                public void itemStateChanged(ItemEvent event) {
                    if (event.getStateChange() == ItemEvent.SELECTED) {
                        TelaDeAtualizacaoController.atualizarCamposController();
                    }
                }
            }
        );

        // cbxId.addActionListener(
        //     new ActionListener() {
        //         @Override
        //         public void actionPerformed(ActionEvent event) {
        //             if (!txtNome.getText().trim().equals(txtNomeCarregado.trim()) && JOptionPane.showConfirmDialog(null, "Nome modificado! Deseja alternar para outro id?") == JOptionPane.CANCEL_OPTION) {
        //                 return;
        //             }
        //             System.out.println("aqui ok");
        //         }
        //     }
        // );

        btnAtualizar.addActionListener(
            new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent event) {
                    TelaDeAtualizacaoController.atualizarController();
                }
            }
        );

        txtNome.addKeyListener(
            new KeyAdapter() {
                @Override
                public void keyReleased(KeyEvent event) {
                    if (txtNomeCarregado.trim().equals(txtNome.getText().trim())) {
                        btnAtualizar.setEnabled(false);
                    } else {
                        btnAtualizar.setEnabled(true);
                    }
                }
            }
        );

        txtEmail.addKeyListener(
            new KeyAdapter() {
                @Override
                public void keyReleased(KeyEvent event) {
                    if (txtEmailCarregado.trim().equals(txtEmail.getText().trim())) {
                        btnAtualizar.setEnabled(false);
                    } else {
                        btnAtualizar.setEnabled(true);
                    }
                }
            }
        );

        txtSenha.addKeyListener(
            new KeyAdapter() {
                @Override
                public void keyReleased(KeyEvent event) {
                    if (String.valueOf(txtSenha.getPassword()).trim().length() == 0) {
                        btnAtualizar.setEnabled(false);
                    } else {
                        btnAtualizar.setEnabled(true);
                    }
                }
            }
        );

        btnCancelar.addActionListener(
            new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent event) {
                    TelaDeMenuView.appTelaDeMenuView.setVisible(true);
                    dispose();
                }
            }
        );

        btnCarregarFoto.addActionListener(
            new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent event) {
                    TelaDeAtualizacaoController.carregarFoto();
                }
            }
        );

        btnRemoverFoto.addActionListener(
            new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent event) {
                    TelaDeAtualizacaoController.removerFoto();
                }
            }
        );

        setSize(350,350);
        setVisible(true);
    }

    public static TelaDeAtualizacaoView appTelaDeAtualizacaoView;
    public static void main(String[] args) {
        appTelaDeAtualizacaoView = new TelaDeAtualizacaoView();
        appTelaDeAtualizacaoView.setDefaultCloseOperation(EXIT_ON_CLOSE);
        // InterfaceView.verificarLarguraEAltura(appTelaDeAtualizacaoView.lblNotificacoes);
    }
}
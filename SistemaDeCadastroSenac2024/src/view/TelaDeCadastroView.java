package view;
import controller.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TelaDeCadastroView extends JFrame {
    public static JLabel lblFoto;
    public static JButton btnCarregarFoto;
    public static JButton btnRemoverFoto;
    public static String nomeArquivoFoto = "";

    public static JLabel lblNome;
    public static JTextField txtNome;

    public static JLabel lblEmail;
    public static JTextField txtEmail;

    public static JLabel lblSenha;
    public static JPasswordField txtSenha;

    public static JButton btnCadastrar;
    public static JButton btnCancelar;

    public static JLabel lblNotificacoes;

    public static GridBagLayout gbLayout;
    public static GridBagConstraints gbConstraints;

    public TelaDeCadastroView() {
        super("Tela de Cadastro");

        gbLayout = new GridBagLayout();
        setLayout(gbLayout);
        gbConstraints = new GridBagConstraints();

        lblFoto = new JLabel("", SwingConstants.CENTER);
        lblFoto.setIcon(new ImageIcon(new ImageIcon(InterfaceView.localViewFolder + "\\imagem-padrao.jpg.png").getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT)));
        InterfaceView.addComponent(lblFoto, 0, 0, 2, 2, gbLayout, gbConstraints, this);

        btnCarregarFoto = new JButton("Carregar foto");
        InterfaceView.addComponent(btnCarregarFoto, 2, 0, 1, 1, gbLayout, gbConstraints, this);

        btnRemoverFoto = new JButton("Remover foto");
        InterfaceView.addComponent(btnRemoverFoto, 2, 1, 1, 1, gbLayout, gbConstraints, this);

        lblNome = new JLabel("Nome:", SwingConstants.RIGHT);
        InterfaceView.addComponent(lblNome, 3, 0, 1, 1, gbLayout, gbConstraints, this);

        txtNome = new JTextField(10);
        InterfaceView.addComponent(txtNome, 3, 1, 1, 1, gbLayout, gbConstraints, this);

        lblEmail = new JLabel("Email:", SwingConstants.RIGHT);
        InterfaceView.addComponent(lblEmail, 4, 0, 1, 1, gbLayout, gbConstraints, this);

        txtEmail = new JTextField(10);
        InterfaceView.addComponent(txtEmail, 4, 1, 1, 1, gbLayout, gbConstraints, this);

        lblSenha = new JLabel("Senha:", SwingConstants.RIGHT);
        InterfaceView.addComponent(lblSenha, 5, 0, 1, 1, gbLayout, gbConstraints, this);

        txtSenha = new JPasswordField(10);
        InterfaceView.addComponent(txtSenha, 5, 1, 1, 1, gbLayout, gbConstraints, this);

        btnCadastrar = new JButton("Cadastrar");
        InterfaceView.addComponent(btnCadastrar, 6, 0, 1, 1, gbLayout, gbConstraints, this);

        btnCancelar = new JButton("Cancelar");
        InterfaceView.addComponent(btnCancelar, 6, 1, 1, 1, gbLayout, gbConstraints, this);

        lblNotificacoes = new JLabel("Notificações", SwingConstants.CENTER);
        InterfaceView.addComponent(lblNotificacoes, 7, 0, 2, 1, gbLayout, gbConstraints, this);

        btnCadastrar.addActionListener(
            new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent event) {
                    if (txtNome.getText().trim().length() == 0) {
                        lblNotificacoes.setText("É necessário digitar alguma coisa no campo Nome. Por favor, digite um caracter válido no campo Nome para prosseguir.");
                        txtNome.requestFocus();
                        return;
                    }

                    if (txtEmail.getText().trim().length() == 0) {
                        lblNotificacoes.setText("É necessário digitar alguma coisa no campo Email. Por favor, digite um caracter válido no campo Email para prosseguir.");
                        txtEmail.requestFocus();
                        return;
                    }

                    if (String.valueOf(txtSenha.getPassword()).trim().length() == 0) {
                        lblNotificacoes.setText("É necessário digitar alguma coisa no campo Senha. Por favor, digite um caracter válido no campo Senha para prosseguir.");
                        txtSenha.requestFocus();
                        return;
                    }

                    TelaDeCadastroController.cadastrarController();
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
                    TelaDeCadastroController.carregarFoto();
                }
            }
        );

        btnRemoverFoto.addActionListener(
            new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent event) {
                    TelaDeCadastroController.removerFoto();
                }
            }
        );

        setSize(280,280);
        setVisible(true);
    }


  

    public static String setHtmlFormat(String str) {
        return "<html><body>" + str + "</body></html>";
    }

    

    public static TelaDeCadastroView appTelaDeCadastroView;
    public static void main(String[] args) {
        appTelaDeCadastroView = new TelaDeCadastroView();
        appTelaDeCadastroView.setDefaultCloseOperation(EXIT_ON_CLOSE);
        // InterfaceView.verificarLarguraEAltura(appTelaDeAtualizacaoViewlblNotificacoes);
    }
}
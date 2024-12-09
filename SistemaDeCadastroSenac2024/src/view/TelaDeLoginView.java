package view;

import controller.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TelaDeLoginView extends JFrame {
    public static JLabel lblLogin;
    public static JTextField txtLogin;
    public static JLabel lblSenha;
    public static JPasswordField txtSenha;
    public static JButton btnLogar;
    public static JLabel lblNotificacoes;


    public static GridBagLayout gbLayout;
    public static GridBagConstraints gbConstraints;

    public TelaDeLoginView() {
        super("Tela de Login");
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

        lblLogin = new JLabel("Login:");
        InterfaceView.addComponent(lblLogin, 0, 0, 1, 1, gbLayout, gbConstraints, this);

        txtLogin = new JTextField(10);
        InterfaceView.addComponent(txtLogin, 0, 1, 1, 1, gbLayout, gbConstraints, this);

        lblSenha = new JLabel("Senha:");
        InterfaceView.addComponent(lblSenha, 1, 0, 1, 1, gbLayout, gbConstraints, this);

        txtSenha = new JPasswordField(10);
        InterfaceView.addComponent(txtSenha, 1, 1, 1, 1, gbLayout, gbConstraints, this);

        btnLogar = new JButton("Logar");
        InterfaceView.addComponent(btnLogar, 2, 0, 2, 1, gbLayout, gbConstraints, this);

        lblNotificacoes = new JLabel("Notificações", SwingConstants.CENTER);
        // lblNotificacoes.setSize(getContentPane().getWidth(), 40);
        InterfaceView.addComponent(lblNotificacoes, 3, 0, 2, 1, gbLayout, gbConstraints, this);

        ButtonHandler buttonHandler = new ButtonHandler();
        btnLogar.addActionListener(buttonHandler);

        txtSenha.addKeyListener(
            new KeyAdapter() {
                @Override
                public void keyReleased(KeyEvent e) {
                    if (String.valueOf(txtSenha.getPassword()).trim().length() > 0) {
                        if (e.getKeyCode() == 10) {
                            TelaDeLoginController.logarController();
                        }
                    }
                }
            }
        );

        setSize(170,140);
        setVisible(true);
    }

   

    private class ButtonHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            TelaDeLoginController.logarController();
        }
    }

    public static void notificarUsuario(String strTexto) {
        lblNotificacoes.setText(InterfaceView.setHtmlFormat(strTexto));
    }

    public static void verificarLarguraEAltura() { // checkFrameWidthHeight()
        appTelaDeLoginView.getRootPane().addComponentListener(
            new ComponentAdapter() {
                public void componentResized(ComponentEvent e) {
                    int larguraTela = appTelaDeLoginView.getWidth();
                    int alturaTela = appTelaDeLoginView.getHeight();
                    notificarUsuario(String.format("Largura: %s, Altura: %s", larguraTela, alturaTela));
                }
            }
        );
    }

    public static TelaDeLoginView appTelaDeLoginView;
    public static void main(String[] args) {
        appTelaDeLoginView = new TelaDeLoginView();
        appTelaDeLoginView.setDefaultCloseOperation(EXIT_ON_CLOSE);
        // InterfaceView.verificarLarguraEAltura(appTelaDeAtualizacaoViewlblNotificacoes);
    }
}
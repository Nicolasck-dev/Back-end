package view;

import controller.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

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

        lblLogin = new JLabel("Login:");
        addComponent(lblLogin, 0, 0, 1, 1);

        txtLogin = new JTextField(10);
        addComponent(txtLogin, 0, 1, 1, 1);

        lblSenha = new JLabel("Senha:");
        addComponent(lblSenha, 1, 0, 1, 1);

        txtSenha = new JPasswordField(10);
        addComponent(txtSenha, 1, 1, 1, 1);

        btnLogar = new JButton("Logar");
        addComponent(btnLogar, 2, 0, 2, 1);

        lblNotificacoes = new JLabel("Notificações", SwingConstants.CENTER);
        // lblNotificacoes.setSize(getContentPane().getWidth(), 40);
        addComponent(lblNotificacoes, 3, 0, 2, 1);

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

    public void addComponent(Component component, int row, int column, int width, int height) {
        if (height > 1 && width > 1) {
            gbConstraints.fill = GridBagConstraints.BOTH;
        } else if (height > 1) {
            gbConstraints.fill = GridBagConstraints.VERTICAL;
        } else {
            gbConstraints.fill = GridBagConstraints.HORIZONTAL;
        }
        gbConstraints.gridy = row;
        gbConstraints.gridx = column;
        gbConstraints.gridwidth = width;
        gbConstraints.gridheight = height;
        gbLayout.setConstraints(component, gbConstraints);
        add(component);
    }

    private class ButtonHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            TelaDeLoginController.logarController();
        }
    }

    public static String setHtmlFormat(String txt) {
        return "<html><body>" + txt + "</body></html>";
    }

    public static void notificarUsuario(String strTexto) {
        lblNotificacoes.setText(setHtmlFormat(strTexto));
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
        // verificarLarguraEAltura();
    }
}
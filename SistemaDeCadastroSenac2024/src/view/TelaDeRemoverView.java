package view;
import controller.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TelaDeRemoverView extends JFrame {
    public static JLabel lblFoto;

    public static JLabel lblId;
    public static JComboBox<String> cbxId;

    public static JLabel lblNome;
    public static JTextField txtNome;

    public static JLabel lblEmail;
    public static JTextField txtEmail;

    public static JButton btnRemover;
    public static JButton btnCancelar;

    public static JLabel lblNotificacoes;

    public static GridBagLayout gbLayout;
    public static GridBagConstraints gbConstraints;

    public TelaDeRemoverView() {
        super("Tela de Remover");

        gbLayout = new GridBagLayout();
        setLayout(gbLayout);
        gbConstraints = new GridBagConstraints();

        lblFoto = new JLabel("", SwingConstants.CENTER);
        lblFoto.setIcon(new ImageIcon(new ImageIcon(InterfaceView.localViewFolder + "\\imagem-padrao.jpg").getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT)));
        InterfaceView.addComponent(lblFoto, 0, 0, 4, 2, gbLayout, gbConstraints, this);

        lblId = new JLabel("Id:", SwingConstants.RIGHT);
        InterfaceView.addComponent(lblId, 2, 0, 1, 1, gbLayout, gbConstraints, this);

        cbxId = new JComboBox<String>();
        TelaDeRemoverController.popularCbxIdController();
        InterfaceView.addComponent(cbxId, 2, 1, 1, 1, gbLayout, gbConstraints, this);

        lblNome = new JLabel("Nome:", SwingConstants.RIGHT);
        InterfaceView.addComponent(lblNome, 3, 0, 1, 1, gbLayout, gbConstraints, this);

        txtNome = new JTextField(10);
        txtNome.setEditable(false);
        InterfaceView.addComponent(txtNome, 3, 1, 1, 1, gbLayout, gbConstraints, this);

        lblEmail = new JLabel("Email:", SwingConstants.RIGHT);
        InterfaceView.addComponent(lblEmail, 4, 0, 1, 1, gbLayout, gbConstraints, this);

        txtEmail = new JTextField(10);
        txtEmail.setEditable(false);
        InterfaceView.addComponent(txtEmail, 4, 1, 1, 1, gbLayout, gbConstraints, this);

        TelaDeRemoverController.atualizarCamposController();

        btnRemover = new JButton("Remover");
        InterfaceView.addComponent(btnRemover, 5, 0, 1, 1, gbLayout, gbConstraints, this);

        btnCancelar = new JButton("Cancelar");
        InterfaceView.addComponent(btnCancelar, 5, 1, 1, 1, gbLayout, gbConstraints, this);

        lblNotificacoes = new JLabel("Notificações", SwingConstants.CENTER);
        InterfaceView.addComponent(lblNotificacoes, 6, 0, 2, 1, gbLayout, gbConstraints, this);

        cbxId.addItemListener(
            new ItemListener() {
                @Override
                public void itemStateChanged(ItemEvent event) {
                    if (event.getStateChange() == ItemEvent.SELECTED) {
                        TelaDeRemoverController.atualizarCamposController();
                    }
                    if (event.getItem().equals(InterfaceView.idLoginAtual)) {
                        btnRemover.setEnabled(false);
                    } else {
                        btnRemover.setEnabled(true);
                    }
                }
            }
        );

        btnRemover.addActionListener(
            new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent event) {
                    if (JOptionPane.showConfirmDialog(null, "Tem certeza de que você deseja remover o id: " + String.valueOf(cbxId.getSelectedItem()) + "?") == 0) {
                        TelaDeRemoverController.removerController();
                        try {
                            cbxId.setSelectedIndex(cbxId.getSelectedIndex() + 1);
                            cbxId.removeItemAt(cbxId.getSelectedIndex() - 1);
                        } catch (Exception e) {
                            cbxId.setSelectedIndex(cbxId.getSelectedIndex() - 1);
                            cbxId.removeItemAt(cbxId.getSelectedIndex() + 1);
                        }
                        TelaDeRemoverController.atualizarCamposController();
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

        setSize(300,300);
        setVisible(true);
    }

    public static TelaDeRemoverView appTelaDeRemoverView;
    public static void main(String[] args) {
        // InterfaceView.idLoginAtual = "16";
        if (InterfaceView.idLoginAtual.equals("")) {
            TelaDeLoginView.appTelaDeLoginView = new TelaDeLoginView();
            TelaDeLoginView.appTelaDeLoginView.setDefaultCloseOperation(EXIT_ON_CLOSE);
        } else {
            appTelaDeRemoverView = new TelaDeRemoverView();
            appTelaDeRemoverView.setDefaultCloseOperation(EXIT_ON_CLOSE);
            // InterfaceView.verificarLarguraEAltura(appTelaDeAtualizacaoView,lblNotificacoes);
        }
    }
}
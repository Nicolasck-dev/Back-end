package view;
/**
 * import controller.*;
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TelaDeMenuView extends JFrame {
    private final JMenu cadastroMenu;
    private final JLabel lblNomeDaTela;
    private final JMenuBar menuBar;
    private final JMenuItem novoItem;
    private final JMenuItem pesquisarItem;
    private final JMenuItem atualizarItem;
    private final JMenuItem removerItem;
    private final JMenuItem sairItem;

    public TelaDeMenuView() {
        super("Tela de Menu");

        cadastroMenu = new JMenu("Cadastro");
        lblNomeDaTela = new JLabel("Tela de Menu", SwingConstants.CENTER);
        menuBar = new JMenuBar();

        novoItem = new JMenuItem("Novo");
        pesquisarItem = new JMenuItem("Pesquisar");
        atualizarItem = new JMenuItem("Atualizar");
        removerItem = new JMenuItem("Remover");
        sairItem = new JMenuItem("Sair");

        cadastroMenu.add(novoItem);
        cadastroMenu.add(pesquisarItem);
        cadastroMenu.add(atualizarItem);
        cadastroMenu.add(removerItem);
        cadastroMenu.add(sairItem);

        menuBar.add(cadastroMenu);


        setJMenuBar(menuBar);

        add(lblNomeDaTela, BorderLayout.CENTER);

        novoItem.addActionListener(
            new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent event) {
                    TelaDeCadastroView.appTelaDeCadastroView = new TelaDeCadastroView();
                    TelaDeCadastroView.appTelaDeCadastroView.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                    appTelaDeMenuView.setVisible(false);
                    TelaDeCadastroView.appTelaDeCadastroView.addWindowListener(
                        new WindowAdapter() {
                            public void windowClosing(WindowEvent e) {
                                appTelaDeMenuView.setVisible(true);
                            }
                        }
                    );
                }
            }
        );

        pesquisarItem.addActionListener(
            new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent event) {
                    TelaDePesquisaView.appTelaDePesquisaView = new TelaDePesquisaView();
                    TelaDePesquisaView.appTelaDePesquisaView.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                    appTelaDeMenuView.setVisible(false);
                    TelaDePesquisaView.appTelaDePesquisaView.addWindowListener(
                        new WindowAdapter() {
                            public void windowClosing(WindowEvent e) {
                                appTelaDeMenuView.setVisible(true);
                            }
                        }
                    );
                }
            }
        );

        atualizarItem.addActionListener(
            new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent event) {
                    TelaDeAtualizacaoView.appTelaDeAtualizacaoView = new TelaDeAtualizacaoView();
                    TelaDeAtualizacaoView.appTelaDeAtualizacaoView.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                    appTelaDeMenuView.setVisible(false);
                    TelaDeAtualizacaoView.appTelaDeAtualizacaoView.addWindowListener(
                        new WindowAdapter() {
                            public void windowClosing(WindowEvent e) {
                                appTelaDeMenuView.setVisible(true);
                            }
                        }
                    );
                }
            }
        );

        removerItem.addActionListener(
            new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent event) {
                    TelaDeRemoverView.appTelaDeRemoverView = new TelaDeRemoverView();
                    TelaDeRemoverView.appTelaDeRemoverView.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                    appTelaDeMenuView.setVisible(false);
                    TelaDeRemoverView.appTelaDeRemoverView.addWindowListener(
                        new WindowAdapter() {
                            public void windowClosing(WindowEvent e) {
                                appTelaDeMenuView.setVisible(true);
                            }
                        }
                    );
                }
            }
        );

        sairItem.addActionListener(
            new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent event) {
                    System.out.println("Aqui ok");
                    if (JOptionPane.showConfirmDialog(null, "Deseja mesmo sair do sistema?") == 0) {
                        System.exit(0);
                    }
                }
            }
        );

        setSize(300,300);
        setVisible(true);
    }

    public static TelaDeMenuView appTelaDeMenuView;
    public static void main(String[] args) {
        appTelaDeMenuView = new TelaDeMenuView();
        appTelaDeMenuView.setDefaultCloseOperation(EXIT_ON_CLOSE);

        appTelaDeMenuView.addWindowListener(
            new WindowAdapter() {
                public void windowClosing(WindowEvent e) {
                    InterfaceView.removerImagensInuteis();
                }
            }
        );
    }
}


package view;

import controller.*;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class TelaDeHistoricoView extends JFrame {
    public static JList<String> lstHistorico;
    public static JButton btnEnviarHistorico;
    public static ArrayList<String> strHistoricos;

    public static GridBagLayout gbLayout;
    public static GridBagConstraints gbConstraints;

    public TelaDeHistoricoView() {
        super("Tela de Histórico");

        gbLayout = new GridBagLayout();
        setLayout(gbLayout);

        gbConstraints = new GridBagConstraints();

        String[] strHistoricos = TelaDeHistoricoController.preencherHistorico();
        lstHistorico = new JList<String>(strHistoricos);
        lstHistorico.setVisibleRowCount(5);
        InterfaceView.addComponent(lstHistorico, 0, 0, 1, 1, gbLayout, gbConstraints, this);

        btnEnviarHistorico = new JButton("Enviar Histórico");
        btnEnviarHistorico.setEnabled(false);
        InterfaceView.addComponent(btnEnviarHistorico, 1, 0, 1, 1, gbLayout, gbConstraints, this);

        lstHistorico.addListSelectionListener(
            new ListSelectionListener() {
                @Override
                public void valueChanged(ListSelectionEvent event) {
                    btnEnviarHistorico.setEnabled(true);
                } 
            } 
        );

        btnEnviarHistorico.addActionListener(
            new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    TelaDePesquisaView.txtPesquisa.setText(lstHistorico.getSelectedValue());
                    TelaDePesquisaView.appTelaDePesquisaView.setVisible(true);
                    dispose();
                }
            }
        );

        setSize(200, 200);
        setVisible(true);
    }

    public static TelaDeHistoricoView appTelaDeHistoricoView;
    public static void main(String[] args) {
        // InterfaceView.idLoginAtual = "16";
        if (InterfaceView.idLoginAtual.equals("")) {
            TelaDeLoginView.appTelaDeLoginView = new TelaDeLoginView();
            TelaDeLoginView.appTelaDeLoginView.setDefaultCloseOperation(EXIT_ON_CLOSE);
        } else {
            appTelaDeHistoricoView = new TelaDeHistoricoView();
            appTelaDeHistoricoView.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            // InterfaceView.verificarLarguraEAltura(appTelaDeHistoricoView,lblNotificacoes);

            appTelaDeHistoricoView.addWindowListener(
                new WindowAdapter() {
                    public void windowClosing(WindowEvent e) {
                        System.out.println("ok");
                        TelaDePesquisaView.appTelaDePesquisaView.setVisible(true);
                        appTelaDeHistoricoView.dispose();
                    }
                }
            );
        }
    }
}
package controller;
import view.*;
import model.*;
import javax.swing.*;
import java.awt.*;

public class TelaDePesquisaController extends TelaDePesquisaView {
    public static String registroDePesquisa = "";
    public static String clausulasDePesquisaComWhere = "";
    public static String clausulasDePesquisaSemWhere = "";

    public static void vaParaPrimeiroRegistro() {
        TelaDePesquisaModel.vaParaPrimeiroRegistroModel();
    }

    public static void vaParaRegistroAnterior() {
        TelaDePesquisaModel.vaParaRegistroAnteriorModel(txtId.getText());
    }

    public static void vaParaProximoRegistro() {
        TelaDePesquisaModel.vaParaProximoRegistroModel(txtId.getText());
    }

    public static void vaParaUltimoRegistro() {
        TelaDePesquisaModel.vaParaUltimoRegistroModel();
    }

    public static void registrarPesquisa() {
        TelaDePesquisaModel.registrarPesquisaModel(txtPesquisa.getText().trim());
    }

    public static void inicializacaoDeRegistros() {
        TelaDePesquisaModel.inicializacaoDeRegistrosModel();
    }

    public static void atualizarCampos(String id, String nome, String email, String foto) {
        if (foto != null) {
            if (foto.length() > 0) {
                lblFoto.setIcon(new ImageIcon(new ImageIcon(InterfaceView.localViewImgFolder + "\\" + foto).getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT)));
            } else {
                lblFoto.setIcon(new ImageIcon(new ImageIcon(InterfaceView.localViewFolder + "\\imagem-padrao.jpg").getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT)));
            }
        } else {
            lblFoto.setIcon(new ImageIcon(new ImageIcon(InterfaceView.localViewFolder + "\\imagem-padrao.jpg").getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT)));
        }

        txtId.setText(id);
        txtNome.setText(nome);
        txtEmail.setText(email);
    }
}
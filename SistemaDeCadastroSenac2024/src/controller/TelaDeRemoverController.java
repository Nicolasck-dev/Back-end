package controller;
import view.*;
import model.*;
import javax.swing.*;
import java.awt.*;

public class TelaDeRemoverController extends TelaDeRemoverView {
    public static void removerController() {
        TelaDeRemoverModel.removerModel(String.valueOf(cbxId.getSelectedItem()));
    }

    public static void popularCbxIdController() {
        TelaDeRemoverModel.popularCbxIdModel();
    }

    public static void adicionarItemCbxId(String str) {
        cbxId.addItem(str);
    }

    public static void preencherCampos(String nome, String email, String foto) {
        if (foto != null) {
            if (foto.length() > 0) {
                lblFoto.setIcon(new ImageIcon(new ImageIcon(InterfaceView.localViewImgFolder + "\\" + foto).getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT)));
            } else {
                lblFoto.setIcon(new ImageIcon(new ImageIcon(InterfaceView.localViewFolder + "\\imagem-padrao.jpg").getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT)));
            }
        } else {
            lblFoto.setIcon(new ImageIcon(new ImageIcon(InterfaceView.localViewFolder + "\\imagem-padrao.jpg").getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT)));
        }

        txtNome.setText(nome);
        txtEmail.setText(email);
    }

    public static void atualizarCamposController() {
        TelaDeRemoverModel.atualizarCamposModel(String.valueOf(cbxId.getSelectedItem()));
    }
}
package controller;

import view.*;
import model.*;

import java.util.*;

public class TelaDeLoginController extends TelaDeLoginView {
    public static void logarController() {
        ArrayList<String> resultados = new ArrayList<String>(TelaDeLoginModel.logarModel(txtLogin.getText(), String.valueOf(txtSenha.getPassword())));
        resultados.size();
    }

    public static void abrirTelaDeMenu() {
        TelaDeMenuView.appTelaDeMenuView = new TelaDeMenuView();
        TelaDeMenuView.appTelaDeMenuView.setDefaultCloseOperation(EXIT_ON_CLOSE);
        TelaDeLoginView.appTelaDeLoginView.dispose();
    }
}
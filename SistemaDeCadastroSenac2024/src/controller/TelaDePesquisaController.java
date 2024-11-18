package controller;
import view.*;
import model.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class TelaDePesquisaController extends TelaDePesquisaView {
    public static String registroDePesquisa;
    public static String clausulasDePesquisaComWhere;
    public static String clausulasDePesquisaSemWhere;

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

    public static void atualizarCampos(String id, String nome, String email) {
        txtId.setText(id);
        txtNome.setText(nome);
        txtEmail.setText(email);
    }
}

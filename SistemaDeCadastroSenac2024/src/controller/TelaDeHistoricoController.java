package controller;

import view.*;
import model.*;

//import java.util.*;
//import java.awt.*;
//import java.awt.event.*;
//import javax.swing.*;

public class TelaDeHistoricoController extends TelaDeHistoricoView {
    public static String[] preencherHistorico() {
        String[] historicos = TelaDeHistoricoModel.capturarHistorico();
        return historicos;
    }
}
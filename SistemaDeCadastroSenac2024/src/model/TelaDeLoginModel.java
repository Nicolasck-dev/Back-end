package model;

import controller.*;
import view.InterfaceView;

import java.sql.*;
import java.util.*;

public class TelaDeLoginModel {
    public static ArrayList<String> logarModel(String login, String senha) {
        ArrayList<String> resultados = new ArrayList<String>();
        resultados.add("resultado1");

        try {
            Connection conexao = MySQLConnector.conectar();
            String strSqlLogin = "select * from `db_senac`.`tbl_senac` where email = '" + login + "' and senha = '" + senha + "';";
            Statement stmSqlLogin = conexao.createStatement();
            ResultSet rstSqlLogin = stmSqlLogin.executeQuery(strSqlLogin);
            if (rstSqlLogin.next()) {
                InterfaceView.notificarUsuario("Login " + rstSqlLogin.getString("email") + " realizado com sucesso.", TelaDeLoginController.lblNotificacoes);
                TelaDeLoginController.abrirTelaDeMenu();
                InterfaceView.idLoginAtual = rstSqlLogin.getString("id");
            } else {
                InterfaceView.notificarUsuario("Não foi possível encontrar o login e/ou senha digitados. Por favor, verifique e tente novamente.", TelaDeLoginController.lblNotificacoes);
            }
            stmSqlLogin.close();
        } catch (Exception e) {
            InterfaceView.notificarUsuario("Houve um problema e não será possível realizar o login neste momento. Por favor, tente novamente mais tarde.", TelaDeLoginController.lblNotificacoes);
            System.err.println("Ops! Deu ruim, se liga no erro: " + e);
        }

        return resultados;
    }
}
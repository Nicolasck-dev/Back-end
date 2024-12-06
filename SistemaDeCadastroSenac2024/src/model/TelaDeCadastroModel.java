package model;
import controller.*;
import view.InterfaceView;

import java.sql.*;

public class TelaDeCadastroModel {
    public static void cadastrarModel(String nome, String email, String senha, String img) {
        try {
            String strSqlCadastrar = "insert into `db_senac`.`tbl_senac` (`nome`, `email`, `senha`, `img`) values ('" + nome + "','" + email + "','" + senha + "','" + img + "');";
            Connection conexao = MySQLConnector.conectar();
            Statement stmSqlCadastrar = conexao.createStatement();
            stmSqlCadastrar.addBatch(strSqlCadastrar);
            stmSqlCadastrar.executeBatch();
            InterfaceView.notificarUsuario("Cadastro realizado com sucesso!", TelaDeCadastroController.lblNotificacoes);
        } catch (Exception e) {
            InterfaceView.notificarUsuario("Ops! Ocorrou um problema e não será possível cadastrar nesse momento. Por favor, tente novamente mais tarde.", TelaDeCadastroController.lblNotificacoes);
            System.err.println("Erro: " + e);
        }
    }
}
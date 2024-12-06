package model;

//import controller.*;
import view.InterfaceView;

import java.util.*;
import java.sql.*;

public class TelaDeHistoricoModel {
    public static String[] capturarHistorico() {
        ArrayList<String> strHistoricos = new ArrayList<String>();
        try {
            String strSqlCapturarHistorico = "select * from `db_senac`.`tbl_historico` where `id_login` = " + InterfaceView.idLoginAtual + ";";
            Connection conexao = MySQLConnector.conectar();
            Statement stmCapturarHistorico = conexao.createStatement();
            ResultSet rstCapturarHistorico = stmCapturarHistorico.executeQuery(strSqlCapturarHistorico);

            while (rstCapturarHistorico.next()) {
                strHistoricos.add(rstCapturarHistorico.getString("txt_historico"));
            }
        } catch (Exception e) {
            System.err.println("Erro: " + e);
        }
        return strHistoricos.toArray(new String[0]);
    }
}
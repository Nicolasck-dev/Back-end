package model;
import controller.*;
import view.InterfaceView;

import java.sql.*;

public class TelaDeRemoverModel {
    public static void removerModel(String id) {
        try {
            Connection conexao = MySQLConnector.conectar();
            String strSqlRemoverId = "delete from `db_senac`.`tbl_senac` where `id` = " + id + ";";
            Statement stmSqlRemoverId = conexao.createStatement();
            stmSqlRemoverId.addBatch(strSqlRemoverId);
            stmSqlRemoverId.executeBatch();
            InterfaceView.notificarUsuario("O id " + id + " foi removido com sucesso.", TelaDeRemoverController.lblNotificacoes);
        } catch (Exception e) {
            InterfaceView.notificarUsuario("Ops! Problema no servidor, tente novamente mais tarde.", TelaDeRemoverController.lblNotificacoes);
            System.err.println("Erro: " + e);
        }
    }

    public static void popularCbxIdModel() {
        try {
            Connection conexao = MySQLConnector.conectar();
            String strSqlPopularCbxId = "select * from `db_senac`.`tbl_senac` order by `id` asc;";
            Statement stmSqlPopularCbxId = conexao.createStatement();
            ResultSet rstSqlPopularCbxId = stmSqlPopularCbxId.executeQuery(strSqlPopularCbxId);
            while (rstSqlPopularCbxId.next()) {
                TelaDeRemoverController.adicionarItemCbxId(rstSqlPopularCbxId.getString("id"));
            }
            stmSqlPopularCbxId.close();
        } catch (Exception e) {
            InterfaceView.notificarUsuario("Ops! Ocorreu um problema no servidor e não será possível carregar os ids neste momento. Por favor, retorne novamente mais tarde.", TelaDeRemoverController.lblNotificacoes);
            System.err.println("Erro: " + e);
        }
    }

    public static void atualizarCamposModel(String strId) {
        try {
            Connection conexao = MySQLConnector.conectar();
            String strSqlAtualizarCampos = "select * from `db_senac`.`tbl_senac` where id = " + strId + ";";
            Statement stmSqlAtualizarCampos = conexao.createStatement();
            ResultSet rstSqlAtualizarCampos = stmSqlAtualizarCampos.executeQuery(strSqlAtualizarCampos);
            if (rstSqlAtualizarCampos.next()) {
                TelaDeRemoverController.preencherCampos(rstSqlAtualizarCampos.getString("nome"), rstSqlAtualizarCampos.getString("email"), rstSqlAtualizarCampos.getString("img"));
            } else {
                InterfaceView.notificarUsuario("Id não encontrado.", TelaDeRemoverController.lblNotificacoes);
            }
        } catch (Exception e) {
            InterfaceView.notificarUsuario("Ops! Problema no servidor. Tente novamente mais tarde.", TelaDeRemoverController.lblNotificacoes);
            System.err.println("Erro: " + e);
        }
    }
}
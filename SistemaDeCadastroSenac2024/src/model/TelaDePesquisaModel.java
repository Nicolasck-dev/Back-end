package model;
import controller.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class TelaDePesquisaModel {
    public static void registrarPesquisaModel(String pequisa) {
        TelaDePesquisaController.registroDePesquisa = pequisa;
        if (TelaDePesquisaController.registroDePesquisa.length() > 0) {
            TelaDePesquisaController.clausulasDePesquisaComWhere = " where `nome` like '%" + 
            TelaDePesquisaController.registroDePesquisa + "%' or `email` like '%" + 
            TelaDePesquisaController.registroDePesquisa + "%'";
            TelaDePesquisaController.clausulasDePesquisaSemWhere = " and (`nome` like '%" + 
            TelaDePesquisaController.registroDePesquisa + "%' or `email` like '%" + 
            TelaDePesquisaController.registroDePesquisa + "%')";
        }
        TelaDePesquisaController.vaParaPrimeiroRegistro();
    }

    public static void inicializacaoDeRegistrosModel() {
        TelaDePesquisaController.vaParaPrimeiroRegistro();
    }

    public static void vaParaPrimeiroRegistroModel() {
        try {
            String strSqlInicializacao = "select * from `db_senac`.`tbl_senac` " + 
            TelaDePesquisaController.clausulasDePesquisaComWhere + " order by `id` asc;";
            Connection conexao = MySQLConnector.conectar();
            Statement stmSqlInicializacao = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rstSqlInicializacao = stmSqlInicializacao.executeQuery(strSqlInicializacao);

            int qtdResultados = 0;
            while (rstSqlInicializacao.next()) {
                qtdResultados++;
            }

            if (rstSqlInicializacao.first()) {
                TelaDePesquisaController.atualizarCampos(rstSqlInicializacao.getString("id"),rstSqlInicializacao.getString("nome") ,rstSqlInicializacao.getString("email"));
                TelaDePesquisaController.notificarUsuario("Foram encontrados \"" + qtdResultados + "\" registros. Primeiro registro posicionado com sucesso!");
                TelaDePesquisaController.habilitarAvancar();
            } else {
                TelaDePesquisaController.limparCampos();
                TelaDePesquisaController.desabilitarTodos();
                TelaDePesquisaController.notificarUsuario("Não foram encontrados registros.");
            }
            stmSqlInicializacao.close();
        } catch (Exception e) {
            TelaDePesquisaController.notificarUsuario("Ops! Houve um problema no servidor e não será possível inicializar os registros no momento. Por favor, retorne novamente mais tarde.");
            System.err.println("Erro: " + e);
        }
    }

    public static void vaParaProximoRegistroModel(String id) {
        try {
            String strSqlProximoRegistro = "select * from `db_senac`.`tbl_senac` where `id` > " + id + TelaDePesquisaController.clausulasDePesquisaSemWhere + " order by `id` asc;";
            Connection conexao = MySQLConnector.conectar();
            Statement stmSqlProximoRegistro = conexao.createStatement();
            ResultSet rstSqlProximoRegistro = stmSqlProximoRegistro.executeQuery(strSqlProximoRegistro);
            if (rstSqlProximoRegistro.next()) {
                TelaDePesquisaController.atualizarCampos(rstSqlProximoRegistro.getString("id"),rstSqlProximoRegistro.getString("nome"),rstSqlProximoRegistro.getString("email"));
            
                TelaDePesquisaController.notificarUsuario("Próximo registro posicionado com sucesso!");
                TelaDePesquisaController.habilitarTodos();
            } else {
                TelaDePesquisaController.habilitarVoltar();
                TelaDePesquisaController.notificarUsuario("Não foram encontrados registros adiante.");
            }
            stmSqlProximoRegistro.close();
        } catch (Exception e) {
            TelaDePesquisaController.notificarUsuario("Ops! Houve um problema no servidor e não será possível inicializar os registros no momento. Por favor, retorne novamente mais tarde.");
            System.err.println("Erro: " + e);
        }
    }

    public static void vaParaUltimoRegistroModel() {
        try {
           
            String strSqlUltimoRegistro = "select * from `db_senac`.`tbl_senac` " + TelaDePesquisaController.clausulasDePesquisaComWhere + " order by `id` desc;";
            Connection conexao = MySQLConnector.conectar();
            Statement stmSqlUltimoRegistro = conexao.createStatement();
            ResultSet rstSqlUltimoRegistro = stmSqlUltimoRegistro.executeQuery(strSqlUltimoRegistro);
            if (rstSqlUltimoRegistro.next()) {
                TelaDePesquisaController.atualizarCampos(rstSqlUltimoRegistro.getString("id"),rstSqlUltimoRegistro.getString("nome"),rstSqlUltimoRegistro.getString("email"));
                TelaDePesquisaController.notificarUsuario("Último registro posicionado com sucesso!");
                TelaDePesquisaController.habilitarVoltar();
            } else {
                TelaDePesquisaController.notificarUsuario("Não foram encontrados registros.");
            }
            stmSqlUltimoRegistro.close();
        } catch (Exception e) {
            TelaDePesquisaController.notificarUsuario("Ops! Houve um problema no servidor e não será possível inicializar os registros no momento. Por favor, retorne novamente mais tarde.");
            System.err.println("Erro: " + e);
        }
    }

    public static void vaParaRegistroAnteriorModel(String id) {
        try {
            String strSqlRegistroAnterior = "select * from `db_senac`.`tbl_senac` where `id` < " + id + TelaDePesquisaController.clausulasDePesquisaSemWhere + " order by `id` desc;";
            Connection conexao = MySQLConnector.conectar();
            Statement stmSqlRegistroAnterior = conexao.createStatement();
            ResultSet rstSqlRegistroAnterior = stmSqlRegistroAnterior.executeQuery(strSqlRegistroAnterior);
            if (rstSqlRegistroAnterior.next()) {
                TelaDePesquisaController.atualizarCampos(rstSqlRegistroAnterior.getString("id"),rstSqlRegistroAnterior.getString("nome"),rstSqlRegistroAnterior.getString("email"));
                TelaDePesquisaController.notificarUsuario("Registro anterior posicionado com sucesso!");
                TelaDePesquisaController.habilitarTodos();
            } else {
                TelaDePesquisaController.habilitarAvancar();
                TelaDePesquisaController.notificarUsuario("Não foram encontrados registros anteriores.");
            }
            stmSqlRegistroAnterior.close();
        } catch (Exception e) {
            TelaDePesquisaController.notificarUsuario("Ops! Houve um problema no servidor e não será possível inicializar os registros no momento. Por favor, retorne novamente mais tarde.");
            System.err.println("Erro: " + e);
        }
    }
}

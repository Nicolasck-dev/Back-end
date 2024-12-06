package controller;
import view.*;
import model.*;

import java.util.*;
import static java.nio.file.StandardCopyOption.*;
import java.nio.file.*;
import javax.swing.*;
import java.awt.*;

public class TelaDeAtualizacaoController extends TelaDeAtualizacaoView {
    public static void popularCbxIdController() {
        ArrayList<String> ids = TelaDeAtualizacaoModel.popularCbxIdModel();
        for (int i = 0; i < ids.size(); i++) {
            cbxId.addItem(ids.get(i));
        }
    }

    public static void atualizarCamposController() {
        ArrayList<String> dados = TelaDeAtualizacaoModel.atualizarCamposModel(String.valueOf(cbxId.getSelectedItem()));

        String foto = dados.get(2);
        if (foto != null) {
            if (foto.length() > 0) {
                lblFoto.setIcon(new ImageIcon(new ImageIcon(InterfaceView.localViewImgFolder + "\\" + foto).getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT)));
                btnRemoverFoto.setEnabled(true);
            } else {
                lblFoto.setIcon(new ImageIcon(new ImageIcon(InterfaceView.localViewFolder + "\\imagem-padrao.jpg").getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT)));
                btnRemoverFoto.setEnabled(false);
            }
        } else {
            lblFoto.setIcon(new ImageIcon(new ImageIcon(InterfaceView.localViewFolder + "\\imagem-padrao.jpg").getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT)));
            btnRemoverFoto.setEnabled(false);
        }

        txtNome.setText(dados.get(0));
        txtEmail.setText(dados.get(1));
    }

    public static void atualizarController() {
        if (TelaDeAtualizacaoModel.atualizarModel(String.valueOf(cbxId.getSelectedItem()), txtNome.getText().trim(), txtEmail.getText().trim(), String.valueOf(txtSenha.getPassword()).trim(), nomeArquivoFoto)) {
            txtNomeCarregado = txtNome.getText().trim();
            txtEmailCarregado = txtEmail.getText().trim();
            btnAtualizar.setEnabled(false);
        }
    }

    public static void carregarFoto() {
        try {
            JFileChooser chooser = new JFileChooser();

            chooser.setDialogTitle("Selecione o arquivo que deseja copiar");
            chooser.setApproveButtonText("Copiar arquivo");
            int returnVal = chooser.showOpenDialog(null);
            String fileFullPath = "";
            String fileName = "";
            if(returnVal == JFileChooser.APPROVE_OPTION) {
                fileFullPath = chooser.getSelectedFile().getAbsolutePath();
                fileName = "file-" + Math.random() + "-" + chooser.getSelectedFile().getName();
            } else {
                System.out.println("Usuário não selecionou o arquivo para copiar...");
            }

            Path pathOrigin = Paths.get(fileFullPath);
            Path pathDestination = Paths.get(InterfaceView.localViewImgFolder + "\\" + fileName);
            if (fileFullPath.length() > 0) {
                Files.copy(pathOrigin, pathDestination, REPLACE_EXISTING);
                System.out.println("Arquivo " + chooser.getSelectedFile().getName() + " copiado/colado com sucesso.");
            } else {
                System.out.println("Ops! Não foi possível copiar o arquivo. Por favor, verifique e tente novamente mais tarde.");
            }

            nomeArquivoFoto = fileName;

            lblFoto.setIcon(new ImageIcon(new ImageIcon(InterfaceView.localViewImgFolder + "\\" + nomeArquivoFoto).getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT)));
            btnRemoverFoto.setEnabled(true);
            btnAtualizar.setEnabled(true);
        } catch (Exception e) {
            System.out.println("Não foi possível copiar o arquivo.");
        }
    }

    public static void removerFoto() {
        lblFoto.setIcon(new ImageIcon(new ImageIcon(InterfaceView.localViewFolder + "\\imagem-padrao.jpg").getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT)));
        nomeArquivoFoto = "";
        btnAtualizar.setEnabled(true);
        btnRemoverFoto.setEnabled(false);
    }
}
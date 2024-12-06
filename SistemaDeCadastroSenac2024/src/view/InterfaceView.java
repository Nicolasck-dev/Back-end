package view;
import controller.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class InterfaceView {
    public static String idLoginAtual = "";

    public static final String localViewImgFolder = System.getProperty("user.dir") 
        + "\\" 
        + "src" 
        + "\\" 
        + "view" 
        + "\\" 
        + "img";

    public static final String localViewFolder = System.getProperty("user.dir") 
        + "\\" 
        + "src" 
        + "\\" 
        + "view";

    public static void addComponent(Component component, int row, int column, int width, int height, GridBagLayout gbLayout, GridBagConstraints gbConstraints, JFrame frame) {
        if (height > 1 && width > 1) {
            gbConstraints.fill = GridBagConstraints.BOTH;
        } else if (height > 1) {
            gbConstraints.fill = GridBagConstraints.VERTICAL;
        } else {
            gbConstraints.fill = GridBagConstraints.HORIZONTAL;
        }
        gbConstraints.gridy = row;
        gbConstraints.gridx = column;
        gbConstraints.gridwidth = width;
        gbConstraints.gridheight = height;
        gbLayout.setConstraints(component, gbConstraints);
        frame.add(component);
    }

    public static void verificarLarguraEAltura(JFrame frame, JLabel label) { // checkFrameWidthHeight()
        frame.getRootPane().addComponentListener(
            new ComponentAdapter() {
                public void componentResized(ComponentEvent e) {
                    int larguraTela = frame.getWidth();
                    int alturaTela = frame.getHeight();
                    notificarUsuario(String.format("Largura: %s, Altura: %s", larguraTela, alturaTela), label);
                }
            }
        );
    }

    public static void notificarUsuario(String str, JLabel label) {
        label.setText(setHtmlFormat(str));
    }

    public static String setHtmlFormat(String str) {
        return "<html><body>" + str + "</body></html>";
    }

    public static void removerImagensInuteis() {
        InterfaceController.verificarApagarImagensInuteis();
    }
}
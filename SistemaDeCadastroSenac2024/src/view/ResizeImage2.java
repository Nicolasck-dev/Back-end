package view;

import javax.imageio.*;
import java.awt.*;
import java.awt.image.*;
import java.io.*;
import java.nio.file.*;
import java.util.*;

public class ResizeImage2 {

    private static final int IMG_WIDTH = 100;
    private static final int IMG_HEIGHT = 100;

    public static void main(String[] args) throws IOException {

        String fullFileName = "imagem-padrao.jpg";
        String fileName = getFileName(fullFileName);

        String fullPathImageOrigin = InterfaceView.localViewFolder + "\\" + fullFileName;
        BufferedImage bfImg = ImageIO.read(new File(fullPathImageOrigin));

        String newFileExtension = "png";
        String newFullPathImageOrigin = InterfaceView.localViewFolder + "\\" + "novo-" + fileName + "." + newFileExtension;

        File file = new File(newFullPathImageOrigin);
        ImageIO.write(bfImg, newFileExtension, file);

        Path source = Paths.get(fullPathImageOrigin);
        Path target = Paths.get(InterfaceView.localViewFolder + "\\" + fileName + "-redimensionado.png");

        try (InputStream is = new FileInputStream(source.toFile())) {
            resize(is, target, IMG_WIDTH, IMG_HEIGHT);
        }

        Path newFullPathImage = Paths.get(newFullPathImageOrigin);
        Files.delete(newFullPathImage);
    }

    public static String getFileExtension(String filename) {
        if (filename == null) {
            return null;
        }
        int dotIndex = filename.lastIndexOf(".");
        if (dotIndex >= 0) {
            return filename.substring(dotIndex + 1);
        }
        return "";
    }

    public static String getFileName(String filename) {
        if (filename == null) {
            return null;
        }
        int dotIndex = filename.lastIndexOf(".");
        if (dotIndex >= 0) {
            return filename.substring(0, dotIndex);
        }
        return "";
    }

    private static void resize(InputStream input, Path target,
    int width, int height) throws IOException {

        BufferedImage originalImage = ImageIO.read(input);

        BufferedImage newResizedImage
        = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = newResizedImage.createGraphics();

        g.setComposite(AlphaComposite.Src);
        g.fillRect(0, 0, width, height);

        Map<RenderingHints.Key,Object> hints = new HashMap<>();
        hints.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        hints.put(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        hints.put(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.addRenderingHints(hints);

        g.drawImage(originalImage, 0, 0, IMG_WIDTH, IMG_HEIGHT, null);
        g.dispose();

        String s = target.getFileName().toString();
        String fileExtension = s.substring(s.lastIndexOf(".") + 1);

        ImageIO.write(newResizedImage, fileExtension, target.toFile());
        System.out.println(InterfaceView.localViewFolder + "\\papel-de-parede.jpg");
    }

    public static void resizeImage(Path sourcePath, Path destinationPath) throws Exception {
    // Lê a imagem original
    BufferedImage originalImage = ImageIO.read(sourcePath.toFile());
    
    // Define as novas dimensões
    int width = 100;  // Largura desejada
    int height = 100; // Altura desejada
    
    // Cria uma nova imagem redimensionada
    BufferedImage resizedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
    Graphics2D g2d = resizedImage.createGraphics();
    g2d.drawImage(originalImage, 0, 0, width, height, null);
    g2d.dispose();
    
    // Salva a imagem redimensionada no caminho de destino
    ImageIO.write(resizedImage, "png", destinationPath.toFile());
}
}

// https://mkyong.com/java/how-to-resize-an-image-in-java/
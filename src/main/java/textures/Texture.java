package textures;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class Texture {

    protected double x, y;
    protected BufferedImage image;

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public Texture(double x, double y, String fileName) {
        this.x = x;
        this.y = y;

        File f = new File(fileName);

        try {
            image = ImageIO.read(f);
        } catch (Exception e) {
            System.out.println("Файл не загружен " + fileName);
            System.exit(0);
        }
    }

    public void paint(Graphics g) {
        g.drawImage(image, (int) x, (int) y, null);
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }
}

package textures;

import interfaces.Paintable;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class Texture implements Paintable {

    protected double x, y;
    protected BufferedImage image;

    public Texture(double x, double y, String fileName) {
        this.x = x;
        this.y = y;

        File f = new File(
                Texture.class.getClassLoader().getResource(fileName).getPath()
        );

        try {
            image = ImageIO.read(f);
        } catch (Exception e) {
            System.out.println("Файл не загружен " + fileName);
            System.exit(0);
        }
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    @Override
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

package textures;

import java.awt.*;
import java.util.ArrayList;

public class Sprite extends Texture {

    protected double alpha = 0;
    protected double speed = 150;

    protected ArrayList<Point> frames = new ArrayList<Point>();
    protected int currentFrame = 0;
    protected int elapsedTime = 0;
    protected int frameTime = 50;
    protected int frameWidth = 100, frameHeight = 100;

    protected boolean visible = true;

    public void show() {
        visible = true;
    }

    public void hide() {
        visible = false;
    }

    public void setAlpha(double alpha) {
        this.alpha = alpha;
    }

    public Sprite(double x, double y, String filePath, Point p) {
        super(x, y, filePath);
        addFrame(p);
    }


    public void addFrame(Point p) {
        frames.add(p);
    }

    public void nextFrame() {
        currentFrame++;
        if (currentFrame >= frames.size()) {
            currentFrame = 0;
        }
    }


    @Override
    public void paint(Graphics g) {
        nextFrame();

        if (!visible) {
            return;
        }

        int x_image1, y_image1, x_image2, y_image2;
        int x_canvas1, y_canvas1, x_canvas2, y_canvas2;

        Point p = frames.get(currentFrame);
        x_image1 = p.x;
        y_image1 = p.y;
        x_image2 = x_image1 + frameWidth;
        y_image2 = y_image1 + frameHeight;

        x_canvas1 = (int) x;
        y_canvas1 = (int) y;
        x_canvas2 = x_canvas1 + frameWidth;
        y_canvas2 = y_canvas1 + frameHeight;

        g.drawImage(image, x_canvas1, y_canvas1, x_canvas2, y_canvas2, x_image1, y_image1, x_image2, y_image2, null);
    }

    public int getFrameWidth() {
        return frameWidth;
    }

    public void setFrameWidth(int frameWidth) {
        this.frameWidth = frameWidth;
    }

    public int getFrameHeight() {
        return frameHeight;
    }

    public void setFrameHeight(int frameHeight) {
        this.frameHeight = frameHeight;
    }
}

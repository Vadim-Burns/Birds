import java.awt.*;
import java.util.ArrayList;

public class Sprite extends Texture {

    protected double alpha = 0;
    protected double speed = 150;

    protected ArrayList<Point> frames = new ArrayList<Point>();
    protected int currentFrame = 0;
    protected int elapsedTime = 0;
    protected int frameTime = 100;
    protected int frameWidth = 100, frameHeight = 100;

    protected boolean visible = true;

    public void toggleVisible() {
        visible = !visible;
    }

    public void show() {
        visible = true;
    }

    public void hide() {
        visible = false;
    }


    public double getAlpha() {
        return alpha;
    }

    public void setAlpha(double alpha) {
        this.alpha = alpha;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public int getFrameTime() {
        return frameTime;
    }

    public void setFrameTime(int frameTime) {
        this.frameTime = frameTime;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public Sprite(double x, double y, String fileName, Point p, double alpha, double speed) {
        this(x, y, fileName, p);
        this.alpha = alpha;
        this.speed = speed;
    }

    public Sprite(double x, double y, String fileName, Point p) {
        super(x, y, fileName);
        addFrame(p);
    }

    public Sprite(double x, double y, String fileName) {
        this(x, y, fileName, new Point(0, 0));

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

    private void updateFrames(int ms) {
        // Обновление кадров
        elapsedTime += ms;
        if (elapsedTime >= frameTime) {
            nextFrame();
            elapsedTime = elapsedTime - frameTime;
        }
    }

    private void updateXY(int ms) {
        // Пересчет дивжения
        double vx = speed * Math.cos(alpha);
        double vy = speed * Math.sin(alpha);

        double dx = vx * ms / 1000.0;
        double dy = vy * ms / 1000.0;

        x += dx;
        y += dy;

    }

    public void update(int ms) {
        updateFrames(ms);
        updateXY(ms);
    }


    @Override
    public void paint(Graphics g) {

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
}

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


class Menu {

    String [] options = {"Игра", "еще один пункт", "Выход"};
    int index = 0;

    public void next () {
        index ++;
        if (index > options.length - 1) {
            index = 0;
        }
    }
    public void prev () {
        index --;
        if (index < 0) {
            index = 0;
        }
    }
    public int select () {
        active = false;
        return index;
    }

    public void toggle () {
        active = !active;
    }

    private Font f = new Font(Font.SERIF, Font.BOLD, 32);
    private boolean active;
    private int margin = 500;

    public void paint (Graphics g) {
        if (active) {
            g.setFont(f);

            for (int i = 0; i < options.length  ; i++  ) {
                if (i == index) {
                    g.setColor(Color.RED);
                }
                else {
                    g.setColor(Color.WHITE);
                }
                g.drawString(options[i], margin, margin + i * 50 );
            }

        }
    }


}


public class Main {

    public static void main(String[] args) {
        Sound.playSound("funny_birds.wav");
        new Window();
    }
}

class Window extends JFrame {

    public Window()  {
        setExtendedState(MAXIMIZED_BOTH);
        setTitle("Фани Бёд");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);

        Panel p = new Panel();
        add(p);
        p.addMouseListener(p);

        addKeyListener(p);

        revalidate();
    }
}


abstract class Actor {

    protected Sprite s;
    protected RectangularCollider collider;

    protected boolean active = true;
    protected int hp = 100;
    protected int damage = 30;

    public void damage(int d) {
        hp -= d;
        if(hp <= 0) {
            die();
        }
    }

    public void up () {
        s.setAlpha(-Math.PI/2);
    }
    public void down () {
        s.setAlpha(Math.PI/2);
    }
    public void left () {
        s.setAlpha(Math.PI);
    }
    public void right () {
        s.setAlpha(0);
    }

    public void die() {
        hp = 0;
        active = false;
    }

    Actor (double x, double y, String fileName, Point p, int pad) {
        s = new Sprite(x, y, fileName, p);
        collider = new RectangularCollider(x, y, 100, 100);
    }

    public void show() {
        s.show();
    }

    public void hide() {
        s.hide();
    }

    public void paint (Graphics g) {
        if(!active) {
            return;
        }
        s.paint(g);
    }


    public abstract void onIntersects(Actor act);

    public final void intersects(Actor act) {

        if( collider.intersects(act.collider) && active && act.active ) {
            onIntersects(act);
            act.onIntersects(this);
        }
    }

    public void update (int ms) {
        if(!active) {
            return;
        }

        s.update(ms);
        // Лучше использовать геттеры
        collider.update(s.x, s.y);
    }
}

class PlayerBird extends  Actor {


    @Override
    public void up() {
        super.up();
        Sound.playSound("jump.wav");
    }

    @Override
    public void down() {
        super.down();
        Sound.playSound("jump.wav");
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.GREEN);
        g.fillRect((int)s.getX(), (int)s.getY() + s.frameHeight,  (int)(hp/100.0 * s.frameWidth), 3   );
    }

    @Override
    public void update(int ms) {
        super.update(ms);

        if(s.getY() < 0) {
            s.y = 0;
            down();
        }
        else if(s.getY() > 700) {
            s.y = 700;
            up();
        }
    }

    PlayerBird(double x, double y) {
        super(x, y, "player.png", new Point(0, 0), 5);
        // Сделать сеттеры для этих полей
        s.frameWidth = 110;
        s.frameHeight = 101;

        int frame = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 5; j++, frame++) {
                if (frame == 0 || frame == 14) {
                    continue;
                }
                s.addFrame( new Point(j * s.frameWidth, i * s.frameHeight)  )   ;

            }

        }

        up();

    }

    @Override
    public void onIntersects(Actor act) {
            damage( act.damage  );
    }
}


class KeyState {
   private boolean [] states = new boolean[256];
   private boolean [] oldStates = new boolean[256];

   public void setKeyState (int keyCode, boolean state) {
       states[keyCode] = state;
   }

   public void update() {
       oldStates = (boolean []) states.clone();
   }

   public boolean keyDown(int keyCode) {
       return  states[keyCode];
   }



   public boolean press(int keyCode) {
        return   !oldStates[keyCode]  && states[keyCode]  ;
   }
    public boolean realise(int keyCode) {
        return   oldStates[keyCode]  && !states[keyCode]  ;
    }


}




class RectangularCollider  {

    double x, y;
    int w, h;
    int pad;


    public RectangularCollider(double x, double y, int w, int h, int pad) {
        this(x,  y,  w,  h);
        this.pad = pad;
    }
    public RectangularCollider(double x, double y, int w, int h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }

    public void update (double x, double y) {
        this.x = x;
        this.y = y;
    }

    private Rectangle getRectangle () {
        return new Rectangle((int)x + pad, (int)y + pad, w - pad*2, h - pad*2);
    }


    public boolean intersects(RectangularCollider collider) {
         return  getRectangle().intersects( collider.getRectangle() );

    }
}



class Panel extends JPanel implements KeyListener, MouseListener {

    Menu menu = new Menu();


    KeyState keyState = new KeyState();
    Texture bird = new Texture(100, 0, "player_frame.png");
    Texture bird2 = new Sprite(100, 300, "player.png");
    Actor act = new PlayerBird(300, 400);
    Actor act2 = new EnemyBird(300, 400);

    long t1, t2;

    int elapsedTime = 0;
    int time = 2000;

    public Panel () {
        setFocusable(true);
        setBackground(Color.BLACK);
        t1 = System.currentTimeMillis();
    }

    private void controlGame() {

        if(keyState.keyDown(KeyEvent.VK_UP) ) {
            act.up();
        }
        else if(keyState.keyDown(KeyEvent.VK_DOWN) ) {
            act.down();
        }


        act.show();

        if (keyState.keyDown(KeyEvent.VK_SPACE) ) {
            act.hide();


        }

        keyState.update();
    }

    private void updateGame (int ms) {

             
        act.update(ms);

        act2.update(ms);

        act.intersects(act2);



    }

    private void paintGame (Graphics g) {
        act.paint(g);
        act2.paint(g);

        menu.paint(g);
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        t2 = System.currentTimeMillis();
        int ms = (int)(t2-t1);

          /*  elapsedTime += ms;

            if (elapsedTime >= time) {
                elapsedTime = 0;

            }*/

        controlGame();
        updateGame(ms);
        paintGame(g);


        t1 = t2;
        repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        keyState.setKeyState( e.getKeyCode(), true);

        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            menu.next();
        }
        else if(e.getKeyCode() == KeyEvent.VK_UP) {
            menu.prev();
        }
        else if(e.getKeyCode() == KeyEvent.VK_ENTER) {
            // Если в режиме меню
            menu.select();
        }
        else if(e.getKeyCode() == KeyEvent.VK_PAUSE) {
            // Если в режиме меню
            menu.toggle();
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        keyState.setKeyState( e.getKeyCode(), false);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();

        if ( x > act.collider.x && x < act.collider.x + act.collider.w) {
            act.hide();
        }

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
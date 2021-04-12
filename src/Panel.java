import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Panel extends JPanel implements KeyListener {

    Menu menu = new Menu();

    KeyState keyState = new KeyState();
    Actor act = new PlayerBird(300, 400);
    Actor act3 = new PlayerBird(0, 400);
    Actor act2 = new EnemyBird(300, 400);

    long t1, t2;

    public Panel() {
        setFocusable(true);
        setBackground(Color.BLACK);
        t1 = System.currentTimeMillis();
    }

    private void controlGame() {

        if (keyState.keyDown(KeyEvent.VK_UP)) {
            act.up();
        } else if (keyState.keyDown(KeyEvent.VK_DOWN)) {
            act.down();
        }


        act.show();

        if (keyState.keyDown(KeyEvent.VK_SPACE)) {
            act.hide();


        }

        keyState.update();
    }

    private void updateGame(int ms) {


        act.update(ms);

        act2.update(ms);

        act.intersects(act2);


    }

    private void paintGame(Graphics g) {
        act.paint(g);
        act2.paint(g);

        menu.paint(g);
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        t2 = System.currentTimeMillis();
        int ms = (int) (t2 - t1);

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

        keyState.setKeyState(e.getKeyCode(), true);

        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            menu.next();
        } else if (e.getKeyCode() == KeyEvent.VK_UP) {
            menu.prev();
        } else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            // Если в режиме меню
            menu.select();
        } else if (e.getKeyCode() == KeyEvent.VK_PAUSE) {
            // Если в режиме меню
            menu.toggle();
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        keyState.setKeyState(e.getKeyCode(), false);
    }
}

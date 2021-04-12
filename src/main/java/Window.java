import javax.swing.*;

public class Window extends JFrame {

    public Window() {
        initMetaInfo();

        Panel p = new Panel();
        add(p);
        addKeyListener(p);

        revalidate();

//        while (true) {
//            try {
//                repaint();
//                wait(10);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//                break;
//            }
//        }
    }

    private void initMetaInfo() {
        setExtendedState(MAXIMIZED_BOTH);
        setTitle("Фани Бёд");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }
}

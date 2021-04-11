import javax.swing.*;

public class Window extends JFrame {

    public Window() {
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

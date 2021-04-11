import java.awt.*;

public class Menu {

    String[] options = {"Игра", "еще один пункт", "Выход"};
    int index = 0;

    public void next() {
        index++;
        if (index > options.length - 1) {
            index = 0;
        }
    }

    public void prev() {
        index--;
        if (index < 0) {
            index = 0;
        }
    }

    public int select() {
        active = false;
        return index;
    }

    public void toggle() {
        active = !active;
    }

    private Font f = new Font(Font.SERIF, Font.BOLD, 32);
    private boolean active;
    private int margin = 500;

    public void paint(Graphics g) {
        if (active) {
            g.setFont(f);

            for (int i = 0; i < options.length; i++) {
                if (i == index) {
                    g.setColor(Color.RED);
                } else {
                    g.setColor(Color.WHITE);
                }
                g.drawString(options[i], margin, margin + i * 50);
            }

        }
    }


}

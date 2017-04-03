import javax.swing.*;
import java.awt.*;

/**
 * Created by aslak on 03.04.17.
 */
public class GameFrame extends JFrame {
    JButton knapp1 = new JButton("TestButton");

    public GameFrame() {
        ;
        this.add(knapp1);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setSize(1280, 720);
        this.setLayout(new GridLayout());
        this.setVisible(true);
    }
}

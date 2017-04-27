import javax.swing.*;
import java.awt.*;

/**
 * Created by aslak on 27.04.17.
 */
public class GoldView {


    JLabel player1GoldLabel;
    JLabel player2GoldLabel;

    public GoldView() {

        // Gold label setup
        player1GoldLabel = new JLabel();
        player1GoldLabel.setForeground(Color.WHITE);
        player2GoldLabel = new JLabel();
        player2GoldLabel.setForeground(Color.WHITE);
    }

    /**
     * This methods takes in a image and returns a JPanel with a ImageIcon on it
     *
     * @param image: gets a image
     * @return: returns a JPanel with a ImageIcon attached to it
     */
    JPanel makeGoldIcon(Image image) {

        JPanel goldIcon = new JPanel();
        JLabel goldLabel = new JLabel(new ImageIcon(image));
        goldIcon.setBackground(new Color(0, 0, 0, 0));
        goldIcon.add(goldLabel);

        return goldIcon;
    }
}

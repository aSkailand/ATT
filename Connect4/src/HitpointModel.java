import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by aslak on 25.04.17.
 */
public class HitpointModel {

    ArrayList<JPanel> heartListPlayer1 = new ArrayList<>();
    ArrayList<JPanel> heartListPlayer2 = new ArrayList<>();
    JLabel tempHeart;
    BufferedImage heart;

    public HitpointModel() {


        try {
            heart = ImageIO.read(new File("heart.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Image scaledHeart = heart.getScaledInstance(40, 40, Image.SCALE_DEFAULT);
        ImageIcon heartIcon = new ImageIcon(scaledHeart);


        for (int i = 0; i < 5; i++) {

            tempHeart = new JLabel();
            tempHeart.setIcon(heartIcon);
            JPanel tempPanel = new JPanel();
            tempPanel.setLayout(new BorderLayout());
            tempPanel.setBackground(Color.RED);
            tempPanel.add(tempHeart);
            heartListPlayer1.add(tempPanel);
        }

        for (int i = 0; i < 5; i++) {
            tempHeart = new JLabel();
            tempHeart.setIcon(heartIcon);
            JPanel tempPanel = new JPanel();
            tempPanel.setLayout(new BorderLayout());
            tempPanel.setBackground(Color.BLUE);
            tempPanel.add(tempHeart);
            heartListPlayer2.add(tempPanel);
        }
    }
}

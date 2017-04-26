import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by aslak on 25.04.17.
 */
public class HitpointModel {

    ArrayList<JPanel> heartListPlayer1 = new ArrayList<>();
    ArrayList<JPanel> heartListPlayer2 = new ArrayList<>();

    ArrayList<JLabel> heartsPlayer1 = new ArrayList<>();
    ArrayList<JLabel> heartsPlayer2 = new ArrayList<>();

    JLabel tempHeart;
    BufferedImage heart;
    BufferedImage deadHeart;
    ImageIcon deadHeartIcon;
    ImageIcon heartIcon;

    private int playersHitpoints = 100;
    private int player_1Hitpoints = playersHitpoints;
    private int player_2Hitpoints = playersHitpoints;

    public HitpointModel() {

        try {
            deadHeart = ImageIO.read(new File("heartdeadd.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Image scaledDeadHeart = deadHeart.getScaledInstance(40,40,Image.SCALE_DEFAULT);
        deadHeartIcon = new ImageIcon(scaledDeadHeart);


        try {
            heart = ImageIO.read(new File("heart.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Image scaledHeart = heart.getScaledInstance(40, 40, Image.SCALE_DEFAULT);
        heartIcon = new ImageIcon(scaledHeart);

        // Loads JLabesl and JPanels with ImageIcons and stores them in a ArrayList<>()
        for (int i = 0; i < 5; i++) {
            tempHeart = new JLabel();
            tempHeart.setIcon(heartIcon);
            heartsPlayer1.add(tempHeart);
            JPanel tempPanel = new JPanel();
            tempPanel.setLayout(new BorderLayout());
            tempPanel.setBackground(Color.RED);
            tempPanel.add(tempHeart);
            heartListPlayer1.add(tempPanel);
        }
        for (int i = 0; i < 5; i++) {
            tempHeart = new JLabel();
            tempHeart.setIcon(heartIcon);
            heartsPlayer2.add(tempHeart);
            JPanel tempPanel = new JPanel();
            tempPanel.setLayout(new BorderLayout());
            tempPanel.setBackground(Color.BLUE);
            tempPanel.add(tempHeart);
            heartListPlayer2.add(tempPanel);
        }
    }

    public int getPlayersHitpoints() {
        return playersHitpoints;
    }

    public void setPlayersHitpoints(int playersHitpoints) {
        this.playersHitpoints = playersHitpoints;
    }

    public int getPlayer_1Hitpoints() {
        return player_1Hitpoints;
    }

    public void setPlayer_1Hitpoints(int player_1Hitpoints) {
        this.player_1Hitpoints = player_1Hitpoints;
    }

    public int getPlayer_2Hitpoints() {
        return player_2Hitpoints;
    }

    public void setPlayer_2Hitpoints(int player_2Hitpoints) {
        this.player_2Hitpoints = player_2Hitpoints;
    }
}

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
public class HitPointsModel {

    ArrayList<JLabel> heartsLabelPlayer1 = new ArrayList<>();
    ArrayList<JLabel> heartsLabelPlayer2 = new ArrayList<>();

    ImageIcon heartAliveIcon;
    ImageIcon heartDeadIcon;

    private int playersHitpoints = 100;
    private int player_1Hitpoints = playersHitpoints;
    private int player_2Hitpoints = playersHitpoints;

    public HitPointsModel() {

        heartAliveIcon = loadImageIcon(new File("heart.png"), 40, 40);
        heartDeadIcon = loadImageIcon(new File("heartdeadd.png"), 40, 40);

        addLabelToArrayList(heartsLabelPlayer1);
        addLabelToArrayList(heartsLabelPlayer2);
    }

    void addLabelToArrayList(ArrayList<JLabel> playerHeartLabelArrayList){
        for (int i = 0; i < 5; i++) {
            JLabel tempHeart = new JLabel();
            tempHeart.setIcon(heartAliveIcon);
            playerHeartLabelArrayList.add(tempHeart);
        }
    }

    // todo: move to a more common class/model
    /**
     * Makes a imageIcon of image file.
     * @param file: the image file.
     * @param imageWidth: Sets the width of imageIcon.
     * @param imageHeight: Sets the height of imageIcon.
     * @return the product imageIcon.
     */
    ImageIcon loadImageIcon(File file, int imageWidth, int imageHeight) {

        BufferedImage bufferedImage;
        try {
            bufferedImage = ImageIO.read(file);
        } catch (IOException e) {
            System.out.println("ImageIcon is null!");
            return null;
        }

        if (bufferedImage == null) {
            System.out.println("Image is null!");
            return null;
        } else {
            Image image = bufferedImage.getScaledInstance(imageWidth, imageHeight, Image.SCALE_DEFAULT);
            return new ImageIcon(image);
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

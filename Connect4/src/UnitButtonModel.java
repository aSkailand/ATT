import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by aslak on 27.04.17.
 */
public class UnitButtonModel {

    ArrayList<JButton> unitButtonsPlayer1 = new ArrayList<>();
    ArrayList<JButton> unitButtonsPlayer2 = new ArrayList<>();

    ArrayList<JLabel> unitLabelPlayer1 = new ArrayList<>();
    ArrayList<JLabel> unitLabelPlayer2 = new ArrayList<>();

    private int unit1Cost = 1;
    private int unit2Cost = 5;
    private int unit3Cost = 10;

    private int magic1Cost = 3;
    private int magic2Cost = 7;
    private int magic3Cost = 9;


    ImageIcon testImg;


    public UnitButtonModel() {

        testImg = loadImageIcon(new File("boxing.png"), 30, 30);

        addButtonsToList(unitButtonsPlayer1);
        addLabelsToList(unitLabelPlayer1);

        addButtonsToList(unitButtonsPlayer2);
        addLabelsToList(unitLabelPlayer2);
    }

    /**
     * stores 6 JButtons to a array list
     *
     * @param playerButtonsList: array list to hold the JButtons
     */
    void addButtonsToList(ArrayList<JButton> playerButtonsList) {
        for (int i = 0; i < 6; i++) {
            JButton tempButton = new JButton();
            playerButtonsList.add(tempButton);
        }
    }

    /**
     * stores 8 JButtons to a array list
     *
     * @param playerLabelList: array list to hold the JLabels
     */
    void addLabelsToList(ArrayList<JLabel> playerLabelList) {
        for (int i = 0; i < 8; i++) {
            JLabel tempLabel = new JLabel();
            playerLabelList.add(tempLabel);
        }
    }

    // loads a image and returns a ImageIcon

    /**
     * this method loads a image and retruns a Image Icon
     *
     * @param file:        what image to load
     * @param imageWidth:  wanted width of the image icon
     * @param imageHeight: wanted height of the image icon
     * @return: returns a image icon
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
            Image image = bufferedImage.getScaledInstance(imageWidth, imageHeight, Image.SCALE_SMOOTH);
            return new ImageIcon(image);
        }
    }

    // getters and setters

    public int getUnit1Cost() {
        return unit1Cost;
    }

    public int getUnit2Cost() {
        return unit2Cost;
    }

    public int getUnit3Cost() {
        return unit3Cost;
    }

    public int getMagic1Cost() {
        return magic1Cost;
    }

    public int getMagic2Cost() {
        return magic2Cost;
    }

    public int getMagic3Cost() {
        return magic3Cost;
    }
}

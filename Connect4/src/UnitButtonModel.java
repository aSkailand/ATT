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

    ImageIcon testImg;


    public UnitButtonModel() {

        testImg = loadImageIcon(new File("boxing.png"),60,60);

        addButtonsToList(unitButtonsPlayer1);
        addLabelsToList(unitLabelPlayer1);

        addButtonsToList(unitButtonsPlayer2);
        addLabelsToList(unitLabelPlayer2);


    }

    void addButtonsToList(ArrayList<JButton> playerButtonsList) {
        for (int i = 0; i < 6; i++) {
            JButton tempButton = new JButton();
            playerButtonsList.add(tempButton);
        }
    }

    void addLabelsToList(ArrayList<JLabel> playerLabelList) {
        for (int i = 0; i < 8; i++) {
            JLabel tempLabel = new JLabel();
            playerLabelList.add(tempLabel);
        }
    }

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
}

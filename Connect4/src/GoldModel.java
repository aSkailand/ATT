import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by aslak on 27.04.17.
 */
public class GoldModel {

    int player1Gold = 10;
    int player2Gold = 10;


    public GoldModel() {


    }

    /**
     * This method lods a image and sets the width and height and returns the image.
     *
     * @param file:   a new file is required.
     * @param width:  sets the wanted width.
     * @param height: sets the wanted height.
     * @return: returns the image with wanted dimensions.
     */
    Image loadGoldIcon(File file, int width, int height) {

        BufferedImage bufferedImage = null;
        try {
            bufferedImage = ImageIO.read(file);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("No image found!");
        }

        if (bufferedImage == null) {
            System.out.println("image is null");
            return null;
        } else {
            return bufferedImage.getScaledInstance(width, height, Image.SCALE_DEFAULT);
        }
    }

    // Getters and setters
    public int getPlayer1Gold() {
        return player1Gold;
    }

    public void setPlayer1Gold(int player1Gold) {
        this.player1Gold = player1Gold;

    }

    public int getPlayer2Gold() {
        return player2Gold;
    }

    public void setPlayer2Gold(int player2Gold) {
        this.player2Gold = player2Gold;
    }

}

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

/**
 * Created by aslak on 20.04.17.
 * <p>
 * This loads the images from files and make them available for usage...
 */
public class GameBodyModel {

    JButton playerOneAvatarButton = new JButton();
    JButton playerTwoAvatarButton = new JButton();


    public GameBodyModel() {

        playerOneAvatarButton.setBorder(new EmptyBorder(0, 0, 0, 0));
        playerOneAvatarButton.setBorderPainted(false);
        playerOneAvatarButton.setContentAreaFilled(false);

        ImageIcon p1_avatar = loadImageIcon(new File("man.png"), 150, 150);
        playerOneAvatarButton.setIcon(p1_avatar);

        playerTwoAvatarButton.setBorder(new EmptyBorder(0, 0, 0, 0));
        playerTwoAvatarButton.setBorderPainted(false);
        playerTwoAvatarButton.setContentAreaFilled(false);

        ImageIcon p2_avatar = loadImageIcon(new File("link.png"),150,150);
        playerTwoAvatarButton.setIcon(p2_avatar);



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
            Image image = bufferedImage.getScaledInstance(imageWidth, imageHeight, Image.SCALE_AREA_AVERAGING);
            return new ImageIcon(image);
        }
    }

    public String readHighScore() {
        String highscoreText = "";

        try {
            FileReader read = new FileReader("highscore.txt");
            BufferedReader bRead = new BufferedReader(read);
            String line = bRead.readLine();
            while (line != null) {
                highscoreText += line + "\n";
                line = bRead.readLine();
            }

            bRead.close();
            read.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return highscoreText;

    }

    public void setHighScore(String a) {
        System.out.println(a);

        try {
            FileWriter fileWriter = new FileWriter("highscore.txt", true);
            fileWriter.write(a);
            fileWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

/**
 * Created by aslak on 20.04.17.
 *
 * This loads the images from files and make them avalible for usage...
 */
public class GameBodyModel {

    JLabel playerTwoLabel = new JLabel();
    JLabel playerOneLabel = new JLabel();
    JLabel redGoldLabel = new JLabel();
    JLabel blueGoldLabel = new JLabel();


    BufferedImage playerOneAvatar = null;
    BufferedImage playerTwoAvatar = null;
    BufferedImage redGold = null;
    BufferedImage blueGold = null;

    public GameBodyModel() {


        //Loads player one avatar
        try {
            playerOneAvatar = ImageIO.read(new File("fuck.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Loads player two avatar
        try {
            playerTwoAvatar = ImageIO.read(new File("fuck2.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Loads red gold image
        try {
            redGold = ImageIO.read(new File("redGold.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Loads blue gold image
        try {
            blueGold = ImageIO.read(new File("blueGold.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }


        // Sets the size and layout for player one avatar
        Image playerOneAvatar2 = playerOneAvatar.getScaledInstance(90, 30, Image.SCALE_FAST);
        ImageIcon imageIcon = new ImageIcon(playerOneAvatar2);
        playerOneLabel.setLayout(new BorderLayout());
        playerOneLabel.setIcon(imageIcon);

        // Sets the size and layout for player two avater
        Image playerTwoAvatar2 = playerTwoAvatar.getScaledInstance(90, 30, Image.SCALE_FAST);
        ImageIcon imageIcon1 = new ImageIcon(playerTwoAvatar2);
        playerTwoLabel.setLayout(new BorderLayout());
        playerTwoLabel.setIcon(imageIcon1);

        Image redGoldResized = redGold.getScaledInstance(30,30, Image.SCALE_DEFAULT);
        ImageIcon imageIconRedGold = new ImageIcon(redGoldResized);
        redGoldLabel.setLayout(new BorderLayout());
        redGoldLabel.setIcon(imageIconRedGold);

        Image blueGoldResized = blueGold.getScaledInstance(30,30, Image.SCALE_DEFAULT);
        ImageIcon imageIconBlueGold = new ImageIcon(blueGoldResized);
        blueGoldLabel.setLayout(new BorderLayout());
        blueGoldLabel.setIcon(imageIconBlueGold);

    }

    public String readHighScore() {
        String highscoreText = "";

        try {
            FileReader read = new FileReader("highscore.txt");
            BufferedReader bRead = new BufferedReader(read);
            String line = bRead.readLine();
            while(line != null){
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

    public void setHighScore(String a){
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

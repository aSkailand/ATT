import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by aslak on 20.04.17.
 *
 * This loads tha images from files and make them avalible for usage...
 */
public class GameBodyModel {

    JPanel playerOnePanel = new JPanel();
    JPanel playerTwoPanel = new JPanel();
    JLabel playerOneLabel;
    JLabel playerTwoLabel;

    BufferedImage playerOneAvatar = null;
    BufferedImage playerTwoAvatar = null;

    public GameBodyModel(){

        playerTwoLabel = new JLabel();
        playerOneLabel = new JLabel();

        playerOnePanel.setLayout(new GridLayout());

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

        // Sets the size and layout for player one avatar
        Image playerOneAvatar2 = playerOneAvatar.getScaledInstance(50,50,Image.SCALE_FAST);
        ImageIcon imageIcon = new ImageIcon(playerOneAvatar2);
        playerOneLabel.setLayout(new BorderLayout());
        playerOneLabel.setIcon(imageIcon);

        // Sets the size and layout for player two avater
        Image playerTwoAvatar2 = playerTwoAvatar.getScaledInstance(50,50,Image.SCALE_FAST);
        ImageIcon imageIcon1 = new ImageIcon(playerTwoAvatar2);
        playerTwoPanel.setLayout(new BorderLayout());
        playerTwoLabel.setIcon(imageIcon1);





    }
}
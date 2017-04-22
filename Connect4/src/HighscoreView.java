import javax.swing.*;
import java.awt.*;

/**
 * Created by aslak on 22.04.2017.
 */
public class HighscoreView extends JFrame {

    JTextArea highscoreText = new JTextArea();
    GameBodyModel gameBodyModel;

    public HighscoreView() {

        gameBodyModel = new GameBodyModel();

        this.setLayout(new GridLayout(1,1));
        this.add(highscoreText);
        this.setSize(200,250);

        highscoreText.setText(gameBodyModel.readHighScore());

        this.setVisible(true);

    }
}

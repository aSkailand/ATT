import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * Created by aslak on 18.04.2017.
 */
public class GameTimer implements ActionListener {

    GameBodyFrame gameBodyFrame;

    int roundTime = 30;
    String currentTime = "Time: "+roundTime;

    Timer gameTimer = new Timer(1000, this);

    public GameTimer() {
        gameBodyFrame = new GameBodyFrame();
        gameTimer.start();
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(gameTimer)) {
            System.out.println(currentTime);
            roundTime--;
            currentTime = "Time: "+roundTime;

            gameBodyFrame.timer.setText(currentTime);
        }
    }
}

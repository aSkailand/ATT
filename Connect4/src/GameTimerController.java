import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by aslak on 19.04.17.
 * This is the timer for the game, it counts down from 30 sec to 0 with 1 seconds interval then resets.
 */
public class GameTimerController implements ActionListener {

    GameBodyFrame gameBodyFrame;
    Timer playerTurnTimer = new Timer(1000, this);
    int currentTime = 30;

    public GameTimerController(GameBodyFrame gbFrame) {

        gameBodyFrame = gbFrame;
        playerTurnTimer.start();
        playerTurnTimer.setActionCommand("time");
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "time": {

                gameBodyFrame.timer.setText("Time left: " + currentTime + " Seconds...");

                currentTime--;

                if (currentTime < 0) {
                    currentTime = 30;
                }
                break;
            }
            case "resetTimer": {

            }
        }
    }
}

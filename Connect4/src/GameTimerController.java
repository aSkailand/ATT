import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by aslak on 19.04.17.
 * This is the timer for the game, it counts down from 30 sec to 0 with 1 seconds interval then resets.
 */
public class GameTimerController implements ActionListener {

    GameBodyFrame gameBodyFrame;
    Timer playerTurnTimer = new Timer(250, this);


    int currentTime = 30;
    int totalTime = 0;
    int seconds = 0;
    int minutes = 0;

    public GameTimerController(GameBodyFrame gbFrame) {

        gameBodyFrame = gbFrame;
        playerTurnTimer.setActionCommand("time");
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "time": {

                gameBodyFrame.timer.setText("Timer: " + currentTime);
                gameBodyFrame.totalTimer.setText("Total time: " + minutes + ":" + seconds);

                currentTime--;
                totalTime++;
                seconds++;

                if (seconds == 60) {
                    minutes++;
                    seconds = 0;
                }

                if (currentTime < 0) {

                    gameBodyFrame.timer.setText("Timer: 30");
                    System.out.println("Time is up");
                    currentTime = 29;
                }



                break;
            }
        }
    }
}

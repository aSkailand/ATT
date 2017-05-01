import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by aslak on 19.04.17.
 * This is the timer for the game, it counts down from 30 sec to 0 with 1 seconds interval then resets.
 */
public class GameTimerController implements ActionListener {

    GameBodyController gameBodyController;
    GameBodyFrame gameBodyFrame;
    Timer playerTurnTimer = new Timer(1000, this);
    JButton timer;

    int currentTime = 30;
    int totalTime = 0;
    int seconds = 0;
    int minutes = 0;


    public GameTimerController(GameBodyController gameBodyController) {

        // Declare MVC
        this.gameBodyController = gameBodyController;
        gameBodyFrame = this.gameBodyController.gameBodyFrame;

        // JButton Setup
        timer = new JButton("<html>END<br/>TURN</html>" +currentTime);

        timer.setBorderPainted(false);
        timer.setFocusPainted(false);
        timer.setBorder(BorderFactory.createEmptyBorder());
        timer.setBackground(Color.DARK_GRAY.darker());
        timer.setForeground(Color.WHITE);
        timer.setFont(gameBodyController.gameBodyModel.loadFont());

        //todo: DELETE? Temporary Function by Trong:
        timer.addActionListener(
                e -> gameBodyController.gameBoardController.roundEnd());


        gameBodyFrame.timerPanel.add(timer);

    }


    @Override
    public void actionPerformed(ActionEvent e) {

        timer.setText("" + currentTime);

        currentTime--;
        totalTime++;
        seconds++;

        if (seconds == 60) {
            minutes++;
            seconds = 0;
        }
        if (currentTime < 0) {

            timer.setText("End turn");
            System.out.println("Time is up");
            currentTime = 29;
        }
        if (currentTime < 10) {
            timer.setText("0" + currentTime);
        }

    }
}

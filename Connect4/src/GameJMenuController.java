import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;

/**
 * Created by aslak on 18.04.17.
 */
public class GameJMenuController implements ActionListener {

    GameJMenu gameJMenu;
    GameBodyFrame gameBodyFrame;
    GameTimerController gameTimerController;

    //This boolean is for the pause/unpause button, when its true its unpaused, if its false its paused
    boolean pauseBoolean = true;


    public GameJMenuController(GameBodyFrame gbFrame) {

        gameTimerController = new GameTimerController(gbFrame);

        gameJMenu = new GameJMenu(this);
        gameBodyFrame = gbFrame;
        gameBodyFrame.setJMenuBar(gameJMenu);

        gameBodyFrame.revalidate();
        gameBodyFrame.repaint();
    }

    // ActionListener for the JMenuBar
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "mainMenu": {
                System.out.println("Main menu...");
                break;
            }
            case "highscore": {
                System.out.println("Highscore...");
                break;
            }
            case "saveGame": {
                System.out.println("Saved game...");
                break;
            }
            case "openGame": {
                System.out.println("Open game...");
                break;
            }
            case "pauseGame": {
                System.out.println("Paused game...");
                if(pauseBoolean==true) {
                    gameTimerController.playerTurnTimer.stop();
                    pauseBoolean = false;
                }
                else{
                    gameTimerController.playerTurnTimer.start();
                    pauseBoolean = true;
                }
                break;
            }
            case "startTimer": {
                System.out.println("Started timer");
                gameTimerController.playerTurnTimer.start();
                pauseBoolean = true;
                break;
            }
            case "resetTimer": {
                System.out.println("Timer reset...");
                gameTimerController.playerTurnTimer.stop();
                gameTimerController.currentTime = 29;
                gameBodyFrame.timer.setText("Time left: 30 Seconds...");
                pauseBoolean = true;
                break;
            }
            case "restartGame": {
                System.out.println("Restarted game...");
                break;
            }
        }
    }
}
